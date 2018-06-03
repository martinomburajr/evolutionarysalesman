import java.util.Random;

public class GA {


    static final double MUTATION_RATE = 0.001;
    static final double GREEDY_MUTATION_RATE = 0.01;
    static final int TOURNAMENT_SIZE = 7;
    static final boolean ELITISM = false;

    // Evolves a population over one generation
    public static Chromosome[] evolve(Chromosome[] pop, City[] cities) {
        Chromosome[] newPopulation = new Chromosome[pop.length];
        Random random = new Random();
        // Keep our best individual if ELITISM is enabled
        int elitismOffset = 0;
        if (ELITISM) {
            Chromosome[] rank = ParentSelection.rank(pop);
            newPopulation[0] = rank[0];
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < newPopulation.length; i++) {
            // Select parents
            Chromosome parent1 = ParentSelection.tournamentSelection(pop, TOURNAMENT_SIZE);
            Chromosome parent2 = ParentSelection.tournamentSelection(pop, TOURNAMENT_SIZE);
            // Crossover parents
            Chromosome child = GreedyCrossover.greedyCrossover(parent1,parent2, cities); // crossover(parent1, parent2);
//            Chromosome child = crossover(parent1,parent2);
            child.calculateCost(cities);
//
//            Chromosome[] temp = new Chromosome[]{parent1, parent2, child};
//            Arrays.sort(temp, Utility.CHROMOSOME_SORTING_COMPARATOR);

//            SurvivorSelection.steadyStateSpecific(newPopulation,parent1,i,true);
            SurvivorSelection.steadyStateSpecific(newPopulation,child,i,true);
//            SurvivorSelection.generational5050(pop, newPopulation,false);
            // Add child to new population

            //newPopulation[].saveChromosome(i, child);
        }



        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.length; i++) {
            mutate(newPopulation[i]);
            if(random.nextFloat() < GREEDY_MUTATION_RATE) {
                greedyMutation(newPopulation[i],cities);
            }
        }

        for (int i = elitismOffset; i < newPopulation.length; i++) {

        }


        return newPopulation;
    }

    // Applies crossover to a set of parents and creates offspring
//    public static Chromosome crossover(Chromosome parent1, Chromosome parent2) {
//        // Create new child tour
//        Chromosome child = new Chromosome();
//
//        // Get start and end sub tour positions for parent1's tour
//        int startPos = (int) (Math.random() * parent1.cityList.length);
//        int endPos = (int) (Math.random() * parent1.cityList.length);
//
//        // Loop and add the sub tour from parent1 to our child
//        for (int i = 0; i < child.cityList.length; i++) {
//            // If our start position is less than the end position
//            if (startPos < endPos && i > startPos && i < endPos) {
//                child.cityList[i] = parent1.cityList[i];
//            } // If our start position is larger
//            else if (startPos > endPos) {
//                if (!(i < startPos && i > endPos)) {
//                    child.cityList[i] = parent1.cityList[i];
//                }
//            }
//        }
//
//        // Loop through parent2's city tour
//        for (int i = 0; i < parent2.cityList.length; i++) {
//            // If child doesn't have the city add it
//            if (!child.contains(parent2.getCity(i))) {
//                // Loop to find a spare position in the child's tour
//                for (int ii = 0; ii < child.cityList.length; ii++) {
//                    // Spare position found, add city
//                    if (child.cityList[ii] == 0) {
//                        child.cityList[ii] = parent2.cityList[i];
//                        break;
//                    }
//                }
//            }
//        }
//        return child;
//    }

    /**
     * T
     * @param tour
     */
    private static void mutate(Chromosome tour) {
        for(int tourPos1=0; tourPos1 < tour.cityList.length; tourPos1++){
            if(Math.random() < MUTATION_RATE){
                int tourPos2 = (int) (tour.cityList.length * Math.random());

                int city1 = tour.cityList[tourPos1];
                int city2 = tour.cityList[tourPos2];

                tour.cityList[tourPos2] =  city1;
                tour.cityList[tourPos1] = city2;
            }
        }
    }



    public static Chromosome greedyMutation(Chromosome chromosome, City[] cities)
    {
        int [] _tour = chromosome.cityList;
        int [] _newTour = new int[chromosome.cityList.length];
        // Get tour size
        int size = _tour.length;


        //CHECK THIS!!
        for (int i=0;i<size;i++)
        {
            _newTour[i] =_tour[i];
        }

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

    public static void greedyMutationSwap(int[] _tour, int [] _newTour, int i, int k )
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