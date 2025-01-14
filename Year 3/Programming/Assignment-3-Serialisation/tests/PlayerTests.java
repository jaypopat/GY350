import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTests {
    /**
     * Tests the creation and serialization of Player objects.
     *
     * @throws IOException if an I/O error occurs during serialization
     */
    @BeforeEach
    void cleanUp() {
        new File("player.ser").delete();
        new File("Achievments.csv").delete();
    }

    /**
     * Tests the creation and serialization of Player objects.
     *
     * @throws IOException if an I/O error occurs during serialization
     */

    @Test
    public void testPlayerCreation() throws IOException {
        // create players and achievements
        List<Player> players = createTestPlayers();

        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(new FileOutputStream("player.ser"));
            for (Player player : players) {
                out.writeObject(player);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new IOException("Could not write to file");
        }

        // Check if files were created
        assertTrue(new File("player.ser").exists(), "Serialized player file should exist");
        assertTrue(new File("Achievments.csv").exists(), "Achievements CSV file should exist");

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("player.ser"))) {
            for (Player originalPlayer : players) {
                Player deserializedPlayer = (Player) in.readObject();
                assertNotNull(deserializedPlayer, "Deserialized player should not be null");
                // checking that the achievments field is being populated in the read process
                assertFalse(deserializedPlayer.getAchievements().isEmpty(), "Achievements should not be empty");
                assertEquals(originalPlayer.getId(), deserializedPlayer.getId(), "Player ID should match");
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Deserialize players and verify their attributes
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("player.ser"))) {
            for (Player originalPlayer : players) {
                Player deserializedPlayer = (Player) in.readObject();
                assertNotNull(deserializedPlayer, "Deserialized player should not be null");
                assertEquals(originalPlayer.getId(), deserializedPlayer.getId(), "Player ID should match");
                assertEquals(originalPlayer.getUsername(), deserializedPlayer.getUsername(), "Player username should match");
                assertEquals(originalPlayer.getCountry().name(), deserializedPlayer.getCountry().name(), "Player country should match");
                assertEquals(originalPlayer.getJoinDate(), deserializedPlayer.getJoinDate(), "Player join date should match");
            }
        } catch (ClassNotFoundException e) {
            fail("Class not found during deserialization: " + e.getMessage());
        }
    }
    /**
     * Tests that an IOException is thrown when trying to serialize to a directory instead of a file.
     */
    @Test
    void testFileIOException() {
        Player player = createTestPlayers().getFirst();

        // Try to serialize to a directory instead of a file
        assertThrows(IOException.class, () -> {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/"))) {
                out.writeObject(player);
            }
        });
    }
    /**
     * Creates a list of test Player objects.
     *
     * @return a list of test Player objects
     */
    private List<Player> createTestPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("1", "John", new Country("USA", "US"), LocalDate.now(), createAchievements(2)));
        players.add(new Player("2", "Jane", new Country("UK", "GB"), LocalDate.now().minusYears(1), createAchievements(3)));
        players.add(new Player("3", "Jack", new Country("Canada", "CA"), LocalDate.now().plusMonths(3), createAchievements(1)));
        players.add(new Player("4", "Jill", new Country("Australia", "AU"), LocalDate.now().minusDays(2), createAchievements(4)));
        players.add(new Player("5", "Jim", new Country("New Zealand", "NZ"), LocalDate.now().plusWeeks(2), createAchievements(2)));
        return players;
    }

    /**
     * Creates a list of test Achievement objects.
     *
     * @param count the number of achievements to create
     * @return a list of test Achievement objects
     */
    private List<Achievement> createAchievements(int count) {
        List<Achievement> achievements = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            achievements.add(new Achievement("Achievement " + i, "Description " + i, LocalDate.now().minusDays(i)));
        }
        return achievements;
    }
}
