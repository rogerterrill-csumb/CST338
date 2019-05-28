import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


class Assig4 {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;


    public static void main(String[] args) {
        JFrame firstWindow = new JFrame();
        firstWindow.setSize(WIDTH, HEIGHT);
        firstWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JButton endButton = new JButton("Click to end program.");
        EndingListener buttonEar = new EndingListener();
        endButton.addActionListener(buttonEar);
        firstWindow.add(endButton);

        firstWindow.setVisible(true);

    }
}

class EndingListener implements ActionListener
{
    public void actionPerformed(ActionEvent e)
    {
        System.exit(0);
    }
}