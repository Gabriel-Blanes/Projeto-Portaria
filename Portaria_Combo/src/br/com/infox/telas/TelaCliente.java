package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conecxao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;



    public TelaCliente() {
        initComponents();
        conecxao = ModuloConexao.conector();
    }

    private void pesquisar_cliente() {
      String sql = "select pc_id as id, pc_nome as Nome, pc_cpf as CPF,pc_rg as RG,pc_nasci as Nascimento, pc_motivo as Motivo, pc_destinatario as Destinatário, pc_empresa as Empresa, pc_placa_modelo_car as 'Modelo/placa do carro', pc_placa_modelo_mot as 'Modelo/placa da moto'  from  pessoas_cadastro where pc_nome like ? order by pc_nome";
         
 
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
        cbPsMot.setSelectedItem(tblPessoas.getModel().getValueAt(setar, 5).toString());
       txtPsDest.setText(tblPessoas.getModel().getValueAt(setar, 6).toString());
       txtPsEm.setText(tblPessoas.getModel().getValueAt(setar, 7).toString());
        txtPsCar.setText(tblPessoas.getModel().getValueAt(setar, 8).toString());
       txtPsMot.setText(tblPessoas.getModel().getValueAt(setar, 9 ).toString());

        btnCliCreate.setEnabled(false);

    }

    private void adicionar() {
        String sql = "insert into pessoas_cadastro(pc_nome,pc_cpf,pc_rg,pc_nasci,pc_motivo,pc_destinatario,pc_empresa,pc_placa_modelo_car,pc_placa_modelo_mot)values(?,?,?,?,?,?,?,?,?)";
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
                pst.setString(5, cbPsMot.getSelectedItem().toString());
                pst.setString(6, txtPsDest.getText());
                pst.setString(7, txtPsEm.getText());
                pst.setString(8, txtPsCar.getText());
                pst.setString(9, txtPsMot.getText());
                
                
              
        
                if ((txtPsNome.getText().isEmpty()) ) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!");
                } else {

                    int adicionado = pst.executeUpdate();
                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Pessoa adicionada com sucesso!");
                        pst.setString(1, txtPsNome.getText());

                        txtPsNome.setText(null);
                     
                        txtPsEm.setText(null);
                        txtPsRG.setText(null);
                        txtPsCPF.setText(null);
                        txtPsDat.setText(null);
                        txtPsCar.setText(null);
                        txtPsMot.setText(null);
                        txtPsId.setText(null);
                    }
                }

            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void alterar() {
       String sql = "update pessoas_cadastro set pc_nome = ?,pc_cpf  = ?,pc_rg = ? ,pc_nasci = ?,pc_motivo = ?,pc_destinatario  = ?,pc_empresa = ?,pc_placa_modelo_car = ?,pc_placa_modelo_mot  = ?  where pc_id = ?";
        try {

            pst = conecxao.prepareStatement(sql);

              
       
            
                pst.setString(1, txtPsNome.getText());
                pst.setString(2, txtPsCPF.getText());
                pst.setString(3, txtPsRG.getText());  
                pst.setString(4, txtPsDat.getText()); 
                pst.setString(5, cbPsMot.getSelectedItem().toString());
                pst.setString(6, txtPsDest.getText());
                pst.setString(7, txtPsEm.getText());
                pst.setString(8, txtPsCar.getText());
                pst.setString(9, txtPsMot.getText());
                 pst.setString(10, txtPsId.getText());
                
                
            if ((txtPsNome.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!");
            }

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Dados da Pessoa alterada com sucesso!");
                pst.setString(1, txtPsNome.getText());

                     txtPsNome.setText(null);
                  
                        txtPsEm.setText(null);
                        txtPsRG.setText(null);
                        txtPsCPF.setText(null);
                        txtPsDat.setText(null);
                        txtPsCar.setText(null);
                        txtPsMot.setText(null);
                        txtPsId.setText(null);

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
                     
                        txtPsEm.setText(null);
                        txtPsRG.setText(null);
                        txtPsCPF.setText(null);
                        txtPsDat.setText(null);
                        txtPsCar.setText(null);
                        txtPsMot.setText(null);
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
        jLabel10 = new javax.swing.JLabel();
        txtPsDat = new javax.swing.JFormattedTextField();
        btnCliCreate = new javax.swing.JButton();
        btnCliDelete = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtPsRG = new javax.swing.JFormattedTextField();
        txtPsCPF = new javax.swing.JFormattedTextField();
        txtPsCar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPsMot = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbPsMot = new javax.swing.JComboBox<>();
        txtPsNome = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPsPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPessoas = new javax.swing.JTable();
        txtPsDest = new javax.swing.JTextField();
        txtPsEm = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnCliUpdate = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Pessoas");
        setPreferredSize(new java.awt.Dimension(604, 630));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Id");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("CPF");

        txtPsId.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("RG");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Data de nascimento");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Empresa");

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

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("*Motivo");

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

        txtPsCar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtPsCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPsCarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Placa/Modelo da moto");

        txtPsMot.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtPsMot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPsMotActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("*Nome");

        cbPsMot.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ajudante", "Colaborador", "Cliente", "Entrevista", "Prestador de Serviço", "Visitante" }));
        cbPsMot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbPsMot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPsMotActionPerformed(evt);
            }
        });

        txtPsNome.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtPsNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPsNomeActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("Destinatário");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Placa/Modelo do carro");

        txtPsPesquisar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtPsPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPsPesquisarKeyReleased(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/pesquisar.png"))); // NOI18N

        tblPessoas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "CPF", "RG", "Data de Nascimento", "Motivo", "Destinatário", "Empresa", "Carro", "Moto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        txtPsDest.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtPsDest.setText("Mk logistica");
        txtPsDest.setEnabled(false);

        txtPsEm.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtPsEm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPsEmActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(btnCliDelete)
                .addGap(106, 106, 106)
                .addComponent(btnCliCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCliUpdate)
                .addGap(61, 61, 61))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtPsPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(19, 19, 19)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel7))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtPsDest, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(35, 35, 35)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPsCar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPsNome, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPsId, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtPsCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(51, 51, 51)
                                    .addComponent(jLabel11)
                                    .addGap(18, 18, 18)
                                    .addComponent(cbPsMot, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPsDat, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtPsEm, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel9)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtPsRG, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(32, 32, 32)
                                    .addComponent(txtPsMot, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel11)
                        .addComponent(cbPsMot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtPsCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtPsRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtPsDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtPsEm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtPsDest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtPsCar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPsMot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCliCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCliUpdate)
                    .addComponent(btnCliDelete))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        setBounds(0, 0, 600, 562);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCliCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliCreateActionPerformed
        // chamando o metodo adicionar
        adicionar();
    }//GEN-LAST:event_btnCliCreateActionPerformed

    private void btnCliDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliDeleteActionPerformed
        // chamando o metodo remover
        remover();
    }//GEN-LAST:event_btnCliDeleteActionPerformed

    private void txtPsCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPsCarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPsCarActionPerformed

    private void txtPsMotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPsMotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPsMotActionPerformed

    private void cbPsMotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPsMotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPsMotActionPerformed

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

    private void txtPsEmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPsEmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPsEmActionPerformed

    private void btnCliUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliUpdateActionPerformed
        // chamando o metodo alterar
        alterar();
    }//GEN-LAST:event_btnCliUpdateActionPerformed

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
    private javax.swing.JButton btnCliUpdate;
    private javax.swing.JComboBox<String> cbPsMot;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPessoas;
    private javax.swing.JFormattedTextField txtPsCPF;
    private javax.swing.JTextField txtPsCar;
    private javax.swing.JFormattedTextField txtPsDat;
    private javax.swing.JTextField txtPsDest;
    private javax.swing.JTextField txtPsEm;
    private javax.swing.JTextField txtPsId;
    private javax.swing.JTextField txtPsMot;
    private javax.swing.JTextField txtPsNome;
    private javax.swing.JTextField txtPsPesquisar;
    private javax.swing.JFormattedTextField txtPsRG;
    // End of variables declaration//GEN-END:variables
}
