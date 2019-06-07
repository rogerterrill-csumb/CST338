import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {

        View view = new View();

        Model model = new Model();

        Controller controller = new Controller(view, model);
    }
}

class View extends JFrame
{
    // First component
    private JLabel firstName, lastName;
    private JButton RogerModelButton, RogerViewButton;

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
        firstName = new JLabel("RogerView" );

        // Create button
        RogerModelButton = new JButton("RogerModel");
        RogerViewButton = new JButton("RogerView");

        // Add the Components
        add(firstName, BorderLayout.CENTER);
        add(RogerModelButton, BorderLayout.SOUTH);
        add(RogerViewButton, BorderLayout.NORTH);

        // Display the View
        setVisible(true);
    }

    // This method adds the button listener to the view
    public void addChangeNameToModel(ActionListener listenForButtonClick)
    {
        RogerModelButton.addActionListener(listenForButtonClick);
    }

    // This method adds the button listener to the view
    public void addChangeNameToView(ActionListener listenForButtonClick)
    {
        RogerViewButton.addActionListener(listenForButtonClick);
    }

    // Getters and Setters
    public String getFirstName()
    {
        return firstName.getText();
    }

    public void setFirstName(String firstName)
    {
        this.firstName.setText(firstName);
    }

    public String getViewButtonText()
    {
        return RogerViewButton.getText();
    }

    public void setViewButtonText(String str)
    {
        RogerModelButton.setText(str);
    }

    public String getModelButtonText()
    {
        return RogerViewButton.getText();
    }

    public void setModelButtonText(String str)
    {
        RogerModelButton.setText(str);
    }
}

class Model
{
    private String firstName, lastName;

    Model()
    {
        firstName = "RogerModel";
    }

    // Getters and Setters
    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
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

    // This is an inner class with action implemented
    class ChangeToView implements ActionListener
    {
        // This next method must be part of the ActionListener implementation
        public void actionPerformed(ActionEvent e)
        {
            view.setFirstName("View");
        }
    }

    // This is an inner class with action implemented
    class ChangeToModel implements ActionListener
    {
        // This next method must be part of the ActionListener implementation
        public void actionPerformed(ActionEvent e)
        {
            view.setFirstName("Model");
        }
    }

}
