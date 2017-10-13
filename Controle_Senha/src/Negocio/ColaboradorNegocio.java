package Negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import DAO.ColaboradorDAO;
import Modelo.ColaboradorModel;

public class ColaboradorNegocio {
	
	// pacote que valida possui as regras de negocio.


		ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
		ColaboradorModel colaboradorModelo = new ColaboradorModel();
		List<ColaboradorModel> listaColaborador = new ArrayList<ColaboradorModel>();

		public String salvar(ColaboradorModel colaborador) throws SQLException {

			ColaboradorDAO colaboradorDao = new ColaboradorDAO();

			String salvo = "Falha";
			boolean cpfValido = false;
			StringBuilder sb = new StringBuilder();
			cpfValido = validaCPF(colaborador.getCPF());
			if (!cpfValido) {
				sb.append("CPF inválido. \n");
			}
			if(colaborador.getNome() == null || colaborador.getNome().equals("")){
	            sb.append("O nome não pode ser vazio. \n");
	        }
			if(colaborador.getFoneCelular() == null || colaborador.getFoneCelular().equals("")){
	            sb.append("O Fone Celular não pode ser vazio. \n");
	        }
			if(colaborador.getEmail() == null || colaborador.getEmail().equals("")){
	            sb.append("O Email não pode ser vazio. \n");
	        }

			if (sb.toString().isEmpty()) {
				
				salvo = colaboradorDao.salvar(colaborador);
			} else {
				sb.append(salvo);
				return sb.toString();
			}
			sb.append(salvo);
			return sb.toString();
		}

		// Metodo para validar CPF
		public boolean validaCPF(String CPF) {

			// considera-se erro CPF's formados por uma sequencia de numeros iguais
			if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
					|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
					|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
					|| CPF.equals("99999999999") || (CPF.length() != 11))
				return (false);

			char dig10, dig11;
			int sm, i, r, num, peso;

			// "try" - protege o codigo para eventuais erros de conversao de tipo
			// (int)
			try {
				// Calculo do 1o. Digito Verificador
				sm = 0;
				peso = 10;
				for (i = 0; i < 9; i++) {
					// converte o i-esimo caractere do CPF em um numero:
					// por exemplo, transforma o caractere '0' no inteiro 0
					// (48 eh a posicao de '0' na tabela ASCII)
					num = (int) (CPF.charAt(i) - 48);
					sm = sm + (num * peso);
					peso = peso - 1;
				}

				r = 11 - (sm % 11);
				if ((r == 10) || (r == 11))
					dig10 = '0';
				else
					dig10 = (char) (r + 48); // converte no respectivo caractere
												// numerico

				// Calculo do 2o. Digito Verificador
				sm = 0;
				peso = 11;
				for (i = 0; i < 10; i++) {
					num = (int) (CPF.charAt(i) - 48);
					sm = sm + (num * peso);
					peso = peso - 1;
				}

				r = 11 - (sm % 11);
				if ((r == 10) || (r == 11))
					dig11 = '0';
				else
					dig11 = (char) (r + 48);

				// Verifica se os digitos calculados conferem com os digitos
				// informados.
				if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
					return (true);
				else
					return (false);
			} catch (InputMismatchException erro) {
				return (false);
			}
		}

		/*public String verificarLogin(String login, String senha) throws SQLException {

			listaColaborador = colaboradorDAO.buscar();

			if (login.equals("")) {
				return "naoentra";
			} else if (senha.equals("")) {
				return "naoentra";
			} else if (verificarListaLogin(login, senha) == true) {
				return "entra";
			}
			return "naoentra";
		}*/

		/*public boolean verificarListaLogin(String login, String senha) throws SQLException {

			for (int i = 0; i < listaColaborador.size(); i++) {
				colaboradorModelo = listaColaborador.get(i); // lista criada no
																// inicio da classe

				if (login.equals(colaboradorModelo.getLogin()) && senha.equals(colaboradorModelo.getSenha())) {
					return true;
				}

			}
			return false;

		}*/

		public void salvarCoordenador(ColaboradorModel colaboradorModelo) throws SQLException {
			colaboradorDAO.salvar(colaboradorModelo);

		}

		public List<ColaboradorModel> listarColaborador() throws SQLException {
			List<ColaboradorModel> colaboradores = new ArrayList<ColaboradorModel>();
			colaboradores = colaboradorDAO.buscar();
			return colaboradores;
		}


}
