package Graficos;

import Graficos.Filtros.FiltroAvanzadoConsultaDemografia;
import ausentismo.ModeloTablaEmpleado;
import ausentismo.Trabajador;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Demografia2 extends javax.swing.JInternalFrame {

    private BaseDatos.Demografia_BD baseDeDatos; //Variable donde se almacena la clase que hace la interconexion entre la interfaz gráfica de Demografia y la Base de Datos.
    private int TiempoDesplazamiento; //Variable donde se almacena el tiempo de transporte gastado por el trabajador para llegar al trabajo, que es tomado de la ventana de Desplazamiento al trabajo
    private String TipoDeTransporte; //Variable donde se almacena el tipo de transporte usado por el trabajador que es tomado de la ventana de Desplazamiento al trabajo
    private ArrayList<Trabajador> Trabajadores; //Variable usada para almacenar los datos de los trabajadores que van a ser mostrados en la tabla de consulta
    private DesplazamientoT despl = null; //Variable donde se va a almacenar la ventana donde muestra los campos correspondientes al desplazamiento al trabajo
    private DatosAdicionalesDemografia datosAdicionales; //Variable donde se almacena la ventana de Datos adicionales que son los campos nuevos que el usuario a ingresado para llevar un mejor control de la demografia de la empresa
    private int cc_A_Retirar; //Variable de clase donde se va a almacenar el numero de cc del trabajador a retirar
    private String[] valorCamposAdicionales;//Este es el valor de los campos nuevos que ya han sido llenados en su correspondiente form.
    private FiltroAvanzadoConsultaDemografia filtroAvanzado; //Formulario para llenar el filtro avanzado de la consulta de Demografia
    private ausentismo.ValidarDatos Validador; //Objeto usado para validar los datos que sean necesarios en el formulario
    private String idIngresoARetirar = ""; //Esta variable se utiliza en caso de querer retirar a alguien, para poder relacionar el retiro con el ultimo ingreso a la empresa
    private boolean trabajadorRetirado = false; //Esta variable es para verificar si el trabajador se encuentra en la bd pero retirado
    
    public Demografia2() {
        initComponents();
        String[] camposNuevos = BaseDatos.Demografia_BD.getNuevosCampos();
        System.out.println("-------LLEGA AQUI-------");
        //Se agrupan los Radio Button---------------------------
        Sex.add(Femenino);
        Sex.add(Masculino);
        Educacion.add(DE1);
        Educacion.add(DE2);
        Educacion.add(DE3);
        Educacion.add(DE4);
        Educacion.add(DE5);
        Educacion.add(DE6);
        Educacion.add(DE7);
        Educacion.add(DE8);
        Educacion.add(DE9);
        //---------------------------------------------
        tabla();
        ocultarBarraTitulo();
        ponerIconos();
        TiempoDesplazamiento =0;
        TipoDeTransporte = "";
        despl = new DesplazamientoT(null,true);
        despl.setLocationRelativeTo(btnDesplazamientoTrabajo);
        Trabajadores = null;
        datosAdicionales = new DatosAdicionalesDemografia(null, true);
        datosAdicionales.setCampos(camposNuevos);
        filtroAvanzado = new FiltroAvanzadoConsultaDemografia(null,true);
        filtroAvanzado.setCamposNuevos(camposNuevos);
        dateFechaNacimiento.setEnabled(false);
        dateFechaNacimiento.getCalendarButton().setEnabled(true);
        dateFechaIngreso.setEnabled(false);
        dateFechaIngreso.getCalendarButton().setEnabled(true);
        cc_A_Retirar=0;
        initValorCamposAdicionales(camposNuevos.length);
        Validador = new ausentismo.ValidarDatos();
        DesactivarControles();
        
    }
    private void initValorCamposAdicionales(int tam){
        valorCamposAdicionales = new String[tam];
        for (int i = 0; i < tam; i++) {
            valorCamposAdicionales[i] = "";
            
        }
    }
    private void aMayuscula(javax.swing.JTextField texto){
        texto.setText(texto.getText().toUpperCase());
    }
    private boolean Validar(){
        ausentismo.ValidarDatos validar = new ausentismo.ValidarDatos();
        return validar.Vnumero(txtCC.getText()) &&
                validar.VcampoVacio(txtCC.getText()) &&
                validar.VcampoVacio(txtNombre.getText()) &&
                validar.VcampoVacio(txtApellido.getText())&&
                validar.VcampoVacio(txtCargo.getText()) &&
                validar.VcampoVacio(txtPersonasACargo.getText()) &&
                validar.Vnumero(txtPersonasACargo.getText()) &&
                dateFechaIngreso.getDate() != null &&
                dateFechaNacimiento.getDate() != null &&
                !TipoDeTransporte.equals("");
                
        
        
    }
    
    
    /** 
     * Funcion usada para agregar la clase con la cual va interactuar con la BD
     * Esta funcion no solo se encarga de la agregar dicha clase si no que tambien
     * muestra todos los trabajadores en la tabla de consulta
     * y muestra los datos de las Estadisticas de transporte en su respectiva tabla
     * @param BD es el tipo de clase para poder interactuar con la base de datos
     */
    public void setBD(BaseDatos.Demografia_BD BD){
        this.baseDeDatos = BD;
        Trabajadores = baseDeDatos.getTrabajadores();
        mostrarTrabajadores();
        tableTransporte.setModel(this.baseDeDatos.EstadisticaTransporte());
        
    }
    private void tabla(){
        TablaCons.setModel(new ModeloTablaEmpleado());
        
        /*for (int i = 0; i < 17; i++) {
            TablaCons.getColumnModel().getColumn(i).setPreferredWidth(500);
            
        }*/
        
    }
    /** 
     * Funcion usada para Mostrar los datos de los trabajadores que esten en la
     * variable de clase Trabajadores. Estos datos son mostrados en la tabla
     * que esta en la parte de consulta de Demografia.
     */
    private void mostrarTrabajadores(){
        for (int i = 0; i < Trabajadores.size(); i++) {            
            Trabajador t = Trabajadores.get(i);
            TablaCons.getModel().setValueAt(t.getCC(), i, 0);
            TablaCons.getModel().setValueAt(t.getNombre(), i, 1);
            TablaCons.getModel().setValueAt(t.getApellidos(), i, 2);
            TablaCons.getModel().setValueAt(t.getFecha_nacimiento(), i, 3);
            TablaCons.getModel().setValueAt(t.getPersonas_a_Cargo(), i, 4);
            TablaCons.getModel().setValueAt(t.getEstado_Civil(), i, 5);
            TablaCons.getModel().setValueAt(t.getSexo(), i, 6);
            TablaCons.getModel().setValueAt(t.getEps(), i, 7);
            TablaCons.getModel().setValueAt(t.getAfp(), i, 8);
            TablaCons.getModel().setValueAt(t.getTipo_Desplazamiento(), i, 9);
            TablaCons.getModel().setValueAt(t.getTiempo_Desplazamiento(), i, 10);
            TablaCons.getModel().setValueAt(t.getEducacion(), i, 11);
            TablaCons.getModel().setValueAt(t.getCargo(), i, 12);
            TablaCons.getModel().setValueAt(t.getArea(), i, 13);
            TablaCons.getModel().setValueAt(t.getFecha_ingreso(), i, 14);
            for (int j = 15; j <t.getAtributos().length+15 ; j++) {
               TablaCons.getModel().setValueAt(t.getAtributos()[j-15], i, j);
            }
            
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Sex = new javax.swing.ButtonGroup();
        Educacion = new javax.swing.ButtonGroup();
        jScrollPane3 = new javax.swing.JScrollPane();
        Paneles = new javax.swing.JTabbedPane();
        tabIngresoTrabajador = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtPersonasACargo = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtCC = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        dateFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jPanel9 = new javax.swing.JPanel();
        btnDesplazamientoTrabajo = new javax.swing.JButton();
        cmbEstadoCivil = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Masculino = new javax.swing.JRadioButton();
        Femenino = new javax.swing.JRadioButton();
        cmbEps = new javax.swing.JComboBox();
        cmbAfp = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        DE1 = new javax.swing.JRadioButton();
        DE2 = new javax.swing.JRadioButton();
        DE3 = new javax.swing.JRadioButton();
        DE4 = new javax.swing.JRadioButton();
        DE5 = new javax.swing.JRadioButton();
        DE6 = new javax.swing.JRadioButton();
        DE7 = new javax.swing.JRadioButton();
        DE8 = new javax.swing.JRadioButton();
        DE9 = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtCargo = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        cmbArea = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        dateFechaIngreso = new com.toedter.calendar.JDateChooser();
        btnIngresar = new javax.swing.JButton();
        btnDatosAdicionales = new javax.swing.JButton();
        tabRetiroTrabajador = new javax.swing.JPanel();
        txtCC_Retiro = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar_Retiro = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblNombre_Retiro = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblApellido_Retiro = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblArea_Retiro = new javax.swing.JLabel();
        btnRetirar = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtAreaMotivoRetiro = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        tabEstadisticas = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cmbItem = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cmbParametro = new javax.swing.JComboBox();
        txtComparacion = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCantidadPersonas = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableTransporte = new javax.swing.JTable();
        tabConsulta = new javax.swing.JPanel();
        txtBuscarTrabajador = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnBusquedaAvanzada = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCons = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(183, 240, 240));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jScrollPane3.setBackground(new java.awt.Color(183, 240, 240));
        jScrollPane3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Paneles.setBackground(new java.awt.Color(183, 240, 240));
        Paneles.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Paneles.setInheritsPopupMenu(true);
        Paneles.setOpaque(true);
        Paneles.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PanelesFocusGained(evt);
            }
        });

        tabIngresoTrabajador.setBackground(new java.awt.Color(183, 240, 240));
        tabIngresoTrabajador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tabIngresoTrabajadorFocusGained(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "DATOS PERSONALES"));
        jPanel6.setOpaque(false);

        jLabel8.setText("C.C.");

        jLabel9.setText("NOMBRE (S)");

        jLabel10.setText("APELLIDOS");

        jLabel11.setText("PERSONAS A CARGO");

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidoKeyReleased(evt);
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        txtCC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCCKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCCKeyTyped(evt);
            }
        });

        jLabel16.setText("FECHA DE NACIMIENTO");

        dateFechaNacimiento.setBackground(new java.awt.Color(255, 255, 255));
        dateFechaNacimiento.setDateFormatString("yyy/MM/d");
        dateFechaNacimiento.setNextFocusableComponent(cmbEstadoCivil);

        jPanel9.setOpaque(false);

        btnDesplazamientoTrabajo.setText("Desplazamiento al trabajo");
        btnDesplazamientoTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesplazamientoTrabajoActionPerformed(evt);
            }
        });

        cmbEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SOLTERO", "CASADO", "UNION LIBRE", "DIVORSIADO", "VIUDO" }));

        jLabel15.setText("AFP");

        jLabel14.setText("EPS");

        jLabel13.setText("SEXO");

        jLabel12.setText("ESTADO CIVIL");

        Masculino.setSelected(true);
        Masculino.setText("M");
        Masculino.setOpaque(false);

        Femenino.setText("F");
        Femenino.setOpaque(false);

        cmbEps.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Coomeva" }));
        cmbEps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEpsActionPerformed(evt);
            }
        });

        cmbAfp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MiAFP" }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDesplazamientoTrabajo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbEps, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(Masculino)
                                        .addGap(18, 18, 18)
                                        .addComponent(Femenino))
                                    .addComponent(cmbEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbAfp, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cmbEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel13))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Masculino)
                            .addComponent(Femenino))))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(cmbEps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbAfp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addComponent(btnDesplazamientoTrabajo)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(dateFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPersonasACargo, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCC, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel8)
                                .addGap(6, 6, 6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPersonasACargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel16))
                            .addComponent(dateFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "DATOS EDUCACIÓN"));
        jPanel7.setOpaque(false);

        DE1.setText("PRIMARIA");
        DE1.setOpaque(false);
        DE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DE1ActionPerformed(evt);
            }
        });

        DE2.setText("SECUNDARIA");
        DE2.setOpaque(false);

        DE3.setText("TECNICO");
        DE3.setOpaque(false);

        DE4.setText("TECNOLOGO");
        DE4.setOpaque(false);

        DE5.setText("PREGRADO");
        DE5.setOpaque(false);

        DE6.setText("POSGRADO");
        DE6.setOpaque(false);

        DE7.setText("MAESTRIA");
        DE7.setOpaque(false);

        DE8.setText("DOCTORADO");
        DE8.setOpaque(false);

        DE9.setSelected(true);
        DE9.setText("NINGUNA");
        DE9.setOpaque(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DE2)
                    .addComponent(DE1)
                    .addComponent(DE3))
                .addGap(104, 104, 104)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DE5)
                            .addComponent(DE6))
                        .addGap(107, 107, 107)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DE8)
                            .addComponent(DE9)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(DE4)
                        .addGap(101, 101, 101)
                        .addComponent(DE7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DE1)
                    .addComponent(DE4)
                    .addComponent(DE7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DE2)
                    .addComponent(DE5)
                    .addComponent(DE8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DE3)
                    .addComponent(DE6)
                    .addComponent(DE9))
                .addGap(0, 25, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "DATOS LABORALES"));
        jPanel8.setOpaque(false);

        jLabel18.setText("CARGO");

        txtCargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCargoKeyReleased(evt);
            }
        });

        jLabel19.setText("AREA");

        cmbArea.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sistemas" }));

        jLabel17.setText("FECHA DE INGRESO");

        dateFechaIngreso.setDateFormatString("yyy/MM/d");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel17))
                .addGap(71, 71, 71)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCargo)
                    .addComponent(cmbArea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateFechaIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cmbArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(dateFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/OK.gif"))); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.setBorder(null);
        btnIngresar.setBorderPainted(false);
        btnIngresar.setContentAreaFilled(false);
        btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIngresar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIngresar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        btnDatosAdicionales.setText("<html>       <sub>D</sub>          <br>      <sub>A</sub>       <br>      <sub>T</sub>       <br>      <sub>O</sub>       <br>      <sub>S</sub>       <br>      <sub> </sub>       <br>      <sub>A</sub>       <br>      <sub>D</sub>       <br>      <sub>C</sub>       <br>      <sub>I</sub><br><sub>O</sub>       <br>      <sub>N</sub>       <br>      <sub>A</sub>       <br>      <sub>L</sub>       <br>      <sub>E</sub>       <br>      <sub>S</sub>      <sub> </sub>     </html>");
        btnDatosAdicionales.setToolTipText("");
        btnDatosAdicionales.setActionCommand("<html>       <sub>D</sub>          <br>     \n <sub>A</sub>       <br>     \n <sub>T</sub>       <br>    \n  <sub>O</sub>       <br>     \n <sub>S</sub>       <br>    \n  <sub> </sub>       <br>      \n<sub>A</sub>       <br>      \n<sub>D</sub>       <br>      \n<sub>C</sub>       <br>      \n<sub>    I  </sub>       <br>      \n<sub>O</sub>       <br>      <sub>N</sub>       <br>      <sub>A</sub>       <br>      <sub>L</sub>       <br>      <sub>E</sub>       <br>      <sub>S</sub>      <sub> </sub>     </html>");
        btnDatosAdicionales.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDatosAdicionales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatosAdicionalesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabIngresoTrabajadorLayout = new javax.swing.GroupLayout(tabIngresoTrabajador);
        tabIngresoTrabajador.setLayout(tabIngresoTrabajadorLayout);
        tabIngresoTrabajadorLayout.setHorizontalGroup(
            tabIngresoTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabIngresoTrabajadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabIngresoTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(tabIngresoTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDatosAdicionales, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIngresar))
                .addGap(0, 2375, Short.MAX_VALUE))
        );
        tabIngresoTrabajadorLayout.setVerticalGroup(
            tabIngresoTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabIngresoTrabajadorLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(tabIngresoTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tabIngresoTrabajadorLayout.createSequentialGroup()
                        .addComponent(btnDatosAdicionales, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnIngresar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tabIngresoTrabajadorLayout.createSequentialGroup()
                        .addGap(0, 7, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(210, 210, 210))))
        );

        Paneles.addTab("   Ingreso de Trabajador", tabIngresoTrabajador);

        tabRetiroTrabajador.setBackground(new java.awt.Color(183, 240, 240));

        jLabel1.setText("Documento de identidad N°");

        btnBuscar_Retiro.setText("Buscar");
        btnBuscar_Retiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar_RetiroActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Trabajador"));
        jPanel5.setOpaque(false);

        jLabel2.setText("Nombre:");

        lblNombre_Retiro.setText("DIGITE EL DOCUMENTO DE IDENTIDAD");

        jLabel4.setText("Apellidos: ");

        lblApellido_Retiro.setText("DIGITE EL DOCUMENTO DE IDENTIDAD");

        jLabel6.setText("Area:");

        lblArea_Retiro.setText("DIGITE EL DOCUMENTO DE IDENTIDAD");

        btnRetirar.setText("Retirar");
        btnRetirar.setEnabled(false);
        btnRetirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetirarActionPerformed(evt);
            }
        });

        txtAreaMotivoRetiro.setColumns(20);
        txtAreaMotivoRetiro.setRows(5);
        txtAreaMotivoRetiro.setEnabled(false);
        jScrollPane5.setViewportView(txtAreaMotivoRetiro);

        jLabel3.setText("Motivo del Retiro:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRetirar))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGap(144, 144, 144)
                                    .addComponent(lblArea_Retiro, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel6)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblNombre_Retiro, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                                .addComponent(lblApellido_Retiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(lblNombre_Retiro)
                .addGap(4, 4, 4)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(23, 23, 23)
                        .addComponent(lblApellido_Retiro)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel6)
                        .addGap(26, 26, 26)
                        .addComponent(lblArea_Retiro))
                    .addComponent(jScrollPane5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRetirar)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout tabRetiroTrabajadorLayout = new javax.swing.GroupLayout(tabRetiroTrabajador);
        tabRetiroTrabajador.setLayout(tabRetiroTrabajadorLayout);
        tabRetiroTrabajadorLayout.setHorizontalGroup(
            tabRetiroTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabRetiroTrabajadorLayout.createSequentialGroup()
                .addGroup(tabRetiroTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabRetiroTrabajadorLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCC_Retiro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar_Retiro))
                    .addGroup(tabRetiroTrabajadorLayout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(114, 114, 114))
        );
        tabRetiroTrabajadorLayout.setVerticalGroup(
            tabRetiroTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabRetiroTrabajadorLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(tabRetiroTrabajadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCC_Retiro, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar_Retiro))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        Paneles.addTab("   Retiro de trabajador", tabRetiroTrabajador);

        tabEstadisticas.setBackground(new java.awt.Color(183, 240, 240));

        jPanel1.setBackground(new java.awt.Color(183, 240, 240));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cantidad de Personas"));

        cmbItem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nombres", "Apellidos", "Fecha_Nacimiento", "Personas_A_Cargo", "Estado_Civil", "Sexo", "Eps", "Afp", "Tipo_De_Transporte", "Tiempo_De_Desplazamiento", "Educacion", "Cargo", "Area", "Fecha_De_Ingreso" }));

        jLabel20.setText("Seleccione un Item");

        jLabel21.setText("Seleccione un parametro de comparación");

        cmbParametro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ninguno", "Igual a", "Mayor a", "Menor a", "Mayor igual a", "Menor igual a" }));
        cmbParametro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbParametroItemStateChanged(evt);
            }
        });

        txtComparacion.setEnabled(false);

        tableCantidadPersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Item", "Cantidad"
            }
        ));
        jScrollPane2.setViewportView(tableCantidadPersonas);

        jButton7.setText("OK");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(cmbItem, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbParametro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtComparacion)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbParametro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtComparacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tableTransporte.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tableTransporte);

        javax.swing.GroupLayout tabEstadisticasLayout = new javax.swing.GroupLayout(tabEstadisticas);
        tabEstadisticas.setLayout(tabEstadisticasLayout);
        tabEstadisticasLayout.setHorizontalGroup(
            tabEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabEstadisticasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(tabEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addGap(0, 0, 0))
        );
        tabEstadisticasLayout.setVerticalGroup(
            tabEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabEstadisticasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(206, Short.MAX_VALUE))
        );

        Paneles.addTab("   Estadisticas", tabEstadisticas);

        tabConsulta.setBackground(new java.awt.Color(183, 240, 240));

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Editar");
        jButton4.setEnabled(false);

        btnBusquedaAvanzada.setText("Búsqueda Avanzada");
        btnBusquedaAvanzada.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btnBusquedaAvanzadaItemStateChanged(evt);
            }
        });
        btnBusquedaAvanzada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaAvanzadaActionPerformed(evt);
            }
        });

        TablaCons.setModel(new javax.swing.table.DefaultTableModel(
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
        TablaCons.setMinimumSize(new java.awt.Dimension(3000, 64));
        jScrollPane1.setViewportView(TablaCons);

        jLabel5.setText("Cédula de Ciudadania #");

        javax.swing.GroupLayout tabConsultaLayout = new javax.swing.GroupLayout(tabConsulta);
        tabConsulta.setLayout(tabConsultaLayout);
        tabConsultaLayout.setHorizontalGroup(
            tabConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabConsultaLayout.createSequentialGroup()
                .addGroup(tabConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabConsultaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addGap(2, 2, 2)
                        .addComponent(txtBuscarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(btnBusquedaAvanzada)
                        .addGap(142, 142, 142)
                        .addComponent(jButton4))
                    .addGroup(tabConsultaLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 2585, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(671, Short.MAX_VALUE))
        );
        tabConsultaLayout.setVerticalGroup(
            tabConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarTrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(btnBusquedaAvanzada)
                    .addComponent(jLabel5))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
        );

        Paneles.addTab("   Consulta", tabConsulta);

        jScrollPane3.setViewportView(Paneles);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1534, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /** 
     * Funcion que se ejecuta al dar click sobre el boton buscar que esta en la pestaña
     * de retiro de demografia. Con esta funcion se busca el numero de cedula en la BD
     * se muestran los datos del trabajador para corroborar que si sea el correcto
     * y se habilita el boton de retirar y el TextArea para que escriba el motivo
     * por el cual se está haciendo el retiro
     */
    private void btnBuscar_RetiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar_RetiroActionPerformed
        int cc =0; //Variable donde se va a almacenar localmente el numero de cc del trabajador a retirar
        
        try{
            cc = Integer.parseInt(txtCC_Retiro.getText());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Debe ingresar un numero de cedula valido\n"
                    + "El numero de cedula no debe llevar puntos. Ejemplo\n"
                    + "Cedula invalida: 1.088.200.424 \n"
                    + "Cedula Válida: 1088200424","Numero de cedula invalido",JOptionPane.ERROR_MESSAGE);
            
        }
        ModeloTablaEmpleado modelo;
        modelo = this.baseDeDatos.getTrabajador(cc);
        
        if(modelo!=null){
            //Se asigna la cedula a una variable privada de clase para poder obtener el
            //numero de la cedula en el momento que haga click en el boton de retiro
            cc_A_Retirar = cc;
            //Se muestran algunos datos del trabajador a retirar para que la persona
            //que esta llevando a cabo el procedimiento pueda verificar que se trata
            //del trabajador correcto
            lblNombre_Retiro.setText(modelo.getValueAt(0, 1).toString());
            lblApellido_Retiro.setText(modelo.getValueAt(0, 2).toString());
            lblArea_Retiro.setText(modelo.getArea());
            //Se activa el text Area para que pueda escribir el motivo por el cual
            //se va a retirar el trabajador
            txtAreaMotivoRetiro.setEnabled(true);
            //Se activa el boton de Retiro para que pueda llevar a cabo el retiro
            //del trabajador
            btnRetirar.setEnabled(true);
            this.idIngresoARetirar = modelo.getIdIngreso(); //Se le asigna el id de ingreso para poder relacionar el retiro con su ultimo ingreso.
        }else{
            this.idIngresoARetirar = ""; //Se le asigna vacio para asegurarse de que no valla a retirar a alguien que no es.
            lblNombre_Retiro.setText("DIGITE EL DOCUMENTO DE IDENTIDAD");
            lblApellido_Retiro.setText("DIGITE EL DOCUMENTO DE IDENTIDAD");
            lblArea_Retiro.setText("DIGITE EL DOCUMENTO DE IDENTIDAD");
            txtAreaMotivoRetiro.setText("");
            txtAreaMotivoRetiro.setEnabled(false);
            JOptionPane.showMessageDialog(null, "No se encontro un trabajador con el numero de cedula \nCC: "+cc, "Error al Buscar Trabajador",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnBuscar_RetiroActionPerformed
/** 
 * Funcion que se ejecuta al hacer click sobre el boton Desplazamiento al trabajo
 * Lo que hace es cojer la variable de clase donde esta almacenada dicha ventana
 * y la pone visible, en caso que cuando cierre la ventana haya hecho click sobre
 * el boton de aceptar, se cargan los datos a las variables de clase correspondientes
 */
    private void btnDesplazamientoTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesplazamientoTrabajoActionPerformed

        despl.setVisible(true);
        if(despl.isAccepted()){
            this.TiempoDesplazamiento = despl.getTiempo();
            this.TipoDeTransporte = despl.getTipo();
        }
    }//GEN-LAST:event_btnDesplazamientoTrabajoActionPerformed

    private void cmbEpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEpsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEpsActionPerformed

    private void btnBusquedaAvanzadaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_btnBusquedaAvanzadaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBusquedaAvanzadaItemStateChanged

    private void btnBusquedaAvanzadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaAvanzadaActionPerformed
        filtroAvanzado.setLocationRelativeTo(null);
        filtroAvanzado.setVisible(true);
    }//GEN-LAST:event_btnBusquedaAvanzadaActionPerformed

    private void PanelesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PanelesFocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_PanelesFocusGained

    private void tabIngresoTrabajadorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabIngresoTrabajadorFocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tabIngresoTrabajadorFocusGained

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        if(Validar()){
            int dia =1,mes=1,anio=1900;
            ausentismo.Trabajador trabajador = new ausentismo.Trabajador();

            trabajador.setCC(txtCC.getText());
            trabajador.setNombre(txtNombre.getText());
            trabajador.setApellidos(txtApellido.getText());

            //Fecha de Nacimiento---------------------------------------------
            dia = dateFechaNacimiento.getCalendar().get(Calendar.DAY_OF_MONTH);
            mes = dateFechaNacimiento.getCalendar().get(Calendar.MONTH)+1;
            anio = dateFechaNacimiento.getCalendar().get(Calendar.YEAR);
            trabajador.setFecha_nacimiento(new ausentismo.Fecha(dia,mes,anio));
            //------------------------------------

            trabajador.setPersonas_a_Cargo(txtPersonasACargo.getText());
            trabajador.setEstado_Civil(cmbEstadoCivil.getSelectedItem().toString());
            trabajador.setSexo(getSexo());
            trabajador.setEps(cmbEps.getSelectedItem().toString());
            trabajador.setAfp(cmbAfp.getSelectedItem().toString());        
            trabajador.setTipo_Desplazamiento(TipoDeTransporte);
            trabajador.setTiempo_Desplazamiento(TiempoDesplazamiento);
            trabajador.setEducacion(getEducacion());
            trabajador.setCargo(txtCargo.getText());
            trabajador.setArea(""+(cmbArea.getSelectedIndex()+1));

            //Fecha de ingreso------------------------------------------
            dia = dateFechaIngreso.getCalendar().get(Calendar.DAY_OF_MONTH);
            mes = dateFechaIngreso.getCalendar().get(Calendar.MONTH)+1;
            anio = dateFechaIngreso.getCalendar().get(Calendar.YEAR);
            trabajador.setFecha_ingreso(new ausentismo.Fecha(dia,mes,anio));
            //-----------------------------------------------------------------
            trabajador.setAtributos(valorCamposAdicionales); 
            if(trabajadorRetirado){
                this.baseDeDatos.reingresoTrabajador(trabajador);
            }else{
                this.baseDeDatos.RegistrarTrabajador(trabajador);
            }
            limpiarCampos();
            DesactivarControles();
            JOptionPane.showMessageDialog(null, "Trabajador ingresado correctamente.", "Ingreso Correcto", JOptionPane.INFORMATION_MESSAGE);
            int opcion = JOptionPane.showConfirmDialog(null, "Desea registrar los examenes médicos de ingreso?");
            if(opcion==JOptionPane.OK_OPTION){ //Cuando ingresa un nuevo trabajador se deben ingresar sus exámenes médicos iniciales
                ExamenesIniciales ex = new ExamenesIniciales(null,true);
                ex.setCC(trabajador.getCC());                
                ex.setLocationRelativeTo(null);
                ex.setVisible(true);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor llene todos los datos, o verifique que son correctos.","Error!!!",JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_btnIngresarActionPerformed

    //Funcion para poner en blanco todo el formulario de registro de trabajador
    private void limpiarCampos(){
        //Campos  text
        txtNombre.setText("");
        txtApellido.setText("");
        txtCC.setText("");
        txtCargo.setText("");
        txtPersonasACargo.setText("");
        //Listas Desplegables
        cmbAfp.setSelectedIndex(0);
        cmbArea.setSelectedIndex(0);
        cmbEps.setSelectedIndex(0);
        cmbEstadoCivil.setSelectedIndex(0);
        //JDateChooser
        dateFechaIngreso.setDate(null);
        dateFechaNacimiento.setDate(null);
        //Datos adicionales 
        datosAdicionales = new DatosAdicionalesDemografia(null, true);
        datosAdicionales.setCampos(BaseDatos.Demografia_BD.getNuevosCampos());
    }
    
    private void btnDatosAdicionalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatosAdicionalesActionPerformed
        datosAdicionales.setLocationRelativeTo(null);
        datosAdicionales.setVisible(true);
        if(datosAdicionales.isAccepted()){
            valorCamposAdicionales = datosAdicionales.getValorCampos();
        }
    }//GEN-LAST:event_btnDatosAdicionalesActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
         DefaultTableModel modelo = null;
         String condicion = null;
         String Item = cmbItem.getSelectedItem().toString();
         String comparacion = txtComparacion.getText();
         if(Item.contains("Fecha")){
             comparacion = "DATE('"+comparacion+"')";
         }
         switch (cmbParametro.getSelectedIndex()) {
             case 0:
                 break;
             case 1: //En caso de que haga una comparacion con el operador igual (=)
                condicion = Item.toString()+"="+comparacion;
                
                break;
            case 2: //En caso de que haga una comparacion con el operador Mayor a (>)
                condicion = Item+">"+comparacion;
                break;
            case 3: //En caso de que haga una comparacion con el operador Menor a (<)
                condicion = Item+"<"+comparacion;
                break;
            case 4: //En caso de que haga una comparacion con el operador Mayor igual a (>=)
                condicion = Item.toString()+">="+comparacion;
                break;
            case 5: //En caso de que haga una comparacion con el operador Menor igual a (<=)
                condicion = Item+"<="+comparacion;
                break;
            default:
                JOptionPane.showMessageDialog(null,"Error!!! No se ha podido Realizar la consulta de la estadistica" , "Error", JOptionPane.ERROR_MESSAGE);
        }
         //En caso de que la condicion sea null entonces se deja como si el indice fuera 0:
         modelo = (condicion != null)? baseDeDatos.consultaCondicion(Item, condicion) : baseDeDatos.cantidadDe(Item.toString());;
         tableCantidadPersonas.setModel(modelo);
         
         
         
    }//GEN-LAST:event_jButton7ActionPerformed

    private void cmbParametroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbParametroItemStateChanged
        if(cmbParametro.getSelectedIndex()>0){
            txtComparacion.setEnabled(true);
        }else{
            txtComparacion.setEnabled(false);
        }
    }//GEN-LAST:event_cmbParametroItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int cc = Integer.parseInt(txtBuscarTrabajador.getText());
        TablaCons.setModel(this.baseDeDatos.getTrabajador(cc));
    }//GEN-LAST:event_jButton3ActionPerformed

    //Boton Retirar Trabajador
    private void btnRetirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetirarActionPerformed
       //Se pregunta si el campo de motivo no esta vacio
        boolean motivo_OK = !txtAreaMotivoRetiro.getText().equals(""); 
       if(motivo_OK){
           this.baseDeDatos.RetirarTrabajador(this.cc_A_Retirar, txtAreaMotivoRetiro.getText(),this.idIngresoARetirar);
           TablaCons.setModel(new ModeloTablaEmpleado());
           Trabajadores = baseDeDatos.getTrabajadores();
           mostrarTrabajadores();
           JOptionPane.showMessageDialog(null, "Se ha retirado Correctamente el Trabajador", "Trabajador Retirado", JOptionPane.INFORMATION_MESSAGE);
       }else{
           JOptionPane.showMessageDialog(null, "Debe ingresar un motivo para retirar el trabajador", "Error", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_btnRetirarActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        aMayuscula(txtNombre);
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyReleased
        aMayuscula(txtApellido);
    }//GEN-LAST:event_txtApellidoKeyReleased

    private void txtCargoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCargoKeyReleased
        aMayuscula(txtCargo);
    }//GEN-LAST:event_txtCargoKeyReleased

    private void txtCCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCCKeyTyped
        
    }//GEN-LAST:event_txtCCKeyTyped

    private void txtCCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCCKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){ //Si se preciona la tecla enter se entra a validar la cc
            if(Validador.Vnumero(txtCC.getText())){ //Se verifica que la cc sea numerica
                int cc = Integer.parseInt(txtCC.getText());
                Object[] tr= Validador.ComprobarTrabajador(cc); 
                if(tr!=null){ //Con esto se constata que el trabajador efectivamente exista en la BD
                    if(tr[1].equals("Retirado")){
                        int op = JOptionPane.showConfirmDialog(null, "El trabajador se encuentra retirado. \n¿Desea Reingresarlo?");
                        if(op==JOptionPane.YES_OPTION){ //Si el usuario desea reingresar el trabajador 
                            ausentismo.Trabajador trabajador = (ausentismo.Trabajador) tr[0];
                            FormReingresar(trabajador);
                            trabajadorRetirado = true;                           
                        }
                    }else{ //Si no está retirado es porque está activo
                        String msnError = "El trabajador ya existe. "
                                + "\nEl trabajador se encuentra laborando en la empresa actualmente. "
                                + "\nNo puede ingresar un trabajador que se encuentre actualmente activo.";
                        JOptionPane.showMessageDialog(null, msnError, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{ //En caso de que el numero de cc no coincida con ninguna en la bd
                    ActivarControles();                    
                }
            }else{//En caso de que digiten mal una cc (No numerica)
                JOptionPane.showMessageDialog(null,"El numero de cédula que digito es incorrecto o no es un número", "Error",  JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtCCKeyPressed

    private void DE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DE1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DE1ActionPerformed
private String getSexo(){
    if(this.Masculino.isSelected()){
        return "M";
    }else{
        return "F";
    }
    
}
private void FormReingresar(ausentismo.Trabajador T){
    ActivarControles();
    txtNombre.setText(T.getNombre());
    txtApellido.setText(T.getApellidos());
    
}
private void ActivarControles(){
    txtNombre.setEnabled(true);
    txtApellido.setEnabled(true);
    txtCargo.setEnabled(true);
    txtPersonasACargo.setEnabled(true);
    dateFechaNacimiento.setEnabled(true);
    dateFechaIngreso.setEnabled(true);
    cmbEstadoCivil.setEnabled(true);
    cmbEps.setEnabled(true);
    cmbArea.setEnabled(true);
    cmbAfp.setEnabled(true);
    btnDesplazamientoTrabajo.setEnabled(true);
    DE1.setEnabled(true);
    DE2.setEnabled(true);
    DE3.setEnabled(true);
    DE4.setEnabled(true);
    DE5.setEnabled(true);
    DE6.setEnabled(true);
    DE7.setEnabled(true);
    DE8.setEnabled(true);
    DE9.setEnabled(true);
    Masculino.setEnabled(true);
    Femenino.setEnabled(true);
    btnDatosAdicionales.setEnabled(true);
    btnIngresar.setEnabled(true);
    
}
private void DesactivarControles(){
    txtNombre.setEnabled(false);
    txtApellido.setEnabled(false);
    txtCargo.setEnabled(false);
    txtPersonasACargo.setEnabled(false);
    dateFechaNacimiento.setEnabled(false);
    dateFechaIngreso.setEnabled(false);
    cmbEstadoCivil.setEnabled(false);
    cmbEps.setEnabled(false);
    cmbArea.setEnabled(false);
    cmbAfp.setEnabled(false);
    btnDesplazamientoTrabajo.setEnabled(false);
    DE1.setEnabled(false);
    DE2.setEnabled(false);
    DE3.setEnabled(false);
    DE4.setEnabled(false);
    DE5.setEnabled(false);
    DE6.setEnabled(false);
    DE7.setEnabled(false);
    DE8.setEnabled(false);
    DE9.setEnabled(false);
    Masculino.setEnabled(false);
    Femenino.setEnabled(false);
    btnDatosAdicionales.setEnabled(false);
    btnIngresar.setEnabled(false);
}

private String getEducacion(){
    if(DE1.isSelected()){
        return "PRIMARIA";
    }else if(DE2.isSelected()){
        return "SECUNDARIA";
    }else if(DE3.isSelected()){
        return "TECNICO";
    }else if(DE4.isSelected()){
        return "TECNOLOGO";
    }else if(DE5.isSelected()){
        return "PREGRADO";
    }else if(DE6.isSelected()){
        return "POSGRADO";
    }else if(DE7.isSelected()){
        return "MAESTRIA";
    }else if(DE8.isSelected()){
        return "DOCTORADO";
    }else{
        return "NINGUNA";
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton DE1;
    private javax.swing.JRadioButton DE2;
    private javax.swing.JRadioButton DE3;
    private javax.swing.JRadioButton DE4;
    private javax.swing.JRadioButton DE5;
    private javax.swing.JRadioButton DE6;
    private javax.swing.JRadioButton DE7;
    private javax.swing.JRadioButton DE8;
    private javax.swing.JRadioButton DE9;
    private javax.swing.ButtonGroup Educacion;
    private javax.swing.JRadioButton Femenino;
    private javax.swing.JRadioButton Masculino;
    private javax.swing.JTabbedPane Paneles;
    private javax.swing.ButtonGroup Sex;
    private javax.swing.JTable TablaCons;
    private javax.swing.JButton btnBuscar_Retiro;
    private javax.swing.JButton btnBusquedaAvanzada;
    private javax.swing.JButton btnDatosAdicionales;
    private javax.swing.JButton btnDesplazamientoTrabajo;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnRetirar;
    private javax.swing.JComboBox cmbAfp;
    private javax.swing.JComboBox cmbArea;
    private javax.swing.JComboBox cmbEps;
    private javax.swing.JComboBox cmbEstadoCivil;
    private javax.swing.JComboBox cmbItem;
    private javax.swing.JComboBox cmbParametro;
    private com.toedter.calendar.JDateChooser dateFechaIngreso;
    private com.toedter.calendar.JDateChooser dateFechaNacimiento;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblApellido_Retiro;
    private javax.swing.JLabel lblArea_Retiro;
    private javax.swing.JLabel lblNombre_Retiro;
    private javax.swing.JPanel tabConsulta;
    private javax.swing.JPanel tabEstadisticas;
    private javax.swing.JPanel tabIngresoTrabajador;
    private javax.swing.JPanel tabRetiroTrabajador;
    private javax.swing.JTable tableCantidadPersonas;
    private javax.swing.JTable tableTransporte;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextArea txtAreaMotivoRetiro;
    private javax.swing.JTextField txtBuscarTrabajador;
    private javax.swing.JTextField txtCC;
    private javax.swing.JTextField txtCC_Retiro;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtComparacion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPersonasACargo;
    // End of variables declaration//GEN-END:variables

    private void ocultarBarraTitulo(){ 
        ausentismo.GraphicsAdmin.ocultarBarraTitulo(this);
    }
    
    //Funcion para poner los iconos correspondientes a cada pestaña de demografia
    private void ponerIconos(){
        
        Paneles.setIconAt(0, new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Boss.gif")));
        Paneles.setIconAt(1, new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Retirar.gif")));
        Paneles.setIconAt(2, new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Barra.png")));
        Paneles.setIconAt(3, new javax.swing.ImageIcon(getClass().getResource("/Imagenes/view.gif")));
    }
}
