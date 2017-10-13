package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import DaoUtil.ConectionFactory;
import Modelo.TipoModel;

public class TipoDAO {
	
	//são obrigatorios este tres metodos
	private Connection conexao; // pegando a conexão criada em daoUtil
	private Statement stmt; //
	private PreparedStatement stm; // executador de SQL
	
	/*String retirada do banco Mysql de criação dos atributos prepara para receber os dados gerados
	   pelo metodo salvar abaixo*/
	String sqlSalvar = "INSERT INTO tipoequipamento (nomeTipoEquipamento)" +
			"VALUES " + "(?)";


public TipoDAO() {

	ConectionFactory factory = new ConectionFactory();
		try {
			conexao = (Connection) factory.criarConexao();
			} catch (SQLException e) {
				e.printStackTrace();
			}
}

// metodo pegando dados do pacote de modelo para salvar em banco String sql salvar do banco
		public String salvar(TipoModel tipo)throws SQLException{
		    String salvo = "Falso"	;
		
			try{
				
			 conexao.setAutoCommit(false);	
			 stm = (PreparedStatement) conexao.prepareStatement(sqlSalvar);
			 //stm.setInt(1, tipo.getIdTipo());
			 stm.setString(1, tipo.getNomeTipoEquipamento());
			 stm.executeUpdate();
			 conexao.commit();
			 salvo = "salvo";
			 
		}catch (Exception e){//testando a conexão se é nula ou não
			
			if (conexao != null){
				try{
					System.out.println(" Efetuando Rollback");
					conexao.rollback();
					salvo = "erro ao gravar " + e.getMessage();
				}catch (SQLException e1){
					System.out.println(" Erro na Conexão" + e1.getMessage());
					salvo = "e1";
					
				}  
			}
				
			}
			return salvo;
		} 
		
		// gerando lista de departamentos listar departamento
		public List<TipoModel> listarTipos(){
			List<TipoModel>  tipos = new ArrayList<TipoModel>(); // criando a lista
			ResultSet res = null;
			
			try{
				stmt = (Statement) conexao.createStatement(); // conectando ao banco para pegar dados da tabela
				res = stmt.executeQuery("Select *from tipoEquipamento"); // select para buscar dados da tabela departamento
				while (res.next()){ // laço de repetição pra pegar dados contidos na tabela
					TipoModel tipo = new TipoModel();
					// pegado os atributos idDepartamento  e Nome do departamento
					tipo.setIdTipo(res.getInt("idTipo"));
					tipo.setNomeTipoEquipamento(res.getString("nomeTipoEquipamento"));
					tipos.add(tipo);
					
				}
			}catch (SQLException e){
				System.out.println("erro na consulta" + e.getMessage());
				
			}
			
			return tipos;
		}

}
