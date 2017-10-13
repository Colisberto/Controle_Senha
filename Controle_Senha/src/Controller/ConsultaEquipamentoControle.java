package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Modelo.EquipamentoModel;
import Modelo.TipoModel;
import Negocio.EquipamentoNegocio;
import Negocio.TipoNegocio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;

public class ConsultaEquipamentoControle  implements Initializable {
	
	@FXML 
	TableView<EquipamentoModel> tableDadosEquipamento;
	@FXML 
	TableColumn<EquipamentoModel, String> clnNomeEquipamento;
	@FXML 
	TableColumn<EquipamentoModel, String> clnEnderecoIp;
	@FXML 
	TableColumn<EquipamentoModel, String> clnMac_1;
	@FXML 
	TableColumn<EquipamentoModel, String> clnMac_2;
	@FXML 
	TableColumn<EquipamentoModel, String> clnSenhaAcesso;
	@FXML 
	ComboBox<String> ComboTipo;
	
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
	@FXML Button btnListar;
	
		
	// metodo para popular o combBox
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			  listaTipo = tipoNegocio.listarTipo();
			  listaTipo.forEach(tipoModel -> {
				  ComboTipo.getItems().add(tipoModel.getNomeTipoEquipamento());
			  });
			  
		  }catch ( SQLException e) {
			  e.printStackTrace();		  
			  
		  }
			
		}

	//metodo para carregar o comboBox quando selecionado
    public TipoModel selecionarTipo() {
    	List<TipoModel> tipoLista = new ArrayList<TipoModel>();
    	String nome = ComboTipo.getSelectionModel().getSelectedItem();
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
    

    
    public  void populaView(List<EquipamentoModel> equipamentos){
        clnNomeEquipamento.setCellValueFactory(new PropertyValueFactory<EquipamentoModel, String>("nomeEquipamento"));
        clnEnderecoIp.setCellValueFactory(new PropertyValueFactory<EquipamentoModel, String>("ipEquipamento"));
        clnMac_1.setCellValueFactory(new PropertyValueFactory<EquipamentoModel, String>("mac_1"));
        clnMac_2.setCellValueFactory(new PropertyValueFactory<EquipamentoModel, String>("mac_2"));
        clnSenhaAcesso.setCellValueFactory(new PropertyValueFactory<EquipamentoModel, String>("senha"));
        equipamentoView = FXCollections.observableArrayList(equipamentos);
        tableDadosEquipamento.getItems().removeAll();
        tableDadosEquipamento.setItems( equipamentoView);
    }
    
    public List<EquipamentoModel> listaEquipamentos() throws SQLException{
    	equipamentos = equipamentoNegocio.listarEquipamento();
    	return equipamentos;
    }

	public void listarEquipamentos() {
		EquipamentoModel equipamento = new EquipamentoModel();
		selecionarTipo();
		tipoSelecionado.clear();
		tipoSelecionado = tipoNegocio.listarTipo(equipamento);
		ComboTipo.getItems().clear();
		if (!tipoSelecionado.isEmpty()) {
			tipoSelecionado.forEach(p -> {
            comboPet.getItems().add(p.getNome());
			});
    }
	}
	
}
	
	



