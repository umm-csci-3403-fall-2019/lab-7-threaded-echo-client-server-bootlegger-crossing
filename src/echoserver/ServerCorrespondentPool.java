package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import echoserver.ServerCorrespondent;

// This class handles accepting connections and dispatching them into threads
// It maintains a threadpool, and has configuration details setup through it's contstructor

public class ServerCorrespondentPool implements Runnable {
  //the ServerSocket we are listening to
  final ServerSocket mainSocket = null;

  //The server port we are listening on
  public final int PORT_NUMBER;

  //our actual threadpool
  private final ExecutorService pool;

  //our constructor
  ServerCorrespondentPool(int PORT_NUMBER){
    mainSocket = new ServerSocket(PORT_NUMBER);
    this.PORT_NUMBER = PORT_NUMBER;
    pool = Executors.newFixedThreadPool(8);
  }

  public void run(){
    try {
      while(true){
        pool.execute(new ServerCorrespondent(mainSocket.accept()));
      }
    } catch (IOException ex){
      System.err.println(ex);
      pool.shutdown();
    }
  }
}
