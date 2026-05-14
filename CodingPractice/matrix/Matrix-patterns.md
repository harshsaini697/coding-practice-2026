# LeetCode Matrix Patterns - Java Boilerplate

## 1. Basic Matrix Traversal

```java
int rows = matrix.length;
int cols = matrix[0].length;

for (int r = 0; r < rows; r++) {
    for (int c = 0; c < cols; c++) {
        // matrix[r][c]
    }
}
```

## 2. Direction Arrays

```java
int[][] dirs = {
    {1, 0},
    {-1, 0},
    {0, 1},
    {0, -1}
};

for (int[] d : dirs) {
    int nr = r + d[0];
    int nc = c + d[1];

    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
        // valid neighbor
    }
}
```

## 3. Flood Fill / Islands

```java
private void dfs(char[][] grid, int r, int c) {
    if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0') {
        return;
    }

    grid[r][c] = '0';

    dfs(grid, r + 1, c);
    dfs(grid, r - 1, c);
    dfs(grid, r, c + 1);
    dfs(grid, r, c - 1);
}
```

## 4. Multi-Source BFS

Use when many starting cells spread at the same time.

```java
Queue<int[]> q = new LinkedList<>();

for (int r = 0; r < rows; r++) {
    for (int c = 0; c < cols; c++) {
        if (grid[r][c] == sourceValue) {
            q.offer(new int[] {r, c});
        }
    }
}

int minutes = 0;

while (!q.isEmpty()) {
    int size = q.size();

    for (int i = 0; i < size; i++) {
        int[] curr = q.poll();
        // expand
    }

    minutes++;
}
```

## 5. Spiral Traversal

```java
int top = 0;
int bottom = matrix.length - 1;
int left = 0;
int right = matrix[0].length - 1;

while (top <= bottom && left <= right) {
    for (int c = left; c <= right; c++) {
        // matrix[top][c]
    }
    top++;

    for (int r = top; r <= bottom; r++) {
        // matrix[r][right]
    }
    right--;

    if (top <= bottom) {
        for (int c = right; c >= left; c--) {
            // matrix[bottom][c]
        }
        bottom--;
    }

    if (left <= right) {
        for (int r = bottom; r >= top; r--) {
            // matrix[r][left]
        }
        left++;
    }
}
```

## 6. Rotate Matrix

Transpose, then reverse each row.

```java
int n = matrix.length;

for (int r = 0; r < n; r++) {
    for (int c = r + 1; c < n; c++) {
        int temp = matrix[r][c];
        matrix[r][c] = matrix[c][r];
        matrix[c][r] = temp;
    }
}

for (int r = 0; r < n; r++) {
    int left = 0;
    int right = n - 1;

    while (left < right) {
        int temp = matrix[r][left];
        matrix[r][left] = matrix[r][right];
        matrix[r][right] = temp;
        left++;
        right--;
    }
}
```

## 7. Matrix Gravity

Use when items fall down into empty spaces.

```java
for (int col = 0; col < cols; col++) {
    int write = rows - 1;

    for (int row = rows - 1; row >= 0; row--) {
        if (grid[row][col] != 0) {
            grid[write][col] = grid[row][col];

            if (write != row) {
                grid[row][col] = 0;
            }

            write--;
        }
    }
}
```

## 8. Prefix Sum 2D

```java
int[][] prefix = new int[rows + 1][cols + 1];

for (int r = 0; r < rows; r++) {
    for (int c = 0; c < cols; c++) {
        prefix[r + 1][c + 1] =
            matrix[r][c] +
            prefix[r][c + 1] +
            prefix[r + 1][c] -
            prefix[r][c];
    }
}
```

# LeetCode Practice Problems By Pattern

| # | Pattern | Practice Problem |
|---|---|---|
| 1 | Basic Matrix Traversal | [1572. Matrix Diagonal Sum](https://leetcode.com/problems/matrix-diagonal-sum/) |
| 2 | Direction Arrays | [733. Flood Fill](https://leetcode.com/problems/flood-fill/) |
| 3 | Flood Fill / Islands | [200. Number of Islands](https://leetcode.com/problems/number-of-islands/) |
| 4 | Multi-Source BFS | [994. Rotting Oranges](https://leetcode.com/problems/rotting-oranges/) |
| 5 | Spiral Traversal | [54. Spiral Matrix](https://leetcode.com/problems/spiral-matrix/) |
| 6 | Rotate Matrix | [48. Rotate Image](https://leetcode.com/problems/rotate-image/) |
| 7 | Matrix Gravity | [1861. Rotating the Box](https://leetcode.com/problems/rotating-the-box/) |
| 8 | Prefix Sum 2D | [304. Range Sum Query 2D - Immutable](https://leetcode.com/problems/range-sum-query-2d-immutable/) |

# Recommended Study Order

| Order | Pattern | Practice Problem |
|---|---|---|
| 1 | [Basic Matrix Traversal](#1-basic-matrix-traversal) | [1572. Matrix Diagonal Sum](https://leetcode.com/problems/matrix-diagonal-sum/) |
| 2 | [Direction Arrays](#2-direction-arrays) | [733. Flood Fill](https://leetcode.com/problems/flood-fill/) |
| 3 | [Flood Fill / Islands](#3-flood-fill--islands) | [200. Number of Islands](https://leetcode.com/problems/number-of-islands/) |
| 4 | [Multi-Source BFS](#4-multi-source-bfs) | [994. Rotting Oranges](https://leetcode.com/problems/rotting-oranges/) |
| 5 | [Spiral Traversal](#5-spiral-traversal) | [54. Spiral Matrix](https://leetcode.com/problems/spiral-matrix/) |
| 6 | [Rotate Matrix](#6-rotate-matrix) | [48. Rotate Image](https://leetcode.com/problems/rotate-image/) |
| 7 | [Matrix Gravity](#7-matrix-gravity) | [1861. Rotating the Box](https://leetcode.com/problems/rotating-the-box/) |
