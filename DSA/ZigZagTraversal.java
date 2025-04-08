package DSA;

import java.util.*;
public class ZigZagTraversal {
    
    public static void main(String[] args) {
    	BinaryTree bt = new BinaryTree();
//        bt.addNode(new Node(7));
//        bt.addNode(new Node(2));
//        bt.addNode(new Node(5));
//        bt.addNode(new Node(1));
//        bt.addNode(new Node(3));
//        bt.addNode(new Node(13));
//        bt.addNode(new Node(10));
//        bt.addNode(new Node(15));
        
        
//        bt.addNode(new Node(5));
//        bt.addNode(new Node(6));
//        bt.addNode(new Node(11));
//        bt.addNode(new Node(7));
//        bt.addNode(new Node(15));                
//        bt.addNode(new Node(14));
//        bt.addNode(new Node(16));
        
    	bt.addNode(new Node(10));
        bt.addNode(new Node(8));
        bt.addNode(new Node(9));
        bt.addNode(new Node(11));
        bt.addNode(new Node(12));
        
        ZigZagTraverser(bt.getRoot());
        ZigZagTraverser2(bt.getRoot());
    }
    
    
    public static void ZigZagTraverser2(Node root)
    {
                boolean forward = true;
        Queue<Node> q = new LinkedList<Node>();
        Stack<Node> s = new Stack<Node>();
        q.add(root);
        Node EMPTY = new Node(-1);
        q.add(EMPTY);
        while (!q.isEmpty()) {
            Node g = q.remove();
            if (g.getLeft() != null) {
                q.add(g.getLeft());
            }
            if(g.getRight()!=null){
               q.add(g.getRight()); 
            }
            if (g.equals(EMPTY)) {
                if(!q.isEmpty()){
                q.add(EMPTY);}
                forward = !forward;
                while (!s.isEmpty()) {
                    System.out.print(s.pop().getValue() + " ");
                }
                continue;
            }
            if (forward == true) {
                System.out.print(g.getValue() + " ");
            } else {
                s.push(g);
            }
        }
    }
    
    public static void ZigZagTraverser(Node root)
    {
//        ArrayList arr = new ArrayList();
        Stack<Node> stk_LR = new Stack<Node>();
        Stack<Node> stk_RL = new Stack<Node>();
        
        stk_RL.push(root);
        Node temp = null;
        while(!(stk_LR.isEmpty() && stk_RL.isEmpty()))
        {
        	while(!stk_RL.isEmpty())
            {
                temp = stk_RL.pop();
                System.out.print(temp.getValue()+"\t");
//                arr.add(temp.getValue());
//                System.out.println("Popped "+temp.getValue()+" from Stack RL");
                
                if(temp.getLeft()!=null)
                {
                    stk_LR.push(temp.getLeft());
//                    System.out.println("Pushed "+temp.getLeft().getValue()+" into Stack LR");
                }
                
                if(temp.getRight()!=null)
                {
                    stk_LR.push(temp.getRight());                                
//                    System.out.println("Pushed "+temp.getRight().getValue()+" into Stack LR");
                }
            }
        	
            while(!stk_LR.isEmpty())
            {
                temp = stk_LR.pop();
                System.out.print(temp.getValue()+"\t");
//                arr.add(temp.getValue());
//                System.out.println("Popped "+temp.getValue()+" from Stack LR");
                
                if(temp.getRight()!=null)
                {
                    stk_RL.push(temp.getRight());
//                    System.out.println("Pushed "+temp.getRight().getValue()+" into Stack RL");
                }
                
                if(temp.getLeft()!=null)
                {
                    stk_RL.push(temp.getLeft());
//                    System.out.println("Pushed "+temp.getLeft().getValue()+" into Stack RL");
                }
            }
            
            
        }
        
//        System.out.print(arr);
        
    }
    
}
