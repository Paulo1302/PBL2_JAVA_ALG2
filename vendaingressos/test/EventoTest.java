
import java.util.Date;
import java.io.File;
import java.util.Calendar;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class EventoTest {

     private void deleteFilesInDirectory(File directory) {
        if (directory.isDirectory()) {
            for (File subFile : directory.listFiles()) {
                if (subFile.isFile() && subFile.getName().endsWith(".json")) {
                    subFile.delete();
                } else if (subFile.isDirectory()) {
                    deleteFilesInDirectory(subFile); // Recursivamente deletar arquivos em subdiretórios
                }
            }
        }
    }

    // Método para limpar arquivos JSON após cada teste
    @After
    public void cleanUp() {
        File directoryEvento = new File("vendaingressos/Dados/Eventos");
        deleteFilesInDirectory(directoryEvento);

        File directoryUser = new File("vendaingressos/Dados/Usuarios");
        deleteFilesInDirectory(directoryUser);
    }



    @Test
    public void testCriarEvento() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date data = calendar.getTime();

        Evento evento = new Evento("Show de Rock", "Banda XYZ", data,100);

        assertNotNull(evento);
        assertEquals("Show de Rock", evento.getNome());
        assertEquals("Banda XYZ", evento.getDescricao());
        assertEquals(100, evento.getIngressos());
        assertEquals(data, evento.getData());
        
    }

   /*  @Test
    public void testAdicionarAssento() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        Evento evento = new Evento("Show de Rock", "Banda XYZ", data);
        evento.adicionarAssento("A1");

        List<String> assentos = evento.getAssentosDisponiveis();
        assertTrue(assentos.contains("A1"));
    } */

   /*  @Test
    public void testRemoverAssento() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        Evento evento = new Evento("Show de Rock", "Banda XYZ", data);
        evento.adicionarAssento("A1");
        evento.removerAssento("A1");

        List<String> assentos = evento.getAssentosDisponiveis();
        assertFalse(assentos.contains("A1"));
    } */

    @Test
    public void testEventoAtivo() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2033, Calendar.SEPTEMBER, 10);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date data = calendar.getTime();

        Evento evento = new Evento("Show de Rock", "Banda XYZ", data, 100);

        assertTrue(evento.isAtivo(data));
    }

   @Test
    public void testEventoInativo() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 10);
        Date data = calendar.getTime();

        Calendar calendario = Calendar.getInstance();
        calendario.set(2024, Calendar.AUGUST, 10);
        Date dataSet = calendario.getTime();

        Evento evento = new Evento("Show de Rock", "Banda XYZ", data, 100);
        assertFalse(evento.isAtivo(dataSet));
    }

    @Test
    public void ArmazenamentoDadosEvento(){
        Controller controller = new Controller();
        Armazenamento dados = new Armazenamento();

        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        Evento evento = controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", data, 100, dados);
        

        controller.ArmazenarEvento(evento, dados);
    }
}
