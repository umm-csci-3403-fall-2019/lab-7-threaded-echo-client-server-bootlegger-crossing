package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class KeyboardPiper implements Runnable{
  private final InputStream source;
  private final OutputStream destination;
  private final Socket socket;


  public  KeyboardPiper (InputStream source, OutputStream destination, Socket socket){
    this.source = source;
    this.destination = destination;
    this.socket = socket;
  }

  @Override
  public void run() {
    try {
      Integer present_byte = null;
      //Nic opinion requested v
      while ((present_byte = source.read()) != -1) {
        //the core of the minion
        destination.write(present_byte);
      }
      //as we are responsible for signaling termination...
      socket.shutdownOutput();
    } catch (IOException ex){
      System.err.println(ex);
    }
  }
}
