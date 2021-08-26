package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class TelaFiltro2 extends javax.swing.JInternalFrame {

    Connection conecxao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;



    public TelaFiltro2() {
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

        } catch (SQLException e) {
          JOptionPane.showMessageDialog(null,"Ocorreu um erro:" +e);
        }

    }

     private void setar_campos() {
        int setar = tblPessoas.getSelectedRow();

      txtCliid.setText(tblPessoas.getModel().getValueAt(setar, 0).toString());
      txtPsNome.setText(tblPessoas.getModel().getValueAt(setar, 1).toString());

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnFiltroIm = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        RadioMesAll = new javax.swing.JRadioButton();
        RadioMesOne = new javax.swing.JRadioButton();
        RadioEntreMes = new javax.swing.JRadioButton();
        TxtMes = new javax.swing.JFormattedTextField();
        TxtMes1 = new javax.swing.JFormattedTextField();
        RadioChaveNome = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        txtOSCh = new javax.swing.JTextField();
        RadioChave = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        txtPsPesquisar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCliid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPsNome = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPessoas = new javax.swing.JTable();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Tela-Filtro-Chaves");

        btnFiltroIm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/print.png"))); // NOI18N
        btnFiltroIm.setToolTipText("Imprimir");
        btnFiltroIm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFiltroIm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltroImActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro"));

        jLabel1.setText("Mes");

        buttonGroup1.add(RadioMesAll);
        RadioMesAll.setText("Todos os meses");
        RadioMesAll.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RadioMesAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioMesAllActionPerformed(evt);
            }
        });

        buttonGroup1.add(RadioMesOne);
        RadioMesOne.setText("Um mes");
        RadioMesOne.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RadioMesOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioMesOneActionPerformed(evt);
            }
        });

        buttonGroup1.add(RadioEntreMes);
        RadioEntreMes.setText("Entre meses");
        RadioEntreMes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RadioEntreMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioEntreMesActionPerformed(evt);
            }
        });

        try {
            TxtMes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("-##-")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            TxtMes1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("-##-")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtMes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtMes1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(RadioChaveNome);
        RadioChaveNome.setText("chave e nome");
        RadioChaveNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioChaveNomeActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Chave");

        txtOSCh.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        buttonGroup1.add(RadioChave);
        RadioChave.setText("Chave");
        RadioChave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioChaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtOSCh, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RadioChave)
                            .addComponent(RadioChaveNome)
                            .addComponent(RadioMesOne)
                            .addComponent(RadioEntreMes)
                            .addComponent(RadioMesAll)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(TxtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtMes1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RadioMesAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RadioMesOne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RadioEntreMes)
                .addGap(1, 1, 1)
                .addComponent(RadioChaveNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RadioChave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtMes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtOSCh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pessoas"));
        jPanel2.setName("Pessoas"); // NOI18N

        txtPsPesquisar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtPsPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPsPesquisarActionPerformed(evt);
            }
        });
        txtPsPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPsPesquisarKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/pesquisar.png"))); // NOI18N

        txtCliid.setEnabled(false);
        txtCliid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCliidMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("id");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Nome");

        txtPsNome.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtPsNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPsNomeActionPerformed(evt);
            }
        });

        tblPessoas = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIdenx, int colIndex){
                return false;
            }
        };
        tblPessoas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblPessoas.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null}
            },
            new String [] {
                " id", "Nome", "Cpf", "RG", "Data de nascimentol"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblPessoas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblPessoas.setFocusable(false);
        tblPessoas.getTableHeader().setReorderingAllowed(false);
        tblPessoas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPessoasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPessoas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtPsNome, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtPsPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCliid, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPsPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtCliid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPsNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(btnFiltroIm, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnFiltroIm)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltroImActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroImActionPerformed
    
        
        if(RadioMesAll.isSelected()){

            int confirma = JOptionPane.showConfirmDialog(null,"Confirma a emissão deste relatório?","ATENÇÃO!!",JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION){
                //imprimindo relatório com o framework JasperReports
                try {
                    HashMap Filtro = new  HashMap();
                    Filtro.put("nome",(txtPsNome.getText()));
                    //Usando a classe JasperPrint para preparar a impressão de um relatório
                    JasperPrint print = JasperFillManager.fillReport("M:/Portaria/Programa/Reports/FiltroPessoas4.jasper",Filtro,conecxao);
                    //a linha abaixo exibe o relatório através da classe JasperViewer
                    JasperViewer.viewReport(print,false);
                } catch (JRException e) {
               JOptionPane.showMessageDialog(null,"Ocorreu um erro:" +e);
                }
            }

        }
        else if(RadioMesOne.isSelected()){

            int confirmaa = JOptionPane.showConfirmDialog(null,"Confirma a emissão deste relatório?","ATENÇÃO!!",JOptionPane.YES_NO_OPTION);
            if (confirmaa == JOptionPane.YES_OPTION){
                //imprimindo relatório com o framework JasperReports
                try {

                    HashMap Filtro = new  HashMap();
                    Filtro.put("nome",(txtPsNome.getText()));
                    Filtro.put("ES",(TxtMes.getText()));

                    //Usando a classe JasperPrint para preparar a impressão de um relatório
                    JasperPrint print = JasperFillManager.fillReport("M:/Portaria/Programa/Reports/FiltroPessoas2.jasper",Filtro,conecxao);
                    //a linha abaixo exibe o relatório através da classe JasperViewer
                    JasperViewer.viewReport(print,false);
                } catch (JRException e) {
                    JOptionPane.showMessageDialog(null,"Ocorreu um erro:" +e);
                }
            }

        }

        else if(RadioEntreMes.isSelected()){

            int confirmaaa = JOptionPane.showConfirmDialog(null,"Confirma a emissão deste relatório?","ATENÇÃO!!",JOptionPane.YES_NO_OPTION);
            if (confirmaaa == JOptionPane.YES_OPTION){
                //imprimindo relatório com o framework JasperReports
                try {
                    HashMap Filtro = new  HashMap();
                    Filtro.put("nome",(txtPsNome.getText()));
                    Filtro.put("ES",(TxtMes.getText()));
                    Filtro.put("ES1",(TxtMes1.getText()));
                    //Usando a classe JasperPrint para preparar a impressão de um relatório
                    JasperPrint print = JasperFillManager.fillReport("M:/Portaria/Reports/FiltroPessoas3.jasper",Filtro,conecxao);
                    //a linha abaixo exibe o relatório através da classe JasperViewer
                    JasperViewer.viewReport(print,false);
                } catch (JRException e) {
                    JOptionPane.showMessageDialog(null,"Ocorreu um erro:" +e);
                }
            }

        }

        else if(RadioChaveNome.isSelected())
        {
            int confirmaaa = JOptionPane.showConfirmDialog(null,"Confirma a emissão deste relatório?","ATENÇÃO!!",JOptionPane.YES_NO_OPTION);
            if (confirmaaa == JOptionPane.YES_OPTION){
                //imprimindo relatório com o framework JasperReports
                try {
                    HashMap Filtro = new  HashMap();
                    Filtro.put("nome",(txtPsNome.getText()));
                    Filtro.put("chave",(txtOSCh.getText()));

                    //Usando a classe JasperPrint para preparar a impressão de um relatório
                    JasperPrint print = JasperFillManager.fillReport("M:/Portaria/Programa/Reports/FiltroChaves1.jasper",Filtro,conecxao);
                    //a linha abaixo exibe o relatório através da classe JasperViewer
                    JasperViewer.viewReport(print,false);
                } catch (JRException e) {
                     JOptionPane.showMessageDialog(null,"Ocorreu um erro:" +e);
                }
            }

        }
        else if(RadioChave.isSelected())
        {
            int confirmaaa = JOptionPane.showConfirmDialog(null,"Confirma a emissão deste relatório?","ATENÇÃO!!",JOptionPane.YES_NO_OPTION);
            if (confirmaaa == JOptionPane.YES_OPTION){
                //imprimindo relatório com o framework JasperReports
                try {
                    HashMap Filtro = new  HashMap();

                    Filtro.put("chave",(txtOSCh.getText()));

                    //Usando a classe JasperPrint para preparar a impressão de um relatório
                    JasperPrint print = JasperFillManager.fillReport("M:/Portaria/Programa/Reports/FiltroChaves2.jasper",Filtro,conecxao);
                    //a linha abaixo exibe o relatório através da classe JasperViewer
                    JasperViewer.viewReport(print,false);
                } catch (JRException e) {
                    JOptionPane.showMessageDialog(null,"Ocorreu um erro:" +e);
                }
            }

        }
       else{
                 JOptionPane.showMessageDialog(null,"Não foi selecionado algum campo das opções do campo Filtro.Por favor,selecione e preencha os campos que apareceu das demais opções, apos selecionar uma opção do campo Filtro!!");
            }

    }//GEN-LAST:event_btnFiltroImActionPerformed

    private void RadioMesAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioMesAllActionPerformed
        // TODO add your handling code here:
        if(RadioMesAll.isSelected()){
            TxtMes.setEnabled(false);
            TxtMes1.setEnabled(false);
            txtOSCh.setEnabled(false);
            txtPsNome.setEnabled(true);
            txtPsPesquisar.setEnabled(true);
            tblPessoas.setEnabled(true);
        }
    }//GEN-LAST:event_RadioMesAllActionPerformed

    private void RadioMesOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioMesOneActionPerformed
        // TODO add your handling code here:
        if(RadioMesOne.isSelected()){
            TxtMes.setEnabled(true);
            TxtMes1.setEnabled(false);
            txtOSCh.setEnabled(false);
            txtPsNome.setEnabled(true);
            txtPsPesquisar.setEnabled(true);
            tblPessoas.setEnabled(true);

        }
    }//GEN-LAST:event_RadioMesOneActionPerformed

    private void RadioEntreMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioEntreMesActionPerformed
        // TODO add your handling code here:
        if(RadioEntreMes.isSelected()){
            TxtMes.setEnabled(true);
            TxtMes1.setEnabled(true);
            txtOSCh.setEnabled(false);
            txtPsNome.setEnabled(true);
            txtPsPesquisar.setEnabled(true);
            tblPessoas.setEnabled(true);
        }
    }//GEN-LAST:event_RadioEntreMesActionPerformed

    private void TxtMes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtMes1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtMes1ActionPerformed

    private void RadioChaveNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioChaveNomeActionPerformed
        // TODO add your handling code here:
        if(RadioChaveNome.isSelected()){
            TxtMes.setEnabled(false);
            TxtMes1.setEnabled(false);
            txtOSCh.setEnabled(true);
            txtPsNome.setEnabled(true);
            txtPsPesquisar.setEnabled(true);
            tblPessoas.setEnabled(true);

        }
    }//GEN-LAST:event_RadioChaveNomeActionPerformed

    private void RadioChaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioChaveActionPerformed
        // TODO add your handling code here:
        if(RadioChave.isSelected()){
            TxtMes.setEnabled(false);
            TxtMes1.setEnabled(false);
            txtOSCh.setEnabled(true);
            txtPsNome.setEnabled(false);
            txtPsPesquisar.setEnabled(false);
            tblPessoas.setEnabled(false);
            txtPsNome.setEnabled(false);

        }
    }//GEN-LAST:event_RadioChaveActionPerformed

    private void txtPsPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPsPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPsPesquisarActionPerformed

    private void txtPsPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPsPesquisarKeyReleased
        // TODO add your handling code here:
        // o evento é tipo "enquanto  for digitado"
        //chamando o metodo pesquisar cliente
        pesquisar_cliente();
    }//GEN-LAST:event_txtPsPesquisarKeyReleased

    private void txtCliidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCliidMouseClicked

    }//GEN-LAST:event_txtCliidMouseClicked

    private void txtPsNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPsNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPsNomeActionPerformed

    private void tblPessoasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPessoasMouseClicked
        // evento que será usado para setar os campos da tabela clicando com o mause
        //chamando o metodo setar_campos
        setar_campos();
    }//GEN-LAST:event_tblPessoasMouseClicked

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
            java.util.logging.Logger.getLogger(TelaFiltro2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaFiltro2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaFiltro2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaFiltro2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaFiltro2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RadioChave;
    private javax.swing.JRadioButton RadioChaveNome;
    private javax.swing.JRadioButton RadioEntreMes;
    private javax.swing.JRadioButton RadioMesAll;
    private javax.swing.JRadioButton RadioMesOne;
    private javax.swing.JFormattedTextField TxtMes;
    private javax.swing.JFormattedTextField TxtMes1;
    private javax.swing.JButton btnFiltroIm;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblPessoas;
    private javax.swing.JTextField txtCliid;
    private javax.swing.JTextField txtOSCh;
    private javax.swing.JTextField txtPsNome;
    private javax.swing.JTextField txtPsPesquisar;
    // End of variables declaration//GEN-END:variables
}
