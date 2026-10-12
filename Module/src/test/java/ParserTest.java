import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
  @Test
  void emptyPostfix() {
    assertThrows(IllegalArgumentException.class, () -> Parser.parsePostfix(""));
  }
  @Test
  void badTokenPostfix() {
    assertThrows(IllegalArgumentException.class, () -> Parser.parsePostfix("2 a +"));
  }
  @Test
  void fewOperandsPostfix() {
    assertThrows(IllegalArgumentException.class, () -> Parser.parsePostfix("2 +"));
  }
  @Test
  void manyOperandsPostfix() {
    assertThrows(IllegalArgumentException.class, () -> Parser.parsePostfix("2 3 4 +"));
  }
  @Test
  void goodPostfix() {
    BinopNode result = new BinopNode(
        "*",
        new BinopNode(
            "+",
            new NumNode(4.0),
            new NumNode(3.0)
        ),
        new NumNode(2.0)
    );
    AST test = Parser.parsePostfix("2 3 4 + *");
    assertEquals(result, test);
  }
  @Test
  void greatPostfix() {
    BinopNode result = new BinopNode(
        "+",
        new BinopNode(
            "*",
            new BinopNode(
                "-",
                new NumNode(1.0),
                new NumNode(5.0)
            ),
            new NumNode(3.0)
        ),
        new BinopNode(
            "+",
            new NumNode(2.0),
            new NumNode(4.0)
        )
    );
    AST test = Parser.parsePostfix("4 2 + 3 5 1 - * +");
    assertEquals(result, test);
  }

  @Test
  void InfixPrecedence() {
    assertEquals("3 4 2 * + 5 -", Parser.parseInfix("3 + 4 * 2 - 5"));
  }
  @Test
  void InfixRightAss() {
    assertEquals("3 4 5 ^ ^ 6 *", Parser.parseInfix("3 ^ 4 ^ 5 * 6"));
  }
  @Test
  void InfixParen() {
    assertEquals("3 4 5 6 - * +", Parser.parseInfix("3 + 4 * ( 5 - 6 )"));
  }
  @Test
  void InfixHard() {
    assertEquals(
        "2 4 - 2 3 4 + 5 2 ^ * * 4 + ^",
        Parser.parseInfix("( 2 - 4 ) ^ ( 2 * ( ( 3 + 4 ) * 5 ^ 2 ) + 4 )")
    );
  }
}
