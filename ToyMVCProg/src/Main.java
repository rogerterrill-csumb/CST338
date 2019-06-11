import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main
{
   public static void main(String[] args)
   {
      View view = new View();

      Model model = new Model();

      Controller controller = new Controller(view, model);
   }
}

/**
 * The View class displays the Frame with text
 */
class View extends JFrame
{
   // First component
   private JLabel textLabel;
   private JButton ModelButton, ViewButton;

   // Default Constructor
   View()
   {
      // Inherited from the parent class
      super("MVC Toy App");

      // Basic setup for display
      setSize(300, 200);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Create the JLabel object with init text
      textLabel = new JLabel("Click Button To Change This Text");

      // Create button
      ModelButton = new JButton("Model Button");
      ViewButton = new JButton("View Button");

      // Add the Components
      add(textLabel, BorderLayout.CENTER);
      add(ModelButton, BorderLayout.SOUTH);
      add(ViewButton, BorderLayout.NORTH);

      // Display the View
      setVisible(true);
   }

   // This method adds the button listener to the view
   public void addChangeNameToModel(ActionListener listenForButtonClick)
   {
      ModelButton.addActionListener(listenForButtonClick);
   }

   // This method adds the button listener to the view
   public void addChangeNameToView(ActionListener listenForButtonClick)
   {
      ViewButton.addActionListener(listenForButtonClick);
   }

   // Getters and Setters
   public String getTextLabel()
   {
      return textLabel.getText();
   }

   public void setTextLabel(String textLabel)
   {
      this.textLabel.setText(textLabel);
   }

   public String getViewButtonText()
   {
      return ViewButton.getText();
   }

   public void setViewButtonText(String str)
   {
      ViewButton.setText(str);
   }

   public String getModelButtonText()
   {
      return ModelButton.getText();
   }

   public void setModelButtonText(String str)
   {
      ModelButton.setText(str);
   }
}

/**
 * The Model class stores text
 */
class Model
{
   private String buttonText;

   Model()
   {
      buttonText = "";
   }

   // Getters and Setters
   public String getButtonText()
   {
      return buttonText;
   }

   public void setButtonText(String buttonText)
   {
      this.buttonText = buttonText;
   }
}

/**
 * Controller executes the logic and has the ActionListeners
 */
class Controller
{
   private View view;
   private Model model;

   Controller(View view, Model model)
   {
      this.view = view;
      this.model = model;

      // Connects the ActionListener Controller into View
      view.addChangeNameToView(new ChangeToView());
      view.addChangeNameToModel(new ChangeToModel());
   }

   // This is an inner class with action implemented to change
   class ChangeToView implements ActionListener
   {
      // This next method must be part of the ActionListener implementation
      public void actionPerformed(ActionEvent e)
      {
         // Sets the Model private text from view button
         model.setButtonText(view.getViewButtonText());

         // Sets the text label in view
         view.setTextLabel(model.getButtonText());
      }
   }

   // This is an inner class with action implemented
   class ChangeToModel implements ActionListener
   {
      // This next method must be part of the ActionListener implementation
      public void actionPerformed(ActionEvent e)
      {
         // Sets the Model text from view button
         model.setButtonText(view.getModelButtonText());

         // Sets the text label in view
         view.setTextLabel(model.getButtonText());
      }
   }

}
