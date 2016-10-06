
public class MyStack<E> {
  private MyLinkedList<E> stack = new MyLinkedList<E>();
  private E temp;
	
	MyStack(){
		
	}
	
	public boolean empty(){
		return stack.size() == 0;
	}
	
	public E peak(){
		return stack.tailData();
	}
	
	public void push(E item){
		stack.add(item);
	}
	
	public E pop(){
	  temp = stack.removeTail();
	  
	  return temp;
	}
}
