package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection conectar(){
        try{
            String url ="jdbc:mysql://localhost:3306/sistemaAcademico";
            String user = "root";
            String senha = "";

            return DriverManager.getConnection(url, user, senha);
        }catch (SQLException e){
            throw new RuntimeException("Erro de Conexão");
        }
    }
}
