package Modelo;

public class UsuarioSenhaModel {
	
	private int IdUserSenha;
	
	private String usuario;
	private String senha;
	
	//private ArrayList<TipoEquipamento>;
	//private ArrayList<Equipamento>;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getIdUserSenha() {
		return IdUserSenha;
	}
	public void setIdUserSenha(int idUserSenha) {
		IdUserSenha = idUserSenha;
	}

}
