package Modelo;

import java.time.LocalDate;

public class EquipamentoModel {
	
	private int idEquipamento;
	private String CnpjFornecedor;
	private String rasaoFornecedor;
	private String nomeEquipamento;
	private String ipEquipamento;
	private String modelo;
	private String mac_1;
	private String mac_2;
	private String senha;
	private LocalDate dataCompra;
	private String fabricante;
	
	public String getNomeEquipamento() {
		return nomeEquipamento;
	}
	public void setNomeEquipamento(String nomeEquipamento) {
		this.nomeEquipamento = nomeEquipamento;
	}
	public String getIpEquipamento() {
		return ipEquipamento;
	}
	public void setIpEquipamento(String ipEquipamento) {
		this.ipEquipamento = ipEquipamento;
	}
	
	public String getMac_1() {
		return mac_1;
	}
	public void setMac_1(String mac_1) {
		this.mac_1 = mac_1;
	}
	public String getMac_2() {
		return mac_2;
	}
	public void setMac_2(String mac_2) {
		this.mac_2 = mac_2;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getIdEquipamento() {
		return idEquipamento;
	}
	public void setIdEquipamento(int idEquipameto) {
		this.idEquipamento = idEquipamento;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public LocalDate getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}
	public String getCnpjFornecedor() {
		return CnpjFornecedor;
	}
	public void setCnpjFornecedor(String cnpjFornecedor) {
		CnpjFornecedor = cnpjFornecedor;
	}
	public String getRasaoFornecedor() {
		return rasaoFornecedor;
	}
	public void setRasaoFornecedor(String rasaoFornecedor) {
		this.rasaoFornecedor = rasaoFornecedor;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

}
