import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class GenerateParentheses {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		generateParenthesis(3);
	}
	public static List<String> generateParenthesis(int n){
		boolean[] stack=new boolean[2*n];
		boolean needReturn=false;
		int top=0;
		int l=0;
		int r=0;
		while(true){
			if(!needReturn){
				if(l==n&&r==n){
					System.out.println(Arrays.toString(stack));
					if(stack[top]){ 
						needReturn=true;
						r--;
					}
					else{
						l--;r++;
						stack[top]=true;
					}
						
				}else if(l>n||r>l){
					needReturn=true;
					if(stack[top])
						r--;
					else
						l--;
				}
				else{
					top++;
					stack[top]=false;
					l++;
				}
			}else{
				if(top==0)
					break;
				else{
					top--;
					if(stack[top]) {
						needReturn=true;
						r--;
					}
					else{
						l--;r++;
						stack[top]=true;
						needReturn=false;
					}
				}
			}
			
		}
		
		return null;
	}
	public static void generateParenthesisReq(List<String> result,LinkedList<Boolean> list,int addIndex,int depth,boolean isFirstPlace ,int max){
		
	}
	public static List<String> generateParenthesis1(int n){
		List<String> result = new ArrayList<String>();
		boolean[] out=new boolean[n*2];
		int top=0;
		generateParenthesisRec(result,out,top,0,0,0,n);
		return result;
	}
	public static void generateParenthesisRec(List<String> result,boolean []out,int top,int stackTop,int lc,int rc, int n){
		if(lc==n&&rc==n){
			StringBuilder sb=new StringBuilder();
			for(boolean v : out){
				System.out.print(v?")":"(");
				sb.append(v?")":"(");
			}
			result.add(sb.toString());
			System.out.println();
		}
		else{
			if(lc<n){
			out[top]=false;
			generateParenthesisRec(result,out,top+1,stackTop+1,lc+1,rc,n);
			}
			if(rc<n&&stackTop>0){
			out[top]=true;
			generateParenthesisRec(result,out,top+1,stackTop-1,lc,rc+1,n);
			}
		}
	}
	
}
