import javax.swing.*;
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
    private JButton nameChanger;

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
        nameChanger = new JButton("ChangeName");

        // Add the Components
        add(firstName, BorderLayout.CENTER);
        add(nameChanger, BorderLayout.SOUTH);

        // Display the View
        setVisible(true);
    }

    // This method adds the button listener to the view
    public void addChangeNameListener(ActionListener listenForButtonClick)
    {
        nameChanger.addActionListener(listenForButtonClick);
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
        view.addChangeNameListener(new ChangeName());
    }

    // This is an inner class with action implemented
    class ChangeName implements ActionListener
    {
        // This next method must be part of the ActionListener implementation
        public void actionPerformed(ActionEvent e)
        {
            view.setFirstName(model.getFirstName());
        }
    }


    public void updateText()
    {
        view.setFirstName(model.getFirstName());
    }
}
