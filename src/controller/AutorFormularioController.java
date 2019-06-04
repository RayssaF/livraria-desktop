package controller;


import DAO.AutorDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Autor;

public class AutorFormularioController {

    @FXML private TextField txfNome;
    @FXML private TextField txfEmail;
    @FXML private Button btnSalvar;

    public void salvar(){
        Autor aut1 = new Autor();
        AutorDAO autorDAO = new AutorDAO();

        System.out.println("Nome: " + txfNome.getText());
        System.out.println("Email: " + txfEmail.getText());

        aut1.setNome(txfNome.getText());
        aut1.setEmail(txfEmail.getText());

        autorDAO.inserir(aut1);
        limparCampos();
    }

    public void limparCampos(){
        txfNome.setText("");
        txfEmail.setText("");
        txfNome.requestFocus();
    }
}
