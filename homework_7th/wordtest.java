package wordtest;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class wordtest {

	public static void main(String[] args) throws FileNotFoundException{
		//�����ļ����Կո���Ϊ�ָ���
		Scanner in1 =new Scanner(new FileInputStream("C:\\Users\\Luojw\\workspace\\Algorithms\\src\\1.txt"),"UTF-8");
		Scanner in2 =new Scanner(new FileInputStream("C:\\Users\\Luojw\\workspace\\Algorithms\\src\\2.txt"),"UTF-8");
		//����4��hashset�洢�����������޳���ͬ��
		HashSet<String> set1 =new HashSet<String>();
		HashSet<String> set2 =new HashSet<String>();
		HashSet<String> bing =new HashSet<String>();
		HashSet<String> jiao =new HashSet<String>();
		//��¼�ļ���������
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
		//���ļ�1�ĵ��ʵ���Ŀ
		for (String string : set1) {
			wenj1++;
		}
		//���ļ�2�ĵ��ʵ���Ŀ
		for (String string : set2) {
			wenj2++;
		}
		//�󲢼��ĵ��ʵ���Ŀ
		for (String string : bing) {
			bingji++;
		}
		//�󽻼��ĵ��ʵ���Ŀ
		for (String str : jiao) {
			System.out.println(str);
		}
		System.out.println("�ļ�1����Ϊ��"+wenj1);
		System.out.println("�ļ�2����Ϊ��"+wenj2);
		System.out.println("�ļ���������Ϊ��"+jiaoji);
		System.out.println("�ļ���������Ϊ��"+bingji);
		//����ٷֱ�
		double a =(double)(wenj1-jiaoji)/wenj1*100;
		double b = (double)(wenj2-jiaoji)/wenj2*100;
		System.out.println("����A�Ҳ�����B�ĵ���ռA�ļ��İٷֱ�"+a+"%");
		System.out.println("����A�Ҳ�����B�ĵ���ռA�ļ��İٷֱ�"+b+"%");
	}
}
