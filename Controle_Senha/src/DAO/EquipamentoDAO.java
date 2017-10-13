package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import DaoUtil.ConectionFactory;
import Modelo.EquipamentoModel;
import Modelo.TipoModel;

public class EquipamentoDAO {
	
	// são obrigatorios este tres metodos
		private Connection conexao; // pegando a conexão criada em daoUtil
		private Statement stm; //
		private PreparedStatement stmt; // executador de SQL

		/*
		 * String retirada do banco Mysql de criação dos atributos prepara para
		 * receber os dados gerados pelo metodo salvar abaixo
		 */
		String sqlSalvar = "INSERT INTO equipamento (idTipo, CnpjFornecedor, rasaoFornecedor, fabricante, modelo,"
				+ "nomeEquipamento, ipEquipamento, mac_1, mac_2, dataCompra,senha)" + "VALUES " + "(?,?,?,?,?,?,?,?,?,?,?)";
		
		
		// SQL para pegar id do Eqi e ser usado na Combo
	    String sqlTipoEquipamento = "SELECT * FROM pets WHERE idTipo = ?";
		

		public EquipamentoDAO() {

			ConectionFactory factory = new ConectionFactory();
			try {
				conexao = factory.criarConexao();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// metodo pegando dados do pacote de modelo para salvar em banco String sql
		// salvar do banco
		public String salvar(TipoModel tipo, EquipamentoModel equipamento ) throws SQLException {

			String salvo = "falha";
			

			try {
				conexao.setAutoCommit(false);
				stmt = (PreparedStatement) conexao.prepareStatement(sqlSalvar);
				stmt.setInt(1, tipo.getIdTipo());
				stmt.setString(2,equipamento.getCnpjFornecedor());
				stmt.setString(3,equipamento.getRasaoFornecedor());
				stmt.setString(4, equipamento.getNomeEquipamento());
				stmt.setString(5, equipamento.getIpEquipamento());
				stmt.setString(6,equipamento.getFabricante());
				stmt.setString(7,equipamento.getModelo());
				stmt.setString(8,equipamento.getMac_1());
				stmt.setString(9,equipamento.getMac_2());
				stmt.setDate(10,Date.valueOf(equipamento.getDataCompra()));
				stmt.setString(11, equipamento.getSenha());
				stmt.executeUpdate();

				// Grava as informaÃ§Ãµes se caso de problema os dados nÃ£o sÃ£o
				// gravados
				conexao.commit();
				salvo = "salvo";

			} catch (SQLException e) {
				if (conexao != null) {
					try {
						System.err.print("Rollback efetuado na transação ");
						conexao.rollback();
					} catch (SQLException e2) {
						System.err.print("Erro na transação!" + e2);
						salvo = "\"Erro na transação!\"+e2";
					}
				}
				System.out.println("Erro na execução:" + e.getMessage());
			} finally {
				if (stmt != null) {
					stmt.close();
				}
				conexao.setAutoCommit(true);
			}

			return salvo;
		}
		
		
	    
	    public List<EquipamentoModel> listarEquipamento() {
	        List<EquipamentoModel> listaEquipamentos = new ArrayList<EquipamentoModel>();
	        ResultSet res = null;

	        try {

	            stm = (Statement) conexao.createStatement();
	            res = stm.executeQuery("Select *from equipamento");
	           
	            while (res.next()){
	            	EquipamentoModel equipamentos = new EquipamentoModel();
	            	 
	                equipamentos.setIdEquipamento(res.getInt("idEquipamento"));
	                equipamentos.setCnpjFornecedor(res.getString("cnpjFornecedor"));
	                equipamentos.setRasaoFornecedor(res.getString("rasaoFornecedor"));
	                equipamentos.setNomeEquipamento(res.getString("nomeEquipamento"));
	                equipamentos.setIpEquipamento(res.getString("ipEquipamento"));
	                equipamentos.setFabricante(res.getString("fabricante"));
	                equipamentos.setModelo(res.getString("modelo"));
	                equipamentos.setMac_1(res.getString("mac_1"));
	                equipamentos.setMac_2(res.getString("mac_2"));
	                Date dataCompra = res.getDate("dataCompra");
	                equipamentos.setDataCompra(dataCompra.toLocalDate());
	                equipamentos.setSenha(res.getString("senha"));
	                
	                

	                listaEquipamentos.add(equipamentos);
	            }
	        }
	        catch (SQLException e){
	            System.out.println("Erro na consulta1:" + e.getMessage());
	        }
	        return listaEquipamentos;
	    }
		


}
