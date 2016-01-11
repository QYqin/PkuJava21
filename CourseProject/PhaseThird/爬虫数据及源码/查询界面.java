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
	// ���������
	JLabel titletop;
	// new���齨
	JButton btnsearch;
	JTextArea txtDate;// �����
	JScrollPane jsp1;// ������
	JTextField txtkeyFirst = new JTextField();// �ؼ��������
	//JTextField txtkeyFirst;
	JFileChooser fileChoser;// �ļ�ѡ��
	File file;// �����ļ�
	String path, keywordFrist, keywordSecond;// �ļ�·�����ؼ���
	//String  t = new String();
	// �������
	public WebCrawler() {
		this.setSize(800, 700);
		// �������
		titletop = new JLabel("��������PkuJava21", JLabel.CENTER);

		// ���尴ť
		// btnOpenFile = new JButton("Open File");
		btnsearch = new JButton("Search");

		// �����ı���
		txtDate = new JTextArea();
		jsp1 = new JScrollPane(txtDate);// ������

		// ����ؼ���
		JLabel labkeyFirst = new JLabel("First key words");

		// ����ؼ����ı���
		//JTextField txtkeyFirst = new JTextField();

		// ���ð�ť����
		JPanel panBtn = new JPanel();
		panBtn.setLayout(new GridLayout(1, 2));
		panBtn.add(btnsearch);

		// ���ùؼ��ʻ���
		JPanel panInput = new JPanel();
		panInput.setLayout(new GridLayout(1, 2));
		panInput.add(labkeyFirst);
		panInput.add(txtkeyFirst);

		// ����ؼ����밴ť����
		JPanel panInputBL = new JPanel();
		panInput.setLayout(new GridLayout(1, 1));
		panInputBL.add(panInput);
		panInputBL.add(panBtn);

		// ����С����
		JPanel panChoose = new JPanel();
		panChoose.setLayout(new BorderLayout());
		panChoose.add(jsp1, BorderLayout.CENTER);
		panChoose.add(panInputBL, BorderLayout.SOUTH);

		// ���ô���
		this.setLayout(new BorderLayout());
		this.add(titletop, BorderLayout.NORTH);
		this.add(panChoose, BorderLayout.CENTER);

		// ע�����
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
			// ��ȡ�ļ�·�����ؼ�������������
			String textfirst = new String();
			textfirst =txtkeyFirst.getText();
			txtDate.append(textfirst+"\r\n");
			//System.out.println("�¼���Ӧ"+textfirst);
		if(textfirst!=null &&textfirst.length()!=0)
				System.out.println(textfirst);
		else
				System.out.println("kong");
		try {
			//jdbc�������ݿ�
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/nlp?user=root&password=123456";
			Connection cn = (Connection) DriverManager.getConnection(url);
//			//ִ�в���
			PreparedStatement ps = (PreparedStatement) cn
					.prepareStatement("insert into text (url,tabword) values (?,?)");
//			for (int i = 1; i <= output.size(); i = i + 2) {
//				ps.setString(1, output.get(i - 1));
//				ps.setString(2, output.get(i));
//				ps.executeUpdate();
//			}
			//��ѯ���
			String keywords =textfirst;
			String sqlSelect = "select * from text where tabword='"+keywords+"'";
			//ִ�в�ѯ
			ResultSet rs = ps.executeQuery(sqlSelect);

			// ִ�в�ѯ��ȡֵ
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
