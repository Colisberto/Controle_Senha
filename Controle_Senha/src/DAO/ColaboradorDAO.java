package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import DaoUtil.ConectionFactory;
import Modelo.ColaboradorModel;
import Modelo.EquipamentoModel;


public class ColaboradorDAO {
	
	// são obrigatorios este tres metodos
	private Connection conexao; // pegando a conexão criada em daoUtil
	private Statement stm; //
	private PreparedStatement stmt; // executador de SQL

	/*
	 * String retirada do banco Mysql de criação dos atributos prepara para
	 * receber os dados gerados pelo metodo salvar abaixo
	 */
	String sqlSalvar = "INSERT INTO colaboradores (idColaborador, CPF, nome, Email, foneFixo,foneCelular, login,"
			+ "senha)" + "VALUES " + "(?,?,?,?,?,?,?,?)";
	
	/*private int idColaborador;
	private String CPF;
	private String nome;
	private String Email;
	private String foneFixo;
	private String foneCelular;
	private String login;
	private String senha;*/

	public ColaboradorDAO() {

		ConectionFactory factory = new ConectionFactory();
		try {
			conexao = factory.criarConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// metodo pegando dados do pacote de modelo para salvar em banco String sql
	// salvar do banco
	public String salvar(ColaboradorModel colaborador) throws SQLException {

		String salvo = "falha";

		try {
			conexao.setAutoCommit(false);
			stmt = (PreparedStatement) conexao.prepareStatement(sqlSalvar);
			stmt.setInt(1, colaborador.getIdColaborador());
			stmt.setString(2,colaborador.getCPF());
			stmt.setString(3,colaborador.getNome());
			stmt.setString(4,colaborador.getEmail());
			stmt.setString(5,colaborador.getFoneFixo());
			stmt.setString(6,colaborador.getFoneCelular());
			stmt.setString(7,colaborador.getLogin());
			stmt.setString(8,colaborador.getSenha());
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
	
	/*public List<ServidorModel> listarServidores(){
		List<ServidorModel> servidor = new ArrayList<ServidorModel>(); // criando a lista
		ResultSet res = null;
		
		try{
			stm = (Statement) conexao.createStatement(); // conectando ao banco para pegar dados da tabela
			res = stm.executeQuery("Select *from servidores"); // select para buscar dados da tabela departamento
			while (res.next()){ // laço de repetição pra pegar dados contidos na tabela
				ServidorModel servidores = new ServidorModel();
				// pegado os atributos idDepartamento  e Nome do departamento
				servidores.setIdServidores(res.getInt("idServidores"));  // 
				servidores.setCnpjFornecedor(res.getString("cnpjFornecedor"));
				servidores.setRasaoFornecedor(res.getString ("rasaoFornecedor"));
				servidores.setFabricante(res.getString ("fabricante"));
				servidores.setModelo(res.getString ("modelo"));
				servidores.setMac(res.getString ("mac"));
				
				
				
			
				
			}
		}catch (SQLException e){
			System.out.println("erro na consulta" + e.getMessage());
			
		}
		
		return servidor;
	}*/
	
	public List<ColaboradorModel> buscar() throws SQLException {
		List<ColaboradorModel> listaColaborador = new ArrayList<ColaboradorModel>();

		ResultSet resposta = null;

		try {

			stmt = (PreparedStatement) conexao.createStatement();
			resposta = stmt.executeQuery("select * from colaboradores ");
			while (resposta.next()) {
				ColaboradorModel colaboradorModel = new ColaboradorModel();

				colaboradorModel.setIdColaborador(resposta.getInt("idColaboradores"));
				colaboradorModel.setCPF(resposta.getString("cpf"));
				colaboradorModel.setNome(resposta.getString("nome"));
				colaboradorModel.setEmail(resposta.getString("email"));
				colaboradorModel.setLogin(resposta.getString("login"));
				colaboradorModel.setSenha(resposta.getString("senha"));
				colaboradorModel.setFoneFixo(resposta.getString("foneFixo"));
				colaboradorModel.setFoneCelular(resposta.getString("foneCelular"));
				colaboradorModel.setLogin(resposta.getString("login"));
				colaboradorModel.setSenha(resposta.getString("senha"));
				
				

				listaColaborador.add(colaboradorModel);

			}
		} catch (SQLException e) {
			System.out.println("Erro na consulta 1:" + e.getMessage());
		}
		return listaColaborador;

	}

}
