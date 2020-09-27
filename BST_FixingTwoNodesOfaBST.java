/*
Two of the nodes of a Binary Search Tree (BST) are swapped. Fix (or correct) the BST by swapping them back. Do not change the structure of the tree.

Note: It is guaranteed than the given input will form BST, except for 2 nodes that will be wrong.

Example 1:

Input:
       10
     /    \
    5      8
   / \
  2   20
Output: 1

Your Task:
You don't need to take any input. Just complete the function correctBst() that takes root node as parameter. The function should return the root of corrected BST.
BST will then be checked by driver code and 0 or 1 will be printed.
*/

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------

//Initial Template for Java

import java.util.LinkedList; 
import java.util.Queue; 
import java.io.*;
import java.util.*;

class Node
{
    int data;
    Node left;
    Node right;
    Node(int data)
    {
        this.data = data;
        left=null;
        right=null;
    }
}
class pair
{
    int first;
    int second;
    pair(int first , int second)
        {
            this.first = first;
            this.second = second;
        }
}
class GfG {
    
    static Node buildTree(String str)
    {
        
        if(str.length()==0 || str.charAt(0)=='N'){
            return null;
        }
        
        String ip[] = str.split(" ");
        // Create the root of the tree
        Node root = new Node(Integer.parseInt(ip[0]));
        // Push the root to the queue
        
        Queue<Node> queue = new LinkedList<>(); 
        
        queue.add(root);
        // Starting from the second element
        
        int i = 1;
        while(queue.size()>0 && i < ip.length) {
            
            // Get and remove the front of the queue
            Node currNode = queue.peek();
            queue.remove();
                
            // Get the current node's value from the string
            String currVal = ip[i];
                
            // If the left child is not null
            if(!currVal.equals("N")) {
                    
                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.left);
            }
                
            // For the right child
            i++;
            if(i >= ip.length)
                break;
                
            currVal = ip[i];
                
            // If the right child is not null
            if(!currVal.equals("N")) {
                    
                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));
                    
                // Push it to the queue
                queue.add(currNode.right);
            }
            i++;
        }
        
        return root;
    }
    static void printInorder(Node root)
    {
        if(root == null)
            return;
            
        printInorder(root.left);
        System.out.print(root.data+" ");
        
        printInorder(root.right);
    }
    
    public static boolean isBST(Node n, int lower, int upper)
    {
        if(n==null)
            return true;
        if( n.data <= lower || n.data >= upper )
            return false;
        return (  isBST( n.left, lower, n.data )  &&  isBST( n.right, n.data, upper )  );
    }

    public static boolean compare( Node a, Node b, ArrayList<pair> mismatch )
    {
        if( a==null && b==null ) return true;
        if( a==null || b==null ) return false;
        
        if( a.data != b.data )
            {
                pair temp = new pair(a.data,b.data);
                mismatch.add(temp);
            }
            
        
        return ( compare( a.left, b.left, mismatch ) && compare( a.right, b.right, mismatch ) );
    }
    
	public static void main (String[] args) throws IOException
	{
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        
	        int t=Integer.parseInt(br.readLine());
    
	        while(t-- > 0)
	        {
	            String s = br.readLine();
    	    	Node root = buildTree(s);
    	    	Node duplicate = buildTree(s);
    	    	
                Sol g = new Sol();
        		
        		root = g.correctBST(root);
        		
        		 // check 1: is tree now a BST
                if(isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE) == false)
                {
                    System.out.println(0);
                    continue;
                }
                
                // check 2: comparing with duplicate tree
                
               
                ArrayList<pair> mismatch = new ArrayList<pair>();
                // an arraylist to store data of mismatching nodes
                
                if( compare( root, duplicate, mismatch) == false)
                {
                    // false output from this function indicates change in structure of tree
                    System.out.println(0);
                    continue;
                }
                
                // finally, analysing the mismatching nodes
                if( mismatch.size() !=2 || mismatch.get(0).first!=mismatch.get(1).second || mismatch.get(0).second!=mismatch.get(1).first )
                    System.out.println(0);
                else
                    System.out.println(1);
            
                    
            
            }
    }
}

// } Driver Code Ends


//User function Template for Java

class Sol
{
    Node prev = null;
    ArrayList<Integer> inOrder(Node root, ArrayList<Integer> nodes)
    {
        if(root != null)
        {
            inOrder(root.left, nodes);
            
            if(prev != null && prev.data > root.data)
            {
                if(nodes.isEmpty())
                {
                    nodes.add(prev.data);
                    nodes.add(root.data);
                }
                    
                else 
                {
                    nodes.remove(1);
                    nodes.add(root.data);
                }
            }
            prev = root;
                
            inOrder(root.right, nodes);
            
        }
        return nodes;
    }
    
    void inOrder(Node root, int val1, int val2)
    {
        if(root != null)
        {
            inOrder(root.left, val1, val2);
            
            if(root.data == val1)
            {
                root.data = val2;
            }
            
            else if(root.data == val2) 
            {
                root.data = val1;
            }
            
            
            inOrder(root.right, val1, val2);
        }
    }
    
    public Node correctBST(Node root)
    {

        ArrayList<Integer> nodes = new ArrayList<Integer>();
        nodes = inOrder(root, nodes);
        
        if(!nodes.isEmpty())
        {
            inOrder(root, nodes.get(0), nodes.get(1));
        }
        
        return root;
    }
}
