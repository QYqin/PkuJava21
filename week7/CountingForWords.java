import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountingForWords {
	public static void main(String[] args) throws IOException {
		String fileReadPath1 = "E:\\sj\\test1.txt";
		String fileReadPath2 = "E:\\sj\\test2.txt";
		String fileWritePath = "E:\\sj\\test3.txt";
		String[] string1, string2;

		string1 = readTxt(fileReadPath1);
		string2 = readTxt(fileReadPath2);

		int j = string1.length;
		System.out.println("The first file has " + j + " words");
		int i = string2.length;
		System.out.println("The second file has " + i + " words");

		List<String> l1 = Arrays.asList(string1);
		List<String> l2 = Arrays.asList(string2);
		List<Object> resultList = new ArrayList<>();
		resultList.addAll(l1);
		resultList.addAll(l2);
		String[] result = resultList.toArray(new String[i + j]);

		// System.out.println(j);
		// // System.out.println(i);
		// for (int i = 0; i < string2.length; i++) {
		// int a = j + i;
		// string1[a] = string2[i];
		// }
		// String[] string = (String[])result;
		counting(result, fileWritePath);

		ArrayList<String> tem = new ArrayList<>();
		int m = 0;
		// int n = 0;
		for (int x = 0; x < j; x++) {
			if (l2.contains(string1[x])) {
				if (!tem.contains(string1[x])) {
					tem.add(string1[x]);
					// tem.add(" ");
					// n = n + 1;
				}
				// System.out.println(string1[x]);
				m = m + 1;// 用于统计交集的单词数目
			}

		}
		System.out.println(tem);
		float c = (float) (j - m) / j;
		float d = (float) (i - m) / i;
		System.out.println("The first file contains " + c * 100
				+ "% different words!");
		System.out.println("The second file contains " + d * 100
				+ "% different words!");
	}

	public static String[] readTxt(String filePath) {
		String[] strarray1 = null;
		try {
			String encoding = "utf-8";
			String mem = "";
			int mm = 0;
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					lineTxt = lineTxt.trim();
					mem += lineTxt + " ";
					mm = mm + 1;
				}
				Thread.sleep(1);
				strarray1 = mem.split(" ");
				read.close();
				return strarray1;
			} else {
				System.out.println("异常");
			}
		} catch (Exception e) {
			System.out.println("文件开关异常");
			e.printStackTrace();
		}
		return strarray1;
	}

	// Map<String, Integer> map = new HashMap<String, Integer>();
	public static void counting(String[] words, String fileWritePath)
			throws IOException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int a = 0;
		for (int j = 0; j < words.length; j++) {
			// map。get，根据key值查value的值，返回值为int，例如查a=words【1】，就是查a的次数
			Integer count = map.get(words[j]);// 返回指定键所映射的值；如果此映射不包含该键的映射关系，则返回
												// null
			if (count == null) {
				map.put(words[j], 1);
			} else {
				map.put(words[j], ++count);
			}
		}
		for (Map.Entry<String, Integer> entry : map.entrySet()) {// entrySet()此映射中包含的映射关系的
																	// set 视图
			System.out.println(entry.getKey() + "," + entry.getValue());
			writeTxtFile(entry.getKey() + "," + entry.getValue(), fileWritePath);
			// a是验证是否漏掉统计单词个数的变量
			a = a + entry.getValue();
		}
		System.out.println(a);
	}

	public static boolean writeTxtFile(String newStr, String fileWritePath)
			throws IOException {
		boolean flag = false;
		String filein = newStr /* + "\r\n" */;
		String temp = "";
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
		PrintWriter pw = null;
		try {
			/*
			 * 由于是按行向文件进行写操作 所以每写一次就需要将文件按行读取一次
			 */
			File file = new File(fileWritePath);
			fis = new FileInputStream(file);// 读取文件上的数据。
			isr = new InputStreamReader(fis);// InputStreamReader 将字节流转换为字符流。
			br = new BufferedReader(isr);// 从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。

			StringBuffer buf = new StringBuffer();
			for (int j = 1; (temp = br.readLine()) != null; j++) {
				buf = buf.append(temp);
				buf = buf.append(System.getProperty("line.separator"));// 平台之间行与行的分隔符
			}
			buf.append(filein);
			fos = new FileOutputStream(file);// 输出字节流
			pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();// 表示强制将缓冲区中的数据发送出去,不必等到缓冲区满.
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