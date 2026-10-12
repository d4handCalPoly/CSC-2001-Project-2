public record NumNode(double num) implements AST {
  public double eval() {
    return num;
  }
}
