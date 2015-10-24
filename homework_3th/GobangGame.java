package newstar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Stack;


 
public class GobangGame {
	// 定义达到赢条件的棋子数目
	private final int WIN_COUNT = 5;
	// 定义用户输入的X坐标
	private int posX = 0;
	// 定义用户输入的X坐标
	private int posY = 0;
	// 定义棋盘
	private Chessboard chessboard;
	Stack<Integer> stack =new Stack<Integer>();
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
			System.out.println(chessboard.getBoard().length);
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
			if (isWon(posX, posY, chessman,6)) {
				isOver = true;

			} else {
				// 计算机随机选择位置坐标
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],
						chessman);
				// 判断计算机是否赢了
				if (isWon(computerPosArr[0], computerPosArr[1], chessman,6)) {
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

		
		
		
		// 机器人ai
		String[][] allChesses = chessboard.getBoard();
		long powerValue = 0;// 初始化防守权值
		int X =1;
		int Y =1;
		int row = 1;// 初始化防守位置行和列
		int col = 1;
		long attackPowerValue = 0;// 初始化进攻权值
		int attactRow = 0;// 初始化进攻位置行和列
		int attactCol = 0;
		boolean isFirst = true;// 是否是第一次，初始化为是
		for (int i = 0; i < allChesses.length; i++) {
			// 通过计算得到防守的最佳位置,因为是防守所以统计白色
			for (int j = 0; j < allChesses.length; j++) {

				if (allChesses[i][j] == "十") {
					// 对还没有走的空棋格进行统计
					if (isFirst == true) {
						// 第一个为空的空格
						row = i;// 在棋盘中的位置
						col = j;
						powerValue = RowPowerValue( i, j,
								"○")
								+ ColPowerValue(i, j, "○")
								+ RightBiasPowerValue( i, j,
										"○")
								+ LeftBiasPowerValue( i, j,
										"○");// 计算当前位置的权值
						isFirst = false;// 表示第一个位置计算完毕

					} else {

						long nowPowerValue;// 打擂计算最佳防守位置
						nowPowerValue = RowPowerValue(i, j,
								"○")
								+ ColPowerValue( i, j, "○")
								+ RightBiasPowerValue( i, j,
										"○")
								+ LeftBiasPowerValue( i, j,
										"○");
						if (nowPowerValue > powerValue) {

							row = i;
							col = j;
							powerValue = nowPowerValue;

						}

					}

				}

			}

		}

		isFirst = true;// 初始化isFirst为true
		for (int i = 0; i < allChesses.length; i++) {

			for (int j = 0; j < allChesses.length; j++) {

				if (allChesses[i][j] == "十") {
					// 计算空棋格的权值
					if (isFirst == true) {
						// 第一个空棋格,只执行一次
						attactRow = i;// 第一个空棋格的行和列
						attactCol = j;
						attackPowerValue = RowPowerValue( i, j,
								"●")
								+ ColPowerValue( i, j, "●")
								+ RightBiasPowerValue(i, j,
										"●")
								+ LeftBiasPowerValue( i, j,
										"●");// 计算第一个空棋格的权值
						isFirst = false;

					} else {

						long nowPowerValue;// 打擂计算最佳进攻位置
						nowPowerValue = RowPowerValue( i, j,
								"●")
								+ ColPowerValue(i, j, "●")
								+ RightBiasPowerValue( i, j,
										"●")
								+ LeftBiasPowerValue( i, j,
										"●");
						if (nowPowerValue > powerValue) {

							attactRow = i;
							attactCol = j;
							attackPowerValue = nowPowerValue;

						}

					}

				}

			}

		}
//		if (stack.isEmpty()) {
//			// 如果机器人走第一颗棋，就不用计算了随机走一步棋
//			Random random = new Random();
//			X = random.nextInt(allChesses.length);
//			X = random.nextInt(allChesses.length);
//
//		} else{
			// 否则就确定是防守还是进攻
			if (attackPowerValue >= powerValue) {
				// 比较防守和进攻权值来确定防守还是进攻
				X = attactRow;
				Y = attactCol;

			} else {

				X = row;
				Y = col;

			}
//		}
//			stack.add(X);// 将走棋信息压入栈中

		
		int[] result = { X, Y };
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
	public boolean isWon(int posX, int posY, String ico,int step) {
		String[][] board = chessboard.getBoard();
		int i=0,j=0;
		while (board[posX+i][posY] == ico){
			i++;
			if(posX+i==Chessboard.BOARD_SIZE) break;
		}
					
		while (board[posX-j][posY] == ico){
			j++;
			if(posX-j==-1) break;
		}
		if(i+j>=step) return true;
		i=0;j=0;
		while (board[posX][posY+i] == ico){
			i++;
			if(posY+i==Chessboard.BOARD_SIZE) break;
		}
		while (board[posX][posY-j] == ico){
			j++;
			if(posY-j==-1) break;
		}
		if(i+j>=step) return true;
		i=0;j=0;
		while (board[posX+i][posY+i] == ico){
			i++;
			if(posY+i==Chessboard.BOARD_SIZE||posX+i==Chessboard.BOARD_SIZE) break;
		}
		while (board[posX-j][posY-j] == ico){
			j++;
			if(posY-j==-1||posX-j==-1) break;
		}
		if(i+j>=step) return true;
		i=0;j=0;
		while (board[posX-i][posY+i] == ico){
			i++;
			if(posY+i==Chessboard.BOARD_SIZE||posX-i==-1) break;
		}
		while (board[posX+j][posY-j] == ico){
			j++;
			if(posY-j==-1||posX+i==Chessboard.BOARD_SIZE) break;
		}
		if(i+j>=step) return true;
		return false;
	}

	public static void main(String[] args) throws Exception {

		GobangGame gb = new GobangGame(new Chessboard());
		gb.start();
	}
	
	
	
	
	
	
	
	
	
	
	
	public long LeftBiasPowerValue( int row, int col,
			String  color) {

		NullAndCount colUp = LeftBiasUpPowerValue(row, col, color);
		NullAndCount colDown = LeftBiasDownPowerValue( row, col,color);
		long powerValue;
		switch (colUp.getChessCount() + colDown.getChessCount() + 1) {

		case 1:
			if (colUp.getNullCount() == 0 && colDown.getNullCount() == 0) {

				powerValue = 0;

			} else if ((colUp.getNullCount() == 1 && colDown.getNullCount() == 0)
					|| (colUp.getNullCount() == 0 && colDown.getNullCount() == 1)) {

				powerValue = 1;

			} else {

				powerValue = 5;

			}
			break;
		case 2:
			if (colUp.getNullCount() == 0 && colDown.getNullCount() == 0) {

				powerValue = 0;

			} else if ((colUp.getNullCount() == 1 && colDown.getNullCount() == 0)
					|| (colUp.getNullCount() == 0 && colDown.getNullCount() == 1)) {

				powerValue = 21;

			} else {

				powerValue = 85;

			}
			break;
		case 3:
			if (colUp.getNullCount() == 0 && colDown.getNullCount() == 0) {

				powerValue = 0;

			} else if ((colUp.getNullCount() == 1 && colDown.getNullCount() == 0)
					|| (colUp.getNullCount() == 0 && colDown.getNullCount() == 1)) {

				powerValue = 341;

			} else {

				powerValue = 1365;

			}
			break;
		case 4:
			if (colUp.getNullCount() == 0 && colDown.getNullCount() == 0) {

				powerValue = 0;

			} else if ((colUp.getNullCount() == 1 && colDown.getNullCount() == 0)
					|| (colUp.getNullCount() == 0 && colDown.getNullCount() == 1)) {

				powerValue = 5461;

			} else {

				powerValue = 21845;

			}
			break;
		default:
			powerValue = 87381;
			break;

		}
		return powerValue;

	}

	public NullAndCount LeftBiasDownPowerValue(int row,
			int col, String color) {
		String[][] allChesses = chessboard.getBoard();
		int count = 0;
		NullAndCount nullAndCount = new NullAndCount();
		int i = row;
		int j = col;
		while (i < allChesses.length - 1 && j > 0) {

			if (allChesses[i + 1][j - 1] == color) {

				count++;
				i = i + 1;
				j = j - 1;

			} else {

				if (allChesses[i + 1][j - 1] == "十") {

					nullAndCount.setNullCount(1);

				}
				break;

			}

		}
		nullAndCount.setChessCount(count);
		return nullAndCount;

	}

	public NullAndCount LeftBiasUpPowerValue( int row,
			int col, String color) {
		String[][] allChesses = chessboard.getBoard();
		int count = 0;
		NullAndCount nullAndCount = new NullAndCount();
		int i = row;
		int j = col;
		while (i > 0 && j < allChesses.length - 1) {

			if (allChesses[i - 1][j + 1] == color) {

				count++;
				i = i - 1;
				j = j + 1;

			} else {

				if (allChesses[i - 1][j + 1] == "十") {

					nullAndCount.setNullCount(1);

				}
				break;

			}

		}
		nullAndCount.setChessCount(count);
		return nullAndCount;

	}

	public long RightBiasPowerValue( int row, int col,
			String color) {
		NullAndCount colUp = RightBiasUpPowerValue(row, col, color);
		NullAndCount colDown = RightBiasDownPowerValue( row, col,
				color);
		long powerValue;
		switch (colUp.getChessCount() + colDown.getChessCount() + 1) {

		case 1:
			if (colUp.getNullCount() == 0 && colDown.getNullCount() == 0) {

				powerValue = 0;

			} else if ((colUp.getNullCount() == 1 && colDown.getNullCount() == 0)
					|| (colUp.getNullCount() == 0 && colDown.getNullCount() == 1)) {

				powerValue = 1;

			} else {

				powerValue = 5;

			}
			break;
		case 2:
			if (colUp.getNullCount() == 0 && colDown.getNullCount() == 0) {

				powerValue = 0;

			} else if ((colUp.getNullCount() == 1 && colDown.getNullCount() == 0)
					|| (colUp.getNullCount() == 0 && colDown.getNullCount() == 1)) {

				powerValue = 21;

			} else {

				powerValue = 85;

			}
			break;
		case 3:
			if (colUp.getNullCount() == 0 && colDown.getNullCount() == 0) {

				powerValue = 0;

			} else if ((colUp.getNullCount() == 1 && colDown.getNullCount() == 0)
					|| (colUp.getNullCount() == 0 && colDown.getNullCount() == 1)) {

				powerValue = 341;

			} else {

				powerValue = 1365;

			}
			break;
		case 4:
			if (colUp.getNullCount() == 0 && colDown.getNullCount() == 0) {

				powerValue = 0;

			} else if ((colUp.getNullCount() == 1 && colDown.getNullCount() == 0)
					|| (colUp.getNullCount() == 0 && colDown.getNullCount() == 1)) {

				powerValue = 5461;

			} else {

				powerValue = 21845;

			}
			break;
		default:
			powerValue = 87381;
			break;

		}
		return powerValue;

	}

	public NullAndCount RightBiasDownPowerValue( int row,
			int col, String color) {
		String[][] allChesses = chessboard.getBoard();
		int count = 0;
		NullAndCount nullAndCount = new NullAndCount();
		int i = row;
		int j = col;
		while (i < allChesses.length - 1 && j < allChesses.length - 1) {

			if (allChesses[i + 1][j + 1] == color) {

				count++;
				i = i + 1;
				j = j + 1;

			} else {

				if (allChesses[i + 1][j + 1] == "十") {

					nullAndCount.setNullCount(1);

				}
				break;

			}

		}
		nullAndCount.setChessCount(count);
		return nullAndCount;

	}

	public NullAndCount RightBiasUpPowerValue( int row,
			int col, String color) {
		String[][] allChesses = chessboard.getBoard();
		int count = 0;
		NullAndCount nullAndCount = new NullAndCount();
		int i = row;
		int j = col;
		while (i > 0 && j > 0) {

			if (allChesses[i - 1][j - 1] == color) {

				count++;
				i = i - 1;
				j = j - 1;

			} else {

				if (allChesses[i - 1][j - 1] == "十") {

					nullAndCount.setNullCount(1);

				}
				break;

			}

		}
		nullAndCount.setChessCount(count);
		return nullAndCount;

	}

	public long ColPowerValue( int row, int col,
			String color) {
		NullAndCount colUp = ColUpPowerValue( row, col, color);
		NullAndCount colDown = ColDownPowerValue( row, col, color);
		long powerValue;
		switch (colUp.getChessCount() + colDown.getChessCount() + 1) {

		case 1:
			if (colUp.getNullCount() == 0 && colDown.getNullCount() == 0) {

				powerValue = 0;

			} else if ((colUp.getNullCount() == 1 && colDown.getNullCount() == 0)
					|| (colUp.getNullCount() == 0 && colDown.getNullCount() == 1)) {

				powerValue = 1;

			} else {

				powerValue = 5;

			}
			break;
		case 2:
			if (colUp.getNullCount() == 0 && colDown.getNullCount() == 0) {

				powerValue = 0;

			} else if ((colUp.getNullCount() == 1 && colDown.getNullCount() == 0)
					|| (colUp.getNullCount() == 0 && colDown.getNullCount() == 1)) {

				powerValue = 21;

			} else {

				powerValue = 85;

			}
			break;
		case 3:
			if (colUp.getNullCount() == 0 && colDown.getNullCount() == 0) {

				powerValue = 0;

			} else if ((colUp.getNullCount() == 1 && colDown.getNullCount() == 0)
					|| (colUp.getNullCount() == 0 && colDown.getNullCount() == 1)) {

				powerValue = 341;

			} else {

				powerValue = 1365;

			}
			break;
		case 4:
			if (colUp.getNullCount() == 0 && colDown.getNullCount() == 0) {

				powerValue = 0;

			} else if ((colUp.getNullCount() == 1 && colDown.getNullCount() == 0)
					|| (colUp.getNullCount() == 0 && colDown.getNullCount() == 1)) {

				powerValue = 5461;

			} else {

				powerValue = 21845;

			}
			break;
		default:
			powerValue = 87381;
			break;

		}
		return powerValue;

	}

	public NullAndCount ColDownPowerValue( int row,
			int col, String color) {
		String[][] allChesses = chessboard.getBoard();
		int count = 0;
		NullAndCount nullAndCount = new NullAndCount();
		for (int i = row; i < allChesses.length - 1; i++) {

			if (allChesses[i + 1][col] == color) {

				count++;

			} else {

				if (allChesses[i + 1][col] == null) {

					nullAndCount.setNullCount(1);

				}
				break;

			}

		}
		nullAndCount.setChessCount(count);
		return nullAndCount;

	}

	public NullAndCount ColUpPowerValue( int row, int col,
			String color) {
		String[][] allChesses = chessboard.getBoard();
		int count = 0;
		NullAndCount nullAndCount = new NullAndCount();
		for (int i = row; i > 0; i--) {

			if (allChesses[i - 1][col] == color) {

				count++;

			} else {

				if (allChesses[i - 1][col] == "十") {

					nullAndCount.setNullCount(1);

				}
				break;

			}

		}
		nullAndCount.setChessCount(count);
		return nullAndCount;

	}

	public long RowPowerValue( int row, int col,
			String color) {
		NullAndCount rightRow = RightRowPowerValue( row, col, color);
		NullAndCount leftRow = LeftRowPowerValue( row, col, color);
		long powerValue;
		switch (rightRow.getChessCount() + leftRow.getChessCount() + 1) {

		case 1:
			if (rightRow.getNullCount() == 0 && leftRow.getNullCount() == 0) {

				powerValue = 0;

			} else if ((rightRow.getNullCount() == 1 && leftRow.getNullCount() == 0)
					|| (rightRow.getNullCount() == 0 && leftRow.getNullCount() == 1)) {

				powerValue = 1;

			} else {

				powerValue = 5;

			}
			break;
		case 2:
			if (rightRow.getNullCount() == 0 && leftRow.getNullCount() == 0) {

				powerValue = 0;

			} else if ((rightRow.getNullCount() == 1 && leftRow.getNullCount() == 0)
					|| (rightRow.getNullCount() == 0 && leftRow.getNullCount() == 1)) {

				powerValue = 21;

			} else {

				powerValue = 85;

			}
			break;
		case 3:
			if (rightRow.getNullCount() == 0 && leftRow.getNullCount() == 0) {

				powerValue = 0;

			} else if ((rightRow.getNullCount() == 1 && leftRow.getNullCount() == 0)
					|| (rightRow.getNullCount() == 0 && leftRow.getNullCount() == 1)) {

				powerValue = 341;

			} else {

				powerValue = 1365;

			}
			break;
		case 4:
			if (rightRow.getNullCount() == 0 && leftRow.getNullCount() == 0) {

				powerValue = 0;

			} else if ((rightRow.getNullCount() == 1 && leftRow.getNullCount() == 0)
					|| (rightRow.getNullCount() == 0 && leftRow.getNullCount() == 1)) {

				powerValue = 5461;

			} else {

				powerValue = 21845;

			}
			break;
		default:
			powerValue = 87381;
			break;

		}
		return powerValue;

	}

	public NullAndCount LeftRowPowerValue( int row,
			int col, String color) {
		String[][] allChesses = chessboard.getBoard();
		int count = 0;
		NullAndCount nullAndCount = new NullAndCount();
		for (int i = col; i > 0; i--) {

			if (allChesses[row][i - 1] == color) {

				count++;

			} else {

				if (allChesses[row][i - 1] == "十") {

					nullAndCount.setNullCount(1);

				}
				break;

			}

		}
		nullAndCount.setChessCount(count);
		return nullAndCount;

	}

	public NullAndCount RightRowPowerValue( int row,
			int col, String color) {
		String[][] allChesses = chessboard.getBoard();
		int count = 0;
		NullAndCount nullAndCount = new NullAndCount();
		for (int i = col; i < allChesses.length - 1; i++) {

			if (allChesses[row][i + 1] == color) {

				count++;

			} else {

				if (allChesses[row][i + 1] == "十") {

					nullAndCount.setNullCount(1);

				}
				break;

			}

		}
		nullAndCount.setChessCount(count);
		return nullAndCount;

	}
}

