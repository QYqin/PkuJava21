public class Solution {
    public int lengthOfLastWord(String s) {
        StringBuffer Fs = new StringBuffer(s);
		char[] ArrayS =Fs.reverse().toString().trim().toCharArray();
		//将字符串转换为StringBuffer型，便于反转，最后转换为数组计数。
		//通过字符串的翻转，只需要计算第一个单词的长度就可以返回。
		int length =0;
		for(int i=0;i<ArrayS.length;i++)
		{
//			if (length==0){
//				if(ArrayS[i]==' ')
//				{
//					break;
//				}
//				else 
//				{
//					length++;
//				}
//			}
//			else if(ArrayS[i]==' ')
//				{
//					
//				}
//			}
			if(ArrayS[i]==' ')
			{
				break;
			}
			//如果碰到空格，直接退出
			else
			{
				length++;
			}
			
		}
		return length;
    }
}
	//算法的优点在于通过反转，直接计算第一个单词的长度。
	//时间复杂度小于N，为最后一个单词的长度