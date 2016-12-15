package view;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import model.SourceCode;
import model.MutantStatus;
import model.SourceClass;
import view.MutantColor;
import java.awt.Color;
import model.Summary;

/**
 * Test class for the CodeLine implementation.
 * @author mlimbird
 */
public class CodeLineTest {

    /*
     * Test the constructor
     */
    @Test
    public void testConstructor() {
        String code = "the code";
        int lineNum = 3;
        int maxLines = 3;

        CodeLine cl = new CodeLine(code, lineNum, maxLines);

        //Check the label's string
        String actualText = cl.getText();
        String expectedText = "3 | the code";
        assertEquals(expectedText, actualText);

        //Check the label's font
        Font expectedFont = new Font("Monospaced", Font.PLAIN, 12);
        Font actualFont = cl.getFont();
        assertEquals(expectedFont, actualFont);

        //Check the line numbers
        int actualLineNumber = cl.getLineNumber();
        assertEquals(lineNum, actualLineNumber);

        //Check targets
        List<SourceCode> expectedTargets = new ArrayList<SourceCode>();
        List<SourceCode> actualTargets = cl.getTargets();
        assertEquals(expectedTargets, actualTargets);
    }

    @Test
    public void testSetStatus() {
        String code = "the code";
        int lineNum = 3;
        int maxLines = 3;

        CodeLine cl = new CodeLine(code, lineNum, maxLines);
        MutantStatus ms = MutantStatus.LIVE;

        cl.setStatus(ms);

        //Check background
        Color expectedBackground = MutantColor.getColor(ColorContext.HIGHLIGHT, ms);
        Color actualBackground = cl.getBackground();
        assertEquals(expectedBackground, actualBackground);
    }

    @Test
    public void testAddTarget() {
        String code = "the code";
        int lineNum = 3;
        int maxLines = 3;

        CodeLine cl = new CodeLine(code, lineNum, maxLines);

        List<SourceCode> expectedTargets = new ArrayList<SourceCode>();
        SourceCode newTarget = new SourceClass("className", "src", new Summary(0,0,0,0));
        expectedTargets.add(newTarget);

        cl.addTarget(newTarget);
        List<SourceCode> actualTargets = cl.getTargets();
        assertEquals(expectedTargets, actualTargets);
    }
}