import java.util.*;
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];

        if (n == 0) {
            return res;
        }

        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;

        int count = 1;

        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                res[top][i] = count++;
            }

            top++;

            for (int i = top; i <= bottom; i++) {
                res[i][right] = count++;
            }

            right--;

            if (left <= right && top <= bottom) {
                for (int i = right; i >= left; i--) {
                    res[bottom][i] = count++;
                }

                bottom--;
            }

            if (top <= bottom && left <= right) {
                for (int i = bottom; i >= top; i--) {
                    res[i][left] = count++;
                }

                left++;
            }
        }

        return res;
    }
}