import java.util.Date;


public class Recibo {
    private String fullName;
    private String cpf;
    private String email;
    private Ingresso ingresso;
    private String eventoID;
    private String Pagamento;
    private Date data;
    

    public Recibo (String fullName, String cpf, String email, Ingresso ingresso, String pagamento, String eventoID, Date data){
        this.fullName = fullName;
        this.cpf = cpf;
        this.email = email;
        this.ingresso = ingresso;
        this.Pagamento = pagamento;
        this.eventoID = eventoID;
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
    
    public String getEventoID() {
        return eventoID;
    }


    public Date getData() {
        return data;
    }

    public String Pagamento() {
        return Pagamento;
    }
}
