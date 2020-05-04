package vista;

import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JPrincipal extends javax.swing.JFrame {
    public static JUsuario usuario = new JUsuario();
    
    public JPrincipal() {
        initComponents();
        this.setTitle("Biblioteca Virtual");
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxtUsuario = new javax.swing.JTextField();
        jbtnIngreso = new javax.swing.JButton();
        jbtnCrearCuenta = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jtxtpass = new javax.swing.JPasswordField();
        jbtncarga = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Biblioteca Virtual");

        jLabel2.setLabelFor(jtxtUsuario);
        jLabel2.setText("Usuario:");

        jLabel3.setText("Contraseña:");

        jbtnIngreso.setText("Ingresar");
        jbtnIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIngresoActionPerformed(evt);
            }
        });

        jbtnCrearCuenta.setText("Crear Cuenta");
        jbtnCrearCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCrearCuentaActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/inicio.png"))); // NOI18N

        jbtncarga.setText("Cargar Usuarios");
        jbtncarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtncargaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtxtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                            .addComponent(jtxtpass)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(137, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jbtncarga)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jbtnIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jbtnCrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtxtpass, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnCrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtncarga, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
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
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIngresoActionPerformed
        String msj = Operaciones.usuarios.loguear(Integer.parseInt(this.jtxtUsuario.getText()), this.jtxtpass.getText());
        if(msj.equals("")){
            JUsuario.usuario = Operaciones.usuarios.buscar(Integer.parseInt(jtxtUsuario.getText()));
            usuario.setTitle("Biblioteca de " + usuario.getUsuario());
            usuario.ops.llenarLibrosUsuario(JUsuario.usuario.getCarnet());
            usuario.crearCategoriasUsuario();
            usuario.crearCatBiblioteca();
            this.setVisible(false);
            usuario.setVisible(true);
        }else JOptionPane.showMessageDialog(null, msj);
        this.jtxtUsuario.setText("");
        this.jtxtpass.setText("");
    }//GEN-LAST:event_jbtnIngresoActionPerformed

    private void jbtnCrearCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCrearCuentaActionPerformed
        this.setVisible(false);
        JCrearUsuario crea = new JCrearUsuario();
        crea.setVisible(true);
    }//GEN-LAST:event_jbtnCrearCuentaActionPerformed

    private void jbtncargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtncargaActionPerformed
        JFileChooser archi = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos JSON", "json");
        archi.setFileFilter(filtro);
        if(archi.showDialog(null, "Cargar Usuarios") == JFileChooser.APPROVE_OPTION){
            File archivo = archi.getSelectedFile();
            usuario.ops.cargarUsuarios(archivo);
        }
    }//GEN-LAST:event_jbtncargaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtnCrearCuenta;
    private javax.swing.JButton jbtnIngreso;
    private javax.swing.JButton jbtncarga;
    private javax.swing.JTextField jtxtUsuario;
    private javax.swing.JPasswordField jtxtpass;
    // End of variables declaration//GEN-END:variables
}