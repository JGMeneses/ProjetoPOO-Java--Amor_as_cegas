package persistencia;

import dominio.PagamentoVIP;
import dominio.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PagamentoVIPDAO {
	private Conexao minhaConexao;
	private String inc = "insert into PagamentoVIP (cpf, numero_cartao, senha_cartao, pg, fk_usuario) values (?,?,?,?,?)";
	private String exc = "delete from PagamentoVip where \"fk_usuario\"  = ?";
	private String rel = "select * from PagamentoVIP where \"pg\" =  true";
	private String bus = "select * from PagamentoVIP where \"fk_usuario\" = ?";
	private String busCPF = "select * from PagamentoVIP where \"cpf\" = ?";
	private String alt = "update pagamentovip set \"numero_cartao\" = ?, \"senha_cartao\" = ? where \"fk_usuario\" = ?";
	private String atualizarPg = "update PagamentoVip set \"pg\" = ? where \"fk_usuario\" = ? ";
	
	public PagamentoVIPDAO() {
		minhaConexao = new Conexao("jdbc:postgresql://localhost:5432/BDProjAppDeRelacionamento", "postgres", "12345");
	}
	
	public void realizarPagamento(PagamentoVIP payment, String nicknameAux) {
		try {
			minhaConexao.conectar();
			
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(inc);	
			instrucao.setString(1, payment.getCpf());
			instrucao.setString(2, payment.getNumero_cartao());
			instrucao.setString(3, payment.getSenha_cartao());
			instrucao.setBoolean(4, payment.getPago());
			instrucao.setString(5, nicknameAux);
			instrucao.execute();
			
			minhaConexao.desconectar();
		}catch(Exception e) {
			System.out.println("Erro ao REALIZAR PAGAMENTO: " + e.getMessage());
		}
	}
	
	public void excluirPagamentoVIP(String nicknameAux) {
		try {
			minhaConexao.conectar();
			
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(exc);
			instrucao.setString(1, nicknameAux);
			instrucao.execute();
		
			minhaConexao.desconectar();	
		}catch(Exception e) {
				System.out.println("Erro na EXCLUSAO PGVIP:" + e.getMessage());
		}
	}
	
	public void atualizarPagamento(String nicknameAux, boolean status) {
		
		if(status == true) {
			try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(atualizarPg);
			instrucao.setBoolean(1, false);
			instrucao.setString(2, nicknameAux);
			instrucao.execute();
			minhaConexao.desconectar();
			
			}catch(Exception e) {
				System.out.println("Erro ao atualizar pagamentoVIP: "+ e.getMessage());
			}
		}else {
			try {
				minhaConexao.conectar();
				PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(atualizarPg);
				instrucao.setBoolean(1, true);
				instrucao.setString(2, nicknameAux);
				instrucao.execute();
				minhaConexao.desconectar();
				
				}catch(Exception e) {
					System.out.println("Erro ao atualizar pagamentoVIP: "+ e.getMessage());
				}
		}
		
		
	}
	
	public ArrayList<PagamentoVIP> relatorioPagamentosVIP(){
		UsuarioDAO userDAO = new UsuarioDAO();
		Usuario user;
		PagamentoVIP pgVIP = null;
		ArrayList<PagamentoVIP> listaPgVIP = new ArrayList<PagamentoVIP>();
		
		try {
			minhaConexao.conectar();
			
			Statement instrucao = minhaConexao.getConexao().createStatement();
			ResultSet rs = instrucao.executeQuery(rel);
			
			while(rs.next()) {
				user = userDAO.buscarUsuario(rs.getString("fk_usuario"));
				if(user != null) {
					pgVIP = new PagamentoVIP(rs.getString("cpf"), rs.getString("numero_cartao"), rs.getString("senha_cartao"), rs.getBoolean("pg"), user);
				}
				listaPgVIP.add(pgVIP); 
			}
			
			minhaConexao.desconectar();
		}catch(Exception e) {
			System.out.println("Erro ao imprimir relatorio pagamentoVIP: "+ e.getMessage());
		}
		return listaPgVIP;
	}
	
	public PagamentoVIP buscarPagamentoVIP(String nicknameAux) {
		UsuarioDAO userDAO = new UsuarioDAO();
		Usuario user;
		PagamentoVIP pgVIP = null;
		
		try {
			minhaConexao.conectar();
			
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(bus);
			instrucao.setString(1, nicknameAux);
			ResultSet rs = instrucao.executeQuery();
			
			if(rs.next()) {
				user = userDAO.buscarUsuario(rs.getString("fk_usuario"));
				pgVIP = new PagamentoVIP(rs.getString("cpf"), rs.getString("numero_cartao"), rs.getString("senha_cartao"), rs.getBoolean("pg"), user);
			}
			
			minhaConexao.desconectar();
		}catch(Exception e) {
			System.out.println("Erro ao buscar pagamentoVIP: "+ e.getMessage());
		}
		return pgVIP;
	}
	
	public Boolean buscarPorCPF(String cpf) {
		Boolean achou = false;
		
		try {
			minhaConexao.conectar();
			
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(busCPF);
			instrucao.setString(1, cpf);
			ResultSet rs = instrucao.executeQuery();
			
			if(rs.next()) {
				achou = true;
			}
			
			minhaConexao.desconectar();
		}catch(Exception e) {
			System.out.println("Erro ao buscar por CPF: "+ e.getMessage());
		}
		return achou;
	}
	
	public void atualizarDadosCartao(PagamentoVIP pgVip, String nvNumCartao, String nvSenhaCartao) {
		try {
			minhaConexao.conectar();
			
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(alt);
			instrucao.setString(1, nvNumCartao);
			instrucao.setString(2, nvSenhaCartao);
			instrucao.setString(3, pgVip.getUser().getNickname());
			instrucao.execute();
			
			minhaConexao.desconectar();
			
		}catch(Exception e) {
			System.out.println("Erro ao atualizar pagamentoVIP: "+ e.getMessage());
		}
	}
	
}