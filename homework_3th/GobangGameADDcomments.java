import java.io.BufferedReader;
import java.io.InputStreamReader;

 
public class GobangGame {
	// 定义达到赢条件的棋子数目
	private final int WIN_COUNT = 5;
	// 定义用户输入的X坐标
	private int posX = 0;
	// 定义用户输入的X坐标
	private int posY = 0;
	// 定义棋盘
	private Chessboard chessboard;
	// 四个方向，横- 、纵| 、正斜/ 、反斜\  
    private static final int CROSS = 0;  
    private static final int VERT = 1;  
    private static final int RIGHT = 3;  
    private static final int LIFT = 2; 
    private int BLACKSIGN = -1;  
    private int WHITESIGN = -1;  
    private int WHITESIGN_THREE = -1; 
    private int WHITESIGN_FOUR= -1; 
    private int SIGN = -1; 
  //首先遍历棋盘，找到玩家和电脑下过的棋子中最大的连线数并返回。
  		//定义黑白棋字的连线最大棋子数量
    private	int BlackMaxCount=0;
    private	int WhiteMaxCount=0;
	/*存储最大连线棋子的位置
	* 如果有四个棋子相连的情况，在四个棋子的线上下棋
	* 如果有三个棋子相连的情况，在三个棋子的线上下棋
	* 其他情况随机在玩家最大的连线上下棋
	* 如果不能再玩家最大连线的位置下棋，则随机在他的周围下棋。
	*/
    private	int BlackMaxlistX=0;
    private	int BlackMaxlistY=0;
    private	int WhiteMaxlistX=0;
    private	int WhiteMaxlistY=0;
    private	int WhiteMaxlistX_Four=0;
    private	int WhiteMaxlistY_Four=0;
    private	int WhiteMaxlistX_Three=0;
    private	int WhiteMaxlistY_Three=0;
	/**
	 * 空构造器
	 */
	public GobangGame() {
	}

	/**
	 * 构造器，初始化棋盘和棋子属性
	 * 
	 * @param chessboard
	 *            棋盘类
	 */
	public GobangGame(Chessboard chessboard) {
		this.chessboard = chessboard;
	}

