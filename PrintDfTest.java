/**
 * Erittäin simppeli hahmotelma pääohjelman testaamiseksi.
 * En ole koskaan aiemmin kirjoittanut testejä javalla.
 * Löysin mockito-kirjaston jolla voi testata mm. alkaako tietty String tietyllä tavalla.
 * Tämän opetteluun olisi mennyt kuitenkin pidempään kuin tähän tehtävään on järkevää käyttää.
 * Created by Olli Ritari on 24/02/2018.
 */

import java.io.PrintStream;
import org.junit.Test;
import static org.mockito.*;

public class PrintDfTest {
    
    @Test
    public void testMain() {
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        testClass.main(new String[]{});
        verify(out).println(startsWith("Filesystem"));
    }
}