 
package Graficos;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Caso extends javax.swing.JDialog {
    
    private String[] DatosIniciales; //En esta variable se va a almacenar la informacion que se envia desde la ventan Seguimiento _Casos
    private boolean EI;
    
    //_____________ DEFINICION DE CONSTANTES PARA UTILIZAR EN EL ARRAY QUE LLEGA ______________
    //              CON INFORMACION DE LA PERSONA A LA QUE SE LE HACE SEGUIMIENTO
            
    private static final int id_seguimiento  = 0;
    private static final int Fecha_Registro  = 1;
    private static final int CC  = 2;
    private static final int Nombres  = 3;
    private static final int Estado  = 4;
    private static final int Diagnostico  = 5;
    private static final int Dias_Incapacidad =6;
    private static final int Apellido = 7;
    private static final int id_motivo = 8;
   /* private static final int RecomendacionesMedicas   = 6;
    private static final int Dias_Incapacidad =7;   */
   // private static final int Responsable = 9;
    
    //___________________________________________________________________________________________

    ArrayList<String[]> Compromisos;
    ausentismo.PanelFondo fondo = null;
    
    public Caso(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        //fondo = new ausentismo.PanelFondo();
       // ausentismo.GraphicsAdmin.fondoAPanel(PanelPrincipal, new ImageIcon(getClass().getResource("/Imagenes/fondo.gif")), fondo);
      
        DatosIniciales = null;
        //Para que el texto se vea en toda la caja de texto y no haga scroll
        //hacia los lados
        textAreaInsertCompromiso.setLineWrap(true);
        textAreaInsertObservacion.setLineWrap(true);
        textAreaCompromiso.setLineWrap(true);
        textAreaDiagnostico.setLineWrap(true);
        textAreaRecomendacionesMedicas.setLineWrap(true);  
        this.EI = false;
    }
    
    public void setDatosInicialesEI(String[] datos){
        this.DatosIniciales = datos;
        this.EI = true;  
        cargarDatosIniciales();
        cargarCompromisos();
        
    }
    
    public void setDatosIniciales(String[] datos){
        this.DatosIniciales=datos;
        cargarDatosIniciales();
        cargarMotivo();
        cargarCompromisos();
      
    }
   
    private void cargarCompromisos(){
        
        
        int idSeguimiento = Integer.parseInt(DatosIniciales[id_seguimiento]);
        
        if(this.EI){
            
            this.Compromisos = BaseDatos.Seguimiento_BD.compromisosEI(String.valueOf(idSeguimiento));
        }else{
            this.Compromisos = BaseDatos.Seguimiento_BD.compromisos(idSeguimiento);
        }
        
        if(this.Compromisos.size()<=0){
            JOptionPane.showMessageDialog(null, "No se encontraron compromisos para el seguimiento actual", "Error!!!", JOptionPane.ERROR_MESSAGE);
        }else{
            System.out.println("ENTRA AL CONDICIONAL");
            String CompromisoInicial[] = Compromisos.get(0);
            lblFechaCumplimiento.setText(CompromisoInicial[3]);
            textAreaCompromiso.setText(CompromisoInicial[4]);
            lblResponsable.setText(CompromisoInicial[6]);
            
            if(this.Compromisos.size()>1){
                DefaultTableModel modelo = new DefaultTableModel(){

                    @Override
                    public boolean isCellEditable(int fila, int columna) {
                        return false;
                    }

                };
                modelo.addColumn("FECHA DE REGISTRO");
                modelo.addColumn("FECHA LIMITE ");
                modelo.addColumn("COMPROMISO");
                modelo.addColumn("OBSERVACIÓN");
                modelo.addColumn("RESPONSABLE");
                                
                for (int i = 1; i < this.Compromisos.size(); i++) {                    
                    String[] datos = this.Compromisos.get(i);
                    Object[] vector = {datos[2],datos[3],datos[4],datos[5],datos[6]};
                    modelo.addRow(vector);                    
                }
                tablaCompromiso.setModel(modelo);
                //Se ajustan los tamaños de las columnas de la tabla de compromisos
                
                
                tablaCompromiso.getColumnModel().getColumn(2).setPreferredWidth(200);
                
            }
        }
    }
    private void cargarMotivo(){
        int idMotivo = Integer.parseInt(DatosIniciales[id_motivo]);
        String motivo = BaseDatos.Seguimiento_BD.motivo(idMotivo);
        lblMotivo.setText(motivo);
    }
    private void cargarDatosIniciales(){
        System.out.println("LLEGA A DATOS INICIALES DE");
        
        String[] D = DatosIniciales;
        System.out.println("NOMBRE:____ "+D[Nombres]);
        lblNombre_Apellido.setText( (D[Nombres]+" "+D[Apellido]).toUpperCase());
        lblCC.setText(D[CC]);
        lblIncapacidad.setText(D[Dias_Incapacidad]);     
        lblEstado.setText("CASO "+D[Estado].toUpperCase());
        textAreaDiagnostico.setText(D[Diagnostico].toUpperCase());
        if(D[Estado].toUpperCase().equals("CERRADO")){
            textAreaInsertCompromiso.setEnabled(false);
            textAreaInsertObservacion.setEnabled(false);
            textResponsable.setEnabled(false);
            dateFechaLimite.setEnabled(false);
            dateFechaRegistroNuevo.setEnabled(false);
        }
        
        
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane5 = new javax.swing.JScrollPane();
        PanelPrincipal = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblNombre_Apellido = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblCC = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblMotivo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaDiagnostico = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        lblResponsable = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblFechaCumplimiento = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAreaCompromiso = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        lblIncapacidad = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        textAreaRecomendacionesMedicas = new javax.swing.JTextArea();
        panelIngresar = new javax.swing.JPanel();
        lbl_Fecha = new javax.swing.JLabel();
        dateFechaLimite = new com.toedter.calendar.JDateChooser();
        lbl_Compromiso_Observacion = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        textAreaInsertCompromiso = new javax.swing.JTextArea();
        btnAdd = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        dateFechaRegistroNuevo = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        textAreaInsertObservacion = new javax.swing.JTextArea();
        textResponsable = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCompromiso = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane5.setBackground(new java.awt.Color(183, 240, 240));

        PanelPrincipal.setBackground(new java.awt.Color(183, 240, 240));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setOpaque(false);

        lblNombre_Apellido.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lblNombre_Apellido.setText("NOMBRE(S) Y APELLIDOS");

        jLabel2.setText("CEDULA:");

        lblCC.setText("Número de cedula");

        jLabel4.setText("MOTIVO:");

        lblMotivo.setText("El motivo del accidente o ausentismo");

        jLabel7.setText("DIAGNÓSTICO:");

        textAreaDiagnostico.setEditable(false);
        textAreaDiagnostico.setColumns(20);
        textAreaDiagnostico.setRows(5);
        jScrollPane2.setViewportView(textAreaDiagnostico);

        jLabel8.setText("RESPONSABLE:");

        lblResponsable.setText("El responsable del compromiso");

        jLabel10.setText("FECHA DE CUMPLIMIENTO DEL COMPROMISO:");

        lblFechaCumplimiento.setText("DD/MM/YYYY");

        jLabel12.setText("COMPROMISO:");

        textAreaCompromiso.setEditable(false);
        textAreaCompromiso.setColumns(20);
        textAreaCompromiso.setRows(5);
        jScrollPane3.setViewportView(textAreaCompromiso);

        jLabel15.setText("DIAS DE INCAPACIDAD:");

        lblIncapacidad.setText("días de incapacidad");

        jLabel1.setText("RECOMENDACIONES MEDICAS:");

        textAreaRecomendacionesMedicas.setEditable(false);
        textAreaRecomendacionesMedicas.setColumns(20);
        textAreaRecomendacionesMedicas.setRows(5);
        jScrollPane6.setViewportView(textAreaRecomendacionesMedicas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre_Apellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblCC))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblMotivo))
                                    .addComponent(jLabel7)
                                    .addComponent(jScrollPane2)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12)
                                    .addComponent(jScrollPane3)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblIncapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1)
                                    .addComponent(jScrollPane6))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(lblFechaCumplimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(lblResponsable)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel4, jLabel7});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNombre_Apellido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblCC))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblMotivo))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIncapacidad)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblResponsable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFechaCumplimiento)
                .addGap(12, 12, 12)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelIngresar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelIngresar.setOpaque(false);

        lbl_Fecha.setText("FECHA LIMITE:");

        lbl_Compromiso_Observacion.setText("COMPROMISO:");

        textAreaInsertCompromiso.setColumns(20);
        textAreaInsertCompromiso.setRows(5);
        jScrollPane4.setViewportView(textAreaInsertCompromiso);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Add.gif"))); // NOI18N
        btnAdd.setText("AGREGAR COMPROMISO");
        btnAdd.setBorder(null);
        btnAdd.setBorderPainted(false);
        btnAdd.setContentAreaFilled(false);
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel3.setText("FECHA DE REGISTRO");

        jLabel5.setText("OBSERVACIÓN");

        textAreaInsertObservacion.setColumns(20);
        textAreaInsertObservacion.setRows(5);
        jScrollPane7.setViewportView(textAreaInsertObservacion);

        jLabel6.setText("RESPONSABLE:");

        javax.swing.GroupLayout panelIngresarLayout = new javax.swing.GroupLayout(panelIngresar);
        panelIngresar.setLayout(panelIngresarLayout);
        panelIngresarLayout.setHorizontalGroup(
            panelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIngresarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(dateFechaRegistroNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Compromiso_Observacion)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(panelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(dateFechaLimite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_Fecha)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(panelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd)
                    .addGroup(panelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(textResponsable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addContainerGap())
        );

        panelIngresarLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane4, jScrollPane7});

        panelIngresarLayout.setVerticalGroup(
            panelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIngresarLayout.createSequentialGroup()
                .addGroup(panelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelIngresarLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_Fecha)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelIngresarLayout.createSequentialGroup()
                        .addGroup(panelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateFechaRegistroNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateFechaLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelIngresarLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelIngresarLayout.createSequentialGroup()
                                .addComponent(lbl_Compromiso_Observacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelIngresarLayout.createSequentialGroup()
                        .addComponent(textResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelIngresarLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane4, jScrollPane7});

        lblEstado.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        lblEstado.setText("CASO ABIERTO ");

        tablaCompromiso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "FECHA DE REGISTRO", "FECHA LIMITE", "COMPROMISO", "OBSERVACIÓN", "RESPONSABLE"
            }
        ));
        jScrollPane1.setViewportView(tablaCompromiso);

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEstado)
                .addGap(475, 475, 475))
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblEstado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addComponent(panelIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane5.setViewportView(PanelPrincipal);

        getContentPane().add(jScrollPane5);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        
        if(dateFechaLimite.getCalendar()!= null && !textAreaInsertCompromiso.getText().equals("")){
            //SE TOMAN LOS DATOS NECESARIOS PARA INSERTAR EN LA BASE DE DATOS
            int dia = dateFechaLimite.getCalendar().get(Calendar.DAY_OF_MONTH);
            int mes = dateFechaLimite.getCalendar().get(Calendar.MONTH)+1;
            int anio = dateFechaLimite.getCalendar().get(Calendar.YEAR);
            ausentismo.Fecha fechaLimite = new ausentismo.Fecha(dia, mes, anio);
            dia = dateFechaRegistroNuevo.getCalendar().get(Calendar.DAY_OF_MONTH);
            mes = dateFechaRegistroNuevo.getCalendar().get(Calendar.MONTH)+1;
            anio = dateFechaRegistroNuevo.getCalendar().get(Calendar.YEAR);
            ausentismo.Fecha fechaRegistro = new ausentismo.Fecha(dia, mes, anio);
            String compromiso = textAreaInsertCompromiso.getText();
            String observacion = textAreaInsertObservacion.getText();
            String responsable = textResponsable.getText();
            String id_seg = DatosIniciales[id_seguimiento];
            
            //SE INSERTAN EN LA BASE DE DATOS LOS CAMPOS CORRESPONDIENTES
            if(this.EI){              
                BaseDatos.Seguimiento_BD.insertarCompromisoEI(id_seg,fechaRegistro,fechaLimite,compromiso,observacion,responsable);
            }else{
                BaseDatos.Seguimiento_BD.insertarCompromiso(compromiso, fechaRegistro, fechaLimite, id_seg, responsable, observacion);
            }
            //SE PONEN EN BLANCO LAS CAJAS DE INSERSION DE DATOS
            dateFechaLimite.setCalendar(null);
            dateFechaRegistroNuevo.setCalendar(null);
            textAreaInsertCompromiso.setText("");
            textResponsable.setText("");
            textAreaInsertObservacion.setText("");
            
            //SE ACTUALIZA LA TABLA
            cargarCompromisos();
            
        }else{
            JOptionPane.showMessageDialog(null, "Por favor llene los campos.", "Error!!!",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

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
            java.util.logging.Logger.getLogger(Caso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Caso dialog = new Caso(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JButton btnAdd;
    private com.toedter.calendar.JDateChooser dateFechaLimite;
    private com.toedter.calendar.JDateChooser dateFechaRegistroNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblCC;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaCumplimiento;
    private javax.swing.JLabel lblIncapacidad;
    private javax.swing.JLabel lblMotivo;
    private javax.swing.JLabel lblNombre_Apellido;
    private javax.swing.JLabel lblResponsable;
    private javax.swing.JLabel lbl_Compromiso_Observacion;
    private javax.swing.JLabel lbl_Fecha;
    private javax.swing.JPanel panelIngresar;
    private javax.swing.JTable tablaCompromiso;
    private javax.swing.JTextArea textAreaCompromiso;
    private javax.swing.JTextArea textAreaDiagnostico;
    private javax.swing.JTextArea textAreaInsertCompromiso;
    private javax.swing.JTextArea textAreaInsertObservacion;
    private javax.swing.JTextArea textAreaRecomendacionesMedicas;
    private javax.swing.JTextField textResponsable;
    // End of variables declaration//GEN-END:variables
}
