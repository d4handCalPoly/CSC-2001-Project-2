import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
  AStack aEmptyStack = new AStack();
  AStack aStack1 = new AStack(new String[]{"Test"});
  AStack aStack3 = new AStack(new String[]{"Test", "Test", "Test"});

  @Test
  void AStack() {
    assertTrue(aEmptyStack.is_empty());
  }

  @Test
  void aEqualsFull() {
    assertEquals("Test", new AStack(new String[]{"Test"}).peek());
  }
  @Test
  void aNotEquals() {
    assertNotEquals(aEmptyStack, aStack1);
  }



  @Test
  void aPushEmpty() {
    AStack stack = AStack.empty_stack();
    stack.push("Test");
    assertEquals("Test", stack.peek());
  }
  @Test
  void aPushThree() {
    AStack stack = AStack.empty_stack();
    stack.push("NotTest");
    stack.push("NotTest");
    stack.push("Test");
    assertEquals("Test", stack.peek());
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
    AStack one = aStack1;
    assertEquals("Test", one.peek());
  }
  @Test
  void aPeekThree() {
    AStack three = aEmptyStack;
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
    AStack one = aStack1;
    assertEquals("Test", one.pop());
    assertThrows(IndexOutOfBoundsException.class, one::pop);
  }
  @Test
  void aPopThree() {
    AStack three = aEmptyStack;
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
