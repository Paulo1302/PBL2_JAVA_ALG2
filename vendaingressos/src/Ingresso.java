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
import java.util.UUID;



/**
 * A classe {@code Ingresso} representa um ingresso de evento. Cada ingresso tem 
 * informações sobre o evento, preço, assento e status (ativo ou cancelado).
 */
public class Ingresso {
    private String EventoID; 
    private double Preco;
    private String IngressoID; 
    private boolean Status;

    /**
     * Constrói um ingresso com preço inicial de 0.0.
     * 
     * @param Evento o evento associado ao ingresso
     * @param Assento o assento associado ao ingresso
     */
    public Ingresso(Evento evento) {
        this.EventoID = evento.getID();
        this.Preco = 0.0;
        this.Status = true;
        this.IngressoID = gerarId(evento);
    }




    /**
     * Constrói um ingresso com um preço específico.
     * 
     * @param Evento o evento associado ao ingresso
     * @param Preco o preço do ingresso
     * @param Assento o assento associado ao ingresso
     */
    public Ingresso(Evento evento, double Preco) {
        this.EventoID = evento.getID();
        this.Preco = Preco;
        this.Status = true;
        this.IngressoID = gerarId(evento);
    }


    private String gerarId(Evento evento) {
        Date data = evento.getData();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String dataEventoString = sdf.format(data);
        String uuidString = UUID.randomUUID().toString();
        return dataEventoString + "-" + uuidString;
    }
    
    /**
     * Retorna o preço do ingresso.
     * 
     * @return o preço
     */
    public double getPreco() {
        return Preco;
    }

    public String getEventoID(){
        return this.EventoID;
    }

    public String getId(){
        return this.IngressoID;
    }

    /**
     * Verifica se o ingresso está ativo.
     * 
     * @return {@code true} se o ingresso está ativo, {@code false} se está cancelado
     */
    public boolean isAtivo() {
        return Status;
    }

    public Boolean setStatus(Boolean status){
        this.Status = status;
        return status;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((EventoID == null) ? 0 : EventoID.hashCode());
        result = prime * result + ((IngressoID == null) ? 0 : IngressoID.hashCode());
        result = prime * result + (Status ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ingresso other = (Ingresso) obj;
        if (EventoID == null) {
            if (other.EventoID != null)
                return false;
        } else if (!EventoID.equals(other.EventoID))
            return false;
        if (IngressoID == null) {
            if (other.IngressoID != null)
                return false;
        } else if (!IngressoID.equals(other.IngressoID))
            return false;
        if (Status != other.Status)
            return false;
        return true;
    }
}