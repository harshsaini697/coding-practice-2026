class Solution {
    public int maxArea(int[] height) {
        //edge case
        if (height == null || height.length == 0) {
            return 0;
        }

        int max = 0;

        int low = 0;
        int high = height.length - 1;

        while (low < high) {
            int min = Math.min(height[low], height[high]);
            max = Math.max(max, min * (high - low));
            if (height[low] <= height[high]) {
                low++;
            } else {
                high--;
            }
        }

        return max;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)

// The brute-force approach would be to check all possible pairs of lines and calculate the area for each pair, which would have a time complexity of O(n^2). 
// The two-pointer approach optimizes this by starting with the widest possible container and then moving the pointers inward based on the heights of the lines, resulting in a linear time complexity of O(n).