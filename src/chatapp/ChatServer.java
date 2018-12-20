package chatapp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer
{
    public static void main(String[] args) throws Exception
    {
        ServerSocket serverSocket = new ServerSocket(3000);
        System.out.println("Server ready for chatting");
        Socket sock = serverSocket.accept( );
        // reading from keyboard (keyRead object)
        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
        // sending to client (printWriter object)
        OutputStream outStream = sock.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outStream, true);

        // receiving from server ( receiveRead  object)
        InputStream inStream = sock.getInputStream();
        BufferedReader receiveRead = new BufferedReader(new InputStreamReader(inStream));

        String receiveMessage, sendMessage;
        while(true)
        {
            if((receiveMessage = receiveRead.readLine()) != null)
            {
                System.out.println(receiveMessage);
            }
            sendMessage = keyRead.readLine();
            printWriter.println(sendMessage);
            printWriter.flush();
        }
    }
}