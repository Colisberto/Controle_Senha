package Controller;


import Modelo.EquipamentoModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class ConsultaSenhaControle {
	

	@FXML 
	private TableView<EquipamentoModel> tableDadosEquipamento;
	@FXML 
	private TableColumn<EquipamentoModel, String> nomeEquipamento;
	@FXML 
	private TableColumn<EquipamentoModel, String> senhaAcesso;
	@FXML 
	private TableColumn<EquipamentoModel, String> enderecoIp;
	@FXML 
	private TableColumn<EquipamentoModel, String> mac_1;
	@FXML 
	private TableColumn<EquipamentoModel, String> mac_2;
	
	EquipamentoModel Equipamento = new EquipamentoModel();

}
