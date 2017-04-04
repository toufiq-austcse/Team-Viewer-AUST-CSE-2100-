package teamviewerclientproject;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class clientThread {

    public Canvas canvasObject = new Canvas();
    public boolean f = true;

    Thread imageThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (f) {
                try {
                    canvasObject.receiveImage();
                } 
                catch (IOException ex) {
                    Logger.getLogger(clientThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });

    Thread mouseKeyboardThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (f) {
                try {
                    canvasObject.mouseKeyboard();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(clientThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
                catch (IOException ex) {
                    Logger.getLogger(clientThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });

    public void startRunning() {
        try {
            canvasObject.connectToServer();
            canvasObject.setUpStream();
            imageThread.start();
            mouseKeyboardThread.start();
        } catch (IOException ex) {
            Logger.getLogger(clientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
