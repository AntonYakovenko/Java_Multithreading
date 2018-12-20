package chatapp;

import java.io.*;
import java.net.Socket;

public class ChatClient
{
    public static void main(String[] args) throws Exception
    {
        Socket sock = new Socket("127.0.0.1", 3000);
        // reading from keyboard (keyRead object)
        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
        // sending to client (printWriter object)
        OutputStream outStream = sock.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outStream, true);

        // receiving from server ( receiveRead  object)
        InputStream inStream = sock.getInputStream();
        BufferedReader receiveRead = new BufferedReader(new InputStreamReader(inStream));

        System.out.println("Start the chitchat, type and press Enter key");

        String receiveMessage, sendMessage;
        while(true)
        {
            sendMessage = keyRead.readLine();  // keyboard reading
            printWriter.println(sendMessage);       // sending to server
            printWriter.flush();                    // flush the data
            if((receiveMessage = receiveRead.readLine()) != null) //receive from server
            {
                System.out.println(receiveMessage); // displaying at DOS prompt
            }
        }
    }
}