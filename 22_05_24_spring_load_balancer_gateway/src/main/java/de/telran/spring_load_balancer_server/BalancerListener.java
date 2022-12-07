package main.java.de.telran.spring_load_balancer_server;
@Component
public class BalancerListener {

    private final int fromBalancerUdpPort;
    private final ServerStorage serverStorage;

    public BalancerListener(@Value("${udp.balancer.inbound.port}")int fromBalancerUdpPort,
                            ServerStorage serverStorage) {
        this.fromBalancerUdpPort = fromBalancerUdpPort;
        this.serverStorage = serverStorage;
    }


    @Async("threadExecutor")
    public void run() {
        try (DatagramSocket datagramSocket = new DatagramSocket(fromBalancerUdpPort)) {
            byte[] data = new byte[1024];
            DatagramPacket packetIn = new DatagramPacket(data, data.length);

            while (true) {
                datagramSocket.receive(packetIn);
                String optimalServerData = new String(data, 0, packetIn.getLength());

                System.out.println(optimalServerData);

                String[] parts = optimalServerData.split(":");
                String serverHost = parts[0];
                int serverPort = Integer.parseInt(parts[1]);

                serverStorage.setServer(new Server(serverHost, serverPort));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}