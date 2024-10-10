package solutions.pack10_bst;

public class TreeNode_6601606 {
    int data;
    TreeNode_6601606 left, right, parent;

    public TreeNode_6601606(int d) {
        data = d;
    }

    @Override
    public String toString() {
        //no child
        if(left == null && right == null) {
            return "null<-" + data + "->null";
        }

        else if(left != null && right == null) {
            return left.data + "<-" + data + " ->null";
        }

        else if(left == null && right != null) {
            return "null<-" + data + "->" + right.data;
        }

        else {
            return left.data + "<-" + data + "->" + right.data;
        }
    }
}