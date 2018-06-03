import java.util.ArrayList;
import java.util.Random;

public class GreedyCrossover {
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
}