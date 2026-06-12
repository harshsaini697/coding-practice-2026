import java.util.List;

class SolutionTest {
    public static void main(String[] args) {
        Solution solution = new Solution();

        assertLines(
                solution.fullJustify(
                        new String[]{"This", "is", "an", "example", "of", "text", "justification."},
                        16),
                new String[]{"This    is    an", "example  of text", "justification.  "});

        assertLines(
                solution.fullJustify(
                        new String[]{"What", "must", "be", "acknowledgment", "shall", "be"},
                        16),
                new String[]{"What   must   be", "acknowledgment  ", "shall be        "});

        System.out.println("All text justification tests passed.");
    }

    private static void assertLines(List<String> actual, String[] expected) {
        if (actual.size() != expected.length) {
            throw new AssertionError("Expected " + expected.length + " lines but got " + actual.size());
        }

        for (int i = 0; i < expected.length; i++) {
            if (!actual.get(i).equals(expected[i])) {
                throw new AssertionError(
                        "Line " + i + " expected [" + expected[i] + "] but got [" + actual.get(i) + "]");
            }
        }
    }
}
