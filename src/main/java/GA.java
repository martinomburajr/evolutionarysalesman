import java.util.Arrays;

public class GA {

    /* GA parameters */
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    // Evolves a population over one generation
    public static Chromosome[] evolveChromosome(Chromosome[] pop, City[] cities) {
        Chromosome[] micro = Crossover.micro(pop, cities);
        Chromosome[] newPopulation = new Chromosome[pop.length];
        // Keep our best individual if elitism is enabled
        int elitismOffset = 0;
        if (elitism) {
            Chromosome[] rank = ParentSelection.rank(pop);
            newPopulation[0] =  rank[0];
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < newPopulation.length; i++) {
            // Select parents
            Chromosome parent1 = ParentSelection.tournamentSelection(pop,tournamentSize);
            Chromosome parent2 = ParentSelection.tournamentSelection(pop, tournamentSize);
            // Crossover parents
            Chromosome child =  crossover(parent1, parent2);
            child.calculateCost(cities);

//            Chromosome[] temp = new Chromosome[]{parent1, parent2, child};
//            Arrays.sort(temp, Utility.CHROMOSOME_SORTING_COMPARATOR);

            SurvivorSelection.steadyStateSpecific(newPopulation,child,i,true);
            // Add child to new population

            //newPopulation[].saveChromosome(i, child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.length; i++) {
            mutate(newPopulation[i]);
        }

        return newPopulation;
    }

    // Applies crossover to a set of parents and creates offspring
    public static Chromosome crossover(Chromosome parent1, Chromosome parent2) {
        // Create new child tour
        Chromosome child = new Chromosome();

        // Get start and end sub tour positions for parent1's tour
        int startPos = (int) (Math.random() * parent1.cityList.length);
        int endPos = (int) (Math.random() * parent1.cityList.length);

        // Loop and add the sub tour from parent1 to our child
        for (int i = 0; i < child.cityList.length; i++) {
            // If our start position is less than the end position
            if (startPos < endPos && i > startPos && i < endPos) {
                child.cityList[i] = parent1.cityList[i];
            } // If our start position is larger
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.cityList[i] = parent1.cityList[i];
                }
            }
        }

        // Loop through parent2's city tour
        for (int i = 0; i < parent2.cityList.length; i++) {
            // If child doesn't have the city add it
            if (!child.contains(parent2.getCity(i))) {
                // Loop to find a spare position in the child's tour
                for (int ii = 0; ii < child.cityList.length; ii++) {
                    // Spare position found, add city
                    if (child.cityList[ii] == 0) {
                        child.cityList[ii] = parent2.cityList[i];
                        break;
                    }
                }
            }
        }
        return child;
    }

    // Mutate a tour using swap mutation
    private static void mutate(Chromosome tour) {
        // Loop through tour cities
        for(int tourPos1=0; tourPos1 < tour.cityList.length; tourPos1++){
            // Apply mutation rate
            if(Math.random() < mutationRate){
                // Get a second random position in the tour
                int tourPos2 = (int) (tour.cityList.length * Math.random());

                // Get the cities at target position in tour
                int city1 = tour.cityList[tourPos1];
                int city2 = tour.cityList[tourPos2];

                // Swap them around
                tour.cityList[tourPos2] =  city1;
                tour.cityList[tourPos1] = city2;
            }
        }
    }

    // Selects candidate tour for crossover
//    private static Chromosome tournamentSelectionGroup(Chromosome[] pop) {
//        // Create a tournament population
//        Chromosome[] tournament = new Chromosome[](tournamentSize, false);
//        // For each place in the tournament get a random candidate tour and
//        // add it
//        for (int i = 0; i < tournamentSize; i++) {
//            int randomId = (int) (Math.random() * pop.populationSize());
//            tournament.saveChromosome(i, pop.getChromosome(randomId));
//        }
//        // Get the fittest tour
//        Chromosome fittest = tournament.getFittest();
//        return fittest;
//    }


}