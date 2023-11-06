import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class MWayTree {
    private MWayTreeNode root;
    private int numberOfChild;

    public MWayTree(int numberOfChild, int rootData) {  //constructor for a MWayTree that has "number of child" children and rootData as its root node
        this.numberOfChild = numberOfChild;
        this.root = new MWayTreeNode(rootData, numberOfChild);
    }

    public void insert(int parentData, int childData) {
        insert(root, parentData, childData);
    }

    private boolean insert(MWayTreeNode node, int parentData, int childData) {
        if (node == null) return false;

        if (node.getData() == parentData) {  //if the first number of the line that is read from a given file matches the parent data.
            for (int i = 0; i < numberOfChild; i++) {
                if (node.getChild(i) == null) { //creating a new node if the node is null using the child and parent data
                    node.setChild(i, new MWayTreeNode(childData, numberOfChild));
                    return true;
                }
            }
            return false;
        } else { //if we don't have a parent node already
            for (int i = 0; i < numberOfChild; i++) {
                if (insert(node.getChild(i), parentData, childData)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(MWayTreeNode node) {
        if (node == null) return 0;
        int maxChildHeight = 0;
        for (int i = 0; i < numberOfChild; i++) {
            int childHeight = getHeight(node.getChild(i));
            maxChildHeight = Math.max(maxChildHeight, childHeight);
        }
        return maxChildHeight + 1;
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
        System.out.println();
    }

    private void preOrderTraversal(MWayTreeNode node) {
        if (node == null) return;
        System.out.print(node.getData() + " ");
        for (int i = 0; i < numberOfChild; i++) {
            preOrderTraversal(node.getChild(i));
        }
    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
        System.out.println();
    }

    private void postOrderTraversal(MWayTreeNode node) {
        if (node == null) return;
        for (int i = numberOfChild - 1; i >= 0; i--) {
            postOrderTraversal(node.getChild(i));
        }
        System.out.print(node.getData() + " ");
    }

    public void inOrderTraversal() {    //unlike in Binary Search Tree, it isn't guaranteed that we will get a sorted list when inOrderTraversal is performed.
        Queue<MWayTreeNode> queue = new LinkedList<>();  //we will use a queue to ensure we visit children of each node
        queue.add(root);

        while (!queue.isEmpty()) {
            MWayTreeNode node = queue.poll();
            if (node != null) {
                System.out.print(node.getData() + " ");
                for (int i = 0; i < numberOfChild; i++) {
                    queue.add(node.getChild(i));
                }
            }
        }

        System.out.println();
    }

    public static MWayTree buildTreeFromFile(String filename) {
        MWayTree tree = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            int m = Integer.parseInt(br.readLine());
            int rootData = Integer.parseInt(br.readLine());
            tree = new MWayTree(m, rootData);

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                int parentData = Integer.parseInt(values[0]);
                for (int i = 1; i < values.length; i++) {
                    int childData = Integer.parseInt(values[i]);
                    tree.insert(parentData, childData);
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tree;
    }

    public static void main(String[] args) {
        MWayTree tree = buildTreeFromFile("src/mWayTree.dat");

        System.out.println("Post-order traversal:");
        tree.postOrderTraversal();

        System.out.println("In-order traversal:");
        tree.inOrderTraversal();

        System.out.println("Pre-order traversal:");
        tree.preOrderTraversal();
        int height = tree.getHeight();
        System.out.println("Height of the tree: " + height);
    }
}