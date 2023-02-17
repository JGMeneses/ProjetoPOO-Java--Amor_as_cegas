package dominio;

import java.util.ArrayList;

import persistencia.HobbiesDAO;
import persistencia.MatchDAO;
import persistencia.UsuarioDAO;
import persistencia.UsuarioHobbiesDAO;

public class Match extends Usuario{
	private ArrayList<Usuario> listaMatchs;
	
	public Match() {
		
	}
	
	public ArrayList<Usuario> getListaMatchs() {
		return listaMatchs;
	}


	public void setListaMatchs(ArrayList<Usuario> listaMatchs) {
		this.listaMatchs = listaMatchs;
	}
	

	public ArrayList<Usuario> setListaMatchs(Usuario user, int qntHobbies) {
			
			UsuarioDAO userDAO = new UsuarioDAO();
			HobbiesDAO hDAO = new HobbiesDAO();
			UsuarioHobbiesDAO userHobbiesDAO = new UsuarioHobbiesDAO();
			Usuario userMatch;
			Usuario userAux; 
			
			ArrayList<Usuario> listaUsers = userDAO.usuariosBanco(user.getNickname()); //lista de usuarios no banco diferentes do user logado
			ArrayList<Usuario> listaMatch = null; //lista de usuarios que fez match com o usuario logado
			ArrayList<Integer> listaCompatibilidade = new ArrayList<Integer>(); //lista de compatibilidade de cada usuario do banco c o user logado
			ArrayList<Hobbies> listaHobbiesUserAux;
			int compatibilidade, i, j, k;
			
		
			for(i=0; i<listaUsers.size(); i++) {
				userAux = listaUsers.get(i); //usuario do banco
				listaHobbiesUserAux = userHobbiesDAO.relatorioUsuario(userAux.getNickname()); // lista de hobbeis do user do banco 
				compatibilidade = 0;
				
				for(j=0; j<listaHobbiesUserAux.size(); j++) { // percorrer a lista de h do user aux
					for(k=0; k<user.getListaHobbies().size(); k++) { // percorrer a lista de h do user logado 
						if(user.getListaHobbies().get(k).getCod_hobbie() == listaHobbiesUserAux.get(j).getCod_hobbie()) { // checa se tem h igual
							compatibilidade++; // se for igual, conta
						}
					}
				}
				listaCompatibilidade.add(compatibilidade); // adc
			}
			
			try {
				listaMatch = new ArrayList<Usuario>();
				for(i=0; i<listaCompatibilidade.size(); i++) { //percorre lista de compatibilidade
					if(listaCompatibilidade.get(i) > qntHobbies) { //verifica se é match (ao inves de 5, ser a escolha do usuario!!!!!)
						userAux = listaUsers.get(i); // guarda o usuario
						listaMatch.add(userAux); //adiciona o usuario à lista de matchs
					}
				} 
			}catch(Exception e) {
				listaMatch = null;
			}
			
			
			setListaMatchs(listaMatch);
			
			return listaMatch;
		}
		
		
		public ArrayList<Usuario> filtragem(Usuario user, int qntHobbies){ // qntHobbies é o mood dele 
			
			MatchDAO matchDAO = new MatchDAO();
			
			ArrayList<Usuario> listaMatchs = setListaMatchs(user, qntHobbies);
			ArrayList<Usuario> filtragem = new ArrayList<Usuario>();
			ArrayList<Usuario> listaMatchsFiltrada = null;
			
			Usuario userAux;
			
			String genero = user.getGenero();
			String orientacaoSexual = user.getOrientacaoSexual();
			int i, j;
	
			switch(genero) {
			case "f":
				switch(orientacaoSexual) {
				case "h":
					filtragem = matchDAO.matchHETERO(user);
					break;
				case "l":
					filtragem = matchDAO.matchLESBICAS(user);
					break;
				case "b":
					filtragem = matchDAO.matchFBI(user);
					break;
				}
				break;
			case "m":
				switch(orientacaoSexual) {
				case "h": 
					filtragem = matchDAO.matchHETERO(user);
					break;
				case "g":
					filtragem = matchDAO.matchGAYS(user);
					break;
				case "b":
					filtragem = matchDAO.matchMBI(user);
					break;
				}
				break;
			}
			
			try {
				listaMatchsFiltrada = new ArrayList<Usuario>();
				
				for( i=0; i<listaMatchs.size(); i++) { //lista de usuarios com hobbies iguais
					for( j=0; j<filtragem.size(); j++) { //lista filtrada (gen e orienS)
						
						if(listaMatchs.get(i).getNickname().equalsIgnoreCase(filtragem.get(j).getNickname())) {
							userAux = listaMatchs.get(i);
							listaMatchsFiltrada.add(userAux);
						}
					}
				}
			}catch(Exception e) {
				listaMatchsFiltrada = null;
			}
			
			
			return listaMatchsFiltrada;
		}
}
