package DSA;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
//http://www.careercup.com/question?id=3403685
// print the vertical columns of binary tree

public class TreesColumnElements {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BinaryTree bt = new BinaryTree();
		//		bt.addNode(new Node(7));
		//		bt.addNode(new Node(2));
		//		bt.addNode(new Node(5));
		//		bt.addNode(new Node(1));
		//		bt.addNode(new Node(3));
		//		bt.addNode(new Node(13));
		//		bt.addNode(new Node(10));
		//		bt.addNode(new Node(15));

		bt.addNode(new Node(5));
		bt.addNode(new Node(6));
		bt.addNode(new Node(11));
		bt.addNode(new Node(7));
		bt.addNode(new Node(15));                
		bt.addNode(new Node(14));
		bt.addNode(new Node(16));

		printTreesColumns(bt.getRoot());

	}


	public static void printTreesColumns(Node root)
	{
		ConcurrentHashMap<Integer, LinkedHashSet<Node>> map = new ConcurrentHashMap<Integer, LinkedHashSet<Node>>(); // concurrent collection because we are adding new keys during iterating the same; else would throw ConcurrentModificationException
		//This map consists of Integer as key which would determine the column of elements; linkedhashset would represent set of elements in that column
		LinkedHashSet<Node> set = new LinkedHashSet<Node>();
		set.add(root);
		map.put(1, set);

		LinkedHashSet<Node> tmpSet = null;
		boolean flag = false;
		int temp = 0;

		loop:
			while(true)
			{
				Set<Integer> keys = map.keySet(); //get keys of map

				for(Integer i: keys) //iterating keys of map
				{
					for(Node n : map.get(i)) //iterating set of nodes in that key
					{
						if(n.getLeft()!=null) //if left element available
						{
							temp = i-1;
							if((tmpSet=map.get(temp))==null) //if key is not available in map
							{
								tmpSet = new LinkedHashSet<Node>();
								flag = tmpSet.add(n.getLeft()); // creating new set and add element
								map.put(temp, tmpSet); //add new set in new key
							}
							else
							{
								flag = tmpSet.add(n.getLeft()); //else add element in set in given key in a map
							}
						}

						if(n.getRight()!=null) //if right element is available
						{
							temp = i+1;
							if((tmpSet=map.get(temp))==null)
							{
								tmpSet = new LinkedHashSet<Node>();
								flag = tmpSet.add(n.getRight());
								map.put(temp, tmpSet);
							}
							else
							{
								flag = tmpSet.add(n.getRight());
							}
						}
					}
				}

				if(flag) //if element was added in map's set, continue over. Else break out.
					continue loop;
				else
					break;
			}

		System.out.println("---------------------");
		Set<Integer> keys = map.keySet();
		for(Integer i: keys)
		{
			System.out.print("KEY "+i+":");
			tmpSet = map.get(i);
			for(Node n: tmpSet)
				System.out.print(n.getValue()+" ");

			System.out.println();
		}
	}

	//inspired code
	//	Tree* Calculatevertical(Tree *root, int index) 
	//	{ 
	//	if(root != NULL) 
	//	{ 
	//	root->verticalIndex = index; 
	//
	//	Calculatevertical(root->left, index-1);	
	//	Calculatevertical(root->right,index+1); 
	//
	//	return root; 
	//	} 
	//	}
}
