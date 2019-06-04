import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ButtonTest
{
   public static void main(String[] args)
   {
      FirstWindow w = new FirstWindow();
      w.setVisible(true);
   }
}

class FirstWindow extends JFrame
{
   public static void main(String[] args)
   {

   }
   public static final int WIDTH = 300;
   public static final int HEIGHT = 200;

   public FirstWindow()
   {
      super();
      setSize(WIDTH, HEIGHT);

      setTitle("First Window Class");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JButton endButton = new JButton("Click to end program");
      endButton.addActionListener(new EndingListener());
      add(endButton);
   }
}

class EndingListener implements ActionListener
{
   public void actionPerformed(ActionEvent e)
   {

      System.out.println(e.getActionCommand());
   }
}


