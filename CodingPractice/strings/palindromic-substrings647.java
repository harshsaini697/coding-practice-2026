class Solution {
    int count = 0;
    public int countSubstrings(String s) {
        for (int start = 0; start < s.length(); start++) {
            expand (s, start, start);
            expand (s, start, start + 1);
        }

        return count;
    }

    private void expand(String s, int left, int right) {
        while (
            left >= 0 && 
            right < s.length() && 
            s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
    }
}

// The idea of this solution is to expand around center
// each string either odd or even has a center
// racecar -> when e is encountered, it should find the palindrome
// abba -> b,b is the center and that's the reason second start is used.