	/**
	 * 检查输入是否合法。
	 * 
	 * @param inputStr
	 *            由控制台输入的字符串。
	 * @return 字符串合法返回true,反则返回false。
	 */
	public boolean isValid(String inputStr) {
		// 将用户输入的字符串以逗号(,)作为分隔，分隔成两个字符串
		String[] posStrArr = inputStr.split(",");
		try {
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("请以(数字,数字)的格式输入：");
			return false;
		}
		// 检查输入数值是否在范围之内
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			chessboard.printBoard();
			System.out.println("X与Y坐标只能大于等于1,与小于等于" + Chessboard.BOARD_SIZE
					+ ",请重新输入：");
			return false;
		}
		// 检查输入的位置是否已经有棋子
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "十") {
			chessboard.printBoard();
			System.out.println("此位置已经有棋子，请重新输入：");
			return false;
		}
		return true;
	}

	/**
	 * 开始下棋
	 */
	public void start() throws Exception {
		// true为游戏结束
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		// 获取键盘的输入
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine:每当键盘输入一行内容按回车键，则输入的内容被br读取到
		while ((inputStr = br.readLine()) != null) {
			isOver = false;
			if (!isValid(inputStr)) {
				// 如果不合法，要求重新输入，再继续
				continue;
			}
			// 把对应的数组元素赋为"●"
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			// 判断用户是否赢了
			if (isWon(posX, posY, chessman)) {
				isOver = true;

			} else {
				// 计算机随机选择位置坐标
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],
						chessman);
				System.out.println("computerPosArr[0]"+computerPosArr[0]);
				System.out.println("computerPosArr[1]"+computerPosArr[1]);
				
				// 判断计算机是否赢了
				if (isWon(computerPosArr[0], computerPosArr[1], chessman)) {
					isOver = true;
				}
			}
			// 如果产生胜者，询问用户是否继续游戏
			if (isOver) {
				// 如果继续，重新初始化棋盘，继续游戏
				if (isReplay(chessman)) {
					chessboard.initBoard();
					chessboard.printBoard();
					continue;
				}
				// 如果不继续，退出程序
				break;
			}
			chessboard.printBoard();
			//System.out.println("请输入您下棋的坐标，应以x,y的格式输入：");
		}
	}

	/**
	 * 是否重新开始下棋。
	 * 
	 * @param chessman
	 *            "●"为用户，"○"为计算机。
	 * @return 开始返回true，反则返回false。
	 */
	public boolean isReplay(String chessman) throws Exception {
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "恭喜您，您赢了，"
				: "很遗憾，您输了，";
		System.out.println(message + "再下一局？(y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (br.readLine().equals("y")) {
			// 开始新一局
			return true;
		}
		return false;

	}

	/**
	 * 计算机随机下棋
	 */
	public int[] computerDo() {
		String[][] board = chessboard.getBoard();
//		ComputerDoAi(board);
		int[] result =new int[2];
		result[0]=-1;
		result[1]=-1;
		//为了判断是否下棋成功
		//定义x，y坐标
		int posx=0;
		int posy=0;
		//String chessman = Chessman.WHITE.getChessman();
		//String[][] board = chessboard.getBoard();
		//String chessman = ico;
		/*
		 * 查找计算机最大的连线棋子数
		 * 
		*/
		for(posy=0;posy<Chessboard.BOARD_SIZE;posy++){
			for(posx=0;posx<Chessboard.BOARD_SIZE;posx++)
			{
				//如果棋盘上有棋子，则计算棋盘上棋子类型对应的连线数量
				if(board[posx][posy]==Chessman.BLACK.getChessman()){
					int[] MaxCountArr = SearchMaxCount(posx, posy, Chessman.BLACK.getChessman());
					//寻找黑棋和白棋的最大连线数量，并记录位置
					if(BlackMaxCount<=MaxCountArr[0]){
						BlackMaxCount=MaxCountArr[0];
						BLACKSIGN =MaxCountArr[1];
						//标志位，显示黑棋最大长度的位置是横、竖、斜
						BlackMaxlistX=posx;
						BlackMaxlistY=posy;
						//记录最大长度的坐标
					}
					//BLACKSIGN =MaxCountArr[1];
					//WhiteMaxCount = SearchMaxCount(posx, posy, Chessman.WHITE.getChessman());
				}else if(board[posx][posy]==Chessman.WHITE.getChessman()){
					//BlackMaxCount = SearchMaxCount(posx, posy, Chessman.BLACK.getChessman());
					int[] MaxCountArr = SearchMaxCount(posx, posy, Chessman.WHITE.getChessman());
					if(MaxCountArr[0]==3){
						//WhiteMaxCount=MaxCountArr[0];
						WHITESIGN_FOUR =MaxCountArr[1];
						WhiteMaxlistX_Four=posx;
						WhiteMaxlistY_Four=posy;
						}
					else if(MaxCountArr[0]==2)
					{
						//WhiteMaxCount=MaxCountArr[0];
						WHITESIGN_THREE =MaxCountArr[1];
						WhiteMaxlistX_Three=posx;
						WhiteMaxlistY_Three=posy;
					}else if(MaxCountArr[0]>=WhiteMaxCount)
					{
						WhiteMaxCount=MaxCountArr[0];
						WHITESIGN=MaxCountArr[1];
						WhiteMaxlistX=posx;
						WhiteMaxlistY=posy;
					}
					//WHITESIGN =MaxCountArr[1];
				}	
				
			}
		}
/*
 * 下棋策略
 * 1.如果电脑有四个棋子连在一起的，那么电脑直接下最后一个棋子
 * 2.如果电脑在四个棋子连线的情况下不能下棋,则看是否有三个棋子的情况。
 * 3.如果三个棋子依然不能下棋，则在玩家周围下棋。
 * 4.判断最大连线的数量白棋是否少于黑棋，如果少于黑棋，在再黑棋的两端下棋。
 * 5.如果黑棋和白棋的数量相等，或者白棋的连线数量大于黑棋，则在白棋的两端下棋。
 * 6.如果上述情况均无法下棋，则随机下棋。
 * 7.设置一个round，限定下棋次数为棋盘的一半。
 * 
*/
while(true)
{
	if(WHITESIGN_FOUR>=0){
	/*
	* 判断计算机连线有三个棋子连线的情况。	
	*/		
	int[] MaxCountArr = SearchPosition(WhiteMaxlistX_Four,WhiteMaxlistY_Four,WHITESIGN_FOUR,Chessman.WHITE.getChessman());
	result[0]=MaxCountArr[0];
	result[1]=MaxCountArr[1];
	//如果返回坐标为-1，则此位置不能下棋，则重新选择。
	if(MaxCountArr[0]<0||MaxCountArr[1]<0)
	{
		WHITESIGN_FOUR=-1;
		continue;
	}else{
		return result;
	}
	}else if(WHITESIGN_THREE>=0){
	/*
	 * 判断计算机连线有三个棋子连线的情况。	
	*/
		int[] MaxCountArr = SearchPosition(WhiteMaxlistX_Three,WhiteMaxlistY_Three,WHITESIGN_THREE,Chessman.WHITE.getChessman());
		result[0]=MaxCountArr[0];
		result[1]=MaxCountArr[1];
		//如果返回坐标为-1，则此位置不能下棋，则重新选择。
		if(MaxCountArr[0]<0||MaxCountArr[1]<0)
		{
			WHITESIGN_THREE=-1;
			continue;
		}else{
			return result;
		}
	}else if(WhiteMaxCount<BlackMaxCount&&BLACKSIGN>=0){
	/*
	 * 判断计算机连线有数量小于玩家连线的数量。	
	*/
		int[] MaxCountArr = SearchPosition(BlackMaxlistX,BlackMaxlistY,BLACKSIGN,Chessman.BLACK.getChessman());
		result[0]=MaxCountArr[0];
		result[1]=MaxCountArr[1];	
		//如果返回坐标为-1，则此位置不能下棋，则重新选择。
		if(MaxCountArr[0]<0||MaxCountArr[1]<0)
		{
			BLACKSIGN=-1;
			continue;
		}else{
			return result;
		}
	}else if(WHITESIGN==-1)
	/*
	* 判断计算机没有连线的情况。	
	* */
	{	
			int posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			int posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		while (board[posX][posY] != "十") {
			posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		}
		result[0]=posX;
		result[1]=posY;
		WHITESIGN=0;
		return result;
	}
	else
	{
		/*
		 * 判断计算机在上述过程都下棋失败的情况。	
		*/
		for(WHITESIGN=0;WHITESIGN<4;WHITESIGN++){
			int[] MaxCountArr = SearchPosition(WhiteMaxlistX,WhiteMaxlistY,WHITESIGN,Chessman.WHITE.getChessman());
			result[0]=MaxCountArr[0];
			result[1]=MaxCountArr[1];	
			if(MaxCountArr[0]<0||MaxCountArr[1]<0)
			{
				continue;
			}else{
				return result;
			}
		}
		WHITESIGN=-1;
	}
}
}
//	System.out.println(BlackMaxCount);
//	System.out.println(WhiteMaxCount);
//	System.out.println(BLACKSIGN);
//	System.out.println(WHITESIGN);
//	System.out.println(WHITESIGN_THREE);
//	System.out.println(WHITESIGN_FOUR);
//	System.out.println(BlackMaxlistX);
//	System.out.println(BlackMaxlistY);
//	int posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//	int posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//	String[][] board = chessboard.getBoard();
//	while (board[posX][posY] != "十") {
//		posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//		posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//	}
//	int[] result = { posX, posY };
//	return result;
//	/**
//	 * 定义计算机下棋策略
//	 * 
//	 * @param searchboard 搜索键盘
//	 * 
//	 * @return posX
//	 *            下一步下棋的X坐标。
//	 *            
//	 * @return posY
//	 *            下一步下棋的Y坐标
//	 */
//	public int[] ComputerDoAi(String[][] board)
//	//{
//		
//			//}		
//		//}
		
	/**
	 * 查找最大连线的棋子数量
	 * 
	 * @param SposX
	 *            棋子的X坐标。
	 * @param SposY
	 *            棋子的Y坐标
	 * @param Sico
	 *            棋子类型
	 * @return SMaxCount最大棋子数量
	 */
	public int[] SearchMaxCount(int Sposx,int Sposy,String Sico){
		//首先遍历棋盘，找到玩家和电脑下过的棋子中最大的连线数并返回。
		//定义最大棋子数量
		int SMaxCount=0;
//		//存储最大连线棋子的位置
//		int SBlackMaxlistX=0;
//		int SBlackMaxlistY=0;
//		int SWhiteMaxlistX=0;
//		int SWhiteMaxlistY=0;
		String[][] Sboard = chessboard.getBoard();
		//定义左斜线搜索的基本变量
		int count=0;
		int x_top=Sposx,y_top=Sposy;
		int x_bottom=Sposx,y_bottom=Sposy;
		//查找左斜线方向的最大计数值
		while(--x_bottom>=0&&--y_bottom>=0&&Sboard[x_bottom][y_bottom]== Sico)
		{
			count++;
		}
		while(++x_top<Chessboard.BOARD_SIZE&&++y_top<Chessboard.BOARD_SIZE&&Sboard[x_top][y_top]== Sico)
		{
			count++;
		}
		if(count>SMaxCount)
		{
			SMaxCount=count;
			SIGN=LIFT;
		}
		//判断右斜线最大棋子数量
		count=0;
		x_top=Sposx;y_top=Sposy;
		x_bottom=Sposx;y_bottom=Sposy;
		while(--x_bottom>=0&&++y_top<Chessboard.BOARD_SIZE&&Sboard[x_bottom][y_top]== Sico)
		{
			count++;
		}
		while(++x_top<Chessboard.BOARD_SIZE&&--y_bottom>=0&&Sboard[x_top][y_bottom]== Sico)
		{
			count++;
		}
		if(count>=SMaxCount)
		{
			SMaxCount=count;
			SIGN=RIGHT;
		}
		//判断横向是否满足胜利
		count=0;
		x_top=Sposx;y_top=Sposy;
		x_bottom=Sposx;y_bottom=Sposy;
		while(--x_bottom>=0&&Sboard[x_bottom][y_bottom]== Sico)
		{
			count++;
		}
		while(++x_top<Chessboard.BOARD_SIZE&&Sboard[x_top][y_top]== Sico)
		{
			count++;
		}
		if(count>=SMaxCount)
		{
			SMaxCount=count;
			SIGN=CROSS;
		}
		//判断同一列是否满足胜利
		count=0;
		x_top=Sposx;y_top=Sposy;
		x_bottom=Sposx;y_bottom=Sposy;
		while(--y_bottom>=0&&Sboard[x_bottom][y_bottom]== Sico)
		{
			count++;
		}
		while(++y_top<Chessboard.BOARD_SIZE&&Sboard[x_top][y_top]== Sico)
		{
			count++;
		}
		if(count>=SMaxCount)
		{
			SMaxCount=count;
			SIGN=VERT;
		}
		int[] result = { SMaxCount, SIGN };
		return result;
//		return SMaxCount;
//		return SIGN;
//		*/
//		//四个棋子连在一起的的情况
//			if(WHITESIGN_FOUR>=0){
//			/*
//			 * 判断计算机连线有四个棋子连线的情况。	
//			*/
//				if(WHITESIGN_FOUR==0)
//				/*
//				 * 判断四个棋子连接的位置是否在横向上
//				 */
//				{
//					int x_top=WhiteMaxlistX_Four,y_top=WhiteMaxlistY_Four;
//					int x_bottom=WhiteMaxlistX_Four,y_bottom=WhiteMaxlistY_Four;
//					while(--x_bottom>=0&&board[x_bottom][y_bottom]== Chessman.WHITE.getChessman());
//					if(x_bottom>=0&&board[x_bottom][y_bottom]=="十")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//					else{
//						x_top=WhiteMaxlistX_Four;y_top=WhiteMaxlistY_Four;
//						x_bottom=WhiteMaxlistX_Four;y_bottom=WhiteMaxlistY_Four;
//						while(++x_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]==Chessman.WHITE.getChessman());
//						if(++x_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="十")
//						{
//							result[0]=x_top;
//							result[0]=y_top;
//							return result;
//						}
//					} 		 
//				}
//				else if(WHITESIGN_FOUR==1)
//				/*
//				 * 查看四个棋子的连线是否在竖直方向
//				 */
//				{
//					int x_top=WhiteMaxlistX_Four,y_top=WhiteMaxlistY_Four;
//					int x_bottom=WhiteMaxlistX_Four,y_bottom=WhiteMaxlistY_Four;
//					while(--y_bottom>=0&&board[x_bottom][y_bottom]== Chessman.WHITE.getChessman());
//					if(y_bottom>=0&&board[x_bottom][y_bottom]=="十")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//					else{
//						x_top=WhiteMaxlistX_Four;y_top=WhiteMaxlistY_Four;
//						x_bottom=WhiteMaxlistX_Four;y_bottom=WhiteMaxlistY_Four;
//						while(++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.WHITE.getChessman());;
//						if(++y_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="十")
//						{
//							result[0]=x_top;
//							result[0]=y_top;
//							return result;
//						}
//					} 
//				}else if(WHITESIGN_FOUR==2)
//				/*
//				 * 查看四个棋子的连线是否在左斜方向 \
//				 */
//				{			
//					int x_top=WhiteMaxlistX_Four,y_top=WhiteMaxlistY_Four;
//					int x_bottom=WhiteMaxlistX_Four,y_bottom=WhiteMaxlistY_Four;
//					while(--x_bottom>=0&&--y_bottom>=0&&board[x_bottom][y_bottom]== Chessman.WHITE.getChessman());
//					if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="十")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//					else{
//						x_top=WhiteMaxlistX_Four;y_top=WhiteMaxlistY_Four;
//						x_bottom=WhiteMaxlistX_Four;y_bottom=WhiteMaxlistY_Four;
//						while(++x_top<Chessboard.BOARD_SIZE&&++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.WHITE.getChessman());;
//					if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="十")
//						{
//							result[0]=x_top;
//							result[0]=y_top;
//							return result;
//						}
//					} 
//								
//				}else if(WHITESIGN_FOUR==3)
//				/*
//				 * 查看四个点的连线是否在右斜方向/
//				 */
//				{
//					int x_top=WhiteMaxlistX_Four,y_top=WhiteMaxlistY_Four;
//					int x_bottom=WhiteMaxlistX_Four,y_bottom=WhiteMaxlistY_Four;
//					while(--x_bottom>=0&&++y_bottom>=0&&board[x_bottom][y_bottom]== Chessman.WHITE.getChessman());
//					if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="十")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//					else{
//						x_top=WhiteMaxlistX_Four;y_top=WhiteMaxlistY_Four;
//						x_bottom=WhiteMaxlistX_Four;y_bottom=WhiteMaxlistY_Four;
//						while(++x_top<Chessboard.BOARD_SIZE&&--y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.WHITE.getChessman());;
//						if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="十")
//						{
//						  result[0]=x_top;
//						  result[0]=y_top;
//						  return result;
//						}
//					}
//				}
//			}else if(WHITESIGN_THREE>=0){
//			/*
//			 * 判断计算机连线有三个棋子连线的情况。	
//			*/
//			if(WHITESIGN_THREE==0){
//				int x_top=WhiteMaxlistX_Three,y_top=WhiteMaxlistY_Three;
//				int x_bottom=WhiteMaxlistX_Three,y_bottom=WhiteMaxlistY_Three;
//				while(--x_bottom>=0&&board[x_bottom][y_bottom]== Chessman.WHITE.getChessman());
//				if(x_bottom>=0&&board[x_bottom][y_bottom]=="十")
//				{
//					result[0]=x_bottom;
//					result[0]=y_bottom;
//					return result;
//				}
//				else{
//					x_top=WhiteMaxlistX_Three;y_top=WhiteMaxlistY_Three;
//					x_bottom=WhiteMaxlistX_Three;y_bottom=WhiteMaxlistY_Three;
//					while(++x_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]==Chessman.WHITE.getChessman());
//					if(++x_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="十")
//					{
//						result[0]=x_top;
//						result[0]=y_top;
//						return result;
//					}
//				} 
//							 
//			}
//			else if(WHITESIGN_THREE==1){
//				int x_top=WhiteMaxlistX_Three,y_top=WhiteMaxlistY_Three;
//				int x_bottom=WhiteMaxlistX_Three,y_bottom=WhiteMaxlistY_Three;
//				while(--y_bottom>=0&&board[x_bottom][y_bottom]== Chessman.WHITE.getChessman());
//				if(y_bottom>=0&&board[x_bottom][y_bottom]=="十")
//				{
//					result[0]=x_bottom;
//					result[0]=y_bottom;
//					return result;
//				}
//				else{
//					x_top=WhiteMaxlistX_Three;y_top=WhiteMaxlistY_Three;
//					x_bottom=WhiteMaxlistX_Three;y_bottom=WhiteMaxlistY_Three;
//					while(++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.WHITE.getChessman());
//					if(++y_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="十")
//					{
//						result[0]=x_top;
//						result[0]=y_top;
//						return result;
//					}
//			  }
//				}else if(WHITESIGN_THREE==2){
//					int x_top=WhiteMaxlistX_Three,y_top=WhiteMaxlistY_Three;
//					int x_bottom=WhiteMaxlistX_Three,y_bottom=WhiteMaxlistY_Three;
//					while(--x_bottom>=0&&--y_bottom>=0&&board[x_bottom][y_bottom]== Chessman.WHITE.getChessman());
//					if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="十")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//					else{
//						x_top=WhiteMaxlistX_Three;y_top=WhiteMaxlistY_Three;
//						x_bottom=WhiteMaxlistX_Three;y_bottom=WhiteMaxlistY_Three;
//						while(++x_top<Chessboard.BOARD_SIZE&&++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.WHITE.getChessman());;
//						if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="十")
//						{
//							result[0]=x_top;
//							result[0]=y_top;
//							return result;
//						}
//					} 	
//				}else if(WHITESIGN_THREE==3){
//					int x_top=WhiteMaxlistX_Three,y_top=WhiteMaxlistY_Three;
//					int x_bottom=WhiteMaxlistX_Three,y_bottom=WhiteMaxlistY_Three;
//					while(--x_bottom>=0&&++y_bottom>=0&&board[x_bottom][y_bottom]== Chessman.WHITE.getChessman());
//					if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="十")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//				else{
//						x_top=WhiteMaxlistX_Three;y_top=WhiteMaxlistY_Three;
//						x_bottom=WhiteMaxlistX_Three;y_bottom=WhiteMaxlistY_Three;
//						while(++x_top<Chessboard.BOARD_SIZE&&--y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.WHITE.getChessman());;
//						if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="十")
//					{
//						result[0]=x_top;
//						result[0]=y_top;
//						return result;
//					}
//				} 
//			}				
//			}else if(WhiteMaxCount<BlackMaxCount){
//			/*
//			 * 判断计算机连线有数量小于玩家连线的数量。	
//			*/
//				int[] MaxCountArr = SearchPosition(BlackMaxlistX,BlackMaxlistY,BLACKSIGN,Chessman.BLACK.getChessman());
//				result[0]=MaxCountArr[0];
//				result[1]=MaxCountArr[1];
////			if(BLACKSIGN==0){
////				int x_top=BlackMaxlistX,y_top=BlackMaxlistY;
////				int x_bottom=BlackMaxlistX,y_bottom=BlackMaxlistY;
////				while(--x_bottom>=0&&board[x_bottom][y_bottom]== Chessman.BLACK.getChessman());
////				if(x_bottom>=0&&board[x_bottom][y_bottom]=="十")
////				{
////					result[0]=x_bottom;
////					result[1]=y_bottom;
////					return result;
////				}
////				else{
////					x_top=BlackMaxlistX;y_top=BlackMaxlistY;
////					x_bottom=BlackMaxlistX;y_bottom=BlackMaxlistY;
////					while(++x_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]==Chessman.BLACK.getChessman());
////					if(++x_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]=="十")
////					{
////						result[0]=x_top;
////						result[1]=y_top;
////						return result;
////					}
////				} 
////			}
////			else if(BLACKSIGN==1){
////				int x_top=BlackMaxlistX,y_top=BlackMaxlistY;
////				int x_bottom=BlackMaxlistX,y_bottom=BlackMaxlistY;
////				while(--y_bottom>=0&&board[x_bottom][y_bottom]== Chessman.BLACK.getChessman());
////				if(y_bottom>=0&&board[x_bottom][y_bottom]=="十")
////				{
////					result[0]=x_bottom;
////					result[1]=y_bottom;
////					return result;
////				}
////				else{
////					x_top=BlackMaxlistX;y_top=BlackMaxlistY;
////					x_bottom=BlackMaxlistX;y_bottom=BlackMaxlistY;
////					while(++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.BLACK.getChessman());;
////					if(++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]=="十")
////					{
////						result[0]=x_top;
////						result[1]=y_top;
////						return result;
////					}
////				} 
////				}else if(BLACKSIGN==2){
////					int x_top=BlackMaxlistX,y_top=BlackMaxlistY;
////					int x_bottom=BlackMaxlistX,y_bottom=BlackMaxlistY;
////					while(--x_bottom>=0&&--y_bottom>=0&&board[x_bottom][y_bottom]== Chessman.BLACK.getChessman());
////					System.out.println("x_bottom"+x_bottom);
////					System.out.println("y_bottom"+y_bottom);
////					System.out.println("BlackMaxlistX"+BlackMaxlistX);
////					System.out.println("BlackMaxlistY"+BlackMaxlistY);
//////					System.out.println("x_top"+x_top);
//////					System.out.println("y_top"+y_top);
////					System.out.println("board[][]"+board[x_bottom][y_bottom]);
////					if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="十")
////					{
////						result[0]=x_bottom;
////						result[1]=y_bottom;
////						return result;
////					}
////					else{
////						x_top=BlackMaxlistX;y_top=BlackMaxlistY;
////						x_bottom=BlackMaxlistX;y_bottom=BlackMaxlistY;
////						while(++x_top<Chessboard.BOARD_SIZE&&++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.BLACK.getChessman());
////						if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]=="十")
////						{
////							result[0]=x_top;
////							result[1]=y_top;
////							return result;
////						}
////					} 
//////					System.out.println("x_top"+x_top);
//////					System.out.println("y_top"+y_top);
////				}else if(BLACKSIGN==3){
////					int x_top=BlackMaxlistX,y_top=BlackMaxlistY;
////					int x_bottom=BlackMaxlistX,y_bottom=BlackMaxlistY;
////					while(--x_bottom>=0&&++y_bottom>=0&&board[x_bottom][y_bottom]== Chessman.BLACK.getChessman());
////					if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="十")
////					{
////						result[0]=x_bottom;
////						result[1]=y_bottom;
////						return result;
////					}
////					else{
////						x_top=BlackMaxlistX;y_top=BlackMaxlistY;
////						x_bottom=BlackMaxlistX;y_bottom=BlackMaxlistY;
////						while(++x_top<Chessboard.BOARD_SIZE&&--y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.BLACK.getChessman());;
////						if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]=="十")
////						{
////							result[0]=x_top;
////							result[1]=y_top;
////							return result;
////						}
////					} 
//////					System.out.println(x_top);
//////					System.out.println(y_top);
////				}		
//				}else 
//				if(WHITESIGN==-1){	
//					int posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//					int posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//					while (board[posX][posY] != "十") {
//					posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//					posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//				}
//				result[0]=posX;
//				result[1]=posY;
//				return result;
////				System.out.println(posX);
////				System.out.println(y_bottom);
//			}
//			else
//			{
//				if(WHITESIGN==0){
//					int x_top=WhiteMaxlistX,y_top=WhiteMaxlistY;
//					int x_bottom=WhiteMaxlistX,y_bottom=WhiteMaxlistY;
//					while(--x_bottom>=0&&board[x_bottom][y_bottom]== Chessman.WHITE.getChessman());
//					if(x_bottom>=0&&board[x_bottom][y_bottom]=="十")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//					else{
//						x_top=WhiteMaxlistX;y_top=WhiteMaxlistY;
//						x_bottom=WhiteMaxlistX;y_bottom=WhiteMaxlistY;
//						while(++x_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]==Chessman.WHITE.getChessman());
//						if(++x_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="十")
//						{
//							result[0]=x_top;
//							result[0]=y_top;
//							return result;
//						}
//					}  
//				}else if(WHITESIGN==1){
//					int x_top=WhiteMaxlistX,y_top=WhiteMaxlistY;
//					int x_bottom=WhiteMaxlistX,y_bottom=WhiteMaxlistY;
//					while(--y_bottom>=0&&board[x_bottom][y_bottom]== Chessman.WHITE.getChessman());
//					if(y_bottom>=0&&board[x_bottom][y_bottom]=="十")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//					else{
//						x_top=WhiteMaxlistX;y_top=WhiteMaxlistY;
//						x_bottom=WhiteMaxlistX;y_bottom=WhiteMaxlistY;
//						while(++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.WHITE.getChessman());;
//						if(++y_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="十")
//						{
//							result[0]=x_top;
//							result[0]=y_top;
//							return result;
//						}
//					} 
//				}else if(WHITESIGN==2){
//					int x_top=WhiteMaxlistX,y_top=WhiteMaxlistY;
//					int x_bottom=WhiteMaxlistX,y_bottom=WhiteMaxlistY;
//					while(--x_bottom>=0&&--y_bottom>=0&&board[x_bottom][y_bottom]== Chessman.WHITE.getChessman());
//					if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="十")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//					else{
//						x_top=WhiteMaxlistX;y_top=WhiteMaxlistY;
//						x_bottom=WhiteMaxlistX;y_bottom=WhiteMaxlistY;
//						while(++x_top<Chessboard.BOARD_SIZE&&++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.WHITE.getChessman());;
//						if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="十")
//						{
//							result[0]=x_top;
//							result[0]=y_top;
//							return result;
//						}
//					} 
////								System.out.println(x_bottom);
////								System.out.println(y_bottom);
//					}else if(WHITESIGN==3){
//					//右斜的情况
//					int x_top=WhiteMaxlistX,y_top=WhiteMaxlistY;
//					int x_bottom=WhiteMaxlistX,y_bottom=WhiteMaxlistY;
//					while(--x_bottom>=0&&++y_bottom>=0&&board[x_bottom][y_bottom]== Chessman.WHITE.getChessman());
//					if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="十")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//					else{
//						x_top=WhiteMaxlistX;y_top=WhiteMaxlistY;
//						x_bottom=WhiteMaxlistX;y_bottom=WhiteMaxlistY;
//						while(++x_top<Chessboard.BOARD_SIZE&&--y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.WHITE.getChessman());;
//						if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="十")
//						{
//							result[0]=x_top;
//							result[0]=y_top;
//							return result;
//						}
//					} 		
//					}
//				}
//						return result;
	}
	/**
	 * 判断下棋位置
	 * 
	 * @param Psoitx
	 *            棋子的X坐标。
	 * @param PsoitY
	 *            棋子的Y坐标
	 * @param Psoitico
	 *            棋子类型
	 * @param POSITSIGN
	 *            最大连线方向
	 * @return 下棋位置坐标x，y。
	 */
	public int[] SearchPosition(int Psoitx,int Psoity,int POSITSIGN,String Psoitico){
		int[] result =new int[3];
		String[][] board = chessboard.getBoard();
		if(POSITSIGN==0){
			int x_top=Psoitx,y_top=Psoity;
			int x_bottom=Psoitx,y_bottom=Psoity;
			while(--x_bottom>=0&&board[x_bottom][y_bottom]== Psoitico);
			if(x_bottom>=0&&board[x_bottom][y_bottom]=="十")
			{
				result[0]=x_bottom;
				result[1]=y_bottom;
				return result;
			}
			else{
				x_top=Psoitx;y_top=Psoity;
				x_bottom=Psoitx;y_bottom=Psoity;
				while(++x_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]==Psoitico);
				if(++x_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]=="十")
				{
					result[0]=x_top;
					result[1]=y_top;
					return result;
				}
			} 
		}
		else if(POSITSIGN==1){
			int x_top=Psoitx,y_top=Psoity;
			int x_bottom=Psoitx,y_bottom=Psoity;
			while(--y_bottom>=0&&board[x_bottom][y_bottom]== Psoitico);
			if(y_bottom>=0&&board[x_bottom][y_bottom]=="十")
			{
				result[0]=x_bottom;
				result[1]=y_bottom;
				return result;
			}
			else{
				x_top=Psoitx;y_top=Psoity;
				x_bottom=Psoitx;y_bottom=Psoity;
				while(++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Psoitico);;
				if(++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]=="十")
				{
					result[0]=x_top;
					result[1]=y_top;
					return result;
				}
			} 
			}else if(POSITSIGN==2){
				int x_top=Psoitx,y_top=Psoity;
				int x_bottom=Psoitx,y_bottom=Psoity;
				while(--x_bottom>=0&&--y_bottom>=0&&board[x_bottom][y_bottom]==Psoitico);
				if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="十")
				{
					result[0]=x_bottom;
					result[1]=y_bottom;
					return result;
				}
				else{
					x_top=Psoitx;y_top=Psoity;
					x_bottom=Psoitx;y_bottom=Psoity;
					while(++x_top<Chessboard.BOARD_SIZE&&++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]==Psoitico);
					if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]=="十")
					{
						result[0]=x_top;
						result[1]=y_top;
						return result;
					}
				} 
			}else if(POSITSIGN==3){
				int x_top=Psoitx,y_top=Psoity;
				int x_bottom=Psoitx,y_bottom=Psoity;
				while(--x_bottom>=0&&++y_bottom>=0&&board[x_bottom][y_bottom]==Psoitico);
				if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="十")
				{
					result[0]=x_bottom;
					result[1]=y_bottom;
					return result;
				}
				else{
					x_top=Psoitx;y_top=Psoity;
					x_bottom=Psoitx;y_bottom=Psoity;
					while(++x_top<Chessboard.BOARD_SIZE&&--y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]==Psoitico);;
					if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]=="十")
					{
						result[0]=x_top;
						result[1]=y_top;
						return result;
					}
				} 
			}
		//如果在此位置两端都下棋不成功，则都置为-1，继续寻找其他 
		result[0]=-1;
		result[1]=-1;
		return result;
	}
	/**
	 * 判断输赢
	 * 
	 * @param posX
	 *            棋子的X坐标。
	 * @param posY
	 *            棋子的Y坐标
	 * @param ico
	 *            棋子类型
	 * @return 如果有五颗相邻棋子连成一条直接，返回真，否则相反。
	 */
	public boolean isWon(int posX, int posY, String ico) {
		String[][] board = chessboard.getBoard();
		//String chessman = ico;
		int count=0;
		//定义x,y的上方向坐标和下方向坐标
		int x_top=posX,y_top=posY;
		int x_bottom=posX,y_bottom=posY;
		//判断左斜线是否满足胜利
		while(--x_bottom>=0&&--y_bottom>=0&&board[x_bottom][y_bottom]== ico)
		{
			count++;
		}
		while(++x_top<Chessboard.BOARD_SIZE&&++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== ico)
		{
			count++;
		}
		if(count>=4)
		{
			return true;
		}
		//判断右斜线是否满足胜利
		count=0;
		x_top=posX;y_top=posY;
		x_bottom=posX;y_bottom=posY;
		while(--x_bottom>=0&&++y_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_top]== ico)
		{
			count++;
		}
		while(++x_top<Chessboard.BOARD_SIZE&&--y_bottom>=0&&board[x_top][y_bottom]== ico)
		{
			count++;
		}
		if(count>=4)
		{
			return true;
		}
		//判断横向是否满足胜利
		x_top=posX;y_top=posY;
		x_bottom=posX;y_bottom=posY;
		count=0;
		while(--x_bottom>=0&&board[x_bottom][y_bottom]== ico)
		{
			count++;
		}
		while(++x_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== ico)
		{
			count++;
		}
		if(count>=4)
		{
			return true;
		}
		//判断同一列是否满足胜利
		x_top=posX;y_top=posY;
		x_bottom=posX;y_bottom=posY;
		count=0;
		while(--y_bottom>=0&&board[x_bottom][y_bottom]== ico)
		{
			count++;
		}
		while(++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== ico)
		{
			count++;
		}
		if(count>=4)
		{
			return true;
		}
//		while(--x_bottom>=0&&--y_bottom>=0)
//		{
//			if(board[x_bottom][y_bottom]== ico)
//			{
//				count++;
//			}
//		}
//		while(++x_top<Chessboard.BOARD_SIZE&&++y_top<Chessboard.BOARD_SIZE)
//		{
//			if(board[x_top][y_top]== ico)
//			{
//				count++;
//			}
//		}
//		if(count>=4)
//		{
//			return true;
//		}
//		while (x_bottom-->=0 || x1++<Chessboard.BOARD_SIZE || y1++>=0
//				|| y1++<Chessboard.BOARD_SIZE)
//		{
//			if( board[x1][y1]== ico||board[x1][y1-1]== ico)
//			{
//				while( board[++x1][++y1]== ico)
//				{
//					count++;
//				}
//				x1=posX;
//				y1=posY;
//				while( board[--x1][--y1]== ico)
//				{
//					count++;
//				}
//				if(count>=4)
//				{
//					return true;
//				}
//			}
//		}
		
		return false;
	}

	public static void main(String[] args) throws Exception {

		GobangGame gb = new GobangGame(new Chessboard());
		gb.start();
	}
}
