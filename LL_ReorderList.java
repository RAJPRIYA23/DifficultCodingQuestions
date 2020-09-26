/*
Given a singly linked list: A0→A1→…→An-1→An, reorder it to: A0→An→A1→An-1→A2→An-2→…
For example: Given 1->2->3->4->5 its reorder is 1->5->2->4->3.

Note: It is recommended do this in-place without altering the nodes' values.

Example:

Input:
LinkedList: 1->2->3
Output: 1 3 2

Your Task:
The task is to complete the function reorderList() which should reorder the list as required. The reorder list is automatically printed by the driver's code.

Note: Try to solve without using any auxilliary space.
*/

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------

import java.util.*;

class Node {
    int data;
    Node next;
    Node(int d) {
        data = d;
        next = null;
    }
}

  public class ReorderList {
    Node head; // head of lisl
    Node last; // last of linked list

    /* Linked list Node*/

    /* Utility functions */

    /* Inserts a new Node at front of the list. */
    public void addToTheLast(Node node) {

        if (head == null) {
            head = node;
            last = head;
        } else {
            // Node temp = head;
            // while (temp.next != null) temp = temp.next;

            last.next = node;
            last = last.next;
        }
    }
    /* Function to print linked list */
    void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    /* Drier program to test above functions */
    public static void main(String args[]) {

        /* Constructed Linked List is 1->2->3->4->5->6->
           7->8->8->9->null */
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0) {
            int n = sc.nextInt();
            ReorderList llist = new ReorderList();

            int a1 = sc.nextInt();
            Node head = new Node(a1);
            llist.addToTheLast(head);
            for (int i = 1; i < n; i++) {
                int a = sc.nextInt();
                llist.addToTheLast(new Node(a));
            }

            llist.head = new gfg().reorderlist(llist.head);

            llist.printList();

            t--;
        }
    }
}// } Driver Code Ends


/* Following is the Linked list node structure */

/*class Node
{
    int data;
    Node next;
    Node(int d) {
        data = d;
        next = null;
    }
}*/

class gfg 
{
    Node reorderlist(Node head)
    {
        Node slow = head;
        Node fast = head.next;
        
        while(fast !=null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        Node node1 = head;
        Node node2 = slow.next;
        slow.next = null;
        
        // after above loc :- 1->2->3    4->5
        
        Node dummy_head = new Node(-1);
        
        dummy_head.next = node2;
        
        reverse(dummy_head); //5->4
        
        node2 = dummy_head.next;
        
        dummy_head.next = null;
        
        Node curr = dummy_head;
        
        while(node1 != null || node2!= null) // combining lists
        {
            
            if(node1 != null)
            {
                curr.next =  node1;
                curr = node1;
                node1 = node1.next;
            }
                
            if(node2 != null)
            {
                curr.next = node2;
                curr = node2;
                node2 = node2.next;
            }
            
        }
        
        dummy_head = dummy_head.next;
        return dummy_head;
    }
    
    
    static void reverse(Node dummy_head)
    {
        
        Node curr=dummy_head.next, prev=null, next;
        
        while(curr != null)
        {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        dummy_head.next = prev;
    }
}


