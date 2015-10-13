public class Solution {
    public boolean isUgly(int num) {
		if(num<=0){
			return false;
		}
		while(num%2==0){//判断num能否被2整除，取整除后的商继续判断，如果能一直被整除，则判断结果是否为1,否则判断能否被3整除
			num=num/2;
		}
		while(num%3==0){//判断num能否被3整除，取整除后的商继续判断，如果能一直被整除，则判断结果是否为1，否则判断能否被5整除
			num=num/3;
		}
		while(num%5==0){//判断num能否被5整除，取整除后的商继续判断，如果能一直被整除，则判断结果是否为1，否则判断商是否为1 
			num=num/5;
		}
		if(num==1){//商为1表示num只能被2、3、5整除
			return true;
		}
		else{
			return false;
		}
    	
        
    }
}