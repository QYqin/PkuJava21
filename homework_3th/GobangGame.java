package newstar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Stack;


 
public class GobangGame {
	// ����ﵽӮ������������Ŀ
	private final int WIN_COUNT = 5;
	// �����û������X����
	private int posX = 0;
	// �����û������X����
	private int posY = 0;
	// ��������
	private Chessboard chessboard;
	Stack<Integer> stack =new Stack<Integer>();
	/**
	 * �չ�����
	 */
	public GobangGame() {
	}

	/**
	 * ����������ʼ�����̺���������
	 * 
	 * @param chessboard
	 *            ������
	 */
	public GobangGame(Chessboard chessboard) {
		this.chessboard = chessboard;
	}

	/**
	 * ��������Ƿ�Ϸ���
	 * 
	 * @param inputStr
	 *            �ɿ���̨������ַ�����
	 * @return �ַ����Ϸ�����true,���򷵻�false��
	 */
	public boolean isValid(String inputStr) {
		// ���û�������ַ����Զ���(,)��Ϊ�ָ����ָ��������ַ���
		String[] posStrArr = inputStr.split(",");
		try {
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("����(����,����)�ĸ�ʽ���룺");
			System.out.println(chessboard.getBoard().length);
			return false;
		}
		// ���������ֵ�Ƿ��ڷ�Χ֮��
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			chessboard.printBoard();
			System.out.println("X��Y����ֻ�ܴ��ڵ���1,��С�ڵ���" + Chessboard.BOARD_SIZE
					+ ",���������룺");
			return false;
		}
		// ��������λ���Ƿ��Ѿ�������
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "ʮ") {
			chessboard.printBoard();
			System.out.println("��λ���Ѿ������ӣ����������룺");
			return false;
		}
		return true;
	}

	/**
	 * ��ʼ����
	 */
	public void start() throws Exception {
		// trueΪ��Ϸ����
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		// ��ȡ���̵�����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine:ÿ����������һ�����ݰ��س���������������ݱ�br��ȡ��
		while ((inputStr = br.readLine()) != null) {
			isOver = false;
			if (!isValid(inputStr)) {
				// ������Ϸ���Ҫ���������룬�ټ���
				continue;
			}
			// �Ѷ�Ӧ������Ԫ�ظ�Ϊ"��"
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			// �ж��û��Ƿ�Ӯ��
			if (isWon(posX, posY, chessman,6)) {
				isOver = true;

			} else {
				// ��������ѡ��λ������
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],
						chessman);
				// �жϼ�����Ƿ�Ӯ��
				if (isWon(computerPosArr[0], computerPosArr[1], chessman,6)) {
					isOver = true;
				}
			}
			// �������ʤ�ߣ�ѯ���û��Ƿ������Ϸ
			if (isOver) {
				// ������������³�ʼ�����̣�������Ϸ
				if (isReplay(chessman)) {
					chessboard.initBoard();
					chessboard.printBoard();
					continue;
				}
				// ������������˳�����
				break;
			}
			chessboard.printBoard();
		}
	}

	/**
	 * �Ƿ����¿�ʼ���塣
	 * 
	 * @param chessman
	 *            "��"Ϊ�û���"��"Ϊ�������
	 * @return ��ʼ����true�����򷵻�false��
	 */
	public boolean isReplay(String chessman) throws Exception {
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "��ϲ������Ӯ�ˣ�"
				: "���ź��������ˣ�";
		System.out.println(message + "����һ�֣�(y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (br.readLine().equals("y")) {
			// ��ʼ��һ��
			return true;
		}
		return false;

	}

	/**
	 * ������������
	 */
	public int[] computerDo() {

		
		
		
		// ������ai
		String[][] allChesses = chessboard.getBoard();
		long powerValue = 0;// ��ʼ������Ȩֵ
		int X =1;
		int Y =1;
		int row = 1;// ��ʼ������λ���к���
		int col = 1;
		long attackPowerValue = 0;// ��ʼ������Ȩֵ
		int attactRow = 0;// ��ʼ������λ���к���
		int attactCol = 0;
		boolean isFirst = true;// �Ƿ��ǵ�һ�Σ���ʼ��Ϊ��
		for (int i = 0; i < allChesses.length; i++) {
			// ͨ������õ����ص����λ��,��Ϊ�Ƿ�������ͳ�ư�ɫ
			for (int j = 0; j < allChesses.length; j++) {

				if (allChesses[i][j] == "ʮ") {
					// �Ի�û���ߵĿ�������ͳ��
					if (isFirst == true) {
						// ��һ��Ϊ�յĿո�
						row = i;// �������е�λ��
						col = j;
						powerValue = RowPowerValue( i, j,
								"��")
								+ ColPowerValue(i, j, "��")
								+ RightBiasPowerValue( i, j,
										"��")
								+ LeftBiasPowerValue( i, j,
										"��");// ���㵱ǰλ�õ�Ȩֵ
						isFirst = false;// ��ʾ��һ��λ�ü������

					} else {

						long nowPowerValue;// ���޼�����ѷ���λ��
						nowPowerValue = RowPowerValue(i, j,
								"��")
								+ ColPowerValue( i, j, "��")
								+ RightBiasPowerValue( i, j,
										"��")
								+ LeftBiasPowerValue( i, j,
										"��");
						if (nowPowerValue > powerValue) {

							row = i;
							col = j;
							powerValue = nowPowerValue;

						}

					}

				}

			}

		}

		isFirst = true;// ��ʼ��isFirstΪtrue
		for (int i = 0; i < allChesses.length; i++) {

			for (int j = 0; j < allChesses.length; j++) {

				if (allChesses[i][j] == "ʮ") {
					// ���������Ȩֵ
					if (isFirst == true) {
						// ��һ�������,ִֻ��һ��
						attactRow = i;// ��һ���������к���
						attactCol = j;
						attackPowerValue = RowPowerValue( i, j,
								"��")
								+ ColPowerValue( i, j, "��")
								+ RightBiasPowerValue(i, j,
										"��")
								+ LeftBiasPowerValue( i, j,
										"��");// �����һ��������Ȩֵ
						isFirst = false;

					} else {

						long nowPowerValue;// ���޼�����ѽ���λ��
						nowPowerValue = RowPowerValue( i, j,
								"��")
								+ ColPowerValue(i, j, "��")
								+ RightBiasPowerValue( i, j,
										"��")
								+ LeftBiasPowerValue( i, j,
										"��");
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
//			// ����������ߵ�һ���壬�Ͳ��ü����������һ����
//			Random random = new Random();
//			X = random.nextInt(allChesses.length);
//			X = random.nextInt(allChesses.length);
//
//		} else{
			// �����ȷ���Ƿ��ػ��ǽ���
			if (attackPowerValue >= powerValue) {
				// �ȽϷ��غͽ���Ȩֵ��ȷ�����ػ��ǽ���
				X = attactRow;
				Y = attactCol;

			} else {

				X = row;
				Y = col;

			}
//		}
//			stack.add(X);// ��������Ϣѹ��ջ��

		
		int[] result = { X, Y };
		return result;
	}

	/**
	 * �ж���Ӯ
	 * 
	 * @param posX
	 *            ���ӵ�X���ꡣ
	 * @param posY
	 *            ���ӵ�Y����
	 * @param ico
	 *            ��������
	 * @return ��������������������һ��ֱ�ӣ������棬�����෴��
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

				if (allChesses[i + 1][j - 1] == "ʮ") {

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

				if (allChesses[i - 1][j + 1] == "ʮ") {

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

				if (allChesses[i + 1][j + 1] == "ʮ") {

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

				if (allChesses[i - 1][j - 1] == "ʮ") {

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

				if (allChesses[i - 1][col] == "ʮ") {

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

				if (allChesses[row][i - 1] == "ʮ") {

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

				if (allChesses[row][i + 1] == "ʮ") {

					nullAndCount.setNullCount(1);

				}
				break;

			}

		}
		nullAndCount.setChessCount(count);
		return nullAndCount;

	}
}

