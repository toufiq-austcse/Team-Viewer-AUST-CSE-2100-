package serverproject;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class serverThread extends ServerProject {

    public serverThread() {
        
    }
    
    public static void sendImage() {
        Rectangle rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage image = robot.createScreenCapture(rect);
        ImageIcon imageIcon = new ImageIcon(image);
        try {
            System.out.println("before sending image");
            output.writeObject(imageIcon);
            output.reset(); //Clear ObjectOutputStream cache
            System.out.println("New screenshot sent");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        //wait for 100ms to reduce network traffic
        try {
            Thread.sleep(100);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    Thread imageThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true){
                sendImage();
            }
        }
    });

    public static String getMouseCoordinateActionKeyboard() throws IOException {
        String coordinateAction = null;
        try {
            coordinateAction = (String) input.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return coordinateAction;
    }
    
    public static void mouseMoveAction() throws AWTException, IOException {
        String mouseString = getMouseCoordinateActionKeyboard();
        String[] parts = mouseString.split(" ");
        int mouseX = Integer.parseInt(parts[0]);
        int mouseY = Integer.parseInt(parts[1]);
        robot.mouseMove(mouseX, mouseY-25);
        
        int mouseAction = Integer.parseInt(parts[2]);
        int button = Integer.parseInt(parts[3]);
        int click = Integer.parseInt(parts[4]);
        //char key = parts[5].charAt(0);
        //System.out.println(parts[5]);

        switch (button) {
            case 1:
                button = InputEvent.BUTTON1_DOWN_MASK;
                break;
            case 2:
                button = InputEvent.BUTTON2_DOWN_MASK;
                break;
            case 3:
                button = InputEvent.BUTTON3_DOWN_MASK;
                break;
            default: System.out.println("Nothing to do");break;
        }

        switch (mouseAction) {
            case 1: {
                if (click > 1) {
                    robot.mousePress(button);
                    robot.mouseRelease(button);
                    robot.mousePress(button);
                    robot.mouseRelease(button);
                    break;
                } else {
                    robot.mousePress(button);
                    robot.mouseRelease(button);
                    break;
                }
            }
            case 2: {
                robot.mousePress(button);
                break;
            }
            case 3: {
                robot.mouseRelease(button);
                break;
            }
            default: System.out.println("Nothing to do"); break;
        }
        
        System.out.println("X " + mouseX + " Y " + mouseY + " Action " + mouseAction + " Button " + button + " click " + click+" key "+parts[5]);
        
      
    }
    
    Thread mouseKeyboardThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true){
                try {
                mouseMoveAction();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(serverThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
                catch (AWTException | IOException ex) {
                Logger.getLogger(serverThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });
    
    public void startRunning() {
        imageThread.start();
        mouseKeyboardThread.start();
    }
}
