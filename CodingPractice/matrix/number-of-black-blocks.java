import java.util.*;

class Solution {
    public long[] countBlackBlocks(int m, int n, int[][] coordinates) {
        Map<Long, Integer> blockCounts = new HashMap<>();

        for (int[] coordinate : coordinates) {
            int row = coordinate[0];
            int col = coordinate[1];

            for (int topRow = row - 1; topRow <= row; topRow++) {
                for (int leftCol = col - 1; leftCol <= col; leftCol++) {
                    if (topRow >= 0 && topRow < m - 1 && leftCol >= 0 && leftCol < n - 1) {
                        long key = getKey(topRow, leftCol, n);
                        blockCounts.put(key, blockCounts.getOrDefault(key, 0) + 1);
                    }
                }
            }
        }

        long[] result = new long[5];
        for (int blackCells : blockCounts.values()) {
            result[blackCells]++;
        }

        long totalBlocks = (long) (m - 1) * (n - 1);
        result[0] = totalBlocks - blockCounts.size();
        return result;
    }

    private long getKey(int row, int col, int n) {
        return (long) row * n + col;
    }
}
