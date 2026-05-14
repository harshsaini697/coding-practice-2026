# LeetCode Backtracking Patterns - Java Boilerplate

## 1. Subsets

Choose or skip each element.

```java
private void backtrack(int[] nums, int index, List<Integer> curr, List<List<Integer>> res) {
    if (index == nums.length) {
        res.add(new ArrayList<>(curr));
        return;
    }

    backtrack(nums, index + 1, curr, res);

    curr.add(nums[index]);
    backtrack(nums, index + 1, curr, res);
    curr.remove(curr.size() - 1);
}
```

## 2. Combinations

Pick increasing indexes.

```java
private void backtrack(int start, int n, int k, List<Integer> curr, List<List<Integer>> res) {
    if (curr.size() == k) {
        res.add(new ArrayList<>(curr));
        return;
    }

    for (int i = start; i <= n; i++) {
        curr.add(i);
        backtrack(i + 1, n, k, curr, res);
        curr.remove(curr.size() - 1);
    }
}
```

## 3. Permutations

Use a `used` array.

```java
private void backtrack(int[] nums, boolean[] used, List<Integer> curr, List<List<Integer>> res) {
    if (curr.size() == nums.length) {
        res.add(new ArrayList<>(curr));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (used[i]) {
            continue;
        }

        used[i] = true;
        curr.add(nums[i]);
        backtrack(nums, used, curr, res);
        curr.remove(curr.size() - 1);
        used[i] = false;
    }
}
```

## 4. Combination Sum

Reuse current index when the same number can be used again.

```java
private void backtrack(int[] candidates, int start, int target, List<Integer> curr, List<List<Integer>> res) {
    if (target == 0) {
        res.add(new ArrayList<>(curr));
        return;
    }

    if (target < 0) {
        return;
    }

    for (int i = start; i < candidates.length; i++) {
        curr.add(candidates[i]);
        backtrack(candidates, i, target - candidates[i], curr, res);
        curr.remove(curr.size() - 1);
    }
}
```

## 5. Backtracking With Duplicates

Sort first, then skip duplicates at the same recursion level.

```java
Arrays.sort(nums);

for (int i = start; i < nums.length; i++) {
    if (i > start && nums[i] == nums[i - 1]) {
        continue;
    }

    curr.add(nums[i]);
    backtrack(i + 1);
    curr.remove(curr.size() - 1);
}
```

## 6. Grid Backtracking

Use visited marking and undo.

```java
private boolean dfs(char[][] board, int r, int c, String word, int index) {
    if (index == word.length()) {
        return true;
    }

    if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] != word.charAt(index)) {
        return false;
    }

    char temp = board[r][c];
    board[r][c] = '#';

    boolean found =
        dfs(board, r + 1, c, word, index + 1) ||
        dfs(board, r - 1, c, word, index + 1) ||
        dfs(board, r, c + 1, word, index + 1) ||
        dfs(board, r, c - 1, word, index + 1);

    board[r][c] = temp;
    return found;
}
```

# LeetCode Practice Problems By Pattern

| # | Pattern | Practice Problem |
|---|---|---|
| 1 | Subsets | [78. Subsets](https://leetcode.com/problems/subsets/) |
| 2 | Combinations | [77. Combinations](https://leetcode.com/problems/combinations/) |
| 3 | Permutations | [46. Permutations](https://leetcode.com/problems/permutations/) |
| 4 | Combination Sum | [39. Combination Sum](https://leetcode.com/problems/combination-sum/) |
| 5 | Backtracking With Duplicates | [90. Subsets II](https://leetcode.com/problems/subsets-ii/) |
| 6 | Grid Backtracking | [79. Word Search](https://leetcode.com/problems/word-search/) |

# Recommended Study Order

| Order | Pattern | Practice Problem |
|---|---|---|
| 1 | [Subsets](#1-subsets) | [78. Subsets](https://leetcode.com/problems/subsets/) |
| 2 | [Combinations](#2-combinations) | [77. Combinations](https://leetcode.com/problems/combinations/) |
| 3 | [Permutations](#3-permutations) | [46. Permutations](https://leetcode.com/problems/permutations/) |
| 4 | [Combination Sum](#4-combination-sum) | [39. Combination Sum](https://leetcode.com/problems/combination-sum/) |
| 5 | [Backtracking With Duplicates](#5-backtracking-with-duplicates) | [90. Subsets II](https://leetcode.com/problems/subsets-ii/) |
| 6 | [Grid Backtracking](#6-grid-backtracking) | [79. Word Search](https://leetcode.com/problems/word-search/) |
