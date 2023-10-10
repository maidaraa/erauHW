import java.util.Arrays;

public class GenericStack<E> {

	private static final int DEFAULT_CAPACITY = 12;
	private E[] array;
	private int stackSize;


	@SuppressWarnings("unchecked")
	public GenericStack() {
		array = (E[]) new Object[DEFAULT_CAPACITY];
		stackSize = 0;
	}

	@SuppressWarnings("unchecked")
	public GenericStack(int specifiedCapacity) {
		if (specifiedCapacity <= 0) {
			throw new IllegalArgumentException("Initial capacity must be greater than zero.");
		}
		array = (E[]) new Object[specifiedCapacity];
		stackSize = 0;
	}

	public int getSize() {
		return stackSize;
	}

	public E peek() {
		return array[stackSize - 1];
	}

	public void push(E element) {
		checkSize();
		array[stackSize++] = element;
	}

	public E pop(){
		if (isEmpty()) {
			throw new IllegalStateException("Stack is empty");
		}
		E topStack = array[--stackSize];
		array[stackSize] = null;
		return topStack;

	}

	public boolean isEmpty() {
		return stackSize == 0;
	}


	private void checkSize() {
		if (stackSize == array.length) {
			int newSize = array.length * 2;
			E[] newData = Arrays.copyOf(array, newSize);
			array = newData;
		}
	}

	public String toString() {
		String stackToString = "stack: [";
		for (int i = 0; i < stackSize; i++) {
			stackToString += array[i];
			if (i < stackSize - 1) {
				stackToString += ", ";
			}
		}
		stackToString += "]";
		return stackToString;
	}

}
