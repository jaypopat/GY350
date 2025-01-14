import java.io.Serializable;

/**
 * The Country record represents a country with a name and a code. Chose a record because we don't need setters and getters.
 */
public record Country(String name, String code) implements Serializable {

}