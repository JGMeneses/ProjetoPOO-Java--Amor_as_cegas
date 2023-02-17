package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Hobbies;
import dominio.Usuario;

public class UsuarioHobbiesDAO {
	private Conexao minhaConexao; 
	private String inc = "insert into usuariohobbies (fk_usuario, fk_hobbies) values (?,?)";
	private String exc = "delete from usuariohobbies where \"fk_usuario\" = ? and \"fk_hobbies\" = ?";
	private String relUsuario = "select * from usuariohobbies where \"fk_usuario\" = ?";
	
	
	public UsuarioHobbiesDAO() {
		minhaConexao = new Conexao("jdbc:postgresql://localhost:5432/BDProjAppDeRelacionamento", "postgres", "12345");
	}
	
	public void incluirUsuarioHobbies(String Nickname, int Codaux) {
		try {
			
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(inc);
			instrucao.setString(1, Nickname);
			instrucao.setInt(2, Codaux);
			instrucao.execute();
			minhaConexao.desconectar();
			
		}catch(Exception e) {
			System.out.println("Erro na INCLUSAO UsuarioHobbies: " + e.getMessage());
		}
	}
	
	public void excluir(String Nickname, int Codaux){
		try {
			
			minhaConexao.conectar();
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(exc);
			instrucao.setString(1, Nickname);
			instrucao.setInt(2, Codaux);
			instrucao.execute();
			minhaConexao.desconectar();
			 
		}catch(Exception e) {
			System.out.println("Erro na EXCLUSAO UsuarioHobbies:" + e.getMessage());
		}
	}
	
	public ArrayList<Hobbies> relatorioUsuario(String nicknameAux){
		Hobbies h;
		HobbiesDAO hDAO = new HobbiesDAO();
		ArrayList<Hobbies> listaHobbies = new ArrayList<Hobbies>();
		
		try {
			
			minhaConexao.conectar();
			
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(relUsuario);
			instrucao.setString(1, nicknameAux);
			ResultSet rs = instrucao.executeQuery();
			
			while(rs.next()) {
				h = hDAO.buscarHobbies(rs.getInt("fk_hobbies"));
				
				if( h != null ) {
					listaHobbies.add(h);
				}
				
			}
			
			minhaConexao.desconectar();
			
		}catch(Exception e) {
			System.out.println("Erro ao emitir relatorioUsuario - UsuarioHobbies:" + e.getMessage());
		}
		
		return listaHobbies;
	}

}
