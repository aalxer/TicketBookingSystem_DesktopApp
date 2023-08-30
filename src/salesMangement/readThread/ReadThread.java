package salesMangement.readThread;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadThread extends Thread {

    private final ReadThreadListener listener;
    private final InputStream is;

    public ReadThread(ReadThreadListener readListener, InputStream is) {
        this.listener = readListener;
        this.is = is;
    }

    @Override
    public void run() {
        try {
            while (true) {
                DataInputStream dis = new DataInputStream(this.is);
                boolean input = dis.readBoolean();
                this.listener.updated();
            }
        } catch (IOException e) {
            this.listener.verbindungSchliessen();
            e.printStackTrace();
        }
    }
}