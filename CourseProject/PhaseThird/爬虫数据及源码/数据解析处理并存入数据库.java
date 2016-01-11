package jdbcTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class TestSQL {
	// 解析了数据 放到了数组array
	public static ArrayList<String> output = new ArrayList<String>();

	public static void main(String args[]) {
		try {
			// TODO Auto-generated method stub
			String encoding = "utf-8";
			String path = "12";
			File file = new File(path);
			File[] tempList = file.listFiles();
			System.out.println("该目录下对象个数：" + tempList.length);
			for (int i = 0; i < tempList.length; i++) {
				if (tempList[i].isFile() && tempList[i].exists()) {
					InputStreamReader read = new InputStreamReader(new FileInputStream(tempList[i]), encoding);
					BufferedReader bufferedReader = new BufferedReader(read);
					String lineTxt = null;
					String reg1 = "(URL:: )(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";
					String reg2 = "((分类： |标签： )[a-zA-Z\\s]*)";
					while ((lineTxt = bufferedReader.readLine()) != null) {
						// System.out.println(bufferedReader.readLine());
						String out = "";
						Pattern p = Pattern.compile(reg1);
						Matcher macher = p.matcher(lineTxt);
						if (macher.find())
							out = out + macher.group(0).replaceAll("(URL:: )", "");
						Pattern pp = Pattern.compile(reg2);
						Matcher macher1 = pp.matcher(lineTxt);
						if (macher1.find())
							out = out + macher1.group(0).replaceAll("(分类： |标签： )", "");
						if (out.equals(""))
							continue;
						output.add(out);
					}
					for (int s = 0; s < 6; s++)
						for (int j = 0; j < output.size() - 1; j++) {
							if (output.get(j).length() > 5 && output.get(j + 1).length() > 5) {
								if (output.get(j).subSequence(0, 5).equals(output.get(j + 1).subSequence(0, 5)))
									output.remove(j);
							}

						}

					// boolean hah= Save(output,path);
					// System.out.println(hah);
					for (String string : output) {
						System.out.println(string);
					}
					// boolean s=Save(output, tempList[i].getPath());
				} else {
					System.out.println("异常");
				}
			}
		} catch (Exception e) {
			System.out.println("文件开关异常");
			e.printStackTrace();
		}

		// try {
		// // TODO Auto-generated method stub
		// String encoding = "utf-8";
		// String path = "E:\\1\\123";
		// File file = new File(path);
		//
		// if (file.isFile() && file.exists()) {
		// InputStreamReader read = new InputStreamReader(new
		// FileInputStream(file), encoding);
		// BufferedReader bufferedReader = new BufferedReader(read);
		// String lineTxt = null;
		// String reg1 = "(URL::
		// )(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";
		// String reg2 = "((分类： |标签： )[a-zA-Z\\s]*)";
		// while ((lineTxt = bufferedReader.readLine()) != null) {
		// String out = "";
		// Pattern p = Pattern.compile(reg1);
		// Matcher macher = p.matcher(lineTxt);
		// if (macher.find())
		// out = out + macher.group(0).replaceAll("(URL:: )", "");
		// Pattern pp = Pattern.compile(reg2);
		// Matcher macher1 = pp.matcher(lineTxt);
		// if (macher1.find())
		// out = out + macher1.group(0).replaceAll("(分类： |标签： )", "");
		// if (out.equals(""))
		// continue;
		// output.add(out);
		// }
		// for (int i = 0; i < output.size() - 1; i++) {
		// if (output.get(i).subSequence(0, 5).equals(output.get(i +
		// 1).subSequence(0, 5)))
		// output.remove(i + 1);
		// }
		//
		// } else {
		// System.out.println("异常");
		// }
		// } catch (Exception e) {
		// System.out.println("文件开关异常");
		// e.printStackTrace();
		// }
		try {
			// jdbc链接数据库
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/nlp?user=root&password=123456";
			Connection cn = (Connection) DriverManager.getConnection(url);
			// 执行插入
			PreparedStatement ps = (PreparedStatement) cn
					.prepareStatement("insert into text (url,tabword) values (?,?)");
			for (int i = 1; i <= output.size(); i = i + 2) {
				ps.setString(1, output.get(i - 1));
				ps.setString(2, output.get(i));
				ps.executeUpdate();
			}
			// 查询语句
			String keywords = "Android";
			String sqlSelect = "select * from text where tabword='" + keywords + "'";
			// 执行查询
			ResultSet rs = ps.executeQuery(sqlSelect);

			// 执行查询后取值
			while (rs.next()) {
				// rs.getString("tabword");
				System.out.println(rs.getString(2));
				// System.out.println(rs.getString(3));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
