package Negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.TipoDAO;
import Modelo.EquipamentoModel;

import Modelo.TipoModel;

public class TipoNegocio {
	
	private EquipamentoModel equipamento;	
	
	
	TipoDAO tipoDAO = new TipoDAO();
	
	public String salvar(TipoModel tipo)throws SQLException {
    	String salvo ="Falha";
    	TipoDAO  tipoDao = new TipoDAO();
    	
        StringBuilder sb = new StringBuilder();  
    
        if (sb.toString().isEmpty()) {
            //aqui eu gravo...
        	salvo = tipoDao.salvar(tipo);// pegando o objeto DEPARTAMENTO da camada departamentoDAO
        } else {
            sb.append(salvo);
            return sb.toString();
        }
        sb.append(salvo);
        return sb.toString();
    }
	
	public void salvarTipo(TipoModel tipoModelo) throws SQLException{
		tipoDAO.salvar(tipoModelo);
		
	}
	
	public List<TipoModel> listarTipo()throws SQLException{
    	TipoDAO  tipoDao = new TipoDAO();
    	List<TipoModel> tipos = new ArrayList<TipoModel>();
    	tipos = tipoDao.listarTipos();
    	return tipos;
    	
    }

	

}
