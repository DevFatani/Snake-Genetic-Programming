package UISnake;

import javax.swing.ButtonGroup;
import javax.swing.JSlider;
import snake.SnakeUserRun;

public class Settings extends javax.swing.JFrame {

    public Settings() {
        initComponents();
        this.jComboBox1.removeAllItems();
        this.groubButton();
    }

    private void groubButton() {
        ButtonGroup bg = new ButtonGroup();
        bg.add(this.rbSimulator15);
        bg.add(this.rbSimulator20);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPlay = new java.awt.Button();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        rbSimulator20 = new javax.swing.JRadioButton();
        rbSimulator15 = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        spNumObstacles = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        chSlowFood = new javax.swing.JCheckBox();
        chBonusFood = new javax.swing.JCheckBox();
        chSpeedFood = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jSpeed = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        speedRate = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("User");
        setBackground(new java.awt.Color(155, 166, 228));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(153, 204, 0));
        setResizable(false);

        btnPlay.setBackground(new java.awt.Color(255, 192, 28));
        btnPlay.setLabel("PLAY");
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(243, 206, 110));

        jLabel4.setText("simulator");

        rbSimulator20.setText("20 * 20");
        rbSimulator20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                on20Clicked(evt);
            }
        });

        rbSimulator15.setSelected(true);
        rbSimulator15.setText("16 * 16");
        rbSimulator15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                on16Clicked(evt);
            }
        });
        rbSimulator15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSimulator15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(70, 70, 70)
                .addComponent(rbSimulator15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbSimulator20)
                .addGap(67, 67, 67))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbSimulator15)
                    .addComponent(rbSimulator20)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(238, 220, 141));

        jLabel2.setBackground(new java.awt.Color(127, 219, 10));
        jLabel2.setText("obstacles");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 3, 1));
        jSpinner1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jSpinner1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        spNumObstacles.setBackground(new java.awt.Color(127, 219, 10));
        spNumObstacles.setText("number of obstacles");

        jLabel6.setBackground(new java.awt.Color(127, 219, 10));
        jLabel6.setText("obstacles length");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(spNumObstacles)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spNumObstacles, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(238, 192, 212));

        jLabel3.setText("food type (default normal food)");

        chSlowFood.setText("Slow -5");

        chBonusFood.setText("Bonus +5");
        chBonusFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chBonusFoodActionPerformed(evt);
            }
        });

        chSpeedFood.setText("Speed +5");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(chBonusFood)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chSpeedFood)
                        .addGap(158, 158, 158)))
                .addComponent(chSlowFood)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chBonusFood)
                    .addComponent(chSpeedFood)
                    .addComponent(chSlowFood))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(127, 127, 156));
        jPanel4.setForeground(new java.awt.Color(204, 255, 255));

        jSpeed.setBackground(new java.awt.Color(238, 108, 217));
        jSpeed.setForeground(new java.awt.Color(0, 177, 194));
        jSpeed.setMaximum(150);
        jSpeed.setMinimum(50);
        jSpeed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpeedStateChanged(evt);
            }
        });
        jSpeed.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSpeedPropertyChange(evt);
            }
        });

        jLabel1.setText("Speed (Delay)");

        speedRate.setForeground(new java.awt.Color(51, 255, 51));
        speedRate.setText("50");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(178, 178, 178)
                        .addComponent(speedRate)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSpeed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(speedRate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
//        final boolean isObstaclesUp = this.chObstaclesUp.isSelected();
//        final boolean isObstaclesDown = this.chObstaclesDown.isSelected();
//        final boolean isObstaclesCenter = this.chObstaclesCenter.isSelected();

        final int obstaclesSize = (int) this.jComboBox1.getItemAt(this.jComboBox1.getSelectedIndex());
        final int obstaclesNum = (int) this.jSpinner1.getValue();
        final boolean isBonusFood = this.chBonusFood.isSelected();
        final boolean isSlowFood = this.chSlowFood.isSelected();
        final boolean isSpeedFood = this.chSpeedFood.isSelected();

        final int speed = this.jSpeed.getValue();

        new Thread() {

            @Override
            public void run() {

                //                new SnakeUserRun(rbSimulator15.isSelected(), isObstaclesUp,
                //                        isObstaclesDown, isObstaclesCenter,
                //                        speed, isSpeedFood, isSlowFood, isBonusFood).run();
                new SnakeUserRun(rbSimulator15.isSelected(), obstaclesSize, obstaclesNum, speed, isSpeedFood, isSlowFood, isBonusFood).run();

            }

        }.start();
    }//GEN-LAST:event_btnPlayActionPerformed

    private void chBonusFoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chBonusFoodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chBonusFoodActionPerformed

    private void rbSimulator15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSimulator15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbSimulator15ActionPerformed

    private void jSpeedPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSpeedPropertyChange

    }//GEN-LAST:event_jSpeedPropertyChange

    private void jSpeedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpeedStateChanged
        this.speedRate.setText(((JSlider) evt.getSource()).getValue() + "");

    }//GEN-LAST:event_jSpeedStateChanged

    private void on20Clicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_on20Clicked

        this.jComboBox1.removeAllItems();

        for (int i = 3; i <= 18; i += 2) {

            this.jComboBox1.addItem(i);

        }
    }//GEN-LAST:event_on20Clicked

    private void on16Clicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_on16Clicked

        this.jComboBox1.removeAllItems();

        for (int i = 3; i <= 12; i += 2) {

            this.jComboBox1.addItem(i);

        }
    }//GEN-LAST:event_on16Clicked

    private void jSpinner1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jSpinner1AncestorAdded

//        if (this.jSpinner1.getValue().equals(0)) {
//            System.out.println("sd.fmds,fmdfm.");
//        } else if (this.jSpinner1.getValue().equals(3)) {
//
//        } else {
//
//        }

    }//GEN-LAST:event_jSpinner1AncestorAdded

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Settings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnPlay;
    private javax.swing.JCheckBox chBonusFood;
    private javax.swing.JCheckBox chSlowFood;
    private javax.swing.JCheckBox chSpeedFood;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSlider jSpeed;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JRadioButton rbSimulator15;
    private javax.swing.JRadioButton rbSimulator20;
    private javax.swing.JLabel spNumObstacles;
    private javax.swing.JLabel speedRate;
    // End of variables declaration//GEN-END:variables
}
