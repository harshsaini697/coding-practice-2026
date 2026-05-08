class Solution {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int total = rows * cols;
        int[][] res = new int[total][2];

        int count = 0;
        int r = rStart;
        int c = cStart;

        res[count++] = new int[] {r, c};

        int step = 1;

        while(count < total) {
            // move towards right
            for (int i = 0; i < step; i++) {
                c++;
                if (r >= 0 && r < rows && c >= 0 && c < cols) {
                    res[count++] = new int[] {r, c};
                }
            }

            // move towards down
            for (int i = 0; i < step; i++) {
                r++;
                if (r >= 0 && r < rows && c >= 0 && c < cols) {
                    res[count++] = new int[] {r, c};
                }
            }

            step++;

            // move left step times
            for (int i = 0; i < step; i++) {
                c--;
                if (r >= 0 && r < rows && c >= 0 && c < cols) {
                    res[count++] = new int[] {r, c};
                }
            }

            // move up step times
            for (int i = 0; i < step; i++) {
                r--;
                if (r >= 0 && r < rows && c >= 0 && c < cols) {
                    res[count++] = new int[] {r, c};
                }
            }

            step++;
        }

        return res;
        
    }
}