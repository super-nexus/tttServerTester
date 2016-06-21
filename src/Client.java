import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by Andrija on 6/21/16.
 */
public class Client {


    private Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    private Scanner scanner;

    private String ip;

    private Request request;
    private requestPrinter requestPrinter;

    int x = 1;


    public Client(String ip){

        this.ip = ip;

        request = new Request();

        scanner = new Scanner(System.in);

        requestPrinter = new requestPrinter();

    }


    public void startRunning(){

        try{

            connectToServer();
            setUpStreams();

            setUpRequest();

            sendingData();

        }catch (IOException e){
            e.printStackTrace();
        }

        finally {
            try {
                connection.close();
                input.close();
                output.flush();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }


    public void connectToServer()throws IOException {
        System.out.println("Connecting to " + ip);

        connection = new Socket(InetAddress.getByName(ip), 4444);

        System.out.println("Connected to "+ connection.getInetAddress().getHostName());

    }

    public void setUpStreams()throws IOException{

        output = new ObjectOutputStream(connection.getOutputStream());

        output.flush();

        input = new ObjectInputStream(connection.getInputStream());

        System.out.println("Streams are ready");

    }

    public void setUpRequest(){

        request.setRequestType(serverConstants.LOGIN_REQUEST);

        request.setWantedFriendName("tester1");

        request.setUsername("tester2");

        request.setPassword("tester1@gmail.com");

        request.setPassword("1234");

        request.setId(1);

    }

    public void sendingData(){



            try{

                do {

                    System.out.println("send or not");

                    if (scanner.next().equals("n")) {continue;}
                    else {
                        output.writeObject(request);

                        output.flush();

                        System.out.println("Just sent a request");
                    }

                    System.out.println("Waiting for response");

                    Request messageRecieved = (Request) input.readObject();

                    System.out.println("messageRecieved! :)");

                    requestPrinter.printRequest(messageRecieved);

                }while (x == 1);

            }catch (IOException e){
                e.printStackTrace();
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }




    }

}
