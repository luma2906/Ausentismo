/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;

import ausentismo.GraphicsAdmin;
import ausentismo.PanelFondo;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mi-VAIO
 */
public class Estadistica extends javax.swing.JInternalFrame {

    /**
     * Creates new form Estadistica
     */
   private int Vradio=0;
   private boolean ValorF=false;//para saber si el boton de indice de frecuencia fue precionado
   private boolean ValorL=false;//para saber si el boton de indice de lesion fue precionado
   private boolean ValorS=false;//para saber si el boton de indice de severidad fue precionado
   private boolean ValorAG=false;//para saber si el boton de ausencia general fue precionado
   private boolean ValorAA=false;//para saber si el boton de accidentalidad fue precionado
   private boolean ValorFC=false;//para saber si el boton de enfermedad comun fue precionado
   private boolean ValorFP=false;//para saber si el boton de enfermedad profesional fue precionado
   private int selectArea=0;
   private int selectano=0;
   private int selectsemestre = 0;
   private int valor=2011;
   DefaultTableModel modelo = new DefaultTableModel();
   DefaultTableModel modelo2 = new DefaultTableModel();
   DefaultTableModel modelo3 = new DefaultTableModel();
   DefaultTableModel modelo4 = new DefaultTableModel();
   DefaultTableModel modelo5 = new DefaultTableModel();
   DefaultTableModel modelo6 = new DefaultTableModel();
   DefaultTableModel modelo7 = new DefaultTableModel();
   DefaultTableModel modelo8 = new DefaultTableModel();
   DefaultTableModel modelo9 = new DefaultTableModel();
   
   PanelFondo panel = new PanelFondo();
    PanelFondo panel2 = new PanelFondo();
   PanelFondo panel22 = new PanelFondo();
   GraphicsAdmin graficar;
   
