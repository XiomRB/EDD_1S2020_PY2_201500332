package vista;

import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import logica.AVLCategoria;
import logica.LecturaJson;
import logica.NodoL;

public class JUsuario extends javax.swing.JFrame {
    public static NodoL usuario;
    
    Operaciones ops = new Operaciones();
    
    
    public JUsuario() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        miBiblioteca = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtxtbtit = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtxtbisbn = new javax.swing.JTextField();
        jbtnbbuscar = new javax.swing.JButton();
        busqueda = new javax.swing.JScrollPane();
        panelbusqueda = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jtxtbvtit = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jtxtbvisbn = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jbtnbvbuscar = new javax.swing.JButton();
        panelbvbusqueda = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        carga = new javax.swing.JMenu();
        cargalibros = new javax.swing.JMenuItem();
        operacionesLibro = new javax.swing.JMenu();
        agregarLibro = new javax.swing.JMenuItem();
        reportes = new javax.swing.JMenu();
        reportarCat = new javax.swing.JMenuItem();
        reportarLibros = new javax.swing.JMenuItem();
        reporteUsuarios = new javax.swing.JMenuItem();
        operacionesUsuario = new javax.swing.JMenu();
        logOut = new javax.swing.JMenuItem();
        actualizarDatos = new javax.swing.JMenuItem();
        eliminarCuenta = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Titulo:");

        jLabel4.setText("ISBN:");

        jbtnbbuscar.setText("Buscar");

        panelbusqueda.setLayout(new java.awt.GridLayout(10, 0));
        busqueda.setViewportView(panelbusqueda);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxtbtit, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxtbisbn, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                        .addComponent(jbtnbbuscar)
                        .addGap(81, 81, 81))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jbtnbbuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtxtbtit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jtxtbisbn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addGap(31, 31, 31)
                .addComponent(busqueda)
                .addContainerGap())
        );

        miBiblioteca.addTab("Busqueda", jPanel2);

        jTabbedPane1.addTab("Mi biblioteca", miBiblioteca);
        miBiblioteca.getAccessibleContext().setAccessibleName("categorias");

        jLabel1.setText("Titulo:");

        jLabel2.setText("ISBN:");

        jbtnbvbuscar.setText("Buscar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelbvbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jtxtbvtit, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxtbvisbn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jbtnbvbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbtnbvbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtxtbvisbn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtxtbvtit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(26, 26, 26)
                .addComponent(panelbvbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Busqueda", jPanel3);

        jTabbedPane1.addTab("Biblioteca Virtual", jTabbedPane3);
        jTabbedPane3.getAccessibleContext().setAccessibleName("categoriasvirtual");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("biblioteca");

        carga.setText("Carga Masiva");

        cargalibros.setText("Libros");
        cargalibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargalibrosActionPerformed(evt);
            }
        });
        carga.add(cargalibros);

        jMenuBar1.add(carga);

        operacionesLibro.setText("Operaciones Libro");

        agregarLibro.setText("Agregar");
        operacionesLibro.add(agregarLibro);

        jMenuBar1.add(operacionesLibro);

        reportes.setText("Reportes");

        reportarCat.setText("Categorias");
        reportes.add(reportarCat);

        reportarLibros.setText("Libros");
        reportes.add(reportarLibros);

        reporteUsuarios.setText("Usuarios");
        reportes.add(reporteUsuarios);

        jMenuBar1.add(reportes);

        operacionesUsuario.setText("Operaciones Usuario");

        logOut.setText("Cerrar Sesion");
        logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutActionPerformed(evt);
            }
        });
        operacionesUsuario.add(logOut);

        actualizarDatos.setText("Actualizar Datos");
        actualizarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarDatosActionPerformed(evt);
            }
        });
        operacionesUsuario.add(actualizarDatos);

        eliminarCuenta.setText("Eliminar Cuenta");
        operacionesUsuario.add(eliminarCuenta);

        jMenuBar1.add(operacionesUsuario);

        setJMenuBar(jMenuBar1);

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
    }// </editor-fold>//GEN-END:initComponents

    private void cargalibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargalibrosActionPerformed
        JFileChooser archi = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos JSON", "json");
        archi.setFileFilter(filtro);
        if(archi.showDialog(null, "Cargar Libros") == JFileChooser.APPROVE_OPTION){
            File archivo = archi.getSelectedFile();
            ops.cargarLibros(archivo, usuario.getCarnet());
        }
        for (int i = 1; i < miBiblioteca.getTabCount(); i++) miBiblioteca.removeTabAt(i);
        crearCategoriasUsuario();
    }//GEN-LAST:event_cargalibrosActionPerformed

    private void logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutActionPerformed
        usuario = null;
        this.setVisible(false);
        JPrincipal inicio = new JPrincipal();
    }//GEN-LAST:event_logOutActionPerformed

    private void actualizarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarDatosActionPerformed
       ActualizarDatos actualiza = new ActualizarDatos(this, true);
       actualiza.setVisible(true);
    }//GEN-LAST:event_actualizarDatosActionPerformed

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
            java.util.logging.Logger.getLogger(JUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JUsuario().setVisible(true);
            }
        });
    }

    public  void setUsuario(NodoL usuario){
        this.usuario = usuario;
    }
    
    public String getUsuario(){
        return this.usuario.getNombre() + " " + this.usuario.getApellido();
    }
    
    private void crearCategoriasUsuario(){
        for (int i = 0; i < Operaciones.categoriasUsuario.size(); i++) {
            JPanel p = new JPanel(new GridLayout(5, 0));
            ArrayList<JButton> botones = crearLibrosUsuario(i);
            System.out.println(botones.size());
            for (int j = 0; j < botones.size(); j++) p.add(botones.get(j));
            miBiblioteca.add(Operaciones.categoriasUsuario.get(i).getCategoria(), p);
        }
        miBiblioteca.updateUI();
    }
    
    private ArrayList<JButton> crearLibrosUsuario(int cat){
        ArrayList<JButton> libros =  new ArrayList<>();
        for (int i = 0; i < Operaciones.categoriasUsuario.get(cat).getLibros().size(); i++) {
            Book libronuevo = Operaciones.categoriasUsuario.get(cat).getLibro(i);
            JButton btn = new JButton(libronuevo.getIsbn() + "     " + libronuevo.getTitulo());
            libros.add(btn);
        }
        return libros;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem actualizarDatos;
    private javax.swing.JMenuItem agregarLibro;
    private javax.swing.JScrollPane busqueda;
    private javax.swing.JMenu carga;
    private javax.swing.JMenuItem cargalibros;
    private javax.swing.JMenuItem eliminarCuenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JButton jbtnbbuscar;
    private javax.swing.JButton jbtnbvbuscar;
    private javax.swing.JTextField jtxtbisbn;
    private javax.swing.JTextField jtxtbtit;
    private javax.swing.JTextField jtxtbvisbn;
    private javax.swing.JTextField jtxtbvtit;
    private javax.swing.JMenuItem logOut;
    private javax.swing.JTabbedPane miBiblioteca;
    private javax.swing.JMenu operacionesLibro;
    private javax.swing.JMenu operacionesUsuario;
    private javax.swing.JPanel panelbusqueda;
    private javax.swing.JScrollPane panelbvbusqueda;
    private javax.swing.JMenuItem reportarCat;
    private javax.swing.JMenuItem reportarLibros;
    private javax.swing.JMenuItem reporteUsuarios;
    private javax.swing.JMenu reportes;
    // End of variables declaration//GEN-END:variables
}
