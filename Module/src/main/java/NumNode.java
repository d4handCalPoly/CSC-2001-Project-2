public record NumNode(double num) implements AST {
  /** return NumNode's value */
  public double eval() {
    return num;
  }
}
