public class MWayTree {


    public static void main(String[] args) {
        Node node = new Node(10);
        node.insert(1);
        node.insert(2);
        node.insert(3);
        node.insert(4);
        node.insert(5);
        node.insert(6);
        node.insert(11);
        node.insert(12);
System.out.println(node.getHeight());
    }
}

