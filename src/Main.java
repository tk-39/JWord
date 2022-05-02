import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main extends JFrame implements ActionListener {

    //a list of all possible 5 letter words in English
    public static HashSet<String> dictionary = new HashSet<>();

    //a smaller list of words for selecting the target word from (i.e. the word the player needs to guess)
    public static ArrayList<String> targetWords = new ArrayList<>();

    //JLabels, JTextFields, JButtons
    JLabel attempt1, attempt2, attempt3, attempt4, attempt5, attempt6, wordle;
    JTextField a1, a2, a3, a4, a5, a6;

    //Variable to return one answer using getTarget, which is then used to play the Wordle game
    static String answer;


    //GUI components
    Main() {
        this.setSize(300, 400);
        this.setTitle("Java Wordle");
        this.setLayout(new GridBagLayout()); // Chose GridBagLayout so components could stack vertically without any hassle
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Constraints
        GridBagConstraints constraints = new GridBagConstraints();


//        Title
        wordle = new JLabel("Wordle");
        wordle.setFont(new Font("Verdana", Font.BOLD, 20));
        wordle.setForeground(Color.RED);
        wordle.setVerticalTextPosition(SwingConstants.NORTH);
        this.add(wordle, constraints);

        //Guess 1

        a1 = new JTextField(10);
        a1.setActionCommand("temp");
        a1.addActionListener(this);
        attempt1 = new JLabel("Attempt 1:");
        attempt1.setFont(new Font("jumble",Font.PLAIN, 15));

        //Constraints for label and textField

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.BOTH;
        this.add(attempt1, constraints);
        constraints.gridx = 1;
        this.add(a1, constraints);

        //Guess 2
        a2 = new JTextField(10);
        a2.setActionCommand("temp");
        a2.addActionListener(this);
        attempt2 = new JLabel("Attempt 2:");
        attempt2.setFont(new Font("jumble",Font.PLAIN, 15));

        //Constraints for label and textField
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(attempt2, constraints);
        //Adding another constraint to x-axis for the JTextField, so it stacks vertically
        constraints.gridx = 1;
        this.add(a2, constraints);

        //Guess 3
        a3 = new JTextField(10);
        a3.setActionCommand("temp");
        a3.addActionListener(this);
        attempt3 = new JLabel("Attempt 3:");
        attempt3.setFont(new Font("jumble",Font.PLAIN, 15));

        //Constraints
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(attempt3, constraints);
        //Same thing here
        constraints.gridx = 1;
        this.add(a3, constraints);

        //Guess 4
        a4 = new JTextField(10);
        a4.setActionCommand("temp");
        a4.addActionListener(this);
        attempt4 = new JLabel("Attempt 4:");
        attempt4.setFont(new Font("jumble",Font.PLAIN, 15));

        //Constraints
        constraints.gridx = 0;
        constraints.gridy = 4;
        this.add(attempt4, constraints);
        //Same thing here for textField
        constraints.gridx = 1;
        this.add(a4, constraints);

        //Guess 5
        a5 = new JTextField(10);
        a5.setActionCommand("temp");
        a5.addActionListener(this);
        attempt5 = new JLabel("Attempt 5:");
        attempt5.setFont(new Font("jumble",Font.PLAIN, 15));

        //Constraints
        constraints.gridx = 0;
        constraints.gridy = 5;
        this.add(attempt5, constraints);
        //Same thing here for textField
        constraints.gridx = 1;
        this.add(a5, constraints);

        //Guess 6
        a6 = new JTextField(10);
        a6.setActionCommand("temp");
        a6.addActionListener(this);
        attempt6 = new JLabel("Attempt 6:");
        attempt6.setFont(new Font("jumble",Font.PLAIN, 15));

        //Constraints
        constraints.gridx = 0;
        constraints.gridy = 6;
        this.add(attempt6, constraints);
        //Same thing here for textField
        constraints.gridx = 1;
        this.add(a6, constraints);



//        int attempts = 6;
//        for(int i = 0; i<attempts; i++) {
//            JTextField text = new JTextField(6);
//            text.setActionCommand("temp");
//            text.addActionListener(this);
//            constraints.gridx = 1;
//            constraints.gridy = i + 1;
//            this.add(text, constraints);
//        }
//
//        //JLabels
//
//        int labels = 6;
//        for(int i = 0; i<labels; i++) {
//            JLabel label = new JLabel("Attempt"+(i+1));
//            constraints.fill = GridBagConstraints.BOTH;
//            constraints.gridx = 0;
//            constraints.gridy = i + 1;
//            this.add(label, constraints);
//
//        }
//

        //JButtons - Old code consisted of making 6 separate fields, opted to use for loop for code efficiency
        int buttons = 6;
        for (int i = 0; i < buttons; i++) {
            JButton button = new JButton("Guess" + (i + 1));
            button.setActionCommand("Guess" + (i + 1));
            button.addActionListener(this);
            constraints.gridx = 2;
            constraints.gridy = 1 + i;
            this.add(button, constraints);
        }


    }

    public static void main(String[] args) {
        //load in the two word lists
        try {
            Scanner in_dict = new Scanner(new File("gameDictionary.txt"));
            while (in_dict.hasNext()) {
                dictionary.add(in_dict.next());
            }

            Scanner in_targets = new Scanner(new File("targetWords.txt"));
            while (in_targets.hasNext()) {
                targetWords.add(in_targets.next());
            }
            in_dict.close();
            in_targets.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);

            }
        });
        System.out.println(getAnswer());
    }

    //use this method for selecting a word. It's important for marking that the word you have selected is printed out to the console!
    public static String getTarget() {
        Random r = new Random();
        String target = targetWords.get(r.nextInt(targetWords.size()));
        //don't delete this line.
        System.out.println(target);
        return target;
    }

    //Method for ending the wordle game
    public boolean endWordle() {
        this.setVisible(false);
        return false;
    }

    //Method for retrieving a final answer using the getTarget() randomiser

    public static String getAnswer() {
        int i = 0;
       while(i < 1) {
           answer = getTarget();
           i++;
       }
       return " ";
    }
    //Guess Counter that increases each time the player guesses the wrong word, but still included in gameDictionary
    int guessCounter = 0;

    //Action event listener
    @Override
    public void actionPerformed(ActionEvent e) {

        String input1 = a1.getText();
        String input2 = a2.getText();
        String input3 = a3.getText();
        String input4 = a4.getText();
        String input5 = a5.getText();
        String input6 = a6.getText();

        /*This code will check if the word is contained in the Game Dictionary
        If the word is not in the dictionary, the text field will reset, and the counter will not increment,
        If the word is in the dictionary, but not the correct word it will increase the game counter
        If a letter is correct and in the correct spot, "Y" is printed to console, if a letter is correct and in the wrong spot,
        "X" is printed to console, otherwise "X" is printed */

        if (e.getActionCommand().equals("Guess1")) {
            if (!dictionary.contains(input1.toLowerCase())) { //toLowerCase method so the program doesn't consider capitalised letters
                JOptionPane.showMessageDialog(null, "Not in word list");
                a1.setText("");
            } else {
                ++guessCounter;
                a1.setEditable(false); //User's guess attempt will be locked in after guessing a word in the dictionary
                for(int i = 0; i < answer.length(); i++) {
                    boolean end = false;
                    for(int j = 0; j < answer.length(); j++) {
                        if (input1.charAt(i) == answer.charAt(i)) {
                            //Letter in correct spot
                            System.out.println("Y");
                            end = true;
                        }
                        if (input1.charAt(i) == answer.charAt(j)) {
                            //Correct letter but wrong spot
                            System.out.println("C");
                            end = true;
                        }
                    }
                        if(!end) {
                            //Incorrect letter
                            System.out.println("X");
                        }

                }
            }
        }

        if (e.getActionCommand().equals("Guess2")) {
            if (!dictionary.contains(input2.toLowerCase())) {
                JOptionPane.showMessageDialog(null, "Not in word list");
                a2.setText("");
            } else {
                ++guessCounter;
                a2.setEditable(false);
                System.out.println(" ");
                for (int i = 0; i < input2.length(); i++) {
                    if (input2.charAt(i) == answer.charAt(i)) {
                        System.out.println("Y");
                    } else {
                        System.out.println("X");
                    }
                }
            }
        }

        if (e.getActionCommand().equals("Guess3")) {
            if (!dictionary.contains(input3.toLowerCase())) {
                JOptionPane.showMessageDialog(null, "Not in word list");
                a3.setText("");
            } else {
                ++guessCounter;
                a3.setEditable(false);
                System.out.println(" ");
                for (int i = 0; i < input3.length(); i++) {
                    if (input3.charAt(i) == answer.charAt(i)) {
                        System.out.println("Y");
                    } else {
                        System.out.println("X");
                    }
                }
            }
        }

        if (e.getActionCommand().equals("Guess4")) {
            if (!dictionary.contains(input4.toLowerCase())) {
                JOptionPane.showMessageDialog(null, "Not in word list");
                a4.setText("");
            } else {
                ++guessCounter;
                a4.setEditable(false);
                System.out.println(" ");
                for (int i = 0; i < input4.length(); i++) {
                    if (input4.charAt(i) == answer.charAt(i)) {
                        System.out.println("Y");
                    } else {
                        System.out.println("X");
                    }
                }
            }
        }

            if (e.getActionCommand().equals("Guess5")) {
                if (!dictionary.contains(input5.toLowerCase())) {
                    JOptionPane.showMessageDialog(null, "Not in word list");
                    a5.setText("");
                } else {
                    ++guessCounter;
                    a5.setEditable(false);
                    System.out.println(" ");
                    for (int i = 0; i < input5.length(); i++) {
                        if (input5.charAt(i) == answer.charAt(i)) {
                            System.out.println("Y");
                        } else {
                            System.out.println("X");
                        }
                    }
                }
            }

            if (e.getActionCommand().equals("Guess6")) {
                if (!dictionary.contains(input6.toLowerCase())) {
                    JOptionPane.showMessageDialog(null, "Not in word list");
                    a6.setText("");
                } else {
                    ++guessCounter;
                    a6.setEditable(false);
                    System.out.println(" ");
                    for (int i = 0; i < input6.length(); i++) {
                        if (input6.charAt(i) == answer.charAt(i)) {
                            System.out.println("Y");
                        } else {
                            System.out.println("X");
                        }
                    }
                }
            }


            //If the correct answer is guessed, a message will appear displaying the guessCounter and then closes window
            if (input2.equals(answer) || input3.equals(answer)
                || input4.equals(answer) || input5.equals(answer) || input6.equals(answer)) {
                JOptionPane.showMessageDialog(null, "Congratulations! you got it in " + guessCounter + " tries!");
                endWordle();

            }
            //Grammar
            if (input1.equals(answer)) {
            JOptionPane.showMessageDialog(null, "Congratulations! you got it in " + guessCounter + " try!");
            endWordle();
        }


            //If guesses reach 6 without a correct answer, a message will appear and will then close the window
            if (guessCounter == 6) {
                JOptionPane.showMessageDialog(null, guessCounter + " Attempts reached, You have lost");
                endWordle();
            }
        }
}
