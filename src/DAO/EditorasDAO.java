package DAO;

import model.Editoras;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditorasDAO {

    private Connection conexao;

    public EditorasDAO(){

        conexao = new ConnectionFactory().getConnection();
    }


    public void inserir(Editoras editoras){
        String sql = "insert into editoras (nome, site, endereco, bairro, telefone )" + "values (?, ?, ?, ?, ?)";

        try{
            //prepara a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, editoras.getNome());
            stmt.setString(2, editoras.getSite());
            stmt.setString(3, editoras.getEndereco());
            stmt.setString(4, editoras.getBairro());
            stmt.setInt(5, editoras.getTelefone());

            //executando o comando
            stmt.execute();

            //fechar a conexao

            conexao.close();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Editoras> listarTodos(){
        String sql = "select * from editoras";
        List<Editoras> editoras = new ArrayList<>();

        try {
            //PREPARAR A CONEXÂO
            PreparedStatement stmt = conexao.prepareStatement(sql);

            //EXECUTAR
            ResultSet resultado = stmt.executeQuery();


            //Percorre os resultados

            while (resultado.next()){

                Editoras editoras1 = new Editoras();
                editoras1.setNome(resultado.getString("nome"));
                editoras1.setSite(resultado.getString("site"));
                editoras1.setEndereco(resultado.getString("endereco"));
                editoras1.setBairro(resultado.getString("bairro"));
                editoras1.setId(resultado.getInt("id"));
                editoras1.setMunicipio_id(resultado.getInt("municipio_id"));
                editoras1.setTelefone(resultado.getInt("telefone"));

               editoras.add(editoras1);

            }

            //fecha a conexão
            conexao.close();

        }catch (SQLException e){
            throw new RuntimeException(e);

        }
        return editoras;
    }

    public Editoras buscaPorId (int id){
        String sql = "select * from editoras where id = ?";

        try {
            //prepara a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            //executar
            Editoras editoras = new Editoras();
            ResultSet resultado = stmt.executeQuery();

            //populando objeto autor
            resultado.next();
            editoras.setId(resultado.getInt("id"));
            editoras.setNome(resultado.getString("nome"));
            editoras.setSite(resultado.getString("site"));
            editoras.setEndereco(resultado.getString("endereco"));
            editoras.setBairro(resultado.getString("bairro"));
            editoras.setTelefone(resultado.getInt("telefone"));

            //fecha conexao
            conexao.close();
            return  editoras;

        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException();

        }
    }

    public void alterar (Editoras editoras) {
        String sql = " update editoras set nome = ?, site = ?, endereco = ?, bairro = ?, telefone = ?  where id =?";


        try {
            //PREPARANDO CONEXÃO
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, editoras.getId());
            stmt.setString(2,editoras.getNome());
            stmt.setString(3,editoras.getSite());
            stmt.setString(4,editoras.getEndereco());
            stmt.setString(5,editoras.getBairro());
            stmt.setInt(6,editoras.getTelefone());


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
        String sql = "delete from editoras where id = ?";

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
