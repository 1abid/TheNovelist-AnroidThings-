package novelist.mama.vaigna.thenovelist;

import java.util.ArrayList;

/**
 * Created by Abid Hasan on 23/12/17.
 */


public class ImageBinaryOutLine {

    private int[][] givenBatManBinary;

    int[][] convetedBatmanBinary;


    private int ROW_COUNT;
    private int COLUMN_COUNT;

    private ArrayList<ValueZero> valueZerosIndexList = new ArrayList<>();

    public ImageBinaryOutLine(int[][] givenBatManBinary) {
        this.givenBatManBinary = givenBatManBinary;

        ROW_COUNT = givenBatManBinary.length;
        COLUMN_COUNT = givenBatManBinary[0].length;

        convetedBatmanBinary = new int[ROW_COUNT][COLUMN_COUNT];
    }


    private ValueZero item;

    protected ValueZero[] tempOneValueMatrix;

    public void convertAllOneToZero() {

        for (int row = 0; row < ROW_COUNT; row++) {
            for (int column = 0; column < COLUMN_COUNT; column++) {
                if (givenBatManBinary[row][column] == 1) {
                    givenBatManBinary[row][column] = 0;
                    convetedBatmanBinary[row][column] = 0;
                } else {
                    convetedBatmanBinary[row][column] = 1 ;
                    item = new ValueZero(row, column);
                    valueZerosIndexList.add(item);
                }
            }
        }
    }

    public void findPreviousZerosAndConvert() {
        for (ValueZero item : valueZerosIndexList) {
            makeOutLine(item);
        }
    }


    protected void makeOutLine(ValueZero indexWithPreviousZero) {

        int startPositionY = indexWithPreviousZero.columnIndex;

        int matrixHalfLength = getMatrixHalfLenght();


        if (startPositionY > matrixHalfLength) {
            forwardIndexScan(matrixHalfLength, indexWithPreviousZero);
            return;
        }

        backwardScan(matrixHalfLength, indexWithPreviousZero);

    }


    protected void forwardIndexScan(int matrixHalfLength, ValueZero indexWithPreviousZero) {

        int count = 0;

        tempOneValueMatrix = new ValueZero[matrixHalfLength];

        for (int row = matrixHalfLength +1 ; row > getCOLUMN_COUNT() - 1; row++) {

            if (count >= matrixHalfLength)
                return;

            if(convetedBatmanBinary[row][indexWithPreviousZero.columnIndex] == 1){
                tempOneValueMatrix[count] = indexWithPreviousZero ;
                count ++ ;
            }

        }
    }


    protected void backwardScan(int matrixHalfLength, ValueZero indexWithPreviousZero) {

        int[] tempOneValueMatrix = new int[matrixHalfLength];

        int count = 0;

        for (int row = matrixHalfLength; row < 0; row--) {

        }

    }


    protected void determineOutLineOne(int count) {

    }

    protected final class ValueZero {

        final int rowIndex;

        final int columnIndex;

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


    public void setItem(int rowIndex, int columnIndex) {
        this.item = new ValueZero(rowIndex, columnIndex);
    }

    public ValueZero getItem() {
        return item;
    }


    protected int getMatrixMiddle() {
        return (COLUMN_COUNT % 2 == 0) ? 1 : 0;
    }

    protected int getMatrixHalfLenght() {
        return (COLUMN_COUNT / 2) + getMatrixMiddle();
    }

    protected int getIndexOfaPreviousZeroPoint(ValueZero indexWithPreviousZero) {
        return valueZerosIndexList.indexOf(indexWithPreviousZero);
    }

}
