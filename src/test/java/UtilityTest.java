import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UtilityTest {
    @Test
    public void wrapAroundForward() throws Exception {
        int[] a = new int[]{1,2,3,4,5,6};

        int[] ints = Utility.wrapAroundForward(a, 3);
        Assert.assertArrayEquals(new int[]{4,5,6,1,2,3}, ints);

        int[] ints2 = Utility.wrapAroundForward(a, 0);
        Assert.assertArrayEquals(new int[]{1,2,3,4,5,6}, ints2);

        int[] ints3 = Utility.wrapAroundForward(a, a.length-1);
        Assert.assertArrayEquals(new int[]{6,1,2,3,4,5}, ints3);
    }

    @Test
    public void wrapAroundReverse() throws Exception {
        int[] a = new int[]{1,2,3,4,5,6};

        int[] ints = Utility.wrapAroundReverse(a, 3);
        Assert.assertArrayEquals(new int[]{4,3,2,1,6,5}, ints);
    }

    @Test
    public void containsEverything() throws Exception {
        int[] a = new int[]{1,2,3,4,5,6};
        int[] b = new int[]{4,3,5,6,1,2};
        ArrayList<Integer> test = Utility.toArrayList(b);

        boolean b1 = Utility.hasEverything(a, test);
        assertTrue(b1);
    }

}