import java.util.Arrays;


public class StackLoop {

//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		int[] stack=new int[4];
//		int top=0;
//		boolean isBack=false;
//		int max=3;
//		while(true){
//			if(!isBack){
//				if(stack[top]>max){
//					if(top==0){
//						break;
//					}
//					top--;isBack=true;
//				}else{
//					top++;
//					if(top==stack.length){
//						System.out.println(Arrays.toString( stack));
//						top--;isBack=true;
//					}else{
//						stack[top]=0;
//					}
//				}
//			}else{
//				stack[top]++;
//				isBack=false;
//			}
//		
//		}
//	}
	/**
	 * @param args
	 */
	public static void main1(String[] args) {
		// TODO Auto-generated method stub
		int[] stack=new int[5];
		boolean[] expended=new boolean[5];
		int top=1;
		stack[0]=0;
		expended[0]=false;
		while(top!=0){
		    if(expended[top-1]==false){
		        if(top==stack.length-1)
		        	expended[top]=true;
		        else{
		        	expended[top]=true;
		            top++;
		            
		        }
		    }else{
		        if(stack[top-1]<=9){
		            System.out.println(stack[top-1]);
		            stack[top-1]++;
		        }else{
		            top--;
		        }
		    
			}
		}
	}
	public static void main(String[] args){
		// my thoughts:
//		A(){
//			st0: i=0;
//				while(true){
//			st1:	if(top==stackMax)
//						print    //goto st2
//					else
//						A()//top++, goto st0 
//			st2:	i++
//					if(i==valueMax)
//						return //top-- ;goto st2   if top==-1 then exit
//					goto st1
//				}
//		}
		
		
		int stackMax=5;
		int[] stack=new int[stackMax];
		int valueMax=3;
		int top=0;
		int state=0;
		while(true){
			switch(state){
			case 0:
				stack[top]=0;
				state=1;
				break;
			case 1:
				if(top==stackMax-1){
					System.out.println(Arrays.toString(stack));
					state=2;
				}else{
					top++;
					state=0;
				}
				break;
			case 2:
				stack[top]++;
				if(stack[top]==valueMax){
					top--;
					if(top==-1)
						return;
					else
						state=2;
				}else{
					state=1;
				}
				break;
			}
		}
		
	}
}
