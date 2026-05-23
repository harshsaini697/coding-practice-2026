class Solution {
    public int sumFourDivisors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int ans = 0;
        for (int num : nums) {
            int count = 0;
            int sum = 0;

            for (int i = 1; i * i <= num; i++) {
                if (num % i == 0) {
                    count++;
                    sum += i;

                    if (i * i != num) {
                        count++;
                        sum += num / i;
                    }
                }
            }
            
            if (count == 4) {
                ans += sum;
            }
        }

        return ans;
    }
}

// The time complexity of this solution is O(N * sqrt(M)) where N is the number of elements in the input array nums 
// and M is the maximum value in nums. 
// The space complexity is O(1) since we are using only a constant amount of extra space to store the count 
// and sum for each number. 
// The idea is to iterate through each number in the input array and count its divisors by checking all 
// integers from 1 to the square root of the number. 
// If we find a divisor, we also check if it is a perfect square to avoid counting the same divisor twice. 
// If the count of divisors is exactly 4, we add the sum of those divisors to our answer.