import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class IS {

    ServerSocket server = null;
    Socket client = null;

    public static void main(String[] args) {
        new IS().sendAndRecv();
    }

    public void sendAndRecv() {
        try {
            server = new ServerSocket(8003);
            while (true) {
                client = server.accept();
                OutputStream os = client.getOutputStream();
                InputStream is = client.getInputStream();
                int size = is.available();
                byte[] a = new byte[size];
                is.read(a);
                String strque = new String(a);
                System.out.println(strque);
                os.write("lisi he zhangsan ye!".getBytes());
                os.flush();
            }
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}