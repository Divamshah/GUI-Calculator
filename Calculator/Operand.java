
public class Operand {

    private int value;

    //Picks integer from the expression
    public Operand(String token) {
        value = Integer.parseInt(token);
    }

    public Operand(int value) {
        this.value = value;
    }

    //Returns the value
    public int getValue() {
        return this.value;
    }

    //Returns true or false, if there are no integers
    public static boolean check(String token) {
        try {
            Integer.parseInt(token);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
