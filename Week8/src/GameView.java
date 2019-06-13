import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

class GameView extends JFrame
{
   // Jpanels to group hands and game information
   private JPanel pnlPlayerHand, pnlComputerHand, pnlInformation;

   // JPanels that display cards and game information
   private JLabel title = new JLabel("BLACKJACK", JLabel.CENTER);
   private JLabel dollars = new JLabel("$10");

   // Buttons that will hit or stay
   private JButton btnHit = new JButton("HIT");
   private JButton btnStay = new JButton("STAY");



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

   // Initializes the table
   public void initTable()
   {
      // Container layout manager
      setLayout(new BorderLayout());

      // Panel to display both hands and game information
      pnlComputerHand = new JPanel(new GridLayout(1, 1));
      pnlInformation = new JPanel(new GridLayout(1, 3));
      pnlPlayerHand = new JPanel(new GridLayout(1, 1));

      // Add panels to the JFrame
      add(pnlComputerHand, BorderLayout.NORTH);
      add(pnlInformation, BorderLayout.CENTER);
      add(pnlPlayerHand, BorderLayout.SOUTH);

      // Add titles to each panel
      pnlComputerHand.setBorder(new TitledBorder("Computer Hand"));
      pnlInformation.setBorder(new TitledBorder("Game Information"));
      pnlPlayerHand.setBorder(new TitledBorder("Player Hand"));

      // Add components to information panel
      pnlInformation.add(btnHit);
      pnlInformation.add(title);
      pnlInformation.add(btnStay);
   }
}