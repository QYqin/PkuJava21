public class Solution {
    public boolean isUgly(int num) {
		if(num<=0){
			return false;
		}
		while(num%2==0){//�ж�num�ܷ�2������ȡ��������̼����жϣ������һֱ�����������жϽ���Ƿ�Ϊ1,�����ж��ܷ�3����
			num=num/2;
		}
		while(num%3==0){//�ж�num�ܷ�3������ȡ��������̼����жϣ������һֱ�����������жϽ���Ƿ�Ϊ1�������ж��ܷ�5����
			num=num/3;
		}
		while(num%5==0){//�ж�num�ܷ�5������ȡ��������̼����жϣ������һֱ�����������жϽ���Ƿ�Ϊ1�������ж����Ƿ�Ϊ1 
			num=num/5;
		}
		if(num==1){//��Ϊ1��ʾnumֻ�ܱ�2��3��5����
			return true;
		}
		else{
			return false;
		}
    	
        
    }
}