import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ASTTest {
  @Test
  void numNodeEval() {
    assertEquals(3.0, new NumNode(3.0).eval());
  }

  BinopNode bNode =
      new BinopNode("*", new NumNode(1.0), new NumNode(3.0));

  @Test
  void binopNode() {
    new BinopNode("*", new NumNode(1.0), new NumNode(3.0));
  }

  @Test
  void binopNodeEqual() {
    assertEquals(new BinopNode("*", new NumNode(1.0), new NumNode(3.0)), bNode);
  }
  @Test
  void binopNodeNotEqual() {
    assertNotEquals(new BinopNode("*", new NumNode(2.0), new NumNode(3.0)),
        bNode);
  }
  @Test
  void binopNodeNotEqualNull() {
    assertNotEquals(null,
        bNode);
  }

  @Test
  void eval() {
    assertEquals(14.0, Parser.parseInfix("2 * ( 3 + 4 )").eval());
  }
  @Test
  void evalAnother() {
    assertEquals(16.0, Parser.parseInfix("12 / 3 * 4").eval());
  }
  @Test
  void evalExpo() {
    assertEquals(512.0, Parser.parseInfix("2 ^ 3 ^ 2").eval());
  }
  @Test
  void evalExpoSub() {
    assertEquals(-1.0, Parser.parseInfix("2 ^ 3 - 3 ^ 2").eval());
  }
  @Test
  void evalNull() {
    assertThrows(IllegalArgumentException.class, ()-> Parser.parseInfix(null).eval());
  }
}
