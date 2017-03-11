package lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class outline {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [][] a=new int[][]{
		  new int[]{1, 3, 3},
		  new int[]{2, 4, 4},
		  new int[]{5, 6, 1},
		  new int[]{4, 7, 4},
		  new int[]{1,8,1}
		  
		};
		new Solution1().buildingOutline(a);
	}

}
class Solution1 {
	public void addPos(TreeMap<Integer,List<int[]>> posSorted,int pos,int[] data){
		if(!posSorted.containsKey(pos))
			posSorted.put(pos,new ArrayList<int[]>());
		posSorted.get(pos).add(data);
	}
	public void addSt(TreeMap<Integer,Integer> sorted,int st){
		if(!sorted.containsKey(st))
			sorted.put(st,0);
		sorted.put(st,sorted.get(st)+1);
	}
	public void rmSt(TreeMap<Integer,Integer> sorted,int st){
		if(sorted.get(st)==1)
			sorted.remove(st);
		else
			sorted.put(st,sorted.get(st)-1);
	}
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
    	ArrayList<ArrayList<Integer>> re=new ArrayList<ArrayList<Integer>>();
    	TreeMap<Integer,Integer> sorted=new TreeMap<Integer,Integer>();
    	TreeMap<Integer,List<int[]>> posSorted=new TreeMap<Integer,List<int[]>>();
    	addPos(posSorted,0,new int[]{0,0});
    	int maxPos=0;
    	for(int i=0;i<buildings.length;i++){
    		if(buildings[i][1]>maxPos)
    			maxPos=buildings[i][1];
    		addPos(posSorted,buildings[i][0],new int[]{buildings[i][2],0});
    		addPos(posSorted,buildings[i][1],new int[]{buildings[i][2],1});
    	}
    	addPos(posSorted,maxPos+1,new int[]{0,1});
    	int lastStart=0;
    	int lastHeight=0;
    	for( Iterator<Entry<Integer, List<int[]>>> iterPos = posSorted.entrySet().iterator();iterPos.hasNext();){
    		Entry<Integer, List<int[]>> entry = iterPos.next();
    		for( Iterator<int[]> iter = entry.getValue().iterator() ;iter.hasNext();){
    			int[] pos = iter.next();
    			if(pos[1]==0){
    				addSt(sorted,pos[0]);
    			}else{
    				rmSt(sorted,pos[0]);
    			}
    		}
    		if(sorted.size()==0)
    			break;
    		int currentHeight=sorted.lastKey();
    		if(currentHeight!=lastHeight){
    			if(lastHeight!=0){
    				//System.out.println(""+lastStart+","+entry.getKey()+","+lastHeight);
    				ArrayList<Integer> al=new ArrayList<Integer>();
    				al.add(lastStart);
    				al.add(entry.getKey());
    				al.add(lastHeight);
    				
    				re.add(al);
    			}
    			lastHeight=currentHeight;
    			lastStart=entry.getKey();
    		}
    	}
    	return re;
    }
    
}
class PosComp implements Comparator<int[]>{

	@Override
	public int compare(int[] o1, int[] o2) {
		// TODO Auto-generated method stub
		return o1[0] - o2[0];
	}
	
}
class HeightComp implements Comparator<int[]> {

	@Override
	public int compare(int[] o1, int[] o2) {
		// TODO Auto-generated method stub
		return Integer.valueOf(o1[2]).compareTo(o2[2]);
	}

}