import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Armazenamento {
        
    public void ArmazenamentoUser (Usuario usuario){
        Gson gsonFile = new Gson();
        String jsonFile = gsonFile.toJson(usuario);
        String UserCPF = usuario.getCpf();
        
        String caminhoArquivo = "vendaingressos\\Dados\\Usuarios\\" + UserCPF + ".json";

        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            writer.write(jsonFile);
            System.out.println("Dados Armazenados com sucesso!");
        } catch (IOException erro) {
            erro.printStackTrace();
        }
    }

    public Usuario LerArquivoUsuario (String cpf){
        Gson gson = new Gson();
        String jsonFile = "vendaingressos/Dados/Usuarios/" + cpf + ".json";
        try (FileReader reader = new FileReader(jsonFile)) {
           Usuario usuario = gson.fromJson(reader, Usuario.class);
            System.out.println("Dados recuperados com sucesso!");
            return usuario;
        } catch (IOException | JsonSyntaxException erro) {
            erro.printStackTrace();
            return null;
        }
    }
    
    public void ArmazenarEvento (Evento evento){
        Gson gsonFile = new Gson();
        String jsonFile = gsonFile.toJson(evento);
        String eventoID = evento.getID();
        
        String caminhoArquivo = "vendaingressos\\Dados\\Eventos\\" + eventoID + ".json";

        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            writer.write(jsonFile);
            System.out.println("Dados Armazenados com sucesso!");
        } catch (IOException erro) {
            erro.printStackTrace();
        }
    }

    
    public Evento LerArquivoEvento (String eventoid){
        
        Gson gson = new Gson();
        String jsonFile = "vendaingressos/Dados/Eventos/" + eventoid + ".json";
        try (FileReader reader = new FileReader(jsonFile)) {
           Evento evento = gson.fromJson(reader, Evento.class);
            System.out.println("Dados recuperados com sucesso!");
            return evento;
        } catch (IOException | JsonSyntaxException erro) {
            erro.printStackTrace();
            return null;
        }
    }
}
