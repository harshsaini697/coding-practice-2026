class Solution {
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        if (wordsContainer == null) {
            return new int[] {};
        }

        Trie trie = new Trie();

        for (int i = 0; i < wordsContainer.length; i++) {
            trie.insert(wordsContainer[i], i);
        }

        int[] result = new int[wordsQuery.length];

        for (int i = 0; i < wordsQuery.length; i++) {
            result[i] = trie.search(wordsQuery[i]);
        }

        return result;
    }
}

class Trie {
    TrieNode root = new TrieNode();

    void insert (String word, int index) {
        TrieNode curr = root;
        updateBest(curr, word.length(), index); // for no matching suffix
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            int idx = c - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }

            curr = curr.children[idx];
            updateBest(curr, word.length(), index);
        }
    }

    int search(String word) {
        TrieNode curr = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (curr.children[index] == null) { 
                break;
            }

            curr = curr.children[index];
        }

        return curr.bestIndex;
    }

    private void updateBest(TrieNode node, int wordLength, int index) { 
        // find the string smallest in length
        // find the one that has lesser index

        if (wordLength < node.bestLength || (wordLength == node.bestLength && index < node.bestIndex)) {
            node.bestLength = wordLength;
            node.bestIndex = index;
        }
    }
}

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    int bestLength = Integer.MAX_VALUE;
    int bestIndex = Integer.MAX_VALUE;
}

// The time complexity of this solution is O(N * L + M * L) where N is the number of words in wordsContainer, M is the number of words in wordsQuery, and L is the average length of the words. The space complexity is O(N * L) for storing the Trie.
// The idea is to build a Trie based on the reversed words from wordsContainer. Each TrieNode keeps track of the best (shortest) word length and its index that can be formed from that node downwards. When searching for each query word, we traverse the Trie in reverse order and return the best index found at the last node reached.
// The reason we keep track of the best length and index at each node is to ensure that if there are multiple words that share the same suffix, we return the one with the shortest length, and if there are multiple with the same length, we return the one with the smallest index.