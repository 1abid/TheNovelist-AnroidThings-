package novelist.mama.vaigna.thenovelist;

import java.util.ArrayList;

/**
 * Created by Abid Hasan on 23/12/17.
 */


public class ImageBinaryOutLine {

    private int[][] givenBatManBinary ;


    private int ROW_COUNT ;
    private int COLUMN_COUNT;

    private ArrayList<ValueZero> valueZerosIndexList = new ArrayList<>();

    public ImageBinaryOutLine(int[][] givenBatManBinary) {
        this.givenBatManBinary = givenBatManBinary;

        ROW_COUNT = givenBatManBinary.length ;
        COLUMN_COUNT = givenBatManBinary[0].length ;
    }


    private ValueZero item ;



    public void convertAllOneToZero(){

        for (int row = 0 ; row < ROW_COUNT ; row ++ ){
            for (int column = 0 ; column < COLUMN_COUNT ; column ++){
                if(givenBatManBinary[row][column] == 1)
                    givenBatManBinary[row][column] = 0 ;
                else {
                    item = new ValueZero(row, column);
                    valueZerosIndexList.add(item);
                }
            }
        }
    }



    protected void makeOutLine(ValueZero indexWithPreviousZero){

        int middleIndex = getMatrixMiddle();
        int startPositionY = indexWithPreviousZero.columnIndex ;

        int matrixHalfLength = (COLUMN_COUNT / 2) + middleIndex ;


        if(startPositionY > matrixHalfLength){
            forwardIndexScan(matrixHalfLength , indexWithPreviousZero);
            return;
        }

        backwardScan(matrixHalfLength , indexWithPreviousZero) ;

    }



    protected int forwardIndexScan(int matrixHalfLength, ValueZero indexWithPreviousZero){

        int consecutiveZeroValue = 0 ;


        for(int i= matrixHalfLength+1 ; i > COLUMN_COUNT - 1 ; i++){
            givenBatManBinary[i][indexWithPreviousZero.columnIndex] = 1 ;

            consecutiveZeroValue ++ ;
        }

        return consecutiveZeroValue ;
    }


    protected void backwardScan(int matrixHalfLength, ValueZero indexWithPreviousZero){

        for (int i = matrixHalfLength ; i < 0 ; i -- ){

        }

    }


    protected final class ValueZero {

        final int rowIndex ;

        final int columnIndex ;

        public ValueZero(int rowIndex, int columnIndex) {
            this.rowIndex = rowIndex;
            this.columnIndex = columnIndex;
        }


        public int getRowIndex() {
            return rowIndex;
        }

        public int getColumnIndex() {
            return columnIndex;
        }
    }

    public int[][] getGivenBatManBinary() {
        return givenBatManBinary;
    }

    public ArrayList<ValueZero> getValueZerosIndexList() {
        return valueZerosIndexList;
    }

    public int getROW_COUNT() {
        return ROW_COUNT;
    }

    public int getCOLUMN_COUNT() {
        return COLUMN_COUNT;
    }


    public void setItem(int rowIndex ,int columnIndex) {
        this.item = new ValueZero(rowIndex , columnIndex);
    }

    public ValueZero getItem() {
        return item;
    }


    protected int getMatrixMiddle(){
        return (COLUMN_COUNT % 2 == 0 ) ? 1 : 0 ;
    }

    protected int getIndexOfaPreviousZeroPoint(ValueZero indexWithPreviousZero){
        return valueZerosIndexList.indexOf(indexWithPreviousZero);
    }

}
