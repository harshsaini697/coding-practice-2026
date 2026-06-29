import java.util.HashMap;
class Solution {
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap();
        map.put(0, -1); // sum, index

        int res = 0;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 0 ? -1 : 1;

            if (map.containsKey(sum)) {
                res = Math.max(res, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }

        return res;
    }
}
// we need to create a running sum of the array and then detect if the same sum occurence as that will determine the number of 0's and 1 are same.
// We would also require a hashmap to track the count

// Time Complexity: O(N) where N is the length of the array
// Space Complexity: O(N) where N is the length of the array
// The idea is to use a hashmap to store the running sum and its index. 
// We can then check if the same sum has occurred before, which would mean that the number of 0's and 1's are the same between those two indices. 
// We can then calculate the length of that subarray and update the result accordingly.  