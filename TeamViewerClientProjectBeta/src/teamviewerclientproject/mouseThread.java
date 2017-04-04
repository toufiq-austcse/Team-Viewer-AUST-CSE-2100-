/*package teamviewerclientproject;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mouseThread extends Thread{
    public Canvas obT = new Canvas();
    public boolean f = true;

    public void startRunning() {

        try {
            obT.connectToServer();
            obT.setUpStream();

            while (f) {
                obT.receiveImage();
                obT.mouse();
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        startRunning();
    }
}*/
