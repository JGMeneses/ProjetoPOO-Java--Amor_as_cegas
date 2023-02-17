package dominio;

import java.util.ArrayList;

import persistencia.HobbiesDAO;
import persistencia.UsuarioDAO;
import persistencia.UsuarioHobbiesDAO;

public class Usuario {
	private String nickname;
	private String nome;
	private String senha;
	private int idade;
	private String genero;
	private String orientacaoSexual;
	private ArrayList<Hobbies> listaHobbies;
	
	
	public Usuario() {
		
	}

	public Usuario(String nickname, String nome, String senha, int idade, String genero, String orientacaoSexual) {
		this.nickname = nickname;
		this.nome = nome;
		this.senha = senha;
		this.idade = idade;
		this.genero = genero;
		this.orientacaoSexual = orientacaoSexual;
		this.listaHobbies = new ArrayList<Hobbies>();
	}
	
	public void setListaHobbies(ArrayList<Hobbies> listaH) {
		this.listaHobbies = listaH;
	}
	
	public ArrayList<Hobbies> getListaHobbies(){
		return this.listaHobbies;
	}
	
	public void adicionarListaHobbies(Hobbies h) {
		this.listaHobbies.add(h);
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getIdade() {
		return idade;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getGenero() {
		
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getOrientacaoSexual() {
		return this.orientacaoSexual;
	}

	public void setOrientacaoSexual(String orientacaoSexual) {
		this.orientacaoSexual = orientacaoSexual;
	}

}
