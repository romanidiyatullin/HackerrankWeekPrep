package com.idiyrom;

import java.io.*;
import java.util.*;


class FlipMatrix {


    /*
     * Complete the 'flippingMatrix' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY matrix as parameter.
     */

    public static int flippingMatrix(List<List<Integer>> matrix) {
        // Write your code here
        int size = matrix.size();
        // indexes matrix to store folding indexes
        int[][]positionsArr = new int[size][size];
        int counter =0;

        // WHAT: Assign folding indexes to first half of indexes matrix:
        // HOW: 2 pointers - one is at most left and other is at most right
        // Most left and most right getting same index value 0
        // Then pointers move closer to each other by 1 and assign index value 1
        // and so on..
        for(int line=0; line<size/2; line++){
            int[] row = new int[size];
            for(int i=0, j = size-1; i<j; i++, j--){
                row[i] = counter;
                row[j] = counter;
                counter++;
            }
            positionsArr[line] = row;
        }

        // Assign indexes to 2nd half of indexes matrix - it is just a mirror of 1st half of indexes matrix
        // so just copy already computed rows of indexes into appropriate positions
        int originalRow = 0;
        for(int line = size-1; line>=size/2; line--){
            positionsArr[line] = positionsArr[originalRow];
            originalRow++;
        }

        // Check what we have in indexes matrix
        Arrays.stream(positionsArr).forEach(array -> System.out.println(Arrays.toString(array)));

        int sum = 0;

        // TODO: Optimization required
        // Find max per each of distinct index in given List matrix
        // Amount of distinct indexes is n*n/4.
        for(int index = 0; index<(size*size)/4; index++) {
            int indexSum = 0;
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    if (index == positionsArr[i][j])
                        indexSum = Math.max(indexSum, matrix.get(i).get(j));
            sum+=indexSum;
        }

        return sum;
    }

    public static void main(String[] args) {
        List<Integer> row1 = Arrays.asList(112,42,83,119);
        List<Integer> row2 = Arrays.asList(56,125,56,49);
        List<Integer> row3 = Arrays.asList(15,78,101,43);
        List<Integer> row4 = Arrays.asList(62,98,114,108);
        List<List<Integer>> input = new ArrayList<>();
        input.add(row1);
        input.add(row2);
        input.add(row3);
        input.add(row4);

        System.out.println(flippingMatrix(input));
    }



}