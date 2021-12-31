import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
public class ThreadHelloCountWithGUI
{
    private JPanel panel1;
    private JButton runThreadsButton;
    private JTextArea textArea1;
    private static JFrame frame = new JFrame("Thread");
    private static String text="";
    public ThreadHelloCountWithGUI()
    {
        runThreadsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ThreadCount threadCount = new ThreadCount();
                ThreadHello threadHello = new ThreadHello();
                Thread thread1 = new Thread(threadCount);
                Thread thread2 = new Thread(threadHello);
                thread1.start(); thread2.start(); textArea1.setText(text);
            }
        });
    }
    public static void main(String[] args)
    {
        frame.setContentPane(new ThreadHelloCountWithGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public static void UpdateText(String str)
    { text = text+ str + "\n"; }
}
class ThreadHello implements Runnable
{
    @Override
    public void run()
    {
        for (int i = 0; i < 5; i++)
        { ThreadHelloCountWithGUI.UpdateText(i + ""); }
    }
}
class ThreadCount implements Runnable
{
    @Override
    public void run()
    {
        for(int i = 0;i<5 ;i++)
        { ThreadHelloCountWithGUI.UpdateText("HI"); }
    }
}
