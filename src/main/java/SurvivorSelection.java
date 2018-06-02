import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class SurvivorSelection {

    /**
     * Returns the new population
     * @param originalChromosomes
     * @param newChromosomes
     * @return
     */
    public static Chromosome[] generational(Chromosome[] originalChromosomes, Chromosome[] newChromosomes) {
        return newChromosomes;
    }

    /**
     * MODIFIES INPUT!
     * Orders the inputs by fitness, then selects half from both sides. (Integer division, it will prefer the bottom)
     * @param originalChromosomes
     * @param newChromosomes
     * @return
     */
    public static Chromosome[] generational5050(Chromosome[] originalChromosomes, Chromosome[] newChromosomes, boolean preSorted) {
        Chromosome[] finalArray = new Chromosome[50];
        if(!preSorted) {
            Arrays.sort(originalChromosomes, Utility.CHROMOSOME_SORTING_COMPARATOR);
            Arrays.sort(newChromosomes, Utility.CHROMOSOME_SORTING_COMPARATOR);
        }

        for (int i = 0; i < 25; i++) {
            finalArray[i] = originalChromosomes[i];
        }
        for (int i = 25; i < 50; i++) {
            finalArray[i] = newChromosomes[i];
        }
        return finalArray;
    }

    /**
     * MODIFIES INPUT! If preSorted = false
     * Selects the overall best from both chromosome populations
     * @param originalChromosomes
     * @param newChromosomes
     * @return
     */
    public static Chromosome[] generationalElitist(Chromosome[] originalChromosomes, Chromosome[] newChromosomes, boolean preSorted) {
        Chromosome[] intermediateArray = new Chromosome[100];
        Chromosome[] finalArray = new Chromosome[50];

        if(!preSorted) {
            Arrays.sort(originalChromosomes, Utility.CHROMOSOME_SORTING_COMPARATOR);
            Arrays.sort(newChromosomes, Utility.CHROMOSOME_SORTING_COMPARATOR);
        }

        for (int i = 0; i < 50; i++) {
            intermediateArray[i] = originalChromosomes[i];
        }
        for (int i = 50; i < 100; i++) {
            intermediateArray[i] = newChromosomes[i];
        }

        Arrays.sort(intermediateArray, Utility.CHROMOSOME_SORTING_COMPARATOR);
        for (int i = 0; i < finalArray.length; i++) {
            finalArray[i] = intermediateArray[i];
        }

        return finalArray;
    }

    /**
     * Replaces the weakest parent and returns the ORIGINAL parent with the modification. If preSorted = false it will
     * modify the array by sorting it
     * @param originalChromosomes
     * @param chromosomeToAdd
     * @return
     */
    public static Chromosome[] steadyState(Chromosome[] originalChromosomes, Chromosome chromosomeToAdd, boolean preSorted) {
        if(!preSorted && !Utility.containsNull(originalChromosomes)) {
            Arrays.sort(originalChromosomes, Utility.CHROMOSOME_SORTING_COMPARATOR);
        }
        originalChromosomes[originalChromosomes.length - 1] = chromosomeToAdd;
        return originalChromosomes;
    }

    /**
     * Replaces the specified parent and returns the ORIGINAL parent with the modification. If preSorted = false it will
     * modify the array by sorting it
     * @param originalChromosomes
     * @param chromosomeToAdd
     * @return
     */
    public static Chromosome[] steadyStateSpecific(Chromosome[] originalChromosomes, Chromosome chromosomeToAdd, int indexToAdd, boolean preSorted) {
        if(!preSorted && !Utility.containsNull(originalChromosomes)) {
            Arrays.sort(originalChromosomes, Utility.CHROMOSOME_SORTING_COMPARATOR);
        }
        originalChromosomes[indexToAdd] = chromosomeToAdd;
        return originalChromosomes;
    }

    /**
     * Introduces N new FITTEST chromosomes into the original and replaces THE WEAKEST from the original. Modifies the original and returns it.
     * @param originalChromosomes
     * @param chromosomesToAdd
     * @param nChromosomesToIntroduce
     * @return
     */
    public static Chromosome[] steadyStateNFitnessBased(Chromosome[] originalChromosomes, Chromosome[] chromosomesToAdd, int nChromosomesToIntroduce, boolean preSorted) {
        if(!preSorted && !Utility.containsNull(originalChromosomes)) {
            Arrays.sort(originalChromosomes, Utility.CHROMOSOME_SORTING_COMPARATOR);
            Arrays.sort(chromosomesToAdd, Utility.CHROMOSOME_SORTING_COMPARATOR);
        }

        for (int i = 0; i < nChromosomesToIntroduce; i++) {
            originalChromosomes[originalChromosomes.length - 1 - nChromosomesToIntroduce + i] = chromosomesToAdd[i];
        }
        return originalChromosomes;
    }

    /**
     * Introduces N new RANDOM chromosomes into the original and replaces THE WEAKEST from the original. Modifies the original and returns it
     * @param originalChromosomes
     * @param chromosomesToAdd
     * @param nChromosomesToIntroduce
     * @return
     */
    public static Chromosome[] steadyStateNRandom(Chromosome[] originalChromosomes, Chromosome[] chromosomesToAdd, int nChromosomesToIntroduce, boolean preSorted) {
        Random random = new Random();
        if(!preSorted) {
            Arrays.sort(originalChromosomes, Utility.CHROMOSOME_SORTING_COMPARATOR);
        }
        for (int i = 0; i < nChromosomesToIntroduce; i++) {
            int randomIndex = random.nextInt(chromosomesToAdd.length);
            originalChromosomes[originalChromosomes.length - 1 - nChromosomesToIntroduce + i] = chromosomesToAdd[randomIndex];
        }
        return originalChromosomes;
    }

    /**
     * Introduces N new random chromosomes into the original and replaces ANY from the original. Modifies the original and returns it
     * @param originalChromosomes
     * @param chromosomesToAdd
     * @param nChromosomesToIntroduce
     * @return
     */
    public static Chromosome[] steadyStateVeryRandom(Chromosome[] originalChromosomes, Chromosome[] chromosomesToAdd, int nChromosomesToIntroduce, boolean preSorted) {
        Random random = new Random();
        for (int i = 0; i < nChromosomesToIntroduce; i++) {
            int randomIndex = random.nextInt(chromosomesToAdd.length);
            int randomOriginalIndex = random.nextInt(chromosomesToAdd.length);
            originalChromosomes[randomOriginalIndex] = chromosomesToAdd[randomIndex];
        }
        return originalChromosomes;
    }
}
