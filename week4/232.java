import java.util.Stack;

class MyQueue {
	Stack<Integer> instack = new Stack<>();
	Stack<Integer> outstack = new Stack<>();
	int tempstack;

	// Push element x to the back of queue.
	public void push(int x) {
		instack.push(x);
	}

	// Removes the element from in front of queue.
	public void pop() {
		if (!outstack.isEmpty()) {
			outstack.pop();
		} else {
			while (!instack.isEmpty()) {
				tempstack = instack.pop();
				outstack.push(tempstack);
			}
			outstack.pop();

		}
	}

	// Get the front element.
	public int peek() {
		if (!outstack.isEmpty()) {
			return outstack.peek();
		} else {
			while (!instack.isEmpty()) {
				tempstack = instack.pop();
				outstack.push(tempstack);

			}
			return outstack.peek();
		}
	}

	// Return whether the queue is empty.
	public boolean empty() {
		if (instack.isEmpty() && outstack.isEmpty())
			return true;
		else
			return false;
	}
}