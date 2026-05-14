# LeetCode String Patterns - Java Boilerplate

## 1. Character Frequency

Use when the string contains lowercase letters, uppercase letters, digits, or a small fixed alphabet.

```java
int[] freq = new int[26];

for (char c : s.toCharArray()) {
    freq[c - 'a']++;
}
```

For all ASCII characters:

```java
int[] freq = new int[128];

for (char c : s.toCharArray()) {
    freq[c]++;
}
```

## 2. HashMap Frequency

Use when characters are not limited to a small alphabet, or when counting words/tokens.

```java
Map<Character, Integer> freq = new HashMap<>();

for (char c : s.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}
```

## 3. Two Pointers

Use for palindromes, reversing, comparing from both ends, or skipping invalid characters.

```java
int left = 0;
int right = s.length() - 1;

while (left < right) {
    char a = s.charAt(left);
    char b = s.charAt(right);

    if (a != b) {
        return false;
    }

    left++;
    right--;
}

return true;
```

## 4. Sliding Window - No Repeats

Use for longest substring without duplicate characters.

```java
Map<Character, Integer> window = new HashMap<>();
int left = 0;
int best = 0;

for (int right = 0; right < s.length(); right++) {
    char in = s.charAt(right);
    window.put(in, window.getOrDefault(in, 0) + 1);

    while (window.get(in) > 1) {
        char out = s.charAt(left);
        window.put(out, window.get(out) - 1);
        left++;
    }

    best = Math.max(best, right - left + 1);
}
```

## 5. Sliding Window - Fixed Length

Use when the substring length is fixed, often anagrams or permutations.

```java
int[] need = new int[26];
int[] window = new int[26];

for (char c : p.toCharArray()) {
    need[c - 'a']++;
}

for (int right = 0; right < s.length(); right++) {
    window[s.charAt(right) - 'a']++;

    if (right >= p.length()) {
        int left = right - p.length();
        window[s.charAt(left) - 'a']--;
    }

    if (right >= p.length() - 1 && Arrays.equals(need, window)) {
        // found an anagram ending at right
    }
}
```

## 6. Sliding Window - Minimum Cover

Use when finding the shortest substring containing required characters.

```java
Map<Character, Integer> need = new HashMap<>();

for (char c : t.toCharArray()) {
    need.put(c, need.getOrDefault(c, 0) + 1);
}

Map<Character, Integer> window = new HashMap<>();
int have = 0;
int required = need.size();
int left = 0;
int bestLen = Integer.MAX_VALUE;
int bestStart = 0;

for (int right = 0; right < s.length(); right++) {
    char in = s.charAt(right);
    window.put(in, window.getOrDefault(in, 0) + 1);

    if (need.containsKey(in) && window.get(in).intValue() == need.get(in).intValue()) {
        have++;
    }

    while (have == required) {
        if (right - left + 1 < bestLen) {
            bestLen = right - left + 1;
            bestStart = left;
        }

        char out = s.charAt(left);
        window.put(out, window.get(out) - 1);

        if (need.containsKey(out) && window.get(out) < need.get(out)) {
            have--;
        }

        left++;
    }
}
```

## 7. StringBuilder

Use when repeatedly appending, deleting, reversing, or building output.

```java
StringBuilder sb = new StringBuilder();

for (char c : s.toCharArray()) {
    sb.append(c);
}

String result = sb.toString();
```

Reverse:

```java
String reversed = new StringBuilder(s).reverse().toString();
```

## 8. Stack For String Cleanup

Use when adjacent characters cancel or nested structures must be processed.

```java
Deque<Character> stack = new ArrayDeque<>();

for (char c : s.toCharArray()) {
    if (!stack.isEmpty() && stack.peek() == c) {
        stack.pop();
    } else {
        stack.push(c);
    }
}

StringBuilder sb = new StringBuilder();

while (!stack.isEmpty()) {
    sb.append(stack.removeLast());
}
```

## 9. Parentheses / Balance Counter

Use when only balance matters.

```java
int balance = 0;

for (char c : s.toCharArray()) {
    if (c == '(') {
        balance++;
    } else if (c == ')') {
        balance--;
    }

    if (balance < 0) {
        return false;
    }
}

return balance == 0;
```

## 10. Split / Token Processing

Use when words are separated by whitespace or punctuation.

```java
String[] words = s.trim().split("\\s+");

for (String word : words) {
    // process word
}
```

## 11. Prefix / Suffix Matching

Use for longest common prefix, word replacement, or checking boundaries.

