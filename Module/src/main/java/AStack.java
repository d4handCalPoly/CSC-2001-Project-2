public class AStack {
  private int length;
  private String[] list;

  public AStack() {
    length = 0;
    list = new String[4];
  }
  public AStack(String[] list) {
    length = list.length;
    this.list = list;
  }

  /** returns an empty stack */
  public static AStack empty_stack() {
    return new AStack();
  }

  /** Add value to top of stack. Increase size of array if necessary */
  public void push(String val) {
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
  public String peek() {
    if (this.is_empty()) {
      throw new IndexOutOfBoundsException("empty stack");
    }
    return list[length - 1];
  }

  /** removes and returns top of stack */
  public String pop() {
    if (this.is_empty()) {
      throw new IndexOutOfBoundsException("empty stack");
    }
    length --;
    return list[length];
  }

  /** if array is full make new list twice as big containing old list */
  private void ensureNotFull() {
    if (length >= list.length) {
      String[] newList = new String[list.length * 2];
      for (int i = 0; i < list.length; i++) {
        newList[i] = list[i];
      }
      list = newList;
    }
  }
}
