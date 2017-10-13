package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import Modelo.ColaboradorModel;
import Modelo.EquipamentoModel;
import Modelo.TipoModel;
import Negocio.ColaboradorNegocio;
import Negocio.EquipamentoNegocio;
import Negocio.TipoNegocio;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.animation.RotateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javafx.scene.control.Button;

public class EquipamentoControle implements Initializable {
	
	private EquipamentoModel tipoEquipamento;
	
	@FXML 
	private MenuItem menuItemCadTipoEquipamento;
	
	@FXML 
	private TextField txtIdEquipamento;
	@FXML 
	private ComboBox<String> comboTipoEquipamento;
	@FXML 
	private Label lblMac;
	@FXML 
	private Label lblCnpjFornecedor;
	@FXML 
	private Label lblFornecedor;
	@FXML 
	private Label lblDataCompra;
	@FXML 
	private DatePicker dtDataCompra;
	@FXML 
	private TextField txtFornecedor;
	@FXML 
	private TextField txtCnpjFornecedor;
	@FXML 
	private Button btnCadastrarServidor;
	@FXML 
	private TextField txtFabricante;
	@FXML 
	private Label lblFabricante;
	@FXML 
	private Label lblModelo;
	@FXML 
	private TextField txtModelo;
	@FXML 
	private Button btnCadastrarEquipamento;
	@FXML 
	private TextField txtMac1;
	@FXML 
	private TextField txtMac2;
	@FXML 
	private Label lblMac1;
	@FXML 
	private Label lblMac2;
	@FXML 
	private TextField txtSenha;
	@FXML 
	private Label lblSenha;
	@FXML 
	private TextField txtNome;
	@FXML 
	private TextField txtIp;
	

	private Object main;

	
	Integer id =0;
	EquipamentoModel equipamento = new EquipamentoModel();
	ObservableList<EquipamentoModel> equipamentoView = null;
	List<EquipamentoModel> equipamentos = new ArrayList<>();
	TipoNegocio tipoNegocio=new TipoNegocio();
	List<TipoModel> listaTipo = new ArrayList <>();
	String filter = "";
	private ObservableList<String> observableListTipo;
	TipoModel tipoSelecionado = new TipoModel();
	EquipamentoNegocio equipamentoNegocio = new EquipamentoNegocio();
	
		
	@Override
	// Inicializando a Lista para carregar o comboBox
	public void initialize(URL location, ResourceBundle resources) {
	  try {
		  listaTipo = tipoNegocio.listarTipo();
		  listaTipo.forEach(tipoModel -> {
			  comboTipoEquipamento.getItems().add(tipoModel.getNomeTipoEquipamento());
		  });
		  
	  }catch ( SQLException e) {
		  e.printStackTrace();		  
		  
	  }
		
	}
	
	
	//metodo para carregar o comboBox quando selecionado
    public TipoModel selecionarTipo() {
    	List<TipoModel> tipoLista = new ArrayList<TipoModel>();
    	String nome = comboTipoEquipamento.getSelectionModel().getSelectedItem();
    	listaTipo.forEach((TipoModel tipo)-> {
    		String nomeCompleto = tipo.getNomeTipoEquipamento();
    		if(nome.equals(nomeCompleto)) {
    			tipoLista.add(tipo);
    	}
    	
    }
	);
    	tipoSelecionado = tipoLista.get(0);
    	return tipoSelecionado;
    }
	
		
	private void pegaValores(EquipamentoModel equimapentoModel) {
		equimapentoModel.setCnpjFornecedor(txtCnpjFornecedor.getText());
		equimapentoModel.setRasaoFornecedor(txtFornecedor.getText());
		equimapentoModel.setModelo(txtModelo.getText());
		equimapentoModel.setNomeEquipamento(txtNome.getText());
		equimapentoModel.setIpEquipamento(txtIp.getText());
		equimapentoModel.setDataCompra(dtDataCompra.getValue());//evento para gerar calendario
		equimapentoModel.setFabricante(txtFabricante.getText());
		equimapentoModel.setMac_1(txtMac1.getText());
		equimapentoModel.setMac_2(txtMac2.getText());
		equimapentoModel.setSenha(txtSenha.getText());
	

	}

	/*private void setaValores(EquipamentoModel equimapentoModel) {

		txtIdEquipamento.setText(String.valueOf(equimapentoModel.getIdEquipamento()));
		txtCnpjFornecedor.setText(equimapentoModel.getCnpjFornecedor());
		txtFornecedor.setText(equimapentoModel.getRasaoFornecedor());
		txtFabricante.setText(equimapentoModel.getFabricante());
		txtModelo.setText(equimapentoModel.getModelo());
		txtMac1.setText(equimapentoModel.getMac_1());
		txtMac2.setText(equimapentoModel.getMac_2());
		txtSenha.setText(equimapentoModel.getSenha());
		
	}*/

		
	@FXML
	public void cadastrarEquipamento(ActionEvent event) throws SQLException {
		String validar;
		tipoEquipamento = new EquipamentoModel();
		pegaValores(tipoEquipamento);
		tipoSelecionado = selecionarTipo();
		boolean validacao = false;
		validacao = validarCampos(); 
		if (validacao) {
		if (tipoEquipamento.getIdEquipamento() == 0) {
			validar = equipamentoNegocio.salvar(tipoEquipamento , tipoSelecionado);

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

		txtCnpjFornecedor.setText("");
		txtFornecedor.setText("");
		txtNome.setText("");
		txtIdEquipamento.setText("");
		txtSenha.setText("");
		txtMac1.setText("");
		txtMac1.setText("");
		txtFabricante.setText("");
		txtModelo.setText("");
			
	}


	public boolean validarCampos() {

		String equipamento = txtMac1.getText();

		List<Control> controls = new ArrayList<>();
		StringBuilder sb = new StringBuilder();

		if (equipamento.equals("") || equipamento == null) {
			sb.append("O nome não pode ser vazio!. \n");
			controls.add(txtMac1);
		}
		
		// colocar mais alguns tratamentos
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
