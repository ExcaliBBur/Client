package Data;

/**
 * Represents all specified objects for storage in the collection.
 */
public abstract class Collectables {
    private String name;

    public Collectables() {

    }

    /**
     * Constructor, gets all necessary things.
     *
     * @param name string identifier of the object
     */
    public Collectables(String name) {
        this.name = name;
    }

    /**
     * Setter of name.
     *
     * @param name String identifier
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of name.
     *
     * @return name of object.
     */
    public String getName() {
        return name;
    }
}
