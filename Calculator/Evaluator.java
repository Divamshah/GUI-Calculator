
import java.util.*;

public class Evaluator {

    private Stack<Operand> operandStack;
    private Stack<Operator> operatorStack;
    
    //StringTokeneizer breaks the expression into tokens
    private StringTokenizer tokenizer;

    //Added (,) this for complex expressions
    private static final String DELIMITERS = "+-*^/#!() ";

    public Evaluator() {
        //Construction of Operand And Operators Stack
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    //Execution of the expression takes place here.
    public int eval(String expression) {
        String token;
        // The 3rd argument is true to indicate that the delimiters should be used
        // as tokens, too. 
        this.tokenizer = new StringTokenizer(expression, DELIMITERS, true);

        //initialized with # operator, used as bogus to compare priorities and detect end of operatorStack.
        operatorStack.push(new PoundOperator());

        //Checks whether there are more tokens to be pushed in stacks
        while (this.tokenizer.hasMoreTokens()) {
            // filter out spaces
            if (!(token = this.tokenizer.nextToken()).equals(" ")) {
                // check if token is an operand
                if (Operand.check(token)) {
                    operandStack.push(new Operand(token));

                } else {
                    if (!Operator.check(token)) {
                        System.out.println("*****invalid token******");
                        System.exit(1);
                    }

                    //Gets a newOperator it matches from hashmap.
                    Operator newOperator = Operator.getToken(token);

                    //Processes only, when there is greater priority in stack and there are no '('
                    while (operatorStack.peek().priority() >= newOperator.priority() && !newOperator.equals(Operator.getToken("("))) {

                        //If the token read is ), then there is already '(' which is inserted in the operator stack.
                        if (token.equals(")")) {
                            //Perform until, the '(' is on top of the stack.
                            while (!operatorStack.peek().equals(Operator.getToken("("))) {
                                Operator oldOpr = operatorStack.pop();
                                Operand op2 = operandStack.pop();
                                Operand op1 = operandStack.pop();
                                operandStack.push(oldOpr.execute(op1, op2));

                            }

                            //If '(' found on top, pop and break, to continue to read more takens
                            if (operatorStack.peek().equals(Operator.getToken("("))) {
                                operatorStack.pop();
                                break;
                            }

                        } 
                        //if ')' not found, it means either its in '(' or there are no parenthesis
                        else {
                            Operator oldOpr = operatorStack.pop();
                            Operand op2 = operandStack.pop();
                            Operand op1 = operandStack.pop();
                            operandStack.push(oldOpr.execute(op1, op2));
                        }
                    }
                    //Pushes the operator, when the operator!=')'
                    if (!newOperator.equals(Operator.getToken(")"))) {
                        operatorStack.push(newOperator);
                    }
                }
            }
        }
        // No more tokens to be read. All the tokens are in stacks, gets processed.
        if (!this.tokenizer.hasMoreTokens()) {
            //Keeps processing, until it reaches #
            while (operatorStack.size() != 1) {
                Operator oldOpr = operatorStack.pop();
                //Checks if there are operand left to be used,
                if (!operandStack.empty()) {
                    Operand op2 = operandStack.pop();
                    Operand op1 = operandStack.pop();
                    operandStack.push(oldOpr.execute(op1, op2));
                } else {
                    System.exit(1);
                }
            }
        }
        // Returns, the final result
        return operandStack.pop().getValue();
    }
}
