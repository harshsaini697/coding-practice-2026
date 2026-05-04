class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int i = 0;
        int j = 0;

        while (j < nums.length) {
            if (nums[i] == nums[j]) {
                j++;
            } else {
                nums[i + 1] = nums[j];
                i++;
                j++;
            }
        }

        return i + 1;
    }
}

// The idea is to use two pointers,
// one pointer (i) to keep track of the position of the last unique element and another pointer (j) to iterate through the array.
// We compare the elements at the two pointers. If they are the same, we move the j pointer to the right to skip the duplicate.
// If they are different, we move the i pointer to the right and copy the unique element from the j pointer to the position after the last unique element.
// We continue this process until we have iterated through the entire array. Finally, we return the length of the unique elements, which is i + 1 since i is zero-indexed.