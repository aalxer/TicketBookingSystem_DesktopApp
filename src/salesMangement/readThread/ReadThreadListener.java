package salesMangement.readThread;

import java.io.IOException;

public interface ReadThreadListener {

    void updated() throws IOException;
    void verbindungSchliessen();
}
