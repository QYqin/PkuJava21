public class Solution {
    public int titleToNumber(String s) {
        int c=0,f=0;
        int[] d= new int[s.length()];
		char[] b= s.toCharArray();
		for(int i=0;i<b.length;i++){
			c=(int)b[i]-64;
			d[i]=c;
		}
		for (int e : d) {
			f*=26;
			f=f+e;
		}
		return f;
//		if(b.length==1){
//		 c =(int) b[0];
//		c=c-64;
//		}
//		else{
//		     c=(int)b[1];
//		    c=c-38;
//		}
    }
}