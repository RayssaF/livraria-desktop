package DAO;

import model.Livros;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LivrosDAO {

    private Connection conexao;

    public LivrosDAO(){

        conexao = new ConnectionFactory().getConnection();
    }


    public void inserir(Livros livros){
        String sql = "insert into livros (titulo, data_lancamento, quantidade, preco, editora_id )" + "values (?, ?, ?, ?, ?)";

        try{
            //prepara a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, livros.getTitulo());
            stmt.setDate(2, Date.valueOf(livros.getData_lancamento()));
            stmt.setInt(3, livros.getQuantidade());
            stmt.setFloat(4, livros.getPreco());
            stmt.setInt(5, livros.getEditora_id());

            //executando o comando
            stmt.execute();

            //fechar a conexao

            conexao.close();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Livros> listarTodos(){
        String sql = "select * from livros";
        List<Livros> livros = new ArrayList<>();

        try {
            //PREPARAR A CONEXÂO
            PreparedStatement stmt = conexao.prepareStatement(sql);

            //EXECUTAR
            ResultSet resultado = stmt.executeQuery();


            //Percorre os resultados

            while (resultado.next()){

                Livros livros1 = new Livros();
                livros1.setTitulo(resultado.getString("titulo"));
                livros1.setData_lancamento(LocalDate.parse(resultado.getDate("data_lancamento").toString()));
                livros1.setPreco(resultado.getFloat("preco"));
                livros1.setQuantidade(resultado.getInt("quantidade"));
                livros1.setId(resultado.getInt("id"));
                livros1.setEditora_id(resultado.getInt("editora_id"));

                livros.add(livros1);

            }

            //fecha a conexão
            conexao.close();

        }catch (SQLException e){
            throw new RuntimeException(e);

        }
        return livros;
    }

    public Livros buscaPorId (int id){
        String sql = "select * from livros where id = ?";

        try {
            //prepara a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            //executar
            Livros livros = new Livros();
            ResultSet resultado = stmt.executeQuery();

            //populando objeto autor
            resultado.next();
            livros.setId(resultado.getInt("id"));
            livros.setTitulo(resultado.getString("titulo"));
            livros.setData_lancamento(LocalDate.parse(resultado.getDate("data_lancamento").toString()));
            livros.setQuantidade(resultado.getInt("quantidade"));
            livros.setPreco(resultado.getFloat("preco"));
            livros.setEditora_id(resultado.getInt("editoras_id"));

            //fecha conexao
            conexao.close();
            return  livros;

        }catch (SQLException e){
            System.out.println(e);
            throw new RuntimeException();

        }
    }

    public void alterar (Livros livros) {
        String sql = " update editoras set titulo = ?, data_lancamento = ?, quantidade = ?, preco = ?, editoras_id = ?  where id =?";


        try {
            //PREPARANDO CONEXÃO
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, livros.getId());
            stmt.setString(2,livros.getTitulo());
            stmt.setDate(3,Date.valueOf(livros.getData_lancamento()));
            stmt.setInt(4,livros.getQuantidade());
            stmt.setFloat(5,livros.getPreco());
            stmt.setInt(6,livros.getEditora_id());



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
        String sql = "delete from livros where id = ?";

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
