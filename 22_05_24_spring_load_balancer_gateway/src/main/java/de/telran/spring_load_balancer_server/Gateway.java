package main.java.de.telran.spring_load_balancer_server;

@Component
public class Gateway implements CommandLineRunner {

    private final TcpServer server;
    private final BalancerListener listener;

    public Gateway(TcpServer server, BalancerListener listener) {
        this.server = server;
        this.listener = listener;
    }

    @Override
    public void run(String... args) throws Exception {
        server.run();
        listener.run();
    }
}