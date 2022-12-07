package main.java.de.telran.spring_load_balancer_server.entity;

public class Server {
    private final String host;
    private final int port;

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}