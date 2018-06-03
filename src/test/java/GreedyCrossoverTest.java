import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GreedyCrossoverTest {
    @Test
    public void performMixedCrossover() throws Exception {
        int[] a = new int[]{1,2,3,4,5,6};
        int[] b = new int[]{4,3,5,6,1,2};

        int[] aReverse = Utility.wrapAroundReverse(a, 2);
        int[] bForward = Utility.wrapAroundForward(b, 1);

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(3);
        ArrayList<Integer> list2 = GreedyCrossover.performMixedCrossover(arrayList, bForward, aReverse);

        int[] ans = Utility.toArray(list2);

        assertArrayEquals(new int[]{4,1,2,3,5,6}, ans);
    }

}