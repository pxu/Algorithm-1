
public class zigzag {
//把公式求出来就可以了

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s=convert1("PAYPALISHIRING",1);
		System.out.print(s);
	}
	 public static String convert1(String s, int numRows) {
	    	StringBuilder sb=new StringBuilder();
	    	int m1;
	    	if(numRows==1)
	    		m1=1;
	    	else
	    		m1=2*numRows-2;
	        for(int y=0;y<numRows;y++){
        		for(int xi=0;;xi++){
        			int pos=xi*m1+y;
        			if(pos>=s.length())
        				break;
        			sb.append(s.charAt(pos));
        			if(y>0&&y<numRows-1){
        				pos=(xi+1)*m1-y;
        				if(pos>=s.length())
	        				break;
        				sb.append(s.charAt(pos));
        			}
        		}
	        }
	        return sb.toString();
	    }
    public static String convert(String s, int numRows) {
    	StringBuilder sb=new StringBuilder();
    	int m1;
    	if(numRows==1)
    		m1=1;
    	else
    		m1=2*numRows-2;
    	
        for(int y=0;y<numRows;y++){
        	if(y==0){
        		for(int xi=0;;xi++){
        			int pos=xi*m1;
        			if(pos>=s.length())
        				break;
        			sb.append(s.charAt(pos));
        		}
        	}else if(y==numRows-1){
        		for(int xi=0;;xi++){
        			int pos=xi*m1+y;
        			if(pos>=s.length())
        				break;
        			sb.append(s.charAt(pos));
        		}
        	}else{
        		for(int xi=0,zi=0;;zi++,xi++){
        			int posx=xi*m1+y;
        			if(posx>=s.length())
        				break;
        			sb.append(s.charAt(posx));
        			int posz=(zi+1)*m1-y;
        			if(posz>=s.length())
        				break;
        			sb.append(s.charAt(posz));
        		}
        	}
        }
        return sb.toString();
    }

}
