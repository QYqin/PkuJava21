public class Solution {
    public boolean isPowerOfTwo(int n) {
//判断一个数是否是2的幂，判断方法主要依据2的N次幂的特点：仅有首位为1，其余各位都为0.
//通过N&N-1是否等于0来判断是否只有首位为1，其余位均为0
    return n>0&&((n&(n-1))==0);
    }
}