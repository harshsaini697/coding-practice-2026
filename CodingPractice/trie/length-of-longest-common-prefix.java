package CodingPractice.trie;

class TrieNode {

    // Each node has up to 10 possible children (digits 0-9)
    TrieNode[] children = new TrieNode[10];
}

class Trie {

    TrieNode root = new TrieNode();

    public void insert(int num) {
        String word = String.valueOf(num);
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int index = c - '0';
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
    }

    int longestCommonPrefix(int num) {
        TrieNode curr = root;
        String word = String.valueOf(num);
        int prefixLength = 0;

        for (char c : word.toCharArray()) {
            int index = c - '0';
            if (curr.children[index] == null) {
                break;
            }
            curr = curr.children[index];
            prefixLength++;
        }

        return prefixLength;
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Trie trie = new Trie();

        // Step 1: Insert all numbers from arr1 into the Trie
        for (int num : arr1) {
            trie.insert(num);
        }

        int longestPrefix = 0;

        // Step 2: Find the longest prefix match for each number in arr2
        for (int num : arr2) {
            int len = trie.longestCommonPrefix(num);
            longestPrefix = Math.max(longestPrefix, len);
        }

        return longestPrefix;
    }
}