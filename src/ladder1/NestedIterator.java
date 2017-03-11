package ladder1;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.util.Stack;

public class NestedIterator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<NestedInteger> nestedList = new LinkedList<NestedInteger>(Arrays.asList(new NestedInteger[]{
			new 	NestedInteger(1),
			new 	NestedInteger(new LinkedList<NestedInteger>(Arrays.asList(new NestedInteger[]{
					new 	NestedInteger(2),
					new 	NestedInteger(3)
					
			}))),
			new 	NestedInteger(4),
			new 	NestedInteger(new LinkedList<NestedInteger>(Arrays.asList(new NestedInteger[]{
					new 	NestedInteger(5),
					new 	NestedInteger(6),
					new 	NestedInteger(new LinkedList<NestedInteger>(Arrays.asList(new NestedInteger[]{
							new 	NestedInteger(7),
							new 	NestedInteger(8),
							
							
					}))),
					new 	NestedInteger(9)
			}))),
		}));
		List<Integer> v=new ArrayList<Integer>();
		NestedIteratorImpl i = new NestedIteratorImpl(nestedList);
		i.next();
		i.next();
		i.next();
		i.remove();
		i = new NestedIteratorImpl(nestedList);
		i.next();
		i.next();
		i.next();
		i.next();
		i.next();
		i.next();
		
	}

}


class NestedIteratorImpl implements Iterator<Integer> {

    public NestedIteratorImpl(List<NestedInteger> nestedList) {
        // Initialize your data structure here.
    	this.nestedList=nestedList;
    }
    Integer currentInteger;
    List<NestedInteger> currentParent;
    List<Integer> stack=new LinkedList<Integer>(){{this.add(0);}};
    List<NestedInteger> nestedList;
    
    boolean findCurrent(int depth,List<NestedInteger> curList){
    	while(curList.size()>stack.get(depth)){
    		NestedInteger ni=curList.get(stack.get(depth));
    		if(ni.isInteger()){
    			currentParent=curList;
    			currentInteger=Integer.valueOf(ni.getInteger());
    			System.out.println(currentInteger);
    			return true;
    		}
    		else{
    			if(stack.size()<=depth+1){
    				stack.add(0);
    				stack.set(depth+1,0);
    			}
    			boolean re=findCurrent(depth+1,ni.getList());
    			if(re)
    				return true;
    		}
    		stack.set(depth,stack.get(depth)+1);
    	}
    	stack.remove(stack.size()-1);
    	return false;
    }
    void deleteCurrent(){
    	currentParent.remove((int)(stack.get(stack.size()-1)));
    }
    void moveToNext(){
    	stack.set(stack.size()-1,stack.get(stack.size()-1)+1);
    }
    // @return {int} the next element in the iteration
    @Override
    public Integer next() {
        // Write your code here
    	if(findCurrent(0,nestedList)){
    		moveToNext();
    		return currentInteger;
    	}
    	return null;
    		
    }

    // @return {boolean} true if the iteration has more element or false
    @Override
    public boolean hasNext() {
        // Write your code here
    	return findCurrent(0,nestedList);
    }

    @Override
    public void remove() {
    	if(findCurrent(0,nestedList)){
    		deleteCurrent();
    	}
    }
}

  // This is the interface that allows for creating nested lists.
  // You should not implement it, or speculate about its implementation
  class NestedInteger {
	  Object data=null;
	  public NestedInteger(Object data){
		  this.data=data;
	  }
      // @return true if this NestedInteger holds a single integer,
      // rather than a nested list.
      public boolean isInteger(){
    	  return data instanceof Integer;
      }
 
      // @return the single integer that this NestedInteger holds,
      // if it holds a single integer
      // Return null if this NestedInteger holds a nested list
      public Integer getInteger(){
    	  return (Integer)data;
      }
 
      // @return the nested list that this NestedInteger holds,
      // if it holds a nested list
      // Return null if this NestedInteger holds a single integer
      public List<NestedInteger> getList(){
    	  return (List<NestedInteger>)data;
      }
  }
 
  

class NestedIteratorImpl2 implements Iterator<Integer> {
	
	Deque<NestedInteger> stack=new ArrayDeque<NestedInteger>();
    public NestedIteratorImpl2(List<NestedInteger> nestedList) {
        // Initialize your data structure here.
    	for(NestedInteger ni : nestedList){
    		stack.add(ni);
    	}
    }
   
    // @return {int} the next element in the iteration
    @Override
    public Integer next() {
    	if(!stack.isEmpty())
    		return stack.pop().getInteger();
    	return null;
    }

    // @return {boolean} true if the iteration has more element or false
    @Override
    public boolean hasNext(){
        // Write your code here
    	while(!stack.isEmpty()){
    		if(stack.peek().isInteger())
    			return true;
    		List<NestedInteger> list=stack.pop().getList();
    		for(NestedInteger ni: list){
    			stack.push(ni);
    		}
    	}
    	return false;
    }

    @Override
    public void remove() {
    	
    }
}