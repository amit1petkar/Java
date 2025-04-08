package DSA;

public class Node {
    
    private int value;
    private Node left;
    private Node right;
    
    public Node()
    {
        this.setValue(0);
        this.setLeft(null);
        this.setRight(null);
    }
    
    public Node(int value)
    {
        this.setValue(value);
        this.setLeft(null);
        this.setRight(null);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
    
}