    public Estadistica() {
        initComponents();            
        graficar.fondoAPanel(jPanel1,"src//Imagenes//fondo(1).gif",panel);  
        graficar.fondoAPanel(jPanel3,"src//Imagenes//fondo(1).gif",panel2); 
        conTablas.setVisible(false);
        conTablasEnfermedad.setVisible(false);
        Año.setEnabled(false);
        panelMes.setEnabled(false);
        grupo.add(Radio);
        grupo.add(Radio2);     
        grupoAusentismo.add(TasaF);
        grupoAusentismo.add(TasaS);
        grupoAusentismo.add(TasaL);
        GrupoTasas.add(TasaG1);
        GrupoTasas.add(TasaA1);
        GrupoTasas.add(TasaEcomun1);
        GrupoTasas.add(TasaEprofesional1);
        Desde.setEnabled(false);
        Hasta.setEnabled(false);
        Radio.setEnabled(false);
        Radio2.setEnabled(false);
        Radio2.setSelected(true);
        Areasc.setEnabled(false);
        
        Indices.setEnabled(false);
        
        TasaF.setEnabled(false);
        TasaS.setEnabled(false);
        TasaL.setEnabled(false);
        System.out.println("seleccion"+grupo.getElements());
        //MODELO 1
        Object array[]={
        "Enero",
        "Febrero",
        "marzo",
        "Abril",
        "Mayo",
        "Junio",
        "Julio",
        "Agosto",
        "Septiembre",
        "Octubre",
        "Noviembre",
        "Diciembre",        
        };        
        modelo.addColumn("Mes de", array);
        modelo.addColumn("Numero de trabajadores");
        modelo.addColumn("Tiempo Trabajado-Horas");
        modelo.addColumn("Dias de Ausencia ");
        modelo.addColumn("Horas De Ausencia ");
        modelo.addColumn("Tasa de Ausencia del proceso ");
        modelo.addColumn("Tasa de Ausencia General ");
        modelo.addColumn("TOTAL");
        //MODELO 2
        Object array2[]={
           "Administracion",
           "Logistica",
           "Mantenimiento",
           "Planta Lusitania",
           "Planta Corinto",
           "Obras",
        };
        modelo2.addColumn("AREA", array2);
        modelo2.addColumn("Numero de trabajadores");
        modelo2.addColumn("Tiempo Trabajado-Horas");
        modelo2.addColumn("Dias de Ausencia ");
        modelo2.addColumn("Horas De Ausencia ");
        modelo2.addColumn("Tasa de Ausencia del proceso ");
        modelo2.addColumn("Tasa de Ausencia General ");
        modelo2.addColumn("TOTAL");
        
        //TablaMia.setModel(modelo2);       
       //MODELO 3
         Object array3[]={
           "N° Casos",          
        };
        modelo3.addColumn("AREA", array3);
        modelo3.addColumn("Enero");
        modelo3.addColumn("Febrero");
        modelo3.addColumn("Marzo ");
        modelo3.addColumn("Abril ");
        modelo3.addColumn("Mayo ");
        modelo3.addColumn("Junio ");
        modelo3.addColumn("Primer Semestre");
        modelo3.addColumn("Julio");
        modelo3.addColumn("Agosto");
        modelo3.addColumn("Septiembre ");
        modelo3.addColumn("Octubre");
        modelo3.addColumn("Noviembre");
        modelo3.addColumn("Dicciembre");
        modelo3.addColumn("Segundo Semestre");
        modelo3.addColumn("Anual");
        
        
        //MODELO 4
         Object array4[]={
           "Días",
           "N dias cargados",
           "N de dias",
           
        };
        modelo4.addColumn("Indice De severidad", array4);
        modelo4.addColumn("Enero");
        modelo4.addColumn("Febrero");
        modelo4.addColumn("Marzo");
        modelo4.addColumn("Abril");
        modelo4.addColumn("Mayo");
        modelo4.addColumn("Junio");
        modelo4.addColumn("Primer Semestre");
        modelo4.addColumn("Julio");
        modelo4.addColumn("Agosto");
        modelo4.addColumn("Septiembre");
        modelo4.addColumn("Octubre");
        modelo4.addColumn("Noviembre");
        modelo4.addColumn("Dicciembre");
        modelo4.addColumn("Segundo Semestre");
        modelo4.addColumn("Anual");
       
        //MODELO 5
         Object array5[]={
           "Ano"+valor,
          
        };
        modelo5.addColumn("Indice De Lesion", array5);
        modelo5.addColumn("Primer Semestre");
        modelo5.addColumn("Segundo Semestre");
        modelo5.addColumn("Anual ");
        
       //MODELO 6
        Object array6[]={
            "Días",
            "Horas"
        };
        modelo6.addColumn("Tasa Ausencia General",array6);
        modelo6.addColumn("Enero");
        modelo6.addColumn("Febrero");
        modelo6.addColumn("Marzo ");
        modelo6.addColumn("Abril ");
        modelo6.addColumn("Mayo ");
        modelo6.addColumn("Junio ");
        modelo6.addColumn("Primer Semestre");
        modelo6.addColumn("Julio");
        modelo6.addColumn("Agosto");
        modelo6.addColumn("Septiembre ");
        modelo6.addColumn("Octubre");
        modelo6.addColumn("Noviembre");
        modelo6.addColumn("Dicciembre");
        modelo6.addColumn("Segundo Semestre");
        modelo6.addColumn("Anual");        
       
      //MODELO 7
        Object array7[]={
            "Días",
            "Horas"
        };
        modelo7.addColumn("Tasa Accidentalidad",array7);
        modelo7.addColumn("Enero");
        modelo7.addColumn("Febrero");
        modelo7.addColumn("Marzo ");
        modelo7.addColumn("Abril ");
        modelo7.addColumn("Mayo ");
        modelo7.addColumn("Junio ");
        modelo7.addColumn("Primer Semestre");
        modelo7.addColumn("Julio");
        modelo7.addColumn("Agosto");
        modelo7.addColumn("Septiembre ");
        modelo7.addColumn("Octubre");
        modelo7.addColumn("Noviembre");
        modelo7.addColumn("Dicciembre");
        modelo7.addColumn("Segundo Semestre");
        modelo7.addColumn("Anual");  
        
      //MODELO 8
        Object array8[]={
            "Días",
            "Horas"
        };
        modelo8.addColumn("Tasa Por Enfermedad comun",array8);
        modelo8.addColumn("Enero");
        modelo8.addColumn("Febrero");
        modelo8.addColumn("Marzo ");
        modelo8.addColumn("Abril ");
        modelo8.addColumn("Mayo ");
        modelo8.addColumn("Junio ");
        modelo8.addColumn("Primer Semestre");
        modelo8.addColumn("Julio");
        modelo8.addColumn("Agosto");
        modelo8.addColumn("Septiembre ");
        modelo8.addColumn("Octubre");
        modelo8.addColumn("Noviembre");
        modelo8.addColumn("Dicciembre");
        modelo8.addColumn("Segundo Semestre");
        modelo8.addColumn("Anual");   
     //MODELO 9
        Object array9[]={
            "Días",
            "Horas"
        };
        modelo9.addColumn("Tasa Por Enfermedad Profesional",array9);
        modelo9.addColumn("Enero");
        modelo9.addColumn("Febrero");
        modelo9.addColumn("Marzo ");
        modelo9.addColumn("Abril ");
        modelo9.addColumn("Mayo ");
        modelo9.addColumn("Junio ");
        modelo9.addColumn("Primer Semestre");
        modelo9.addColumn("Julio");
        modelo9.addColumn("Agosto");
        modelo9.addColumn("Septiembre ");
        modelo9.addColumn("Octubre");
        modelo9.addColumn("Noviembre");
        modelo9.addColumn("Dicciembre");
        modelo9.addColumn("Segundo Semestre");
        modelo9.addColumn("Anual");  
        
    }
    
    
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        grupoAusentismo = new javax.swing.ButtonGroup();
        grupoAccidentalidad = new javax.swing.ButtonGroup();
        GrupoTasas = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        TablaPanel = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        panelarea = new javax.swing.JPanel();
        Area = new javax.swing.JCheckBox();
        Año = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        panelMes = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Radio = new javax.swing.JRadioButton();
        Radio2 = new javax.swing.JRadioButton();
        Areasc = new javax.swing.JComboBox();
        panelanos = new javax.swing.JPanel();
        ano = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        Desde = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Hasta = new javax.swing.JTextField();
        Indices = new javax.swing.JPanel();
        TasaF = new javax.swing.JRadioButton();
        TasaS = new javax.swing.JRadioButton();
        TasaL = new javax.swing.JRadioButton();
        conTablas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaMia = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        conTablasEnfermedad = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaMia1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        Desde1 = new javax.swing.JTextField();
        Hasta1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Tasas1 = new javax.swing.JPanel();
        TasaG1 = new javax.swing.JRadioButton();
        TasaA1 = new javax.swing.JRadioButton();
        TasaEcomun1 = new javax.swing.JRadioButton();
        TasaEprofesional1 = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();

