package com.bdilab.bikemanagement.util;


import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.*;

public class Main {

    public static void print(int m, int n) {
        char[][] arr = new char[m][n];
        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        int i=top,j=left;
        char c = 'A';
        while (top <= bottom && left <= right) {
            while (j<=right){
                arr[i][j] = c;
                c = charIncre(c);
                j++;
            }
            top++;
            if (top>bottom) break;
            i = top;
            j = right;
            while (i<=bottom) {
                arr[i][j] = c;
                c = charIncre(c);
                i++;
            }
            right--;
            if (left>right) break;
            i=bottom;
            j=right;
            while (j >= left) {
                arr[i][j]=c;
                c = charIncre(c);
                j--;
            }
            bottom--;
            if (top>bottom) break;
            i=bottom;
            j=left;
            while (i >= top) {
                arr[i][j]=c;
                c = charIncre(c);
                i--;
            }
            left++;
            if (left>right) break;
            i=top;
            j=left;
        }
        for (int k = 0; k < m; k++) {
            for (int l = 0; l < n; l++) {
                System.out.print(arr[k][l]+" ");
            }
            System.out.println();
        }
    }

    public static char charIncre(char c) {
        c++;
        if (c > 'Z') {
            c = 'A';
        }
        return c;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        print(m,n);
    }
}
