package file;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    String msg = "";
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.contains("?msg=") || str.contains("&msg=")) {
                            msg = str;
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (msg.contains("?msg=Exit") || msg.contains("&msg=Exit")) {
                        out.write("Bye".getBytes());
                        server.close();
                    } else if (msg.contains("?msg=Hello") || msg.contains("&msg=Hello")) {
                        out.write("Hello, dear friend.".getBytes());
                    } else {
                        out.write("What".getBytes());
                    }
                }
            }
        }
    }
}
