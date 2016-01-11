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
 * �ڴ���������ڱ��������⣬��separateWord���ֳ������⣬��separate���еı�ע������˹̹���ٷ��ṩ��
 * ÿ̨��������ڴ����������Ҳ��ͬ
 * ����ȫ������д�ļ�����ʱҲ������ڴ�����������ԭ����д�ļ��޹ء�
 */
public class ToolReadAndWriteForSW {
	//���ļ�ʱ���һ���ı�������
	Map<Integer, String> map = new HashMap<Integer, String>();
	//׼�����ݽ��������д���ı�
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
					//ÿ��һ�������ı����뻺������mm����������linetext����ÿһ�е�����
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
				System.out.println("�쳣");
			}
		} catch (Exception e) {
			System.out.println("�ļ������쳣");
			e.printStackTrace();
		}
		return 0;
	}

	public void separateWordsAndWrite(String path) throws IOException{
		SeparateWord start = new SeparateWord();
		int a = 0;
		//����map����map����ÿһ�������Ա�׼
		for (Map.Entry<Integer,String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "," + entry.getValue());
			//writeTxtFile(start.SeparateWords(entry.getValue()),path);
			newmap.put(entry.getKey(), start.SeparateWords(entry.getValue()));
		}
		String resultsString="";
		//����newmap����newmap�����ÿһ��д���ı�����
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