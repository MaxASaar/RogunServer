import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Server {

    private boolean listening = false;
    private ServerSocket serverSocket;
    private Socket socket;
    private int port;
    private Thread listenThread;

    private final int MAX_PACKET_SIZE = 1024;

    private byte[] receivedDataBuffer = new byte[MAX_PACKET_SIZE*10];

    public Server(int port){
        this.port = port;
    }

    public void start(){

        try{
            serverSocket = new ServerSocket(port);
        }catch(IOException e){
            e.printStackTrace();
        }
        listening = true;
        listenThread = new Thread(() -> listen());
        listenThread.start();
        System.out.println("Server running on port: " + port);
    }

    private void listen() {
        while(listening){
            try {
                Socket connectionSocket = serverSocket.accept();
                connectionSocket.setSoTimeout(5000); // 5s timeout on read
                System.out.println("Got a message from a client!");
                BufferedReader inFromClient =
                        new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                System.out.println("hmm");
                // DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

                // clientSentence = inFromClient.readLine();
                System.out.println("Received: " + inFromClient.readLine());
                // outToClient.writeBytes();
            }catch(IOException e){
                e.printStackTrace();
            }
            // process(packet);
        }
    }

    public void process(DatagramPacket packet){
        // If RCDB
        byte [] data = packet.getData();
        System.out.println("Received a Packet: " + new String(data, 0, data.length));
        if(new String(data, 0, 4).equals("RCDB")){

        }
    }

    public void send(byte[] data, InetAddress address, int port){
        assert(socket.isConnected());
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
//        try{
//            // socket.send(packet);
//        }catch(IOException e){
//            e.printStackTrace();
//        }
    }
}
