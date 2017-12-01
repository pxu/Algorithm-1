package JavaFund;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by zhahua on 10/9/17.
 */
public class SocketStreamReadTimeout {
  public static void main(String[] args) {
    String hostName = "localhost";
    int portNumber = 8080;

    try (
        Socket echoSocket = new Socket();

        BufferedReader in =
            new BufferedReader(
                new InputStreamReader(echoSocket.getInputStream()));
    ){
      echoSocket.connect(new InetSocketAddress(hostName, portNumber),10000);
      echoSocket.setSoTimeout(5000);
      while(true) {
        System.out.print(Character.valueOf((char) in.read()));
      }
    }catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
