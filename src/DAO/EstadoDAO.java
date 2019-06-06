package DAO;

import model.Estado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EstadoDAO {

    private Connection conexao;

    public EstadoDAO(){

        conexao = new ConnectionFactory().getConnection();
    }
    public void inserir(Estado estado){
        String sql = "insert into Estado (uf)" + "values (?)";

        try{
            //prepara a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1,estado.getUf());

            //inicia a conexão
            stmt.execute();

            //fecha conexão
            stmt.close();

        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException(e);

        }
    }



}
