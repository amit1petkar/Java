//http://www.careercup.com/question?id=4439677
//Count no. of non leaf nodes in a tree

package DSA;

public class CountNonLeafNodes {
	
	public static void main(String []p)
	{
		BinaryTree bt = new BinaryTree();
        bt.addNode(new Node(7));
        bt.addNode(new Node(2));
        bt.addNode(new Node(5));
        bt.addNode(new Node(1));
        bt.addNode(new Node(3));
        bt.addNode(new Node(13));
        bt.addNode(new Node(10));
        bt.addNode(new Node(15));
        
//        System.out.println("In order traversal:");
//        bt.InOrder(bt.getRoot());
        System.out.println(countNonLeafNodes(bt.getRoot()));
	}
	
	public static int countNonLeafNodes(Node node)
	{
		if(node==null || (node.getLeft()==null && node.getRight()==null))
			return 0;
		else
			return 1+ countNonLeafNodes(node.getLeft()) + countNonLeafNodes(node.getRight()); 
	}

}
