public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
      //String a=Integer.toBinaryString(n);
		if(n==0)
		{
			return 0;
		}
		//如果是0，则二进制数的1个数为0
		int count=1;
		//对于n-1，如果和N进行与操作，结果将N之前的二进制值数去掉一个1
		//以此类推，每次都减1，那么有几次操作，就会有几个1，计数操作的次数，可求得N中二进制1的个数
		while(((n&(n-1))!=0))
		{
			n&=n-1;
			count++;
		}
		return count;
    }
}
		//时间复杂度<=n;
		//此种算法在N中1的个数比较少时非常高效，若1的个数非常多，建议采用以下方式
		/*m1  = 0x5555555555555555; //binary: 0101...  
		m2  = 0x3333333333333333; //binary: 00110011..  
		m4  = 0x0f0f0f0f0f0f0f0f; //binary:  4 zeros,  4 ones ...  
		m8  = 0x00ff00ff00ff00ff; //binary:  8 zeros,  8 ones ...  
		m16 = 0x0000ffff0000ffff; //binary: 16 zeros, 16 ones ...  
		m32 = 0x00000000ffffffff; //binary: 32 zeros, 32 ones  
		x = (x & m1 ) + ((x >>  1) & m1 ); //put count of each  2 bits into those  2 bits   
		x = (x & m2 ) + ((x >>  2) & m2 ); //put count of each  4 bits into those  4 bits   
		x = (x & m4 ) + ((x >>  4) & m4 ); //put count of each  8 bits into those  8 bits   
		x = (x & m8 ) + ((x >>  8) & m8 ); //put count of each 16 bits into those 16 bits   
		x = (x & m16) + ((x >> 16) & m16); //put count of each 32 bits into those 32 bits   
		x = (x & m32) + ((x >> 32) & m32); //put count of each 64 bits into those 64 bits  
		算法使用了分治的思想，每步将问题划分成子问题，然后合并来减小问题的规模，求解问题的过程像是一棵倒置的二叉树。
		先将n位的二进制相邻的两位两两分为一组，并巧妙的利用移位和掩码来使其利用自身来表示所得到的和，这样从宏观上来看，
		问题就被简化成规模为n/2bit(这里的bit其实已经是虚指了，其实理解为unit更好)的问题求解了，同样的，继续两两划分成一组分治求解。
		经过lg（n）步，得到最终的解。由以上分析可见，算法的复杂度为O（lgn）。
		第二步，可对上述移位操作进行优化
		x -= (x >> 1) & m1;             //put count of each 2 bits into those 2 bits  
		x = (x & m2) + ((x >> 2) & m2); //put count of each 4 bits into those 4 bits   
		x = (x + (x >> 4)) & m4;        //put count of each 8 bits into those 8 bits   
		x += x >>  8;  //put count of each 16 bits into their lowest 8 bits  
		x += x >> 16;  //put count of each 32 bits into their lowest 8 bits  
		x += x >> 32;  //put count of each 64 bits into their lowest 8 bits  
		return x & 0x7f;  
		第一步基于了这样一个事实:ab-0a得到的值为ab中1的个数。简单证明：若a为0，那么0a=0,减0无变化，那么b就是结果。
		若a位1，那么只有两种情况，10-01 = 01, 11-01 = 10.都符合上述事实。
		这样x -= (x >> 1) & m1和 x = (x & m1 ) + ((x >> 1) & m1 )的结果相同，却节省了1个操作。
		*/
		