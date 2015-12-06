public class Solution {
    public boolean isAnagram(String s, String t) {
    /*//	if(s.length()==0&&t.length()==0){
//		return true;
//	}
	if(s.length()!=t.length())
	{
		return false;
	}
	for(int i=0;i<t.length()-1;i++){
		if(s.charAt(i)!=t.charAt(i)){
			if(s.charAt(i+1)==t.charAt(i)&&s.charAt(i)==t.charAt(i+1)){
				i++;
			}else
			{
				return false;
			}
		}
	}
	if(s.length()>1){
		if(s.charAt(s.length()-1)!=t.charAt(t.length()-1)){
			 if(s.charAt(s.length()-1)==t.charAt(t.length()-2)&&s.charAt(s.length()-2)==t.charAt(t.length()-1)){
			        return true;
			    }
			return false;
		}
	}else if(s.length()==1){
		//考虑字符串长度只有1的情况，不能进入循环。
		if(s.charAt(0)!=t.charAt(0)){
			return false;
		}
	}
	return true;
	//hashset
	if(s.length()!=t.length())
	{
		return false;
	}
	HashSet<Character> s_set=new HashSet<>();
	for(int i=0;i<s.length();i++){
		s_set.add(s.charAt(i));
	}
	for(int i=0;i<t.length();i++){
		if(!s_set.contains(t.charAt(i))){
			return false;
		}
	}
	return true;*/
	if(s.length()==0&&t.length()==0){
	return true;
	}
	if(s.length()!=t.length())
	{
		return false;
	}
	int[] count=new int[26];
	for(int i=0;i<26;i++){
		count[i]=0;
	}
	for(int i=0;i<s.length();i++){
		count[s.charAt(i)-'a']++;
	}
	for(int i=0;i<t.length();i++){
		count[t.charAt(i)-'a']--;
	}
	for(int i=0;i<26;i++){
		if(count[i]!=0){
			return false;
		};
	}
	return true;
    }
}