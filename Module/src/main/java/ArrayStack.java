import java.util.NoSuchElementException;

public class ArrayStack<T> {
  private int length;
  private Object[] list;

  public ArrayStack() {
    length = 0;
    list = new Object[4];
  }
  public ArrayStack(Object[] list) {
    length = list.length;
    this.list = list;
  }

  /** returns an empty stack */
  public static <T> ArrayStack<T> empty_stack() {
    return new ArrayStack<>();
  }

  /** Add value to top of stack. Increase size of array if necessary */
  public void push(T val) {
    this.ensureNotFull();
    list[length] = val;
    length++;
  }

  /** returns true if stack is empty */
  public boolean is_empty() {
    return length == 0;
  }

  /** returns length of stack */
  public int size() {
    return length;
  }

  /** returns top of stack */
  @SuppressWarnings("unchecked")
  public T peek() {
    if (this.is_empty()) {
      throw new IndexOutOfBoundsException("empty stack");
    }
    return (T) list[length - 1];
  }

  /** removes and returns top of stack */
  @SuppressWarnings("unchecked")
  public T pop() {
    if (this.is_empty()) {
      throw new IndexOutOfBoundsException("empty stack");
    }
    length --;
    return (T) list[length];
  }

  /** if array is full make new list twice as big containing old list */
  private void ensureNotFull() {
    if (length >= list.length) {
      Object[] newList = new String[list.length * 2];
      for (int i = 0; i < list.length; i++) {
        newList[i] = list[i];
      }
      list = newList;
    }
  }
}
