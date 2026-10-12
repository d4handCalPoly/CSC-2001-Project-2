public record BinopNode(String oper, AST left, AST right) implements AST {
  @Override
  public boolean equals(Object obj) {
    return switch (obj) {
      case BinopNode(String o, AST l, AST r) -> o.equals(this.oper) && l.equals(this.left) && r.equals(this.right);
      case null, default -> false;
    };
  }
}
