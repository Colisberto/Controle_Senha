package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;


import Modelo.ColaboradorModel;
import Negocio.ColaboradorNegocio;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class ColaboradorControle implements Initializable {
	
	
	@FXML
	private TextField txtIdColaborador;
	@FXML 
	private AnchorPane TelaCadColaborador;
	@FXML 
	private Label lblCPF;
	@FXML 
	private Label lblNome;
	@FXML 
	private TextField txtNome;
	@FXML 
	private TextField txtEmail;
	@FXML 
	private TextField txtFoneCelular;
	@FXML 
	private TextField txtFoneFixo;
	@FXML 
	private Label lblEmail;
	@FXML 
	private Label lblFoneCelular;
	@FXML 
	private Label lblFoneFixo;
	@FXML 
	private Label lblLogin;
	@FXML 
	private TextField txtLogin;
	@FXML 
	private Label lblSenha;
	@FXML 
	private TextField txtSenha;
	@FXML 
	private TextField txtCPF;
	@FXML 
	private Button btnCadastrarColaborador;
	
	private Object main;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	ColaboradorNegocio colaboradorNegocio = new ColaboradorNegocio();
	

	// Metodo que pega valores digitados pelo usuário e seta nos atributos do
	// pacote Model Classe ColaboradorModel
	private void pegaValores(ColaboradorModel colaborador) {
		
		colaborador.setCPF(txtCPF.getText());
		colaborador.setNome(txtNome.getText());
		colaborador.setEmail(txtEmail.getText());
		colaborador.setFoneFixo(txtFoneFixo.getText());
		colaborador.setFoneCelular(txtFoneCelular.getText());
		colaborador.setLogin(txtLogin.getText());
		colaborador.setSenha(txtSenha.getText());
		

	}

	private void setaValores(ColaboradorModel colaborador) {

		txtIdColaborador.setText(String.valueOf(colaborador.getIdColaborador()));
		txtCPF.setText(colaborador.getCPF());
		txtNome.setText(colaborador.getNome());
		txtEmail.setText(colaborador.getEmail());
		txtFoneFixo.setText(colaborador.getFoneFixo());
		txtFoneCelular.setText(colaborador.getFoneCelular());
		

	}

	// metodo para salvar dados no banco
	@FXML
	private void cadastrarColaborador() throws SQLException {
		String validar;
		boolean validacao = false;
		ColaboradorModel colaborador = new ColaboradorModel();
		pegaValores(colaborador);
		validacao = validarCampos();
		if (validacao) {
			if (colaborador.getIdColaborador() == 0) {
				validar = colaboradorNegocio.salvar(colaborador);
				if (validar.equals("salvo")) {
					String msg = "Salvo";
					exibeMensagem(msg);
					limpaCampos();

				} else {
					exibeMensagem(validar);
				}
			}
		}
	}

	public boolean validarCampos() {

		String cpf = txtCPF.getText();
		String nome = txtNome.getText();
		String fone = txtFoneCelular.getText();
		String login = txtLogin.getText();
		String senha = txtSenha.getText();
		String email = txtEmail.getText();
		List<Control> controls = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("");
		if (login.equals("") || login == null) {
			sb.append("O login não pode ser vazio! \n");
			controls.add(txtLogin);
		}
		if (senha.equals("") || senha == null) {
			sb.append("A senha não pode ser vazia! \n");
			controls.add(txtSenha);
		}
		if (cpf.equals("") || cpf == null) {
			sb.append("O CPF não pode ser vazio! \n");
			controls.add(txtCPF);
		}
		if (nome.equals("") || nome == null) {
			sb.append("O nome não pode ser vazio! \n");
			controls.add(txtNome);
		}
		if (fone.equals("") || fone == null) {
			sb.append("O Telefone Celular não pode ser vazio! \n");
			controls.add(txtFoneCelular);
		}
		if (email.equals("") || email == null) {
			sb.append("O Email não pode ser vazio! \n");
			controls.add(txtEmail);
		}

		if (!sb.equals("")) {
			exibeMensagem(sb.toString());
			animaCamposValidados(controls);
		}

		return sb.toString().isEmpty();

	}

	public void limpaCampos() {

		txtCPF.setText("");
		txtNome.setText("");
		txtFoneFixo.setText("");
		txtFoneCelular.setText("");
		txtEmail.setText("");
		txtLogin.setText("");
		txtSenha.setText("");
	}

	public void animaCamposValidados(List<Control> controls) {
		controls.forEach(control -> {
			RotateTransition rotateTransition = new RotateTransition(Duration.millis(60), control);
			rotateTransition.setFromAngle(-4);
			rotateTransition.setToAngle(4);
			rotateTransition.setCycleCount(8);
			rotateTransition.setAutoReverse(true);
			rotateTransition.setOnFinished((ActionEvent event1) -> {
				control.setRotate(0);
			});
			rotateTransition.play();
		});
		if (!controls.isEmpty()) {
			controls.get(0).requestFocus();
		}
	}

	public void exibeMensagem(String msg) {
		Notifications.create().title("Informação").text(String.valueOf(msg)).owner(main).hideAfter(Duration.seconds(3))
				.darkStyle().position(Pos.TOP_RIGHT).showInformation();

	}

}
