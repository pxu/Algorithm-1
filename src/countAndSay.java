import java.util.ArrayList;


public class countAndSay {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(countAndSay(5));
	}
    public static String countAndSay(int n) {
        ArrayList<Byte> last=new ArrayList<Byte>();
        last.add((byte) 1);
        ArrayList<Byte> cur=new ArrayList<Byte>();
        cur.add((byte) 1);
        for(int i=1;i<=n;i++){
        	int c=0;
        	cur.clear();
        	for(int j=0;j<last.size();j++){
        		c++;
        		if(j+1==last.size()||last.get(j+1)!=last.get(j)){
        			int tc=c/10;
        			int mask=1;
        			while(tc!=0){tc=tc/10;mask*=10;}
        			while(mask!=0){
        				cur.add((byte) (c/mask%10));
        				mask/=10;
        			}
        			cur.add(last.get(j));
        			c=0;
        		}
        	}
//            StringBuilder sb=new StringBuilder();
//            for(Byte b : cur){
//            	sb.append(b.toString());
//            }
//            System.out.println(sb.toString());
        	ArrayList<Byte> tmp=last;
        	last=cur;
        	cur=tmp;
        }
        StringBuilder sb=new StringBuilder();
        for(Byte b : last){
        	sb.append(b.toString());
        }
        return sb.toString();
    }
}
