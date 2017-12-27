package novelist.mama.vaigna.thenovelist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Abid Hasan on 23/12/17.
 */


public class ImageBinaryOutLine {

    public static final int SEARCH_DIRECTION_FORWARD = 0;
    public static final int SEARCH_DIRECTION_BACKWARD = 1;

    private int[][] givenBatManBinary;

    int[][] convetedBatmanBinary;


    private int ROW_COUNT;
    private int COLUMN_COUNT;

    private ArrayList<ValueZero> valueZerosIndexList = new ArrayList<>();

    public ImageBinaryOutLine(int[][] givenBatManBinary) {
        this.givenBatManBinary = givenBatManBinary;

        ROW_COUNT = givenBatManBinary.length;
        COLUMN_COUNT = givenBatManBinary[0].length;

        convetedBatmanBinary = givenBatManBinary;

    }


    private ValueZero item;

    protected ValueZero[] tempOneValueMatrix;

    protected void convertAllOneToZero() {

        for (int row = 0; row < ROW_COUNT; row++) {
            for (int column = 0; column < COLUMN_COUNT; column++) {
                if (givenBatManBinary[row][column] == 1) {
                    givenBatManBinary[row][column] = 0;
                    convetedBatmanBinary[row][column] = 0;
                } else {
                    convetedBatmanBinary[row][column] = 1;
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
            forwardIndexScan(indexWithPreviousZero);
            return;
        }

        backwardScan(indexWithPreviousZero);

    }


    protected void forwardIndexScan(ValueZero indexWithPreviousZero) {


        int startingRowNumber = indexWithPreviousZero.rowIndex;

        int matrixHalf = getMatrixHalfLenght();

        if (convetedBatmanBinary[startingRowNumber][matrixHalf] == 1
                && convetedBatmanBinary[startingRowNumber][getCOLUMN_COUNT() - 1] == 1) {

            for (int i = matrixHalf + 1; i < getCOLUMN_COUNT() - 1; i++)
                convetedBatmanBinary[startingRowNumber][i] = 0;
        }


        else {

            LinkedHashMap<ValueZero , Integer> tempHash = new LinkedHashMap<>();
            for (int column = matrixHalf; column < getCOLUMN_COUNT(); column++) {

                if(convetedBatmanBinary[startingRowNumber][column] == 1
                        && convetedBatmanBinary[startingRowNumber][column+1] ==1
                        && column +1 <getCOLUMN_COUNT())

                tempHash.put(new ValueZero(startingRowNumber , column) , convetedBatmanBinary[startingRowNumber][column]);

            }

            int count = 1 ;
            for (Map.Entry<ValueZero, Integer> entry : tempHash.entrySet()) {
                ValueZero key = entry.getKey();
                Integer value = entry.getValue();
                // now work with key and value...
                System.out.println(" "+key.columnIndex+" "+value);

                if(count < tempHash.size() -1)
                    System.out.println(" "+key.columnIndex);

                count ++ ;
            }
        }


    }


    protected int[] backwardScan(ValueZero indexWithPreviousZero) {

        int[] arrayUnderInspection = new int[getMatrixHalfLenght()];

        for (int row = getMatrixHalfLenght(); row < 0; row--) {

        }

        return arrayUnderInspection;
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


}
