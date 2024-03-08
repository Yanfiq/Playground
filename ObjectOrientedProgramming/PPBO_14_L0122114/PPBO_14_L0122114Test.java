package PPBO_14_L0122114;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LaptopTest {

    private Laptop laptop;

    @BeforeEach
    public void setUp() {
        laptop = new Laptop("BrandA", "ModelX", 2022);
    }

    @Test
    public void testPrintData() {
        String expectedOutput = "Brand : BrandA\n" +
                                "Model : ModelX\n" +
                                "Tahun : 2022\n\n";
        assertEquals(expectedOutput, getPrintedDataFromLaptop());
    }

    @Test
    public void testGetObject() {
        assertEquals(laptop, Laptop.getObject(0));
        assertNull(Laptop.getObject(1));
    }

    private String getPrintedDataFromLaptop() {
        String printedData;
        try (java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
             java.io.PrintStream originalOut = System.out) {
            System.setOut(new java.io.PrintStream(outContent));
            laptop.printData();
            printedData = outContent.toString();
        } catch (java.io.IOException e) {
            printedData = "Error capturing printed data.";
        }
        return printedData;
    }
}
