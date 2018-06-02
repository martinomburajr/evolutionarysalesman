import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class ParentSelection {

    /**
     * Returns an array that sorts the fitness of the population. Returns the highest fitness. MODIFIES the population
     * @param population
     * @return
     */
    public static Chromosome[] rank(Chromosome [] population) {
        Chromosome[] chromosomes2 = new Chromosome[population.length];
        for (int i = 0; i < population.length; i++) {
            chromosomes2[i] = population[i];
        }

        Arrays.sort(chromosomes2, Utility.CHROMOSOME_SORTING_COMPARATOR);
        return chromosomes2;
    }


    /**
     * Returns an array that sorts the fitness of k random parents
     * @param population
     * @param k
     * @return
     */
    public static Chromosome[] tournamentSelectionGroup(Chromosome [] population, int k) {
        Chromosome[] selectedChromosomes = new Chromosome[k];
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            int randInt = random.nextInt(population.length);
            Chromosome randChromosome = population[randInt];
            selectedChromosomes[i] = randChromosome;
        }
        //Sort/Rank
        Arrays.sort(selectedChromosomes, (o1, o2) -> {
            if(o1.getCost() < o2.getCost()) {
                return -1;
            }else if(o1.getCost() < o2.getCost()) {
                return 1;
            }
            return 0;
        });
        return selectedChromosomes;
    }

    /**
     * Returns an array that sorts the fitness of k random parents
     * @param population
     * @param k
     * @return
     */
    public static Chromosome tournamentSelection(Chromosome [] population, int k) {
        Chromosome[] selectedChromosomes = new Chromosome[k];
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            int randInt = random.nextInt(population.length);
            Chromosome randChromosome = population[randInt];
            selectedChromosomes[i] = randChromosome;
        }
        //Sort/Rank
        Arrays.sort(selectedChromosomes, (o1, o2) -> {
            if(o1.getCost() < o2.getCost()) {
                return -1;
            }else if(o1.getCost() < o2.getCost()) {
                return 1;
            }
            return 0;
        });
        return selectedChromosomes[0];
    }
}
