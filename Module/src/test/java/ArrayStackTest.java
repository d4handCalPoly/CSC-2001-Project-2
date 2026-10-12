import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {
  ArrayStack aEmptyStack = new ArrayStack();
  ArrayStack aStack1 = new ArrayStack(new String[]{"Test"});
  ArrayStack aStack3 = new ArrayStack(new String[]{"Test", "Test", "Test"});

  @Test
  void AStack() {
    assertTrue(aEmptyStack.is_empty());
  }

  @Test
  void aEqualsFull() {
    assertEquals("Test", new ArrayStack(new String[]{"Test"}).peek());
  }
  @Test
  void aNotEquals() {
    assertNotEquals(aEmptyStack, aStack1);
  }



  @Test
  void aPushEmpty() {
    ArrayStack stack = ArrayStack.empty_stack();
    stack.push("Test");
    assertEquals("Test", stack.peek());
  }
  @Test
  void aPushThree() {
    ArrayStack stack = ArrayStack.empty_stack();
    stack.push("NotTest");
    stack.push("NotTest");
    stack.push("Test");
    assertEquals("Test", stack.peek());
  }
  @Test
  void aPushOverflow() {
    ArrayStack stack = ArrayStack.empty_stack();
    stack.push("Test2");
    stack.push("NotTest");
    stack.push("NotTest");
    stack.push("NotTest");
    stack.push("Test");
    assertEquals("Test", stack.peek());
    stack.pop();
    stack.pop();
    stack.pop();
    stack.pop();
    assertEquals("Test2", stack.pop());
  }

  @Test
  void aPeekEmpty() {
    assertThrows(
        IndexOutOfBoundsException.class,
        () -> aEmptyStack.peek()
    );
  }
  @Test
  void aPeekOne() {
    ArrayStack one = aStack1;
    assertEquals("Test", one.peek());
  }
  @Test
  void aPeekThree() {
    ArrayStack three = aEmptyStack;
    three.push("NotTest");
    three.push("Test");
    three.push("NotTest");
    three.pop();
    assertEquals("Test", three.peek());
  }

  @Test
  void aPopEmpty() {
    assertThrows(
        IndexOutOfBoundsException.class,
        () -> aEmptyStack.pop()
    );
  }
  @Test
  void aPopOne() {
    ArrayStack one = aStack1;
    assertEquals("Test", one.pop());
    assertThrows(IndexOutOfBoundsException.class, one::pop);
  }
  @Test
  void aPopThree() {
    ArrayStack three = aEmptyStack;
    three.push("NotTest");
    three.push("Test");
    three.push("NotTest");
    three.pop();
    assertEquals("Test", three.pop());
  }

  @Test
  void aSizeNull() {
    assertEquals(0, aEmptyStack.size());
  }
  @Test
  void aSizeOne() {
    assertEquals(1, aStack1.size());
  }
  @Test
  void aSizeThree() {
    assertEquals(3, aStack3.size());
  }
}
