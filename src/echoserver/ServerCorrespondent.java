package echoserver;

//This class handles the details of echoing an individual client
//it implements runnable, and should be used from ServerCorrespondentPool

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class ServerCorrespondent implements Runnable{
  private final Socket socket;

  //constructor
  public ServerCorrespondent (Socket socket){
    this.socket = socket;
  }


  public void run() {
    System.out.println("Connection established");
    InputStream input = null;
    OutputStream output = null;
    boolean cont = true;

    try {
      input = socket.getInputStream();
      output = socket.getOutputStream();
      //we now have a connected client
      //Read its first byte
      Integer input_byte = input.read();
      System.out.println(input_byte);

      //if the present byte isn't null, continue reading and writing
      while (cont){
        output.write(input_byte);
        output.flush();
        input_byte = input.read();
        System.out.println(input_byte);

        if( input_byte == -1){
          cont = false;
        }
      }
      socket.shutdownOutput();
      System.out.println("Output closed");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
