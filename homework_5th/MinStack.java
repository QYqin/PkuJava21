class MinStack {
    private Stack<Integer> st=new Stack<Integer>();// 定义两个栈，一个实现出入栈的功能，另一个存储最小值
	private Stack<Integer> stmin=new Stack<Integer>();
	public void push(int x) {
		 st.push(x);
		if (stmin.empty()==true) {
			stmin.push(x);
		} else if (Integer.parseInt(stmin.peek().toString()) >= x) {
			stmin.push(x);
		} // 如果最小堆栈为空， 直接进栈，如果不为空，比较大小，如果小于最小堆栈中的元素，则进栈。
	}

	public void pop() {
		int pop = st.pop();
		if (pop <= stmin.peek()) {
			stmin.pop();
		}
	}

	public int top() {
		return st.peek();

	}
	public int getMin() {
	   // if(stmin.empty())
	   // {
	   //     return 0;
	   // }
		return stmin.peek();

	}
}
