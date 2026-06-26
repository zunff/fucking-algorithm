package 栈;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyQueue232 {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue232().new MyQueue();

    }


    class MyQueue {

        private Deque<Integer> inStack;
        private Deque<Integer> outStack;

        public MyQueue() {
            inStack = new ArrayDeque<>();
            outStack = new ArrayDeque<>();
        }

        public void push(int x) {
            inStack.push(x);
        }

        public int pop() {
            moveIfNeed();
            if (outStack.isEmpty()) {
                throw new RuntimeException();
            }
            return outStack.pop();
        }

        public int peek() {
            moveIfNeed();
            if (outStack.isEmpty()) {
                throw new RuntimeException();
            }
            return outStack.peek();
        }

        public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }

        private void moveIfNeed() {
            if (outStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
        }
    }

}
