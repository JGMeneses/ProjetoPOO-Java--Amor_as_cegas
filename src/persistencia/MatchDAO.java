package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dominio.Usuario;

public class MatchDAO {
	private Conexao minhaConexao;
	private String matchHETEROS = "select * from usuario where usuario.nickname != ? "
			+ "and usuario.orientacaosexual = 'h' and usuario.genero != ?";
	
	private String matchLESBICAS = "select * from usuario where usuario.nickname != ? "
			+ "and usuario.orientacaosexual = 'l' or usuario.orientacaosexual = 'b' and usuario.genero = ?";
	
	private String matchGAYS = "select * from usuario where usuario.nickname != ? "
			+ "and usuario.orientacaosexual = 'g' or usuario.orientacaosexual = 'b' and usuario.genero = ?";
	
	//private String matchBI = "select * from usuario where usuario.nickname != ?";
	
	private String matchMBI = "SELECT * FROM usuario where nickname != ? "
			+ "EXCEPT SELECT * FROM usuario WHERE orientacaosexual = 'h'and genero = 'm' "
			+ "EXCEPT SELECT * FROM usuario WHERE orientacaosexual = 'l' and genero = 'f';";
	
	private String matchFBI = "SELECT * FROM usuario where nickname != ? "
			+ "EXCEPT SELECT * FROM usuario WHERE orientacaosexual = 'h'and genero = 'f' "
			+ "EXCEPT SELECT * FROM usuario WHERE orientacaosexual = 'g' and genero = 'm';";
	
	public MatchDAO() {
		minhaConexao = new Conexao("jdbc:postgresql://localhost:5432/BDProjAppDeRelacionamento", "postgres", "12345");
	}
	
	public ArrayList<Usuario> matchHETERO(Usuario user){
		UsuarioDAO userDAO = new UsuarioDAO();
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		Usuario userAux;
		
		try {
			minhaConexao.conectar();
			
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(matchHETEROS);
			
			instrucao.setString(1, user.getNickname());
			instrucao.setString(2, user.getGenero());
			
			ResultSet rs = instrucao.executeQuery();
			while(rs.next()) {
				userAux = userDAO.buscarUsuario(rs.getString("nickname"));
				listaUsuarios.add(userAux);
			}
			
			minhaConexao.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na filtragem matchHETEROS: " + e.getMessage());
		}
		
		return listaUsuarios;
	}
	
	public ArrayList<Usuario> matchLESBICAS(Usuario user){
		UsuarioDAO userDAO = new UsuarioDAO();
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		Usuario userAux;
		
		try {
			minhaConexao.conectar();
			
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(matchLESBICAS);
			
			instrucao.setString(1, user.getNickname());
			instrucao.setString(2, user.getGenero());
			
			ResultSet rs = instrucao.executeQuery();
			while(rs.next()) {
				userAux = userDAO.buscarUsuario(rs.getString("nickname"));
				listaUsuarios.add(userAux);
			}
			
			minhaConexao.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na filtragem matchLESBICAS: " + e.getMessage());
		}
		
		return listaUsuarios;
	}
	
	public ArrayList<Usuario> matchGAYS(Usuario user){
		UsuarioDAO userDAO = new UsuarioDAO();
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		Usuario userAux;
		
		try {
			minhaConexao.conectar();
			
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(matchGAYS);
			
			instrucao.setString(1, user.getNickname());
			instrucao.setString(2, user.getGenero());
			
			ResultSet rs = instrucao.executeQuery();
			while(rs.next()) {
				userAux = userDAO.buscarUsuario(rs.getString("nickname"));
				listaUsuarios.add(userAux);
			}
			
			minhaConexao.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na filtragem matchGAYS: " + e.getMessage());
		}
		
		return listaUsuarios;
	}
	
	public ArrayList<Usuario> matchMBI(Usuario user){
		UsuarioDAO userDAO = new UsuarioDAO();
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		Usuario userAux;
		
		try {
			minhaConexao.conectar();
			
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(matchMBI);
			
			instrucao.setString(1, user.getNickname());
			
			ResultSet rs = instrucao.executeQuery();
			while(rs.next()) {
				userAux = userDAO.buscarUsuario(rs.getString("nickname"));
				listaUsuarios.add(userAux);
			}
			
			minhaConexao.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na filtragem matchBI: " + e.getMessage());
		}
		
		return listaUsuarios;
	}
	
	public ArrayList<Usuario> matchFBI(Usuario user){
		UsuarioDAO userDAO = new UsuarioDAO();
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		Usuario userAux;
		
		try {
			minhaConexao.conectar();
			
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(matchFBI);
			
			instrucao.setString(1, user.getNickname());
			
			ResultSet rs = instrucao.executeQuery();
			while(rs.next()) {
				userAux = userDAO.buscarUsuario(rs.getString("nickname"));
				listaUsuarios.add(userAux);
			}
			
			minhaConexao.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na filtragem matchBI: " + e.getMessage());
		}
		
		return listaUsuarios;
	}
	
	
}
