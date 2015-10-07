import java.util.*;
public class Pascal {
public List<List<Integer>> generate(int numRows) {
	ArrayList<List<Integer>> row = new ArrayList<List<Integer>>();//创建二维链表
	if(numRows==0){
		return row;
	}
	ArrayList<Integer> tem = new ArrayList<Integer>();
	if(numRows>0){
		for(int i=0;i<numRows;i++){
			ArrayList<Integer> rowTemp = new ArrayList<Integer>();
			rowTemp.add(1);
			if(i>1){
				/*
				 for(int j=0;j<i-1;j++){
					rowTemp.add(tem.get(j)+tem.get(j+1)); */
				/*
				 防止链表的下角标越界
					<=是防止缩小上一行相加的范围 */
				for(int j=1;j<=i-1;j++){
					rowTemp.add(tem.get(j)+tem.get(j-1));
				}
				//rowTemp.add(1);
			}
			if(i>0){//必须判断i是否大于0，否则当i=0时会出现结果[1,1]
			rowTemp.add(1);
			}
			row.add(rowTemp);
			tem=rowTemp;
		}
		
	}
	return row;
        
    }
}
