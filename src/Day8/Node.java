package Day8;

public class Node {
    private String right;
    private String left;

    public Node(String right, String left){
        this.right=right;
        this.left=left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }
}
