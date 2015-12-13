public class Solution {
    public boolean isAnagram(String s, String t) {
        // 开始完全没有考虑到为空的情况
        // if(s==null||t==null) return false;
        // if (s.length()!=t.length()) return false;
        // boolean flag =false;
        // char[] tt =t.toCharArray();
        // for(int i=0; i<s.length();i++){
        //     char ss =s.charAt(i);
        //     for(int j=0; j<t.length();j++){
        //         if (tt[j] ==ss)
        //         tt[j] =' ';
        //         flag =true;
        //     }
        //     if(!flag) return false;
        //     else flag =false;
        // }
        // return true;
        if(t.length()!=s.length()) return false;
        int[] ch = new int[300];
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            ch[c]++;
        }
        for(int i=0;i<t.length();i++){
            char h = t.charAt(i);
            if(--ch[h]<0) return false;
        }
        return true;
    }
}