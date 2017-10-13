package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import Modelo.EquipamentoModel;
import Modelo.UsuarioSenhaModel;
import Negocio.UsuarioSenhaNegocio;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javafx.scene.control.Label;

public class UserSenhaControle implements Initializable{

	@FXML 
	private Button btnCadastrar;
	@FXML 
	private ComboBox<String> comboEquipamento;
	@FXML 
	private TextField txtUsuario;
	@FXML 
	private Label lblUsuario;
	@FXML 
	private Label lblSenha;
	@FXML 
	private TextField txtSenha;
	@FXML 
	private Button btnAlterar;
	@FXML 
	private ComboBox<String> comboTipo;
	
    private UsuarioSenhaModel autenticacao;
	
	private Object main;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	private void pegaValores(UsuarioSenhaModel dados) {
		dados.setUsuario(txtUsuario.getText());
		dados.setSenha(txtSenha.getText());
		
	}
	
	private void setaValores(UsuarioSenhaModel dados) {

		txtUsuario.setText(dados.getUsuario());
		txtSenha.setText(dados.getSenha());
		
	}
	
	UsuarioSenhaNegocio usuarioSenha = new UsuarioSenhaNegocio();

	@FXML 
	public void cadastrarUsuarioSenha(ActionEvent event) throws SQLException{
		String validar;
		boolean validacao = false;
		autenticacao = new UsuarioSenhaModel();
		pegaValores(autenticacao);
		validacao = validarCampos(); 
		if (validacao) {
		if (autenticacao.getIdUserSenha() == 0) {
			validar = usuarioSenha.salvar(autenticacao);

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

	public void limpaCampos() {

		txtUsuario.setText("");
		txtSenha.setText("");
		
	}
	
	public boolean validarCampos() {

		String usuarioSenha = txtUsuario.getText();

		List<Control> controls = new ArrayList<>();
		StringBuilder sb = new StringBuilder();

		if (usuarioSenha.equals("") || usuarioSenha == null) {
			sb.append("O nome não pode ser vazio!. \n");
			controls.add(txtUsuario);
		}
		if (!sb.equals("")) {
			exibeMensagem(sb.toString());
			animaCamposValidados(controls);
		}

		return sb.toString().isEmpty();

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
