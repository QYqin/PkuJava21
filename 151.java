
public class Solution {
	public String reverseWords(String s) {
		//一旦StringBuffer生成了想要的字符串，就可以调用它的toString()方法将其转换为一个String对象
		
		StringBuffer sb = new StringBuffer();
		String[] ss = s.split(" ");//节约空间或防止数组越界
		//ss = s.split(" ");//以空格为separator分割字符串，将非空格的元素存入数组中
		//for(int i=s.length()-1;i>=0;i--){
		for(int i=ss.length-1;i>=0;i--){
			//if(ss[i].equals(" ")){
			//lenth()用于查看字符串的长度，length用于查看数组的长度，这里不能使用length()的原因是字符串的首尾位置可能包含空格
			if(ss[i].equals("")){
				continue;
			}
			sb=sb.append(ss[i]);
			sb.append(" ");
			//sb.trim();
		}
		//sb = sb.toString();
		//return sb.trim();
		return sb.toString().trim();//trim()的作用是消除首尾两端的空格
    }
}
