package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import java.awt.HeadlessException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conecxao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;



    public TelaCliente() {
        initComponents();
        conecxao = ModuloConexao.conector();
    }

    private void pesquisar_cliente() {
      String sql = "select pc_id as id, pc_nome as Nome, pc_cpf as CPF,pc_rg as RG,pc_nasci as Nascimento  from  pessoas_cadastro where pc_nome like ? order by pc_nome";
         
 
        try {
            pst = conecxao.prepareStatement(sql);
            pst.setString(1, txtPsPesquisar.getText() + '%');
            rs = pst.executeQuery();

            tblPessoas.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void setar_campos() {
        int setar = tblPessoas.getSelectedRow();

      txtPsId.setText(tblPessoas.getModel().getValueAt(setar, 0).toString());
        txtPsNome.setText(tblPessoas.getModel().getValueAt(setar, 1).toString());
        txtPsCPF.setText(tblPessoas.getModel().getValueAt(setar, 2).toString());
        txtPsRG.setText(tblPessoas.getModel().getValueAt(setar, 3).toString());
        txtPsDat.setText(tblPessoas.getModel().getValueAt(setar, 4).toString());
      

        btnCliCreate.setEnabled(false);

    }

    private void adicionar() {
        String sql = "insert into pessoas_cadastro(pc_nome,pc_cpf,pc_rg,pc_nasci)values(?,?,?,?)";
       String sqll = "select pc_nome from pessoas_cadastro where  pc_nome = ? ";

        try {
            pst = conecxao.prepareStatement(sqll);
            pst.setString(1, txtPsNome.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "O nome preenchido corresponde a uma Pessoa já cadastrada!");
            } else {

                pst = conecxao.prepareStatement(sql);

         
           
           
                
                pst.setString(1, txtPsNome.getText());
                pst.setString(2, txtPsCPF.getText());
                pst.setString(3, txtPsRG.getText());  
                pst.setString(4, txtPsDat.getText()); 
             
                
                
              
        
                if ((txtPsNome.getText().isEmpty()) ) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!");
                } else {

                    int adicionado = pst.executeUpdate();
                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Pessoa adicionada com sucesso!");
                        pst.setString(1, txtPsNome.getText());

                        txtPsNome.setText(null);
                     
                     
                        txtPsRG.setText(null);
                        txtPsCPF.setText(null);
                        txtPsDat.setText(null);
                     
                        txtPsId.setText(null);
                    }
                }

            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void alterar() {
       String sql = "update pessoas_cadastro set pc_nome = ?,pc_cpf  = ?,pc_rg = ? ,pc_nasci = ? where pc_id = ?";
        try {

            pst = conecxao.prepareStatement(sql);

              
       
            
                pst.setString(1, txtPsNome.getText());
                pst.setString(2, txtPsCPF.getText());
                pst.setString(3, txtPsRG.getText());  
                pst.setString(4, txtPsDat.getText()); 
              
                 pst.setString(5, txtPsId.getText());
                
                
            if ((txtPsNome.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!");
            }

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Dados da Pessoa alterada com sucesso!");
                pst.setString(1, txtPsNome.getText());

                     txtPsNome.setText(null);
                  
                    
                        txtPsRG.setText(null);
                        txtPsCPF.setText(null);
                        txtPsDat.setText(null);
                     

                btnCliCreate.setEnabled(true);

            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void remover() {

        int comfirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esta Pessoa?", "ATENÇÃO!", JOptionPane.YES_NO_OPTION);
        if (comfirma == JOptionPane.YES_OPTION) {
            String sql = "delete from pessoas_cadastro where pc_id = ?";

            try {
                pst = conecxao.prepareStatement(sql);
                pst.setString(1, txtPsId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados da Pessoa removido com sucesso!");

                 txtPsNome.setText(null);
                     
                     
                        txtPsRG.setText(null);
                        txtPsCPF.setText(null);
                        txtPsDat.setText(null);
                     
                        txtPsId.setText(null);

                }
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPsId = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtPsDat = new javax.swing.JFormattedTextField();
        btnCliCreate = new javax.swing.JButton();
        btnCliDelete = new javax.swing.JButton();
        txtPsRG = new javax.swing.JFormattedTextField();
        txtPsCPF = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPsNome = new javax.swing.JTextField();
        txtPsPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPessoas = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnCliUpdate = new javax.swing.JButton();
        btnCliIm = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Pessoas");
        setMinimumSize(new java.awt.Dimension(1, 1));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Id");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("CPF");

        txtPsId.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("RG");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Data de nascimento");

        try {
            txtPsDat.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnCliCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        btnCliCreate.setToolTipText("Adicionar");
        btnCliCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCliCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliCreateActionPerformed(evt);
            }
        });

        btnCliDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        btnCliDelete.setToolTipText("Remover");
        btnCliDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliDeleteActionPerformed(evt);
            }
        });

        try {
            txtPsRG.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtPsCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("*Nome");

        txtPsNome.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtPsNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPsNomeActionPerformed(evt);
            }
        });

        txtPsPesquisar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtPsPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPsPesquisarKeyReleased(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/pesquisar.png"))); // NOI18N

        tblPessoas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "CPF", "RG", "Data de Nascimento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblPessoas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblPessoas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPessoasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPessoas);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("*Campos Obrigatórios");

        btnCliUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        btnCliUpdate.setToolTipText("Alterar");
        btnCliUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliUpdateActionPerformed(evt);
            }
        });

        btnCliIm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/print.png"))); // NOI18N
        btnCliIm.setToolTipText("Imprimir");
        btnCliIm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliIm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliImActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtPsPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPsNome, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(141, 141, 141)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(txtPsId, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCliDelete)
                .addGap(59, 59, 59)
                .addComponent(btnCliCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(btnCliUpdate)
                .addGap(52, 52, 52)
                .addComponent(btnCliIm, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPsDat, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPsRG, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPsCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtPsPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPsNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtPsId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPsCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtPsRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtPsDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnCliUpdate)
                        .addComponent(btnCliCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCliDelete))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btnCliIm)))
                .addGap(69, 69, 69))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCliCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliCreateActionPerformed
        // chamando o metodo adicionar
        adicionar();
    }//GEN-LAST:event_btnCliCreateActionPerformed

    private void btnCliDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliDeleteActionPerformed
        // chamando o metodo remover
        remover();
    }//GEN-LAST:event_btnCliDeleteActionPerformed

    private void txtPsNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPsNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPsNomeActionPerformed

    private void txtPsPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPsPesquisarKeyReleased
        // o evento é tipo "enquanto  for digitado"
        //chamando o metodo pesquisar cliente
        pesquisar_cliente();
    }//GEN-LAST:event_txtPsPesquisarKeyReleased

    private void tblPessoasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPessoasMouseClicked
        // evento que será usado para setar os campos da tabela clicando com o mause
        //chamando o metodo setar_campos
        setar_campos();
    }//GEN-LAST:event_tblPessoasMouseClicked

    private void btnCliUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliUpdateActionPerformed
        // chamando o metodo alterar
        alterar();
    }//GEN-LAST:event_btnCliUpdateActionPerformed

    private void btnCliImActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliImActionPerformed
        // chamando o metodo imprimir_es
        int confirma = JOptionPane.showConfirmDialog(null,"Confirma a emissão deste relatório?","ATENÇÃO!!",JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION){
            //imprimindo relatório com o framework JasperReports
            try {
                HashMap Filtro = new  HashMap();
                Filtro.put("nome",(txtPsNome.getText()));
                //Usando a classe JasperPrint para preparar a impressão de um relatório
                JasperPrint print = JasperFillManager.fillReport("M:/Portaria/Reports/FiltroPessoas.jasper",Filtro,conecxao);
                //a linha abaixo exibe o relatório através da classe JasperViewer
                JasperViewer.viewReport(print,false);
            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btnCliImActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCliCreate;
    private javax.swing.JButton btnCliDelete;
    private javax.swing.JButton btnCliIm;
    private javax.swing.JButton btnCliUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPessoas;
    private javax.swing.JFormattedTextField txtPsCPF;
    private javax.swing.JFormattedTextField txtPsDat;
    private javax.swing.JTextField txtPsId;
    private javax.swing.JTextField txtPsNome;
    private javax.swing.JTextField txtPsPesquisar;
    private javax.swing.JFormattedTextField txtPsRG;
    // End of variables declaration//GEN-END:variables
}
