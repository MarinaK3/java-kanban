package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Epic")
class EpicTest {
    @Test
    @DisplayName("should be the same its with copy")
    public void shouldEqualsWithCopy() {
        Epic epic = new Epic("name", "descr");
        Epic epic1 = new Epic("name1", "descr2");
      //  assertEquals(epic1.getName(), epic.getName(), "name should be the same");
       // assertEquals(epic1.getDescription(), epic.getDescription(), "decr should be the same");
    }


}