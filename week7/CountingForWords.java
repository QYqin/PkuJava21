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
				m = m + 1;// ����ͳ�ƽ����ĵ�����Ŀ
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
				System.out.println("�쳣");
			}
		} catch (Exception e) {
			System.out.println("�ļ������쳣");
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
			// map��get������keyֵ��value��ֵ������ֵΪint�������a=words��1�������ǲ�a�Ĵ���
			Integer count = map.get(words[j]);// ����ָ������ӳ���ֵ�������ӳ�䲻�����ü���ӳ���ϵ���򷵻�
												// null
			if (count == null) {
				map.put(words[j], 1);
			} else {
				map.put(words[j], ++count);
			}
		}
		for (Map.Entry<String, Integer> entry : map.entrySet()) {// entrySet()��ӳ���а�����ӳ���ϵ��
																	// set ��ͼ
			System.out.println(entry.getKey() + "," + entry.getValue());
			writeTxtFile(entry.getKey() + "," + entry.getValue(), fileWritePath);
			// a����֤�Ƿ�©��ͳ�Ƶ��ʸ����ı���
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
			 * �����ǰ������ļ�����д���� ����ÿдһ�ξ���Ҫ���ļ����ж�ȡһ��
			 */
			File file = new File(fileWritePath);
			fis = new FileInputStream(file);// ��ȡ�ļ��ϵ����ݡ�
			isr = new InputStreamReader(fis);// InputStreamReader ���ֽ���ת��Ϊ�ַ�����
			br = new BufferedReader(isr);// ���ַ��������ж�ȡ�ı�����������ַ����Ӷ�ʵ���ַ���������еĸ�Ч��ȡ��

			StringBuffer buf = new StringBuffer();
			for (int j = 1; (temp = br.readLine()) != null; j++) {
				buf = buf.append(temp);
				buf = buf.append(System.getProperty("line.separator"));// ƽ̨֮�������еķָ���
			}
			buf.append(filein);
			fos = new FileOutputStream(file);// ����ֽ���
			pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();// ��ʾǿ�ƽ��������е����ݷ��ͳ�ȥ,���صȵ���������.
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