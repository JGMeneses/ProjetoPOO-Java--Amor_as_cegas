package visao;

import java.util.ArrayList;
import java.util.Scanner;

import dominio.Hobbies;
import dominio.Match;
import dominio.PagamentoVIP;
import dominio.Usuario;
import persistencia.HobbiesDAO;
import persistencia.PagamentoVIPDAO;
import persistencia.UsuarioDAO;
import persistencia.UsuarioHobbiesDAO;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner ler = new Scanner(System.in);
		
		// USUARIO
		UsuarioDAO userDAO = new UsuarioDAO();
		Usuario user, u1;
		
		// HOBBIES
		HobbiesDAO hDAO = new HobbiesDAO();
		Hobbies h;
		
		// PAGAMENTO VIP
		PagamentoVIPDAO pgVIPDAO = new PagamentoVIPDAO();
		PagamentoVIP pgVIP;
		
		// USUARIO_HOBBIES (n pra n)
		UsuarioHobbiesDAO userHobbieDAO = new UsuarioHobbiesDAO();
		
		// CLASSE MATCH
		Match matchs = new Match();
		
		// ARRAYLISTS
		ArrayList<Usuario> listaUser;
		ArrayList<Usuario> listaMatchs;
		ArrayList<Hobbies> listaHobbies;
		ArrayList<PagamentoVIP> listaPgVIP;
		
		// VARIAVEIS AUX
		String nicknameAux, senhaAux, nomeAux, descricaoAux, generoAux, orienSexAux, senhaUser, numCartaoAux, cpfAux;
		int i, j, op, op2, op3, resp, mood, idadeAux, codHAux;
		boolean existe;

		
		do {
			System.out.println("\n\n\t\tAMOR ÀS CEGAS");
			System.out.println("\t--------------------------------");
			System.out.println("\n\n\t---- Menu PRINCIPAL ----");
			System.out.println("\t--------------------------------");
			System.out.println("\t1 - Login / Cadastrar Usuario"); 
			System.out.println("\t2 - Buscar Usuarios"); 
			System.out.println("\t3 - Relatorio de Usuarios");
			System.out.println("\t4 - Sair");
			System.out.println("\t--------------------------------");
			System.out.print("\t> ");
			op = ler.nextInt();

			switch(op) {
			case 1:
				System.out.println("\n\n\t--- LOGIN / CADASTRO ---");
				System.out.println("\t--------------------------------");
				System.out.print("\tDigite o nickname: ");
				nicknameAux = ler.next();
				ler.nextLine();

				user = userDAO.buscarUsuario(nicknameAux); /* verifica se login ou cadastro */
				
				if(user == null) {
					System.out.println("\n\n\t*Nenhum usuario cadastrado com este NickName.*"
							+ "\n\tDeseja realizar o cadastro ('1' - sim | '0' - nao)?");
					System.out.print("\t> "); 
					resp = ler.nextInt();

					if(resp == 1) { 
						// cadastro de um novo usuario //
						user = new Usuario();
						user.setNickname(nicknameAux);

						System.out.println("\n\n\t--- CADASTRO USUARIOS ---");
						System.out.println("\t--------------------------------");
						System.out.println("\tNickName: " + user.getNickname()); 
						System.out.print("\tCrie uma senha: ");
						user.setSenha(ler.next());
						System.out.print("\tNome: "); 
						user.setNome(ler.next());
						ler.nextLine();
						System.out.print("\tIdade: ");
						idadeAux = ler.nextInt();

						if(idadeAux < 18) {
							System.out.println("\t*É necessário ter acima de 17 anos para realizar o cadastro.*");
							System.out.println("\tVoltando ao MENU PRINCIAPAL");
						}else {
							user.setIdade(idadeAux);
							System.out.print("\tGenero:"
									+ "\n\t\t'f' - feminino"
									+ "\n\t\t'm' - masculino");
							System.out.print("\n\t> ");
							generoAux = ler.next();
							user.setGenero(generoAux);
							System.out.println("\tOrientacao sexual: "
									+ "\n\t\t'h' - hetero"
									+ "\n\t\t'b' - bisexual"
									+ "\n\t\t'g' - gay"
									+ "\n\t\t'l' - lesbica");
							System.out.print("\t> ");
							orienSexAux = ler.next();
							user.setOrientacaoSexual(orienSexAux);

							userDAO.incluirUsuario(user); /* incluindo usuario novo ao banco */

							System.out.println("\n\n\t*Para finalizar cadastro, adicione 5 hobbies ao seu perfil.*");

							listaHobbies = hDAO.emitirRelatorio(); /* lista de todos hobbies cadastrados no banco */

							System.out.println("\n\n\t--------------------------------");
							System.out.println("\tLISTA HOBBIES");
							System.out.println("\t--------------------------------");
							System.out.println("\tCODIGO" + "\t-" + "\tDESCRICAO");
							System.out.println("\t--------------------------------");
							for(i=0; i<listaHobbies.size(); i++) {
								System.out.println("\t" + listaHobbies.get(i).getCod_hobbie() + "\t-\t" + listaHobbies.get(i).getDescricao());
								System.out.println("\t--------------------------------");
							}
							System.out.println("\tDigite o codigo de cada um dos 5 hobbies desejados.");

							for(j=0; j<5; j++) {
								do {
									System.out.print("\t> ");
									codHAux = ler.nextInt();

									h = hDAO.buscarHobbies(codHAux); 

									if(h != null) {
										userHobbieDAO.incluirUsuarioHobbies(user.getNickname(), codHAux); /* vinculando usuario + hobbies */
										System.out.println("\tCodigo cadastrado. ");
									}else {
										System.out.println("\t*Codigo invalido, Tente novamente");
									}
								}while(h == null);
							}
							System.out.println("\n\tUsuario cadastrado com sucesso!"
									+ "*\n\tFaça o LOGIN para ter acesso aos seus MATCHS.*");
						}
					}else {
						System.out.println("\n\n\tVoltando ao MENU PRINCIPAL");
					}
				}else {
					// login de usuario //
					System.out.print("\tDigite a senha: ");
					senhaAux = ler.next();
					System.out.println("\t--------------------------------");

					if(senhaAux.equalsIgnoreCase(user.getSenha())) { 

						System.out.println("\n\n\t\tOlá, " + user.getNickname().toUpperCase() + "! Bem vindo(a), ao AMOR ÀS CEGAS.");
						System.out.println("\t\tO que voce esta procurando hoje?");
						System.out.println("\t\t--------------------------------");
						System.out.println("\t\t1 - Uma noite...");
						System.out.println("\t\t2 - Ficante");
						System.out.println("\t\t3 - Namorado(a)");
						System.out.println("\t\t--------------------------------");
						System.out.print("\t\t> ");
						mood = ler.nextInt(); /* mood sera parametro para a filtragem de match */
						
						System.out.println("\n\t\tIncrivel, vamos achar o MATCH perfeito para você!");
						
						do {
							System.out.println("\n\n\t\t---- Menu USUARIO ----");
							System.out.println("\t\t--------------------------------");
							System.out.println("\t\t1 - Ver Meu Perfil");
							System.out.println("\t\t2 - Adicionar Hobbies");
							System.out.println("\t\t3 - Ver Meus Hobbies"); 
							System.out.println("\t\t4 - Ver Meus Matchs"); 
							System.out.println("\t\t5 - Alterar DADOS e HOBBIES");
							System.out.println("\t\t6 - Area Usuarios VIPS"); 
							System.out.println("\t\t7 - Excluir Meu Perfil");
							System.out.println("\t\t8 - Logout");
							System.out.println("\t\t--------------------------------");
							System.out.print("\t\t> ");
							op2 = ler.nextInt();

							switch(op2) {
							case 1:
								// perfil de usuario //
								System.out.println("\n\n\t\t--------------------------------");
								System.out.println("\t\tMEU PERFIL ");
								System.out.println("\t\t--------------------------------");
								System.out.println("\t\tNickname: " + user.getNickname().toUpperCase());
								System.out.println("\t\tNome: " + user.getNome() + "   \tIdade: " + user.getIdade());
								System.out.println("\t\tGenero: " + user.getGenero() + "   \tOrientacao Sexual: " + user.getOrientacaoSexual());
								System.out.println("\t\tDescricao Hobbies: " + user.getListaHobbies().get(0).getDescricao() + ", "
										+ "\n\t\t" + user.getListaHobbies().get(1).getDescricao() + ", " + user.getListaHobbies().get(2).getDescricao() + "...");
								System.out.println("\t\t--------------------------------");
								break;


							case 2:
								System.out.println("\n\n\t\t-- Cadastro HOBBIES --");
								System.out.println("\t\t--------------------------------");
								System.out.println("\t\tPara cadastrar seus hobbies, responda o "
										+ "\n\t\tcom o codigo referente ao hobbie desejado.");

								listaHobbies = hDAO.emitirRelatorio(); /* preenchendo o arraylist com todos os hobbies existentes no banco */
								
								System.out.println("\n\n\t\t----------------------------------");
								System.out.println("\t\t\tLista de HOBBIES");
								System.out.println("\t\t----------------------------------");
								System.out.println("\t\tCODIGO" + "\t-" + "\tDESCRICAO");
								for(i=0; i<listaHobbies.size(); i++) { 
									System.out.println("\t\t----------------------------------");
									System.out.println("\t\t" + listaHobbies.get(i).getCod_hobbie() + "\t-\t" + listaHobbies.get(i).getDescricao());
								}
								System.out.println("\t\t----------------------------------");

								System.out.println("\n\t\tDigite o CODIGO referente ao Hobbie desejado "
										+ "\n\t\t\tOU DIGITE '1' para cadastrar um novo Hobbie ao banco"
										+ "\n\t\t\tOU DIGITE '0' para voltar ao MENU USUARIO.");
								System.out.print("\t\t> ");
								resp = ler.nextInt();
									
								if((resp != 1)){
									// cadastro de hobbies existentes //
									int pos = -1;
									
									for(i=0; i<listaHobbies.size(); i++) {
										for(j=0; j<user.getListaHobbies().size(); j++) {
												if(listaHobbies.get(i).getCod_hobbie() == resp && resp != user.getListaHobbies().get(j).getCod_hobbie()) {
													pos = i;
													break;
											}
										}
									} /* verifica se o hobbie existe no banco e guarda o indice do hobbie */

									if(pos == -1 ){
										System.out.println("\n\n\tCodigo invalido!!\n\t*Foi digitado um codigo inexistente ou tentou adicionar um hobbie já existente na sua lista*");
									} /* caso o codigo nao exista */ 

									if( pos != -1 ) {
										userHobbieDAO.incluirUsuarioHobbies(user.getNickname(), resp);
										user.adicionarListaHobbies(hDAO.buscarHobbies(resp));
									} /* caso o codigo exista, vincula usuario + hobbie e adiciona ao arraylist hobbies do usuario */
								}

								if(resp == 1) {
									// cadastro de hobbies personalizados //
									System.out.println("\n\n\t\t- Cadastrando um NOVO HOBBIE -");
									System.out.println("\t\t----------------------------------");
									System.out.print("\t\tDigite o codigo do Hobbie: ");
									int cod_hobbieAux = ler.nextInt();
									ler.nextLine();

									h = hDAO.buscarHobbies(cod_hobbieAux); /* verifica se o hobbie existe */

									if(h == null) {
										h = new Hobbies(); /* instaciando novo obj HOBBIE para preenche-lo */
										h.setCod_hobbie(cod_hobbieAux);

										System.out.print("\t\tDescricao: ");
										descricaoAux = ler.nextLine();
										h.setDescricao(descricaoAux); 
										System.out.println("\t\t----------------------------------");

										hDAO.incluirHobbies(h); /* incluindo hobbie preenchido ao banco */
										userHobbieDAO.incluirUsuarioHobbies(user.getNickname(), cod_hobbieAux); /* associando usuario + hobbie */ 
										user.adicionarListaHobbies(h); /*adicionando ao arraylist hobbies de usuario */ 

										System.out.println("\n\t\tNovo Hobbie cadastrado e adicionado a "
												+ "lista de Hobbies do usuario *COM SUCESSO*.");
									}else {
										System.out.println("\n\t\t*Já existe um hobbie com este codigo: " + h.getCod_hobbie() + 
												" - " + h.getDescricao() + ".*");
									}
								}

								break;
							case 3:
								// relatorio de HOBBIES do usuario //
								
								listaHobbies = userHobbieDAO.relatorioUsuario(user.getNickname()); /* busca todos os hobbies do usuario */

								System.out.println("\n\n\t\t--------------------------------");
								System.out.println("\t\tMEUS HOBBIES - Nickame: " + user.getNickname().toUpperCase());
								System.out.println("\t\t--------------------------------");
								System.out.println("\t\tCODIGO" + "\t-\t" + "DESCRICAO");
								System.out.println("\t\t--------------------------------");
								for( i=0; i < listaHobbies.size(); i++ ) {
									System.out.println("\t\t" + listaHobbies.get(i).getCod_hobbie() + "\t-\t" + listaHobbies.get(i).getDescricao());
								}
								System.out.println("\t\t--------------------------------");

								break;
							case 4:
								// relatorio de MATCHS do usuario //
								
								pgVIP = pgVIPDAO.buscarPagamentoVIP(user.getNickname()); /* verifica se é um usuario VIP */
								
								listaMatchs = matchs.filtragem(user, mood); /* busca os matchs filtrados */
								
								
								if(listaMatchs == null) {
									System.out.println("\n\n\t\t*Nao existem MATCHS para o seu perfil! Adicione MAIS HOBBIES.*");
								}else
									if( pgVIP == null || pgVIP.getPago() == false) { 
										/* se for nulo ou se o pagamento nao foi realizado, mostra apenas 1 match */
										System.out.println("\n\n\t\t--------------------------------");
										System.out.println("\t\tMATCH para: " + user.getNickname().toUpperCase());
										System.out.println("\t\t--------------------------------");
										System.out.println("\t\tNickname: " + listaMatchs.get(0).getNickname().toUpperCase());
										System.out.println("\t\tNome: " + listaMatchs.get(0).getNome() + "\tIdade: " + listaMatchs.get(0).getIdade());
										System.out.println("\t\tGenero: " + listaMatchs.get(0).getGenero() + "\tOrientacao sexual: " + listaMatchs.get(0).getOrientacaoSexual());
										System.out.println("\t\t--------------------------------");
										System.out.println("\n\t\t*Adquira o VIP para ter acesso à lista completa de MATCHS.*");
									}else { 
										/* se for um usuario vip, tera acesso a todo o arraylist de matchs */
										System.out.println("\n\n\t\t--------------------------------");
										System.out.println("\t\tMATCHS para: " + user.getNickname().toUpperCase());
										System.out.println("\t\t--------------------------------");
										for( i=0; i < listaMatchs.size(); i++ ) {
											System.out.println("\t\tNickname: " + listaMatchs.get(i).getNickname());
											System.out.println("\t\tNome: " + listaMatchs.get(i).getNome());
											System.out.println("\t\tIdade: " + listaMatchs.get(i).getIdade());
											System.out.println("\t\tGenero: " + listaMatchs.get(i).getGenero());
											System.out.println("\t\tOrientacao sexual: " + listaMatchs.get(i).getOrientacaoSexual());
											System.out.println("\t\t--------------------------------");
										}
									}
								

								break;
							case 5:
								do {
									System.out.println("\n\n\t\t---- Menu ALTERAR DADOS ----");
									System.out.println("\t\t--------------------------------");
									System.out.println("\t\t1 - Alterar DADOS DO PERFIL"); 
									System.out.println("\t\t2 - Alterar DADOS DO CARTAO"); 
									System.out.println("\t\t3 - Excluir HOBBIES do Meu Perfil");
									System.out.println("\t\t4 - Voltar ao MENU USUARIO");
									System.out.println("\t\t--------------------------------");
									System.out.print("\t\t> ");
									op3 = ler.nextInt();

									switch(op3) {
									case 1: 

										System.out.println("\n\n\t\tEscolha quais dados deseja alterar: ");
										System.out.println("\t\t--------------------------------");
										System.out.println("\t\t1- Alterar LOGIN (Nickname e Senha)"); 
										System.out.println("\t\t2- Alterar DADOS DO USUARIO");
										System.out.println("\t\t--------------------------------");
										System.out.print("\t\t> ");
										resp = ler.nextInt();

										switch(resp) {
										case 1:
											// alteracao de chave primaria //
											
											u1 = new Usuario(); /* instancia obj usuario para preenchelo*/

											System.out.print("\n\t\tDigite o novo Nickname: ");
											u1.setNickname(ler.next());
											System.out.print("\t\tDigite a nova Senha: ");
											u1.setSenha(ler.next());
											u1.setIdade(user.getIdade());
											u1.setGenero(user.getGenero());
											u1.setOrientacaoSexual(user.getGenero());

											System.out.print("\t\tDigite senha atual: ");
											senhaUser = ler.next();

											if(user.getSenha().equalsIgnoreCase(senhaUser)) {
												userDAO.atualizarDadosLogin(user.getNickname(), u1); /* chama metodo DAO update */
												user = userDAO.buscarUsuario(u1.getNickname()); /* atualiza o usuario logado */
												
												System.out.println("\n\t\tLogin do usuario atualizados com sucesso! Lembre-se desses dados!");

											}else {
												System.out.println("\n\t\tSenha incompativel. Tente novamente!");
											}

											break;
										case 2:
											u1 = new Usuario();
											u1.setNickname(user.getNickname());
											u1.setSenha(user.getSenha());
											
											System.out.print("\n\t\tDigite o novo nome: ");
											nomeAux = ler.next();
											u1.setNome(nomeAux);
											System.out.print("\t\tDigite a nova idade: ");
											idadeAux = ler.nextInt();
											u1.setIdade(idadeAux);
											System.out.print("\t\tDigite o novo genero"
													+ "\n\t\t\t'f' - feminino"
													+ "\n\t\t\t'm' - masculino"
													+ "\n\t\t\t> ");
											generoAux = ler.next();
											u1.setGenero(generoAux);
											System.out.print("\t\tDigite a nova orientacao sexual:"
													+ "\n\t\t\t'h' - hetero"
													+ "\n\t\t\t'b' - bisexual"
													+ "\n\t\t\t'g' - gay"
													+ "\n\t\t\t'l' - lesbica"
													+ "\n\t\t\t> ");
											orienSexAux = ler.next();
											u1.setOrientacaoSexual(orienSexAux);

											System.out.print("\n\t\tDigite senha atual: ");
											senhaUser = ler.next();

											if(user.getSenha().equalsIgnoreCase(senhaUser)) {
												userDAO.atualizarDadosUser(user.getNickname(), u1); /* atualiza dados usuario */
												user = userDAO.buscarUsuario(u1.getNickname()); /* atualiza usuario logado */
												
												System.out.println("\n\t\tDados do usuario " + user.getNickname().toUpperCase() + " atualizados com sucesso!");
											}else {
												System.out.println("\n\t\tSenha incorreta. Tente novamente!");
											}

											break;
										default: System.out.println("\n\n\t\tOpcao invalida. Tente novamente.");
										}


										break;
									case 2:
										pgVIP = pgVIPDAO.buscarPagamentoVIP(user.getNickname()); /* se é usuario vip */

										if(pgVIP == null || pgVIP.getPago() == false) {
											System.out.println("\n\t\t*Não é possivel concluir processo pois você não é um Usuario VIP!*");
											System.out.println("\n\t\tVoltando ao MENU ALTERAR DADOS");
										}else {
											System.out.println("\n\t\tOlá, " + pgVIP.getUser().getNickname().toUpperCase() + ", este é o cartão atual cadastrado no seu perfil:");
											System.out.println("\n\t\t\t--------------------------------");
											System.out.println("\n\t\t\tCPF: " + pgVIP.getCpf());
											System.out.println("\n\t\t\tNumero do cartao: " + pgVIP.getNumero_cartao());
											System.out.println("\n\t\t\t--------------------------------");

											System.out.println("\n\t\tCerteza que deseja mudar os dados do cartao? ('1' - sim | '0' - nao)");
											System.out.print("\n\t\t> ");
											resp = ler.nextInt();

											if(resp == 1) {
												System.out.print("\n\t\tDigite sua senha atual: ");
												senhaUser = ler.next();

												if(pgVIP.getUser().getSenha().equalsIgnoreCase(senhaUser)) {
													System.out.print("\n\t\tDigite o novo numero do cartao: ");
													numCartaoAux = ler.next();
													System.out.print("\n\t\tDigite a senha: ");
													senhaAux = ler.next();

													pgVIPDAO.atualizarDadosCartao(pgVIP, numCartaoAux, senhaAux);
												}else {
													System.out.println("\n\t\tSenha incorreta. Tente novamente!");
												}

											}else {
												System.out.println("\n\t\tVoltando ao MENU ALTERAR DADOS");
											}
										}

										break;
									case 3: 		
										System.out.println("\n\n\t\t--------------------------------");
										System.out.println("\t\tMEUS HOBBIES - Nickame: " + user.getNickname().toUpperCase());
										System.out.println("\t\t--------------------------------");
										System.out.println("\t\tCODIGO" + "\t-\t" + "DESCRICAO");
										System.out.println("\t\t--------------------------------");
										for( i=0; i < user.getListaHobbies().size(); i++ ) {
											System.out.println("\t\t" + user.getListaHobbies().get(i).getCod_hobbie() + "\t-\t" + user.getListaHobbies().get(i).getDescricao());
										}
										System.out.println("\t\t--------------------------------");
										do {
											System.out.print("\n\t\tDigite o CODIGO do Hobbie que deseja excluir do seu perfil: ");
											codHAux = ler.nextInt();

											h = hDAO.buscarHobbies(codHAux);
											if( h != null) {
												userHobbieDAO.excluir(user.getNickname(), codHAux);
												System.out.println("\n\t\tHobbie excluido com sucesso!");
											}else {
												System.out.println("\n\t\t*Codigo não existente!*");
											}
										}while(h == null);

										break;
									case 4: System.out.println("\n\n\t\tVoltando ao MENU USUARIO.");
									break;
									default: System.out.println("\n\tOpcao invalida! Tente novamente.");
									}

								}while(op3!=4);

								break;
							case 6:

								pgVIP = pgVIPDAO.buscarPagamentoVIP(user.getNickname());

								do {


									System.out.println("\n\n\t\t---- Menu USUARIOS VIPS ----");
									System.out.println("\t\t--------------------------------");
									System.out.println("\t\tAo adquirir o VIP, seu perfil poderá "
											+ "\n\t\tter mais possibilidades de MATCHS");
									System.out.println("\t\t1- Adquirir VIP");
									System.out.println("\t\t2- Cancelar VIP");
									System.out.println("\t\t3- Voltar ao MENU PRINCIPAL");
									System.out.println("\t\t--------------------------------");
									System.out.print("\t\t> ");
									op3 = ler.nextInt();

									switch(op3) {
									case 1: 

										if(pgVIP == null) {
											pgVIP = new PagamentoVIP();
											System.out.print("\n\t\tDigite seu CPF: ");
											cpfAux = ler.next();
											
											existe = pgVIPDAO.buscarPorCPF(cpfAux);
											
											if(!existe) {
												pgVIP.setCpf(cpfAux);
												System.out.print("\t\tDigite o numero do cartao: (16 digitos)"
														+ "\n\t\t> ");
												pgVIP.setNumero_cartao(ler.next());

												System.out.print("\t\tDigite a senha do cartao: ");
												pgVIP.setSenha_cartao(ler.next());
												pgVIP.setPago(true);
												pgVIP.setUser(user);

												pgVIPDAO.realizarPagamento(pgVIP, user.getNickname());

												System.out.println("Pagamento realizado com Sucesso!");
											}else {
												System.out.println("*Nao foi possivel concluir, CPF ja cadastrado!*");
											}
											
										}else {
											System.out.println("\nDigite a senha do cartao cadastrado: (10 digitos)");
											senhaAux = ler.next();

											if(senhaAux.equalsIgnoreCase(pgVIP.getSenha_cartao())) {
												pgVIPDAO.atualizarPagamento(pgVIP.getUser().getNickname(), pgVIP.getPago());
												System.out.println("Pagamento realizado com Sucesso!");
											}else {
												System.out.println("Senha incorreta operacao nao concluida");
											}
										}

										break;
									case 2: 		
										if(pgVIP != null) {
											System.out.println("\n\n\t\tCerteza que deseja cancelar assinatura VIP? (1-sim, 0-nao)");
											resp = ler.nextInt();

											if(resp == 1) {
												System.out.println("\n\t\tDigite sua senha de usuario: ");
												senhaAux = ler.next();
												if(senhaAux.equalsIgnoreCase(user.getSenha())) {
													pgVIP = pgVIPDAO.buscarPagamentoVIP(user.getNickname());
													pgVIPDAO.atualizarPagamento(pgVIP.getUser().getNickname(), pgVIP.getPago());
													System.out.println("\n\t\tOperacao concluida com sucesso");
												}else {
													System.out.println("\n\n\t\tSenha incorreta! Cancelamento nao realizado.");
												}
											}else {
												System.out.println("\n\t\t(0) - Operacao nao realizada.");
											}
										}else {
											System.out.println("\n\t\tPara cancelar algo, primeiro tem que comprar bobinho");
										}
										break;
									case 3: System.out.println("\n\n\tVoltando ao MENU USUARIO.");
									break;
									default: System.out.println("\n\t\tOpcao invalida. Tente novamente!");
									}

								}while(op3!=3);
								break;
							case 7:
								System.out.print("\n\n\tCerteza que deseja excluir sua conta ('1' - sim | '0' - nao)? ");
								resp = ler.nextInt();

								if(resp == 1) {
									System.out.print("\n\t\tDigite sua senha de usuario: ");
									senhaAux = ler.next();
									if(senhaAux.equalsIgnoreCase(user.getSenha())) {
										userDAO.excluirUsuario(user.getNickname());
										System.out.println("\n\n\t\tExclusao efetuada com sucesso.");
									}else {
										System.out.println("\n\n\t\t*Senha incorreta! Exclusao nao realizada.*");
									}
								}else {
									System.out.println("*\n\t\tExclusao nao realizada. Voltando ao MENU USUARIOS VIPS*");
								}

								break;
							case 8: System.out.println("\n\n\t\tVoltando ao MENU PRINCIPAL.");
							break;
							default: System.out.println("\n\tOpcao invalida! Tente novamente.");
							}
						}while(op2 != 8);

					}else {
						System.out.println("\t*Senha incorreta.*");
						System.out.println("\tVoltando ao MENU PRINCIAPAL");
					}

				}


				break;
			case 2:

				System.out.println("\n\n\t---- BUSCAR Usuario ----");
				System.out.println("\t--------------------------------");
				System.out.println("\tDigite o nickname do usuario desejado:");
				System.out.print("\t> ");
				nicknameAux = ler.next();

				user = userDAO.buscarUsuario(nicknameAux);

				if(user == null) {
					System.out.println("\n\n\t\t*Codigo incorreto OU usuario nao cadastrado!*");
				}else {
					System.out.println("\n\n\t\tUsuario "+ user.getNickname()+ " localizado!");
					System.out.println("\t\t--------------------------------");
					System.out.println("\t\tNome: "+ user.getNome());
					System.out.println("\t\tIdade: "+user.getIdade());
					System.out.println("\t\tGenero: "+user.getGenero());
					System.out.println("\t\tOrientacao Sexual: "+user.getOrientacaoSexual());
					System.out.println("\t\t-------------------------------");
				}

				break;
			case 3:
				do {
					System.out.println("\n\n\t---- Menu RELATORIOS ----");
					System.out.println("\t--------------------------------");
					System.out.println("\t1 - Relatorio TODOS OS USUARIOS");
					System.out.println("\t2 - Relatorio USUARIOS VIPS");
					System.out.println("\t3 - Voltar ao MENU PRINCIPAL");
					System.out.println("\t--------------------------------");
					System.out.print("\t> ");
					op2 = ler.nextInt();

					switch(op2) {
					case 1:

						listaUser = userDAO.emitirRelatorio();

						System.out.println("\n\n\t\t-----------------------------------");
						System.out.println("\t\tRelatorio TODOS OS USUARIOS");
						System.out.println("\t\t-----------------------------------");
						for(i=0; i<listaUser.size(); i++) {
							System.out.println("\t\tUSUARIO " + listaUser.get(i).getNickname());
							System.out.println("\t\tNome: " + listaUser.get(i).getNome() + "\tIdade: " + listaUser.get(i).getIdade());
							System.out.println("\t\tGenero: " + listaUser.get(i).getGenero() + "\tOrientacao Sexual: " + listaUser.get(i).getOrientacaoSexual());
							System.out.println("\t\t-----------------------------------");
						}
						break;
					case 2:

						listaPgVIP = pgVIPDAO.relatorioPagamentosVIP();

						System.out.println("\n\n\t\t-----------------------------------");
						System.out.println("\t\tRELATORIO USUARIOS VIPS ");
						System.out.println("\t\t-----------------------------------");
						for(i=0; i<listaPgVIP.size(); i++) {							
							System.out.println("\t\tUSUARIO " + listaPgVIP.get(i).getUser().getNickname());
							System.out.println("\t\tCPF: " + listaPgVIP.get(i).getCpf());
							System.out.println("\t\tNumero Cartao: " + listaPgVIP.get(i).getNumero_cartao());
							System.out.println("\t\tStatus atual: " + listaPgVIP.get(i).getPago());
							System.out.println("\t\t-----------------------------------");
						}
						break;
					case 3: System.out.println("\n\tVoltando ao MENU PRINCIPAL");
					break;
					default: System.out.println("\n\tOpcao invalida! Tente novamente");
					}

				}while(op2!=3);

				break;
			case 4: System.out.println("\n\nFIM DE PROGRAMA");
			break;
			default: System.out.println("\n\tOpcao invalida! Tente novamente.");
			}

		}while(op != 4);

	}
}