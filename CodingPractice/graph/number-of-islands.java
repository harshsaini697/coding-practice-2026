import java.util.*;

class Solution {
    int result = 0;
    int[][] dirs = new int[][]{{0,1},{1,0},{-1,0},{0,-1}};
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        
        Queue<int[]> q = new LinkedList();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    //dfs(grid, i, j);
                    q.add(new int[] {i, j});// 0,0
                    grid[i][j] = '0';
                    
                    while(!q.isEmpty()) {
                        int[] curr = q.poll();
                        
                        for(int k = 0; k < dirs.length; k++) {
                            int x = dirs[k][0] + curr[0];
                            int y = dirs[k][1] + curr[1];
                            
                            if(x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == '0') {
                                continue;
                            }
                            grid[x][y] = '0';
                            q.add(new int[] {x, y});
                        }  
                    }
                    result++;
                    //result++;
                }
            }
        }
        
        
        
        return result;
    }
    
    
    private void dfs(char[][] grid, int x, int y) {
        // base
        if(x >= grid.length || y >= grid[0].length || x < 0 || y < 0) {
            return;
        }
        if(grid[x][y] != '1') {
            return;
        }
        
        //recurse
        for(int i = 0; i < dirs.length; i++) {
            int m = dirs[i][0] + x;
            int n = dirs[i][1] + y;
            grid[x][y] = '0'; 
            dfs(grid, m, n);
        }
        
    }
    
}