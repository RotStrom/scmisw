package d3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * to check this out you can simple connect to socket in bash
 *
 * connect:
 * $ exec 3<>/dev/tcp/localhost/4567
 *
 * write:
 * $ echo hi >&3
 *
 * read:
 * $ timeout 1 cat <&3
 *
 * disconnect:
 * $ exec 3<&-
 * $ exec 3>&-
 * */
public class EchoServerWithPool {
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

//        int threadPoolSize = Runtime.getRuntime().availableProcessors() * 2;
        int threadPoolSize = 1;
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        while (true) {
            Socket socket = server.accept();
            executor.execute(new ConnectionHandler(socket));
        }
    }
}
