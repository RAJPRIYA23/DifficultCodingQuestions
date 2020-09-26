/*
Given a string S consisting of opening and closing parenthesis '(' and ')'. Find length of the longest valid parenthesis substring.

Input:
First line contains number of test cases T.  Each test case have one line string S of character '(' and ')' of length  N.

Constraints:
1 <= T <= 500
1 <= N <= 105

Example:
Input:
2
((()
)()())

Output:
2
4

Explanation:
Testcase 1: Longest valid balanced parantheses is () and its length is 2.
*/

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG 
{
	public static void main (String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader
		(new InputStreamReader(System.in)); 
        int t = Integer.parseInt(br.readLine().trim()); 
        
	    while(t-- > 0)
	    {
	        MyStack obj = new MyStack(); 
	       
	        String str = br.readLine().trim();
	        int cnt = 0, max= 0;
	        
	        Map<Integer, Integer> tm = new TreeMap<Integer, Integer>(); 
	        
	        for(int i=0; i < str.length(); i++)
	        {
	            if(obj.top == -1 && str.charAt(i) == ')')
	            {
	                continue;
	            }
	            
	            else if(obj.top != -1 && str.charAt(i) == ')')
	            {
	                int y = obj.pop();
	                tm.put(y,i);
	                cnt++;
	            }
	                
	            else
	            {
	                obj.push(i);
	            }
	        }
	        
	   
	        int overall_max = 0, curr_last = -1;
	        
	        for(Map.Entry elem : tm.entrySet()) 
	        {
	            
	            if((int)elem.getKey() < curr_last) 
	                continue;
	                
	            int curr_start = (int)elem.getKey();
	            int curr_end = (int)elem.getValue();
	            int curr_len = (int)elem.getValue() - (int)elem.getKey() +1;
	            
	            curr_last = curr_end;
            
	            while(tm.containsKey(curr_last + 1))
	            {
	                curr_start = curr_end +1;
	                curr_end = tm.get(curr_start);
	                curr_len += (curr_end - curr_start)+1;  
	                curr_last = curr_end;
	            }
	            
	            if(curr_len > overall_max)
	                overall_max = curr_len; 
	        }    
	           
	        System.out.println(overall_max);
	    }
	}
}

class MyStack
{
    int top;
	int arr[] = new int[100000];

    MyStack()
	{
		top = -1;
	}
	
	
    void push(int a)
	{
	    top++;
	    arr[top] = a;
	} 
	
    
	int pop()
	{
	    int y = -2;
        if(top != -1)
        {
            y = arr[top];
            top--;
        }
        return y;
    }
    
}
