public class Solution {
    public static void main(String args[]){
    	//int x = 1534236469;
    	System.out.println(reverse(1534236469));
    }
	public static int reverse(int x) {
        int y = 0;
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        if(x==0)
        	return 0;
        else if(x<=min||x>=max)
        	return 0;
        else{
        	int tem=0;
        	//for(;x/10!=0;x/=10)
        	//for(;x!=0;x/=10)
        	while(x!=0){
        		//y=x%10;
        		y=tem*10+x%10;
        		x=x/10;
        		if(y/10!=tem){//判断反序过程中是否溢出，
            		y=0;
            		break;
        		}
        		tem=y;
        	}
        	return  y;
        }
    }
}