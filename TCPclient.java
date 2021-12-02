import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPclient {

    private static InetAddress host;
    private static  final  int PORT=1234;

    public static void main(String[] args) {

        try{

            host=InetAddress.getLocalHost();
        }
        catch (Exception ioex){
            System.out.println("Host ID not found!");
            System.exit(1);
        }
        accessServer();
    }
    private static void accessServer(){
        Socket link=null;
        try {
            link=new Socket(host,PORT);
            Scanner input=new Scanner(link.getInputStream());
            PrintWriter output=new PrintWriter(link.getOutputStream(),true);
            Scanner inputUser=new Scanner(System.in);
            String userName,passWord,rspns;
              do{
                  System.out.print("Enter Your Username: ");
                  userName=inputUser.nextLine();
                  output.println(userName);
                  rspns=input.nextLine();
                  if(!rspns.equals("DONE"))
                  System.out.println("\nSERVER> "+rspns);
              }while (rspns.equals("Wrong Username, Try again...."));

             do {
                  System.out.print("Enter Your Password: ");
                  passWord=inputUser.nextLine();
                  output.println(passWord);
                  rspns=input.nextLine();
                  System.out.println("\nSERVER> "+rspns);
              }while (rspns.equals("Wrong Password, Try again...."));
             String msg;
             do {
                 System.out.println(rspns);
                 System.out.print("Enter your message: ");
                 msg = inputUser.nextLine();
                 output.println(msg);
                 rspns = input.nextLine();
                 System.out.println("Server> " + rspns);
             }while(!msg.equals("***CLOSE***"));


        }
        catch (Exception ex){
            System.out.println("Error");
        }finally {
            try {
                System.out.println("CLOSING CONNECTION ...");
                link.close();
            } catch (IOException e) {
                System.out.println("Unable to disconnect");
                System.exit(1);
            }

        }

    }
}
