package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaCliente2 extends javax.swing.JInternalFrame {

    Connection conecxao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;



    public TelaCliente2() {
        initComponents();
        conecxao = ModuloConexao.conector();
    }

    private void pesquisar_cliente() {
      String sql = "select pc_id as id, pc_nome as Nome, pc_cpf as CPF,pc_rg as RG,pc_nasci as Nascimento  from  pessoas_cadastro2 where pc_nome like ? order by pc_nome";
         
 
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
        String sql = "insert into pessoas_cadastro2(pc_nome,pc_cpf,pc_rg,pc_nasci)values(?,?,?,?)";
       String sqll = "select pc_nome from pessoas_cadastro2 where  pc_nome = ? ";

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
       String sql = "update pessoas_cadastro2 set pc_nome = ?,pc_cpf  = ?,pc_rg = ? ,pc_nasci = ? where pc_id = ?";
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
            String sql = "delete from pessoas_cadastro2 where pc_id = ?";

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

        txtPsId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnCliUpdate = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnCliDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPessoas = new javax.swing.JTable();
        txtPsRG = new javax.swing.JFormattedTextField();
        txtPsCPF = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPsDat = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPsNome = new javax.swing.JTextField();
        btnCliCreate = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtPsPesquisar = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Pessoas-Chaves");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        txtPsId.setEnabled(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/pesquisar.png"))); // NOI18N

        btnCliUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        btnCliUpdate.setToolTipText("Alterar");
        btnCliUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliUpdateActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("RG");

        btnCliDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        btnCliDelete.setToolTipText("Remover");
        btnCliDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliDeleteActionPerformed(evt);
            }
        });

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
        tblPessoas.setDragEnabled(true);
        tblPessoas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPessoasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPessoas);

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

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Data de nascimento");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("*Nome");

        try {
            txtPsDat.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Id");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("*Campos Obrigatórios");

        txtPsNome.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtPsNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPsNomeActionPerformed(evt);
            }
        });

        btnCliCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        btnCliCreate.setToolTipText("Adicionar");
        btnCliCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCliCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliCreateActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("CPF");

        txtPsPesquisar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtPsPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPsPesquisarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtPsPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(0, 63, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtPsCPF))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPsDat, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtPsRG))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPsNome, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPsId, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(btnCliDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCliCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(btnCliUpdate)
                .addGap(65, 65, 65))
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPsNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtPsId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtPsCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtPsRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtPsDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCliUpdate)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCliDelete)
                                .addGap(7, 7, 7))))
                    .addComponent(btnCliCreate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCliUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliUpdateActionPerformed
        // chamando o metodo alterar
        alterar();
    }//GEN-LAST:event_btnCliUpdateActionPerformed

    private void btnCliDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliDeleteActionPerformed
        // chamando o metodo remover
        remover();
    }//GEN-LAST:event_btnCliDeleteActionPerformed

    private void tblPessoasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPessoasMouseClicked
        // evento que será usado para setar os campos da tabela clicando com o mause
        //chamando o metodo setar_campos
        setar_campos();
    }//GEN-LAST:event_tblPessoasMouseClicked

    private void txtPsNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPsNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPsNomeActionPerformed

    private void btnCliCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliCreateActionPerformed
        // chamando o metodo adicionar
        adicionar();
    }//GEN-LAST:event_btnCliCreateActionPerformed

    private void txtPsPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPsPesquisarKeyReleased
        // o evento é tipo "enquanto  for digitado"
        //chamando o metodo pesquisar cliente
        pesquisar_cliente();
    }//GEN-LAST:event_txtPsPesquisarKeyReleased

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
            java.util.logging.Logger.getLogger(TelaCliente2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCliente2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCliente2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCliente2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCliente2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCliCreate;
    private javax.swing.JButton btnCliDelete;
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
