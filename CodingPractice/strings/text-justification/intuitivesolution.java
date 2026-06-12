import java.util.*;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList();
        if (words == null || words.length == 0) {
            return result;
        }

        int len = 0;
        List<List<String>> lines = new ArrayList<>();
        List<String> currentLine = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (len + word.length() + currentLine.size() <= maxWidth) {
                currentLine.add(word);
                len += word.length();
            } else {
                lines.add(currentLine);
                currentLine = new ArrayList();
                currentLine.add(word);
                len = word.length();
            }
        }
        
        lines.add(currentLine);

        //Justify
        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
            List<String> lineWords = lines.get(lineIndex);

            int totalWordLength = 0;
            for (String word : lineWords) {
                totalWordLength += word.length();
            }

            int gaps = lineWords.size() - 1;
            boolean isLastLine = lineIndex == lines.size() - 1;
            StringBuilder line = new StringBuilder();

            // gaps == 0 means that only word was able to fit, in these cases, we are only going to add trailing spaces.
            if (isLastLine || gaps == 0) {
                for (int i = 0; i < lineWords.size(); i++) { 
                    if (i > 0) {
                        line.append(' ');
                    }

                    line.append(lineWords.get(i));
                }

                while (line.length() < maxWidth) {
                    line.append(' ');
                }
            } else {
                int totalSpaces = maxWidth - totalWordLength;
                int spacesPerGap = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;

                for (int i = 0; i < lineWords.size(); i++) {
                    line.append(lineWords.get(i));
                    
                    int spaces = spacesPerGap;
                    if (i < gaps) {
                        if (i < extraSpaces) {
                            spaces++;
                        }

                        line.append(" ".repeat(spaces));
                    }
                }
            }

            result.add(line.toString());
        }


        return result;
    }
}