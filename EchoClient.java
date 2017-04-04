
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public final class EchoClient {

	public static void main(String[] args) throws Exception {
		try (Socket socket = new Socket("localhost", 22222)) {
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			BufferedReader uBr = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(br.readLine());

			OutputStream os = socket.getOutputStream();
			PrintStream out = new PrintStream(os, true, "UTF-8");

			System.out.print("Client> ");
			String text;
			while (!(text = uBr.readLine()).equalsIgnoreCase("exit")) {
				out.println(text);
				
				System.out.println("Server> " + br.readLine());
				System.out.print("Client> ");
			}
			out.println("exit");
		}
	}
}
