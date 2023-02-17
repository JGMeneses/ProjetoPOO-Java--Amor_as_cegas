package dominio;

public class PagamentoVIP {
	private String cpf;
	private String numero_cartao;
	private String senha_cartao;
	private boolean pago;
	private Usuario user;
	
	
	public PagamentoVIP() {
		
	}

	public PagamentoVIP(String cpf, String numero_cartao, String senha_cartao, boolean pago, Usuario user) {
		
		this.cpf = cpf;
		this.numero_cartao = numero_cartao;
		this.senha_cartao = senha_cartao;
		this.pago = pago;
		this.user = user;
	}
	
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNumero_cartao() {
		return numero_cartao;
	}
	
	public void setNumero_cartao(String numero_cartao) {
		this.numero_cartao = numero_cartao;
	}
	
	public String getSenha_cartao() {
		return senha_cartao;
	}
	
	public void setSenha_cartao(String senha_cartao) {
		this.senha_cartao = senha_cartao;
	}
	
	public boolean getPago() {
		return pago;
	}
	
	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
}
