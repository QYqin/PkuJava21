
public class Solution {
	public String reverseWords(String s) {
		//һ��StringBuffer��������Ҫ���ַ������Ϳ��Ե�������toString()��������ת��Ϊһ��String����
		
		StringBuffer sb = new StringBuffer();
		String[] ss = s.split(" ");//��Լ�ռ���ֹ����Խ��
		//ss = s.split(" ");//�Կո�Ϊseparator�ָ��ַ��������ǿո��Ԫ�ش���������
		//for(int i=s.length()-1;i>=0;i--){
		for(int i=ss.length-1;i>=0;i--){
			//if(ss[i].equals(" ")){
			//lenth()���ڲ鿴�ַ����ĳ��ȣ�length���ڲ鿴����ĳ��ȣ����ﲻ��ʹ��length()��ԭ�����ַ�������βλ�ÿ��ܰ����ո�
			if(ss[i].equals("")){
				continue;
			}
			sb=sb.append(ss[i]);
			sb.append(" ");
			//sb.trim();
		}
		//sb = sb.toString();
		//return sb.trim();
		return sb.toString().trim();//trim()��������������β���˵Ŀո�
    }
}
