/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graficos;

import ausentismo.ModeloTablaMostrarSeguimiento;

/**
 *
 * @author usuario
 */
public class Mostrar_Registros_Seguimiento extends javax.swing.JDialog {

    private ModeloTablaMostrarSeguimiento modeloTabla;
    public Mostrar_Registros_Seguimiento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setSize(this.getToolkit().getScreenSize().width,this.getToolkit().getScreenSize().height-40);
        
        modeloTabla = new ModeloTablaMostrarSeguimiento();
        TablaRegistros.setModel(modeloTabla);
        grupoBotones();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BotonesConsulta = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Text_Documento = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        C_ABIERTOS = new javax.swing.JRadioButton();
        C_CERRADOS = new javax.swing.JRadioButton();
        C_TRABAJADOR = new javax.swing.JRadioButton();
        C_TODOS = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaRegistros = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel2.setText("Registros - Seguimiento");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CONSULTA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        Text_Documento.setText("Documento de identidad");
        Text_Documento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Text_DocumentoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Text_DocumentoFocusLost(evt);
            }
        });

        jButton1.setText("CONSULTAR");

        C_ABIERTOS.setText("CASOS ABIERTOS");

        C_CERRADOS.setText("CASOS CERRADOS");
        C_CERRADOS.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                C_CERRADOSFocusGained(evt);
            }
        });

        C_TRABAJADOR.setText("TRABAJADOR");
        C_TRABAJADOR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                C_TRABAJADORMouseReleased(evt);
            }
        });
        C_TRABAJADOR.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                C_TRABAJADORStateChanged(evt);
            }
        });
        C_TRABAJADOR.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                C_TRABAJADORPropertyChange(evt);
            }
        });

        C_TODOS.setText("TODOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(C_ABIERTOS)
                .addGap(18, 18, 18)
                .addComponent(C_CERRADOS)
                .addGap(18, 18, 18)
                .addComponent(C_TRABAJADOR)
                .addGap(18, 18, 18)
                .addComponent(C_TODOS)
                .addGap(10, 10, 10)
                .addComponent(Text_Documento, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Text_Documento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(C_ABIERTOS)
                    .addComponent(C_CERRADOS)
                    .addComponent(C_TRABAJADOR)
                    .addComponent(C_TODOS))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        TablaRegistros.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TablaRegistros);

        jPanel2.add(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Text_DocumentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Text_DocumentoFocusGained
        if(Text_Documento.getText().equals("Documento de identidad")){
            Text_Documento.setText("");
        }
    }//GEN-LAST:event_Text_DocumentoFocusGained

    private void Text_DocumentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Text_DocumentoFocusLost
         if(Text_Documento.getText().equals("")){
             Text_Documento.setText("Documento de identidad");
         }
        
    }//GEN-LAST:event_Text_DocumentoFocusLost

    private void C_TRABAJADORPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_C_TRABAJADORPropertyChange
        // TODO add your handling code here:
        accionesBotones();
    }//GEN-LAST:event_C_TRABAJADORPropertyChange

    private void C_TRABAJADORMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C_TRABAJADORMouseReleased
        // TODO add your handling code here:
        accionesBotones();
    }//GEN-LAST:event_C_TRABAJADORMouseReleased

    private void C_CERRADOSFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_C_CERRADOSFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_C_CERRADOSFocusGained

    private void C_TRABAJADORStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_C_TRABAJADORStateChanged
        // TODO add your handling code here:
        accionesBotones();
    }//GEN-LAST:event_C_TRABAJADORStateChanged

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
            java.util.logging.Logger.getLogger(Mostrar_Registros_Seguimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mostrar_Registros_Seguimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mostrar_Registros_Seguimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mostrar_Registros_Seguimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Mostrar_Registros_Seguimiento dialog = new Mostrar_Registros_Seguimiento(new javax.swing.JFrame(), true);
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
    private void grupoBotones(){
        BotonesConsulta.add(C_ABIERTOS);
        BotonesConsulta.add(C_CERRADOS);
        BotonesConsulta.add(C_TODOS);
        BotonesConsulta.add(C_TRABAJADOR);
    }
    private void accionesBotones(){
       if(C_TRABAJADOR.isSelected()){
           Text_Documento.setEnabled(true);
       }
       else{
           Text_Documento.setEnabled(false);
       }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BotonesConsulta;
    private javax.swing.JRadioButton C_ABIERTOS;
    private javax.swing.JRadioButton C_CERRADOS;
    private javax.swing.JRadioButton C_TODOS;
    private javax.swing.JRadioButton C_TRABAJADOR;
    private javax.swing.JTable TablaRegistros;
    private javax.swing.JTextField Text_Documento;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
