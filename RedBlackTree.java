public class RedBlackTree {
    private Node root;
    private final Node NIL = new Node(null);

    private static class Node {
        Product product;

        Node left;
        Node right;
        Node parent;
        boolean color;

        public Node(Product product) {
            this.product = product;
        }
    }


    public RedBlackTree() {
        root = NIL;
    }

    public void insert(Product key) {
        Node node = new Node(key);
        node.left = node.right = node.parent = NIL;

        Node current = root;
        Node parent = NIL;

        while (current != NIL) {
            parent = current;
            if (key.getID().compareTo(current.product.getID()) == 0)
                System.out.println("ERROR already exists");
            else if (key.getID().compareTo(current.product.getID()) < 0)
                current = current.left;
            else
                current = current.right;
        }

        node.parent = parent;
        if (parent == NIL)
            root = node;
        else if (key.getID().compareTo(parent.product.getID()) < 0)
            parent.left = node;
        else
            parent.right = node;


        insertBalance(node);
    }


    private void insertBalance(Node n) {
        while (n.parent.color) {
            if (n.parent == n.parent.parent.left) {
                Node grand = n.parent.parent.right;
                if (grand.color) {
                    n.parent.color = false;
                    grand.color = false;
                    n.parent.parent.color = true;
                    n = n.parent.parent;
                } else {
                    if (n == n.parent.right) {
                        n = n.parent;
                        rotateLeft(n);
                    }
                    n.parent.color = false;
                    n.parent.parent.color = true;
                    rotateRight(n.parent.parent);
                }
            } else {
                Node grand = n.parent.parent.left;
                if (grand.color) {
                    n.parent.color = false;
                    grand.color = false;
                    n.parent.parent.color = true;
                    n = n.parent.parent;
                } else {
                    if (n == n.parent.left) {
                        n = n.parent;
                        rotateRight(n);
                    }
                    n.parent.color = false;
                    n.parent.parent.color = true;
                    rotateLeft(n.parent.parent);
                }
            }
        }
        root.color = false;
    }

    public Product search(String id) {
        Node current = root;
        while (current != NIL) {
            if (id.compareTo(current.product.getID()) < 0) {
                current = current.left;
            } else if (id.compareTo(current.product.getID()) > 0) {
                current = current.right;
            } else {
                return current.product;
            }
        }
        return null;
    }

    private void rotateRight(Node n) {
        Node temp = n.left;
        n.left = temp.right;
        if (temp.right != NIL)
            temp.right.parent = n;
        if (n.parent == NIL)
            root = temp;
        else if (n == n.parent.right)
            n.parent.right = temp;
        else
            n.parent.left = temp;
        temp.right = n;
        temp.parent = n;
    }

    private void rotateLeft(Node n) {
        Node temp = n.right;
        n.right = temp.left;
        if (temp.left != NIL)
            temp.left.parent = n;
        if (n.parent == NIL)
            root = temp;
        else if (n == n.parent.left)
            n.parent.left = temp;
        else
            n.parent.right = temp;
        temp.left = n;
        temp.parent = n;
    }
}

