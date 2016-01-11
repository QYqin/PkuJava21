package separate;

import java.io.IOException
//分词和词性标注的main函数接口
public class SeparateMain {
	public static void main(String[] args) {
		//文件的路径
		String fileReadPath="C:\\Users\\Luojw\\workspace\\java-net\\1321";
		String fileWritePath="C:\\Users\\Luojw\\workspace\\java-net\\2.txt";
		ToolReadAndWriteForSW trwf=new ToolReadAndWriteForSW();
		trwf.readTxtFile(fileReadPath, fileWritePath);
		try {
			//调用词性标注工具
			trwf.separateWordsAndWrite(fileWritePath);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}