```java
String prefix = words[0];

for (int i = 1; i < words.length; i++) {
    while (!words[i].startsWith(prefix)) {
        prefix = prefix.substring(0, prefix.length() - 1);
    }
}
```

## 12. Trie For Prefix Search

Use when many prefix queries are needed.

```java
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isWord;
}

class Trie {
    TrieNode root = new TrieNode();

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
}
```

## 13. Rolling Hash

Use for repeated substring comparison, duplicate substrings, and Rabin-Karp style matching.

```java
long hash = 0;
long base = 31;
long mod = 1_000_000_007L;

for (char c : s.toCharArray()) {
    hash = (hash * base + c) % mod;
}
```

## 14. KMP Prefix Function

Use for efficient pattern matching.

```java
int[] lps = new int[pattern.length()];
int len = 0;

for (int i = 1; i < pattern.length(); i++) {
    while (len > 0 && pattern.charAt(i) != pattern.charAt(len)) {
        len = lps[len - 1];
    }

    if (pattern.charAt(i) == pattern.charAt(len)) {
        len++;
        lps[i] = len;
    }
}
```

## 15. Expand Around Center

Use for palindromic substrings.

```java
private int expand(String s, int left, int right) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
        left--;
        right++;
    }

    return right - left - 1;
}
```

## Mental Shortcuts

## Character Counts

Think:

```text
frequency array / hashmap
```

## Longest Substring

Think:

```text
sliding window
```

## Prefix Queries

Think:

```text
trie / hashset prefixes
```

## Matching Pattern Inside Text

Think:

```text
KMP / rolling hash
```

## Palindrome

Think:

```text
two pointers / expand around center / DP
```

# LeetCode Practice Problems By Pattern

| # | Pattern | Practice Problem |
|---|---|---|
| 1 | Character Frequency | [242. Valid Anagram](https://leetcode.com/problems/valid-anagram/) |
| 2 | HashMap Frequency | [387. First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string/) |
| 3 | Two Pointers | [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/) |
| 4 | Sliding Window - No Repeats | [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) |
| 5 | Sliding Window - Fixed Length | [438. Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/) |
| 6 | Sliding Window - Minimum Cover | [76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) |
| 7 | StringBuilder | [151. Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string/) |
| 8 | Stack Cleanup | [1047. Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/) |
| 9 | Parentheses Balance | [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) |
| 10 | Split / Token Processing | [819. Most Common Word](https://leetcode.com/problems/most-common-word/) |
| 11 | Prefix / Suffix Matching | [14. Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/) |
| 12 | Trie For Prefix Search | [208. Implement Trie](https://leetcode.com/problems/implement-trie-prefix-tree/) |
| 13 | Rolling Hash | [187. Repeated DNA Sequences](https://leetcode.com/problems/repeated-dna-sequences/) |
| 14 | KMP | [28. Find the Index of the First Occurrence in a String](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/) |
| 15 | Expand Around Center | [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) |

# Recommended Study Order

```text
1, 3, 4, 5, 6, 8, 9, 11, 12, 15, 14, 13
```

Expanded:

| Order | Pattern | Practice Problem |
|---|---|---|
| 1 | [Character Frequency](#1-character-frequency) | [242. Valid Anagram](https://leetcode.com/problems/valid-anagram/) |
| 3 | [Two Pointers](#3-two-pointers) | [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/) |
| 4 | [Sliding Window - No Repeats](#4-sliding-window---no-repeats) | [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) |
| 5 | [Sliding Window - Fixed Length](#5-sliding-window---fixed-length) | [438. Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/) |
| 6 | [Sliding Window - Minimum Cover](#6-sliding-window---minimum-cover) | [76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) |
| 8 | [Stack Cleanup](#8-stack-for-string-cleanup) | [1047. Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/) |
| 9 | [Parentheses Balance](#9-parentheses--balance-counter) | [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) |
| 11 | [Prefix / Suffix Matching](#11-prefix--suffix-matching) | [14. Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/) |
| 12 | [Trie For Prefix Search](#12-trie-for-prefix-search) | [208. Implement Trie](https://leetcode.com/problems/implement-trie-prefix-tree/) |
| 15 | [Expand Around Center](#15-expand-around-center) | [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) |
| 14 | [KMP](#14-kmp-prefix-function) | [28. Find the Index of the First Occurrence in a String](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/) |
| 13 | [Rolling Hash](#13-rolling-hash) | [187. Repeated DNA Sequences](https://leetcode.com/problems/repeated-dna-sequences/) |
