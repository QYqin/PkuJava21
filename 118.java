import java.util.*;
public class Pascal {
public List<List<Integer>> generate(int numRows) {
	ArrayList<List<Integer>> row = new ArrayList<List<Integer>>();//������ά����
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
				 ��ֹ������½Ǳ�Խ��
					<=�Ƿ�ֹ��С��һ����ӵķ�Χ */
				for(int j=1;j<=i-1;j++){
					rowTemp.add(tem.get(j)+tem.get(j-1));
				}
				//rowTemp.add(1);
			}
			if(i>0){//�����ж�i�Ƿ����0������i=0ʱ����ֽ��[1,1]
			rowTemp.add(1);
			}
			row.add(rowTemp);
			tem=rowTemp;
		}
		
	}
	return row;
        
    }
}
