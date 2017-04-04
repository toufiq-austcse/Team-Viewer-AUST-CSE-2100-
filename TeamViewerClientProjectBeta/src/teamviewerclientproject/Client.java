/*Author
Ahsanullah University  of Scinece And Technology
Software Development-II

Md.TOufiqul Islam
Md. Ashiqul Islam
Kazi Alif Haider
Rahat Mushfiq Abir


*/

package teamviewerclientproject;

public class Client extends javax.swing.JFrame {

    private String ip;
    private static boolean f = true;

    public Client() {
        initComponents();
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public clientThread objectClientThread = new clientThread();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 204));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setBorder(null);
        jTextField1.setOpaque(false);
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 220, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamviewerclientproject/Disconnect.png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, 160, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamviewerclientproject/connect.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 150, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamviewerclientproject/Background.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       if (f) {
            setIp(jTextField1.getText());
            objectClientThread.canvasObject.setIp(ip);
            objectClientThread.f = true;
            objectClientThread.canvasObject.setVisible(f);
            f = !f;
            objectClientThread.startRunning();
        } 
        else {
            objectClientThread.imageThread.resume();
            objectClientThread.mouseKeyboardThread.resume();
            objectClientThread.f = true;
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
       
         objectClientThread.imageThread.suspend();
        objectClientThread.mouseKeyboardThread.suspend();
        objectClientThread.f = false;

        
    }//GEN-LAST:event_jLabel4MouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client().setVisible(f);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
