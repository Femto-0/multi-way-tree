public class Node {
    int height=0;
    Node left;
    Node right;
    int data;

    public Node(int data) {
        this.data = data;
    }

    public void insert(int num) {
        if (num <= data) {
            if (left == null) {
                left = new Node(num);
            } else {
                left.insert(num);
            }
        } else {
            if (right == null) {
                right = new Node(num);
            } else {
                right.insert(num);
            }
        }
    }

    public boolean find(int num) {
        if (num == data) {
            return true;
        } else if (num < data) {
            if (left == null) {
                return false;
            } else {
                left.find(num);
            }
        } else {
            if (right == null) {
                return false;
            } else {
                right.find(num);
            }
        }
        return true;
    }
    public int getHeight(Node node){
         if(node==null){
             return -1;
         }

        return Math.max(getHeight(node.left), getHeight(node.right))+1;
    }
}
