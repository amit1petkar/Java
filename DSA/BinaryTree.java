package DSA;

import java.util.*;

public class BinaryTree {

    //Insert,Delete,Search time complexity: O(base2log n)
    private Node root;

    public BinaryTree() {
//        root = new Node();
    }

    public BinaryTree(Node root) {
        this.setRoot(root);
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.addNode(new Node(7));
        bt.addNode(new Node(2));
        bt.addNode(new Node(5));
        bt.addNode(new Node(1));
        bt.addNode(new Node(3));
        bt.addNode(new Node(13));
        bt.addNode(new Node(10));
        bt.addNode(new Node(15));

        bt.addNode(new Node(16));
        bt.addNode(new Node(17));

//        bt.addNode(new Node(1));
//        bt.addNode(new Node(2));
//        bt.addNode(new Node(3));
//        bt.addNode(new Node(4));
//        bt.addNode(new Node(5));

//        for(int i=1; i<=11; i++) {
//            bt.addNode(new Node((int)(Math.random()*20)));
//        }

        System.out.println("Count:" + bt.countNodes(bt.getRoot()));
        System.out.println("Height:" + bt.treeHeight(bt.getRoot()));

        Stack<Node> even = new Stack<>();
        Stack<Node> odd = new Stack<>();

        odd.push(bt.getRoot());
//        even.push(bt.getRoot()); changes direction

        System.out.print("ZigZag traversal:");
        bt.zigZagTraverse(even, odd);


        even.clear();
        odd.clear();
        odd.push(bt.getRoot());
        System.out.println("\nDiff level values:" + bt.diffLevelValues(even, odd));

        System.out.println("\nIn order traversal:");
        bt.InOrder(bt.getRoot());
        System.out.println("\nPost order traversal:");
        bt.PostOrder(bt.getRoot());
        System.out.println("\nPre order traversal (DFS):");
        bt.PreOrder(bt.getRoot());
        System.out.println("\nDFS:");
        bt.DFS(bt.getRoot());
        System.out.println("\nBFS:");
        bt.BFS(bt.getRoot());

        //treecolumns
        System.out.println("\nTree columns:");
        SortedMap<Integer, Set<Integer>> columns = new TreeMap<>();
        bt.flagTreeColumns(bt.getRoot(), 0, columns);
        System.out.println(columns);

    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void addNode(Node node) {
        Node temp = getRoot(), prev = getRoot();
        if (getRoot() == null) {
            System.out.println("Tree doesnt exists. Creating new.. Adding root");
            setRoot(node);
        } else {
            while (temp != null) {
                if (node.getValue() <= temp.getValue()) {
                    if (temp.getLeft() != null) {
                        prev = temp.getLeft();
                    }
                    temp = temp.getLeft();

                } else {
                    if (temp.getRight() != null) {
                        prev = temp.getRight();
                    }
                    temp = temp.getRight();

                }
            }

            if (node.getValue() <= prev.getValue())
                prev.setLeft(node);
            else
                prev.setRight(node);
        }
    }

    public int countNodes(Node root) {
        if (root != null) {
            return 1 + countNodes(root.getLeft()) + countNodes(root.getRight());
        } else {
            return 0;
        }
    }

    public int treeHeight(Node root) {
        if (root != null && (root.getLeft() != null || root.getRight() != null)) {
            return 1 + max(
                    (root.getLeft() != null ? treeHeight(root.getLeft()) : 0),
                    (root.getRight() != null ? treeHeight(root.getRight()) : 0)
            );
        } else {
            return 1;
        }
    }

    private int max(int x, int y) {
        return x > y ? x : y;
    }

    //diff of total of even level values and odd level values
    //using zigzag approach as we need to have even odd levels
    public int diffLevelValues(Stack<Node> even, Stack<Node> odd) {
        int value = 0;
        if (even.isEmpty() && odd.isEmpty()) {
            return 0;
        } else {
            if (!odd.isEmpty()) {
                while (!odd.isEmpty()) {
                    Node popped = odd.pop();
                    value = value + popped.getValue();
                    if (popped.getRight() != null) even.push(popped.getRight());
                    if (popped.getLeft() != null) even.push(popped.getLeft());
                }
            } else if (!even.isEmpty()) {
                while (!even.isEmpty()) {
                    Node popped = even.pop();
                    value = value - popped.getValue();
                    if (popped.getLeft() != null) odd.push(popped.getLeft());
                    if (popped.getRight() != null) odd.push(popped.getRight());
                }
            }
        }
        return value + diffLevelValues(even, odd);
    }

    public void zigZagTraverse(Stack<Node> even, Stack<Node> odd) {
        if (even.isEmpty() && odd.isEmpty()) {
            return;
        } else {
            if (!odd.isEmpty()) {
                while (!odd.isEmpty()) {
                    Node popped = odd.pop();
                    System.out.print(" " + popped.getValue());
                    if (popped.getRight() != null) even.push(popped.getRight());
                    if (popped.getLeft() != null) even.push(popped.getLeft());
                }
            } else if (!even.isEmpty()) {
                while (!even.isEmpty()) {
                    Node popped = even.pop();
                    System.out.print(" " + popped.getValue());
                    if (popped.getLeft() != null) odd.push(popped.getLeft());
                    if (popped.getRight() != null) odd.push(popped.getRight());
                }
            }
        }
        zigZagTraverse(even, odd);
    }

    public void InOrder(Node node) //sorting in ascending order
    {
        if (node == null)
            return;
        else {
            InOrder(node.getLeft());
            System.out.print(" " + node.getValue());
            InOrder(node.getRight());
        }
    }

    public void InOrderByFlag(Node node) //sorting in ascending order
    {
        if (node == null)
            return;
        else {
            InOrder(node.getLeft());
            System.out.print(" " + node.getValue());
            InOrder(node.getRight());
        }
    }

    public void PreOrder(Node node) // Similar to DFS for Binary tree. DFS can be implemented using stacks
    {
        if (node == null)
            return;
        else {
            System.out.print(" " + node.getValue());
            PreOrder(node.getLeft());
            PreOrder(node.getRight());
        }
    }

    public void PostOrder(Node node) {
        if (node == null)
            return;
        else {
            PostOrder(node.getLeft());
            PostOrder(node.getRight());
            System.out.print(" " + node.getValue());
        }
    }

//    public void DFS(Node node)
//    {
//        PreOrder(node);
//    }

    public void BFS(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node polledNode = queue.poll();
            System.out.print(" " + polledNode.getValue());
            if (polledNode.getLeft() != null) {
                queue.add(polledNode.getLeft());
            }
            if (polledNode.getRight() != null) {
                queue.add(polledNode.getRight());
            }
        }
    }

    public void DFS(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.add(node);

        while (!stack.isEmpty()) {
            Node poppedNode = stack.pop();
            System.out.print(" " + poppedNode.getValue());
            if (poppedNode.getRight() != null) {
                stack.add(poppedNode.getRight());
            }
            if (poppedNode.getLeft() != null) {
                stack.add(poppedNode.getLeft());
            }
        }
    }

    private void flagTreeColumns(Node root, int index, SortedMap<Integer, Set<Integer>> columns) {
        //flag the nodes recursively
        if (root != null) {
            if (!columns.containsKey(index)) {
                columns.put(index, new LinkedHashSet<>());
            }
            columns.get(index).add(root.getValue());
            flagTreeColumns(root.getLeft(), index - 1, columns);
            flagTreeColumns(root.getRight(), index + 1, columns);
        }
    }
}
