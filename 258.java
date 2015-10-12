public class Solution {
//     public int addDigits(int num) {
// 		String digits = ""+num;
// 		while(digits.length()!=1){
// 		    int b=0;
// 			for (int i = 0; i < digits.length(); i++) {
// 				b+=digits.charAt(i)-48;
// 			}
// 			digits =""+b;
// 		}
//     return digits.charAt(0)-48;
// 	}
    public int addDigits(int num) {
        return num - (num - 1) / 9 * 9;
    }
    }