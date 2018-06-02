import java.util.ArrayList;
import java.util.Comparator;

public class Utility {
    public static double calculateCost(int a, int b, City[] cities) {
        return cities[a].proximity(cities[b].getx(),cities[b].gety());
    }

    /**
     * Returns the index of a city in a chromosome
     * @param chromosome
     * @param city
     * @return
     */
    public static int findCityInChromosome(Chromosome chromosome, int city) {
        for (int i = 0; i < chromosome.cityList.length; i++) {
            if(chromosome.cityList[i] == city) {
                return i;
            }
        }
        return -1;
    }

    public static int findIntInArray(int [] arr, int search) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == search) {
                return i;
            }
        }
        return -1;
    }

    public static int findIntInArrayList(ArrayList<Integer> arr, int search) {
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i) == search) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Converts an int[] array to an ArrayList<Integer></Integer>
     * @param arr
     * @return
     */
    public static ArrayList<Integer> toArrayList(int [] arr) {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            integerArrayList.add(arr[i]);
        }
        return integerArrayList;
    }

    /**
     * Converts and ArrayList<Integer> to an array.
     * @param integerArrayList
     * @return
     */
    public static int[] toArray(ArrayList<Integer> integerArrayList) {
        int [] ints = new int[integerArrayList.size()];
        for (int i = 0; i < integerArrayList.size(); i++) {
            ints[i] = integerArrayList.get(i);
        }
        return ints;
    }

    public static int[] deepCopy(int[] arr) {
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }

    public static ArrayList<Integer> deepCopy(ArrayList<Integer> arrayList) {
        ArrayList<Integer> copy = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            copy.add(arrayList.get(i));
        }
        return copy;
    }

    /**
     * Compares two chromosomes based on cost. A lower cost is a better one. When applied an array should be ordered
     * from lowest cost upward, meaning the trailing Chromosomes are the weakest.
     */
    public static final Comparator<Chromosome> CHROMOSOME_SORTING_COMPARATOR = (o1, o2) -> {
        if (o1.getCost() < o2.getCost()) {
            return -1;
        } else if (o1.getCost() < o2.getCost()) {
            return 1;
        }
        return 0;
    };

    public static boolean containsNull(Chromosome[] chromosomes) {
        for (int i = 0; i < chromosomes.length; i++) {
            if(chromosomes[i] == null) {
                return true;
            }
        }
        return false;
    }

}
