/**
 * Tässä yksinkertaiset testit pääohjelman metodeille.
 * En ole koskaan aiemmin kirjoittanut testejä javalla.
 * Created by Olli Ritari on 24/02/2018.
 */

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.*;
import java.util.*;

public class PrintDfTest {
    String wantedLine = "testing text";
    String wrongLine = "wrong text";
    ArrayList<String> mockList = new ArrayList<String>();

    @Test
    public void testGetLine() {
        mockList.add("headerline");
        mockList.add("testing text");
        PrintDf printDf = new PrintDf();
        assertTrue(printDf.getLine(wantedLine,mockList));
        assertFalse(printDf.getLine(wrongLine, mockList));
    }

    @Test
    public void testRemoveLine() {
        mockList.add("headerline");
        mockList.add("testing text");
        PrintDf printDf = new PrintDf();
        assertTrue(printDf.removeLine(wantedLine,mockList));
        assertFalse(printDf.removeLine(wrongLine, mockList));
    }
}
