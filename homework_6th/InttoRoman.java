public class intToRoman {
	public static String intToRoman(int num)
	{
		//使用匹配的方式，直接输出整数各个位上对应的罗马数字
		String roman[][]={{"","I","II","III","IV","V","VI","VII","VIII","IX"},
				{"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},
		{"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},
		{"","M","MM","MMM"}};
		//int snum[]=integer.to;	
		
		String toroman="";
		int j=0;
		if(num<1||num>3999){
			return null;
		}
		int number[]=new int[4];
		for(int i=0;i<4&&num>0;i++)
		{
			//将整数的各个位拆开放到数组里
			number[i]=num%10;
			num=num/10;
//			System.out.println(num);
		}
		//从最高位开始转换，并以字符串的形式连接在一起
		for(int i=number.length-1;i>=0;i--)
		{
//			System.out.println(i);
//			System.out.println(number[i]);
			toroman=toroman.concat(roman[i][number[i]]);
		}
		/*int i=0;
		while(s>0){
			i=s%1000;	
			if((j=num/1000)>0)
			{
				System.out.println(j);
				i=num/1000;
				toroman=toroman+roman[3][i];
				j++;
				num=num/10;
			}
			if((j=num/100)>0){
				System.out.println(j);
				i=num/100;
				toroman=toroman.concat(roman[2][i]);
				j++;
				num=num/10;
			}
			if((j=num/10)>0){
				i=num/10;
				toroman=toroman.concat(roman[1][i]);
				j++;
				num=num%10;
			}
			if(num>0){
				System.out.println(num);
				toroman=toroman.concat(roman[0][num]);
			}
			
		}*/
		return toroman;
	}
	}