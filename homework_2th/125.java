public class Solution {
    
    // public boolean isPalindrome(String s) {
    //     // if(s == null) return false;
	   //    // String ss = "";
	   //    // Pattern pattern = Pattern.compile("[^a-zA-Z_0-9]");
	   //    // Matcher matcher = pattern.matcher(s);
	   //    // ss =matcher.replaceAll("");
	        
	   //    // char[] chars = ss.toLowerCase().toCharArray();
	   //    // int i = 0, j = chars.length - 1;
	   //    // while (i<j){
	   //    // 	if(chars[i] != chars[j]) return false;
	   //    // 	i++;
	   //    // 	j--;
	   //    // }
	        
	   //    // return true;
	   // }
	   //无法使用正则表达式求解问题，尴尬！！
	    
    boolean re(char[] chars, int i){
        return !(('A' <= chars[i] && chars[i]<= 'Z') 
                || ('a' <= chars[i] && chars[i]<= 'z') 
                || ('0' <= chars[i] && chars[i]<= '9'));
    }
    
    public boolean isPalindrome(String s) {
        if(s == null) return false;
        
        char[] chars = s.toLowerCase().toCharArray();
        int i = 0, j = chars.length - 1;
        
        while(i < j){
            
            // 自己定义一个正则表达式规律吧
            while( (i < j) && re(chars, i)) i++;
            while( (i < j) && re(chars, j)) j--;
            
            if(chars[i] != chars[j]) return false;
            
            i++;
            j--;
        }
        
        return true;
    }
}