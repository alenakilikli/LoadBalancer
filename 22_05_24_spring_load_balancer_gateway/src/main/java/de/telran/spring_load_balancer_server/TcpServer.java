package main.java.de.telran.spring_load_balancer_server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class TcpServer{
    private final int selfTcpPort;
    private final ServerStorage serverStorage;


    public TcpServer(@Value("${tcp.outer.port}") int selfTcpPort,
                     ServerStorage serverStorage) {
        this.selfTcpPort = selfTcpPort;
        this.serverStorage = serverStorage;
    }

    @Async("threadExecutor")
    public void run() {
        try(ServerSocket clientSocket = new ServerSocket(selfTcpPort)){

            while(true){
                Socket socket = clientSocket.accept();
                TcpServerTask task = new TcpServerTask(socket,serverStorage);
                task.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}