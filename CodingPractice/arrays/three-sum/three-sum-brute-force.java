import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length == 0){
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> result = new ArrayList();
        Set<List<Integer>> set = new HashSet<>();


        // O(N^3)
        for (int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++){
                for ( int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum == 0) {
                        
                        List<Integer> curr = new ArrayList();
                        curr.add(nums[i]);
                        curr.add(nums[j]);
                        curr.add(nums[k]);

                        Collections.sort(curr);
                        if(!set.contains(curr)) {
                            result.add(curr);
                            set.add(curr);
                        }
                    }
                }
            }
        }
        
        return result;
    }
}

// The idea is to use three nested loops to iterate through all possible combinations of three numbers in the input array.
// For each combination, we calculate the sum of the three numbers and check if it equals zero.
// If the sum is zero, we create a list of the three numbers, sort it, and check if it has already been added to the result set to avoid duplicates.
// If it has not been added, we add it to the result list and the set. 
// This approach has a time complexity of O(N^3) due to the three nested loops, and a space complexity of O(N) for the result list and set.