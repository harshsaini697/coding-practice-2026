class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList();

        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];

            if (interval[0] <= prev[1]) {
                prev[1] = Math.max(prev[1], interval[1]);
            } else {
                merged.add(prev);
                prev = interval;
            }
        }

        merged.add(prev);

        return merged.toArray(new int[merged.size()] {});
    }
}
// The idea is to first sort the intervals based on their start times. 
// Then, we iterate through the sorted intervals and merge them if they overlap. 
// We keep track of the previous interval and compare it with the current interval. 
// If the start time of the current interval is less than or equal to the end time of the previous interval, it means they overlap, 
// and we merge them by updating the end time of the previous interval to be the maximum of the end times of both intervals. 
// If they do not overlap, we add the previous interval to our merged list and move on to the current interval. 
// Finally, we add the last interval to the merged list and return the result as an array.