import java.util.EmptyStackException;

/**
 * 
 * @author Zachery Knoebel
 *
 * @param <E>
 */
public class MyStack<E> {
	private MyLinkedList<E> stack = new MyLinkedList<E>();
	private E temp;

	MyStack() {

	}

	/**
	 * checks to see if the stack is empty
	 * 
	 * @return true if stack is empty
	 */
	public boolean empty() {
		return stack.size() == 0;
	}

	/**
	 * returns the data of the item at the top of the stack
	 * 
	 * @return data of item at top of stack
	 */
	public E peak() {
		if (empty()) {
			throw new EmptyStackException();
		} else {
			return stack.tailData();
		}
	}

	/**
	 * adds an element to the top of the stack
	 * 
	 * @param item
	 *            the element that will be the data of the item added to the
	 *            stack
	 */
	public E push(E item) {
		stack.add(item);
		return item;
	}

	/**
	 * stores the data at the top of the stack in temp, removes the top element
	 * and then returns temp
	 * 
	 * @return the data of the element at the top of the stack
	 */
	public E pop() {
		if (empty()) {
			throw new EmptyStackException();
		} else {
			temp = stack.removeTail();

			return temp;
		}
	}

	/**
	 * returns the 1-based position of object o from the top of the stack
	 * 
	 * @param o
	 *            the object to be found
	 * @return the position of the object or -1 if it is not in the stack
	 */
	public int search(Object o) {
		return stack.indexOf(o) + 1;
	}
}
