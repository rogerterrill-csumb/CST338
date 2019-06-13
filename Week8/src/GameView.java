import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

class GameView extends JFrame
{
   // Default constructor
   GameView()
   {
      // Sets the title of the JFrame
      super("Blackjack");

      // Basic Jframe foundation
      setSize(800,700);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Display the JFrame
      setVisible(true);
   }
}