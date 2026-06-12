import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int start = 0;

        while (start < words.length) {
            int end = start + 1;
            int wordsLength = words[start].length();

            // Include as many words as possible. Each existing gap needs one space.
            while (end < words.length
                    && wordsLength + words[end].length() + (end - start) <= maxWidth) {
                wordsLength += words[end].length();
                end++;
            }

            int wordCount = end - start;
            int gapCount = wordCount - 1;
            boolean isLastLine = end == words.length;
            StringBuilder line = new StringBuilder(maxWidth);

            if (isLastLine || gapCount == 0) {
                // Last lines and single-word lines are left justified.
                for (int i = start; i < end; i++) {
                    if (i > start) {
                        line.append(' ');
                    }
                    line.append(words[i]);
                }
                appendSpaces(line, maxWidth - line.length());
            } else {
                int totalSpaces = maxWidth - wordsLength;
                int spacesPerGap = totalSpaces / gapCount;
                int extraSpaces = totalSpaces % gapCount;

                for (int i = start; i < end; i++) {
                    line.append(words[i]);

                    if (i < end - 1) {
                        int spaces = spacesPerGap;
                        if (i - start < extraSpaces) {
                            spaces++;
                        }
                        appendSpaces(line, spaces);
                    }
                }
            }

            result.add(line.toString());
            start = end;
        }

        return result;
    }

    private void appendSpaces(StringBuilder line, int count) {
        for (int i = 0; i < count; i++) {
            line.append(' ');
        }
    }
}