        jPanel4.setBackground(new java.awt.Color(99, 130, 191));

        TablaPanel.setBackground(new java.awt.Color(255, 102, 255));
        TablaPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                TablaPanelComponentResized(evt);
            }
        });

        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentResized(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel3.setText("Estadisticas Ausentismo De Accidentalidad");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Table.gif"))); // NOI18N
        jButton1.setText("Mostrar Tabla");
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        panelarea.setBorder(javax.swing.BorderFactory.createTitledBorder("Por Area"));
        panelarea.setOpaque(false);

        Area.setText("Por Area");
        Area.setOpaque(false);
        Area.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AreaActionPerformed(evt);
            }
        });

        Año.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AñoActionPerformed(evt);
            }
        });

        jLabel2.setText("Año");

        panelMes.setOpaque(false);

        jLabel4.setText("Ver por mes");

        Radio.setText("SI");
        Radio.setOpaque(false);
        Radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioActionPerformed(evt);
            }
        });

        Radio2.setText("NO");
        Radio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Radio2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMesLayout = new javax.swing.GroupLayout(panelMes);
        panelMes.setLayout(panelMesLayout);
        panelMesLayout.setHorizontalGroup(
            panelMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(panelMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Radio2)
                    .addComponent(Radio))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panelMesLayout.setVerticalGroup(
            panelMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMesLayout.createSequentialGroup()
                .addGroup(panelMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Radio)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Radio2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Areasc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administracion", "Logistica", "Mantenimiento", "Planta Lusitania", "Planta Corinto", "Obras", " " }));
        Areasc.setOpaque(false);

        javax.swing.GroupLayout panelareaLayout = new javax.swing.GroupLayout(panelarea);
        panelarea.setLayout(panelareaLayout);
        panelareaLayout.setHorizontalGroup(
            panelareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelareaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelareaLayout.createSequentialGroup()
                        .addComponent(Area)
                        .addGap(90, 90, 90)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Año, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Areasc, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelareaLayout.setVerticalGroup(
            panelareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelareaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelareaLayout.createSequentialGroup()
                        .addGroup(panelareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Area)
                            .addComponent(jLabel2)
                            .addComponent(Año, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Areasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelanos.setBorder(javax.swing.BorderFactory.createTitledBorder("Por Años"));
        panelanos.setOpaque(false);

        ano.setText("Por Año");
        ano.setOpaque(false);
        ano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anoActionPerformed(evt);
            }
        });

        jLabel1.setText("Desde");

        Desde.setOpaque(false);

        jLabel5.setText("Hasta");

        Hasta.setOpaque(false);
        Hasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HastaActionPerformed(evt);
            }
        });

        Indices.setBorder(javax.swing.BorderFactory.createTitledBorder("Accidentalidad"));
        Indices.setOpaque(false);

        TasaF.setText("Indice de Frecuencia");
        TasaF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TasaFActionPerformed(evt);
            }
        });

        TasaS.setText("Indice de Severidad");
        TasaS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TasaSActionPerformed(evt);
            }
        });

        TasaL.setText("Indice de Lesiones");
        TasaL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TasaLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout IndicesLayout = new javax.swing.GroupLayout(Indices);
        Indices.setLayout(IndicesLayout);
        IndicesLayout.setHorizontalGroup(
            IndicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, IndicesLayout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(IndicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TasaF)
                    .addGroup(IndicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TasaL)
                        .addComponent(TasaS)))
                .addGap(24, 24, 24))
        );

        IndicesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {TasaF, TasaL, TasaS});

        IndicesLayout.setVerticalGroup(
            IndicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IndicesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TasaF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TasaS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TasaL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelanosLayout = new javax.swing.GroupLayout(panelanos);
        panelanos.setLayout(panelanosLayout);
        panelanosLayout.setHorizontalGroup(
            panelanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelanosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelanosLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Desde, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addComponent(ano, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(Hasta, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(Indices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelanosLayout.setVerticalGroup(
            panelanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelanosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ano)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Desde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(Hasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelanosLayout.createSequentialGroup()
                .addComponent(Indices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        conTablas.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        conTablas.setOpaque(false);

        TablaMia.setModel(new javax.swing.table.DefaultTableModel(
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
        TablaMia.setOpaque(false);
        jScrollPane1.setViewportView(TablaMia);

        javax.swing.GroupLayout conTablasLayout = new javax.swing.GroupLayout(conTablas);
        conTablas.setLayout(conTablasLayout);
        conTablasLayout.setHorizontalGroup(
            conTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1193, Short.MAX_VALUE)
        );
        conTablasLayout.setVerticalGroup(
            conTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conTablasLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(28, 28, 28)
                        .addComponent(panelanos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addComponent(jLabel3))
                    .addComponent(conTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addComponent(panelanos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(conTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(445, Short.MAX_VALUE))
        );

        TablaPanel.addTab("Ausencia  por Accidentalidad", jPanel1);

        conTablasEnfermedad.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        conTablasEnfermedad.setOpaque(false);

        TablaMia1.setModel(new javax.swing.table.DefaultTableModel(
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
        TablaMia1.setOpaque(false);
        jScrollPane2.setViewportView(TablaMia1);

        javax.swing.GroupLayout conTablasEnfermedadLayout = new javax.swing.GroupLayout(conTablasEnfermedad);
        conTablasEnfermedad.setLayout(conTablasEnfermedadLayout);
        conTablasEnfermedadLayout.setHorizontalGroup(
            conTablasEnfermedadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        conTablasEnfermedadLayout.setVerticalGroup(
            conTablasEnfermedadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, conTablasEnfermedadLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel6.setText("Estadisticas Ausentismo por Enfermedad");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("año"));

        jLabel9.setText("Desde");

        Desde1.setOpaque(false);

        Hasta1.setOpaque(false);
        Hasta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hasta1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Hasta");

        Tasas1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ausentismo"));

        TasaG1.setText("Tasa por Ausentismo General");
        TasaG1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TasaG1ActionPerformed(evt);
            }
        });

        TasaA1.setText("Tasa por Ausentismo Accidentalidad");
        TasaA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TasaA1ActionPerformed(evt);
            }
        });

        TasaEcomun1.setText("Tasa por  Enfermedad Comun");
        TasaEcomun1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TasaEcomun1ActionPerformed(evt);
            }
        });

        TasaEprofesional1.setText("Tasapor  Enferemedad Profesional");
        TasaEprofesional1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TasaEprofesional1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Tasas1Layout = new javax.swing.GroupLayout(Tasas1);
        Tasas1.setLayout(Tasas1Layout);
        Tasas1Layout.setHorizontalGroup(
            Tasas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TasaA1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
            .addComponent(TasaEcomun1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(TasaG1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(TasaEprofesional1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Tasas1Layout.setVerticalGroup(
            Tasas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tasas1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TasaG1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TasaA1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TasaEcomun1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TasaEprofesional1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(170, 170, 170))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Desde1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addGap(7, 7, 7)
                .addComponent(Hasta1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Tasas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(Desde1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(Hasta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 133, Short.MAX_VALUE))
                    .addComponent(Tasas1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, Short.MAX_VALUE))
                .addContainerGap())
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Table.gif"))); // NOI18N
        jButton2.setText("Mostrar Tabla");
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 966, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(250, 250, 250)
                                        .addComponent(jLabel6))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))
                        .addGap(0, 145, Short.MAX_VALUE))
                    .addComponent(conTablasEnfermedad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(conTablasEnfermedad, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(555, Short.MAX_VALUE))
        );

        TablaPanel.addTab("Ausencia por Enfermedad", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TablaPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1186, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TablaPanel, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void anoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anoActionPerformed
        // TODO add your handling code here:
     
        if(ano.isSelected()){            
            selectArea=0;
            selectano=1;
       
            Radio2.setSelected(true);
            TablaMia.removeAll();
            conTablas.setVisible(false);
            Area.setSelected(false);
            Desde.setEnabled(true);
            Hasta.setEnabled(true);
            panelanos.setEnabled(true);
            Indices.setEnabled(true);
            panelarea.setEnabled(false);
            Areasc.setEnabled(false);
            Año.setEnabled(false);
            //Area.setEnabled(false);
            panelMes.setEnabled(false);
            Radio.setEnabled(false);
            Radio2.setEnabled(false);
            
            //TASAS E IDICE LOS VUELVE EDITABLES
       
            TasaF.setEnabled(true);
            TasaS.setEnabled(true);
            TasaL.setEnabled(true);
      
        
        }

    }//GEN-LAST:event_anoActionPerformed

    private void Radio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Radio2ActionPerformed
        // TODO add your handling code here:
        Vradio=0;
        if(Radio2.isSelected()){
            Areasc.setEnabled(false);
        }else{
            System.out.println("ERROR");
        }

    }//GEN-LAST:event_Radio2ActionPerformed

    private void RadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioActionPerformed
        // TODO add your handling code here:
        Vradio=1;
        if(Radio.isSelected()){
            Areasc.setEnabled(true);
        }else{
            System.out.println("ERROR");
        }

    }//GEN-LAST:event_RadioActionPerformed

    private void AñoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AñoActionPerformed
        // TODO add your handling code here:
        TablaMia.removeAll();
        TablaMia.setModel(modelo);
    }//GEN-LAST:event_AñoActionPerformed

    private void AreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AreaActionPerformed
        // TODO add your handling code here:
        if(Area.isSelected()){
            panelanos.setEnabled(false);
            panelarea.setEnabled(true);
            selectArea=1;
            selectano=0;
            Año.setEnabled(true);
            Desde.setEnabled(false);
            Hasta.setEnabled(false);
            Indices.setEnabled(false);
   
            //Area.setEnabled(false);
            ano.setSelected(false);
            panelMes.setEnabled(true);
            Radio.setEnabled(true);
            Radio2.setEnabled(true);
            
          
            Indices.setEnabled(false);
        
            TasaF.setEnabled(false);
            TasaS.setEnabled(false);
            TasaL.setEnabled(false);
        }else{
            selectArea=0;
            Areasc.setEnabled(false);
            Año.setEnabled(false);
            //Area.setEnabled(false);
            panelMes.setEnabled(false);
            Radio.setEnabled(false);
            Radio2.setEnabled(false);
        }

    }//GEN-LAST:event_AreaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(Vradio==0 && selectArea==1){
            System.out.println("           "+Año.getText());
            if(Año.getText().trim().length()!=0){ // aqui valido que el campo año no este vacio
                System.out.println("dentrooooooooooooooooooooooooooooooooooooooo");
                TablaMia.removeAll();
                TablaMia.setModel(modelo2);
                conTablas.setVisible(true);
                TablaMia.getColumnModel().getColumn(1).setMaxWidth(600);
                TablaMia.getColumnModel().getColumn(0).setPreferredWidth(30);
                TablaMia.getColumnModel().getColumn(1).setPreferredWidth(150);
                TablaMia.getColumnModel().getColumn(2).setPreferredWidth(100);
                TablaMia.getColumnModel().getColumn(3).setPreferredWidth(80);
                TablaMia.getColumnModel().getColumn(4).setPreferredWidth(80);
                TablaMia.getColumnModel().getColumn(5).setPreferredWidth(120);
                TablaMia.getColumnModel().getColumn(6).setPreferredWidth(100);
               
               
            }else{
                JOptionPane.showMessageDialog(null,"campo año esta vacio");
            }
        }
        if(Vradio==1 && selectArea==1){
             if(Año.getText().trim().length()!=0){ // aqui valido que el campo año no este vacio
                TablaMia.removeAll();
                TablaMia.setModel(modelo);
                conTablas.setVisible(true);
                TablaMia.getColumnModel().getColumn(1).setMaxWidth(600);
                TablaMia.getColumnModel().getColumn(0).setPreferredWidth(30);
                TablaMia.getColumnModel().getColumn(1).setPreferredWidth(150);
                TablaMia.getColumnModel().getColumn(2).setPreferredWidth(100);
                TablaMia.getColumnModel().getColumn(3).setPreferredWidth(80);
                TablaMia.getColumnModel().getColumn(4).setPreferredWidth(80);
                TablaMia.getColumnModel().getColumn(5).setPreferredWidth(120);
                TablaMia.getColumnModel().getColumn(6).setPreferredWidth(100);
                
             }else{
                JOptionPane.showMessageDialog(null,"el campo año esta vacio");
            }
        }
        
        if(selectano==1 && ValorF==true){
            if(Desde.getText().trim().length()!=0 && Hasta.getText().trim().length()!=0){
                TablaMia.removeAll();
                TablaMia.setModel(modelo3);
                conTablas.setVisible(true);
                TablaMia.getColumnModel().getColumn(1).setMaxWidth(600);
                TablaMia.getColumnModel().getColumn(0).setPreferredWidth(30);
                TablaMia.getColumnModel().getColumn(1).setPreferredWidth(50);
                TablaMia.getColumnModel().getColumn(2).setPreferredWidth(30);
                TablaMia.getColumnModel().getColumn(3).setPreferredWidth(30);
                TablaMia.getColumnModel().getColumn(4).setPreferredWidth(30);
                TablaMia.getColumnModel().getColumn(5).setPreferredWidth(30);
                TablaMia.getColumnModel().getColumn(6).setPreferredWidth(30);
                TablaMia.getColumnModel().getColumn(7).setPreferredWidth(80);
                TablaMia.getColumnModel().getColumn(8).setPreferredWidth(20);
                TablaMia.getColumnModel().getColumn(9).setPreferredWidth(40);
                TablaMia.getColumnModel().getColumn(10).setPreferredWidth(60);
                TablaMia.getColumnModel().getColumn(11).setPreferredWidth(40);
                TablaMia.getColumnModel().getColumn(12).setPreferredWidth(40);
                TablaMia.getColumnModel().getColumn(13).setPreferredWidth(40);
                TablaMia.getColumnModel().getColumn(12).setPreferredWidth(40);
                TablaMia.getColumnModel().getColumn(14).setPreferredWidth(90);
                TablaMia.getColumnModel().getColumn(15).setPreferredWidth(30);
            }else{
                JOptionPane.showMessageDialog(null,"el campo año esta vacio");
            }
        }else{
            if (selectano==1 && ValorL==true){
                if(Desde.getText().trim().length()!=0 && Hasta.getText().trim().length()!=0){
                    TablaMia.removeAll();
                    TablaMia.setModel(modelo4);
                    conTablas.setVisible(true);                
                    TablaMia.getColumnModel().getColumn(0).setPreferredWidth(100);
                    TablaMia.getColumnModel().getColumn(1).setPreferredWidth(20);
                    TablaMia.getColumnModel().getColumn(2).setPreferredWidth(30);
                    TablaMia.getColumnModel().getColumn(3).setPreferredWidth(20);
                    TablaMia.getColumnModel().getColumn(4).setPreferredWidth(20);
                    TablaMia.getColumnModel().getColumn(5).setPreferredWidth(20);
                    TablaMia.getColumnModel().getColumn(6).setPreferredWidth(20);
                    TablaMia.getColumnModel().getColumn(7).setPreferredWidth(80);
                    TablaMia.getColumnModel().getColumn(8).setPreferredWidth(20);
                    TablaMia.getColumnModel().getColumn(9).setPreferredWidth(40);
                    TablaMia.getColumnModel().getColumn(10).setPreferredWidth(60);
                    TablaMia.getColumnModel().getColumn(11).setPreferredWidth(40);
                    TablaMia.getColumnModel().getColumn(12).setPreferredWidth(40);
                    TablaMia.getColumnModel().getColumn(13).setPreferredWidth(40);
                    TablaMia.getColumnModel().getColumn(12).setPreferredWidth(50);
                    TablaMia.getColumnModel().getColumn(14).setPreferredWidth(90);
                    TablaMia.getColumnModel().getColumn(15).setPreferredWidth(30);
                    
                    
                }else{
                    JOptionPane.showMessageDialog(null,"el campo año esta vacio");
                }
            }else{
                if (selectano==1 && ValorS==true){
                   if(Desde.getText().trim().length()!=0 && Hasta.getText().trim().length()!=0){
                        TablaMia.removeAll();
                        TablaMia.setModel(modelo5);
                        conTablas.setVisible(true);                        
                        TablaMia.getColumnModel().getColumn(0).setPreferredWidth(30);
                        TablaMia.getColumnModel().getColumn(1).setPreferredWidth(50);
                        TablaMia.getColumnModel().getColumn(2).setPreferredWidth(30);
                        TablaMia.getColumnModel().getColumn(3).setPreferredWidth(30);
                        
                   }else{
                       JOptionPane.showMessageDialog(null,"el campo año esta vacio");
                   }
                }
            }
            
        }
       
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TablaPanelComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_TablaPanelComponentResized
        // TODO add your handling code here:
        
    }//GEN-LAST:event_TablaPanelComponentResized

    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentResized
        // TODO add your handling code here:
        panel.setSize(jPanel1.getSize());        
    }//GEN-LAST:event_jPanel1ComponentResized

    private void HastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HastaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HastaActionPerformed

    private void TasaEcomun1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TasaEcomun1ActionPerformed
        // TODO add your handling code here:
        ValorFC=TasaEcomun1.isSelected();
         ValorF=false;
         ValorS=false;
         ValorAG=false;
         ValorAA=false;
         ValorL=false;
         ValorFP=false;
    }//GEN-LAST:event_TasaEcomun1ActionPerformed

    private void TasaA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TasaA1ActionPerformed
        // TODO add your handling code here:
        ValorAA=TasaA1.isSelected();
         ValorF=false;
         ValorS=false;
         ValorAG=false;
         ValorL=false;
         ValorFC=false;
         ValorFP=false;
    }//GEN-LAST:event_TasaA1ActionPerformed

    private void Hasta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Hasta1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Hasta1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        System.out.println("valor"+ValorAG);
        if(ValorAG==true){
            System.out.println("dentrooooo");
            TablaMia1.removeAll();
            TablaMia1.setModel(modelo6);
            conTablasEnfermedad.setVisible(true); 
            TablaMia1.getColumnModel().getColumn(0).setPreferredWidth(100);
            TablaMia1.getColumnModel().getColumn(1).setPreferredWidth(20);
            TablaMia1.getColumnModel().getColumn(2).setPreferredWidth(30);
            TablaMia1.getColumnModel().getColumn(3).setPreferredWidth(20);
            TablaMia1.getColumnModel().getColumn(4).setPreferredWidth(20);
            TablaMia1.getColumnModel().getColumn(5).setPreferredWidth(20);
            TablaMia1.getColumnModel().getColumn(6).setPreferredWidth(20);
            TablaMia1.getColumnModel().getColumn(7).setPreferredWidth(80);
            TablaMia1.getColumnModel().getColumn(8).setPreferredWidth(20);
            TablaMia1.getColumnModel().getColumn(9).setPreferredWidth(40);
            TablaMia1.getColumnModel().getColumn(10).setPreferredWidth(60);
            TablaMia1.getColumnModel().getColumn(11).setPreferredWidth(40);
            TablaMia1.getColumnModel().getColumn(12).setPreferredWidth(40);
            TablaMia1.getColumnModel().getColumn(13).setPreferredWidth(40);
            TablaMia1.getColumnModel().getColumn(12).setPreferredWidth(50);
            TablaMia1.getColumnModel().getColumn(14).setPreferredWidth(90);
            TablaMia1.getColumnModel().getColumn(15).setPreferredWidth(30);
            
        }else{
            if(ValorAA==true){
                TablaMia1.removeAll();
                TablaMia1.setModel(modelo7);
                conTablasEnfermedad.setVisible(true);  
                TablaMia1.getColumnModel().getColumn(0).setPreferredWidth(100);
                TablaMia1.getColumnModel().getColumn(1).setPreferredWidth(20);
                TablaMia1.getColumnModel().getColumn(2).setPreferredWidth(30);
                TablaMia1.getColumnModel().getColumn(3).setPreferredWidth(20);
                TablaMia1.getColumnModel().getColumn(4).setPreferredWidth(20);
                TablaMia1.getColumnModel().getColumn(5).setPreferredWidth(20);
                TablaMia1.getColumnModel().getColumn(6).setPreferredWidth(20);
                TablaMia1.getColumnModel().getColumn(7).setPreferredWidth(80);
                TablaMia1.getColumnModel().getColumn(8).setPreferredWidth(20);
                TablaMia1.getColumnModel().getColumn(9).setPreferredWidth(40);
                TablaMia1.getColumnModel().getColumn(10).setPreferredWidth(60);
                TablaMia1.getColumnModel().getColumn(11).setPreferredWidth(40);
                TablaMia1.getColumnModel().getColumn(12).setPreferredWidth(40);
                TablaMia1.getColumnModel().getColumn(13).setPreferredWidth(40);
                TablaMia1.getColumnModel().getColumn(12).setPreferredWidth(50);
                TablaMia1.getColumnModel().getColumn(14).setPreferredWidth(90);
                TablaMia1.getColumnModel().getColumn(15).setPreferredWidth(30);
            }else{
                if(ValorFC==true){
                    TablaMia1.removeAll();
                    TablaMia1.setModel(modelo8);
                    conTablasEnfermedad.setVisible(true);    
                    TablaMia1.getColumnModel().getColumn(0).setPreferredWidth(100);
            TablaMia1.getColumnModel().getColumn(1).setPreferredWidth(20);
            TablaMia1.getColumnModel().getColumn(2).setPreferredWidth(30);
            TablaMia1.getColumnModel().getColumn(3).setPreferredWidth(20);
            TablaMia1.getColumnModel().getColumn(4).setPreferredWidth(20);
            TablaMia1.getColumnModel().getColumn(5).setPreferredWidth(20);
            TablaMia1.getColumnModel().getColumn(6).setPreferredWidth(20);
            TablaMia1.getColumnModel().getColumn(7).setPreferredWidth(80);
            TablaMia1.getColumnModel().getColumn(8).setPreferredWidth(20);
            TablaMia1.getColumnModel().getColumn(9).setPreferredWidth(40);
            TablaMia1.getColumnModel().getColumn(10).setPreferredWidth(60);
            TablaMia1.getColumnModel().getColumn(11).setPreferredWidth(40);
            TablaMia1.getColumnModel().getColumn(12).setPreferredWidth(40);
            TablaMia1.getColumnModel().getColumn(13).setPreferredWidth(40);
            TablaMia1.getColumnModel().getColumn(12).setPreferredWidth(50);
            TablaMia1.getColumnModel().getColumn(14).setPreferredWidth(90);
            TablaMia1.getColumnModel().getColumn(15).setPreferredWidth(30);
                }else{
                    if(ValorFP==true){
                        TablaMia1.removeAll();
                        TablaMia1.setModel(modelo9);
                        conTablasEnfermedad.setVisible(true); 
                        TablaMia1.getColumnModel().getColumn(0).setPreferredWidth(100);
            TablaMia1.getColumnModel().getColumn(1).setPreferredWidth(20);
            TablaMia1.getColumnModel().getColumn(2).setPreferredWidth(30);
            TablaMia1.getColumnModel().getColumn(3).setPreferredWidth(20);
            TablaMia1.getColumnModel().getColumn(4).setPreferredWidth(20);
            TablaMia1.getColumnModel().getColumn(5).setPreferredWidth(20);
            TablaMia1.getColumnModel().getColumn(6).setPreferredWidth(20);
            TablaMia1.getColumnModel().getColumn(7).setPreferredWidth(80);
            TablaMia1.getColumnModel().getColumn(8).setPreferredWidth(20);
            TablaMia1.getColumnModel().getColumn(9).setPreferredWidth(40);
            TablaMia1.getColumnModel().getColumn(10).setPreferredWidth(60);
            TablaMia1.getColumnModel().getColumn(11).setPreferredWidth(40);
            TablaMia1.getColumnModel().getColumn(12).setPreferredWidth(40);
            TablaMia1.getColumnModel().getColumn(13).setPreferredWidth(40);
            TablaMia1.getColumnModel().getColumn(12).setPreferredWidth(50);
            TablaMia1.getColumnModel().getColumn(14).setPreferredWidth(90);
            TablaMia1.getColumnModel().getColumn(15).setPreferredWidth(30);
                    }
                }
            }
        
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void TasaFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TasaFActionPerformed
        // TODO add your handling code here:
            
            ValorF=TasaF.isSelected();
            ValorS=false;
            ValorL=false;
            ValorAG=false;
            ValorAA=false;
            ValorFC=false;
            ValorFP=false;
            
            
    }//GEN-LAST:event_TasaFActionPerformed

    private void TasaSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TasaSActionPerformed
        // TODO add your handling code here:
         ValorS=TasaS.isSelected();
         ValorF=false;
         ValorL=false;
         ValorAG=false;
         ValorAA=false;
         ValorFC=false;
         ValorFP=false;
    }//GEN-LAST:event_TasaSActionPerformed

    private void TasaLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TasaLActionPerformed
        // TODO add your handling code here:
         ValorL=TasaL.isSelected();
         ValorF=false;
         ValorS=false;
         ValorAG=false;
         ValorAA=false;
         ValorFC=false;
         ValorFP=false;
    }//GEN-LAST:event_TasaLActionPerformed

    private void TasaG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TasaG1ActionPerformed
        // TODO add your handling code here:
        ValorAG=TasaG1.isSelected();
         ValorF=false;
         ValorS=false;
         ValorL=false;
         ValorAA=false;
         ValorFC=false;
         ValorFP=false;
    }//GEN-LAST:event_TasaG1ActionPerformed

    private void TasaEprofesional1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TasaEprofesional1ActionPerformed
        // TODO add your handling code here:
        ValorFP=TasaEprofesional1.isSelected();
         ValorF=false;
         ValorS=false;
         ValorAG=false;
         ValorAA=false;
         ValorFC=false;
         ValorL=false;
    }//GEN-LAST:event_TasaEprofesional1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Area;
    private javax.swing.JComboBox Areasc;
    private javax.swing.JTextField Año;
    private javax.swing.JTextField Desde;
    private javax.swing.JTextField Desde1;
    private javax.swing.ButtonGroup GrupoTasas;
    private javax.swing.JTextField Hasta;
    private javax.swing.JTextField Hasta1;
    private javax.swing.JPanel Indices;
    private javax.swing.JRadioButton Radio;
    private javax.swing.JRadioButton Radio2;
    private javax.swing.JTable TablaMia;
    private javax.swing.JTable TablaMia1;
    private javax.swing.JTabbedPane TablaPanel;
    private javax.swing.JRadioButton TasaA1;
    private javax.swing.JRadioButton TasaEcomun1;
    private javax.swing.JRadioButton TasaEprofesional1;
    private javax.swing.JRadioButton TasaF;
    private javax.swing.JRadioButton TasaG1;
    private javax.swing.JRadioButton TasaL;
    private javax.swing.JRadioButton TasaS;
    private javax.swing.JPanel Tasas1;
    private javax.swing.JCheckBox ano;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel conTablas;
    private javax.swing.JPanel conTablasEnfermedad;
    private javax.swing.ButtonGroup grupo;
    private javax.swing.ButtonGroup grupoAccidentalidad;
    private javax.swing.ButtonGroup grupoAusentismo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JPanel panelMes;
    private javax.swing.JPanel panelanos;
    private javax.swing.JPanel panelarea;
    // End of variables declaration//GEN-END:variables
}
