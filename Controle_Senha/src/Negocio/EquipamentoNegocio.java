package Negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import DAO.EquipamentoDAO;
import Modelo.ColaboradorModel;
import Modelo.EquipamentoModel;
import Modelo.TipoModel;

public class EquipamentoNegocio {
	
	EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
	
	public String salvar(EquipamentoModel equipamento, TipoModel tipo)throws SQLException {
		EquipamentoDAO  equipamentoDao = new EquipamentoDAO();
		
    	String salvo ="Falha";
    	StringBuilder sb = new StringBuilder(); 
        boolean cnpjValido = false;
        if(tipo == null || tipo.equals("")){
        	sb.append("Selecione o tipo de Equipamento");
        }
        
        cnpjValido = CnpjValido(equipamento.getCnpjFornecedor());
        if(!cnpjValido) {
        	sb.append("CNPJ inválido. \n");
        }
        if(equipamento.getCnpjFornecedor() == null || equipamento.getCnpjFornecedor().equals("")){
            sb.append("O Campo CNPJ não pode ser VAZIO \n");
        }
        if(equipamento.getRasaoFornecedor() == null || equipamento.getRasaoFornecedor().equals("")){
            sb.append("O Campo RASÃO não pode ser VAZIO. \n");
        }
        if(equipamento.getMac_1() == null || equipamento.getMac_1().equals("")){
            sb.append("O Campo MAC não pode ser VAZIO. \n");
        }
        if(equipamento.getDataCompra() == null || equipamento.getDataCompra().equals("")){
            sb.append("O Campo DATA não pode ser VAZIO. \n");
        }
    
        if (sb.toString().isEmpty()) {
            //aqui eu gravo...
        	salvo = equipamentoDao.salvar(tipo, equipamento  );// pegando o objeto DEPARTAMENTO da camada departamentoDAO
        } else {
        	 sb.append(salvo);
             return sb.toString();
        }
        sb.append(salvo);
        return sb.toString();
       
    }
	
	
	// metodo para validar dados do CPF se é um valor válido
	 public boolean validaCPF(String CPF){

		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		            if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
		                    CPF.equals("22222222222") || CPF.equals("33333333333") ||
		                    CPF.equals("44444444444") || CPF.equals("55555555555") ||
		                    CPF.equals("66666666666") || CPF.equals("77777777777") ||
		                    CPF.equals("88888888888") || CPF.equals("99999999999") ||
		                    (CPF.length() != 11))
		                return(false);

		            char dig10, dig11;
		            int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		            try {
		// Calculo do 1o. Digito Verificador
		                sm = 0;
		                peso = 10;
		                for (i=0; i<9; i++) {
		// converte o i-esimo caractere do CPF em um numero:
		// por exemplo, transforma o caractere '0' no inteiro 0
		// (48 eh a posicao de '0' na tabela ASCII)
		                    num = (int)(CPF.charAt(i) - 48);
		                    sm = sm + (num * peso);
		                    peso = peso - 1;
		                }

		                r = 11 - (sm % 11);
		                if ((r == 10) || (r == 11))
		                    dig10 = '0';
		                else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

		// Calculo do 2o. Digito Verificador
		                sm = 0;
		                peso = 11;
		                for(i=0; i<10; i++) {
		                    num = (int)(CPF.charAt(i) - 48);
		                    sm = sm + (num * peso);
		                    peso = peso - 1;
		                }

		                r = 11 - (sm % 11);
		                if ((r == 10) || (r == 11))
		                    dig11 = '0';
		                else dig11 = (char)(r + 48);

		// Verifica se os digitos calculados conferem com os digitos informados.
		                if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
		                    return(true);
		                else return(false);
		            } catch (InputMismatchException erro) {
		                return(false);
		            }
		        }
	 
	 // metodo para validar CNPJ
	 public static boolean CnpjValido(String cnpj) {
	        if (!cnpj.substring(0, 1).equals("")) {
	            try {
	                cnpj = cnpj.replace('.', ' ');//onde há ponto coloca espaço
	                cnpj = cnpj.replace('/', ' ');//onde há barra coloca espaço
	                cnpj = cnpj.replace('-', ' ');//onde há traço coloca espaço
	                cnpj = cnpj.replaceAll(" ", "");//retira espaço
	                int soma = 0, dig;
	                String cnpj_calc = cnpj.substring(0, 12);
	                if (cnpj.length() != 14) {
	                    return false;
	                }
	                char[] chr_cnpj = cnpj.toCharArray();
	                /* Primeira parte */
	                for (int i = 0; i < 4; i++) {
	                    if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
	                        soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
	                    }
	                }
	                for (int i = 0; i < 8; i++) {
	                    if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
	                        soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
	                    }
	                }
	                dig = 11 - (soma % 11);
	                cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(
	                        dig);
	                /* Segunda parte */
	                soma = 0;
	                for (int i = 0; i < 5; i++) {
	                    if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
	                        soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
	                    }
	                }
	                for (int i = 0; i < 8; i++) {
	                    if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
	                        soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
	                    }
	                }
	                dig = 11 - (soma % 11);
	                cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(
	                        dig);
	                return cnpj.equals(cnpj_calc);
	            }
	            catch (Exception e) {
	                return false;
	            }
	        }
	        else {
	            return false;
	        }
	    }
	
	public List<EquipamentoModel> listarEquipamento()throws SQLException{
    	EquipamentoDAO  equipamentoDao = new EquipamentoDAO();
    	List<EquipamentoModel> equipamento = new ArrayList<EquipamentoModel>();
    	equipamento = equipamentoDao.listarEquipamento();
    	return equipamento;
    	
    }
	
	
 

}
