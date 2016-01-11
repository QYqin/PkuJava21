package jdbcTest;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class WebCrawler extends JFrame implements ActionListener {
	// 定义标题条
	JLabel titletop;
	// new新组建
	JButton btnsearch;
	JTextArea txtDate;// 输出框
	JScrollPane jsp1;// 滚动条
	JTextField txtkeyFirst = new JTextField();// 关键词输入框
	//JTextField txtkeyFirst;
	JFileChooser fileChoser;// 文件选择
	File file;// 定义文件
	String path, keywordFrist, keywordSecond;// 文件路径及关键词
	//String  t = new String();
	// 构造界面
	public WebCrawler() {
		this.setSize(800, 700);
		// 定义标题
		titletop = new JLabel("网络爬虫PkuJava21", JLabel.CENTER);

		// 定义按钮
		// btnOpenFile = new JButton("Open File");
		btnsearch = new JButton("Search");

		// 定义文本框
		txtDate = new JTextArea();
		jsp1 = new JScrollPane(txtDate);// 滚动条

		// 定义关键词
		JLabel labkeyFirst = new JLabel("First key words");

		// 定义关键词文本框
		//JTextField txtkeyFirst = new JTextField();

		// 布置按钮画板
		JPanel panBtn = new JPanel();
		panBtn.setLayout(new GridLayout(1, 2));
		panBtn.add(btnsearch);

		// 布置关键词画板
		JPanel panInput = new JPanel();
		panInput.setLayout(new GridLayout(1, 2));
		panInput.add(labkeyFirst);
		panInput.add(txtkeyFirst);

		// 定义关键词与按钮画板
		JPanel panInputBL = new JPanel();
		panInput.setLayout(new GridLayout(1, 1));
		panInputBL.add(panInput);
		panInputBL.add(panBtn);

		// 布置小画板
		JPanel panChoose = new JPanel();
		panChoose.setLayout(new BorderLayout());
		panChoose.add(jsp1, BorderLayout.CENTER);
		panChoose.add(panInputBL, BorderLayout.SOUTH);

		// 布置窗体
		this.setLayout(new BorderLayout());
		this.add(titletop, BorderLayout.NORTH);
		this.add(panChoose, BorderLayout.CENTER);

		// 注册监听
		btnsearch.addActionListener(this);
		// btnOpenFile.addActionListener(this);
		// btncluster.addActionListener(this);
	}

	public static void main(String[] args) throws Exception {
		WebCrawler w = new WebCrawler();
		w.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			if (arg0.getActionCommand().equals("Search")) {
			// 获取文件路径及关键词输入框的内容
			String textfirst = new String();
			textfirst =txtkeyFirst.getText();
			txtDate.append(textfirst+"\r\n");
			//System.out.println("事件相应"+textfirst);
		if(textfirst!=null &&textfirst.length()!=0)
				System.out.println(textfirst);
		else
				System.out.println("kong");
		try {
			//jdbc链接数据库
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/nlp?user=root&password=123456";
			Connection cn = (Connection) DriverManager.getConnection(url);
//			//执行插入
			PreparedStatement ps = (PreparedStatement) cn
					.prepareStatement("insert into text (url,tabword) values (?,?)");
//			for (int i = 1; i <= output.size(); i = i + 2) {
//				ps.setString(1, output.get(i - 1));
//				ps.setString(2, output.get(i));
//				ps.executeUpdate();
//			}
			//查询语句
			String keywords =textfirst;
			String sqlSelect = "select * from text where tabword='"+keywords+"'";
			//执行查询
			ResultSet rs = ps.executeQuery(sqlSelect);

			// 执行查询后取值
			while (rs.next()) {
				// rs.getString("tabword");
				System.out.println(rs.getString(2));
				// System.out.println(rs.getString(3));
				txtDate.append(rs.getString(2)+"\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		}
	}
}
