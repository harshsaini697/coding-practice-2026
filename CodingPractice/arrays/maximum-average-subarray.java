class Solution {
    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (k > nums.length) {
            return 0;
        }

        int sum = 0;
        for (int l = 0; l < k; l++) {
            sum += nums[l];
        }

        double res = sum;
        int j = k;
        while (j < nums.length) {
            sum += nums[j] - nums[j - k];
            j++;
            res = Math.max(res, sum);
        }

        return res/k;
    }
}

// The idea is to use a sliding window of size k to calculate the sum of each subarray of length k and keep track of the maximum sum found.
// We start by calculating the sum of the first k elements. Then, we slide the window through the array by adding the next element and removing the first element of the previous window. 
// We update our result with the maximum sum found and finally return the average by dividing the maximum sum by k.