import java.util.Date;
import java.util.List;


/**
 * Classe responsável pelo controle de operações relacionadas a eventos, 
 * usuários e ingressos.
 */
public class Controller {

    
    /**
     * Cadastra um novo usuário no sistema.
     * 
     * @param username o nome de usuário do novo usuário
     * @param senha a senha do usuário
     * @param nome o nome completo do usuário
     * @param cpf o CPF do usuário
     * @param email o e-mail do usuário
     * @param ativo indica se o usuário está ativo
     * @return o objeto {@code Usuario} criado
     */
    public Usuario cadastrarUsuario(String username, String senha, String nome, String cpf, String email, boolean ativo) {
        return new Usuario(username, senha, nome, cpf, email, ativo);
    }

    /**
     * Cadastra um novo evento no sistema, desde que o usuário seja administrador.
     * 
     * @param admin o usuário que está tentando cadastrar o evento
     * @param nome o nome do evento
     * @param descricao a descrição do evento
     * @param data a data do evento
     * @return o objeto {@code Evento} criado
     * @throws SecurityException se o usuário não for administrador
     */
   
    public Evento cadastrarEvento(Usuario admin, String nome, String descricao, Date data, int ingressos, Armazenamento dados) throws SecurityException { 
        if (admin.isAdmin()) {
            Evento evento = new Evento(nome, descricao, data, ingressos);
            dados.ArmazenarEvento(evento);
            return evento;
        } else {
            throw new SecurityException("Somente administradores podem cadastrar eventos.");
        }
    } 

    /**
     * Procura um evento pelo nome.
     * 
     * @param nomeEvento o nome do evento a ser procurado
     * @return o evento correspondente ou {@code null} se não for encontrado
     */
   /*  public Evento procuraEvento(String nomeEvento) {
        for (Evento evento : listaEventos) {
            if (evento.getNome().equals(nomeEvento)) {
                return evento;
            }
        }
        System.out.println("Evento Não encontrado!");
        return null; 
    } */

    /**
     * Adiciona um assento a um evento.
     * 
     * @param nomeEvento o nome do evento
     * @param assento o nome do assento a ser adicionado
     */
   /* public void adicionarAssentoEvento(String nomeEvento, String assento) {
        Evento evento = this.procuraEvento(nomeEvento);
        evento.adicionarAssento(assento);
    } */

    /**
     * Compra um ingresso para um usuário.
     * 
     * @param usuario o usuário que está comprando o ingresso
     * @param evento o nome do evento para o qual o ingresso está sendo comprado
     * @param assento o assento escolhido para o evento
     * @return o objeto {@code Ingresso} comprado
     */
    public Ingresso comprarIngresso(Usuario usuario, String eventoID, Armazenamento dados) {
        Evento EventoAtual = dados.LerArquivoEvento(eventoID);
        Ingresso ingresso = new Ingresso(EventoAtual);
        
        EventoAtual.setIngressos(EventoAtual.getIngressos()-1);
        usuario.adicionarIngresso(ingresso);
        
        dados.ArmazenamentoUser(usuario);
        dados.ArmazenarEvento(EventoAtual);
        
        return ingresso;
    }

    /**
     * Cancela a compra de um ingresso de um usuário.
     * 
     * @param usuario o usuário que está cancelando a compra
     * @param ingresso o ingresso que está sendo cancelado
     * @return {@code true} se o cancelamento foi bem-sucedido, {@code false} caso contrário
     */
    public boolean cancelarCompra(Usuario usuario, Ingresso ingresso, Date data, Armazenamento dados) {
        String eventoId = ingresso.getEventoID();
        Evento evento = dados.LerArquivoEvento(eventoId);
        if(usuario.removeIngresso(ingresso, evento, data)) {
        evento.setIngressos(evento.getIngressos()+1);
        dados.ArmazenarEvento(evento);
        dados.ArmazenamentoUser(usuario);
        }
    
        return usuario.removeIngresso(ingresso, evento, data);
    }

    /**
     * Lista os ingressos comprados por um usuário.
     * 
     * @param usuario o usuário cujos ingressos serão listados
     * @return a lista de ingressos comprados pelo usuário
     */
    public List<Ingresso> listarIngressosComprados(Usuario usuario) {
        return usuario.getIngressos();
    }

    /**
     * Lista todos os eventos disponíveis.
     * 
     * @return a lista de eventos disponíveis
     */
   /*  public List<Evento> listarEventosDisponiveis() {
        return listaEventos;
    } */



    // ATUALIZAÇÕES DO CÓDIGO

    public void NovoCadastroUsuario(Usuario usuario, String username, String senha, String nome, String email) {
        usuario.atualizarDados(username, senha, nome, email);
    }

    public void ArmazenarDadosUsuario(Usuario usuario, Armazenamento dados){
        dados.ArmazenamentoUser(usuario);
    }

    public Usuario LerDadosUsuario(String cpf, Armazenamento dados){
        return dados.LerArquivoUsuario(cpf);
    }

    public void ArmazenarEvento(Evento evento, Armazenamento dados){
        dados.ArmazenarEvento(evento);
    }
}