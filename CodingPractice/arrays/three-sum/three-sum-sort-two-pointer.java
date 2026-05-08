import java.util.*;
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length == 0){
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> res = new ArrayList<>();
        // two pointer with sorting
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int j = i + 1;
            int k = nums.length - 1;

            while(j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    while(j < k && nums[j] == nums[j - 1]) j++;
                    while(j < k && nums[k] == nums[k + 1]) k--;
                } else if (sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        
        return res;
    }
}

// Time complexity: O(n^2)
// Space complexity: O(1) if we don't consider the space for output list, otherwise O(n) for the output list.

// The idea is to sort the array and then use two pointers to find pairs that sum up to the negative of the current element. 
// We also need to skip duplicates to avoid repeating the same triplet in the output.