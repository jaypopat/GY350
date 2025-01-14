import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 This class implements Serializable to allow its instances to be serialized and deserialized.
 */
public class Player implements Serializable {

    private final String id;
    private final String username;
    private final Country country;
    private final LocalDate joinDate;
    private transient List<Achievement> achievements;

    /**
     * Player constructor
     *
     * @param id the id of the player
     * @param username the username of the player
     * @param country the country of the player
     * @param joinDate the date the player joined
     * @param achievements the list of achievements of the player
     */
    public Player(String id, String username, Country country, LocalDate joinDate, List<Achievement> achievements) {
        this.id = id;
        this.username = username;
        this.country = country;
        this.joinDate = joinDate;
        this.achievements = achievements;
    }

    /**
     * Returns the id of the player.
     *
     * @return the id of the player
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the username of the player.
     *
     * @return the username of the player
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the country of the player.
     *
     * @return the country of the player
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Returns the date the player joined.
     *
     * @return the date the player joined
     */
    public LocalDate getJoinDate() {
        return joinDate;
    }

    /**
     * Returns the list of achievements of the player.
     *
     * @return the list of achievements of the player
     */
    public List<Achievement> getAchievements() {
        return achievements;
    }

    /**
     * Custom deserialization logic to read the achievements from a CSV file (Achievments.csv).
     *
     * @param in the ObjectInputStream to read from
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class of a serialized object could not be found
     */
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        achievements = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("Achievments.csv"))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts[0].equals(this.id)) {
                    achievements.add(new Achievement(parts[1], parts[2], LocalDate.parse(parts[3])));
                }
            }
        } catch (IOException e) {
            throw new IOException("Could not read from file");
        }
    }

    /**
     * Custom serialization logic to write the achievements to a CSV file.
     *
     * @param out the ObjectOutputStream to write to
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class could not be found
     */
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException, ClassNotFoundException {
        out.defaultWriteObject();
        try (FileWriter fileOutCSV = new FileWriter("Achievments.csv", true)) {
            for (Achievement achievement : achievements) {
                fileOutCSV.append(id).append(",").append(achievement.getAchievementName()).append(",").append(achievement.getDescription()).append(",").append(String.valueOf(achievement.getDateOfAward())).append("\n");
            }
        } catch (IOException e) {
            throw new IOException("Could not write to file");
        }
    }
}