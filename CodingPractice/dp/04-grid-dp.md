# 04. Grid DP

Use grid DP when movement happens through a matrix.

Common moves:

```text
right and down
up, down, left, right
diagonal
```

## Basic Path Count

State:

```text
dp[r][c] = number of ways to reach cell (r, c)
```

Transition for right/down movement:

```text
dp[r][c] = dp[r - 1][c] + dp[r][c - 1]
```

## Minimum Path Sum

State:

```text
dp[r][c] = minimum cost to reach cell (r, c)
```

Transition:

```text
dp[r][c] = grid[r][c] + min(dp[r - 1][c], dp[r][c - 1])
```

## Obstacles

If a cell is blocked:

```text
dp[r][c] = 0
```

Otherwise use normal transition.

## Java Template

```java
int minPathSum(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    int[][] dp = new int[rows][cols];

    dp[0][0] = grid[0][0];

    for (int r = 1; r < rows; r++) {
        dp[r][0] = dp[r - 1][0] + grid[r][0];
    }

    for (int c = 1; c < cols; c++) {
        dp[0][c] = dp[0][c - 1] + grid[0][c];
    }

    for (int r = 1; r < rows; r++) {
        for (int c = 1; c < cols; c++) {
            dp[r][c] = grid[r][c] + Math.min(dp[r - 1][c], dp[r][c - 1]);
        }
    }

    return dp[rows - 1][cols - 1];
}
```

## Space Optimization

If each cell only depends on previous row and current row:

```java
int[] dp = new int[cols];
```

## Interview Problems

- Unique Paths
- Unique Paths II
- Minimum Path Sum
- Dungeon Game
- Maximal Square
- Triangle

