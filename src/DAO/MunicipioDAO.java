package DAO;

import model.Municipio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MunicipioDAO {

    private Connection conexao;

    public MunicipioDAO(){

        conexao = new ConnectionFactory().getConnection();
    }


    public void inserir(Municipio municipio){
        String sql = "insert into municipio (nome, estado_id)" + "values (?, ?)";

        try{
            //prepara a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, municipio.getNome());
            stmt.setInt(2, municipio.getEstado_id());

            //executando o comando
            stmt.execute();

            //fechar a conexao

            conexao.close();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Municipio> listarTodos(){
        String sql = "select * from autores";
        List<Municipio> municipio = new ArrayList<>();

        try {
            //PREPARAR A CONEXÂO
            PreparedStatement stmt = conexao.prepareStatement(sql);

            //EXECUTAR
            ResultSet resultado = stmt.executeQuery();


            //Percorre os resultados

            while (resultado.next()){

                Municipio municipio1 = new Municipio();
                municipio1.setId(resultado.getInt("id"));
                municipio1.setNome(resultado.getString("nome"));
                municipio1.setEstado_id(resultado.getInt("estado_id"));

                municipio.add(municipio1);

            }

            //fecha a conexão
            conexao.close();

        }catch (SQLException e){
            throw new RuntimeException(e);

        }
        return municipio;
    }

    public Municipio buscaPorId (int id){
        String sql = "select * from municipio where id = ?";

        try {
            //prepara a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            //executar
            Municipio municipio = new Municipio();
            ResultSet resultado = stmt.executeQuery();

            //populando objeto autor
            resultado.next();
            municipio.setId(resultado.getInt("id"));
            municipio.setNome(resultado.getString("nome"));
            municipio.setEstado_id(resultado.getInt("estado_municipio"));

            //fecha conexao
            conexao.close();
            return  municipio;

        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException();

        }
    }

    public void alterar (Municipio municipio) {
        String sql = " update municipio set nome = ?, estado_id = ? where id =?";

        try {
            //PREPARANDO CONEXÃO
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, municipio.getNome());
            stmt.setInt(2, municipio.getEstado_id());
            stmt.setInt(3, municipio.getId());

            // EXECUTAR
            stmt.execute();

            //FECHAR CONEXÃO
            conexao.close();


        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException();
        }
    }

    public void deletar (int id){
        String sql = "delete from autores where id = ?";

        try{
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.execute();

            conexao.close();

        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException();



        }
    }









}
