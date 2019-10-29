package echoserver;

//This class handles the details of echoing an individual client
//it implements runnable, and should be used from ServerCorrespondentPool

class ServerCorrespondent implements Runnable{
  private final Socket socket;

  //constructor
  ServerCorrespondent (Socket socket){
    this.socket = socket;
  }

  public void run(){
    System.out.println("Connection established");
    InputStream input = socket.getInputStream();
    OutputStream output = socket.getOutputStream();

    //we now have a connected client
    //Read its first byte
    Integer input_byte = input.read();
    System.out.println(input_byte);
    //if the present byte isn't null, continue reading and writing
    while (input_byte != -1){
      output.write(input_byte);
      output.flush();
      input_byte = input.read();
      System.out.println(input_byte);
    }
    System.out.println("Connection closed");
  }

}
