import java.util.LinkedList;
import java.util.Queue;

class MyStack {
	// Queue<Integer> inq = new Queue<>();
	Queue<Integer> inq = new LinkedList<>();
	Queue<Integer> outq = new LinkedList<>();
	int temq;

	// Push element x onto stack.
	public void push(int x) {
		inq.offer(x);// ��ָ����Ԫ�ز���˶���
	}

	// Removes the element on top of the stack.
	public void pop() {
		while (inq.size() > 1) {
			temq = inq.poll();
			outq.offer(temq);
		}
		inq.poll();
		Queue<Integer> q = inq;
		inq = outq;
		outq = q;
	}

	// Get the top element.
	public int top() {
		while (inq.size() > 1) {
			temq = inq.poll();
			outq.offer(temq);
		}
		int x = inq.poll();// ���Ƴ����ʣ��Ԫ�ط��ظ�inq
		outq.offer(x);
		Queue<Integer> q = inq;
		inq = outq;
		outq = q;
		return x;

	}

	// Return whether the stack is empty.
	public boolean empty() {
		if (inq.isEmpty() && outq.isEmpty())
			return true;
		else
			return false;
	}
}