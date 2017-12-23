package novelist.mama.vaigna.thenovelist;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static android.support.v4.content.MimeTypeFilter.matches;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Abid Hasan on 23/12/17.
 */


public class ImageBinaryConversionTest {


    int[][] matrixUnderTest = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                               {1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1},
                               {1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1},
                               {1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1},
                               {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                               {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                               {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                               {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                               {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                               {1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
                               {1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1},
                               {1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1},
                               {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    ImageBinaryOutLine objectUnderTest;


    @Before
    public void setUp() {

        objectUnderTest = new ImageBinaryOutLine(matrixUnderTest);

    }


    @Test
    public void givenMatrixHasAExpectedValue() {

        assertEquals("out member variable matrix has a row and column count", true,
                objectUnderTest.getROW_COUNT() != 0 || objectUnderTest.getCOLUMN_COUNT() != 0);


        assertEquals("target matrix has 26 column", 26, objectUnderTest.getCOLUMN_COUNT());


        assertEquals("target matrix has 13 row", 13, objectUnderTest.getROW_COUNT());

    }


    @Test
    public void givenMatrixCanBeConvertedToALLZERO() {

        objectUnderTest.convertAllOneToZero();

        int[][] matrix_Under_test = objectUnderTest.getGivenBatManBinary() ;

        boolean isZero = false ;

        for (int row = 0 ; row < matrix_Under_test.length ; row ++ ){
            for (int column = 0 ; column < matrix_Under_test[0].length ; column ++){
                isZero = matrix_Under_test[row][column] == 0 ? true : false ;
            }
        }

        assertEquals("all values are zero ", true, isZero);
    }


    @Test
    public void WhileConvertingWeCanSaveIndexOfZero() {

        objectUnderTest.convertAllOneToZero();

        assertNotNull(objectUnderTest.getValueZerosIndexList());

        assertEquals("our indexWithZero value list size matches expected", 174, objectUnderTest.getValueZerosIndexList().size());

    }


    @Test
    public void CanAccessToAIndexWithValueZero() {
        objectUnderTest.setItem(1, 7);

        ImageBinaryOutLine.ValueZero target = objectUnderTest.getItem();


        assertEquals("get correct row index of Interest ", 1, target.rowIndex);
        assertEquals("get correct column index of Interest ", 7, target.columnIndex);
    }


    @Test
    public void ArbitraryIndexWithZeroValueHasBeenAddedTOList() {

        objectUnderTest.setItem(1, 7);

        objectUnderTest.convertAllOneToZero();

        ImageBinaryOutLine.ValueZero target = objectUnderTest.getItem();

        ArrayList<ImageBinaryOutLine.ValueZero> items = objectUnderTest.getValueZerosIndexList();

        ImageBinaryOutLine.ValueZero underTest = null;

        for (ImageBinaryOutLine.ValueZero item :
                items) {
            if (item.rowIndex == target.rowIndex && item.columnIndex == target.columnIndex) {
                underTest = item;
                break;
            }

        }

        assertNotNull(underTest);

        assertThat(underTest.rowIndex , is(equalTo(target.rowIndex)));

    }


    @Test
    public void canCalculateMiddleIndexOfTargetArray(){

        //should get 0 for even matrix
        int count = objectUnderTest.getMatrixMiddle();
        assertThat("Should have 1 extra middle point for an odd matrix " ,1 , is(equalTo(count)));

    }



    @Test
    public void forwardOrBackwardSearchBasedOnAIndexValue(){

        objectUnderTest.setItem(0,6);

        ImageBinaryOutLine.ValueZero pointOfInterest = objectUnderTest.getItem() ;

        objectUnderTest.makeOutLine(pointOfInterest);



    }

    @Test
    public void consecutivePreviousZeroValue(){

        objectUnderTest.convertAllOneToZero();

        objectUnderTest.setItem(1,14);

        int count = objectUnderTest.forwardIndexScan(objectUnderTest.getMatrixMiddle() , objectUnderTest.getItem());

        assertEquals(2 , count);
    }


    @After
    public void tearDown() {

        objectUnderTest = null;

    }


}
