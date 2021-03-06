package DAO;

import model.Contatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatosDAO {

    private Connection conexao;

    public ContatosDAO(){

        conexao = new ConnectionFactory().getConnection();
    }


    public void inserir(Contatos contatos){
        String sql = "insert into contatos (nome, contato)" + "values (?, ?)";

        try{
            //prepara a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, contatos.getNome() );
            stmt.setString(2, contatos.getContato());

            //executando o comando
            stmt.execute();

            //fechar a conexao

            conexao.close();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Contatos> listarTodos(){
        String sql = "select * from contatos";
        List<Contatos> contatos = new ArrayList<>();

        try {
            //PREPARAR A CONEXÂO
            PreparedStatement stmt = conexao.prepareStatement(sql);

            //EXECUTAR
            ResultSet resultado = stmt.executeQuery();


            //Percorre os resultados

            while (resultado.next()){

                Contatos contatos1 = new Contatos();
                contatos1.setId(resultado.getInt("id"));
                contatos1.setNome(resultado.getString("nome"));
                contatos1.setContato(resultado.getString("contato"));

                contatos.add(contatos1);

            }

            //fecha a conexão
            conexao.close();

        }catch (SQLException e){
            throw new RuntimeException();

        }
        return contatos;
    }

    public Contatos buscaPorId (int id){
        String sql = "select * from contatos where id = ?";

        try {
            //prepara a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            //executar
            Contatos contatos = new Contatos();
            ResultSet resultado = stmt.executeQuery();

            //populando objeto autor
            resultado.next();
            contatos.setId(resultado.getInt("id"));
            contatos.setNome(resultado.getString("nome"));
            contatos.setContato(resultado.getString("contato"));

            //fecha conexao
            conexao.close();
            return  contatos;

        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException();

        }
    }

    public void alterar (Contatos contatos) {
        String sql = " update contatos set nome = ?, contato = ? where id =?";

        try {
            //PREPARANDO CONEXÃO
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, contatos.getNome());
            stmt.setString(2, contatos.getContato());
            stmt.setInt(3, contatos.getId());

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
        String sql = "delete from contato where id = ?";

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
