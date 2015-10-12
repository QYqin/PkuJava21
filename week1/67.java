public class Solution {
    public String addBinary(String a, String b) {
		StringBuffer Fa= new StringBuffer(a) ;
		StringBuffer Fb= new StringBuffer(b) ;
		char[] ArrayA1=Fa.reverse().toString().toCharArray();
		//System.out.println(ArrayA1);
		//先将字符串转化为stringbuffer类型，可以实现翻转，便于计算，再转换为数组。
		char[] ArrayB=Fb.reverse().toString().toCharArray();
		//char[] Fa=ArrayA.revers
		int lenmax=Math.max(ArrayA1.length, ArrayB.length);
		int lenmin=Math.min(ArrayA1.length, ArrayB.length);
		//获取相加的两个数组的长度
		//char[] SUMc = new char[Math.max(a.length(),b.length())+2];
		//String s = null;
		char[] SUMc = new char[lenmax+2];
		//定义计算和
		int contA=0;
		int contB=0;
		//定义计算临时变量；
		int carry=0;
		//进位变量
		int val=0;
		//摩变量
		
		//for(int i=0;i<lenmax;i++)
		for(int i=0;i<lenmin;i++)
		{
			contA=ArrayA1[i]-'0';
			contB=ArrayB[i]-'0';
			//减去'0'的ASCII值为数组中的数字大小
			val=(carry+contA+contB)%2;
			carry=(carry+contA+contB)/2;
			SUMc[i]=(char) (val+'0');
			//将int型转换为字符型存入数组
		}
		for(int j=lenmin;j<lenmax;j++)
		{
			//判断是A还是B数组较长，选择较长的数组将剩下的内容按照之前的方法加进位拷贝进结果数组
			if(ArrayA1.length>ArrayB.length)
			{
				contA=ArrayA1[j]-'0';
				val=(carry+contA)%2;
				carry=(carry+contA)/2;
				SUMc[j]=(char) (val+'0');	
			}
			else if(ArrayA1.length<ArrayB.length)
			{
				contB=ArrayB[j]-'0';
				val=(carry+contB)%2;
				carry=(carry+contB)/2;
				SUMc[j]=(char) (val+'0');	
			}
		}
		if (carry>0)
		{
			SUMc[lenmax]=(char) (carry+'0');
		}
		//最后一位有进位，则增加一位
		//System.out.println(SUMc);
		StringBuffer Fc= new StringBuffer(String.copyValueOf(SUMc).trim()) ;
		//System.out.println(SUMc.toString());
		return Fc.reverse().toString();
	    //return a,b;
    }
}
		//此题可以使用StringBuffer，不转换为数组而直接计算，但未有明显的效率提高。
		//算法的时间复杂度为n，即lenmax。