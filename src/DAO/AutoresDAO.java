package DAO;

import model.Autor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AutoresDAO {
   private Connection conexao;

   public AutoresDAO(){

       conexao = new ConnectionFactory().getConnection();
    }


   public void inserir(Autor autor){
       String sql = "insert into autores (nome, email)" + "values (?, ?)";

       try{
           //prepara a conexao
           PreparedStatement stmt = conexao.prepareStatement(sql);
           stmt.setString(1, autor.getNome());
           stmt.setString(2, autor.getEmail());

           //executando o comando
           stmt.execute();

           //fechar a conexao

           conexao.close();

       }catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }

   public List<Autor> listarTodos(){
       String sql = "select * from autores";
       List<Autor> autores = new ArrayList<>();

       try {
           //PREPARAR A CONEXÂO
           PreparedStatement stmt = conexao.prepareStatement(sql);

           //EXECUTAR
           ResultSet resultado = stmt.executeQuery();


           //Percorre os resultados

           while (resultado.next()){

               Autor autor = new Autor();
               autor.setId(resultado.getInt("id"));
               autor.setNome(resultado.getString("nome"));
               autor.setEmail(resultado.getString("email"));

               autores.add(autor);

           }

           //fecha a conexão
           conexao.close();

       }catch (SQLException e){
           throw new RuntimeException(e);

       }
       return autores;
    }

   public Autor buscaPorId (int id){
       String sql = "select * from autores where id = ?";

       try {
           //prepara a conexao
           PreparedStatement stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, id);

           //executar
           Autor autor = new Autor();
           ResultSet resultado = stmt.executeQuery();

           //populando objeto autor
           resultado.next();
           autor.setId(resultado.getInt("id"));
           autor.setNome(resultado.getString("nome"));
           autor.setEmail(resultado.getString("email"));

           //fecha conexao
           conexao.close();
           return  autor;

       }catch (SQLException e){
           System.out.println(e);
           throw new RuntimeException();

       }
   }

   public void alterar (Autor autor) {
       String sql = " update autores set nome = ?, email = ? where id =?";

       try {
           //PREPARANDO CONEXÃO
           PreparedStatement stmt = conexao.prepareStatement(sql);
           stmt.setString(1, autor.getNome());
           stmt.setString(2, autor.getEmail());
           stmt.setInt(3, autor.getId());

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


