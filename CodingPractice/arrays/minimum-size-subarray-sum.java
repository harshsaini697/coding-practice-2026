class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }

        int left = 0;
        int best = Integer.MAX_VALUE;
        int sum = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum >= target) {
                best = Math.min(best, right - left + 1);
                sum -= nums[left];
                left++; 
            }     
        }

        return best == Integer.MAX_VALUE ? 0 : best;
    }
}
// The idea is to use a sliding window approach with two pointers, left and right.
// We expand the right pointer to increase the sum until it is greater than or equal to the target.
// Once we have a valid window, we try to shrink it from the left to find the minimum length of the subarray that meets the condition.
// We keep track of the best (minimum) length found and return it at the end. If we never find a valid window, we return 0.