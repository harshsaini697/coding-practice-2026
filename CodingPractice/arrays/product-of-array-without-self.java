class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[] {};
        }

        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1]; // 1-> 1 * 1
        }

        int suffix = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= suffix;
            suffix *= nums[i];
        }

        return res;
    }
}

// nums = [1, 2, 3, 4]
// res = [1, 1, 2, 6]
// suffix = 1
// i = 3, res[3] = 6 * 1, suffix = 1 * 4
// i = 2, res[2] = 2 * 4, suffix = 4 * 3
// i = 1, res[1] = 1 * 12, suffix = 12 * 2
// i = 0, res[0] = 1 * 24, suffix = 24 * 1

// The idea is to calculate the product of all elements to the left of each index and store it in the result array.
// Then, we iterate through the input array from the end and calculate the product of all elements to the right of each index using a suffix variable, and multiply it with the corresponding value in the result array.
// This way, we can get the product of all elements except self for each index without using division and in O(n) time complexity.