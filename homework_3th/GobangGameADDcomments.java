import java.io.BufferedReader;
import java.io.InputStreamReader;

 
public class GobangGame {
	// ����ﵽӮ������������Ŀ
	private final int WIN_COUNT = 5;
	// �����û������X����
	private int posX = 0;
	// �����û������X����
	private int posY = 0;
	// ��������
	private Chessboard chessboard;
	// �ĸ����򣬺�- ����| ����б/ ����б\  
    private static final int CROSS = 0;  
    private static final int VERT = 1;  
    private static final int RIGHT = 3;  
    private static final int LIFT = 2; 
    private int BLACKSIGN = -1;  
    private int WHITESIGN = -1;  
    private int WHITESIGN_THREE = -1; 
    private int WHITESIGN_FOUR= -1; 
    private int SIGN = -1; 
  //���ȱ������̣��ҵ���Һ͵����¹������������������������ء�
  		//����ڰ����ֵ����������������
    private	int BlackMaxCount=0;
    private	int WhiteMaxCount=0;
	/*�洢����������ӵ�λ��
	* ������ĸ�������������������ĸ����ӵ���������
	* ���������������������������������ӵ���������
	* �����������������������������
	* ������������������ߵ�λ�����壬�������������Χ���塣
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
			if (isWon(posX, posY, chessman)) {
				isOver = true;

			} else {
				// ��������ѡ��λ������
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],
						chessman);
				System.out.println("computerPosArr[0]"+computerPosArr[0]);
				System.out.println("computerPosArr[1]"+computerPosArr[1]);
				
				// �жϼ�����Ƿ�Ӯ��
				if (isWon(computerPosArr[0], computerPosArr[1], chessman)) {
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
			//System.out.println("����������������꣬Ӧ��x,y�ĸ�ʽ���룺");
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
		String[][] board = chessboard.getBoard();
//		ComputerDoAi(board);
		int[] result =new int[2];
		result[0]=-1;
		result[1]=-1;
		//Ϊ���ж��Ƿ�����ɹ�
		//����x��y����
		int posx=0;
		int posy=0;
		//String chessman = Chessman.WHITE.getChessman();
		//String[][] board = chessboard.getBoard();
		//String chessman = ico;
		/*
		 * ���Ҽ������������������
		 * 
		*/
		for(posy=0;posy<Chessboard.BOARD_SIZE;posy++){
			for(posx=0;posx<Chessboard.BOARD_SIZE;posx++)
			{
				//��������������ӣ�������������������Ͷ�Ӧ����������
				if(board[posx][posy]==Chessman.BLACK.getChessman()){
					int[] MaxCountArr = SearchMaxCount(posx, posy, Chessman.BLACK.getChessman());
					//Ѱ�Һ���Ͱ���������������������¼λ��
					if(BlackMaxCount<=MaxCountArr[0]){
						BlackMaxCount=MaxCountArr[0];
						BLACKSIGN =MaxCountArr[1];
						//��־λ����ʾ������󳤶ȵ�λ���Ǻᡢ����б
						BlackMaxlistX=posx;
						BlackMaxlistY=posy;
						//��¼��󳤶ȵ�����
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
 * �������
 * 1.����������ĸ���������һ��ģ���ô����ֱ�������һ������
 * 2.����������ĸ��������ߵ�����²�������,���Ƿ����������ӵ������
 * 3.�������������Ȼ�������壬���������Χ���塣
 * 4.�ж�������ߵ����������Ƿ����ں��壬������ں��壬���ٺ�����������塣
 * 5.�������Ͱ����������ȣ����߰���������������ں��壬���ڰ�����������塣
 * 6.�������������޷����壬��������塣
 * 7.����һ��round���޶��������Ϊ���̵�һ�롣
 * 
*/
while(true)
{
	if(WHITESIGN_FOUR>=0){
	/*
	* �жϼ���������������������ߵ������	
	*/		
	int[] MaxCountArr = SearchPosition(WhiteMaxlistX_Four,WhiteMaxlistY_Four,WHITESIGN_FOUR,Chessman.WHITE.getChessman());
	result[0]=MaxCountArr[0];
	result[1]=MaxCountArr[1];
	//�����������Ϊ-1�����λ�ò������壬������ѡ��
	if(MaxCountArr[0]<0||MaxCountArr[1]<0)
	{
		WHITESIGN_FOUR=-1;
		continue;
	}else{
		return result;
	}
	}else if(WHITESIGN_THREE>=0){
	/*
	 * �жϼ���������������������ߵ������	
	*/
		int[] MaxCountArr = SearchPosition(WhiteMaxlistX_Three,WhiteMaxlistY_Three,WHITESIGN_THREE,Chessman.WHITE.getChessman());
		result[0]=MaxCountArr[0];
		result[1]=MaxCountArr[1];
		//�����������Ϊ-1�����λ�ò������壬������ѡ��
		if(MaxCountArr[0]<0||MaxCountArr[1]<0)
		{
			WHITESIGN_THREE=-1;
			continue;
		}else{
			return result;
		}
	}else if(WhiteMaxCount<BlackMaxCount&&BLACKSIGN>=0){
	/*
	 * �жϼ��������������С��������ߵ�������	
	*/
		int[] MaxCountArr = SearchPosition(BlackMaxlistX,BlackMaxlistY,BLACKSIGN,Chessman.BLACK.getChessman());
		result[0]=MaxCountArr[0];
		result[1]=MaxCountArr[1];	
		//�����������Ϊ-1�����λ�ò������壬������ѡ��
		if(MaxCountArr[0]<0||MaxCountArr[1]<0)
		{
			BLACKSIGN=-1;
			continue;
		}else{
			return result;
		}
	}else if(WHITESIGN==-1)
	/*
	* �жϼ����û�����ߵ������	
	* */
	{	
			int posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			int posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		while (board[posX][posY] != "ʮ") {
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
		 * �жϼ�������������̶�����ʧ�ܵ������	
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
//	while (board[posX][posY] != "ʮ") {
//		posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//		posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
//	}
//	int[] result = { posX, posY };
//	return result;
//	/**
//	 * ���������������
//	 * 
//	 * @param searchboard ��������
//	 * 
//	 * @return posX
//	 *            ��һ�������X���ꡣ
//	 *            
//	 * @return posY
//	 *            ��һ�������Y����
//	 */
//	public int[] ComputerDoAi(String[][] board)
//	//{
//		
//			//}		
//		//}
		
	/**
	 * ����������ߵ���������
	 * 
	 * @param SposX
	 *            ���ӵ�X���ꡣ
	 * @param SposY
	 *            ���ӵ�Y����
	 * @param Sico
	 *            ��������
	 * @return SMaxCount�����������
	 */
	public int[] SearchMaxCount(int Sposx,int Sposy,String Sico){
		//���ȱ������̣��ҵ���Һ͵����¹������������������������ء�
		//���������������
		int SMaxCount=0;
//		//�洢����������ӵ�λ��
//		int SBlackMaxlistX=0;
//		int SBlackMaxlistY=0;
//		int SWhiteMaxlistX=0;
//		int SWhiteMaxlistY=0;
		String[][] Sboard = chessboard.getBoard();
		//������б�������Ļ�������
		int count=0;
		int x_top=Sposx,y_top=Sposy;
		int x_bottom=Sposx,y_bottom=Sposy;
		//������б�߷����������ֵ
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
		//�ж���б�������������
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
		//�жϺ����Ƿ�����ʤ��
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
		//�ж�ͬһ���Ƿ�����ʤ��
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
//		//�ĸ���������һ��ĵ����
//			if(WHITESIGN_FOUR>=0){
//			/*
//			 * �жϼ�����������ĸ��������ߵ������	
//			*/
//				if(WHITESIGN_FOUR==0)
//				/*
//				 * �ж��ĸ��������ӵ�λ���Ƿ��ں�����
//				 */
//				{
//					int x_top=WhiteMaxlistX_Four,y_top=WhiteMaxlistY_Four;
//					int x_bottom=WhiteMaxlistX_Four,y_bottom=WhiteMaxlistY_Four;
//					while(--x_bottom>=0&&board[x_bottom][y_bottom]== Chessman.WHITE.getChessman());
//					if(x_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//					else{
//						x_top=WhiteMaxlistX_Four;y_top=WhiteMaxlistY_Four;
//						x_bottom=WhiteMaxlistX_Four;y_bottom=WhiteMaxlistY_Four;
//						while(++x_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]==Chessman.WHITE.getChessman());
//						if(++x_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="ʮ")
//						{
//							result[0]=x_top;
//							result[0]=y_top;
//							return result;
//						}
//					} 		 
//				}
//				else if(WHITESIGN_FOUR==1)
//				/*
//				 * �鿴�ĸ����ӵ������Ƿ�����ֱ����
//				 */
//				{
//					int x_top=WhiteMaxlistX_Four,y_top=WhiteMaxlistY_Four;
//					int x_bottom=WhiteMaxlistX_Four,y_bottom=WhiteMaxlistY_Four;
//					while(--y_bottom>=0&&board[x_bottom][y_bottom]== Chessman.WHITE.getChessman());
//					if(y_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//					else{
//						x_top=WhiteMaxlistX_Four;y_top=WhiteMaxlistY_Four;
//						x_bottom=WhiteMaxlistX_Four;y_bottom=WhiteMaxlistY_Four;
//						while(++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.WHITE.getChessman());;
//						if(++y_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="ʮ")
//						{
//							result[0]=x_top;
//							result[0]=y_top;
//							return result;
//						}
//					} 
//				}else if(WHITESIGN_FOUR==2)
//				/*
//				 * �鿴�ĸ����ӵ������Ƿ�����б���� \
//				 */
//				{			
//					int x_top=WhiteMaxlistX_Four,y_top=WhiteMaxlistY_Four;
//					int x_bottom=WhiteMaxlistX_Four,y_bottom=WhiteMaxlistY_Four;
//					while(--x_bottom>=0&&--y_bottom>=0&&board[x_bottom][y_bottom]== Chessman.WHITE.getChessman());
//					if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//					else{
//						x_top=WhiteMaxlistX_Four;y_top=WhiteMaxlistY_Four;
//						x_bottom=WhiteMaxlistX_Four;y_bottom=WhiteMaxlistY_Four;
//						while(++x_top<Chessboard.BOARD_SIZE&&++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.WHITE.getChessman());;
//					if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="ʮ")
//						{
//							result[0]=x_top;
//							result[0]=y_top;
//							return result;
//						}
//					} 
//								
//				}else if(WHITESIGN_FOUR==3)
//				/*
//				 * �鿴�ĸ���������Ƿ�����б����/
//				 */
//				{
//					int x_top=WhiteMaxlistX_Four,y_top=WhiteMaxlistY_Four;
//					int x_bottom=WhiteMaxlistX_Four,y_bottom=WhiteMaxlistY_Four;
//					while(--x_bottom>=0&&++y_bottom>=0&&board[x_bottom][y_bottom]== Chessman.WHITE.getChessman());
//					if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//					else{
//						x_top=WhiteMaxlistX_Four;y_top=WhiteMaxlistY_Four;
//						x_bottom=WhiteMaxlistX_Four;y_bottom=WhiteMaxlistY_Four;
//						while(++x_top<Chessboard.BOARD_SIZE&&--y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.WHITE.getChessman());;
//						if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="ʮ")
//						{
//						  result[0]=x_top;
//						  result[0]=y_top;
//						  return result;
//						}
//					}
//				}
//			}else if(WHITESIGN_THREE>=0){
//			/*
//			 * �жϼ���������������������ߵ������	
//			*/
//			if(WHITESIGN_THREE==0){
//				int x_top=WhiteMaxlistX_Three,y_top=WhiteMaxlistY_Three;
//				int x_bottom=WhiteMaxlistX_Three,y_bottom=WhiteMaxlistY_Three;
//				while(--x_bottom>=0&&board[x_bottom][y_bottom]== Chessman.WHITE.getChessman());
//				if(x_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
//				{
//					result[0]=x_bottom;
//					result[0]=y_bottom;
//					return result;
//				}
//				else{
//					x_top=WhiteMaxlistX_Three;y_top=WhiteMaxlistY_Three;
//					x_bottom=WhiteMaxlistX_Three;y_bottom=WhiteMaxlistY_Three;
//					while(++x_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]==Chessman.WHITE.getChessman());
//					if(++x_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="ʮ")
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
//				if(y_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
//				{
//					result[0]=x_bottom;
//					result[0]=y_bottom;
//					return result;
//				}
//				else{
//					x_top=WhiteMaxlistX_Three;y_top=WhiteMaxlistY_Three;
//					x_bottom=WhiteMaxlistX_Three;y_bottom=WhiteMaxlistY_Three;
//					while(++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.WHITE.getChessman());
//					if(++y_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="ʮ")
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
//					if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//					else{
//						x_top=WhiteMaxlistX_Three;y_top=WhiteMaxlistY_Three;
//						x_bottom=WhiteMaxlistX_Three;y_bottom=WhiteMaxlistY_Three;
//						while(++x_top<Chessboard.BOARD_SIZE&&++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.WHITE.getChessman());;
//						if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="ʮ")
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
//					if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//				else{
//						x_top=WhiteMaxlistX_Three;y_top=WhiteMaxlistY_Three;
//						x_bottom=WhiteMaxlistX_Three;y_bottom=WhiteMaxlistY_Three;
//						while(++x_top<Chessboard.BOARD_SIZE&&--y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.WHITE.getChessman());;
//						if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="ʮ")
//					{
//						result[0]=x_top;
//						result[0]=y_top;
//						return result;
//					}
//				} 
//			}				
//			}else if(WhiteMaxCount<BlackMaxCount){
//			/*
//			 * �жϼ��������������С��������ߵ�������	
//			*/
//				int[] MaxCountArr = SearchPosition(BlackMaxlistX,BlackMaxlistY,BLACKSIGN,Chessman.BLACK.getChessman());
//				result[0]=MaxCountArr[0];
//				result[1]=MaxCountArr[1];
////			if(BLACKSIGN==0){
////				int x_top=BlackMaxlistX,y_top=BlackMaxlistY;
////				int x_bottom=BlackMaxlistX,y_bottom=BlackMaxlistY;
////				while(--x_bottom>=0&&board[x_bottom][y_bottom]== Chessman.BLACK.getChessman());
////				if(x_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
////				{
////					result[0]=x_bottom;
////					result[1]=y_bottom;
////					return result;
////				}
////				else{
////					x_top=BlackMaxlistX;y_top=BlackMaxlistY;
////					x_bottom=BlackMaxlistX;y_bottom=BlackMaxlistY;
////					while(++x_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]==Chessman.BLACK.getChessman());
////					if(++x_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]=="ʮ")
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
////				if(y_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
////				{
////					result[0]=x_bottom;
////					result[1]=y_bottom;
////					return result;
////				}
////				else{
////					x_top=BlackMaxlistX;y_top=BlackMaxlistY;
////					x_bottom=BlackMaxlistX;y_bottom=BlackMaxlistY;
////					while(++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.BLACK.getChessman());;
////					if(++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]=="ʮ")
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
////					if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
////					{
////						result[0]=x_bottom;
////						result[1]=y_bottom;
////						return result;
////					}
////					else{
////						x_top=BlackMaxlistX;y_top=BlackMaxlistY;
////						x_bottom=BlackMaxlistX;y_bottom=BlackMaxlistY;
////						while(++x_top<Chessboard.BOARD_SIZE&&++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.BLACK.getChessman());
////						if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]=="ʮ")
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
////					if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
////					{
////						result[0]=x_bottom;
////						result[1]=y_bottom;
////						return result;
////					}
////					else{
////						x_top=BlackMaxlistX;y_top=BlackMaxlistY;
////						x_bottom=BlackMaxlistX;y_bottom=BlackMaxlistY;
////						while(++x_top<Chessboard.BOARD_SIZE&&--y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.BLACK.getChessman());;
////						if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]=="ʮ")
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
//					while (board[posX][posY] != "ʮ") {
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
//					if(x_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//					else{
//						x_top=WhiteMaxlistX;y_top=WhiteMaxlistY;
//						x_bottom=WhiteMaxlistX;y_bottom=WhiteMaxlistY;
//						while(++x_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]==Chessman.WHITE.getChessman());
//						if(++x_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="ʮ")
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
//					if(y_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//					else{
//						x_top=WhiteMaxlistX;y_top=WhiteMaxlistY;
//						x_bottom=WhiteMaxlistX;y_bottom=WhiteMaxlistY;
//						while(++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.WHITE.getChessman());;
//						if(++y_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="ʮ")
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
//					if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//					else{
//						x_top=WhiteMaxlistX;y_top=WhiteMaxlistY;
//						x_bottom=WhiteMaxlistX;y_bottom=WhiteMaxlistY;
//						while(++x_top<Chessboard.BOARD_SIZE&&++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.WHITE.getChessman());;
//						if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="ʮ")
//						{
//							result[0]=x_top;
//							result[0]=y_top;
//							return result;
//						}
//					} 
////								System.out.println(x_bottom);
////								System.out.println(y_bottom);
//					}else if(WHITESIGN==3){
//					//��б�����
//					int x_top=WhiteMaxlistX,y_top=WhiteMaxlistY;
//					int x_bottom=WhiteMaxlistX,y_bottom=WhiteMaxlistY;
//					while(--x_bottom>=0&&++y_bottom>=0&&board[x_bottom][y_bottom]== Chessman.WHITE.getChessman());
//					if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
//					{
//						result[0]=x_bottom;
//						result[0]=y_bottom;
//						return result;
//					}
//					else{
//						x_top=WhiteMaxlistX;y_top=WhiteMaxlistY;
//						x_bottom=WhiteMaxlistX;y_bottom=WhiteMaxlistY;
//						while(++x_top<Chessboard.BOARD_SIZE&&--y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Chessman.WHITE.getChessman());;
//						if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_bottom][y_bottom]=="ʮ")
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
	 * �ж�����λ��
	 * 
	 * @param Psoitx
	 *            ���ӵ�X���ꡣ
	 * @param PsoitY
	 *            ���ӵ�Y����
	 * @param Psoitico
	 *            ��������
	 * @param POSITSIGN
	 *            ������߷���
	 * @return ����λ������x��y��
	 */
	public int[] SearchPosition(int Psoitx,int Psoity,int POSITSIGN,String Psoitico){
		int[] result =new int[3];
		String[][] board = chessboard.getBoard();
		if(POSITSIGN==0){
			int x_top=Psoitx,y_top=Psoity;
			int x_bottom=Psoitx,y_bottom=Psoity;
			while(--x_bottom>=0&&board[x_bottom][y_bottom]== Psoitico);
			if(x_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
			{
				result[0]=x_bottom;
				result[1]=y_bottom;
				return result;
			}
			else{
				x_top=Psoitx;y_top=Psoity;
				x_bottom=Psoitx;y_bottom=Psoity;
				while(++x_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]==Psoitico);
				if(++x_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]=="ʮ")
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
			if(y_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
			{
				result[0]=x_bottom;
				result[1]=y_bottom;
				return result;
			}
			else{
				x_top=Psoitx;y_top=Psoity;
				x_bottom=Psoitx;y_bottom=Psoity;
				while(++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]== Psoitico);;
				if(++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]=="ʮ")
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
				if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
				{
					result[0]=x_bottom;
					result[1]=y_bottom;
					return result;
				}
				else{
					x_top=Psoitx;y_top=Psoity;
					x_bottom=Psoitx;y_bottom=Psoity;
					while(++x_top<Chessboard.BOARD_SIZE&&++y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]==Psoitico);
					if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]=="ʮ")
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
				if(x_bottom>=0&&y_bottom>=0&&board[x_bottom][y_bottom]=="ʮ")
				{
					result[0]=x_bottom;
					result[1]=y_bottom;
					return result;
				}
				else{
					x_top=Psoitx;y_top=Psoity;
					x_bottom=Psoitx;y_bottom=Psoity;
					while(++x_top<Chessboard.BOARD_SIZE&&--y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]==Psoitico);;
					if(x_top<Chessboard.BOARD_SIZE&&y_top<Chessboard.BOARD_SIZE&&board[x_top][y_top]=="ʮ")
					{
						result[0]=x_top;
						result[1]=y_top;
						return result;
					}
				} 
			}
		//����ڴ�λ�����˶����岻�ɹ�������Ϊ-1������Ѱ������ 
		result[0]=-1;
		result[1]=-1;
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
	public boolean isWon(int posX, int posY, String ico) {
		String[][] board = chessboard.getBoard();
		//String chessman = ico;
		int count=0;
		//����x,y���Ϸ���������·�������
		int x_top=posX,y_top=posY;
		int x_bottom=posX,y_bottom=posY;
		//�ж���б���Ƿ�����ʤ��
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
		//�ж���б���Ƿ�����ʤ��
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
		//�жϺ����Ƿ�����ʤ��
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
		//�ж�ͬһ���Ƿ�����ʤ��
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
