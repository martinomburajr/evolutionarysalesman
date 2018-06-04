import java.util.ArrayList;
import java.util.Random;

public class Crossover {
    public static Chromosome[] cutAndCrossFillCrossover(Chromosome parent1, Chromosome parent2, City[] cities, int nPoint) {
        Chromosome[] children = new Chromosome[2];

        ArrayList<Integer> parent1List = Utility.toArrayList(parent1.cityList);
        ArrayList<Integer> parent2List = Utility.toArrayList(parent2.cityList);

        if(nPoint < 1) {
            nPoint = 1;
        }
        if(nPoint > 49) {
            nPoint = 49;
        }

        ArrayList<Integer> child1 = performCrossover(parent1List, parent2List, nPoint);
        ArrayList<Integer> child2 = performCrossover(parent2List, parent1List, nPoint);

        int[] child1Arr = Utility.toArray(child1);
        int[] child2Arr = Utility.toArray(child2);

        Chromosome childChromosome1 = new Chromosome(child1Arr, cities);
        Chromosome childChromosome2 = new Chromosome(child2Arr, cities);

        children[0] = childChromosome1;
        children[1] = childChromosome2;

        return children;
    }

    public static Chromosome[] inverOverCrossover(Chromosome[] chromosomes, City[] cities) {
        Random random = new Random();
        float probability = 0.5f;
        float randFloat = random.nextFloat();
        for (int i = 0; i < chromosomes.length; i++) {
            Chromosome testChrom = chromosomes[i].deepCopy();
            int randomCityIndex = random.nextInt(49);
            int c = testChrom.cityList[randomCityIndex];

            while(true) {
                int cPrimeRandom = random.nextInt(49);
                int cPrime = -1;
                if(randFloat <= probability) {
                    cPrime = testChrom.cityList[cPrimeRandom];
                }else {
                    int randomChromosomeIndex = random.nextInt(100);
                    Chromosome randomChromosome = chromosomes[randomChromosomeIndex];
                    //Search for c in a randomly selected new chromosome
                    for (int i1 = 0; i1 < randomChromosome.cityList.length; i1++) {
                        if (i1 == randomChromosome.cityList.length - 1) {
                            if (randomChromosome.cityList[i1] == c) {
                                cPrime = randomChromosome.cityList[0];
                                break;
                            }
                        } else {
                            if (randomChromosome.cityList[i1] == c) {
                                cPrime = randomChromosome.cityList[i1 + 1];
                                break;
                            }
                        }
                    }
                }

                if(randomCityIndex > 0) {
                    if(testChrom.cityList[randomCityIndex+1] == cPrime || testChrom.cityList[randomCityIndex-1] == cPrime) {
                        break;
                    }else{
                        int indexOfNewCityInCandidateChromosome = -1;
                        for (int i1 = 0; i1 < testChrom.cityList.length; i1++) {
                            if(testChrom.cityList[i1] == cPrime) {
                                indexOfNewCityInCandidateChromosome = i1;
                                break;
                            }
                        }
                        //Perform inversion
                        if(randomCityIndex < indexOfNewCityInCandidateChromosome) {
                            int[] newChild = Mutation.splitReverseInsert(testChrom.cityList, randomCityIndex + 1, indexOfNewCityInCandidateChromosome +1);
                            testChrom.cityList = newChild;
                        }else{
                            int[] newChild = Mutation.splitReverseInsert(testChrom.cityList,indexOfNewCityInCandidateChromosome+1,randomCityIndex +1);
                            testChrom.cityList = newChild;
                        }
                    }
                }else{
                    if(testChrom.cityList[randomCityIndex+1] == cPrime) {
                        break;
                    }else{
                        int indexOfNewCityInCandidateChromosome = -1;
                        for (int i1 = 0; i1 < testChrom.cityList.length; i1++) {
                            if(testChrom.cityList[i1] == cPrime) {
                                indexOfNewCityInCandidateChromosome = i1;
                            }
                        }
                        //Perform inversion
                        if(randomCityIndex < indexOfNewCityInCandidateChromosome) {
                            int[] newChild = Mutation.splitReverseInsert(testChrom.cityList, randomCityIndex + 1, indexOfNewCityInCandidateChromosome +1);
                            testChrom.cityList = newChild;
                        }else{
                            int[] newChild = Mutation.splitReverseInsert(testChrom.cityList,indexOfNewCityInCandidateChromosome+1,randomCityIndex +1);
                            testChrom.cityList = newChild;
                        }
                    }
                }
                c = cPrime;
            }
            testChrom.calculateCost(cities);
            if(testChrom.getCost() < chromosomes[i].getCost()){
                chromosomes[i].cityList = testChrom.cityList;
            }
        }
        return chromosomes;
    }

