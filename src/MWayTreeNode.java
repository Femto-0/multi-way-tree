class MWayTreeNode {
    private int data;  //Initial root of the MWayTree
    private MWayTreeNode[] children;

    public MWayTreeNode(int data, int numberOfChildNodes) {
        this.data = data;
        this.children = new MWayTreeNode[numberOfChildNodes];
    }

    public int getData() {
        return data;
    }

    public MWayTreeNode getChild(int index) {
        return children[index];
    }

    public void setChild(int index, MWayTreeNode child) {
        children[index] = child;
    }
}
