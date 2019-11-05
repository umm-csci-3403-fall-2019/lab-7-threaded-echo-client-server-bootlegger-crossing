package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ScreenPiper implements Runnable{
  private final InputStream source;
  private final OutputStream destination;

  public ScreenPiper(InputStream source, OutputStream destination){
    this.source = source;
    this.destination = destination;
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
      //as we are responsible for turning things off...
      destination.flush();
      source.close();

    } catch (IOException ex){
      System.err.println(ex);
    }
  }
}
