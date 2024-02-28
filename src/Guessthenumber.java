// Code Casa - Task 1 - JAVA development 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Guessthenumber {
    private JFrame frame;
    private JTextField textField;
    private JButton button;
    private JLabel label;
    private int randomNumber;
    private int attempts;
    private int score;
    private int maxNumber;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Guessthenumber();
            }
        });
    }

    public Guessthenumber() {
        maxNumber = 1000;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Guess the number");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        label = new JLabel("Enter your guess (1-" + maxNumber + "):");
        panel.add(label);

        textField = new JTextField();
        panel.add(textField);

        button = new JButton("Submit");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });
        panel.add(button);

        JLabel resultLabel = new JLabel("");
        panel.add(resultLabel);

        frame.getContentPane().add(panel);
        frame.setVisible(true);

        randomNumber = new Random().nextInt(maxNumber) + 1;
        attempts = 0;
        score = 0;
    }

    private void handleGuess() {
        int guess;
        try {
            guess = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (guess < 1 || guess > maxNumber) {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a number between 1 and " + maxNumber + ".", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        attempts++;

        if (guess == randomNumber) {
            score += 10 - attempts;
            JOptionPane.showMessageDialog(frame, "Congratulations! You guessed the correct number in " + attempts + " attempts. Your score is " + score + " points.", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            initialize();
        } else if (guess > randomNumber) {
            JOptionPane.showMessageDialog(frame, "Your guess is too high. Try again.", "Incorrect guess", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Your guess is too low. Try again.", "Incorrect guess", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}