package dominio;

public class Hobbies {
	private int cod_hobbie;
	private String descricao;
	
	public Hobbies() {
		
	}
	
	public Hobbies(int cod, String descricao) {
		this.cod_hobbie = cod;
		this.descricao = descricao;
	}

	public int getCod_hobbie() {
		return cod_hobbie;
	}

	public void setCod_hobbie(int cod_hobbie) {
		this.cod_hobbie = cod_hobbie;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
