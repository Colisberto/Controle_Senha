package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import Modelo.TipoModel;
import Negocio.TipoNegocio;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class TipoEquipamentoControle implements Initializable {
	
	@FXML 
	private AnchorPane TelaCadTipoEquipamento;
	
	@FXML 
	private Label lblTipoEquipamento;
	@FXML 
	private TextField txtTipoEquipamento;
	@FXML 
	private TextField txtIdTipo;
	@FXML 
	private Button btnCadastrar;
	@FXML 
	private Button btnCancelar;
	@FXML 
	
	private TipoModel tipo;
	
	private Object main;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	private void pegaValores(TipoModel tipo) {

		
		tipo.setNomeTipoEquipamento(txtTipoEquipamento.getText());
		

	}

	private void setaValores(TipoModel tipo) {

		txtIdTipo.setText(String.valueOf(tipo.getIdTipo()));
		txtTipoEquipamento.setText(tipo.getNomeTipoEquipamento());
		
		

	}

	TipoNegocio tipoNegocio = new TipoNegocio();

	

	

	@FXML
	public void cadastrarTipo(ActionEvent event) throws SQLException {
		String validar;
		boolean validacao = false;
		tipo = new TipoModel();
		pegaValores(tipo);
		validacao = validarCampos(); 
		if (validacao) {
		if (tipo.getIdTipo() == 0) {
			validar = tipoNegocio.salvar(tipo);

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

		txtTipoEquipamento.setText("");
		
		
		
	}


	public boolean validarCampos() {

		String tipo = txtTipoEquipamento.getText();

		List<Control> controls = new ArrayList<>();
		StringBuilder sb = new StringBuilder();

		if (tipo.equals("") || tipo == null) {
			sb.append("O nome não pode ser vazio!. \n");
			controls.add(txtTipoEquipamento);
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
