package wordtest;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class wordtest {

	public static void main(String[] args) throws FileNotFoundException{
		//读入文件，以空格作为分隔符
		Scanner in1 =new Scanner(new FileInputStream("C:\\Users\\Luojw\\workspace\\Algorithms\\src\\1.txt"),"UTF-8");
		Scanner in2 =new Scanner(new FileInputStream("C:\\Users\\Luojw\\workspace\\Algorithms\\src\\2.txt"),"UTF-8");
		//建立4个hashset存储，这样可以剔除相同项
		HashSet<String> set1 =new HashSet<String>();
		HashSet<String> set2 =new HashSet<String>();
		HashSet<String> bing =new HashSet<String>();
		HashSet<String> jiao =new HashSet<String>();
		//记录文件长度命名
		int wenj1 = 0,wenj2=0,jiaoji=0,bingji=0;
		while (in1.hasNext()){
			String s=in1.next();
			set1.add(s);
		}
		
		in1.close();
		while (in2.hasNext()){
			String ss=in2.next();
			set2.add(ss);
		}
		in2.close();
		Iterator<String> it2 =set2.iterator();
		Iterator<String> it1 =set1.iterator();
		while(it2.hasNext()){
			bing.add(it2.next());
		}
		while(it1.hasNext()){
			
			bing.add(it1.next());
		}
		Iterator<String> it3 =set1.iterator();
		while(it3.hasNext()){
			String j =it3.next();
			if (set2.contains(j)){
				jiao.add(j);
				jiaoji++;
			}
		}
		//求文件1的单词的数目
		for (String string : set1) {
			wenj1++;
		}
		//求文件2的单词的数目
		for (String string : set2) {
			wenj2++;
		}
		//求并集的单词的数目
		for (String string : bing) {
			bingji++;
		}
		//求交集的单词的数目
		for (String str : jiao) {
			System.out.println(str);
		}
		System.out.println("文件1长度为："+wenj1);
		System.out.println("文件2长度为："+wenj2);
		System.out.println("文件交集长度为："+jiaoji);
		System.out.println("文件并集长度为："+bingji);
		//计算百分比
		double a =(double)(wenj1-jiaoji)/wenj1*100;
		double b = (double)(wenj2-jiaoji)/wenj2*100;
		System.out.println("属于A且不属于B的单词占A文件的百分比"+a+"%");
		System.out.println("属于A且不属于B的单词占A文件的百分比"+b+"%");
	}
}
