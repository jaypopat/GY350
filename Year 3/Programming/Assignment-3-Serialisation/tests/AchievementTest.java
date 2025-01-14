import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AchievementTest {

    /**
     * Tests the creation of an Achievement object and verifies its attributes.
     */
    @Test
    public void testAchievementCreation() {
        Achievement achievement = new Achievement("First Achievement", "Description for first Achievement", LocalDate.now());
        assertEquals("First Achievement", achievement.getAchievementName());
        assertEquals("Description for first Achievement", achievement.getDescription());
        assertEquals(LocalDate.now(), achievement.getDateOfAward());
    }
}