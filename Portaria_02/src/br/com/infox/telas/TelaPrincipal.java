
package br.com.infox.telas;

import br.com.infox.dal.ModuloConexao;
import java.sql.*;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;






public class TelaPrincipal extends javax.swing.JFrame {
          
    Connection conecxao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
   
    public TelaPrincipal() {
        initComponents();
            conecxao = ModuloConexao.conector();
    }
  
 

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Desktop = new javax.swing.JDesktopPane();
        lblUsuario = new javax.swing.JLabel();
        lblPerfil = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        menCad = new javax.swing.JMenu();
        menCadCli = new javax.swing.JMenuItem();
        menCadOS = new javax.swing.JMenuItem();
        menCadUsu = new javax.swing.JMenuItem();
        menAju = new javax.swing.JMenu();
        menAjuSob = new javax.swing.JMenuItem();
        MenReles = new javax.swing.JMenu();
        MenRelES = new javax.swing.JMenuItem();
        MenRelES1 = new javax.swing.JMenuItem();
        menOp = new javax.swing.JMenu();
        menOpSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MK-Controle de Chaves");

        Desktop.setPreferredSize(new java.awt.Dimension(599, 561));

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblUsuario.setText("Usuário");

        lblPerfil.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblPerfil.setText("Perfil");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/Logo MK - Novo.png"))); // NOI18N

        menCad.setText("Cadastro");
        menCad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        menCadCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        menCadCli.setText("Pessoas");
        menCadCli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menCadCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadCliActionPerformed(evt);
            }
        });
        menCad.add(menCadCli);

        menCadOS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        menCadOS.setText("Entrada e Saida");
        menCadOS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menCadOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadOSActionPerformed(evt);
            }
        });
        menCad.add(menCadOS);

        menCadUsu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        menCadUsu.setText("Usuários");
        menCadUsu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menCadUsu.setEnabled(false);
        menCadUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadUsuActionPerformed(evt);
            }
        });
        menCad.add(menCadUsu);

        Menu.add(menCad);

        menAju.setText("Ajuda");
        menAju.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menAju.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menAjuActionPerformed(evt);
            }
        });

        menAjuSob.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.ALT_MASK));
        menAjuSob.setText("Sobre");
        menAjuSob.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menAjuSob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menAjuSobActionPerformed(evt);
            }
        });
        menAju.add(menAjuSob);

        Menu.add(menAju);

        MenReles.setText("Relatório");
        MenReles.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenReles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenRelesActionPerformed(evt);
            }
        });

        MenRelES.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        MenRelES.setText("Entrada e Saida");
        MenRelES.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenRelES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenRelESActionPerformed(evt);
            }
        });
        MenReles.add(MenRelES);

        MenRelES1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
        MenRelES1.setText("Filtro Chaves");
        MenRelES1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenRelES1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenRelES1ActionPerformed(evt);
            }
        });
        MenReles.add(MenRelES1);

        Menu.add(MenReles);

        menOp.setText("Opções");
        menOp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        menOpSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        menOpSair.setText("Sair");
        menOpSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menOpSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menOpSairActionPerformed(evt);
            }
        });
        menOp.add(menOpSair);

        Menu.add(menOp);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(252, 252, 252)
                                .addComponent(lblPerfil))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(239, 239, 239)
                                .addComponent(lblUsuario)))
                        .addGap(0, 638, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(lblUsuario)
                        .addGap(26, 26, 26)
                        .addComponent(lblPerfil)
                        .addGap(66, 66, 66)
                        .addComponent(jLabel1)))
                .addContainerGap(188, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menCadCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadCliActionPerformed
        // vai charar a tela do cliente
        TelaCliente pessoas= new TelaCliente();
        pessoas.setVisible(true);
        Desktop.add(pessoas);
    }//GEN-LAST:event_menCadCliActionPerformed

    private void menCadOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadOSActionPerformed
        // as linhas abaixo iram abrir o form TelaOS dentro do desktopPane

        TelaOS OS = new TelaOS();
        OS.setVisible(true);
        Desktop.add(OS);
    }//GEN-LAST:event_menCadOSActionPerformed

    private void menCadUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadUsuActionPerformed
        // as linhas abaixo irão abrir o from telaUsuario dentro do desktopPane

        TelaUsuario usuario = new TelaUsuario();
        usuario.setVisible(true);
        Desktop.add(usuario);
    }//GEN-LAST:event_menCadUsuActionPerformed

    private void menAjuSobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menAjuSobActionPerformed

        // vai chamar a tela sobre
        TelaSobre sobre = new TelaSobre();
        sobre.setVisible(true);
    }//GEN-LAST:event_menAjuSobActionPerformed

    private void menAjuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menAjuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menAjuActionPerformed

    private void MenRelESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenRelESActionPerformed

        // gerando um relatório de clientes
        int confirma = JOptionPane.showConfirmDialog(null,"Confirma a emissão deste relatório?","ATENÇÃO!!",JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION){
            //imprimindo relatório com o framework JasperReports
            try {
                //Usando a classe JasperPrint para preparar a impressão de um relatório
                JasperPrint print = JasperFillManager.fillReport("M:/Portaria/Reports/EntradaSaida3.jasper",null,conecxao);
                //a linha abaixo exibe o relatório através da classe JasperViewer
                JasperViewer.viewReport(print,false);
            } catch (JRException e) {
              JOptionPane.showMessageDialog(null,"Ocorreu um erro:" +e);
            }
        }
    }//GEN-LAST:event_MenRelESActionPerformed

    private void MenRelesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenRelesActionPerformed

    }//GEN-LAST:event_MenRelesActionPerformed

    private void menOpSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menOpSairActionPerformed
        // vai parecer uma tela para comfirmação para sair do projeto
        int sair = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja sair?","ATENÇÃO",JOptionPane.YES_NO_OPTION);
        if(sair == JOptionPane.YES_OPTION)
        {
            System.exit(0);

        }
        else
        {

        }
    }//GEN-LAST:event_menOpSairActionPerformed

    private void MenRelES1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenRelES1ActionPerformed
        // TODO add your handling code here:
         TelaFiltro filtro = new TelaFiltro();
        filtro.setVisible(true);
        Desktop.add(filtro);
    }//GEN-LAST:event_MenRelES1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JMenuItem MenRelES;
    private javax.swing.JMenuItem MenRelES1;
    private javax.swing.JMenu MenReles;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel lblPerfil;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JMenu menAju;
    private javax.swing.JMenuItem menAjuSob;
    private javax.swing.JMenu menCad;
    private javax.swing.JMenuItem menCadCli;
    private javax.swing.JMenuItem menCadOS;
    public static javax.swing.JMenuItem menCadUsu;
    private javax.swing.JMenu menOp;
    private javax.swing.JMenuItem menOpSair;
    // End of variables declaration//GEN-END:variables
}
