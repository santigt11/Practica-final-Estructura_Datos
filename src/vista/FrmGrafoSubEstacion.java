/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista;

import controlador.DAO.grafosEjemplo.SubEstacionDao;
import controlador.TDA.grafos.PaintGraph;
import controlador.Utiles.Utiles;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vista.grafoEjemplo.utilidades.UtilesVistaSubEstacion;
import vista.tabla.ModeloTablaAdyacenciaSubEstacion;

/**
 *
 * @author Santiago
 */
public class FrmGrafoSubEstacion extends javax.swing.JDialog {

    private ModeloTablaAdyacenciaSubEstacion mtas = new ModeloTablaAdyacenciaSubEstacion();
    private SubEstacionDao se = new SubEstacionDao();

    /**
     * Creates new form FrmGrafoEscuela
     */
    public FrmGrafoSubEstacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        limpiar();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void busqueda() throws Exception {
        if (cbxBusqueda.getSelectedIndex() == 0) {
            if (se.getGrafo().DFS(1)) {
                JOptionPane.showMessageDialog(null, "El grafo esta compleatamente conectado");
            } else {
                JOptionPane.showMessageDialog(null, "El grafo no esta conectado");
            }
        } else {
            if (se.getGrafo().BFS(1)) {
                JOptionPane.showMessageDialog(null, "El grafo esta compleatamente conectado");
            } else {
                JOptionPane.showMessageDialog(null, "El grafo no esta conectado");
            }
        }
    }
    
    private void calcularTiempoAlgoritmo(int algoritmo) throws Exception{
        if (algoritmo == 0) {
            long inicioFloyd = System.nanoTime();
            System.out.println(se.getGrafo().aplicarAlgoritmoFloydConEtiquetas());
            long finFloyd = System.nanoTime();
            long tiempoFloyd = finFloyd - inicioFloyd;
            System.out.println("Tiempo de ejecución del algoritmo de Floyd: " + tiempoFloyd + " nanosegundos");
            JOptionPane.showMessageDialog(null, se.getGrafo().encontrarCaminoMasCorto(cbxO1.getSelectedIndex() + 1, cbxD1.getSelectedIndex() + 1));
            inicioFloyd = 0;
            finFloyd = 0;
            tiempoFloyd = 0;
        }else{
            long inicioFloyd = System.nanoTime();
            System.out.println(se.getGrafo().aplicarAlgoritmoBellmanFord(0));
            long finFloyd = System.nanoTime();
            long tiempoFloyd = finFloyd - inicioFloyd;
            System.out.println("Tiempo de ejecución del algoritmo de Bellman: " + tiempoFloyd + " nanosegundos");
            JOptionPane.showMessageDialog(null, se.getGrafo().encontrarCaminoMasCorto(cbxO1.getSelectedIndex() + 1, cbxD1.getSelectedIndex() + 1));
            inicioFloyd = 0;
            finFloyd = 0;
            tiempoFloyd = 0;
        }
    }
    
    private void recorrido() throws Exception {
        if (cbxRecorrido.getSelectedIndex() == 1) {
            calcularTiempoAlgoritmo(0);
        } else {
            calcularTiempoAlgoritmo(1);
        }
    }

    public void mostrarGrafo() throws Exception {
        PaintGraph p = new PaintGraph();
        se.loadGraph();
        p.updateFile(se.getGrafo());
        String url = "d3\\grafo.html";
        Utiles.abrirNavegadorPredeterminado(url);
    }

