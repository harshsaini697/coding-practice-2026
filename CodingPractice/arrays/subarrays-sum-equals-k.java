class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        HashMap<Integer, Integer> prefixMap = new HashMap();
        prefixMap.put(0, 1);
        int prefix = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            if (prefixMap.containsKey(prefix - k)) {
                res += prefixMap.get(prefix - k);
            }

            prefixMap.put(prefix, prefixMap.getOrDefault(prefix, 0) + 1);
        }

        return res;
    }
}

// The idea is to use a prefix sum and a hashmap to count the number of times a particular prefix sum has been seen. 
// We iterate through the array, calculating the prefix sum at each step. 
// If the difference between the current prefix sum and k has been seen before, 
// it means there is a subarray that sums to k, and we increment our result by the count of that prefix sum in the hashmap. 
// Finally, we update the hashmap with the current prefix sum.

// As per our understanding before, 
// prefix[i] = nums[0] + nums[1] + ... + nums[i]
// If we want to find a subarray that sums to k, we can rearrange the equation to find the prefix sum that we need to have seen before:
// prefix[i] - prefix[j] = k
// This means that if we have seen a prefix sum of prefix[i] - k before
//  then there is a subarray from index j+1 to i that sums to k.