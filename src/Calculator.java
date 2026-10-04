import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {
    int boardWidth = 360;
    int boardHeight = 540;

    //colours - these will be used for the interface
    Color customLightGrey = new Color(212, 212, 210);
    Color customDarkGrey = new Color(80, 80, 80);
    Color customBlack = new Color(28,28,28);
    Color customOrange = new Color(255,149,0);

    String[] buttonValues = {
            "AC", "+/-", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "√", "="
    };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};

    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();//panel for displaying
    JPanel buttonsPanel = new JPanel();//panel for the buttons

    String A = "0";
    String operator = null;
    String B = null;

    Calculator(){ //creates the calculator window
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        displayLabel.setBackground(customBlack); //sets the background of the label, font, colour
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout()); //creates the display panel "top of calc"
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);

        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(customBlack);
        frame.add(buttonsPanel);

        for (int i = 0; i < buttonValues.length; i++){
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBlack));
            if (Arrays.asList(topSymbols).contains(buttonValue)){
                button.setBackground(customLightGrey);
                button.setForeground(customBlack);
            }
            else if (Arrays.asList(rightSymbols).contains(buttonValue)){
                button.setBackground(customOrange);
                button.setForeground(Color.WHITE);
            }
            else {
                button.setBackground(customDarkGrey);
                button.setForeground(Color.WHITE);
            }

            buttonsPanel.add(button);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    String buttonValue = button.getText();
                    if (Arrays.asList(rightSymbols).contains(buttonValue)){
                        if (buttonValue.equals("=")){
                            if (A != null){
                                B = displayLabel.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);

                                switch (operator){ //uses switch instead of if for readability
                                    case "+" -> displayLabel.setText(removeZeroDec(numA+numB));
                                    case "-" -> displayLabel.setText(removeZeroDec(numA-numB));
                                    case "×" -> displayLabel.setText(removeZeroDec(numA*numB));
                                    case "÷" -> displayLabel.setText(removeZeroDec(numA/numB));
                                }
                                clearAll();
                            }
                        }
                        else if ("+-×÷".contains(buttonValue)){
                            if (operator == null){
                                A = displayLabel.getText();
                                displayLabel.setText("0");
                                B = "0";
                            }
                            operator = buttonValue;
                        }

                    }
                    else if (Arrays.asList(topSymbols).contains(buttonValue)){

                        if (buttonValue.equals("AC")){
                            clearAll(); // calls clearAll function and resets the variables
                            displayLabel.setText("0");
                        }
                        else if (buttonValue.equals("+/-")){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay *= -1;
                            displayLabel.setText(removeZeroDec(numDisplay));//calls removeZeroDec to convert the double back to an int if its remainder 0
                        }
                        else if (buttonValue.equals("%")){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay /= 100;
                            displayLabel.setText(removeZeroDec(numDisplay));
                        }
                    }
                    else{
                        if (buttonValue.equals(".")){
                            if (!displayLabel.getText().contains(buttonValue)){//checks if the decimal place is the already there
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }

                        }
                        else if ("0123456789".contains(buttonValue)){
                            if (displayLabel.getText().equals("0")){ //if the display label start with 0, replace 0 with any other button value
                                displayLabel.setText(buttonValue);
                            }
                            else {
                                displayLabel.setText(displayLabel.getText() + buttonValue);//sets the text in the label with new information
                            }
                        }
                        else if ("√".contains(buttonValue)){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay = Math.sqrt(numDisplay);
                            displayLabel.setText(removeZeroDec(numDisplay));//calls removeZeroDec to convert the double back to an int if its remainder 0
                        }
                    }
                }
            });

        }

    }
    void clearAll(){
        A = "0";
        operator = null;
        B = null;
    }
    String removeZeroDec(double numDisplay){
        if (numDisplay % 1 == 0){
            return Integer.toString((int) numDisplay);
        }
        return Double.toString(numDisplay);
    }
}
