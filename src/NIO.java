
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIO {
    public static void main(String[] args) throws IOException {
//        String str="你好，易家洛222222222";
//        FileOutputStream fileOutputStream=new FileOutputStream("d://test.txt");
//        FileChannel fileChannel=fileOutputStream.getChannel();
//        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
//        byteBuffer.put(str.getBytes());
//        byteBuffer.flip();
//        fileChannel.write(byteBuffer);
//        fileOutputStream.close();

//        File file=new File("d://test.txt");
//        FileInputStream fileInputStream=new FileInputStream(file);
//
//        FileChannel fileChannel=fileInputStream.getChannel();
//        System.out.println(file.length());
//        ByteBuffer byteBuffer=ByteBuffer.allocate((int)file.length());
//        fileChannel.read(byteBuffer);
//
//        System.out.println(new String(byteBuffer.array()));
//        fileInputStream.close();

//        FileInputStream fileInputStream=new FileInputStream("1.txt");
//        FileChannel fileChannel01=fileInputStream.getChannel();
//
//        FileOutputStream fileOutputStream=new FileOutputStream("2.txt");
//        FileChannel fileChannel02=fileOutputStream.getChannel();
//
//        ByteBuffer byteBuffer=ByteBuffer.allocate(512);
//
//        while (true){
//            byteBuffer.clear();
//            int read=fileChannel01.read(byteBuffer);
//            if (read==-1)
//                break;
//            byteBuffer.flip();
//            fileChannel02.write(byteBuffer);
//        }
//        fileInputStream.close();
//        fileOutputStream.close();
//
//        FileInputStream fileInputStream = new FileInputStream("1.txt");
//        FileChannel fileChannel01=fileInputStream.getChannel();
//
//        FileOutputStream fileOutputStream=new FileOutputStream("yijaluo.txt");
//        FileChannel fileChannel02=fileOutputStream.getChannel();
//
//        fileChannel02.transferFrom(fileChannel01,0,fileChannel01.size());
//
//        fileInputStream.close();
//        fileOutputStream.close();
//        fileChannel01.close();
//        fileChannel02.close();



    }
}
