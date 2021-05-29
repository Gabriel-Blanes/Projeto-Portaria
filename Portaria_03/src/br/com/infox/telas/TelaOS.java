package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class TelaOS extends javax.swing.JInternalFrame {

    Connection conecxao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    private String tipo;

    public TelaOS() {
        initComponents();
        conecxao = ModuloConexao.conector();
    }

    private void pesquisar_cliente() {
      String sql = "select pc_id as id, pc_nome as Nome, pc_cpf as CPF,pc_rg as RG,pc_nasci as Nascimento  from  pessoas_cadastro where pc_nome like ? order by pc_nome";
      
        try {
            
            pst = conecxao.prepareStatement(sql);        
            pst.setString(1, txtCliPesquisar.getText() + '%');
            rs = pst.executeQuery();
          
         
            

            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void setar_campos() {
        int setar = tblClientes.getSelectedRow();

        txtCliid.setText(tblClientes.getModel().getValueAt(setar, 0).toString());

    }

    private void adicionar() {

        String sql = "insert into entrada_saida (en_placa_modelo,en_situacao,en_dest,en_km_sai,en_km_en,en_km_ro,en_data_sai,en_data_en,pc_id) values(?,?,?,?,?,?,?,?,?) ";

        try {

            pst = conecxao.prepareStatement(sql);

           
            pst.setString(1, cbOSPl.getSelectedItem().toString());
            pst.setString(2, cbOsSit.getSelectedItem().toString());
            pst.setString(3,txtOSDest.getText());
            pst.setString(4,txtOSKmS.getText());
            pst.setString(5,txtOSKmE.getText());
            pst.setString(6,txtOSKmR.getText());
            pst.setString(7, txtOSSai.getText());
            pst.setString(8, txtOSEn.getText());
            pst.setString(9,txtCliid.getText());
     
       
           

          if ((txtCliid.getText().isEmpty()) ){
              JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!");
          } else {
              
              int adicionado = pst.executeUpdate();
              if (adicionado > 0) {
                  JOptionPane.showMessageDialog(null, "Entrada e Saida da frota adicionada com sucesso!");
                  
                     txtOsEs.setText(null);
                     txtOSDest.setText(null);
                     txtOSKmE.setText(null);
                     txtOSKmS.setText(null);
                     txtOSKmR.setText(null);
                     txtOSEn.setText(null);
                     txtOSSai.setText(null);
                  
                   txtCliid.setText(null);
              }
          }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void consultar() {
        String num_os = JOptionPane.showInputDialog("Número da E/S da frota");

        String sql = "select * from entrada_saida where  en_id = " + num_os;

        try {
            pst = conecxao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
            txtOsEs.setText(rs.getString(1));
            cbOSPl.setSelectedItem(rs.getString(2));  
            cbOsSit.setSelectedItem(rs.getString(3));
            txtOSDest.setText(rs.getString(4));
            txtOSKmS.setText(rs.getString(5));
            txtOSKmE.setText(rs.getString(6));
            txtOSKmR.setText(rs.getString(7));
            txtOSSai.setText(rs.getString(8));
            txtOSEn.setText(rs.getString(9));
            txtCliid.setText(rs.getString(10));
          

                btnOSCreate.setEnabled(false);
                txtCliPesquisar.setEnabled(false);
                tblClientes.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "E/S da frota não cadastrada!");

            }
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "E/S  de frota invalida!");
            //System.out.println(e);

        } catch (HeadlessException | SQLException m) {
            JOptionPane.showMessageDialog(null, m);
        }
    }

    private void alterar() {
        String sql = "update entrada_saida set en_placa_modelo = ?,en_situacao = ?,en_dest = ?,en_km_sai = ?,en_km_en  = ?,en_km_ro = ?,en_data_sai = ? ,en_data_en = ?where en_id =?";

      
        try {

            pst = conecxao.prepareStatement(sql);
            
            
            pst.setString(1,cbOSPl.getSelectedItem().toString());
            pst.setString(2,cbOsSit.getSelectedItem().toString());
            pst.setString(3,txtOSDest.getText());
            pst.setString(4,txtOSKmS.getText());
            pst.setString(5,txtOSKmE.getText());
            pst.setString(6,txtOSKmR.getText());
            pst.setString(7,txtOSSai.getText());
            pst.setString(8,txtOSEn.getText());
            pst.setString(9,txtOsEs.getText());
     
     

           if ((txtCliid.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!");
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados de Entrada e Saida da frota alteradas com sucesso!");
                      
                     txtOsEs.setText(null);
                     txtOSDest.setText(null);
                     txtOSKmE.setText(null);
                     txtOSKmS.setText(null);
                     txtOSKmR.setText(null);
                     txtOSEn.setText(null);
                     txtOSSai.setText(null);
             
              
             
                    txtCliid.setText(null);

                    btnOSCreate.setEnabled(true);
                    txtCliPesquisar.setEnabled(true);
                    tblClientes.setVisible(true);
                }
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void remover() {

        int comfirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esta Entrada e Saida?", "ATENÇÃO!", JOptionPane.YES_NO_OPTION);
        if (comfirma == JOptionPane.YES_OPTION) {
            String sql = "delete from entrada_saida where en_id = ?";
            try {
                pst = conecxao.prepareStatement(sql);
                pst.setString(1, txtOsEs.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados de Entrada e Saida da frota Removidas com sucesso!");
                }
                
                     txtOsEs.setText(null);
                     txtOSDest.setText(null);
                     txtOSKmE.setText(null);
                     txtOSKmS.setText(null);
                     txtOSKmR.setText(null);
                     txtOSEn.setText(null);
                     txtOSSai.setText(null);
             
                    txtCliid.setText(null);
                    
                btnOSCreate.setEnabled(true);
                txtCliPesquisar.setEnabled(true);
                tblClientes.setVisible(true);

            } catch (HeadlessException | SQLException e) {

                JOptionPane.showMessageDialog(null, e);
            }

        }
    }
    
    
    
     private void imprimir_es()
     {
      int confirma = JOptionPane.showConfirmDialog(null,"Confirma a impressão desta E/S da frota?","ATENÇÃO!!",JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION){
            //imprimindo relatório com o framework JasperReports
            try {
                
                HashMap Filtro = new  HashMap();
                Filtro.put("en_id",Integer.parseInt(txtOsEs.getText()));
                //Usando a classe JasperPrint para preparar a impressão de um relatório
                   JasperPrint print = JasperFillManager.fillReport("M:/Portaria/Reports/ImES.jasper",Filtro,conecxao);
                //a linha abaixo exibe o relatório através da classe JasperViewer
             JasperViewer.viewReport(print,false);                  
            } catch (NumberFormatException | JRException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
     
     }
    
private  void km()
{
    try {
        
               String placa = cbOSPl.getSelectedItem().toString(); //aqui estou pegando a variavel do combobox

      String sql = "select max(en_km_en), max(en_km_sai) "
                    +"from entrada_saida " 
                    +"where en_placa_modelo = ?;"; //select para executar as funções max

        pst = conecxao.prepareStatement(sql); //passo a instrução para PreparedStatement
        pst.setString(1,placa); //aqui passamos a condição (where) para o select acima
        rs = pst.executeQuery(); //executa a query
        if(rs.next())
        {
            //caso de certo, true, pega o resultado, que é o resultSet do banco e seta nos campos os valores
            //da query 
            txtOSKmE.setText(rs.getString(1)); 
            txtOSKmS.setText(rs.getString(2));

        }
        
        
    } catch (Exception e) {
    }

} 
  private void calculaKm()
  {
      Double kmEntrada =  Double.parseDouble(txtOSKmE.getText());
      Double kmSaida = Double.parseDouble(txtOSKmS.getText());
      Double kmResultado = kmEntrada - kmSaida;
      txtOSKmR.setText(kmResultado.toString());
  }
        
private  void getCbDados()
    {
         
  try{
        String sit  = cbOsSit.getSelectedItem().toString();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Calendar c = Calendar.getInstance();
    
       switch (sit) {
            case "Na planta":
                 
       
                txtOSSai.setText(formato.format(c.getTime()));
                txtOSSai.setEnabled(true);
                txtOSEn.setEnabled(false);
                break;
            case "Encerrada":

           
                txtOSEn.setEnabled(true); //ultimo parametro atualizado
                txtOSEn.setText(formato.format(c.getTime()));
                txtOSSai.setEnabled(false);
                
                break;
            default:
                txtOSSai.setEnabled(true);
               break;
        }
       

        
    }
  catch(Exception e)
    {
    
    }
       
       
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        btnOSDelete1 = new javax.swing.JButton();
        btnOSRead1 = new javax.swing.JButton();
        txtOSSai1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnCliIm1 = new javax.swing.JButton();
        cbOsSit1 = new javax.swing.JComboBox<>();
        btnOSCreate1 = new javax.swing.JButton();
        txtOSCh1 = new javax.swing.JTextField();
        btnOSUpdate1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtCliPesquisar1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblClientes1 = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        txtCliid1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtOsEs1 = new javax.swing.JTextField();
        txtOSEn2 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtOSAuto1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtCliPesquisar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtCliid = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtOsEs = new javax.swing.JTextField();
        btnOSCalc = new javax.swing.JButton();
        btnOSRead = new javax.swing.JButton();
        btnOSUpdate = new javax.swing.JButton();
        txtOSKmR = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnCliIm = new javax.swing.JButton();
        btnOSCreate = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtOSKmS = new javax.swing.JTextField();
        cbOSPl = new javax.swing.JComboBox<>();
        txtOSKmE = new javax.swing.JTextField();
        cbOsSit = new javax.swing.JComboBox<>();
        txtOSDest = new javax.swing.JTextField();
        txtOSSai = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtOSEn = new javax.swing.JTextField();
        btnOSDelete = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Controle-de- Entrada-e-Saida");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("*Campos Obrigatórios");

        jInternalFrame1.setClosable(true);
        jInternalFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jInternalFrame1.setIconifiable(true);
        jInternalFrame1.setMaximizable(true);
        jInternalFrame1.setResizable(true);
        jInternalFrame1.setTitle("Controle-de- Entrada-e-Saida");

        btnOSDelete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        btnOSDelete1.setToolTipText("Remover");
        btnOSDelete1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSDelete1ActionPerformed(evt);
            }
        });

        btnOSRead1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/read.png"))); // NOI18N
        btnOSRead1.setToolTipText("Consultar");
        btnOSRead1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSRead1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSRead1ActionPerformed(evt);
            }
        });

        txtOSSai1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtOSSai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOSSai1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Data de saida");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("*Campos Obrigatórios");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("Chave");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("*Situação");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Data de entrada");

        btnCliIm1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/print.png"))); // NOI18N
        btnCliIm1.setToolTipText("Imprimir");
        btnCliIm1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliIm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliIm1ActionPerformed(evt);
            }
        });

        cbOsSit1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cbOsSit1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione uma opção", "Na planta", "Encerrada" }));
        cbOsSit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbOsSit1MouseClicked(evt);
            }
        });
        cbOsSit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOsSit1ActionPerformed(evt);
            }
        });

        btnOSCreate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        btnOSCreate1.setToolTipText("Adicionar");
        btnOSCreate1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSCreate1.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOSCreate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSCreate1ActionPerformed(evt);
            }
        });

        txtOSCh1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        btnOSUpdate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        btnOSUpdate1.setToolTipText("Alterar");
        btnOSUpdate1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSUpdate1ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Pessoas"));
        jPanel3.setName("Pessoas"); // NOI18N

        txtCliPesquisar1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtCliPesquisar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliPesquisar1ActionPerformed(evt);
            }
        });
        txtCliPesquisar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisar1KeyReleased(evt);
            }
        });

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/pesquisar.png"))); // NOI18N

        tblClientes1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblClientes1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                " id", "Nome", "Cpf", "RG", "Data de nascimentol", "Motivo", "Carro/placa", "Moto/placa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblClientes1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientes1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblClientes1);

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText("*id");

        txtCliid1.setEnabled(false);
        txtCliid1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCliid1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Numero da Entrada e Saida");

        txtOsEs1.setEnabled(false);
        txtOsEs1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtOsEs1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtCliPesquisar1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(txtCliid1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel2))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(txtOsEs1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCliPesquisar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(txtCliid1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtOsEs1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtOSEn2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setText("Autorização");

        txtOSAuto1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jLabel12))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtOSCh1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(btnOSCreate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(26, 26, 26)
                                        .addComponent(cbOsSit1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtOSAuto1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel11))
                                        .addGap(18, 18, 18)
                                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtOSSai1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtOSEn2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(19, 19, 19)))
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnOSRead1)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(btnCliIm1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnOSUpdate1)
                                    .addComponent(btnOSDelete1))))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOSCreate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtOSCh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(btnOSUpdate1)
                                .addGap(39, 39, 39)
                                .addComponent(btnOSDelete1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel14)
                                            .addComponent(cbOsSit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel18)
                                            .addComponent(txtOSAuto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(60, 60, 60)
                                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel15)
                                            .addComponent(txtOSEn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(btnCliIm1))
                                .addGap(22, 22, 22)
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtOSSai1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOSRead1)))
                .addGap(100, 100, 100))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pessoas"));
        jPanel2.setName("Pessoas"); // NOI18N

        txtCliPesquisar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtCliPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliPesquisarActionPerformed(evt);
            }
        });
        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/pesquisar.png"))); // NOI18N

        tblClientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblClientes);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("*id");

        txtCliid.setEnabled(false);
        txtCliid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCliidMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Numero da Entrada e Saida");

        txtOsEs.setEnabled(false);
        txtOsEs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtOsEsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtCliid, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(txtOsEs, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txtCliid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtOsEs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnOSCalc.setToolTipText("Remover");
        btnOSCalc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSCalcActionPerformed(evt);
            }
        });

        btnOSRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/read.png"))); // NOI18N
        btnOSRead.setToolTipText("Consultar");
        btnOSRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSReadActionPerformed(evt);
            }
        });

        btnOSUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        btnOSUpdate.setToolTipText("Alterar");
        btnOSUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSUpdateActionPerformed(evt);
            }
        });

        txtOSKmR.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Km de entrada");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("Km de saida");

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setText("Destino");

        btnCliIm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/print.png"))); // NOI18N
        btnCliIm.setToolTipText("Imprimir");
        btnCliIm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliIm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliImActionPerformed(evt);
            }
        });

        btnOSCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        btnOSCreate.setToolTipText("Adicionar");
        btnOSCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOSCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSCreateActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("*placa");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setText("Km Rodado");

        txtOSKmS.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        cbOSPl.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cbOSPl.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione uma opção", "EVA 2366 ÔNIX", "EXU 1948 ÔNIX", "FHE 1321 TOWNER", "FZH 0439 QQ", "FWF-7005 FIAT", " " }));
        cbOSPl.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbOSPlItemStateChanged(evt);
            }
        });
        cbOSPl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbOSPlMouseClicked(evt);
            }
        });
        cbOSPl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOSPlActionPerformed(evt);
            }
        });

        txtOSKmE.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        cbOsSit.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cbOsSit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione uma opção", "Na planta", "Encerrada" }));
        cbOsSit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbOsSitMouseClicked(evt);
            }
        });
        cbOsSit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOsSitActionPerformed(evt);
            }
        });

        txtOSDest.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        txtOSSai.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtOSSai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOSSaiActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Data de saida");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("*Situação");

        txtOSEn.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        btnOSDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        btnOSDelete.setToolTipText("Remover");
        btnOSDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSDeleteActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Data de entrada");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtOSEn, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txtOSSai, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel20)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtOSKmE, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel19)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(92, 92, 92)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtOSDest, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtOSKmR, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtOSKmS, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(43, 43, 43)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbOSPl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbOsSit, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnOSCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCliIm))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(btnOSRead)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnOSCalc, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnOSUpdate)
                                    .addComponent(btnOSDelete)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 292, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 283, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cbOSPl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbOsSit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtOSDest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel21)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtOSKmS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtOSKmE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtOSKmR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnOSCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCliIm)
                            .addComponent(btnOSUpdate))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnOSCalc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnOSDelete)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(btnOSRead)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtOSSai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOSEn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 234, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 234, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOSReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSReadActionPerformed
        //  chamando o metodo consultar
        consultar();
    }//GEN-LAST:event_btnOSReadActionPerformed

    private void btnOSDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSDelete1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOSDelete1ActionPerformed

    private void btnOSRead1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSRead1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOSRead1ActionPerformed

    private void txtOSSai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOSSai1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOSSai1ActionPerformed

    private void btnCliIm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliIm1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCliIm1ActionPerformed

    private void cbOsSit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbOsSit1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbOsSit1MouseClicked

    private void cbOsSit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOsSit1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbOsSit1ActionPerformed

    private void btnOSCreate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSCreate1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOSCreate1ActionPerformed

    private void btnOSUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSUpdate1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOSUpdate1ActionPerformed

    private void txtCliPesquisar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliPesquisar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliPesquisar1ActionPerformed

    private void txtCliPesquisar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisar1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliPesquisar1KeyReleased

    private void tblClientes1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientes1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblClientes1MouseClicked

    private void txtCliid1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCliid1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliid1MouseClicked

    private void txtOsEs1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtOsEs1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsEs1MouseClicked

    private void txtOSSaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOSSaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOSSaiActionPerformed

    private void cbOSPlItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbOSPlItemStateChanged
        // TODO add your handling code here:
        //getDadosForm();
        // getCbDados();
    }//GEN-LAST:event_cbOSPlItemStateChanged

    private void cbOSPlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbOSPlMouseClicked
        // TODO add your handling code here:
        km();

    }//GEN-LAST:event_cbOSPlMouseClicked

    private void cbOSPlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOSPlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbOSPlActionPerformed

    private void cbOsSitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbOsSitMouseClicked
        // chamando o metodo de data e hora
        getCbDados();
        //getDadosForm();
    }//GEN-LAST:event_cbOsSitMouseClicked

    private void cbOsSitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOsSitActionPerformed

    }//GEN-LAST:event_cbOsSitActionPerformed

    private void txtCliPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliPesquisarActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // TODO add your handling code here:
        // o evento é tipo "enquanto  for digitado"
        //chamando o metodo pesquisar cliente
        pesquisar_cliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // evento que será usado para setar os campos da tabela clicando com o mause
        //chamando o metodo setar_campos
        setar_campos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void txtCliidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCliidMouseClicked

    }//GEN-LAST:event_txtCliidMouseClicked

    private void txtOsEsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtOsEsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOsEsMouseClicked

    private void btnOSCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSCreateActionPerformed
        // chamando o metodo adicionar
        adicionar();
    }//GEN-LAST:event_btnOSCreateActionPerformed

    private void btnCliImActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliImActionPerformed
        // chamando o metodo imprimir_es
        imprimir_es();
    }//GEN-LAST:event_btnCliImActionPerformed

    private void btnOSCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSCalcActionPerformed
        calculaKm();
    }//GEN-LAST:event_btnOSCalcActionPerformed

    private void btnOSUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSUpdateActionPerformed
        // chamando o metodo alterar
        alterar();
    }//GEN-LAST:event_btnOSUpdateActionPerformed

    private void btnOSDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSDeleteActionPerformed
        // chamando o metodo remover
        remover();
    }//GEN-LAST:event_btnOSDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(TelaOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaOS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCliIm;
    private javax.swing.JButton btnCliIm1;
    private javax.swing.JButton btnOSCalc;
    private javax.swing.JButton btnOSCreate;
    private javax.swing.JButton btnOSCreate1;
    private javax.swing.JButton btnOSDelete;
    private javax.swing.JButton btnOSDelete1;
    private javax.swing.JButton btnOSRead;
    private javax.swing.JButton btnOSRead1;
    private javax.swing.JButton btnOSUpdate;
    private javax.swing.JButton btnOSUpdate1;
    private javax.swing.JComboBox<String> cbOSPl;
    private javax.swing.JComboBox<String> cbOsSit;
    private javax.swing.JComboBox<String> cbOsSit1;
    private javax.swing.JInternalFrame jInternalFrame1;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblClientes1;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtCliPesquisar1;
    private javax.swing.JTextField txtCliid;
    private javax.swing.JTextField txtCliid1;
    private javax.swing.JTextField txtOSAuto1;
    private javax.swing.JTextField txtOSCh1;
    private javax.swing.JTextField txtOSDest;
    private javax.swing.JTextField txtOSEn;
    private javax.swing.JTextField txtOSEn2;
    private javax.swing.JTextField txtOSKmE;
    private javax.swing.JTextField txtOSKmR;
    private javax.swing.JTextField txtOSKmS;
    private javax.swing.JTextField txtOSSai;
    private javax.swing.JTextField txtOSSai1;
    private javax.swing.JTextField txtOsEs;
    private javax.swing.JTextField txtOsEs1;
    // End of variables declaration//GEN-END:variables
}
