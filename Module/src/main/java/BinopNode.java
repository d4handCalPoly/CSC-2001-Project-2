public record BinopNode(String oper, AST left, AST right) implements AST {
  public double eval() {
    switch(oper) {
      case "+":
        return left.eval() + right.eval();
      case "-":
        return left.eval() - right.eval();
      case "*":
        return left.eval() * right.eval();
      case "/":
        return left.eval() / right.eval();
      case "^":
        return Math.pow(left.eval(), right.eval());
      default:
        throw new IllegalArgumentException("Illegal BinopNode operator");
    }
  }

  @Override
  public boolean equals(Object obj) {
    return switch (obj) {
      case BinopNode(String o, AST l, AST r) -> o.equals(this.oper) && l.equals(this.left) && r.equals(this.right);
      case null, default -> false;
    };
  }
}
