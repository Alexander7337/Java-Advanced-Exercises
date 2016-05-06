package com.company;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Alexander7337 on 5/6/2016.
 */
public class Problem2CrossfireReworked {

    /*
        You will receive two integers which represent the dimensions of a matrix.
        Then, you must fill the matrix with increasing integers starting from 1.
        You will also receive several commands in the form of 3 integers separated by a space.
        These 3 integers will represent a row in the matrix, a column and a radius.
        You must then destroy the cells which correspond to those arguments cross-like.
        Destroying a cell means that, that cell becomes completely nonexistent in the matrix.
        Destroying cells cross-like means that you form a cross figure,
        with center point - equal to the cell with coordinates – the given row and column,
        and lines with length equal to the given radius.
     */

    public static int[][] matrix;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int row = scn.nextInt();
        int col = scn.nextInt();
        scn.nextLine();

        matrix = new int[row][col];

        //Fill in the matrix
        int count = 1;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                matrix[r][c] = count++;
            }
        }

        String line;
        while (!"Nuke it from orbit".equals(line = scn.nextLine())) {
            List<Integer>  rcr = Arrays.stream(line.split("\\s")).map(Integer::parseInt).collect(Collectors.toList());
            int currentRow = rcr.get(0);
            int currentCol = rcr.get(1);
            int radius = rcr.get(2);

            //destroy cells horizontally
            for (int i = currentCol - radius; i <= currentCol + radius; i++) {
                if (currentRow >= 0 && currentRow < matrix.length && i >= 0 && i < matrix[currentRow].length) {
                    matrix[currentRow][i] = 0;
                }
            }

            //destroy cells vertically
            for (int i = currentRow - radius; i <= currentRow + radius; i++) {
                if (i >= 0 && i < matrix.length && currentCol >= 0 && currentCol < matrix[i].length) {
                    matrix[i][currentCol] = 0;
                }
            }

            //count non-empty rows for a new matrix
            int countNonEmptyRows = 0;
            for (int rc = 0; rc < matrix.length; rc++) {
                if (Arrays.stream(matrix[rc]).sum() != 0) {
                    countNonEmptyRows++;
                }
            }

            //initialize the new matrix
            int[][] newMatrix = new int[countNonEmptyRows][];
            int currentRowNM = 0;
            for (int roMat = 0; roMat < matrix.length; roMat++) {
                if (Arrays.stream(matrix[roMat]).sum() != 0) {
                    newMatrix[currentRowNM++] = Arrays.stream(matrix[roMat]).filter(n -> n != 0).toArray();
                }
            }

            matrix = newMatrix;

            /*
            working in the initial matrix does not give absolutely accurate answer. That is why I leave it as a comment

            for (int rw = 0; rw < row; rw++) {
                for (int cl = 0; cl < col - 1; cl++) {
                    if (matrix[rw][cl] == 0) {
                        matrix[rw][cl] = matrix[rw][cl + 1];
                        matrix[rw][cl + 1] = 0;
                    }
                }
            }

            for (int rd = row - 1; rd > 0 ; rd--) {
                if (Arrays.stream(matrix[rd]).sum() == 0) {
                    int[] colCells = matrix[rd];
                    matrix[rd] = matrix[rd - 1];
                    matrix[rd - 1] = colCells;
                }
            }
            */

        }

        //print old matrix
        /*
        for (int r = 0; r < row; r++) {

            if (Arrays.stream(matrix[r]).sum() != 0) {
                for (int c = 0; c < col; c++) {
                    if (matrix[r][c] != 0)
                    System.out.print(matrix[r][c] + " ");
                }
                System.out.println();
            }

        }
        */

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }

    }
}
