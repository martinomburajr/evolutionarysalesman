import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EvolutionTest {
    @Test
    public void splitArray() throws Exception {
        int []arr = {1,2,3,4,5,6,7,8,9,10};
        int[] ints = Mutation.splitArray(arr, 1, 4);
        Assert.assertArrayEquals(new int[]{1,4,3,2,5,6,7,8,9,10}, arr);
    }

    @Test
    public void splitReverse() throws Exception {
    }

    @Test
    public void splitReverseInsert() throws Exception {
        int []arr = {1,2,3,4,5,6,7,8,9,10};
        int []arr1 = {1,2,3,4,5,6,7,8,9,10};
        int []arr3 = {2,3,9,4,1,5,8,6,7};
//        int[] ints = Mutation.splitReverseInsert(arr, 1, 4);
//        Assert.assertArrayEquals(new int[]{1,4,3,2,5,6,7,8,9,10}, ints);
//
//        int[] ints2 = Mutation.splitReverseInsert(arr1, 5, 9);
//        Assert.assertArrayEquals(new int[]{1,2,3,4,5,9,8,7,6,10}, ints2);

        int[] ints3 = Mutation.splitReverseInsert(arr3, 2, 7);
        Assert.assertArrayEquals(new int[]{2,3,8,5,1,4,9,6,7}, ints3);
    }

    @Test
    public void insertAtIndexTest() throws Exception {
        int []arr = {1,2,3,4,5,6,7,8,9,10};
        int []arr1 = {1,2,3,4,5,6,7,8,9,10};
        int[] ints = Mutation.insertAtIndex(arr, 1, 4);
        Assert.assertArrayEquals(new int[]{1,3,4,5,2,6,7,8,9,10}, ints);

        int[] ints2 = Mutation.insertAtIndex(arr1, 5, 9);
        Assert.assertArrayEquals(new int[]{1,2,3,4,5,7,8,9,10,6}, ints2);
    }

    @Test
    public void splitInsertTest() throws Exception {
        int []arr = {1,2,3,4,5,6,7,8,9,10};
        int []arr1 = {1,2,3,4,5,6,7,8,9,10};

        int[] ints = Mutation.splitInsert(arr, 1, 4,2);
        Assert.assertArrayEquals(new int[]{1,4,2,3,5,6,7,8,9,10}, ints);

//        int[] ints2 = Mutation.insertAtIndex(arr1, 5, 9);
//        Assert.assertArrayEquals(new int[]{1,2,3,4,5,7,8,9,10,6}, ints2);
    }

    @Test
    public void reverseArray() throws Exception {
    }

}