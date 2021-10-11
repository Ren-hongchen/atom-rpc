package top.renhongchen;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void clientTest() {
        DTO message = new DTO();
        Client client = new Client("127.0.0.1",8080,message);
    }
}
