package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Hobbies;
import dominio.Usuario;

public class UsuarioDAO {
	private Conexao minhaConexao;
	private String inc = "insert into Usuario (nickname, nome, senha, idade, genero, orientacaoSexual) values (?,?,?,?,?,?)";
	private String exc = "delete from Usuario where \"nickname\" = ?";
	private String bus = "select * from Usuario where \"nickname\" =?"; 
	private String rel = "select * from Usuario";
	private String altLogin = "update usuario set \"nickname\" = ?, \"senha\" = ? where nickname = ?";
	private String altDados = "update usuario set \"nome\" = ?, \"idade\" = ?, \"genero\" = ?, \"orientacaosexual\" = ?  where nickname = ?";
	private String usersBanco = "select * from usuario where \"nickname\" != ?";
	
	public UsuarioDAO() {
		minhaConexao = new Conexao("jdbc:postgresql://localhost:5432/BDProjAppDeRelacionamento", "postgres", "12345");
	}
	
	public void incluirUsuario(Usuario user) {
		try {
			
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(inc);
			instrucao.setString(1, user.getNickname());
			instrucao.setString(2, user.getNome());
			instrucao.setString(3, user.getSenha());
			instrucao.setInt(4, user.getIdade());
			instrucao.setString(5, user.getGenero());
			instrucao.setString(6, user.getOrientacaoSexual());
			
			instrucao.execute();
			minhaConexao.desconectar();
			
		}catch(Exception e) {
			System.out.println("Erro na INCLUSAO USUARIO: " + e.getMessage());
		}
	}
	
	public void excluirUsuario(String nicknameAux) {
		try {
			
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(exc);
			instrucao.setString(1, nicknameAux);
			instrucao.execute();
			minhaConexao.desconectar();
			
		}catch(Exception e) {
			System.out.println("Erro na EXCLUSAO USUARIO:" + e.getMessage());
		}
	}
	
	public Usuario buscarUsuario(String nicknameAux) {
		Usuario user = null;
		UsuarioHobbiesDAO userHobbiesDAO = new UsuarioHobbiesDAO();
		ArrayList<Hobbies> listaHobbies = new ArrayList<Hobbies>();
		
		try {
			
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(bus);
			instrucao.setString(1, nicknameAux);
			ResultSet rs = instrucao.executeQuery();
			if(rs.next()) {
				user = new Usuario(rs.getString("nickname"), rs.getString("nome"), rs.getString("senha"), rs.getInt("idade"), rs.getString("genero"), rs.getString("orientacaoSexual"));
				listaHobbies = userHobbiesDAO.relatorioUsuario(user.getNickname());
				user.setListaHobbies(listaHobbies);
			}
			minhaConexao.desconectar();
			
		}catch(Exception e) {
			System.out.println("Erro na BUSCA USUARIO: " + e.getMessage());
		}
		
		return user;
	} 
	
	public ArrayList<Usuario> emitirRelatorio(){
		Usuario user;
		ArrayList<Usuario> relatorio = new ArrayList<Usuario>();
		
		try {
			minhaConexao.conectar();
			Statement instrucao = minhaConexao.getConexao().createStatement();
			ResultSet rs = instrucao.executeQuery(rel);
			while(rs.next()) {
				user = new Usuario(rs.getString("nickname"), rs.getString("nome"), rs.getString("senha"), rs.getInt("idade"), rs.getString("genero"), rs.getString("orientacaoSexual"));
				relatorio.add(user);
			}
			minhaConexao.desconectar();
		}catch(Exception e){
			System.out.println("Erro no RELATORIO USUARIO:" + e.getMessage());
		}

		return relatorio;
	}
	
	public void atualizarDadosLogin(String nicknameAtual, Usuario user) {
		try {
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(altLogin);
			instrucao.setString(1, user.getNickname());
			instrucao.setString(2, user.getSenha());
			instrucao.setString(3, nicknameAtual);
			instrucao.execute();
			minhaConexao.desconectar();
			
		}catch(Exception e) {
			System.out.println("Erro na ALTERACAO LOGIN USUARIO: " + e.getMessage());
		}
	}
	
	public void atualizarDadosUser(String nickname, Usuario user) {
		try {
			minhaConexao.conectar();
			
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(altDados);
			instrucao.setString(1, user.getNome());
			instrucao.setInt(2, user.getIdade());
			instrucao.setString(3, user.getGenero());
			instrucao.setString(4, user.getOrientacaoSexual());
			instrucao.setString(5, nickname);
			instrucao.execute();
			
			minhaConexao.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na ALTERACAO DADOS USUARIO:" + e.getMessage());
		}
	}
	
	public ArrayList<Usuario> usuariosBanco(String nicknameAux){
		//retorna todos os usuarios menos o que passou o nickname
		
		Usuario user;
		UsuarioDAO userDAO = new UsuarioDAO();
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		
		try {
			minhaConexao.conectar();
			
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(usersBanco);
			instrucao.setString(1, nicknameAux);
			ResultSet rs = instrucao.executeQuery();
			
			while(rs.next()) {
				user = userDAO.buscarUsuario(rs.getString("nickname"));
				listaUsuarios.add(user);
			}
			
			minhaConexao.desconectar();
			
		}catch(Exception e) {
			System.out.println("Erro ao EMITIR RELATORIO USUARIOS BANCO: " + e.getMessage());

		}
		return listaUsuarios;
	}
	
}