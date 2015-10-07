public class Solution {
//   public static String reverseWords(String s) {
//     	String[] ss =new String[s.length()];
//     	StringBuffer sss =new StringBuffer();
//     	ss=s.split(" ");
//     	HashMap<Integer, String> smap =new HashMap<Integer, String>();
//     	for (int i = 0; i < ss.length; i++) {
// 			smap.put(i, ss[i].toString());
			
// 		}
//     	for (int i = ss.length-1; i >=0; i--) {
//             if(smap.get(i)!=""){
// 			    sss.append(smap.get(i));
// 			    sss.append(" ");
//             }
//             else break;
// // 			if(smap.get(i)!=""){
// // 			sss.append(" ");
// // 			}
// 		}
// 		return sss.toString().trim();
        
//     }
public static String reverseWords(String s) {
//	    	String[] ss =new String[s.length()];
	    	StringBuffer sss =new StringBuffer();
	    	s =s.trim();
	    	String[] ss=s.split(" ");
//	    	HashMap<Integer, String> smap =new HashMap<Integer, String>();
//	    	for (int i = 0; i < ss.length; i++) {
//	    		
//				smap.put(i, ss[i]);
//				
//			}
	    	for (int i = ss.length-1; i >=0; i--) {
	            if(ss[i].equals("")){
	            	continue;
	            }
//	 			if(smap.get(i)!=""){
//	 			sss.append(" ");
//	 			}
	            sss.append(ss[i]).append(" ");
			}
			return sss.toString().trim();
	        
	    }
}