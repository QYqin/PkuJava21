import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
	public static int romanToint(String s)
	{
    	if(s.length()==0)
		{
			return 0;
		}
		//使用hashmap的方式匹配罗马数字对应的值
		Map<Character,Integer> m =new HashMap<Character,Integer>();
		m.put('I',1);
		m.put('V',5);
		m.put('X',10);
		m.put('L',50);
		m.put('C',100);
		m.put('D',500);
		m.put('M',1000);
		int result=0;
		//如果只有一个罗马字母，直接返回
		if(s.length()==1)
		{
			return m.get(s.charAt(0));
		}
		//通过判断前一个罗马字母和后一个罗马字母对应数字的大小来判断是4还是6
		for(int i=0;i<s.length()-1;i++)
		{
			if(m.get(s.charAt(i))<m.get(s.charAt(i+1))){
				result=result-m.get(s.charAt(i));
//				System.out.println(result);
//				System.out.println(i);
			}else
			{
				result=result+m.get(s.charAt(i));
//				System.out.println(result);
			}
		}
		//输入最后一个罗马数字对应的值
		result=result+m.get(s.charAt(s.length()-1));
		return result;
		
	}
	}