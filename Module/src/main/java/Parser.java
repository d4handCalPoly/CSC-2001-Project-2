public class Parser {
  public static AST parsePostfix(String input) {
    if (input == null || input.isEmpty()) {
      throw new IllegalArgumentException("empty input");
    }
    String[] tokens = input.split("\\s+");
    ArrayStack<AST> stack = ArrayStack.empty_stack();

    for (int i = 0; i < tokens.length; i++) {
      String token = tokens[i];
      if (isOperator(token)) {
        BinopNodeConstruct(token, stack);
      } else if (isOperand(token)) {
        stack.push(new NumNode(Double.parseDouble(token)));
      } else {
        throw new IllegalArgumentException("invalid token");
      }
    }
    if (stack.size() > 1) {
      throw new IllegalArgumentException("too many operands");
    }
    return stack.pop();
  }

  public static String infixToPostFix(String input) {
    if (input == null || input.isEmpty()) {
      throw new IllegalArgumentException("empty input");
    }
    String[] tokens = input.split("\\s+");
    ArrayStack<String> output = ArrayStack.empty_stack();
    ArrayStack<String> stack = ArrayStack.empty_stack();

    for (int i = 0; i < tokens.length; i++) {
      String token = tokens[i];
      if (isOperand(token)) {
        output.push(token);
      } else if (token.equals("(")) {
        stack.push(token);
      } else if (token.equals(")")) {
        while (!stack.is_empty() && !stack.peek().equals("(")) {
          output.push(stack.pop());
        }
        if (stack.is_empty()) {
          throw new IllegalArgumentException("mismatched closed paren");
        }
        stack.pop();
      } else if (isOperator(token)) {
        while (!stack.is_empty() && isOperator(stack.peek())) {
          boolean leftAss = !token.equals("^");
          if (getPrecedence(token) < getPrecedence(stack.peek()) ||
              (leftAss && getPrecedence(token) == getPrecedence(stack.peek())))
          {
            output.push(stack.pop());
          } else {
            break;
          }
        }
        stack.push(token);
      } else {
        throw new IllegalArgumentException("invalid token");
      }
    }

    while (!stack.is_empty()) {
      if (stack.peek().equals("(")) {
        throw new IllegalArgumentException("mismatched open paren");
      } else {
        output.push(stack.pop());
      }
    }

    ArrayStack<String> reverse = ArrayStack.empty_stack();
    while (!output.is_empty()) {
      reverse.push(output.pop());
    }

    String out = "";
    while (!reverse.is_empty()) {
      out += reverse.pop() + " ";
    }
    return out.substring(0, out.length() - 1);
  }

  public static AST parseInfix(String input) {
    return parsePostfix(infixToPostFix(input));
  }


  private static boolean isOperand(String oper) {
    try {
        Double.parseDouble(oper);
        return true;
      } catch (NumberFormatException _) {
        return false;
      }
  }

  private static boolean isOperator(String oper) {
    return oper.equals("+") ||
        oper.equals("-") ||
        oper.equals("*") ||
        oper.equals("/") ||
        oper.equals("^");
  }

  private static int getPrecedence(String oper) {
    return switch (oper) {
      case "+", "-" -> 1;
      case "*", "/" -> 2;
      default -> 3;
    };
  }

  private static void BinopNodeConstruct(String oper, ArrayStack<AST> stack) {
    if (stack.size() < 2) {
      throw new IllegalArgumentException("insufficient operands");
    } else {
      stack.push(new BinopNode(oper, stack.pop(), stack.pop()));
    }
  }
}
