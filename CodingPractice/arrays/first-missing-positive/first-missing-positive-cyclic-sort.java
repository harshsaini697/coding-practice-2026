class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }

        int i = 0;
        while(i < nums.length) {
            int corIdx = nums[i] - 1;
            if (nums[i] >= 1 && nums[i] <= nums.length && nums[i] != nums[corIdx]) {
                int temp = nums[i];
                nums[i] = nums[corIdx];
                nums[corIdx] = temp;
            } else {
                i++;
            }
        }

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }
}

// smallest positive integer

// nums = [7, 8, 9, 11, 12]
// seen = [false, false, false, false, false]

// The idea is to use the input array itself to keep track of which positive integers have been seen.
// We iterate through the input array and for each positive integer that is less than or equal to
// the length of the input array, we swap it to its correct position (index = value - 1).
// After processing the input array, we iterate through it again and return the first index where the
// value does not match the index + 1, which represents the smallest missing positive integer.
// If all indices from 0 to the length of the input array - 1 match their corresponding values, it means that all positive integers up to the length of the input array are present,
// so we return the next integer, which is the length of the input array plus one.  