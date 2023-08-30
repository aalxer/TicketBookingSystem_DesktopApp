package tcpThread;

import java.io.IOException;

public interface TCPStreamCreatedListener {
    void streamCreated(TCPStream channel) throws IOException;
}