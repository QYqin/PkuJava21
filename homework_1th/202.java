public class Solution {
    public boolean isHappy(int n) {
        int SumN=0;
		//记录平方和
    	HashSet<Integer> Hash=new HashSet<Integer>();
		//定义hash表，利用hash表的快速匹配判断是否进入无限循环，若有相同项，则退出循环。
    	if(n==0)
    	{
    		//判断是否为0，若为0弹出
    		return false;
    	}
    	else if(n==1)
    	{
    		//判断是否为1，若为1弹出
    		return true;
    	}
    	else{
				//通过Hash表查询，看是否存在相同的数，如果出现之前有过的数进行HapperNumber的验证，说明此验证进入循环，不是HapperNumber。
    		while(!Hash.contains(n))
        	{
    			//将此次验证的N加入hash表中，便于验证是否进入无限循环。
    			Hash.add(n);
        		while(n!=0)
        		{
            		SumN+=(n%10)*(n%10);
            		n=n/10;
            	}
        		if(SumN==1)
        		{
        			return true;
        		}
        		else
        		{
        			n=SumN;
        			SumN=0;
        		}
        		
        	}
    		
    	}
//    	while(!Hash.contains(n))
//    	{
//    		while(n!=0)
//    		{
//        		SumN+=n%10*n%10;
//        		n=n/10;
//        	}
//    	}
		return false;
    }
}
	//算法利用Hash表，提供查找的效率