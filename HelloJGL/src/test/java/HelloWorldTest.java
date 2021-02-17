import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HelloWorldTest {

    @Test
    void calculator() {

        long id = a();
        if (id == 0L) {
            System.out.println(4|2);
        }
    }

    long a() {
        if (new Random().nextBoolean()) {
            throw new RuntimeException("Help");
        }
        return 30L;
    }
}