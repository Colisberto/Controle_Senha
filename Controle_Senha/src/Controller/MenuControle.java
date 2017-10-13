package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class MenuControle implements Initializable{
	
	@FXML
	private Pane paneInicial;
	
	private Object main;

	@FXML 
	private Button btnEntrar;

	@FXML 
	private Button btnSair;

	@FXML 
	private Menu MenuCadastros;

	@FXML 
	private MenuItem menuItemCadColaborador;
	@FXML 
	private MenuItem menuItemCadTipoEquipamento;

	@FXML 
	private MenuItem menuItemCadEquipamento;
	
	@FXML 
	private MenuItem menuItemConsEquipamento;

	@FXML 
	private MenuItem menuItemRelEquipamento;

	@FXML
	private Menu menuConsultas;

	@FXML 
	private Menu meuRelatorios;

	@FXML 
	private Label lblLogin;

	@FXML 
	private Label lblSenha;

	@FXML 
	private TextField txtLogin;

	@FXML 
	private TextField txtSenha;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	// metodo para abrir tela de cadastro de parceiro ---FUNCIONANDO
			@FXML
			public void abrirCadastrarColaborador(ActionEvent event) throws IOException {

				URL arquivoFXML;
				arquivoFXML = getClass().getResource("/Visao/TelaCadastrarColaborador.fxml");
				Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
				paneInicial.getChildren().clear();
				paneInicial.getChildren().add(fxmlParent);

			}
			
			// metodo para abrir tela de cadastro de parceiro ---FUNCIONANDO
			@FXML
			public void abrirCadastrarEquipamento(ActionEvent event) throws IOException {

				URL arquivoFXML;
				arquivoFXML = getClass().getResource("/Visao/TelaCadastrarEquipamento.fxml");
				Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
				paneInicial.getChildren().clear();
				paneInicial.getChildren().add(fxmlParent);

			}
			
			
			
			
			@FXML
			public void abrirTelaCadTipoEquipamento(ActionEvent event) throws IOException {

		       URL arquivoFXML;
			   arquivoFXML = getClass().getResource("/Visao/TelaTipoEquipamento.fxml");
			   Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
			   paneInicial.getChildren().clear();
			   paneInicial.getChildren().add(fxmlParent);

						}
			
			@FXML
			public void abrirTelaConsultaSenha(ActionEvent event) throws IOException {

		       URL arquivoFXML;
			   arquivoFXML = getClass().getResource("/Visao/TelaConsultaEquipamento.fxml");
		       Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
			   paneInicial.getChildren().clear();
			   paneInicial.getChildren().add(fxmlParent);

						}
			


}
