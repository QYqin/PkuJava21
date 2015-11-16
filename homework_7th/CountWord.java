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
			//��ͳ���ļ�
			FileWriter wordcountfileout= new FileWriter("C:/Users/qiuyu/Desktop/java/wordcountfile.txt");
			FileReader wordcountfilein= new FileReader("C:/Users/qiuyu/Desktop/java/wordcountfile.txt");
			//����bufferreader����
			BufferedReader FirstRead = new BufferedReader(wordFirst);
			BufferedReader SecondRead = new BufferedReader(wordSecond);
			BufferedReader TotalRead = new BufferedReader(wordtotalin);
			BufferedWriter TotalWriter = new BufferedWriter(wordtotalout);
			BufferedReader IntersectionRead = new BufferedReader(wordIntersectionin);
			BufferedWriter IntersectionWriter = new BufferedWriter(wordIntersectionout);
			BufferedReader countRead = new BufferedReader(wordcountfilein);
			BufferedWriter countWriter = new BufferedWriter(wordcountfileout);
			//���ļ���ȡ�����ݴ��붯̬������
			ArrayList FirstAarry =new ArrayList();
			ArrayList SecondAarry =new ArrayList();
			ArrayList TotalAarry =new ArrayList();
			ArrayList IntersectionAarry =new ArrayList();
			String lineStr=null;
			//������������������ļ�1�Ҳ������ļ�2�еĵ�������
			int count1not2 =0;
			//������������������ļ�2�Ҳ������ļ�1�еĵ�������
			int count2not1=0;
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
								count2not1++;
							}else{
								//����ļ�2�еĵ������ļ�1���Ѿ����ڣ�����뽻���ʻ��
								IntersectionAarry.add(str.trim());
								IntersectionWriter.write(str+" ");
							}
						}
					}
				}
			}
			double proportion2not1 =(double)(Math.round(count2not1*10000/SecondAarry.size())/10000.00);
			double proportion1not2 =(double)(Math.round((TotalAarry.size()-count2not1-IntersectionAarry.size())*10000/FirstAarry.size())/10000.00);
			System.out.println("�ļ�1�еĵ���������"+FirstAarry.size());
			System.out.println("�ļ�2�еĵ���������"+SecondAarry.size());
			System.out.println("�ܹ���������"+TotalAarry.size());
			//System.out.println("count2not1��"+count2not1);
			//System.out.println("count1not2��"+(TotalAarry.size()-count2not1-IntersectionAarry.size()));
			System.out.println("�����ļ�1�Ҳ������ļ�2�ĵ�������ռ�ļ�1���������ı�����"+proportion1not2*100+"%");
			System.out.println("�����ļ�2�Ҳ������ļ�1�ĵ�������ռ�ļ�2���������ı�����"+proportion2not1*100+"%");
			//��ͳ������д���ļ�
			countWriter.write("�ļ�1�еĵ���������"+FirstAarry.size()+"\r\n");
			countWriter.write("�ļ�2�еĵ���������"+SecondAarry.size()+"\r\n");
			countWriter.write("�ܹ���������"+TotalAarry.size()+"\r\n");
			countWriter.write("�����ļ�1�Ҳ������ļ�2�ĵ�������ռ�ļ�1���������ı�����"+proportion1not2*100+"%"+"\r\n");
			countWriter.write("�����ļ�2�Ҳ������ļ�1�ĵ�������ռ�ļ�2���������ı�����"+proportion2not1*100+"%"+"\r\n");
			//�رջ������ļ���ʹ�û�����������д���ļ���
			TotalWriter.close();
			IntersectionWriter.close();
			countWriter.close();
		}catch(Exception e){}
	}
	public static void main(String[] args){
		countword();
	}
}
