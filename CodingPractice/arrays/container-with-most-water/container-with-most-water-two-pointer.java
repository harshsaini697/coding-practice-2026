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