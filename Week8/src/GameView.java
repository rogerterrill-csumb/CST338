import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

class GameView extends JFrame
{
   // Jpanels to group hands and game information
   private JPanel pnlPlayerHand, pnlComputerHand, pnlButtonsAndInfo, pnlInfo;

   // JPanels that display cards and game information
   private JLabel title = new JLabel("BLACKJACK", JLabel.CENTER);
   private JLabel dollars = new JLabel("You have $10", JLabel.CENTER);
   private JLabel message = new JLabel("How much would you like to bet", JLabel.CENTER);
   private JTextField bet = new JTextField(4);

   // Buttons that will hit or stay
   private JButton btnHit = new JButton("HIT");
   private JButton btnStay = new JButton("STAY");

   // Labels for cards for each hand
   private JLabel[] computerHandLabels = new JLabel[GameModel.MAX_CARDS_PER_HAND];
   private JLabel[] playerHandLabels = new JLabel[GameModel.MAX_CARDS_PER_HAND];

   // Keeps track of number of cards in players hand
   private int numCards = 2;

   // Default constructor
   GameView()
   {
      // Sets the title of the JFrame
      super("Blackjack");

      // Basic Jframe foundation
      setSize(600,450);
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
      pnlButtonsAndInfo = new JPanel(new GridLayout(1, 3));
      pnlPlayerHand = new JPanel(new GridLayout(1, 1));

      // Add panels to the JFrame
      add(pnlComputerHand, BorderLayout.NORTH);
      add(pnlButtonsAndInfo, BorderLayout.CENTER);
      add(pnlPlayerHand, BorderLayout.SOUTH);

      // Add titles to each panel
      pnlComputerHand.setBorder(new TitledBorder("Computer Hand"));
      pnlButtonsAndInfo.setBorder(new TitledBorder("Game Information"));
      pnlPlayerHand.setBorder(new TitledBorder("Player Hand"));

      // Panels within the Information Panel, title, money and bet
      pnlInfo = new JPanel(new GridLayout(4,0));

      // Center the text in the input field
      bet.setHorizontalAlignment(JTextField.CENTER);

      // Add components to panel info
      pnlInfo.add(title);
      pnlInfo.add(dollars);
      pnlInfo.add(message);
      pnlInfo.add(bet);

      // Add components to information panel
      pnlButtonsAndInfo.add(btnHit);
      pnlButtonsAndInfo.add(pnlInfo);
      pnlButtonsAndInfo.add(btnStay);

      // Load the GUI Icons for the cards
      GUICard.loadCardIcons();

      // Add labels to array of cards
      for(int i = 0; i < GameModel.MAX_CARDS_PER_HAND; i++)
      {
         computerHandLabels[i] = new JLabel(GUICard.getBackcardIcon());
         playerHandLabels[i] = new JLabel(GUICard.getBackcardIcon());
      }

      // Create the labels for both computer and players

      // Add cards to computer panel
      pnlComputerHand.add(computerHandLabels[0]);
      pnlComputerHand.add(computerHandLabels[1]);

      //Add cards to player panel
      for(int i = 0; i < numCards; i++)
      {
         pnlPlayerHand.add(playerHandLabels[i]);
      }

      setVisible(true);
   }

   // Update the players panel
   public void updatePlayersHand()
   {
      pnlPlayerHand.removeAll();
      //Add cards to player panel
      for(int i = 0; i < numCards; i++)
      {
         pnlPlayerHand.add(playerHandLabels[i]);
      }
      repaint();
   }

   // Add actionListener to hit button
   public void addActionListenerToHit(ActionListener hitListener)
   {
      btnHit.addActionListener(hitListener);
   }

   // Add actionListener to stay button
   public void addActionListenerToStay(ActionListener stayListener)
   {
      btnStay.addActionListener(stayListener);
   }

   // Set the dollar text
   public void setDollars(String dollarsMessage)
   {
      this.dollars.setText(dollarsMessage);
   }

   // Set players hand
   public void setHandLabels(int playerIndex, int labelIndex, Card card)
   {
      // If player
      if(playerIndex == 0)
      {
         playerHandLabels[labelIndex].setIcon(GUICard.getIcon(card));
      }
      // If computer
      if(playerIndex == 1)
      {
         computerHandLabels[labelIndex].setIcon(GUICard.getIcon(card));
      }
   }

   // Set number of cards
   public void setNumCards(int numCards)
   {
      this.numCards = numCards;
   }

   // Sets the number of cards in players hand

   // DEBUG
   public String showDollarInConsole()
   {
      return bet.getText();
   }
}