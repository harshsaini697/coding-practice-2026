import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    private Map<String, Integer> memo = new HashMap<>();

    private int solve(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }

        String key = Arrays.toString(boxes);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int n = boxes.length;
        int best = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j < n && boxes[j] == boxes[i]) {
                j++;
            }
            
            int groupSize = j - i;
            int[] newBoxes = IntStream.concat(
                Arrays.stream(boxes, 0, i),
                Arrays.stream(boxes, j, boxes.length)
            ).toArray();
            int score = groupSize * groupSize + removeBoxes(newBoxes);
            best = Math.max(score, best);
        }

        memo.put(key, best);
        return best;
    }
    
    public int removeBoxes(int[] boxes) {
        return solve(boxes);
    }
}