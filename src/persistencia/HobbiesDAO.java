package persistencia;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Hobbies;

public class HobbiesDAO {
	private Conexao minhaConexao;
	private String inc = "insert into hobbies (cod_hobbie, descricao) values (?,?)";
	private String exc = "delete from hobbies where \"cod_hobbie\" = ?"; 
	private String bus = "select * from hobbies where \"cod_hobbie\" = ?";
	private String rel = "select * from hobbies";
	
	public HobbiesDAO() {
		minhaConexao = new Conexao("jdbc:postgresql://localhost:5432/BDProjAppDeRelacionamento", "postgres", "12345");
	}
	
	public void incluirHobbies(Hobbies Hobbie) {
		try {
			minhaConexao.conectar();
			
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(inc);
			instrucao.setInt(1, Hobbie.getCod_hobbie());
			instrucao.setString(2,Hobbie.getDescricao());
			instrucao.execute();
			
			minhaConexao.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na INCLUSAO: " + e.getMessage());
		}
	}
	
	public void excluir(int CodAux) {
		try {
			minhaConexao.conectar();
			
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(exc);
			instrucao.setInt(1, CodAux);
			instrucao.execute();
			minhaConexao.desconectar();
			 
		}catch(Exception e) {
			System.out.println("Erro na EXCLUSAO:" + e.getMessage());
		}
	}
	
	public Hobbies buscarHobbies(int cod_hobbie){
		Hobbies h = null;
			
		try {
			minhaConexao.conectar();
			
			PreparedStatement instrucao = minhaConexao.getConexao().prepareStatement(bus);
			instrucao.setInt(1, cod_hobbie);
			ResultSet rs = instrucao.executeQuery();
			
			if(rs.next()) {
				h = new Hobbies(rs.getInt("cod_hobbie"), rs.getString("descricao"));
			}
			
			minhaConexao.desconectar();
		}catch(Exception e) {
			System.out.println("Erro ao buscar hobbie: " + e.getMessage());
		}
		return h;
	}
	
	public ArrayList<Hobbies> emitirRelatorio(){
		Hobbies h;
		ArrayList<Hobbies> relatorio = new ArrayList<Hobbies>();
		
		try {
			minhaConexao.conectar();
			
			Statement instrucao = minhaConexao.getConexao().createStatement();
			ResultSet rs = instrucao.executeQuery(rel);
			
			while(rs.next()) {
				h = new Hobbies(rs.getInt("cod_hobbie"), rs.getString("descricao"));
				relatorio.add(h);
			}
			minhaConexao.desconectar();
		}catch(Exception e){
			System.out.println("Erro no relatorio:" + e.getMessage());
		}
		return relatorio;
	}
	
}
