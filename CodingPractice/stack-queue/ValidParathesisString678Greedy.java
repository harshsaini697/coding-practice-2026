class Solution {
    public boolean checkValidString(String s) {
        int cmax = 0;
        int cmin = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                cmax++;
                cmin++;
            } else if (c == '*') {
                cmax++;
                cmin--;
            } else if (c == ')') {
                cmax--;
                cmin--;
            }

            cmin = Math.max(0, cmin);

            if (cmax < 0) {
                return false;
            }
        }

        return cmin == 0;
    }
}

// Time Complexity: O(n) where n is the length of the string
// Space Complexity: O(1) as we are using constant space
// The idea is to use two counters, cmax and cmin, to keep track of the maximum and minimum number of open parentheses that can be formed at any point in the string.   
// cmax is incremented for every '(' and '*' and decremented for every ')'. cmin is incremented for every '(' and decremented for every ')' and '*'.
// If at any point cmax becomes negative, it means there are more ')' than '(' and '*' combined, so we return false. 
// If cmin becomes negative, we reset it to 0 because we cannot have negative open parentheses. 
// At the end, if cmin is 0, it means we have a valid string, otherwise we return false.
// If we encounter a ')' and cmin is 0, it means we have an unmatched ')' and we return false.