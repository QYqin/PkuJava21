public class Solution {
    public int trailingZeroes(int n) {
        int num=0;
        if (n<5)
        return 0;
        else{
        while(n>0){
            num +=n/5;
            n=n/5;
        }return num;
        }
    }
}