    public static Chromosome greedyCrossover(Chromosome a, Chromosome b, City[] cities) {
        Random random = new Random();
        int aX = random.nextInt(a.cityList.length);
        int t = a.cityList[aX];
        int bY = Utility.findCityInChromosome(b, t);

        ArrayList<Integer> finalChild = new ArrayList<>();

        finalChild.add(t);

        int[] forwardB = Utility.wrapAroundReverse(b.cityList, bY);
        int[] reverseA = Utility.wrapAroundReverse(a.cityList, aX);

        ArrayList<Integer> mixedCrossover = performMixedCrossover(finalChild, forwardB, reverseA);
        int[] child = Utility.toArray(mixedCrossover);
        return new Chromosome(child,cities);
    }


    public static ArrayList<Integer> performMixedCrossover(ArrayList<Integer> finalChild, int[] forwardB, int[] reverseA) {
        int i = 1;
        while(true) {
            if (!finalChild.contains(reverseA[i])) {
                finalChild.add(0,reverseA[i]);
            }
            if (!finalChild.contains(forwardB[i])) {
                finalChild.add(forwardB[i]);
            }
            if(finalChild.size() == forwardB.length && Utility.hasEverything(forwardB,finalChild)) {
                break;
            }
            i++;
        }
        return finalChild;
    }

    /**
     *
     * @param population
     * @param cities
     * @return
     */
    public static Chromosome[] micro(Chromosome [] population, City[] cities) {
        Random random = new Random();
        for (int i = 0; i < population.length; i++) {
            //Obtain chromosome in order
            Chromosome chromosome = population[i];
            //Select the first random genotype
            int randChrom1GenotypeIndex1 = random.nextInt(50);
            //Obtain a random chromosome to compare with
            int rand = random.nextInt(population.length);
            if(rand != i) {
                //Random GenotypeIndex
                int randChrom1GenotypeIndex2;
                if(randChrom1GenotypeIndex1+1 < 50) {
                    randChrom1GenotypeIndex2 = randChrom1GenotypeIndex1 + 1;
                }else{
                    randChrom1GenotypeIndex2 = 0;
                }
                int randChrom1Genotype1 = chromosome.cityList[randChrom1GenotypeIndex1];
                int randChrom1Genotype2 = chromosome.cityList[randChrom1GenotypeIndex2];
                double adjacentCost = Utility.calculateCost(randChrom1Genotype1, randChrom1Genotype2, cities);

                //Obtain the comparator chromosome2
                int chromosome2Index = random.nextInt(population.length);
                Chromosome chromosome2 = population[chromosome2Index];

                double adjacentCost2 = 1000000000d;
                int nextCity = -1;
                int i1;
                //Search for randChrom1GenortypeIndex1 in chrom2
                for (i1 = 0; i1 < chromosome2.cityList.length; i1++) {
                    if(chromosome2.cityList[i1] == randChrom1Genotype1) {
                        if(i1 == chromosome2.cityList.length -1) {
                            adjacentCost2 = Utility.calculateCost(chromosome2.cityList[i1], chromosome2.cityList[0], cities);
                            break;
                        }else{
                            adjacentCost2 = Utility.calculateCost(chromosome2.cityList[i1], chromosome2.cityList[i1+1], cities);
                            break;
                        }
                    }
                }
                /**
                 * If the adjacent cost found in the 2nd randomly selected chromosome is smaller, then take the n+1 city and pass it back
                 * to the first chromosome and place it near the init city
                 */
                if(adjacentCost2 < adjacentCost) {
                    if(i1 == chromosome2.cityList.length-1) {
                        nextCity = chromosome2.cityList[0];
                    }else{
                        nextCity = chromosome2.cityList[i1+1];
                    }
                    int cityInChromosomeIndex = Utility.findCityInChromosome(chromosome, nextCity);
                    /**
                     * Perform translocation of the index.
                     */
                    Chromosome premeditatedTranslocationChromosome = Mutation.premeditatedTranslocation(chromosome, randChrom1GenotypeIndex1 + 1, cityInChromosomeIndex, cities);
                    premeditatedTranslocationChromosome.calculateCost(cities);
                    double premeditatedCost = premeditatedTranslocationChromosome.getCost();
                    double originalCost = chromosome.getCost();
                    if(premeditatedCost < originalCost) {
                        chromosome.cityList = premeditatedTranslocationChromosome.cityList;
                        chromosome.setCost(premeditatedCost);
                    }
                }else{
                    int randChrom1Genotype2Index = Utility.findCityInChromosome(chromosome2, randChrom1Genotype2);
                    population[chromosome2Index] = Mutation.premeditatedTranslocation(chromosome2, i1, randChrom1Genotype2Index, cities);
                }
            }else{
                //perform some mutation
            }
        }
        return population;
    }

