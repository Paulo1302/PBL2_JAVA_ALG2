/*******************************************************************************
#Autor: Paulo Gabriel da Rocha Costa Silva
#Componente Curricular: EXA 863 - MI - Programação
#Concluido em: 16/09/2024


#Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
#trecho de código de outro colega ou de outro autor, tais como provindos de livros e
#apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
#de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
#do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
#******************************************************************************************/


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Classe responsável pelo controle de operações relacionadas a eventos, 
 * usuários e ingressos.
 */
public class Controller {
    // Armazenamento dos eventos
    private List<Evento> listaEventos = new ArrayList<>();
    
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
    public Evento cadastrarEvento(Usuario admin, String nome, String descricao, Date data) throws SecurityException { 
        if (admin.isAdmin()) {
            Evento evento = new Evento(nome, descricao, data);
            this.listaEventos.add(evento);
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
    public Evento procuraEvento(String nomeEvento) {
        for (Evento evento : listaEventos) {
            if (evento.getNome().equals(nomeEvento)) {
                return evento;
            }
        }
        System.out.println("Evento Não encontrado!");
        return null; 
    }

    /**
     * Adiciona um assento a um evento.
     * 
     * @param nomeEvento o nome do evento
     * @param assento o nome do assento a ser adicionado
     */
    public void adicionarAssentoEvento(String nomeEvento, String assento) {
        Evento evento = this.procuraEvento(nomeEvento);
        evento.adicionarAssento(assento);
    }

    /**
     * Compra um ingresso para um usuário.
     * 
     * @param usuario o usuário que está comprando o ingresso
     * @param evento o nome do evento para o qual o ingresso está sendo comprado
     * @param assento o assento escolhido para o evento
     * @return o objeto {@code Ingresso} comprado
     */
    public Ingresso comprarIngresso(Usuario usuario, String evento, String assento) {
        Evento EventoAtual = this.procuraEvento(evento);
        Ingresso ingresso = new Ingresso(EventoAtual, assento);
        usuario.adicionarIngresso(ingresso);

        return ingresso;
    }

    /**
     * Cancela a compra de um ingresso de um usuário.
     * 
     * @param usuario o usuário que está cancelando a compra
     * @param ingresso o ingresso que está sendo cancelado
     * @return {@code true} se o cancelamento foi bem-sucedido, {@code false} caso contrário
     */
    public boolean cancelarCompra(Usuario usuario, Ingresso ingresso) {
        return usuario.removeIngresso(ingresso);
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
    public List<Evento> listarEventosDisponiveis() {
        return listaEventos;
    }



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