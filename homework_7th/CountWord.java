import java.io.*;
import java.util.ArrayList;
public class CountWord {

	public static void countword(){
		try{
			FileReader wordFirst = new FileReader("C:/Users/qiuyu/Desktop/java/wordFirst.txt");
			FileReader wordSecond= new FileReader("C:/Users/qiuyu/Desktop/java/wordSecond.txt");
			//打开总共包含的词汇表
			FileWriter wordtotalout= new FileWriter("C:/Users/qiuyu/Desktop/java/wordtotal.txt");
			FileReader wordtotalin= new FileReader("C:/Users/qiuyu/Desktop/java/wordtotal.txt");
			//打开交集词汇表
			FileWriter wordIntersectionout= new FileWriter("C:/Users/qiuyu/Desktop/java/wordIntersection.txt");
			FileReader wordIntersectionin= new FileReader("C:/Users/qiuyu/Desktop/java/wordIntersection.txt");
			//打开统计文件
			FileWriter wordcountfileout= new FileWriter("C:/Users/qiuyu/Desktop/java/wordcountfile.txt");
			FileReader wordcountfilein= new FileReader("C:/Users/qiuyu/Desktop/java/wordcountfile.txt");
			//定义bufferreader方法
			BufferedReader FirstRead = new BufferedReader(wordFirst);
			BufferedReader SecondRead = new BufferedReader(wordSecond);
			BufferedReader TotalRead = new BufferedReader(wordtotalin);
			BufferedWriter TotalWriter = new BufferedWriter(wordtotalout);
			BufferedReader IntersectionRead = new BufferedReader(wordIntersectionin);
			BufferedWriter IntersectionWriter = new BufferedWriter(wordIntersectionout);
			BufferedReader countRead = new BufferedReader(wordcountfilein);
			BufferedWriter countWriter = new BufferedWriter(wordcountfileout);
			//将文件读取的内容存入动态数组中
			ArrayList FirstAarry =new ArrayList();
			ArrayList SecondAarry =new ArrayList();
			ArrayList TotalAarry =new ArrayList();
			ArrayList IntersectionAarry =new ArrayList();
			String lineStr=null;
			//定义变量：单词属于文件1且不属于文件2中的单词数量
			int count1not2 =0;
			//定义变量：单词属于文件2且不属于文件1中的单词数量
			int count2not1=0;
			//将文件1和文件2中的单词都存入动态数组中
			while((lineStr=FirstRead.readLine())!= null){
				String[] splittedStr = lineStr.split("[\\s,.;。，； ]");
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
			//将文件2中的单词读入数组，并在此判断是否在共同存在的文件中。
			//如果不存在则添加到共同存在的文件，如果存在则添加至交集文件中。
			while((lineStr=SecondRead.readLine())!= null){
				String[] splittedStr = lineStr.split("[\\s,.;。，； ]");
				for(String str:splittedStr){
					if(!str.trim().equals("")){
						if(!SecondAarry.contains(str.trim()))
						{
							SecondAarry.add(str.trim());
//							System.out.println(str.trim());
							//判断文件2中的单词在文件1是否存在，如果不存在，则添加到总共的词汇表。
							if(!TotalAarry.contains(str.trim())){
								TotalAarry.add(str.trim());
								TotalWriter.write(str+" ");
								count2not1++;
							}else{
								//如果文件2中的单词在文件1中已经存在，则放入交集词汇表
								IntersectionAarry.add(str.trim());
								IntersectionWriter.write(str+" ");
							}
						}
					}
				}
			}
			double proportion2not1 =(double)(Math.round(count2not1*10000/SecondAarry.size())/10000.00);
			double proportion1not2 =(double)(Math.round((TotalAarry.size()-count2not1-IntersectionAarry.size())*10000/FirstAarry.size())/10000.00);
			System.out.println("文件1中的单词总数："+FirstAarry.size());
			System.out.println("文件2中的单词总数："+SecondAarry.size());
			System.out.println("总共单词数："+TotalAarry.size());
			//System.out.println("count2not1："+count2not1);
			//System.out.println("count1not2："+(TotalAarry.size()-count2not1-IntersectionAarry.size()));
			System.out.println("属于文件1且不属于文件2的单词数量占文件1单词总数的比例："+proportion1not2*100+"%");
			System.out.println("属于文件2且不属于文件1的单词数量占文件2单词总数的比例："+proportion2not1*100+"%");
			//将统计数据写入文件
			countWriter.write("文件1中的单词总数："+FirstAarry.size()+"\r\n");
			countWriter.write("文件2中的单词总数："+SecondAarry.size()+"\r\n");
			countWriter.write("总共单词数："+TotalAarry.size()+"\r\n");
			countWriter.write("属于文件1且不属于文件2的单词数量占文件1单词总数的比例："+proportion1not2*100+"%"+"\r\n");
			countWriter.write("属于文件2且不属于文件1的单词数量占文件2单词总数的比例："+proportion2not1*100+"%"+"\r\n");
			//关闭缓冲区文件，使得缓冲区的数据写入文件中
			TotalWriter.close();
			IntersectionWriter.close();
			countWriter.close();
		}catch(Exception e){}
	}
	public static void main(String[] args){
		countword();
	}
}
