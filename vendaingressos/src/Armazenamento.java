import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    /**
     * Lista todos os arquivos JSON na pasta Eventos com data posterior à data atual.
     *
     * @return uma lista com os nomes dos arquivos JSON na pasta Eventos com data posterior à data atual
     */
    public List<String> listarEventosDisponiveis() {
        List<String> arquivosJson = new ArrayList<>();
        File diretorio = new File("vendaingressos/Dados/Eventos");
        Date dataAtual = new Date();

        if (diretorio.exists() && diretorio.isDirectory()) {
            File[] arquivos = diretorio.listFiles();

            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    if (arquivo.isFile() && arquivo.getName().endsWith(".json")) {
                        String nomeArquivo = arquivo.getName();
                        String dataString = nomeArquivo.substring(0, 6); // Pega os primeiros 6 caracteres (yymmdd)
                        try {
                            Date dataArquivo = new SimpleDateFormat("yyMMdd").parse(dataString);
                            if (dataArquivo.after(dataAtual)) {
                                arquivosJson.add(nomeArquivo);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } 
                }
            }
        }

        return arquivosJson;
    }
}
