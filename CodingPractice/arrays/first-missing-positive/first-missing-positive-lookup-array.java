class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }

        boolean[] seen = new boolean[nums.length + 1];

        for (int num: nums) {
            if(num > 0 && num <= nums.length){
                seen[num] = true;
            }
        }

        for(int i = 1; i < seen.length; i++) {
            if(seen[i] == false) {
                return i;
            }
        }

        return seen.length;
    }
}

// smallest positive integer

// nums = [7, 8, 9, 11, 12]
// seen = [false, false, false, false, false]

// The idea is to use a boolean array to keep track of which positive integers have been seen in the input array.
// We iterate through the input array and mark the corresponding index in the boolean array as true for
// each positive integer that is less than or equal to the length of the input array.
// After processing the input array, we iterate through the boolean array starting from index 1 and return the first index that is still false, 
// which represents the smallest missing positive integer.
// If all indices from 1 to the length of the input array are true, it means that all positive integers up to the length of the input array are present, 
// so we return the next integer, which is the length of the input array plus one.