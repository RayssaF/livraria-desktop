package DAO;

import model.Estado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO {

    private Connection conexao;

    public EstadoDAO(){

        conexao = new ConnectionFactory().getConnection();
    }

    public void inserirEstado(Estado estado){
        String sql = "insert into Estado (uf)" + "values (?)";

        try{
            //prepara a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1,estado.getUf());

            //inicia a conexão
            stmt.execute();

            //fecha conexão
            conexao.close();

        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException(e);

        }
    }

    public List<Estado> listarTodosEstados(){
        String sql = " select * from estado";
        List<Estado> estados =  new ArrayList<>();

        try {
            //PREPARAR A CONEXÂO
            PreparedStatement stmt = conexao.prepareStatement(sql);

            //EXECUTAR

            ResultSet resultado = stmt.executeQuery();

            //PERCORRE OS RESULTADOS
             while (resultado.next()){
                 Estado estado1 = new Estado();
                 estado1.setUf(resultado.getString("uf"));
                 estado1.setId(resultado.getInt("id"));

                 estados.add(estado1);
             }

            //FECHA CONEXÃO
            conexao.close();



        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException();
        }

        return estados;
    }

     public  Estado buscaIdEstado (int id){
        String sql = "select * from estado where id =?";

        try {

            //PREPARAR A CONEXÂO
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1,id);

            //EXECUTAR
            Estado estado = new Estado();
            ResultSet resultado = stmt.executeQuery();

            //POPULANDO OBJETO AUTOR
            resultado.next();
            estado.setId(resultado.getInt("id"));
            estado.setUf(resultado.getString("uf"));

            //FECHA CONEXÃO
            conexao.close();

            return estado;


        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException(e);

        }
     }

        public void alterarEstado (Estado estado) {
        String sql = "update estado set uf = ? where id=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, estado.getUf());
            stmt.setInt(2, estado.getId());

            stmt.execute();

            conexao.close();


        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public void deletarEstado (int id){
        String sql = "Select from estado where id = ?";

        try {
            //PREPARA CONEXÃO
            PreparedStatement stmt =  conexao.prepareStatement(sql);
            stmt.setInt(1,id);

            //EXECUTA A CONEXÃO
            stmt.execute();

            //FECHA A CONEXAO
            conexao.close();


        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException(e);
        }


    }

}