    private static ArrayList<Integer> performCrossover(ArrayList<Integer> parent1List, ArrayList<Integer> parent2List, int nPoint) {
        int [] tempHead = new int[nPoint];
        ArrayList<Integer> parent1ListCopy = Utility.deepCopy(parent1List);
        ArrayList<Integer> parent2ListCopy = Utility.deepCopy(parent2List);

        for (int i = 0; i < nPoint; i++) {
            tempHead[i] = parent1ListCopy.get(i);
        }
        parent1ListCopy.clear();
        for (int i = 0; i < nPoint; i++) {
            parent1ListCopy.add(tempHead[i]);
        }

        for (int i = 0; i < parent2ListCopy.size(); i++) {
            if(!parent1ListCopy.contains(parent2ListCopy.get(i))) {
                parent1ListCopy.add(parent2ListCopy.get(i));
            }
        }
        return parent1ListCopy;
    }

    public static Chromosome[] evolveGreedy(Chromosome[] population, City[] cities) {
        float p = 0.8f;
        Random random = new Random();
        for (int i = 0; i < population.length; i++) {
            Chromosome chromosome = population[i].deepCopy();
            int cIndex = random.nextInt(chromosome.cityList.length-1);
            int c = chromosome.cityList[cIndex];
            while(true) {
                int cPrime;
                int cPrimeIndexChrom1 = -1;
                if(random.nextFloat() <= p) {
                    int i1 = random.nextInt(50 - cIndex);
                    cPrime = chromosome.cityList[i1+cIndex];
                    cPrimeIndexChrom1 = i1+cIndex;
                }else{
                    int randIndiv2Index = random.nextInt(population.length);
                    Chromosome randChromosome2 = population[randIndiv2Index];
                    int cIndexInChromosome2 = Utility.findCityInChromosome(randChromosome2, c);
                    int cPrimeIndex = getSafeRightEdgeIndex(randChromosome2.cityList, cIndexInChromosome2);
                    cPrime = randChromosome2.cityList[cPrimeIndex];
                    cPrimeIndexChrom1 = Utility.findCityInChromosome(chromosome, cPrime);
                }

                int safeRightEdgeValue = getSafeRightEdgeValue(chromosome.cityList, cIndex);
                int safeLeftEdgeValue = getSafeLeftEdgeValue(chromosome.cityList, cIndex);
                if(safeLeftEdgeValue == cPrime || safeRightEdgeValue == cPrime) {
                    break;
                }
                int safeRightEdgeIndexOnChrom1 = getSafeRightEdgeIndex(chromosome.cityList, cIndex);
                int[] newCityLength = Mutation.inversionMutation(chromosome.cityList, safeRightEdgeIndexOnChrom1, cPrimeIndexChrom1+1, cities);
                chromosome.cityList = newCityLength;
                c = cPrime;
            }
            double chromosome1Cost = chromosome.calculateCost(cities);
            double popiCost = population[i].calculateCost(cities);
            if(chromosome1Cost <= popiCost) {
                population[i].cityList = chromosome.cityList;
                population[i].setCost(chromosome.getCost());
            }
        }
        return population;
    }


    public static Chromosome[] greedyCrossover2(Chromosome parent1, Chromosome parent2, City[] cities) {
        //genrate random startpoint
        Random random = new Random();
        int randIndex = random.nextInt(parent1.cityList.length);
        int randParentGene = parent1.cityList[randIndex];


        //boolean: Check if Connection exists in both parents
        int rightIndex = getSafeRightEdgeIndex(parent1.cityList, randIndex);
        int leftIndex = getSafeLeftEdgeIndex(parent1.cityList, randIndex);
        int rightGene = parent1.cityList[rightIndex];
        int leftGene = parent1.cityList[leftIndex];
        Connection connection = new Connection(randIndex, randParentGene, rightGene, leftGene);

        PresentEdge edgeConnectionExists = edgeConnectionExists(parent2, connection);
        if(edgeConnectionExists.leftEdge == -1 && edgeConnectionExists.rightEdge == -1) {
            //No edge exists
            double rightEdgeCostParent1 = getRightEdgeCost(parent1.cityList, randIndex, cities);
            int parent2BaseIndex = Utility.findIntInArray(parent2.cityList, randParentGene);
            int safeRightEdgeIndex = getSafeRightEdgeIndex(parent2.cityList, parent2BaseIndex);
            double rightEdgeCostParent2 = getRightEdgeCost(parent2.cityList, parent2BaseIndex, cities);

            if(rightEdgeCostParent1 < rightEdgeCostParent2) {
                //In parent2 citylist, add the rightGene, use randParentGene as a reference point
                translocate(parent2.cityList, rightGene, randParentGene);
            }else{
                translocate(parent1.cityList, parent2.cityList[safeRightEdgeIndex], randParentGene);
            }

        }else if(edgeConnectionExists.leftEdge == -1 && edgeConnectionExists.rightEdge > -1) {
            //Right edge exists


        }else if(edgeConnectionExists.leftEdge > -1 && edgeConnectionExists.rightEdge == -1) {
            //Left edge exists

        }else{
            //All edges exist
        }
        return null;
    }

