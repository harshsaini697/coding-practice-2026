class Solution {
    public int maxSubArray(int[] nums) {
        int prefix = 0;
        int minPrefix = 0;
        int best = Integer.MIN_VALUE;

        for (int x : nums) {
            prefix += x;

            best = Math.max(best, prefix - minPrefix);

            minPrefix = Math.min(minPrefix, prefix);
        }

        return best;
    }
}

// The idea is to use a prefix sum and keep track of the minimum prefix sum seen so far.
// We iterate through the array, calculating the prefix sum at each step.
// The maximum subarray sum ending at the current index can be found by subtracting the minimum prefix sum 
// from the current prefix sum.
// We update our result with the maximum value found and also update the minimum prefix sum if the current prefix 
// sum is smaller than the minimum seen so far.