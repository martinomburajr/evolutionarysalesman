import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mutation {
    /**
     * Performs an inversion mutation on a given parent by swapping its random start position and selection
     * @param population
     * @return
     */
    public static Chromosome[] inversionMutation(Chromosome [] population, City[] cities) {
        Random random = new Random();
        int randSegment = random.nextInt(20);
        if (randSegment == 0); randSegment = 5;
        int randStartPosition = random.nextInt(25);

        for (int i = 0; i < population.length; i++) {
            Chromosome chromosome = population[i];
            Chromosome tempchromosome = population[i];
            //get random segment of array
            int[] splitReverse = splitReverseInsert(chromosome.cityList, randStartPosition, randSegment+randStartPosition);
            tempchromosome.cityList = splitReverse;

            chromosome.cityList = splitReverse;
            double oldCost = chromosome.cost;
            tempchromosome.calculateCost(cities);
            double newCost = tempchromosome.getCost();
            if(oldCost < newCost) {
            }else{
                chromosome.cityList = splitReverse;
            }
        }
        return population;
    }

    /**
     * Performs an inversion mutation on a given parent by swapping its random start position and selection
     * @param arr
     * @param startInclusive
     * @param endExclusive
     * @param cities
     * @return
     */
    public static int[] inversionMutation(int [] arr, int startInclusive, int endExclusive,  City[] cities) {
        if(endExclusive > startInclusive) {
            int[] ints = splitReverseInsert(arr, startInclusive, endExclusive);
            return ints;
        }else{
            return arr;
        }
    }

    public static Chromosome[] pointExchange(Chromosome [] population, City[] cities, int nPointExchange) {
        Random random = new Random();
        int randPlacementPosition = random.nextInt(40);
        if (randPlacementPosition == 0); randPlacementPosition = 5;
        int randStartPosition = random.nextInt(25);
        if(randStartPosition + randPlacementPosition >= 49) {
            randPlacementPosition=20;
        }

        for (int i = 0; i < population.length; i++) {
            Chromosome chromosome = population[i];
            Chromosome tempchromosome = population[i];

            int[] splitReverse = splitInsert(chromosome.cityList, randStartPosition, randPlacementPosition, nPointExchange);
            tempchromosome.cityList = splitReverse;

            double oldCost = chromosome.cost;
            tempchromosome.calculateCost(cities);
            double newCost = tempchromosome.getCost();
            if(oldCost < newCost) {
            }else{
                chromosome.cityList = splitReverse;
            }
        }
        return population;
    }

    public static Chromosome pointExchangeSingle(Chromosome chromosome, City[] cities, int nPointExchange) {
        Random random = new Random();
        int randPlacementPosition = random.nextInt(40);
        if (randPlacementPosition == 0); randPlacementPosition = 5;
        int randStartPosition = random.nextInt(25);
        if(randStartPosition + randPlacementPosition >= 49) {
            randPlacementPosition=20;
        }

        Chromosome tempchromosome = chromosome;

        int[] splitReverse = splitInsert(chromosome.cityList, randStartPosition, randPlacementPosition, nPointExchange);
        tempchromosome.cityList = splitReverse;

        double oldCost = chromosome.cost;
        tempchromosome.calculateCost(cities);
        double newCost = tempchromosome.getCost();
        if(oldCost < newCost) {
        }else{
            chromosome.cityList = splitReverse;
        }
        return chromosome;
    }

    public static Chromosome[] transposition(Chromosome [] population, City [] cityList) {
        return null;
    }

    public static Chromosome[] translocation(Chromosome [] population, City[] cities) {
        Random random = new Random();

        int indexToSelect = random.nextInt(48);
        int indexToInsert = random.nextInt(48);

        for (int i = 0; i < population.length; i++) {
            Chromosome chromosome = population[i];
            Chromosome tempchromosome = population[i];
            //get random segment of array
            int[] splitReverse = insertAtIndex(chromosome.cityList, indexToSelect, indexToInsert);
            tempchromosome.cityList = splitReverse;

            double oldCost = chromosome.cost;
            tempchromosome.calculateCost(cities);
            double newCost = tempchromosome.getCost();
            if(oldCost < newCost) {
            }else{
                chromosome.cityList = splitReverse;
            }
        }
        return population;
    }

    public static int[] insertAtIndex(int []arr, int indexToSelect, int insertAtIndex) {
        List<Integer> list = Utility.toArrayList(arr);
        Integer tempSelect = list.get(indexToSelect);
        list.remove(indexToSelect);
        list.add(insertAtIndex,tempSelect);
        return Utility.toArray((ArrayList<Integer>) list);
    }

    /**
     * Splits an array into a smaller chunk defined by startInclusive and endExclusive
     * @param arr
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static int[] splitArray(int []arr, int startInclusive, int endExclusive) {
        int range = endExclusive - startInclusive;
        int newArr[] = new int[range];
        for (int i = 0; i < range; i++) {
            newArr[i] = arr[startInclusive+i];
        }
        return newArr;
    }

    /**
     * splits the array and returns it reversed. O(n) not O(n+n)
     * @param arr
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static int[] splitReverse(int []arr, int startInclusive, int endExclusive) {
        int range = endExclusive - startInclusive;
        int newArr[] = new int[range];
        for (int i = 0; i < range; i++) {
            newArr[(range-1)-i] = arr[startInclusive+i];
        }
        return newArr;
    }


    /**
     * Splits the array and returns the appropriate segment reversed. O(n) not O(n+n)
     * @param arr
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static int[] splitReverseInsert(int []arr, int startInclusive, int endExclusive) {
        int range = endExclusive - startInclusive;
        int []newArr = new int[range];
        for (int i = 0; i < range; i++) {
            newArr[i] = arr[(endExclusive-1)-i];
        }
        for (int i = 0; i < range; i++) {
            arr[startInclusive+i] = newArr[i];
        }
        return arr;
    }

    /**
     * Splits the array and returns the appropriate segment in the approrpiate place. O(n) not O(n+n)
     * @param arr
     * @param startIndex
     * @param replacementIndex
     * @return
     */
    public static int[] splitInsert(int []arr, int startIndex, int replacementIndex, int range) {
        int []newArr = new int[range];
        for (int i = 0; i < range; i++) {
            newArr[i] = arr[startIndex+i];
        }
        List<Integer> list = Utility.toArrayList(arr);

        for (int i = 0; i < range; i++) {
            list.add((replacementIndex+i),newArr[i]);
        }
        for (int i = 0; i < range; i++) {
            list.remove(startIndex);
        }

        return Utility.toArray((ArrayList<Integer>) list);
    }

    /**
     * Returns a copy of the chromosome.
     * @param chromosome
     * @param targetPlacementIndex
     * @param targetCityIndex
     * @return
     */
    public static Chromosome premeditatedTranslocation(Chromosome chromosome, int targetPlacementIndex, int targetCityIndex, City[] cities) {
        ArrayList<Integer> cityListArrayList = Utility.toArrayList(chromosome.cityList);
        int targetCity = chromosome.cityList[targetCityIndex];

        if(targetPlacementIndex != 0) {
            cityListArrayList.add(targetPlacementIndex-1, targetCity);
        }else{
            cityListArrayList.add(chromosome.cityList.length-1, targetCity);
        }

        if(targetCityIndex < targetPlacementIndex) {
            cityListArrayList.remove(targetCityIndex);
        }else{
            cityListArrayList.remove(targetCityIndex+1);
        }

        int[] newCityList = Utility.toArray(cityListArrayList);

        Chromosome newChromosome = new Chromosome(newCityList,cities);
        return newChromosome;
    }

    /**
     * Performs a simple transposition
     * @param tour
     */
    public static void transposition(Chromosome tour, float mutationRate) {
        for(int i=0; i < tour.cityList.length; i++){
            if(Math.random() < mutationRate){
                int tempCity2 = (int) (tour.cityList.length * Math.random());

                int c1 = tour.cityList[i];
                int c2 = tour.cityList[tempCity2];

                tour.cityList[tempCity2] =  c1;
                tour.cityList[i] = c2;
            }
        }
    }

    /**
     * Performs a greedy mutation
     * @param chromosome
     * @param cities
     * @return
     */
    public static Chromosome greedyMutation(Chromosome chromosome, City[] cities)
    {
        int [] _tour = chromosome.cityList;
        int [] _newTour = new int[chromosome.cityList.length];
        // Get tour size
        int size = _tour.length;


        //CHECK THIS!!
        System.arraycopy(_tour, 0, _newTour, 0, size);

        // repeat until no improvement is made
        int improve = 0;
        int iteration = 0;

        while ( improve < 1)
        {
            chromosome.cityList = _tour;
            double best_distance = chromosome.calculateCost(cities);

            for ( int i = 1; i < size - 1; i++ )
            {
                for ( int k = i + 1; k < size; k++)
                {
                    greedyMutationSwap(_tour, _newTour, i, k );
                    iteration++;
                    Chromosome chromosome1 = new Chromosome(_newTour, cities);
                    double new_distance = chromosome1.calculateCost(cities);

                    if ( new_distance < best_distance )
                    {
                        // Improvement found so reset
                        improve = 0;

                        for (int j=0;j<size;j++)
                        {
                            _tour[j] =  _newTour[j];
                        }

                        best_distance = new_distance;
                    }
                }
            }

            improve ++;
        }
        return new Chromosome(_newTour, cities);
    }

    private static void greedyMutationSwap(int[] _tour, int[] _newTour, int i, int k)
    {
        int size = _tour.length;

        for ( int c = 0; c <= i - 1; ++c )
        {
            _newTour[c]= _tour[c];
        }

        int dec = 0;
        for ( int c = i; c <= k; ++c )
        {
            _newTour[c] = _tour[k - dec];
            dec++;
        }

        for ( int c = k + 1; c < size; ++c )
        {
            _newTour[c] = _tour[c];
        }
    }
}
