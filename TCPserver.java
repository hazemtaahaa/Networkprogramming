
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPserver {
    private static ServerSocket serverSocket;
    private static final int PORT = 1234;

    public static void main(String[] args) {

        System.out.println("Opening port ..." );
        try{
            serverSocket=new ServerSocket(PORT);

        }
        catch (Exception ex){
            System.out.println("Failed to connect to port: " + PORT);
            System.out.println("Please try another port");
            System.exit(1);
        }
        while (true){
            handelConnection();
        }
    }

private static void handelConnection(){
        Socket link=null;
        try{
            link=serverSocket.accept();
           Scanner input=new Scanner(link.getInputStream());
            Scanner userInput = new Scanner(System.in);
           PrintWriter output=new PrintWriter(link.getOutputStream(),true);
           String userName="Hazem",passWord="12345678";
           String user=input.nextLine();
           while(!user.equals(userName)){
               output.println("Wrong Username, Try again....");
               user=input.nextLine();
           }

            output.println("DONE");
            String pass=null;
            pass=input.nextLine();

           while(!pass.equals(passWord)) {
               output.println("Wrong Password, Try again....");
               pass = input.nextLine();
           }
           output.println("Welcome Hazem...");
            String msg=input.nextLine();
            int msg_num=0;
            while(!msg.equals("***CLOSE***")){
                System.out.print("Message Received");
                msg_num++;
                output.println("Message "+msg_num+": "+msg);
                msg=input.nextLine();

            }
            output.println("Messages received is "+msg_num);

        }catch ( IOException ioex){
               ioex.printStackTrace();
        }finally {
            try {
                System.out.println("\n*Closing Connection...*\n");
                link.close();
            } catch (IOException ioex) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }



}
}
