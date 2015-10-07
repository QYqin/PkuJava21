public class Solution {
	public static boolean isHappy(int n) {
	    //     int bai=0,shi=0,yi=0;
	    //     boolean [] nums = new boolean[100000000];
	    //  while(n!=1){
	    //      nums[n]=true;
	    //      int nn=n,a=0,b=0;
	    //      while(nn!=0){
	    //          a=nn%10;
	    //          nn=nn/10;
	    //          b+=a^2;
	    //      }
	    //      n=b;
	    //     if(nums[n]==true)
	    //     break;
	        
	    //  }   
	    //  if(n==1) return true;
	    //  else return false;
	    //应该用hashset存储重复出现的数字
// 	    int interator = 0;
// 	    while(n!=1){
// 	    	HashSet<Integer> panduan =new HashSet<Integer>();
// 	    	if(panduan.contains(n)){
// 		    	return false;
// 		    	}
// 			panduan.add(n);
// 		    while(n>0){
// 		        interator += (n%10)*(n%10);
// 		        n= n/10;
// 		    }
// 		    n = interator;
// 		    interator =0;
// 	    }
// 	    return true;
//用全局变量为什么跑不出来。
		HashSet<Integer> panduan = new HashSet<Integer>();
        while (n != 1) {
            if (panduan.contains(n)) {
                return false;
            }
            panduan.add(n);
            n = pand(n);
        }
        return true;
	}
	 public static int pand(int n) {
	        int interator = 0;
	        while (n>0) {
	            interator += (n % 10) * (n % 10);
	            n /= 10;
	        }
	        return interator;
	    }
	}