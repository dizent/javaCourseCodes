package cn.dizent.javaCourseCodes.week02nio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: 布谷
 * @Date: 2021/7/6 15:10
 * @Description:
 */
public class HttpServer03 {


    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4);

        ServerSocket serverSocket = new ServerSocket(8803);
        while (true) {
            final Socket socket = serverSocket.accept();
            executorService.execute(()->{service(socket);});
        }
    }

    public static void service(Socket socket){
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio3";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println("");
            Thread.sleep(20);
            printWriter.write(body);
            printWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
