public class Solution {
    public String addBinary(String a, String b) {
        int size = a.length()>b.length()? a.length():b.length();
        int ai=0,bi=0,rst=0;
        int com = 0;
        // StringBuffer result = new StringBuffer(size+1);
        // for (int i=0; i<size;i++){ 
        //     ai=i<a.length()? a.charAt(i)-'0':0;
        //     bi=i<b.length()? b.charAt(i)-'0':0;
        //     rst = (ai+bi+com)%2;
        //     com=(ai+bi+com)/2;
        //     result.append(rst);
        // }
        StringBuffer aa = new StringBuffer(size+1);
        StringBuffer bb = new StringBuffer(size+1);
        StringBuffer result = new StringBuffer(size+1);
        aa.append(a).reverse();
        bb.append(b).reverse();
        for (int i=0; i<size;i++){ 
            ai=i<aa.length()? aa.charAt(i)-'0':0;
            bi=i<bb.length()? bb.charAt(i)-'0':0;
            rst = (ai+bi+com)%2;
            com=(ai+bi+com)/2;
            result.append(rst);
        }
        if (com == 1){
            result.append(1);
        }
        return result.reverse().toString();
    }
}