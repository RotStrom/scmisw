package d3;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        class ConnectionHandler implements Runnable {
            private InputStream in;
            private OutputStream out;

            private ConnectionHandler(Socket socket) throws IOException {
                in = socket.getInputStream();
                out = socket.getOutputStream();
            }

            @Override
            public void run() {
                try {
                    System.out.println("client connected");
                    int n;
                    byte[] buffer = new byte[1024];
                    while ((n = in.read(buffer)) != -1) {
                        System.out.write(buffer, 0, n);
                        System.out.flush();
                        out.write(buffer, 0, n);
                        out.flush();
                    }
                } catch (IOException e) {
                    // ...
                }
            }
        }

        ServerSocket server = new ServerSocket(4567);
        while (true) {
            Socket socket = server.accept();
            Thread handler = new Thread(new ConnectionHandler(socket));
            handler.start();
        }
    }
}
