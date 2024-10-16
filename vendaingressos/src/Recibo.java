import java.util.Date;


public class Recibo {
    private String fullName;
    private String cpf;
    private String email;
    private Ingresso ingresso;
    private Evento evento;
    private Date data;
    

    public Recibo (String fullName, String cpf, String email, Ingresso ingresso, Evento evento, Date data){
        this.fullName = fullName;
        this.cpf = cpf;
        this.email = email;
        this.ingresso = ingresso;
        this.evento = evento;
        this.data = data;    
    }


    public String getFullName() {
        return fullName;
    }


    public String getCpf() {
        return cpf;
    }


    public String getEmail() {
        return email;
    }


    public Ingresso getIngresso() {
        return ingresso;
    }
    
    public Evento getEvento() {
        return evento;
    }


    public Date getData() {
        return data;
    }

}
