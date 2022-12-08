package init;
import java.net.InetSocketAddress;

public class ServerConfiguration {

    private static ServerConfiguration severConfigurationInstance;

    private final InetSocketAddress serverAddress;
    private final String greetingMessage;

    private ServerConfiguration(int port, String greetingMessage) {
        this.greetingMessage = greetingMessage;
        this.serverAddress = new InetSocketAddress("localhost", port);
    
        if (severConfigurationInstance == null) {
            severConfigurationInstance = this;
        }
    }
    
    public static ServerConfiguration getInstance() {
        return severConfigurationInstance;
    }

    public InetSocketAddress getSeverAddress() {
        return serverAddress;
    }

    public String getGreetingMessage() {
        return greetingMessage;
     }

}
