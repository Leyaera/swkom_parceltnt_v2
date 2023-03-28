package at.fhtw.swen3.controller.rest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class HomeControllerTest {
    private HomeController homeController = new HomeController();
    @Test
    void testIndex() {
        assertEquals("redirect:swagger-ui.html", homeController.index());
    }

    @Test
    void testIndexNotNull() {
        assertNotNull(homeController.index());
    }
}