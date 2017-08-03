

package Graficos.Filtros;

import javax.swing.DefaultListModel;


public class FiltroAvanzadoConsultaDemografia extends javax.swing.JDialog {

    private String[] camposNuevos;
    private String[] filtroTexto;
    private String[] filtroNumero;
    private String tipoFiltro; //Esta variable se utiliza para saber si el filtro es de texto, numerico, o un Listado maestro
    private Object textoCondicion; //Aqui se puede almacenar un textFiel o un comboBox dependiendo del tipo de filtro
    private java.util.ArrayList<String> condicionesSQL; //Aqui se almacenan las condiciones en el formato en que deben ir en la sentencia sql
    private DefaultListModel modeloLista;
    
    public FiltroAvanzadoConsultaDemografia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        camposNuevos = null;
        llenarArrayFiltros();
        tipoFiltro = "";
        textoCondicion = null;
        condicionesSQL = new java.util.ArrayList<>();
        modeloLista = new DefaultListModel();
        listFiltros.setModel(modeloLista);
    }
    
    private void llenarCombo(String tipoCombo){
       String[] lista = {"uno","dos","tres"};
        switch (tipoCombo) {
            case "Eps":
                
                break;
            default:
                throw new AssertionError();
        }
        javax.swing.JComboBox combo = (javax.swing.JComboBox) this.textoCondicion;
        combo.removeAllItems();
        for (String e : lista) {
            combo.addItem(e);
        }
    }
  
    private void llenarArrayFiltros(){
        String[] texto = {"    ="};
        String[] numero = {"    =", "    >", "    <","   >=","   <="};
        filtroTexto = texto;
        filtroNumero = numero;
    }

    public void setCamposNuevos(String[] camposNuevos){
        this.camposNuevos = camposNuevos;
        llenarCmbItem();
    }
    private void llenarCmbItem(){
        String[] Items ={"CC","Nombres",
            "Apellidos","Fecha_Nacimiento","Personas_A_Cargo","Estado_Civil","Sexo",
            "Eps","Afp","Tipo_De_Transporte","Tiempo_De_Desplazamiento",
            "Educacion","Cargo","Area,Fecha_De_Ingreso"};
        int size = Items.length + this.camposNuevos.length;
        
        for (int i = 0; i < size; i++) {
            if(i<Items.length){
                cmbItem.addItem(Items[i]);
            }else{
                System.out.println("Size: "+size+" i: "+i+" size-i: "+(size-i));
                cmbItem.addItem(this.camposNuevos[(size-i)-1]);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ContenedorPrincipal = new javax.swing.JPanel();
        lblItem = new javax.swing.JLabel();
        cmbItem = new javax.swing.JComboBox();
        cmbCondicion = new javax.swing.JComboBox();
        panelContCondicion = new javax.swing.JPanel();
        btnAgregarFiltro = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listFiltros = new javax.swing.JList();
        btnFiltrar = new javax.swing.JButton();
        btnBorrarTodo = new javax.swing.JButton();
        btnBorrarSeleccion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblItem.setText("ITEM:");

        cmbItem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbItemItemStateChanged(evt);
            }
        });

        panelContCondicion.setLayout(new javax.swing.BoxLayout(panelContCondicion, javax.swing.BoxLayout.LINE_AXIS));

        btnAgregarFiltro.setText("Agregar Filtro");
        btnAgregarFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarFiltroActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(listFiltros);

        btnFiltrar.setText("Filtrar");

        btnBorrarTodo.setText("Borrar Todo");
        btnBorrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarTodoActionPerformed(evt);
            }
        });

        btnBorrarSeleccion.setText("Borrar Selección");

        javax.swing.GroupLayout ContenedorPrincipalLayout = new javax.swing.GroupLayout(ContenedorPrincipal);
        ContenedorPrincipal.setLayout(ContenedorPrincipalLayout);
        ContenedorPrincipalLayout.setHorizontalGroup(
            ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContenedorPrincipalLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnFiltrar))
                    .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                        .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                                .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblItem)
                                    .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                                        .addComponent(cmbItem, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(panelContCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(25, 25, 25)
                                .addComponent(btnAgregarFiltro)))
                        .addGap(18, 18, 18)
                        .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnBorrarTodo))
                            .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                                .addComponent(btnBorrarSeleccion)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        ContenedorPrincipalLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBorrarSeleccion, btnBorrarTodo});

        ContenedorPrincipalLayout.setVerticalGroup(
            ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAgregarFiltro)
                    .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                        .addComponent(lblItem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelContCondicion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(ContenedorPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ContenedorPrincipalLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(btnBorrarTodo)
                        .addGap(18, 18, 18)
                        .addComponent(btnBorrarSeleccion)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFiltrar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContenedorPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContenedorPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbItemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbItemItemStateChanged
        int index = cmbItem.getSelectedIndex();
        String [] condiciones;
        textoCondicion = new javax.swing.JTextField();
        switch (index) {
           //Se toman en cuenta los casos en que son numericos
            case 0:
                condiciones = this.filtroNumero;
                tipoFiltro = "Numero";
                
                break;
            case 4:
                condiciones = this.filtroNumero;
                tipoFiltro = "Numero";
                break;
            case 10:
                condiciones = this.filtroNumero;
                tipoFiltro = "Numero";
                break;
            
            //Fin de los casos numericos
                
            //Se toman en cuenta los listados maestros
            case 5:
                condiciones = this.filtroTexto;
                tipoFiltro = "ListadoMaestro";
                textoCondicion = new javax.swing.JComboBox();
                break;
            case 7:
                condiciones = this.filtroTexto;
                tipoFiltro = "ListadoMaestro";
                textoCondicion = new javax.swing.JComboBox();
                llenarCombo("Eps");
                break;
            case 8:
                condiciones = this.filtroTexto;
                tipoFiltro = "ListadoMaestro";
                textoCondicion = new javax.swing.JComboBox();
                break;
            
            
                
            //Por defecto el filtro es de texto
            default:
                condiciones = this.filtroTexto;
                tipoFiltro = "Texto";
        }
        cmbCondicion.removeAllItems();
        for (String condicion : condiciones) {
            cmbCondicion.addItem(condicion);
        }
        panelContCondicion.removeAll();
        panelContCondicion.add((java.awt.Component)textoCondicion);
        
    }//GEN-LAST:event_cmbItemItemStateChanged

    private void btnAgregarFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarFiltroActionPerformed
        switch (tipoFiltro) {
            case "Texto":
                javax.swing.JTextField texto = (javax.swing.JTextField) textoCondicion;
                String lineaSQl = cmbItem.getSelectedItem().toString() +" like "+ texto.getText();
                modeloLista.addElement(cmbItem.getSelectedItem().toString() +" = "+ texto.getText());
                condicionesSQL.add(lineaSQl);
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_btnAgregarFiltroActionPerformed

    private void btnBorrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarTodoActionPerformed
        modeloLista = new DefaultListModel();
        listFiltros.setModel(modeloLista);
        condicionesSQL = new java.util.ArrayList<>();
    }//GEN-LAST:event_btnBorrarTodoActionPerformed

    
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
            java.util.logging.Logger.getLogger(FiltroAvanzadoConsultaDemografia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FiltroAvanzadoConsultaDemografia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FiltroAvanzadoConsultaDemografia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FiltroAvanzadoConsultaDemografia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FiltroAvanzadoConsultaDemografia dialog = new FiltroAvanzadoConsultaDemografia(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel ContenedorPrincipal;
    private javax.swing.JButton btnAgregarFiltro;
    private javax.swing.JButton btnBorrarSeleccion;
    private javax.swing.JButton btnBorrarTodo;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JComboBox cmbCondicion;
    private javax.swing.JComboBox cmbItem;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblItem;
    private javax.swing.JList listFiltros;
    private javax.swing.JPanel panelContCondicion;
    // End of variables declaration//GEN-END:variables
}
