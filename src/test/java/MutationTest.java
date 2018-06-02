import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MutationTest {
    @Test
    public void inversionMutation() throws Exception {
        int[] a = new int[]{1,2,3,4,5,6};
        int[] ints = Mutation.inversionMutation(a, 1+1, 6, null);
        Assert.assertArrayEquals(new int[]{1,2,6,5,4,3}, ints);

        a = new int[]{1,2,3,4,5,6};
        int[] ints2 = Mutation.inversionMutation(a, 1+1, 5, null);
        Assert.assertArrayEquals(new int[]{1,2,5,4,3,6}, ints2);

        a = new int[]{1,2,3,4,5,6};
        int[] ints3 = Mutation.inversionMutation(a, 0, 5, null);
        Assert.assertArrayEquals(new int[]{5,4,3,2,1,6}, ints3);

        a = new int[]{1,2,3,4,5,6};
        int[] ints4 = Mutation.inversionMutation(a, 0, 6, null);
        Assert.assertArrayEquals(new int[]{6,5,4,3,2,1}, ints4);

        a = new int[]{1,2,3,4,5,6};
        int[] ints5 = Mutation.inversionMutation(a, 5, 6, null);
        Assert.assertArrayEquals(new int[]{1,2,3,4,5,6}, ints5);

        a = new int[]{1,2,3,4,5,6};
        int[] ints6 = Mutation.inversionMutation(a, 2, 2, null);
        Assert.assertArrayEquals(new int[]{1,2,3,4,5,6}, ints6);

        a = new int[]{1,2,3,4,5,6};
        int[] ints7 = Mutation.inversionMutation(a, 3, 2, null);
        Assert.assertArrayEquals(new int[]{1,2,3,4,5,6}, ints7);
    }

}