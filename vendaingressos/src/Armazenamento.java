import java.io.File;
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

        String pastaEvento = evento.getID().substring(0, 4);
        String caminhoPasta = "vendaingressos/Dados/Eventos/" + pastaEvento;
        File pasta = new File(caminhoPasta);

        if (!pasta.exists()) {
            if (!pasta.mkdirs()) {
                System.out.println("Falha ao criar a pasta.");
                return;
            }
        }
        
        String caminhoArquivo = caminhoPasta + '/' + eventoID + ".json";

        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            writer.write(jsonFile);
            System.out.println("Dados Armazenados com sucesso!");
        } catch (IOException erro) {
            erro.printStackTrace();
        }
    }


}
