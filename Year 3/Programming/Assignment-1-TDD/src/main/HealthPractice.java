// Jay Popat 22346566
// Used a record class because HealthPractice is an immutable data object with no need for setters
public record HealthPractice(String name, String address) {

    // constructor to validate the name and address and populating variables (name and address)
    public HealthPractice {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }
    }

    // toString method to return the name and address of the health practice
    @Override
    public String toString() {
        return String.format(
                """
                        Health Practice: %s
                        Address: %s""",
                name,
                address
        );
    }
}