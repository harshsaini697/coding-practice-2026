import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int orangesRotting(int[][] grid) {
        int[][] dirs = {{0,1}, {1,0}, {-1,0}, {0,-1}};
        Queue<int[]> q = new LinkedList();
        int fresh = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    q.add(new int[] {i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                } 
            }
        }

        if (fresh == 0) return 0;
        int time = -1;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] element = q.poll();
                for (int[] dir: dirs) {
                    int x = element[0] + dir[0];
                    int y = element[1] + dir[1];
                    if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                        q.add(new int[] {x , y});
                        fresh--;
                        grid[x][y] = 2; 
                    }
                }
            }
            time++;
        }

        if (fresh > 0) {
            return -1;
        }

        return time;
    }
}


// Basically we are going to find all oranges in the neighbors
// After one iteration
// the oranges in the 4 direction goes bad
// this is a classic BFS problem
// The time complexity of this solution is O(N * M) where N is the number of rows and M is the number of columns in the grid. 
// The space complexity is O(N * M) in the worst case when all oranges are rotten and added to the queue.
