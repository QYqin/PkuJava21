public class Solution {
    public int myAtoi(String str) {
       //判断字符串是否为空
		if(str==null)
		{
			return 0;
		}
		//去掉首尾的空格
		str=str.trim();
		//设置正负标志位和字符串指针
		int sign=1;
		int index=0;
		//int signbreak=1;
		long num=0;
		if(str.length()==0){
			return 0;
		}
		//判断此整数的正负值，如果出现多个-号或者多个‘+’号，则在循环中自行忽略
		/*for(;index<str.length();index++)
		{
			if(str.charAt(index)=='-'||str.charAt(index)=='+')
			{
				if(str.charAt(index)=='-'&&signbreak==1)
				{
					sign=-1;
					index++;
				}else if(str.charAt(index)=='+'&&signbreak==1)
				{
					sign=1;
					index++;
				}
				signbreak++;
			}else
			{
				break;
			}
		}*/
		//如果符号位，则返回0
		if(str.charAt(index)=='-')
		{
			sign=-1;
			index++;
		}else if(str.charAt(index)=='+')
		{
			sign=1;
			index++;
		}
		//循环加
		for(;index<str.length();index++){
			if(str.charAt(index)<'0'||str.charAt(index)>'9')
			{
				break;
			}
			num=num*10+str.charAt(index)-'0';
			if (num>Integer.MAX_VALUE)
			{
				break;
			}
		}
		//判断是否超出边界
		if(num*sign>Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		else if(num*sign<Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		return (int)num*sign;
        
    }
}