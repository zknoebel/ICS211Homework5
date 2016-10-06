/**
 * used for sorting methods
 */
import java.util.Comparator;

/**
 * 
 * MyLinkedList
 *
 * @author Zachery Knoebel
 *
 * @param <E> the data conatined by the node
 */
public class MyLinkedList<E> implements List211<E> {

  /**
   * @param finished: used to keep bubbleSort from running on a sorted list
   * @param head: points to the beginning of the linkedList
   * @param tail: points to the end of the linkedList
   * @param temp: used to traverse the linkedList
   * @param tempData: temporarily stores data from the DLinkedNode data variables
   * @param placeHolder: used in sorts
   * @param size: the length of the linkedList
   * @param dataArray: holds an array of the data variables from the linkedList nodes
   */
	private boolean finished;
	private DLinkedNode<E> head;
	private DLinkedNode<E> tail;
	private DLinkedNode<E> temp;
	private E tempData;
	private int placeHolder;
	private int size = 0;
	private Object[] dataArray;

	MyLinkedList() {

	}

	/**
	 * 
	 * DLinkedNode
	 *
	 * @author Zachery Knoebel
	 *
	 * @param <E> data variable
	 * 
	 * creates a node that holds an object and can point to the next and previous node in the list
	 */
	@SuppressWarnings("hiding")
	private class DLinkedNode<E> {

		private E data = null;
		private DLinkedNode<E> next = null;
		private DLinkedNode<E> prev = null;

		DLinkedNode(E data) {
			this.data = data;
		}
	}

	/**
	 * adds a node with data 'e' to the end of the list
	 */
	@Override
	public boolean add(E e) {

		DLinkedNode<E> n = new DLinkedNode<E>(e);

		if (size == 0) {
			head = n;
			tail = n;
		} else {

			n.prev = tail;
			tail.next = n;
			tail = n;
		}
		size++;

		return true;
	}

	/**
	 * adds a node wiht data 'e' to the index location
	 */
	@Override
	public void add(int index, E e) {

		if (index < 0 || index > size) {

			throw new IndexOutOfBoundsException();
		}

		DLinkedNode<E> n = new DLinkedNode<E>(e);

		if (index == size) {
			add(e);
		} else if (index == 0) {
			if (size == 0) {
				head = n;
				tail = n;
			} else {

				head.prev = n;
				n.next = head;
				head = n;
			}
			size++;
		} else {

			temp = head;

			for (int i = 0; i < index; i++) {
				temp = temp.next;
			}

			n.prev = temp.prev;
			n.next = temp;
			temp.prev = n;
			n.prev.next = n;
			size++;
		}
	}

	/**
	 * adds a cycle to the list for testing purposes
	 * 
	 * @param cycleTo: cycles from the end of the list to the position indicated by cycleTo
	 */
	public void addCycle(int cycleTo) {

		temp = head;

		for (int i = 0; i < cycleTo; i++) {
			temp = temp.next;
		}
		tail.next = temp;
	}

	/**
	 * inserts data values in dataArray into the data variables in the DLinkedNodes
	 * this is done so the previously made array sorting methods could be implemented with few changes
	 */
	@SuppressWarnings("unchecked")
	private void arrayToList() {

		temp = head;

		for (int i = 0; i < size; i++) {
			temp.data = (E) dataArray[i];
			temp = temp.next;
		}
	}

	/**
	 * uses bubbleSort method on DLinkedList
	 */
	public void bubbleSort(Comparator<? super E> comp) {

		for (int i = 0; i < size - 1; i++) {

			finished = true;
			temp = head;

			for (int j = 0; j < size - 1 - i; j++) {

				if (comp.compare(temp.data, temp.next.data) > 0) {

					finished = false;

					tempData = temp.data;
					temp.data = temp.next.data;
					temp.next.data = tempData;

				}

				temp = temp.next;
			}

			if (finished == true) {
				break;
			}
		}
	}

	/**
	 * checks to make sure the index is within the bounds of the LinkedList
	 */
	public void checkIndex(int index) {

		if (index < 0 || index >= size) {

			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * returns data from the DLinkedNode at index
	 */
	@Override
	public E get(int index) {

		checkIndex(index);

		temp = head;

		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}

		return temp.data;
	}

	/**
	 * checks to see if there is a cycle within the DLinkedList
	 */
	public boolean hasCycle() {

		DLinkedNode<E> tortoise = head;
		DLinkedNode<E> hare = head.next;

		try {
			while (true) {

				if (tortoise.equals(hare)) {
					return true;
				}

				tortoise = tortoise.next;
				hare = hare.next.next;
			}
		} catch (NullPointerException e) {
			return false;
		}

	}

	/**
	 * returns the index of the object obj
	 * if obj is not in the linkedList then it returns -1
	 */
	public int indexOf(Object obj) {

		temp = head;

		for (int i = 0; i < size; i++) {

			if (obj.equals(temp.data)) {
				return i;
			}
			temp = temp.next;
		}

		return -1;
	}

	/**
	 * preforms an insertionSort on the DLinkedList
	 */
	@SuppressWarnings("unchecked")
	public void insertionSort(Comparator<? super E> comp) {

		makeArray();

		for (int i = 0; i < size - 1; i++) {

			placeHolder = i;

			while (comp.compare((E) dataArray[placeHolder], (E) dataArray[placeHolder + 1]) > 0) {

				tempData = (E) dataArray[placeHolder];
				dataArray[placeHolder] = dataArray[placeHolder + 1];
				dataArray[placeHolder + 1] = tempData;

				if (placeHolder > 0) {
					placeHolder--;
				}
			}
		}

		arrayToList();
	}

	/**
	 * used to make an array of data from the data in the DlinkedNodes
   * this is done so the previously made array sorting methods could be implemented with few changes
	 */
	@SuppressWarnings("unchecked")
	private E[] makeArray() {

		dataArray = new Object[size];
		temp = head;

		for (int i = 0; i < size; i++) {

			dataArray[i] = temp.data;
			temp = temp.next;
		}

		return (E[]) dataArray;
	}

	/**
	 * removes a DLinkedNode from the list at index by changing the previous and next pointers of the nodes that point to it
	 */
	@Override
	public E remove(int index) {

		checkIndex(index);

		if (index == 0) {
			head = head.next;
			head.prev = null;
		} else if (index == size - 1) {
			tail = tail.prev;
			tail.next = null;
		}

		else {

			temp = head;

			for (int i = 0; i < index; i++) {
				temp = temp.next;
			}

			tempData = temp.data;
			temp.next.prev = temp.prev;
			temp.prev.next = temp.next;
		}

		size--;
		return tempData;
	}

	/**
	 * uses a selectionSort on the DLinkedList
	 */
	@SuppressWarnings("unchecked")
	public void selectionSort(Comparator<? super E> comp) {

		makeArray();

		for (int i = 0; i < size - 1; i++) {

			tempData = (E) dataArray[i];
			placeHolder = i;

			for (int j = i; j < size; j++) {

				if (comp.compare(tempData, (E) dataArray[j]) > 0) {

					tempData = (E) dataArray[j];
					placeHolder = j;
				}
			}

			dataArray[placeHolder] = dataArray[i];
			dataArray[i] = tempData;
		}

		arrayToList();
	}
/**
 * changes data value at index to 'e'
 */
	@Override
	public E set(int index, E e) {

		checkIndex(index);

		temp = head;

		for (int i = 0; i < index; i++) {

			temp = temp.next;
		}

		tempData = temp.data;

		temp.data = e;
		return tempData;
	}

	/**
	 * returns the length of the DLinkedList
	 */
	@Override
	public int size() {

		return size;
	}

}
