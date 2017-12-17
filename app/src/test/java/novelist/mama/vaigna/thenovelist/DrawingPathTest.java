package novelist.mama.vaigna.thenovelist;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

/**
 * Created by Abid Hasan on 17/12/17.
 */


public class DrawingPathTest {



    private static final int[][] targetMatrixH = {{0, 0, 1, 0, 0, 1, 0, 0},
                                                  {0, 0, 1, 0, 0, 1, 0, 0},
                                                  {0, 0, 1, 0, 0, 1, 0, 0},
                                                  {0, 0, 1, 1, 1, 1, 0, 0},
                                                  {0, 0, 1, 1, 1, 1, 0, 0},
                                                  {0, 0, 1, 0, 0, 1, 0, 0},
                                                  {0, 0, 1, 0, 0, 1, 0, 0},
                                                  {0, 0, 1, 0, 0, 1, 0, 0}};

    DrawingPath objectUnderTest;


    @Before
    public void setUp() {
        objectUnderTest = new DrawingPath(targetMatrixH);

    }


    @Test
    public void DrawingClassCanValidAPointIfItIsAccessible(){
        boolean unAccessiblepoint =objectUnderTest.isValidPoint(targetMatrixH , 0 , 1);

        assertEquals(false , unAccessiblepoint);

        boolean accessAblePoint = objectUnderTest.isValidPoint(targetMatrixH , 2 ,2);

        assertEquals(true , accessAblePoint);
    }


    @Test
    public void DrawingClassGetsExpectedNumberOfNeighbourForANode(){

        List<DrawingPath.Node> twoNeighbour = objectUnderTest.getNeighbours(targetMatrixH ,
                objectUnderTest.new Node(2,2));

        assertThat("this node should have 2 neighbour" , twoNeighbour , hasSize(2));

        List<DrawingPath.Node> threeNeighbour = objectUnderTest.getNeighbours(targetMatrixH ,
                objectUnderTest.new Node(3,5));

        assertThat("this node should have 3 neighbour" , threeNeighbour , hasSize(3));

       /* List<DrawingPath.Node> fourNeighbour = objectUnderTest.getNeighbours(targetMatrixH ,
                objectUnderTest.new Node(4,4));

        assertThat("this node should have 4 neighbour" , fourNeighbour , hasSize(4));*/
    }


    @Test
    public void DrawingPathPrintsCurrcetPathofCoordinated(){
        objectUnderTest.getPaths();


        assertThat("this matrix should have 20 coordinated with value 1" , objectUnderTest.coordintes , hasSize(20));
    }
}
