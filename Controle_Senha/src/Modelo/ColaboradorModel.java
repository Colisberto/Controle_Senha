package Modelo;

public class ColaboradorModel {
	
	private int idColaborador;
	private String CPF;
	private String nome;
	private String Email;
	private String foneFixo;
	private String foneCelular;
	private String login;
	private String senha;
	
	public int getIdColaborador() {
		return idColaborador;
	}
	public void setIdColaborador(int idColaborador) {
		this.idColaborador = idColaborador;
	}
	
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getFoneFixo() {
		return foneFixo;
	}
	public void setFoneFixo(String foneFixo) {
		this.foneFixo = foneFixo;
	}
	public String getFoneCelular() {
		return foneCelular;
	}
	public void setFoneCelular(String foneCelular) {
		this.foneCelular = foneCelular;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
