import java.util.ArrayList;
import java.util.List;


public class multiply {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(multiply("12345678","87654321"));
		
	}
	static int order=9;
	static long orderPow=1000000000;
	static String orderFormat="%0"+order+"d";
	public static long GetSubNum(String num,int index){
		int end=num.length()-index*order;
		int start=end-order;
		start=start<0? 0:start;
		String subNumStr=num.substring(start,end);
		return Long.parseLong(subNumStr);
	}
	public static void InplacementPower(List<Long> num,int powerOrderNum){
		int orgSize=num.size();
		for(int i=0;i<powerOrderNum;i++){
			num.add(Long.valueOf(0));
		}
		for(int i=orgSize-1;i>=0;i--){
			num.set(i+powerOrderNum, num.get(i));
		}
		for(int i=0;i<powerOrderNum;i++){
			num.set(i,Long.valueOf(0));
		}
	}
	public static void InplacementAdd(List<Long> num1,List<Long> num2){

		long remain=0;
		int i;
		for(i=0;i<num1.size()||i<num2.size();i++)
		{
			long subNum1=0;
			long subNum2=0;
			if(i<num1.size()){
				subNum1=num1.get(i);
			}
			if(i<num2.size()){
				subNum2=num2.get(i);
			}
			long currNumber=subNum1+subNum2+remain;
			remain=currNumber/orderPow;
			currNumber=currNumber%orderPow;
			if(num1.size()<=i)
				num1.add(i,currNumber);
			else
				num1.set(i, currNumber);
		}
		if(remain!=0)
			num1.add(i,remain);
	}
    public static String multiply(String num1, String num2) {
    	List<Long> listNum1=new ArrayList<Long>();
    	List<Long> listNum2=new ArrayList<Long>();
    	for(int i=0;i*order<num1.length();i++)
    		listNum1.add(GetSubNum(num1,i));
    	for(int j=0;j*order<num2.length();j++)
    		listNum2.add(GetSubNum(num2,j));
    	
    	List<Long> sum=new ArrayList<Long>();
    	List<Long> muListNum=new ArrayList<Long>();
        for(int i=0;i*order<num2.length();i++){
        	for(int j=0;j*order<num1.length();j++){
        		long subNum1=listNum1.get(j);
        		long subNum2=listNum2.get(i);
        		long muNum=subNum1*subNum2;
        		muListNum.clear();
        		muListNum.add(muNum);
        		int orderNum=i+j;
        		InplacementPower(muListNum,orderNum);
        		InplacementAdd(sum,muListNum);
        	}
        }
        
        StringBuilder sb =new StringBuilder();
        for(int j=sum.size()-1;j>=0;j--){
			if(j==sum.size()-1)
				sb.append(sum.get(j).toString());
			else
				sb.append(String.format(orderFormat, sum.get(j)));
		}
		return sb.toString();
    }
}
