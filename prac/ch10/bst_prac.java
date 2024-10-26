class TreeNode {
      int data;
      TreeNode left, right, parent;
      TreeNode(int d) {
        data = d;
        left = null;
        right = null;
        parent = null;
      }
}

class BinaryTree {
    TreeNode root;
    BinaryTree() {
        root = null;
    }

    private TreeNode searchRecursive(int data, TreeNode node) {
        if(node == null) {
            return null;
        } 
        if(data == node.data) {
            return node;
        }
        if (data < node.data) {
            return searchRecursive(data, node.left);
        }
        return searchRecursive(data, node.right);
    } 

    // binary search tree
    public TreeNode search(int data) {
        return searchRecursive(data, root);
    }

    public void insert(int data) {
        if(root == null) {
            root = new TreeNode(data);
            return;
        }
        // current point to root
        TreeNode current = root;
        while(current != null) {
            if(data < current.data) {
                if (current.left != null) {
                    current = current.left;
                } else {
                    current.left = new TreeNode(data);
                    current.left.parent = current;
                    return;
                }
            } else {
                if(current.right != null) {
                    current = current.right;
                } else {
                    current.right = new TreeNode(data);
                    current.right.parent = current;
                    return;
                }
            }
        }
    }

    private TreeNode findMin(TreeNode node) {
        if (node == null) {
            return null;
        }
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }

    private TreeNode deleteRecursion(int data, TreeNode node) {
        if(node == null) {
            return null;
        }
        if(data < node.data) {
            node.left = deleteRecursion(data, node.left);
        } else if (data > node.data) {
            node.right = deleteRecursion(data, node.right);
        } else {
            // case1 : tree has only one node
            if (node.left == null && node.right == null) {
                return null;
            }
            // case2 : one child
            if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } 
            // case3 : two children
            else {
                // TreeNode successorNode = findMax(node.left);
                // that will come from right
                TreeNode successorNode = findMin(node.right);
                node.data = successorNode.data;
                node.right = deleteRecursion(successorNode.data, node.right);
            }
        }
        return node;
    }

    public void delete(int data) {
        root = deleteRecursion(data, root);
    }

    // left root right
    public void inorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        System.out.print(node.data + " "); // root
        inorderTraversal(node.right);
    }

    // root left right
    public void preOrderTraversal(TreeNode node) {
        if(node == null) {
            return;
        }
        System.out.print(node.data + " ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    // left right root
    public void postOrderTraversal(TreeNode node) {
        if(node == null) {
            return;
        }
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.data + " ");
    }

}

public class bst_prac {
    public static void main(String args[]) {
        BinaryTree bst = new BinaryTree();

        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);

        System.out.println("Inorder traversal of the given tree:");
        bst.inorderTraversal(bst.root);
        System.out.println();

        System.out.println("Delete 20");
        bst.delete(20);
        bst.inorderTraversal(bst.root);
        System.out.println();

        System.out.println("Delete 30");
        bst.delete(30);
        bst.inorderTraversal(bst.root);
        System.out.println();

        System.out.println("Delete 50");
        bst.delete(50);
        bst.inorderTraversal(bst.root);
        System.out.println();
        bst.preOrderTraversal(bst.root);
        System.out.println();
        bst.postOrderTraversal(bst.root);
    }
}