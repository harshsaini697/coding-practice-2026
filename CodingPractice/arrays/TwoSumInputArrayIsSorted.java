class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return new int[]{};
        }

        int i = 0;
        int j = numbers.length - 1;

        while(i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[] {i + 1, j + 1};
            } else if (sum > target) {
                j--;
            } else {
                i++;
            }
        }

        return new int[] {-1,-1};
    }
}

// The idea is to use two pointers, 
// one starting at the beginning of the array and the other starting at the end of the array.
// We calculate the sum of the two numbers at the pointers.
// If the sum is equal to the target, we return the indices of the two numbers.
// If the sum is greater than the target, we move the right pointer to the left to decrease the sum.
// If the sum is less than the target, we move the left pointer to the right to increase the sum.
// We continue this process until we find the two numbers that add up to the target or until the pointers cross each other.
// Note: we are able to do this since the array is sorted, 
// which allows us to make informed decisions about which pointer to move based on the sum compared to the target.