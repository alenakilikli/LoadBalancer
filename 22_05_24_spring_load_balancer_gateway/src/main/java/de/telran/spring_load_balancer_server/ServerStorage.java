package main.java.de.telran.spring_load_balancer_server;

@Component
public class ServerStorage {
    private Server server;

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}