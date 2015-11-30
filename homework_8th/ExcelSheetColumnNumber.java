public class Solution {
    public int titleToNumber(String s) {
      int colum=0;
	  for(int i=0;i<s.length();i++)
	  {
	  colum=colum*26+s.charAt(i)-'A'+1;
	  }
	  return colum;  
    }
}