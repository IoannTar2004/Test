import java.io.*;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class Box {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Selector selector = Selector.open();
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(5454));
        channel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(100);
        channel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator;
            for (iterator = keys.iterator(); iterator.hasNext();) {
                SelectionKey key = iterator.next();
                if (key.isValid()) {
                    if (key.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(key.selector(), SelectionKey.OP_READ);
                    }
                    if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        while (socketChannel.read(buffer) != -1) ;
//                        byte[] bytes = new byte[data];
//                        System.arraycopy(buffer.array(), 0, data, 0, data);
                        System.out.println(buffer.array());
                    }
                }
            }
        }
    }
}
