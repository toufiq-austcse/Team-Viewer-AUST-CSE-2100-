package teamviewerclientproject;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.SysexMessage;
import javax.swing.ImageIcon;

public class Canvas extends javax.swing.JFrame {

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;
    private String ip;
    private int xMouse=0,yMouse=0,mouseAction=0,button=0,click=0;
    public boolean f = true;
    public int key=0;

    public Canvas() {
        initComponents();
    }

    public void setIp(String Ip) {
        this.ip = Ip;
    }

    public void connectToServer() throws IOException {
        System.out.println("connecting...");
        connection = new Socket(InetAddress.getByName(ip), 6789);
        System.out.println("connected");
    }

    public void setUpStream() throws IOException {
        input = new ObjectInputStream(connection.getInputStream());
        System.out.println("INPUT Stream set up");
        output = new ObjectOutputStream(connection.getOutputStream());
        System.out.println("OUTPUT Stream set up");
    }

    public void receiveImage() throws IOException {
        try {
            ImageIcon imageIcon = (ImageIcon) input.readObject();
            Image image = imageIcon.getImage();
            image = image.getScaledInstance(jPanel2.getWidth(), jPanel2.getHeight(), Image.SCALE_FAST);
            Graphics graphics = jPanel2.getGraphics();
            graphics.drawImage(image, 0, 0, jPanel2.getWidth(), jPanel2.getHeight(), jPanel2);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void mouseKeyboard() throws IOException {
        /*PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        int x = (int) b.getX();
        int y = (int) (b.getY()-25);*/
        
        String coordinateAction =xMouse+" "+yMouse+" "+mouseAction+" "+button+" "+click+" "+key;
        System.out.println(coordinateAction);
        output.writeObject(coordinateAction);
        output.flush();
        System.out.println("coordinate and action send to server");
        mouseAction=0;
        click=0;
        key=0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();

        jPanel2.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel2MouseMoved(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel2MouseReleased(evt);
            }
        });
        jPanel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPanel2KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        button=evt.getButton();
        mouseAction=1;
        click=evt.getClickCount();
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        mouseAction=2;
        button=evt.getButton();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseReleased
        mouseAction=3;
        button=evt.getButton();
    }//GEN-LAST:event_jPanel2MouseReleased

    private void jPanel2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyTyped
        key=evt.getKeyCode();
    }//GEN-LAST:event_jPanel2KeyTyped

    private void jPanel2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseMoved
        xMouse=evt.getXOnScreen();
        yMouse=evt.getYOnScreen();
    }//GEN-LAST:event_jPanel2MouseMoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}
