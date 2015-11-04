public class Solution {
    public int climbStairs(int n) {
        int a=0,b=0,tmp=0;
        if(n==1)return 1;
        else if (n==2) return 2;
        else{
            a=1;b=2;
            while(n>2){
                tmp =a+b;
                a=b;
                b=tmp;
                n--;
            }
        }
        return b;
    }
}