import java.util.Random;

public class Evolution {


    private static final float MUTATION_RATE = 0.001f;
    private static final float GREEDY_MUTATION_RATE = 0.1f;
    private static final int TOURNAMENT_SIZE = 7;


    /**
     * Evolve given population to produce the next generation.
     * @param pop
     * @param cities
     * @return
     */
    public static Chromosome[] evolve(Chromosome[] pop, City[] cities) {
        Chromosome[] newPopulation = new Chromosome[pop.length];
        Random random = new Random();

        for (int i = 0; i < newPopulation.length; i++) {
            // Select parents
            Chromosome parent1 = ParentSelection.tournamentSelection(pop, TOURNAMENT_SIZE);
            Chromosome parent2 = ParentSelection.tournamentSelection(pop, TOURNAMENT_SIZE);
            // Crossover parents
            Chromosome child = Crossover.greedyCrossover(parent1,parent2, cities); // crossover(parent1, parent2);
            child.calculateCost(cities);

            SurvivorSelection.steadyStateSpecific(newPopulation,child,i,true);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = 0; i < newPopulation.length; i++) {
            Mutation.transposition(newPopulation[i], MUTATION_RATE);
            //Apply an even more random greedy mutation for faster convergence.
            if(random.nextFloat() < GREEDY_MUTATION_RATE) {
                Mutation.greedyMutation(newPopulation[i],cities);
            }
        }
        return newPopulation;
    }
}