    public void guardarGrafo() {
        try {
            int i = JOptionPane
                    .showConfirmDialog(null, "¿Estas seguro de guardar?", "ADVERTENCIA", JOptionPane.OK_CANCEL_OPTION);
            if (i == JOptionPane.OK_OPTION) {
                if (se.getGrafo() != null) {
                    se.guardarGrafo();
                    JOptionPane.showMessageDialog(null, "Grafo guardado", "GUARDADO", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede guardar un grafo vacio", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiar() {
        try {
            UtilesVistaSubEstacion.cargarComboSubEstaciones(cbxO);
            UtilesVistaSubEstacion.cargarComboSubEstaciones(cbxD);
            UtilesVistaSubEstacion.cargarComboSubEstaciones(cbxO1);
            UtilesVistaSubEstacion.cargarComboSubEstaciones(cbxD1);
            cargarTabla();
        } catch (Exception e) {
        }
    }

    private void load() throws Exception {
        int i = JOptionPane
                .showConfirmDialog(null, "Está seguro de cargar?", "Advertencia", JOptionPane.OK_CANCEL_OPTION);
        if (i == JOptionPane.OK_OPTION) {
            se.loadGraph();
            limpiar();
            JOptionPane.showMessageDialog(null, "Grafo cargado correctamente");
        }
    }

    private void adyacencia() {
        try {
            Integer o = cbxO.getSelectedIndex();
            Integer d = cbxD.getSelectedIndex();
            if (o.intValue() == d.intValue()) {
                JOptionPane.showMessageDialog(null, "Escoja subEstaciones diferentes", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Double dist = UtilesVistaSubEstacion.calcularDistanciaSubEstaciones(se.getSubEstaciones().getInfo(o), se.getSubEstaciones().getInfo(d));
                se.getGrafo().insertEdgeE(se.getSubEstaciones().getInfo(o), se.getSubEstaciones().getInfo(d), dist);
                JOptionPane.showMessageDialog(null, "Adyacencia correcta");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void cargarTabla() throws Exception {
        mtas.setGrafo(se.getGrafo());
        mtas.fireTableDataChanged();
        tblTabla.setModel(mtas);
        tblTabla.updateUI();
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
        panelNice1 = new org.edisoncor.gui.panel.PanelNice();
        labelHeader1 = new org.edisoncor.gui.label.LabelHeader();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbxO = new javax.swing.JComboBox<>();
        cbxD = new javax.swing.JComboBox<>();
        buttonAero1 = new org.edisoncor.gui.button.ButtonAero();
        btMapa = new org.edisoncor.gui.button.ButtonAero();
        buttonAero7 = new org.edisoncor.gui.button.ButtonAero();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTabla = new javax.swing.JTable();
        buttonAero3 = new org.edisoncor.gui.button.ButtonAero();
        buttonAero4 = new org.edisoncor.gui.button.ButtonAero();
        buttonAero5 = new org.edisoncor.gui.button.ButtonAero();
        buttonAero2 = new org.edisoncor.gui.button.ButtonAero();
        cbxO1 = new javax.swing.JComboBox<>();
        cbxD1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbxRecorrido = new javax.swing.JComboBox<>();
        cbxBusqueda = new javax.swing.JComboBox<>();
        buttonAero6 = new org.edisoncor.gui.button.ButtonAero();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelHeader1.setText("ADYACENCIAS");

        jLabel1.setText("Origen:");

        jLabel2.setText("Destino:");

        cbxO.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonAero1.setBackground(new java.awt.Color(255, 255, 255));
        buttonAero1.setForeground(new java.awt.Color(0, 0, 0));
        buttonAero1.setText("ADYACENCIA");
        buttonAero1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAero1ActionPerformed(evt);
            }
        });

        btMapa.setBackground(new java.awt.Color(0, 102, 255));
        btMapa.setText("GRAFO");
        btMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMapaActionPerformed(evt);
            }
        });

        buttonAero7.setBackground(new java.awt.Color(255, 255, 255));
        buttonAero7.setForeground(new java.awt.Color(0, 0, 0));
        buttonAero7.setText("ADYACENCIAS ALETORIAS");
        buttonAero7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAero7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNice1Layout = new javax.swing.GroupLayout(panelNice1);
        panelNice1.setLayout(panelNice1Layout);
        panelNice1Layout.setHorizontalGroup(
            panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelNice1Layout.createSequentialGroup()
                        .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelNice1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelNice1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(cbxO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 742, Short.MAX_VALUE)
                        .addComponent(btMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelNice1Layout.createSequentialGroup()
                        .addComponent(buttonAero1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonAero7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelNice1Layout.setVerticalGroup(
            panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNice1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNice1Layout.createSequentialGroup()
                        .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cbxO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbxD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelNice1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonAero1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonAero7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tblTabla.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTabla);

        buttonAero3.setBackground(new java.awt.Color(51, 255, 51));
        buttonAero3.setText("GUARDAR");
        buttonAero3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAero3ActionPerformed(evt);
            }
        });

        buttonAero4.setBackground(new java.awt.Color(204, 204, 0));
        buttonAero4.setText("CARGAR");
        buttonAero4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAero4ActionPerformed(evt);
            }
        });

