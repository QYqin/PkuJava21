import java.util.*;
public class PascalTwo {
	public List<Integer> getRow(int rowIndex) {
		ArrayList<Integer> row = new ArrayList<Integer>();
		rowIndex = rowIndex+1;
		for(int i=0;i<rowIndex;i++){
			//ArrayList<Integer> temp = new ArrayList<Integer>();
			for(int j=i-1;j>0;j--){
				row.set(j, row.get(j)+row.get(j-1));
			}
			row.add(1);
		}
		return row;
		//return null;    
    }
	
}
