import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class IC {
    Socket client = null;

    public static void main(String[] args) {
        new IC().sendAndRecv();
    }

    public void sendAndRecv() {
        try {
            client = new Socket("127.0.0.1", 8003);
            OutputStream os = client.getOutputStream();
            InputStream is = client.getInputStream();
            os.write("zhangsan he li si is firend".getBytes());
            os.flush();
            int size = is.available();
            byte[] a = new byte[size];
            is.read(a);
            String strque = new String(a);
            System.out.println(strque);
        } catch (UnknownHostException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
} 