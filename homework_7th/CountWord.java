import java.io.*;
import java.util.ArrayList;
public class CountWord {

	public static void countword(){
		try{
			FileReader wordFirst = new FileReader("C:/Users/qiuyu/Desktop/java/wordFirst.txt");
			FileReader wordSecond= new FileReader("C:/Users/qiuyu/Desktop/java/wordSecond.txt");
			//���ܹ������Ĵʻ��
			FileWriter wordtotalout= new FileWriter("C:/Users/qiuyu/Desktop/java/wordtotal.txt");
			FileReader wordtotalin= new FileReader("C:/Users/qiuyu/Desktop/java/wordtotal.txt");
			//�򿪽����ʻ��
			FileWriter wordIntersectionout= new FileWriter("C:/Users/qiuyu/Desktop/java/wordIntersection.txt");
			FileReader wordIntersectionin= new FileReader("C:/Users/qiuyu/Desktop/java/wordIntersection.txt");
			//����bufferreader����
			BufferedReader FirstRead = new BufferedReader(wordFirst);
			BufferedReader SecondRead = new BufferedReader(wordSecond);
			BufferedReader TotalRead = new BufferedReader(wordtotalin);
			BufferedWriter TotalWriter = new BufferedWriter(wordtotalout);
			BufferedReader IntersectionRead = new BufferedReader(wordIntersectionin);
			BufferedWriter IntersectionWriter = new BufferedWriter(wordIntersectionout);
			//���ļ���ȡ�����ݴ��붯̬������
			ArrayList FirstAarry =new ArrayList();
			ArrayList SecondAarry =new ArrayList();
			ArrayList TotalAarry =new ArrayList();
			String lineStr=null;
			//���ļ�1���ļ�2�еĵ��ʶ����붯̬������
			while((lineStr=FirstRead.readLine())!= null){
				String[] splittedStr = lineStr.split("[\\s,.;������ ]");
				for(String str:splittedStr){
					if(!str.trim().equals("")){
						if(!FirstAarry.contains(str.trim()))
						{
							FirstAarry.add(str.trim());
							TotalAarry.add(str.trim());
//							System.out.println(str.trim());
							TotalWriter.write(str+" ");
						}
					}
				}
			}
			//���ļ�2�еĵ��ʶ������飬���ڴ��ж��Ƿ��ڹ�ͬ���ڵ��ļ��С�
			//�������������ӵ���ͬ���ڵ��ļ����������������������ļ��С�
			while((lineStr=SecondRead.readLine())!= null){
				String[] splittedStr = lineStr.split("[\\s,.;������ ]");
				for(String str:splittedStr){
					if(!str.trim().equals("")){
						if(!SecondAarry.contains(str.trim()))
						{
							SecondAarry.add(str.trim());
//							System.out.println(str.trim());
							//�ж��ļ�2�еĵ������ļ�1�Ƿ���ڣ���������ڣ�����ӵ��ܹ��Ĵʻ��
							if(!TotalAarry.contains(str.trim())){
								TotalAarry.add(str.trim());
								TotalWriter.write(str+" ");
							}else{
								//����ļ�2�еĵ������ļ�1���Ѿ����ڣ�����뽻���ʻ��
								IntersectionWriter.write(str+" ");
							}
						}
					}
				}
			}
			System.out.println("!!"+FirstAarry.size());
			System.out.println("!!"+SecondAarry.size());
			//�رջ������ļ���ʹ�û�����������д���ļ���
			TotalWriter.close();
			IntersectionWriter.close();
		}catch(Exception e){}
	}
	public static void main(String[] args){
		countword();
	}
}
