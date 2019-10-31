package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Piper implements Runnable{
  private final InputStream source;
  private final OutputStream destination;
  private final boolean shouldCleanUp;

  public  Piper (InputStream source, OutputStream destination){
    this.source = source;
    this.destination = destination;
    shouldCleanUp = false;
  }

  public  Piper (InputStream source, OutputStream destination,boolean shouldCleanUp){
    this.source = source;
    this.destination = destination;
    this.shouldCleanUp = shouldCleanUp;
  }

  @Override
  public void run() {
    try {
      boolean cont = true;
      while (cont) {
        //the core of the minion
        Integer present_byte = source.read();
        if (present_byte != -1) {
          destination.write(present_byte);
        } else{
          destination.write((byte)0x14);
          cont = false;
        }

        if (present_byte == -1){
          cont = false;
          }
        }
      //if we are responsible for turning things off...
      if(shouldCleanUp){
        System.out.println("WHOOPSIE DOODLES!");
        System.exit(0);

      }
    } catch (IOException ex){
      System.err.println(ex);
    }
  }
}
