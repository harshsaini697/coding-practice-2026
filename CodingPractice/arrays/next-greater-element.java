class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return new int[] {};
        }

        HashMap<Integer, Integer> map = new HashMap();
        Stack<Integer> st = new Stack();

        for (int num: nums2) {
            while(!st.isEmpty() && st.peek() < num) {
                map.put(st.pop(), num);
            }

            st.push(num);
        }

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++){
            res[i] = map.getOrDefault(nums1[i], -1);
        }

        return res;
    }
}

// The idea is to use a stack to keep track of the elements in nums2 and a hashmap to store the next greater element for each number in nums2.
// We iterate through nums2 and for each number, we pop elements from the stack until we find a number that is greater than the current number. 
// For each popped element, we store the current number as the next greater element in the hashmap.
// After processing nums2, we iterate through nums1 and for each number, we look up the next greater element in the hashmap. 
// If it exists, we add it to the result array; otherwise, we add -1. Finally, we return the