import java.util.*;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if(matrix == null || matrix.length == 0) {
            return result;
        }
        
        int n = matrix.length;
        int m = matrix[0].length;

        int top = 0;
        int left = 0;
        int bottom = n - 1;
        int right = m - 1;

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }

            top++;
            for(int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            
            right--;
            if (left <= right && top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            if (top <= bottom && left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        return result;
    }
}