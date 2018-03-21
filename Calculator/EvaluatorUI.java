
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This program performs calculations for binary expressions.This also gives
 * GUI. However, the answer is not saved. Once the operation is performed, it is
 * cleared, on pressing any other buttons. It also does not handle, invalid or
 * uneven parenthesis.
 *
 * @author Divam Hiren Shah
 * @Date: 09/14/2017
 */
public class EvaluatorUI extends JFrame implements ActionListener {

    private TextField txField = new TextField();
    private Panel buttonPanel = new Panel();

    //Global string, to re-initialze, after every new expression is evaluated
    String express = "";

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] bText = {
        "7", "8", "9", "+", "4", "5", "6", "- ", "1", "2", "3",
        "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };
    private Button[] buttons = new Button[bText.length];

    public static void main(String argv[]) {
        EvaluatorUI calc = new EvaluatorUI();
    }

    public EvaluatorUI() {
       
        setLayout(new BorderLayout());
        
        add(txField, BorderLayout.NORTH);
        txField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        for (int i = 0; i < 20; i++) {
            buttons[i] = new Button(bText[i]);
        }

        //add buttons to button panel
        for (int i = 0; i < 20; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < 20; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent arg0) {

        //If C, CE, = is not pressed.
        if (arg0.getActionCommand() != bText[18] && arg0.getActionCommand() != bText[19] && arg0.getActionCommand() != bText[14]) {
            //Other operators and Operands are pressed
            for (int i = 0; i < buttons.length; i++) {
                if (arg0.getActionCommand() == bText[i]) {
                    //Concatenates the buttons pressed as an expression.
                    express += bText[i];
                    //Displays the text, on screen of calc.
                    txField.setText(express);
                }
            }
        }
        //If its C or CE it clears the screen and resets the expression to "" 
        else if (arg0.getActionCommand() == bText[18] || arg0.getActionCommand() == bText[19]) {
            txField.setText("");
            express = "";
        } 
        //If its =, result is performed.
        else if (arg0.getActionCommand() == bText[14]) {
           
            Evaluator ob1 = new Evaluator();
            //Gets the text, from the screen of the calc
            express = txField.getText();
            //Clears the screen, after = is pressed.
            txField.setText("");
            //Displays the result on string, after calculated
            txField.setText(txField.getText() + ob1.eval(express));
            //Initialzes to ""
            express = "";

        }

    }
}
