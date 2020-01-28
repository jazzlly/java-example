import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.io.IOException;

public class ChargenClient {

  public static void main(String[] args) {
    
    try {
      SocketAddress address = new InetSocketAddress("localhost", 8888);
      SocketChannel client = SocketChannel.open(address);
      
      ByteBuffer buffer = ByteBuffer.allocate(74);
      WritableByteChannel out = Channels.newChannel(System.out);
      
      while (client.read(buffer) != -1) {
        buffer.flip();
        out.write(buffer);
        buffer.clear();
      }     
    } catch (IOException ex) {
      ex.printStackTrace();   
    }
  }
}