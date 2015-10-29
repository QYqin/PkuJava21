public class Solution {
    public List<List<Integer>> generate(int numRows) {
         List<List<Integer>> res= new ArrayList<List<Integer>>();
        //如果numRows值小于0，返回初始值。
        if (numRows<=0)
        return res;
        //设置pre变量，求下一列的各个值。
        List<Integer> pre =new ArrayList<Integer>();
        pre.add(1);
        res.add(pre);
        //设置循环，每次放入一列。
        for(int i=2;i<=numRows;i++)
        {
            List<Integer> cur=new ArrayList<Integer>();
            cur.add(1);
            //每列的首值为1
            //每列的中间值为前一列的对应值之和
            for(int j=0;j<pre.size()-1;j++)
            {
                cur.add(pre.get(j)+pre.get(j+1));
            }
            cur.add(1);
            //列尾为1
            res.add(cur);
            pre=cur;
        }
        return res;
    }
}