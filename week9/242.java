import java.util.Arrays;

//public class Solution {
//    public boolean isAnagram(String s, String t) {
//        char[] sc = s.toCharArray();
//        char[] tc = t.toCharArray();
//        if(sc.length==tc.length){
//        	int n = sc.length;
//        	int m =0;
//        	for(int i = 0;i<n;i++){
//        		for(int j = 0;j<n;j++){
//        			if(sc[i]==tc[j])
//        				m = m+1;
//        		}
//        	}
//        	return m==n;
//        }else
//        	return false;
//    }
//}
public class Solution {
	public boolean isAnagram(String s, String t) {
		char[] sc = s.toCharArray();
		char[] tc = t.toCharArray();

		Arrays.sort(tc);
		Arrays.sort(sc);

		String scs = String.valueOf(tc);
		String tcs = String.valueOf(sc);

		return scs.equals(tcs);// 将 char数组 data转换成字符串并判断两个字符串是否相等
	}
}