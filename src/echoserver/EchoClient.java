package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {
	public static final int PORT_NUMBER = 6013;

	public static void main(String[] args) throws IOException {
		EchoClient client = new EchoClient();
		client.start();
	}

	private void start() throws IOException {
		Socket socket = new Socket("localhost", PORT_NUMBER);
		InputStream socketInputStream = socket.getInputStream();
		OutputStream socketOutputStream = socket.getOutputStream();
		//our two hot-potato playing minions
		Thread keyboardPiper = new Thread(new KeyboardPiper(System.in,socketOutputStream, socket));
		Thread screenPiper = new Thread(new ScreenPiper(socketInputStream,System.out));

		//Minions, start your engines
		screenPiper.start();
		keyboardPiper.start();
	}
}
