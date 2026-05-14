# LeetCode Trie Patterns - Java Boilerplate

## 1. Basic Trie Node

```java
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isWord;
}
```

## 2. Insert Word

```java
void insert(String word) {
    TrieNode curr = root;

    for (char c : word.toCharArray()) {
        int idx = c - 'a';

        if (curr.children[idx] == null) {
            curr.children[idx] = new TrieNode();
        }

        curr = curr.children[idx];
    }

    curr.isWord = true;
}
```

## 3. Search Word

```java
boolean search(String word) {
    TrieNode curr = root;

    for (char c : word.toCharArray()) {
        int idx = c - 'a';

        if (curr.children[idx] == null) {
            return false;
        }

        curr = curr.children[idx];
    }

    return curr.isWord;
}
```

## 4. Starts With

```java
boolean startsWith(String prefix) {
    TrieNode curr = root;

    for (char c : prefix.toCharArray()) {
        int idx = c - 'a';

        if (curr.children[idx] == null) {
            return false;
        }

        curr = curr.children[idx];
    }

    return true;
}
```

## 5. Digit Trie

Use for numeric prefix problems.

```java
class TrieNode {
    TrieNode[] children = new TrieNode[10];
}
```

## 6. Trie + DFS Board Search

Use for word search with many words.

```java
private void dfs(char[][] board, int r, int c, TrieNode node) {
    if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) {
        return;
    }

    char ch = board[r][c];

    if (ch == '#' || node.children[ch - 'a'] == null) {
        return;
    }

    node = node.children[ch - 'a'];
    board[r][c] = '#';

    dfs(board, r + 1, c, node);
    dfs(board, r - 1, c, node);
    dfs(board, r, c + 1, node);
    dfs(board, r, c - 1, node);

    board[r][c] = ch;
}
```

# LeetCode Practice Problems By Pattern

| # | Pattern | Practice Problem |
|---|---|---|
| 1 | Basic Trie | [208. Implement Trie](https://leetcode.com/problems/implement-trie-prefix-tree/) |
| 2 | Prefix Replacement | [648. Replace Words](https://leetcode.com/problems/replace-words/) |
| 3 | Word Dictionary | [211. Design Add and Search Words Data Structure](https://leetcode.com/problems/design-add-and-search-words-data-structure/) |
| 4 | Digit Trie | [3043. Find the Length of the Longest Common Prefix](https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/) |
| 5 | Trie + DFS | [212. Word Search II](https://leetcode.com/problems/word-search-ii/) |

# Recommended Study Order

| Order | Pattern | Practice Problem |
|---|---|---|
| 1 | [Basic Trie](#1-basic-trie-node) | [208. Implement Trie](https://leetcode.com/problems/implement-trie-prefix-tree/) |
| 2 | [Prefix Replacement](#4-starts-with) | [648. Replace Words](https://leetcode.com/problems/replace-words/) |
| 4 | [Digit Trie](#5-digit-trie) | [3043. Find the Length of the Longest Common Prefix](https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/) |
| 5 | [Trie + DFS](#6-trie--dfs-board-search) | [212. Word Search II](https://leetcode.com/problems/word-search-ii/) |
