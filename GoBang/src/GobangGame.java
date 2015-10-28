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
	 *      
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
				// 计算机随机选择位置坐标，即电脑下棋
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],
						chessman);
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

		int x = 1;
		int y = 1;
		long weight = 0;// 初始化权值
		long defenceWeight = 0;// 初始化进攻权值，用于与防守权值进行比较
		String[][] board = chessboard.getBoard();// 获取棋盘
		int row = 1;
		int col = 1;// 初始化防守位置的坐标
		int defencerow = 1;
		int defencecol = 1;// 初始化防守位置坐标，尽可能阻止黑子形成连子
		boolean isFirst = true;// 是否是第一次
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == "十") {
					// 对没有落子的位置进行扫描
					if (isFirst == true) {
						row = i;
						col = j;
						weight = RowWeight(i, j, "○") + ColWeight(i, j, "○")
								+ LeftUtoDWeight(i, j, "o")
								+ LeftDtoUWeight(i, j, "o");// 计算当前位置的进攻权值
						isFirst = false;
					} else {
						long nowWeight = 0;
						nowWeight = RowWeight(i, j, "○") + ColWeight(i, j, "○")
								+ LeftUtoDWeight(i, j, "o")
								+ LeftDtoUWeight(i, j, "o");
						if (nowWeight > weight) {
							row = i;
							col = j;
							weight = nowWeight;
						}
					}
				}
			}
		}
		isFirst = true;// 判断黑子的位置，即计算防守权值，堵住黑子的位置
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == "十") {
					if (isFirst == true) {
						defencerow = i;
						defencecol = j;
						defenceWeight = RowWeight(i, j, "●")
								+ ColWeight(i, j, "●")
								+ LeftUtoDWeight(i, j, "●")
								+ LeftDtoUWeight(i, j, "●");
						isFirst = false;
					} else {
						long nowWeight;
						nowWeight = RowWeight(i, j, "●") + ColWeight(i, j, "●")
								+ LeftUtoDWeight(i, j, "●")
								+ LeftDtoUWeight(i, j, "●");
						if (nowWeight > defenceWeight) {
							defencerow = i;
							defencecol = j;
							defenceWeight = nowWeight;
						}
					}
				}
			}
		}
		if (weight >= defenceWeight) {
			x = row;
			y = col;
		} else {
			x = defencerow;
			y = defencecol;
		}
		// int posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		// int posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		// String[][] board = chessboard.getBoard();
		// while (board[posX][posY] != "十") {
		// posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		// posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		// }
		int[] result = { x, y };
		return result;
	}

	public long ColWeight(int row, int col, String chesscolor) {
		long weight;
		NullAndCount upCol = UpColWeight(row, col, chesscolor);
		NullAndCount downCol = DownColWeight(row, col, chesscolor);
		switch (upCol.getChessCount() + downCol.getChessCount() + 1) {
		case 1:
			if (upCol.getNullCount() == 0 && downCol.getNullCount() == 0) {
				weight = 0;
			} else if ((upCol.getNullCount() == 1 && downCol.getNullCount() == 0)
					|| (upCol.getNullCount() == 0 && downCol.getNullCount() == 1)) {
				weight = 1;
			} else {
				weight = 5;
			}
			break;
		case 2:
			if (upCol.getNullCount() == 0 && downCol.getNullCount() == 0) {
				weight = 0;
			} else if ((upCol.getNullCount() == 1 && downCol.getNullCount() == 0)
					|| (upCol.getNullCount() == 0 && downCol.getNullCount() == 1)) {
				weight = 21;
			} else {
				weight = 85;
			}
			break;
		case 3:
			if (upCol.getNullCount() == 0 && downCol.getNullCount() == 0) {
				weight = 0;
			} else if ((upCol.getNullCount() == 1 && downCol.getNullCount() == 0)
					|| (upCol.getNullCount() == 0 && downCol.getNullCount() == 1)) {
				weight = 341;
			} else {
				weight = 1365;
			}
			break;
		case 4:
			if (upCol.getNullCount() == 0 && downCol.getNullCount() == 0) {
				weight = 0;
			} else if ((upCol.getNullCount() == 1 && downCol.getNullCount() == 0)
					|| (upCol.getNullCount() == 0 && downCol.getNullCount() == 1)) {
				weight = 5461;
			} else {
				weight = 21845;
			}
			break;
		default:
			weight = 87381;
			break;
		}
		return weight;
	}

	public NullAndCount UpColWeight(int row, int col, String chesscolor) {
		String[][] board = chessboard.getBoard();
		int count = 0;
		NullAndCount nullAndCount = new NullAndCount();
		for (int i = row; i > 0; i--) {
			if (board[i - 1][col] == chesscolor) {
				count++;
			} else {
				if (board[i - 1][col] == "十") {
					nullAndCount.setNullCount(1);
				}
				break;
			}
		}
		nullAndCount.setChessCount(count);
		return nullAndCount;
	}

	public NullAndCount DownColWeight(int row, int col, String chesscolor) {
		String[][] board = chessboard.getBoard();
		int count = 0;
		NullAndCount nullAndCount = new NullAndCount();
		for (int i = row; i < board.length - 1; i++) {
			if (board[i + 1][col] == chesscolor) {
				count++;
			} else {
				if (board[i + 1][col] == "十") {
					nullAndCount.setNullCount(1);
				}
				break;
			}
		}
		nullAndCount.setChessCount(count);
		return nullAndCount;
	}

	public long RowWeight(int row, int col, String chesscolor) {
		NullAndCount rightRow = rightRowWeight(row, col, chesscolor);
		NullAndCount leftRow = leftRowWeight(row, col, chesscolor);
		long weight = 0;
		switch (rightRow.getChessCount() + leftRow.getChessCount() + 1) {
		case 1:
			if (rightRow.getNullCount() == 0 && leftRow.getNullCount() == 0) {
				weight = 0;
			} else if ((rightRow.getNullCount() == 1 && leftRow.getNullCount() == 0)
					|| (rightRow.getNullCount() == 0 && leftRow
							.getNullCount() == 1)) {
				weight = 1;
			} else {
				weight = 5;
			}
			break;
		case 2:
			if (rightRow.getNullCount() == 0 && leftRow.getNullCount() == 0) {
				weight = 0;
			} else if ((rightRow.getNullCount() == 1 && leftRow.getNullCount() == 0)
					|| (rightRow.getNullCount() == 0 && leftRow
							.getNullCount() == 1)) {
				weight = 21;
			} else {
				weight = 85;
			}
			break;
		case 3:
			if (rightRow.getNullCount() == 0 && leftRow.getNullCount() == 0) {
				weight = 0;
			} else if ((rightRow.getNullCount() == 1 && leftRow.getNullCount() == 0)
					|| (rightRow.getNullCount() == 0 && leftRow
							.getNullCount() == 1)) {
				weight = 341;
			} else {
				weight = 1365;
			}
			break;
		case 4:
			if (rightRow.getNullCount() == 0 && leftRow.getNullCount() == 0) {
				weight = 0;
			} else if ((rightRow.getNullCount() == 1 && leftRow.getNullCount() == 0)
					|| (rightRow.getNullCount() == 0 && leftRow
							.getNullCount() == 1)) {
				weight = 5461;
			} else {
				weight = 21845;
			}
			break;
		default:
			weight = 87381;
		}
		return weight;
	}

	public NullAndCount rightRowWeight(int row, int col, String chesscolor) {
		NullAndCount nullAndCount = new NullAndCount();
		String[][] board = chessboard.getBoard();
		int count = 0;
		for (int i = col; i < board.length - 1; i++) {
			if (board[row][i + 1] == chesscolor) {
				count = count + 1;
			} else {
				if (board[row][i + 1] == "十") {
					nullAndCount.setNullCount(1);
				}
				
				break;
			}
			
		}
		nullAndCount.setChessCount(count);
		return nullAndCount;
	}

	public NullAndCount leftRowWeight(int row, int col, String chesscolor) {
		NullAndCount nullAndCount = new NullAndCount();
		String[][] board = chessboard.getBoard();
		int count = 0;
		for (int i = col; i > 0; i--) {
			if (board[row][i - 1] == chesscolor) {
				count = count + 1;
			} else {
				if (board[row][i - 1] == "十") {
				nullAndCount.setNullCount(1);
				}
				break;
			}
			
		}
		nullAndCount.setChessCount(count);
		return nullAndCount;
	}

	public long LeftDtoUWeight(int row, int col, String chesscolor) {
		long weight;
		NullAndCount leftline = LLeftDtoUWeight(row, col, chesscolor);
		NullAndCount rightline = RLeftDtoUWeight(row, col, chesscolor);
		switch (leftline.getChessCount() + rightline.getChessCount() + 1) {
		case 1:
			if (leftline.getNullCount() == 0 && rightline.getNullCount() == 0) {
				weight = 0;
			} else if ((leftline.getNullCount() == 0 && rightline
					.getNullCount() == 1)
					|| (leftline.getNullCount() == 1 && rightline
							.getNullCount() == 0)) {
				weight = 1;
			} else {
				weight = 5;
			}
			break;
		case 2:
			if (leftline.getNullCount() == 0 && rightline.getNullCount() == 0) {
				weight = 0;
			} else if ((leftline.getNullCount() == 0 && rightline
					.getNullCount() == 1)
					|| (leftline.getNullCount() == 1 && rightline
							.getNullCount() == 0)) {
				weight = 21;
			} else {
				weight = 85;
			}
			break;
		case 3:
			if (leftline.getNullCount() == 0 && rightline.getNullCount() == 0) {
				weight = 0;
			} else if ((leftline.getNullCount() == 0 && rightline
					.getNullCount() == 1)
					|| (leftline.getNullCount() == 1 && rightline
							.getNullCount() == 0)) {
				weight = 341;
			} else {
				weight = 1365;
			}
			break;
		case 4:
			if (leftline.getNullCount() == 0 && rightline.getNullCount() == 0) {
				weight = 0;
			} else if ((leftline.getNullCount() == 0 && rightline
					.getNullCount() == 1)
					|| (leftline.getNullCount() == 1 && rightline
							.getNullCount() == 0)) {
				weight = 5461;
			} else {
				weight = 21845;
			}
			break;
		default:
			weight = 87381;
			break;
		}
		return weight;
	}

	public NullAndCount LLeftDtoUWeight(int row, int col, String chesscolor) {
		NullAndCount nullAndCount = new NullAndCount();
		int count = 0;
		String[][] board = chessboard.getBoard();
		int i = row;
		int j = col;
		while (i < board.length - 1 && j > 0) {
			if (board[i + 1][j - 1] == chesscolor) {
				count++;
				i = i + 1;
				j = j - 1;
			} else {
				if (board[i + 1][j - 1] == "十") {
				nullAndCount.setNullCount(1);
				}
				break;
			}
			
		}
		nullAndCount.setChessCount(count);
		return nullAndCount;
	}

	public NullAndCount RLeftDtoUWeight(int row, int col, String chesscolor) {
		NullAndCount nullAndCount = new NullAndCount();
		int count = 0;
		String[][] board = chessboard.getBoard();
		int i = row;
		int j = col;
		while (i > 0 && j < board.length-1) {
			if (board[i - 1][j + 1] == chesscolor) {
				count++;
				i = i - 1;
				j = j + 1;
			} else {
				if (board[i - 1][j + 1] == "十") {
				nullAndCount.setNullCount(1);
				}
				break;
			}
			
		}
		nullAndCount.setChessCount(count);
		return nullAndCount;
	}

	public long LeftUtoDWeight(int row, int col, String chesscolor) {
		long weight;
		NullAndCount leftline = new NullAndCount();
		NullAndCount rightline = new NullAndCount();
		// String[][] board=chessboard.getBoard();
		switch (leftline.getChessCount() + rightline.getChessCount() + 1) {
		case 1:
			if (leftline.getNullCount() == 0 && rightline.getNullCount() == 0) {
				weight = 0;
			} else if ((leftline.getNullCount() == 0 && rightline
					.getNullCount() == 1)
					|| (leftline.getNullCount() == 1 && rightline
							.getNullCount() == 0)) {
				weight = 1;
			} else {
				weight = 5;
			}
			break;
		case 2:
			if (leftline.getNullCount() == 0 && rightline.getNullCount() == 0) {
				weight = 0;
			} else if ((leftline.getNullCount() == 0 && rightline
					.getNullCount() == 1)
					|| (leftline.getNullCount() == 1 && rightline
							.getNullCount() == 0)) {
				weight = 21;
			} else {
				weight = 85;
			}
			break;
		case 3:
			if (leftline.getNullCount() == 0 && rightline.getNullCount() == 0) {
				weight = 0;
			} else if ((leftline.getNullCount() == 0 && rightline
					.getNullCount() == 1)
					|| (leftline.getNullCount() == 1 && rightline
							.getNullCount() == 0)) {
				weight = 341;
			} else {
				weight = 1365;
			}
			break;
		case 4:
			if (leftline.getNullCount() == 0 && rightline.getNullCount() == 0) {
				weight = 0;
			} else if ((leftline.getNullCount() == 0 && rightline
					.getNullCount() == 1)
					|| (leftline.getNullCount() == 1 && rightline
							.getNullCount() == 0)) {
				weight = 5461;
			} else {
				weight = 21845;
			}
			break;
		default:
			weight = 87381;
			break;
		}
		return weight;
	}

	public NullAndCount LLeftUtoDWeight(int row, int col, String chesscolor) {
		NullAndCount nullAndCount = new NullAndCount();
		String[][] board = chessboard.getBoard();
		int count = 0;
		int i = row;
		int j = col;
		while (i > 0 && j > 0) {
			if (board[i - 1][j - 1] == chesscolor) {
				count = count + 1;
				i--;
				j--;
			} else {
				if (board[i - 1][j - 1] == "十") {
				nullAndCount.setNullCount(1);
				}
				break;
			}
		}
		nullAndCount.setChessCount(count);
		return nullAndCount;
	}

	public NullAndCount RLeftUtoDWeight(int row, int col, String chesscolor) {
		NullAndCount nullAndCount = new NullAndCount();
		int count = 0;
		String[][] board = chessboard.getBoard();
		int x = row;
		int y = col;
		while (x < board.length && y < board.length) {
			if (board[x + 1][y + 1] == chesscolor) {
				count = count + 1;
				x++;
				y++;
			} else{ 
				if (board[x + 1][y + 1] == "十") {
				nullAndCount.setNullCount(1);
				}
				break;
			}
		}
		nullAndCount.setChessCount(count);
		return nullAndCount;
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
		int startPosX, startPosY;// 定义X，Y在直线上的起点坐标
		int endPosX, endPosY;// 定义X，Y在直线上的终点坐标
		int count = 0;// 定义连续棋子数
		int temp;
		// 给X的起点坐标赋值
		temp = posX - WIN_COUNT + 1;
		if (temp >= 0)
			startPosX = temp;
		else
			startPosX = 0;
		// 给X的终点坐标赋值
		temp = posX + WIN_COUNT - 1;
		if (temp <= 21)// 因为棋盘的数组范围是0到21；
			endPosX = temp;
		else
			endPosX = 21;
		// 给Y的起点坐标赋值
		temp = posY - WIN_COUNT + 1;
		if (temp >= 0)
			startPosY = temp;
		else
			startPosY = 0;
		// 给Y的终点坐标赋值
		temp = posY + WIN_COUNT - 1;
		if (temp <= 21)
			endPosY = temp;
		else
			endPosY = 21;
		// 判断从左至右的直线方向
		String[][] board = chessboard.getBoard();
		for (int i = startPosY; i < endPosY; i++) {
			if (board[posX][i] == ico && board[posX][i + 1] == ico) {
				count++;
			} else if (count != 4)
				count = 0;
		}
		// 判断从上到下的直线方向
		if (count == 0) {
			for (int i = startPosX; i < endPosX; i++) {
				if (board[i][posY] == ico && board[i + 1][posY] == ico)
					count++;
				else if (count != 4)
					count = 0;
			}

		}
		// 判断左上至右下方向的直线方向
		if (count == 0) {
			int j = startPosY;
			for (int i = startPosX; i < endPosX; i++) {
				if (j < endPosY) {
					if (board[i][j] == ico && board[i + 1][j + 1] == ico)
						count++;
					else if (count != 4)
						count = 0;
				}
				j = j + 1;
			}

		}
		// 判断左下至右上方向的直线方向
		if (count == 0) {
			int j = startPosY;
			for (int i = endPosX; i > startPosX; i--) {
				if (j < endPosY) {
					if (board[i][j] == ico && board[i - 1][j + 1] == ico)
						count++;
					else if (count != 4)
						count = 0;
				}
				j = j + 1;
			}

		}
		if (count == 4)
			return true;
		else
			return false;
	}

	public static void main(String[] args) throws Exception {

		GobangGame gb = new GobangGame(new Chessboard());
		gb.start();
	}
}
