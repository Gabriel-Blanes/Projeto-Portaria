
package br.com.infox.dal;
import java.sql.*;

 

public class ModuloConexao {
    //metodo responsavel por estabelecer a conexao como banco de dados
    public static Connection conector()
    {
    java.sql.Connection conecxao = null;
     // a limnha a baixo "chama" o driver       
    String driver ="com.mysql.jdbc.Driver";
    //armazenando imforamações referente ao banco de dados
     String url = "jdbc:mysql://10.10.3.15:3306/portaria_03";
     String user = "mklogistica";
     String password= "root";
     //estabelecer a conexão com o banco de dados
        try {
            Class.forName(driver);
            conecxao = DriverManager.getConnection(url,user,password);
            return conecxao;
        } catch (ClassNotFoundException | SQLException e) {
            //a linha a baixo serve para esclarecer qual que é o erro ocorrido na conexão
            System.out.println(e);
                    
            return null;
        }
         
    }
}
