class Node {
    int data;
    Node parent;
    Node left;
    Node right;

    Node(int num) {
        data = num;
        parent = left = right = null;
    }
}

class BST {
    Node root = null;

    private void insert(int num, Node p) {
        if (p == null) {
            root = new Node(num);
            return;
        }
        if (num < p.data) {
            if (p.left == null) {
                p.left = new Node(num);
                p.left.parent = p;
            } else {
                insert(num, p.left);
            }
        } else {
            if (p.right == null) {
                p.right = new Node(num);
                p.right.parent = p;
            } else {
                insert(num, p.right);
            }
        }
    }

    public int height(Node node) {
        if(node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public void insert(int num) {
        insert(num, root);
    }

    public Node search(int num, Node node) {
        if (node == null) {
            return null;
        }
        if (num == node.data) {
            return node;
        }
        if (num < node.data) {
            return search(num, node.left);
        }
        return search(num, node.right);
    }

    private Node findMax(Node node) {
        if (node == null || node.right == null) {
            return node;
        }
        return findMax(node.right);
    }

    private void delete(int num, Node p) {
        if (p == null) {
            return;
        }
        if (num < p.data) {
            delete(num, p.left);
        } else if (num > p.data) {
            delete(num, p.right);
        } else {
            if (p.left == null || p.right == null) {
                Node q = (p.left == null) ? p.right : p.left;
                if (p == root) {
                    root = q;
                } else if (p.parent.left == p) {
                    p.parent.left = q;
                } else {
                    p.parent.right = q;
                }
                if (q != null) {
                    q.parent = p.parent;
                }
            } else {
                Node q = findMax(p.left);
                p.data = q.data;
                delete(q.data, q);
            }
        }
    }

    public void delete(int num) {
        delete(num, root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void printPreOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

    public void printInOrder(Node node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        System.out.print(node.data + " ");
        printInOrder(node.right);
    }

    public void printPostOrder(Node node) {
        if (node == null) {
            return;
        }
        printPostOrder(node.left);
        printPostOrder(node.right);
        System.out.print(node.data + " ");
    }
}

public class TreePrac {
    public static void main(String[] args) {
        BST tree = new BST();

        // Insertion
        int[] nums = {10, 5, 15, 3, 7, 13, 17};
        for (int num : nums) {
            tree.insert(num);
        }

        System.out.println("In-order traversal:");
        tree.printInOrder(tree.root); // Expected order: 3 5 7 10 13 15 17

        System.out.println("\n\nPre-order traversal:");
        tree.printPreOrder(tree.root); // Expected order: 10 5 3 7 15 13 17

        System.out.println("\n\nPost-order traversal:");
        tree.printPostOrder(tree.root); // Expected order: 3 7 5 13 17 15 10

        // Searching for an element
        int searchNum = 7;
        Node searchResult = tree.search(searchNum, tree.root);
        System.out.println("\n\nSearch result for " + searchNum + ": " + (searchResult != null ? "Found" : "Not Found"));

        // Deletion
        int deleteNum = 10;
        System.out.println("\nDeleting " + deleteNum);
        tree.delete(deleteNum);

        System.out.println("In-order traversal after deletion:");
        tree.printInOrder(tree.root); // Expected: 3 5 7 13 15 17
    }
}