        buttonAero5.setBackground(new java.awt.Color(255, 0, 51));
        buttonAero5.setText("ATRAS");
        buttonAero5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAero5ActionPerformed(evt);
            }
        });

        buttonAero2.setBackground(new java.awt.Color(0, 51, 255));
        buttonAero2.setText("RECORRIDO");
        buttonAero2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAero2ActionPerformed(evt);
            }
        });

        cbxO1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxD1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Origen:");

        jLabel4.setText("Destino:");

        cbxRecorrido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bellman-Ford", "Floyd" }));

        cbxBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Profundidad", "Anchura" }));

        buttonAero6.setBackground(new java.awt.Color(0, 51, 255));
        buttonAero6.setText("BUSQUEDA");
        buttonAero6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAero6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(buttonAero3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonAero4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(304, 304, 304)
                        .addComponent(buttonAero5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxO1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbxBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77)
                                .addComponent(buttonAero6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbxRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)
                                .addComponent(buttonAero2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxO1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonAero2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAero6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAero3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAero4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAero5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelNice1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelNice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void btMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMapaActionPerformed
        try {
            mostrarGrafo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btMapaActionPerformed

    private void buttonAero2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAero2ActionPerformed
        try {
            recorrido();
        } catch (Exception ex) {
            Logger.getLogger(FrmGrafoSubEstacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAero2ActionPerformed

    private void buttonAero1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAero1ActionPerformed
        adyacencia();
        try {
            cargarTabla();
        } catch (Exception ex) {
            Logger.getLogger(FrmGrafoSubEstacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAero1ActionPerformed

    private void tblTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTablaMouseClicked

    }//GEN-LAST:event_tblTablaMouseClicked

    private void buttonAero3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAero3ActionPerformed
        guardarGrafo();
    }//GEN-LAST:event_buttonAero3ActionPerformed

    private void buttonAero4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAero4ActionPerformed
        try {
            load();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_buttonAero4ActionPerformed

    private void buttonAero5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAero5ActionPerformed
        new FrmAgregarSubEstacion().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_buttonAero5ActionPerformed

    private void buttonAero6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAero6ActionPerformed
        try {
            busqueda();
        } catch (Exception ex) {
            Logger.getLogger(FrmGrafoSubEstacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAero6ActionPerformed

    private void buttonAero7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAero7ActionPerformed
        try {
            se.getGrafo().conectarAleatoriamente();
            // Verificar si el grafo está conectado utilizando DFS
            boolean conectadoDFS = se.getGrafo().DFS(1); // Se inicia el recorrido DFS desde el nodo 1
            System.out.println("¿El grafo está conectado (DFS)? " + conectadoDFS);

            // Verificar si el grafo está conectado utilizando BFS
            boolean conectadoBFS = se.getGrafo().BFS(1); // Se inicia el recorrido BFS desde el nodo 1
            System.out.println("¿El grafo está conectado (BFS)? " + conectadoBFS);
            cargarTabla();
        } catch (Exception ex) {
            Logger.getLogger(FrmGrafoSubEstacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAero7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
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
            java.util.logging.Logger.getLogger(FrmGrafoSubEstacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGrafoSubEstacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGrafoSubEstacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGrafoSubEstacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmGrafoSubEstacion dialog = new FrmGrafoSubEstacion(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonAero btMapa;
    private org.edisoncor.gui.button.ButtonAero buttonAero1;
    private org.edisoncor.gui.button.ButtonAero buttonAero2;
    private org.edisoncor.gui.button.ButtonAero buttonAero3;
    private org.edisoncor.gui.button.ButtonAero buttonAero4;
    private org.edisoncor.gui.button.ButtonAero buttonAero5;
    private org.edisoncor.gui.button.ButtonAero buttonAero6;
    private org.edisoncor.gui.button.ButtonAero buttonAero7;
    private javax.swing.JComboBox<String> cbxBusqueda;
    private javax.swing.JComboBox<String> cbxD;
    private javax.swing.JComboBox<String> cbxD1;
    private javax.swing.JComboBox<String> cbxO;
    private javax.swing.JComboBox<String> cbxO1;
    private javax.swing.JComboBox<String> cbxRecorrido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.label.LabelHeader labelHeader1;
    private org.edisoncor.gui.panel.PanelNice panelNice1;
    private javax.swing.JTable tblTabla;
    // End of variables declaration//GEN-END:variables
}
