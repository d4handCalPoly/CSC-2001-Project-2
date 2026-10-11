import java.util.NoSuchElementException;

public class ArrayStack {
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
  public static ArrayStack empty_stack() {
    return new ArrayStack();
  }

  /** Add value to top of stack. Increase size of array if necessary */
  public void push(Object val) {
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
  public Object peek() {
    if (this.is_empty()) {
      throw new NoSuchElementException("empty stack");
    }
    return list[length - 1];
  }

  /** removes and returns top of stack */
  public Object pop() {
    if (this.is_empty()) {
      throw new NoSuchElementException("empty stack");
    }
    length --;
    return list[length];
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
