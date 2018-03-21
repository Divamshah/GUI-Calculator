
import java.util.HashMap;

public abstract class Operator {
    // The Operator class should contain an instance of a HashMap
    // This map will use keys as the tokens we're interested in,
    // and values will be instances of the Operators.

    private final static HashMap<String, Operator> operators;

    //Initialization of Hashmap takes place here
    static {
        operators = new HashMap<>();
        operators.put("+", new AdditionOperator());
        operators.put("-", new SubtractionOperator());
        operators.put("*", new MultiplicationOperator());
        operators.put("/", new DivisionOperator());
        operators.put("!", new ExclaimOperator());
        operators.put("^", new ExponentOperator());
        operators.put("#", new PoundOperator());
        operators.put(")", new RightPOperator());
        operators.put("(", new LeftPOperator());
    }

    //This helps to give the priority of each operator 
    public abstract int priority();

    //Used as a getter method, to get operators
    public static Operator getToken(String token) {

        return operators.get(token);
    }

    //Performs execution of each operator: +,-/,^,*
    public abstract Operand execute(Operand op1, Operand op2);

    //Checks whether the hashmap contains the operators, matched from expression
    public static boolean check(String token) {

        return operators.containsKey(token);
    }

}

//Each opeartion has its own class extended from operator and returns its priority 
//and performs its execution of operations. 

class AdditionOperator extends Operator {

    public int priority() {
        return 2;
    }

    public Operand execute(Operand op1, Operand op2) {
        return new Operand(op1.getValue() + op2.getValue());
    }
}

class SubtractionOperator extends Operator {

    public int priority() {
        return 2;
    }

    public Operand execute(Operand op1, Operand op2) {
        return new Operand(op1.getValue() - op2.getValue());
    }
}

class MultiplicationOperator extends Operator {

    public int priority() {
        return 3;
    }

    public Operand execute(Operand op1, Operand op2) {
        return new Operand(op1.getValue() * op2.getValue());
    }
}

class DivisionOperator extends Operator {

    public int priority() {
        return 3;
    }

    public Operand execute(Operand op1, Operand op2) {
        
        return new Operand(op1.getValue() / op2.getValue());
    }

}

class ExponentOperator extends Operator {

    public int priority() {
        return 4;
    }

    public Operand execute(Operand op1, Operand op2) {
        return new Operand((int) Math.pow(op1.getValue(), op2.getValue()));
    }
}

class PoundOperator extends Operator {

    public int priority() {
        return -1;
    }

    public Operand execute(Operand op1, Operand op2) {
        return null;
    }
}

class ExclaimOperator extends Operator {

    public int priority() {
        return 1;
    }

    public Operand execute(Operand op1, Operand op2) {
        return null;
    }
}

class RightPOperator extends Operator {

    public int priority() {
        return 1;
    }

    public Operand execute(Operand op1, Operand op2) {
        return null;
    }
}

class LeftPOperator extends Operator {

    public int priority() {
        return 0;
    }

    public Operand execute(Operand op1, Operand op2) {
        return null;
    }
}
