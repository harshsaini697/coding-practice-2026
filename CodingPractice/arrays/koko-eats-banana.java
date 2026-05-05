class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        if(piles == null || piles.length == 0) {
            return 0;
        }

        int left = 1;
        int right = 1;
        for(int pile: piles) {
            right = Math.max(right, pile);
        }

        while(left < right) {
            int hours = 0;
            int mid = left + (right - left) / 2;

            for (int pile: piles) {
                hours += Math.ceil((double)pile/mid);
            }

            if(hours <= h) {
                right = mid; // we are keeping the middle in the search as it could be our answer. 
                // since the question asks for the minimum, we will continue our search forward.
            } else if (hours > h){
                left = mid + 1; 
            }
        }
        
        return left;
    }
}

// [3,6,7,11], h = 8

// The idea is to use binary search to find the minimum eating speed that allows Koko to finish eating all the bananas within h hours.
// We initialize two pointers, left and right, to represent the minimum and maximum possible eating speeds, respectively. The minimum speed is 1 banana per hour, and the maximum speed is the largest pile of bananas, since Koko cannot eat more than the largest pile in one hour.
// We calculate the middle speed and determine how many hours it would take for Koko to eat all the bananas at that speed. We do this by iterating through the piles and summing up the hours needed for each pile, using the formula hours = ceil(pile / speed).
// If the total hours needed is less than or equal to h, it means that Koko can finish eating at this speed, so we can try to find a smaller speed by moving the right pointer to mid.
// If the total hours needed is greater than h, it means that Koko cannot finish eating at this speed, so we need to increase the speed by moving the left pointer to mid + 1.
// We continue this process until the left pointer is no longer less than the right pointer, at which point the left pointer will be at the minimum eating speed that allows Koko to finish eating all the bananas within h hours.