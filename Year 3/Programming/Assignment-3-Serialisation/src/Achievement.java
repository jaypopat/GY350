import java.time.LocalDate;


public class Achievement {
    private final String achievementName;
    private final String description;
    private final LocalDate dateofAward;

    /**
     * Achievment constructor
     *
     * @param achievementName the name of the achievement
     * @param description the description of the achievement
     * @param dateOfAward the date the achievement was awarded
     */
    public Achievement(String achievementName, String description, LocalDate dateOfAward) {
        this.achievementName = achievementName;
        this.description = description;
        this.dateofAward = dateOfAward;
    }

    /**
     * Returns the name of the achievement.
     *
     * @return the name of the achievement
     */
    public String getAchievementName() {
        return achievementName;
    }

    /**
     * Returns the description of the achievement.
     *
     * @return the description of the achievement
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the date the achievement was awarded.
     *
     * @return the date the achievement was awarded
     */
    public LocalDate getDateOfAward() {
        return dateofAward;
    }
}