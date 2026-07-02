import java.util.Stack;
class Solution {
    public boolean checkValidString(String s) {
        Stack<Character> leftStack = new Stack<>();
        Stack<Character> starStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftStack.push(c);
            } else if (c == '*') {
                starStack.push(c);
            } else if (c == ')') {
                if (!leftStack.isEmpty()) {
                    leftStack.pop();
                } else if (!starStack.isEmpty()) {
                    starStack.pop();
                } else {
                    return false;
                }
            }
        }

        while (!leftStack.isEmpty() && !starStack.isEmpty()) {
            leftStack.pop();
            starStack.pop();
        }

        return leftStack.isEmpty();
    }
}

//  Rule 1. ()
// Rule 2. Right ) should have a corressponding left
// Rule 3. * can be either ( or ) or empty

// Pseudo

// Take a stack and track mismatches

// when the character is ( push it to the stack
// It should have a corresponding ) in the stack. If not then we can use * to balance it out. 
// If * is not available then return false
// Maintain a count of * and ( in the stack. If ) is encountered then check if there is a corresponding ( or * in the stack. If not then return false.