public record BinopNode(String oper, AST left, AST right) implements AST {
  public double eval() {
    switch(oper) {
      case "+":
        return right.eval() + left.eval();
      case "-":
        return right.eval() - left.eval();
      case "*":
        return right.eval() * left.eval();
      case "/":
        return right.eval() / left().eval();
      default:
        return Math.pow(right.eval(), left.eval());
    }
  }
}
