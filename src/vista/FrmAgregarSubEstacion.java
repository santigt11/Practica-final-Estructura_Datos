/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.DAO.grafosEjemplo.SubEstacionDao;
import controlador.Utiles.Utiles;
import java.io.File;
import java.util.UUID;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import vista.tabla.ModeloTablaSubEstacion;

/**
 *
 * @author Santiago
 */
public class FrmAgregarSubEstacion extends javax.swing.JFrame {

    ModeloTablaSubEstacion mts = new ModeloTablaSubEstacion();
    private File fImagen1;
    private File fImagen2;
    private File fImagen3;
    SubEstacionDao estacionControl = new SubEstacionDao();

    /**
     * Creates new form FrmEscuela
     */
    public FrmAgregarSubEstacion() {
        initComponents();
        cargarTabla();
    }

    private void cargarVista() {
        int fila = tbSubEstaciones.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Escoja un registro de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                estacionControl.setSubEstacion(mts.getSubEstaciones().getInfo(fila));
                txtImagen1.setText(estacionControl.getSubEstacion().getImagen1());
                txtImagen2.setText(estacionControl.getSubEstacion().getImagen2());
                txtImagen3.setText(estacionControl.getSubEstacion().getImagen3());
                fImagen1 = new File("foto/" + estacionControl.getSubEstacion().getImagen1());
                fImagen2 = new File("foto/" + estacionControl.getSubEstacion().getImagen2());
                fImagen3 = new File("foto/" + estacionControl.getSubEstacion().getImagen3());
                txtLatitud.setText(String.valueOf(estacionControl.getSubEstacion().getCoordenada().getLatitud()));
                txtLongitud.setText(String.valueOf(estacionControl.getSubEstacion().getCoordenada().getLongitud()));
                txtNombre.setText(estacionControl.getSubEstacion().getNombre());
            } catch (Exception ex) {
                ex.toString();
            }
        }
    }

    private void cargarTabla() {
        mts.setEscuelas(estacionControl.all());
        tbSubEstaciones.setModel(mts);
        tbSubEstaciones.updateUI();
    }

    //No se hace control con la tercera imagen para seguir las instrucciones de la práctica. La tercera imagen no es obligatoria
    private Boolean verificar() {
        return (!txtImagen1.getText().trim().isEmpty()
                && !txtLatitud.getText().trim().isEmpty()
                && !txtLongitud.getText().trim().isEmpty()
                && !txtImagen2.getText().trim().isEmpty()
                && !txtNombre.getText().trim().isEmpty());
    }

    private String nombreUnico(File file) throws Exception {
        String aux1 = UUID.randomUUID().toString();
        aux1 += ("." + Utiles.extension(file.getName()));
        Utiles.copiarArchivo(file, new File("foto/" + aux1));
        return aux1;
    }

    private void limpiar() {
        txtImagen1.setText("");
        txtLatitud.setText("");
        txtLongitud.setText("");
        txtNombre.setText("");
        txtImagen2.setText("");
        txtImagen3.setText("");
        tbSubEstaciones.clearSelection();
        estacionControl.setSubEstacion(null);
        cargarTabla();
    }

    private void guardar() throws Exception {
        if (verificar()) {
            estacionControl.getSubEstacion().setNombre(txtNombre.getText());
            estacionControl.getSubEstacion().getCoordenada().setLatitud(Double.parseDouble(txtLatitud.getText()));
            estacionControl.getSubEstacion().getCoordenada().setLongitud(Double.parseDouble(txtLongitud.getText()));
            estacionControl.getSubEstacion().setImagen1(nombreUnico(fImagen1));
            estacionControl.getSubEstacion().setImagen2(nombreUnico(fImagen2));
            if (fImagen3 == null) {
                estacionControl.getSubEstacion().setImagen3("");
            }else{
                estacionControl.getSubEstacion().setImagen3(nombreUnico(fImagen3));
            }
            if (estacionControl.persist()) {
                JOptionPane.showMessageDialog(null, "Datos guardados");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo guardar, hubo un error", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private File cargarFoto() throws Exception {
        File obj = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter
                = new FileNameExtensionFilter("Imagenes", "jpg", "png", "jpeg");
        chooser.addChoosableFileFilter(filter);
        Integer resp = chooser.showOpenDialog(this);
        if (resp == JFileChooser.APPROVE_OPTION) {
            obj = chooser.getSelectedFile();
            System.out.println("Hizo ok" + obj.getName());
        } else {
            System.out.println("No ok");
        }
        return obj;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelShadow1 = new org.edisoncor.gui.panel.PanelShadow();
        jPanel2 = new javax.swing.JPanel();
        labelHeader1 = new org.edisoncor.gui.label.LabelHeader();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new org.edisoncor.gui.textField.TextFieldRound();
        jLabel2 = new javax.swing.JLabel();
        txtLongitud = new org.edisoncor.gui.textField.TextFieldRound();
        txtLatitud = new org.edisoncor.gui.textField.TextFieldRound();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtImagen1 = new org.edisoncor.gui.textField.TextFieldRound();
        btCargarImagen1 = new org.edisoncor.gui.button.ButtonAero();
        jLabel5 = new javax.swing.JLabel();
        txtImagen2 = new org.edisoncor.gui.textField.TextFieldRound();
        btGuardarSubEstacion = new org.edisoncor.gui.button.ButtonAero();
        txtCargarImagen2 = new org.edisoncor.gui.button.ButtonAero();
        jLabel6 = new javax.swing.JLabel();
        txtImagen3 = new org.edisoncor.gui.textField.TextFieldRound();
        btCargarImagen3 = new org.edisoncor.gui.button.ButtonAero();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSubEstaciones = new javax.swing.JTable();
        btQuitarSeleccion = new org.edisoncor.gui.button.ButtonAero();
        btGuardarSubEstacion1 = new org.edisoncor.gui.button.ButtonAero();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelHeader1.setText("Sub-Estacion de Energía");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Nombre: ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Longitud:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Latitud:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Imagen 1:");

        txtImagen1.setEnabled(false);
        txtImagen1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtImagen1MouseClicked(evt);
            }
        });

        btCargarImagen1.setBackground(new java.awt.Color(51, 51, 255));
        btCargarImagen1.setText("CARGAR");
        btCargarImagen1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCargarImagen1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Imagen 2:");

        txtImagen2.setEnabled(false);
        txtImagen2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtImagen2MouseClicked(evt);
            }
        });

        btGuardarSubEstacion.setBackground(new java.awt.Color(51, 255, 51));
        btGuardarSubEstacion.setText("GUARDAR");
        btGuardarSubEstacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarSubEstacionActionPerformed(evt);
            }
        });

        txtCargarImagen2.setBackground(new java.awt.Color(51, 51, 255));
        txtCargarImagen2.setText("CARGAR");
        txtCargarImagen2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCargarImagen2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Imagen 3:");

        txtImagen3.setEnabled(false);
        txtImagen3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtImagen3MouseClicked(evt);
            }
        });

        btCargarImagen3.setBackground(new java.awt.Color(51, 51, 255));
        btCargarImagen3.setText("CARGAR");
        btCargarImagen3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCargarImagen3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtImagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(btCargarImagen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCargarImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtImagen3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btCargarImagen3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(62, 62, 62))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btGuardarSubEstacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtImagen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCargarImagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCargarImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtImagen3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCargarImagen3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btGuardarSubEstacion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbSubEstaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbSubEstaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSubEstacionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbSubEstaciones);

        btQuitarSeleccion.setBackground(new java.awt.Color(255, 51, 51));
        btQuitarSeleccion.setText("QUITAR SELECCION");
        btQuitarSeleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btQuitarSeleccionActionPerformed(evt);
            }
        });

        btGuardarSubEstacion1.setBackground(new java.awt.Color(0, 204, 204));
        btGuardarSubEstacion1.setText("ADYACENCIAS");
        btGuardarSubEstacion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarSubEstacion1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addComponent(btQuitarSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btGuardarSubEstacion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btQuitarSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btGuardarSubEstacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btCargarImagen1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCargarImagen1ActionPerformed
        try {
            fImagen1 = cargarFoto();
            if (fImagen1 != null) {
                txtImagen1.setText(fImagen1.getAbsolutePath());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btCargarImagen1ActionPerformed

    private void btGuardarSubEstacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarSubEstacionActionPerformed
        try {
            guardar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btGuardarSubEstacionActionPerformed

    private void txtCargarImagen2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCargarImagen2ActionPerformed
        try {
            fImagen2 = cargarFoto();
            if (fImagen2 != null) {
                txtImagen2.setText(fImagen2.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(null, "ERROR");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_txtCargarImagen2ActionPerformed

    private void txtImagen1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtImagen1MouseClicked
        
    }//GEN-LAST:event_txtImagen1MouseClicked

    private void txtImagen2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtImagen2MouseClicked

    }//GEN-LAST:event_txtImagen2MouseClicked

    private void tbSubEstacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSubEstacionesMouseClicked
        cargarVista();
    }//GEN-LAST:event_tbSubEstacionesMouseClicked

    private void btQuitarSeleccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btQuitarSeleccionActionPerformed
        limpiar();
    }//GEN-LAST:event_btQuitarSeleccionActionPerformed

    private void txtImagen3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtImagen3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImagen3MouseClicked

    private void btCargarImagen3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCargarImagen3ActionPerformed
        try {
            fImagen3 = cargarFoto();
            if (fImagen3 != null) {
                txtImagen3.setText(fImagen3.getAbsolutePath());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btCargarImagen3ActionPerformed

    private void btGuardarSubEstacion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarSubEstacion1ActionPerformed
        new FrmGrafoSubEstacion(null, false).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btGuardarSubEstacion1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarSubEstacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarSubEstacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarSubEstacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarSubEstacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAgregarSubEstacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonAero btCargarImagen1;
    private org.edisoncor.gui.button.ButtonAero btCargarImagen3;
    private org.edisoncor.gui.button.ButtonAero btGuardarSubEstacion;
    private org.edisoncor.gui.button.ButtonAero btGuardarSubEstacion1;
    private org.edisoncor.gui.button.ButtonAero btQuitarSeleccion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.label.LabelHeader labelHeader1;
    private org.edisoncor.gui.panel.PanelShadow panelShadow1;
    private javax.swing.JTable tbSubEstaciones;
    private org.edisoncor.gui.button.ButtonAero txtCargarImagen2;
    private org.edisoncor.gui.textField.TextFieldRound txtImagen1;
    private org.edisoncor.gui.textField.TextFieldRound txtImagen2;
    private org.edisoncor.gui.textField.TextFieldRound txtImagen3;
    private org.edisoncor.gui.textField.TextFieldRound txtLatitud;
    private org.edisoncor.gui.textField.TextFieldRound txtLongitud;
    private org.edisoncor.gui.textField.TextFieldRound txtNombre;
    // End of variables declaration//GEN-END:variables
}
