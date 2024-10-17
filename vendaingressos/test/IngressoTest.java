
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Calendar;
import java.util.Date;



// refazer
public class IngressoTest {

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
    public void testCriarIngresso() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();
        
        Evento evento = new Evento("Show de Rock", "Banda XYZ", data,100);
        Ingresso ingresso = new Ingresso(evento, 100.0);

        assertNotNull(ingresso);
        assertEquals(evento.getID(), ingresso.getEventoID());
        assertEquals(100.0, ingresso.getPreco(), 0.0001);
      //  assertEquals("A1", ingresso.getAssento());
        assertTrue(ingresso.isAtivo());
    }

    @Test
    public void testCancelarIngresso() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        Evento evento = new Evento("Show de Rock", "Banda XYZ", data,100);
        Ingresso ingresso = new Ingresso(evento, 100.0);

        ingresso.setStatus(false);
        
        assertFalse(ingresso.isAtivo());
    }

   /*@Test
    public void testCancelarIngressoEventoPassado() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 10);
        Date data = calendar.getTime();

        Evento evento = new Evento("Show de Rock", "Banda XYZ", data);
        Ingresso ingresso = new Ingresso(evento, 100.0);

        assertFalse(ingresso.cancelar());
        assertTrue(ingresso.isAtivo());
    } */

   /*  @Test
    public void testReativarIngresso() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        Evento evento = new Evento("Show de Rock", "Banda XYZ", data);
        Ingresso ingresso = new Ingresso(evento, 100.0, "A1");

        ingresso.cancelar();
        assertFalse(ingresso.isAtivo());

        ingresso.reativar();
        assertTrue(ingresso.isAtivo());
    } */

   /*  @Test
    public void testIngressoDuplicado() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        Evento evento = new Evento("Show de Rock", "Banda XYZ", data,100);
        Ingresso ingresso1 = new Ingresso(evento, 100.0);
        Ingresso ingresso2 = new Ingresso(evento, 100.0);

        assertEquals(ingresso1, ingresso2);
        assertEquals(ingresso1.hashCode(), ingresso2.hashCode());
    } */
}