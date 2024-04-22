import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        int numberToGuess = 9; 
        int maxNoOfAttempts = 5;
        int attempts = 0;

        DrawingPanel panel = new DrawingPanel(200, 200);
        Graphics g = panel.getGraphics();

        while (attempts < maxNoOfAttempts) {
            int guess = Integer.parseInt(JOptionPane.showInputDialog("Guess the number [between 1 and 10]:"));

            if (guess < numberToGuess) {
                System.out.println("Too low!");
                panel.setBackground(Color.BLUE);
                g.setColor(Color.ORANGE);
                g.fillRect(50, 50, 100, 100);
            } else if (guess > numberToGuess) {
                System.out.println("Too high!");
                panel.setBackground(Color.ORANGE);
                g.setColor(Color.BLUE);
                g.fillRect(50, 50, 100, 100);
            } 
            else {
                System.out.println("Congratulations! You guessed the number.");
                panel.setBackground(Color.GREEN);
                break;
            }

            attempts++;
        }

        if (attempts == maxNoOfAttempts) {
            System.out.println("Sorry, you've used all your attempts. The correct number was " + numberToGuess);
            panel.setBackground(Color.GRAY);
        }
    }
}
