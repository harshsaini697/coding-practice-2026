# 06. Subsequence And String DP

String DP often uses two indices.

```text
dp[i][j] = answer for prefix s[0..i-1] and t[0..j-1]
```

## Longest Common Subsequence

State:

```text
dp[i][j] = LCS length of s[0..i-1] and t[0..j-1]
```

Transition:

```text
if s[i - 1] == t[j - 1]:
    dp[i][j] = 1 + dp[i - 1][j - 1]
else:
    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
```

Java:

```java
int lcs(String a, String b) {
    int n = a.length();
    int m = b.length();
    int[][] dp = new int[n + 1][m + 1];

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (a.charAt(i - 1) == b.charAt(j - 1)) {
                dp[i][j] = 1 + dp[i - 1][j - 1];
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    return dp[n][m];
}
```

## Edit Distance

State:

```text
dp[i][j] = min operations to convert word1[0..i-1] to word2[0..j-1]
```

Transitions:

```text
insert  = dp[i][j - 1]
delete  = dp[i - 1][j]
replace = dp[i - 1][j - 1]
```

## Palindrome DP

State:

```text
dp[l][r] = whether s[l..r] is palindrome
```

Transition:

```text
s[l] == s[r] and (r - l <= 2 or dp[l + 1][r - 1])
```

## Common String DP Clues

- match two strings
- transform one string into another
- count subsequences
- longest palindromic something
- wildcard or regex matching

## Interview Problems

- Longest Common Subsequence
- Edit Distance
- Distinct Subsequences
- Longest Palindromic Subsequence
- Longest Palindromic Substring
- Regular Expression Matching
- Wildcard Matching
- Interleaving String

