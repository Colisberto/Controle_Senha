package DaoUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//fabrica de conecxão com pando de dados

public class ConectionFactory {
	
	private Connection conexao =null; // segurança para conexão
	
	// metodo para abrir conexão com banco
	public Connection criarConexao() throws SQLException{ // metodo para chamar o driver para conectar ao Banco
		try{
			Class.forName("com.mysql.jdbc.Driver");// pegando o driver de conexão
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/Password_control","root","root");
			
		}catch(ClassNotFoundException e){
		  e.printStackTrace();
		}
		return conexao;
	}
	
	// metodo para fechar a conecxão com banco
	public void fecharConexao() throws SQLException{ 
		try{
			conexao.close();
		}catch (Exception e){
			
			System.out.println("Erro na conexão" + e.getMessage());
			
		}
	}
	
 
}
