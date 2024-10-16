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

/**
 * A classe {@code Ingresso} representa um ingresso de evento. Cada ingresso tem 
 * informações sobre o evento, preço, assento e status (ativo ou cancelado).
 */
public class Ingresso {
    private Evento Evento; 
    private double Preco;
    private String Assento;
    private boolean Status;

    /**
     * Constrói um ingresso com preço inicial de 0.0.
     * 
     * @param Evento o evento associado ao ingresso
     * @param Assento o assento associado ao ingresso
     */
    public Ingresso(Evento Evento, String Assento) {
        this.Evento = Evento;
        this.Preco = 0.0;
        this.Assento = Assento;
        this.Status = true;
    }

    /**
     * Constrói um ingresso com um preço específico.
     * 
     * @param Evento o evento associado ao ingresso
     * @param Preco o preço do ingresso
     * @param Assento o assento associado ao ingresso
     */
    public Ingresso(Evento Evento, double Preco, String Assento) {
        this.Evento = Evento;
        this.Preco = Preco;
        this.Assento = Assento;
        this.Status = true;
    }

    /**
     * Retorna o evento associado ao ingresso.
     * 
     * @return o evento
     */
    public Evento getEvento() {
        return Evento;
    }
    
    /**
     * Retorna o preço do ingresso.
     * 
     * @return o preço
     */
    public double getPreco() {
        return Preco;
    }

    /**
     * Retorna o assento associado ao ingresso.
     * 
     * @return o assento
     */
    public String getAssento() {
        return Assento;
    }

    /**
     * Verifica se o ingresso está ativo.
     * 
     * @return {@code true} se o ingresso está ativo, {@code false} se está cancelado
     */
    public boolean isAtivo() {
        return Status;
    }

    /**
     * Cancela o ingresso se o evento ainda estiver ativo.
     * 
     * @return {@code true} se o ingresso foi cancelado com sucesso, {@code false} se o evento não estiver ativo
     */
    public boolean cancelar() {
        if (Evento.isAtivo()) {
            this.Status = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Reativa o ingresso, tornando-o ativo novamente.
     * 
     * @return o status do ingresso após a reativação
     */
    public boolean reativar() {
        this.Status = true;
        return Status;
    }

    /**
     * Gera um código hash baseado nos atributos do ingresso, 
     * garantindo que objetos com os mesmos valores tenham o mesmo hash.
     * 
     * @return o código hash do ingresso
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Evento == null) ? 0 : Evento.hashCode());
        long temp;
        temp = Double.doubleToLongBits(Preco);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((Assento == null) ? 0 : Assento.hashCode());
        result = prime * result + (Status ? 1231 : 1237);
        return result;
    }

    /**
     * Verifica se este ingresso é igual a outro objeto.
     * 
     * @param obj o objeto a ser comparado
     * @return {@code true} se os objetos são iguais, {@code false} caso contrário
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ingresso other = (Ingresso) obj;
        if (Evento == null) {
            if (other.Evento != null)
                return false;
        } else if (!Evento.equals(other.Evento))
            return false;
        if (Double.doubleToLongBits(Preco) != Double.doubleToLongBits(other.Preco))
            return false;
        if (Assento == null) {
            if (other.Assento != null)
                return false;
        } else if (!Assento.equals(other.Assento))
            return false;
        if (Status != other.Status)
            return false;
        return true;
    }
}