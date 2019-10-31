package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {
	public static final int PORT_NUMBER = 6013;

	public static void main(String[] args) throws IOException, InterruptedException {
		EchoServer server = new EchoServer();
		server.start();
	}

	private void start() throws IOException, InterruptedException {

		//the ServerSocket we are listening to
		final ServerSocket mainSocket;

		//our actual threadpool
		final ExecutorService pool;

		//setup main socket
		mainSocket = new ServerSocket(this.PORT_NUMBER);

		//initialize pool
		pool = Executors.newFixedThreadPool(8);

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

