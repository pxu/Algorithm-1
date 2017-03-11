
public class maxArea {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print( maxArea(new int[]{5,4,7,60,10,13,9,70,6,6}));
	}
	public static int maxArea(int[] height){
		int lMax=height[0];
		int lMaxIndex=0;
		int rMax=height[height.length-1];
		int rMaxIndex=height.length-1;
		int l=0;
		int r=height.length-1;
		int maxArea=getMaxArea(lMaxIndex,rMaxIndex,height);
		int maxAreaLIndex=lMax;
		int maxAreaRIndex=rMax;
		
		outer: while(true){
			if(lMax<rMax){
				while(true){
					if(l>=rMaxIndex)
						break outer;
					if(height[l]>lMax){
						lMax=height[l];
						lMaxIndex=l;
						int area=getMaxArea(lMaxIndex,rMaxIndex,height);
						if(area>maxArea){
							maxArea=area;
							maxAreaLIndex=lMaxIndex;
							maxAreaRIndex=rMaxIndex;
						}
						l++;
						break;
					}
					l++;
				}
			}else{
				while(true){
					if(r<=lMaxIndex)
						break outer;
					if(height[r]>rMax){
						rMax=height[r];
						rMaxIndex=r;
						int area=getMaxArea(lMaxIndex,rMaxIndex,height);
						if(area>maxArea){
							maxArea=area;
							maxAreaLIndex=lMaxIndex;
							maxAreaRIndex=rMaxIndex;
						}
						r--;
						break;
					}
					r--;
				}
			}
		}
		return maxArea;
	}
	public static int getMaxArea(int l,int r,int[] height){
		int min=height[l]>height[r]? height[r]:height[l];
		return (r-l)*min;
	}
//    public static int maxArea(int[] height) {
//    	
//    	int [][]lHeight=new int[height.length][2];
//    	int [][]rHeight=new int[height.length][2];
//    	
//    	int lMax=0;
//    	int lMaxIndex=0;
//    	int rMax=0;
//    	int rMaxIndex=height.length-1;
//    	int ii=0,jj=0;
//    	for(int i=0,j=height.length-1;;){
//        	if(height[i]>lMax){
//        		lMax=height[i];
//        		lMaxIndex=i;
//        		lHeight[ii][0]=lMax;
//        		lHeight[ii++][1]=lMaxIndex;
//        	}
//        	i++;
//        	if(i>=rMaxIndex)
//        		break;
//        	if(height[j]>rMax){
//        		rMax=height[j];
//        		rMaxIndex=j;
//        		rHeight[jj][0]=rMax;
//        		rHeight[jj++][1]=rMaxIndex;
//        	}
//        	j--;
//        	if(j<=lMaxIndex)
//        		break;
//        }
//    	int rHeightBase=0;
//    	int areaMax=0;
//    	int ll=0,rr=0;
//    	for(int i=0;i<ii;i++){
//    		for(int j=rHeightBase;j<jj;j++){
//    			int min=lHeight[i][0]>rHeight[j][0]? rHeight[j][0]:lHeight[i][0];
//    			int area=min*(rHeight[i][1]-lHeight[j][1]);
//    			if(area>areaMax){
//    				areaMax=area;
//    				ll=i;rr=j;
//    			}
//    			if(lHeight[i][0]>=rHeight[j][0])
//    				rHeightBase++;
//    		}
//    	}
//    	return areaMax;
//    }

}
