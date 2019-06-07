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

class Model
{
   private String viewText, modelText;

   Model()
   {
      viewText = "";
      modelText = "";
   }

   // Getters and Setters
   public String getViewText()
   {
      return viewText;
   }

   public void setViewText(String viewText)
   {
      this.viewText = viewText;
   }

   public String getModelText()
   {
      return modelText;
   }

   public void setModelText(String modelText)
   {
      this.modelText = modelText;
   }
}


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
         model.setModelText(view.getViewButtonText());
         view.setTextLabel(model.getModelText());
      }
   }

   // This is an inner class with action implemented
   class ChangeToModel implements ActionListener
   {
      // This next method must be part of the ActionListener implementation
      public void actionPerformed(ActionEvent e)
      {
         model.setModelText(view.getModelButtonText());
         view.setTextLabel(model.getModelText());
      }
   }

}
