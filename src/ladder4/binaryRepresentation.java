package ladder4;

import java.util.ArrayList;
import java.util.zip.Deflater;

/**
 * Created by zhahua on 2/7/17.
 */
public class binaryRepresentation {
    public static void main(String[] args){
        System.out.println(new Solution().binaryRepresentation("0.5"));
        Deflater a;

    }

}

class Solution {
    /**
     *@param n: Given a decimal number that is passed in as a string
     *@return: A string
     */
    public String binaryRepresentation(String n) {
        // write your code here
        String fr=n.substring(n.indexOf(".")+1);
        String num=n.substring(0,n.indexOf("."));
        String frBin=getFrString(fr);
        String numBin=getNumString(num);
        if(frBin==null)
            return "ERROR";
        if(numBin.equals(""))
            numBin="0";
        if(frBin.equals(""))
            return numBin;
        return numBin+"."+frBin;
    }
    private String getNumString(String num){
        ArrayList<Boolean> al=new ArrayList<>();
        long numLong=Long.parseLong(num);
        while(numLong!=0){
            long bin=numLong%2;
            al.add(bin>0);
            numLong=numLong/2;
        }
        StringBuilder sb=new StringBuilder();
        for(int i=al.size()-1;i>=0;i--){
            sb.append(al.get(i)?"1":"0");
        }
        return sb.toString();
    }
    private String getFrString(String fr) {
        long frNum=0;
        int frDL=fr.length();
        for(char ch : fr.toCharArray()){
            int num=Integer.parseInt(String.valueOf(ch));
            frNum=frNum*10+num;
        }
        long base=1;
        for(int i=0;i<frDL;i++)
            base*=10;
        long frNumTmp=frNum;
        StringBuilder sb=new StringBuilder();

        while(frNumTmp!=0){
            if(sb.length()==32)
                return null;
            if(frNumTmp>=base/2)
                sb.append("1");
            else
                sb.append("0");
            frNumTmp=frNumTmp*2%base;
        }
        return sb.toString();
    }
}