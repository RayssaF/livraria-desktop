package DAO;

import model.Livros_autor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Livros_autorDAO {
    private Connection conexao;

    public Livros_autorDAO(){

        conexao = new ConnectionFactory().getConnection();
    }


    public void inserir(Livros_autor livros_autor){
        String sql = "insert into livro_autor (livro_id, autor_id)" + "values (?, ?)";

        try{
            //prepara a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, livros_autor.getLivro_id());
            stmt.setInt(2, livros_autor.getAutor_id());

            //executando o comando
            stmt.execute();

            //fechar a conexao

            conexao.close();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Livros_autor> listarTodos(){
        String sql = "select * from livros_autor";
        List<Livros_autor> livros_autor = new ArrayList<>();

        try {
            //PREPARAR A CONEXÂO
            PreparedStatement stmt = conexao.prepareStatement(sql);

            //EXECUTAR
            ResultSet resultado = stmt.executeQuery();


            //Percorre os resultados

            while (resultado.next()){

                Livros_autor lAutor = new Livros_autor();
                lAutor.setId(resultado.getInt("id"));
                lAutor.setLivro_id(resultado.getInt("livro_id"));
                lAutor.setAutor_id(resultado.getInt("autor_id"));

                livros_autor.add(lAutor);

            }

            //fecha a conexão
            conexao.close();

        }catch (SQLException e){
            throw new RuntimeException(e);

        }
        return livros_autor;
    }

    public Livros_autor buscaPorId (int id){
        String sql = "select * from livros_autor where id = ?";

        try {
            //prepara a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            //executar
            Livros_autor lautor1 = new Livros_autor();
            ResultSet resultado = stmt.executeQuery();

            //populando objeto autor
            resultado.next();
            lautor1.setId(resultado.getInt("id"));
            lautor1.setId(resultado.getInt("livros_id"));
            lautor1.setId(resultado.getInt("autor_id"));

            //fecha conexao
            conexao.close();
            return  lautor1;

        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException();

        }
    }

    public void alterar (Livros_autor livros_autor) {
        String sql = " update livros_autor set livro_id = ?, autor_id = ? where id =?";

        try {
            //PREPARANDO CONEXÃO
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, livros_autor.getLivro_id());
            stmt.setInt(2, livros_autor.getAutor_id());
            stmt.setInt(3, livros_autor.getId());

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
        String sql = "delete from livros_autor where id = ?";

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
