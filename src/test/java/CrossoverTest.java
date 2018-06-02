import org.junit.Test;

import static org.junit.Assert.*;

public class CrossoverTest {
    @Test
    public void cutAndCrossFillCrossover() throws Exception {
    }

    @Test
    public void inverOverCrossover() throws Exception {
    }

    @Test
    public void micro() throws Exception {
    }

    @Test
    public void greedyCrossover() throws Exception {
    }

    @Test
    public void translocate() throws Exception {
        int [] a = new int[]{1,2,3,4,5,6};

        int[] translocate = Crossover.translocate(a, 6, 3);
        assertArrayEquals(new int[]{1,2,3,6,4,5}, translocate);

        int[] translocate2 = Crossover.translocate(a, 1, 3);
        assertArrayEquals(new int[]{2,3,1,4,5,6}, translocate2);

        int[] translocate3 = Crossover.translocate(a, 4, 6);
        assertArrayEquals(new int[]{4,1,2,3,5,6}, translocate3);
    }

    @Test
    public void getRightEdgeCost() throws Exception {
    }

    @Test
    public void getLeftEdgeCost() throws Exception {
    }

    @Test
    public void getSafeRightEdgeIndex() throws Exception {
    }

    @Test
    public void getSafeLeftEdgeIndex() throws Exception {
    }

    @Test
    public void edgeConnectionExists() throws Exception {
    }

}