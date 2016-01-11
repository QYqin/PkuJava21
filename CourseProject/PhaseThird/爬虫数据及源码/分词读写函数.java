package separate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;




/*
 * 
 * 内存溢出不属于本程序问题，是separateWord内种出现问题，而separate类中的标注方法由斯坦福官方提供。
 * 每台计算机的内存溢出的数量也不同
 * 在完全不调用写文件代码时也会出现内存溢出情况，即原因与写文件无关。
 */
public class ToolReadAndWriteForSW {
	//读文件时候的一个文本缓冲区
	Map<Integer, String> map = new HashMap<Integer, String>();
	//准备数据结果，用于写出文本
	Map<Integer, String> newmap = new HashMap<Integer, String>();

	public int readTxtFile(String fileReadPath,String fileWritePath) {
		try {
			String encoding = "utf-8";
			String mem = "";
			int mm = 0;
			String[] strarray;
			//SeparateWord start = new SeparateWord();
			File file = new File(fileReadPath);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					//每读一条，将文本放入缓冲区，mm代表条数，linetext代表每一行的内容
					map.put(mm, lineTxt);
					// writeTxtFile(start.SeparateWords(lineTxt),fileWritePath);
					 System.out.println(mm+lineTxt);
					mm = mm + 1;
					
					System.out.println(mm);
				}
				Thread.sleep(1);
				read.close();
				return mm;
			} else {
				System.out.println("异常");
			}
		} catch (Exception e) {
			System.out.println("文件开关异常");
			e.printStackTrace();
		}
		return 0;
	}

	public void separateWordsAndWrite(String path) throws IOException{
		SeparateWord start = new SeparateWord();
		int a = 0;
		//遍历map，将map里面每一条做词性标准
		for (Map.Entry<Integer,String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "," + entry.getValue());
			//writeTxtFile(start.SeparateWords(entry.getValue()),path);
			newmap.put(entry.getKey(), start.SeparateWords(entry.getValue()));
		}
		String resultsString="";
		//遍历newmap，将newmap里面的每一条写出文本保存
		for (Map.Entry<Integer,String> entry : newmap.entrySet()) {
			System.out.println(entry.getKey() + "," + entry.getValue());
			//resultsString+=entry.getValue()+"\r\n";
			writeTxtFile(entry.getValue(),path);
		}
		//writeTxtFile(resultsString,path);
		System.out.println(a);
	}
	public boolean writeTxtFile(String newStr,String path) throws IOException {
		boolean flag = false;
		String filein = newStr /* + "\r\n" */;
		String temp = "";
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
		PrintWriter pw = null;
		try {
			File file = new File(path);
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			StringBuffer buf = new StringBuffer();
			for (int j = 1; (temp = br.readLine()) != null; j++) {
				buf = buf.append(temp);
				buf = buf.append(System.getProperty("line.separator"));
			}
			buf.append(filein);
			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();
			flag = true;
		} catch (IOException e1) {
			throw e1;
		} finally {
			if (pw != null) {
				pw.close();
			}
			if (fos != null) {
				fos.close();
			}
			if (br != null) {
				br.close();
			}
			if (isr != null) {
				isr.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
		return flag;
	}
}