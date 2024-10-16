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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * A classe {@code Evento} representa um evento disponível para a venda de ingressos.
 * Cada evento possui um nome, descrição, data e uma lista de assentos disponíveis.
 */
public class Evento {
    private String ID;
    private String Nome;
    private String Descricao;
    private Date Data;
    private int QuantidadeIngressos;
    private Map<String, String> avaliacoes;

    /**
     * Construtor da classe {@code Evento}.
     *
     * @param Nome o nome do evento
     * @param Descricao a descrição do evento
     * @param Data a data em que o evento ocorrerá
     */
    public Evento(String Nome, String Descricao, Date Data, int ingressos) {
        this.ID = gerarId(Data, Nome);
        this.Nome = Nome;
        this.Descricao = Descricao;
        this.Data = ajustarData(Data);
        this.QuantidadeIngressos = ingressos;
        this.avaliacoes = new HashMap<>();
    }

    private String gerarId(Date data, String Nome) {
        // Formatar a data para pegar os dois últimos dígitos do ano, mês e dia
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String dataEventoString = sdf.format(data);
        return Nome + "-" + dataEventoString;
    }

    private Date ajustarData(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public String getID() {
        return ID;
    }

    /**
     * Retorna o nome do evento.
     *
     * @return o nome do evento
     */
    public String getNome() {
        return Nome;
    }

    /**
     * Retorna a descrição do evento.
     *
     * @return a descrição do evento
     */
    public String getDescricao() {
        return Descricao;
    }

    /**
     * Retorna a data do evento.
     *
     * @return a data do evento
     */
    public Date getData() {
        return Data;
    }

    public int getIngressos(){
        return this.QuantidadeIngressos;
    }

    public void setIngressos(int ingressos){
        this.QuantidadeIngressos = ingressos;
    }

    /**
     * Verifica se o evento está ativo com base na data atual.
     * O evento é considerado ativo se a data do evento for posterior à data de referência (10 de agosto de 2024).
     *
     * @return {@code true} se o evento está ativo, {@code false} caso contrário
     */
    public boolean isAtivo(Date data) {
        if(this.Data.before(data)){
            return false;
        }else{
        return true;}
    }

    public void adicionarAvaliacao(String usuario, String avaliacao) {
        avaliacoes.put(usuario, avaliacao);
    }

    public void exibirAvaliacoes() {
        for (Map.Entry<String, String> usuario : avaliacoes.entrySet()) {
            System.out.println("Usuário: " + usuario.getKey() + " - Avaliação: " + usuario.getValue());
        }
    }
}
