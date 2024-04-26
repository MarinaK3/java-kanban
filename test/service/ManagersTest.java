package service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ManagersTest")
class ManagersTest {

    @DisplayName("Managers")
    @Test
    public void getDefaultShouldReturnNotNullTest() {
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(taskManager);
    }
}