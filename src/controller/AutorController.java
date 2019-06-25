package controller;


import DAO.AutoresDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Autor;

import java.util.ArrayList;
import java.util.List;

public class AutorController {

    @FXML private TextField txfNome;
    @FXML private TextField txfEmail;
    @FXML private Button btnSalvar;
    @FXML private TableView<Autor> tableViewAutor;
    @FXML private TableColumn<Autor, String> tableColumnAutorNome;
    @FXML private TableColumn<Autor, String> tableColumnAutorEmail;

  private List<Autor> listAutor = new ArrayList();

  private ObservableList<Autor> observableListAutor;


    public void salvar(){
        Autor aut1 = new Autor();
        AutoresDAO autorDAO = new AutoresDAO();

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





    public void carregarTableViewAutor(){

        Autor autor1 = new Autor();
        Autor autor2 = new Autor();

        listAutor.add(autor1);
        listAutor.add(autor2);

        observableListAutor = FXCollections.observableArrayList(listAutor);


        tableViewAutor.setItems(observableListAutor);


    }







}
