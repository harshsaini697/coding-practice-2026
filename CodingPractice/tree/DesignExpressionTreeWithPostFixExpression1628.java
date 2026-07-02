/**
 * This is the interface for the expression tree Node.
 * You should not remove it, and you can define some classes to implement it.
 */

abstract class Node {
    public abstract int evaluate();
    // define your fields here
};

class OperandNode extends Node {
    int value;

    OperandNode(int value) {
        this.value = value;
    }

    @Override
    public int evaluate() {
        return this.value;
    }
}

class OperatorNode extends Node {
    char op;
    Node left;
    Node right;

    OperatorNode(Node left, Node right, char op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public int evaluate() {
        int l = this.left.evaluate();
        int r = this.right.evaluate();

        switch (op) {
            case '+':
                return l + r;
            case '-':
                return l - r;
            case '*':
                return l * r;
            default:
                return l / r;
        }
    }
}


/**
 * This is the TreeBuilder class.
 * You can treat it as the driver code that takes the postinfix input 
 * and returns the expression tree representing it as a Node.
 */

class TreeBuilder {
    Node buildTree(String[] postfix) {
        Stack<Node> st = new Stack<>();

        for (String token : postfix) {
            if (isOperator(token)) {
                Node right = st.pop();
                Node left = st.pop();

                st.push(new OperatorNode(left, right, token.charAt(0)));
            } else {
                st.push(new OperandNode(Integer.parseInt(token)));
            }
        }

        return st.pop();
    }

    private boolean isOperator (String s) {
        return s.equals("+") ||
            s.equals("-") ||
            s.equals("*") ||
            s.equals("/");
    }
};


/**
 * Your TreeBuilder object will be instantiated and called as such:
 * TreeBuilder obj = new TreeBuilder();
 * Node expTree = obj.buildTree(postfix);
 * int ans = expTree.evaluate();
 */