    public static int[] translocate(int[] cityList, int itemToInsert, int baseParentValue) {
        ArrayList<Integer> cityListArrayList = Utility.toArrayList(cityList);
        for (int i = 0; i < cityListArrayList.size(); i++) {
            if(cityListArrayList.get(i) == itemToInsert) {
                cityListArrayList.remove(i);
            }
        }
        int itemIndex = Utility.findIntInArrayList(cityListArrayList, baseParentValue);

        if(itemIndex == cityList.length-2) {
            cityListArrayList.add(0, itemToInsert);
        }else{
            cityListArrayList.add(itemIndex+1, itemToInsert);
        }


        int[] ans = Utility.toArray(cityListArrayList);
        return  ans;
    }

    public static double getRightEdgeCost(int [] arr, int baseIndex, City[] cities) {
        int rightIndex = getSafeRightEdgeIndex(arr, baseIndex);
        double cost = Utility.calculateCost(arr[baseIndex], arr[rightIndex], cities);
        return cost;
    }

    public static double getLeftEdgeCost(int [] arr, int baseIndex, City[] cities) {
        int leftIndex = getSafeLeftEdgeIndex(arr, baseIndex);
        double cost = Utility.calculateCost(arr[baseIndex], arr[leftIndex], cities);
        return cost;
    }

    public static int getSafeRightEdgeIndex(int [] array, int startIndex) {
        if(startIndex == array.length-1) {
            return 0;
        }else{
            return startIndex+1;
        }
    }

    public static int getSafeLeftEdgeIndex(int [] array, int startIndex) {
        if(startIndex == 0) {
            return array[array.length-1];
        }else{
            return startIndex-1;
        }
    }

    public static int getSafeRightEdgeValue(int [] array, int startIndex) {
        if(startIndex == array.length-1) {
            return 0;
        }else{
            return array[startIndex+1];
        }
    }

    public static int getSafeLeftEdgeValue(int [] array, int startIndex) {
        if(startIndex == 0) {
            return array[array.length-1];
        }else{
            return array[startIndex-1];
        }
    }

    /**
     *
     * Returns index if connection exists, returns -1 if it doesnt
     * @param parent2
     * @param connection
     * @return
     */
    public static PresentEdge edgeConnectionExists(Chromosome parent2, Connection connection) {
        for (int i = 0; i < parent2.cityList.length; i++) {
            if(parent2.cityList[i] == connection.value) {
                int safeLeftEdgeIndex = getSafeLeftEdgeIndex(parent2.cityList, i);
                int safeRightEdgeIndex = getSafeRightEdgeIndex(parent2.cityList, i);
                if(parent2.cityList[safeLeftEdgeIndex] == connection.leftValue) {
                    return new PresentEdge(connection.valueIndex, i, safeLeftEdgeIndex, -1);
                }
                if(parent2.cityList[safeRightEdgeIndex] == connection.rightValue) {
                    return new PresentEdge(connection.valueIndex, i, -1, safeRightEdgeIndex);
                }
            }
        }
        return new PresentEdge(connection.valueIndex,-1,-1, -1);
    }

    protected static class Connection {
        int rightValue;
        int leftValue;
        int value;
        int valueIndex;

        public Connection(int valueIndex, int value,  int rightValue, int leftValue) {
            this.rightValue = rightValue;
            this.leftValue = leftValue;
            this.value = value;
            this.valueIndex = valueIndex;
        }
    }

    protected static class PresentEdge {
        int baseIndex;
        int baseIndexInParent2;
        int leftEdge = -1;
        int rightEdge = -1;

        public PresentEdge(int baseIndex, int baseIndexInParent2, int leftEdge, int rightEdge) {
            this.baseIndex = baseIndex;
            this.baseIndexInParent2 = baseIndexInParent2;
            this.leftEdge = leftEdge;
            this.rightEdge = rightEdge;
        }
    }
}
