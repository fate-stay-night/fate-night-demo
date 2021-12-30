package cn.fatenight;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * http
 *
 * @author zhangzheng
 * @version 1.0.0
 * @date 2021/10/29
 */
public class HttpService {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = new ThreadPoolExecutor(1, 5, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), r -> new Thread(new ThreadGroup("http-service"), r));

        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket accept = serverSocket.accept();
            if (Objects.isNull(accept)) {
                continue;
            }

            executor.execute(new Handler(accept));
            TimeUnit.SECONDS.sleep(1);
        }
    }
}

class Handler extends Thread {

    Socket sock;

    public Handler(Socket sock) {
        this.sock = sock;
    }

    @Override
    public void run() {
        System.out.println("####################" + Thread.currentThread().getName());
        try (InputStream input = this.sock.getInputStream()) {
            try (OutputStream output = this.sock.getOutputStream()) {
                handle(input, output);
            }
        } catch (Exception e) {
            try {
                this.sock.close();
            } catch (IOException ioe) {
                System.out.println("client disconnected error.");
            }
            System.out.println("client disconnected.");
        }
    }

    private void handle(InputStream input, OutputStream output) throws IOException {
        System.out.println("Process new http request...");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        // 读取HTTP请求:
        boolean requestOk = false;
        String first = reader.readLine();
        System.out.println("$$$$$$$$$$$$$$$$" + first);
        if (first.startsWith("GET / HTTP/1.")) {
            requestOk = true;
        }
        for (; ; ) {
            String header = reader.readLine();
            // 读取到空行时, HTTP Header读取完毕
            if (header.isEmpty()) {
                break;
            }
            System.out.println(header);
        }
        System.out.println(requestOk ? "Response OK" : "Response Error");
        if (!requestOk) {
            // 发送错误响应:
            writer.write("HTTP/1.0 404 Not Found\r\n");
            writer.write("Content-Length: 0\r\n");
            writer.write("\r\n");
            writer.flush();
            return;
        }

        // 发送成功响应:
        String data = "<html><body><h1>Hello, world!</h1></body></html>";
        int length = data.getBytes(StandardCharsets.UTF_8).length;
        writer.write("HTTP/1.0 200 OK\r\n");
        writer.write("Connection: close\r\n");
        writer.write("Content-Type: text/html\r\n");
        writer.write("Content-Length: " + length + "\r\n");
        // 空行标识Header和Body的分隔
        writer.write("\r\n");
        writer.write(data);
        writer.flush();
    }
}

