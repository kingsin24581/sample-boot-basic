package th.mfu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PathVariable;

@SpringBootTest
public class HelloControllerTest {

    @Autowired
    private HelloController controller; //create controler class for Autowired

    @Test
    public void testHello() {
        // Act
        String response = controller.hello();
        // Assert
        assertEquals("Hello World!", response);
        // Act
        response = controller.hello("aomsin");
        // Assert
        assertEquals("Hello World!aomsin", response);
    }

    @Test
    public void testSumNegativeNegative() {
        // Act
        int response = controller.sum(-15 , -15);
        // Assert
        assertEquals(-30, response);
        // Act
        response = controller.sum(-8 , 15);
        // Assert
        assertEquals(7, response);
        // Act
        response = controller.sum(15 , 15);
        // Assert
        assertEquals(30, response);
        // Act
        response = controller.sum(15 , -15);
        // Assert
        assertEquals(0, response);
    }
}
