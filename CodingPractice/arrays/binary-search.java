class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}

// The idea is to use binary search to find the target in the sorted array.
// We initialize two pointers, left and right, to the start and end of the array, respectively.
// We calculate the middle index and compare the middle element with the target.
// If the middle element is equal to the target, we return the middle index.
// If the middle element is less than the target, we know that the target must be in the right half of the array, so we move the left pointer to mid + 1.
// If the middle element is greater than the target, we know that the target must be in the left half of the array, so we move the right pointer to mid - 1.
// We continue this process until the left pointer is greater than the right pointer, which means that the target is not in the array, and we return -1.