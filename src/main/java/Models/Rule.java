package Models;

public class Rule {
    private final Column column;
    private final Order order;
    private String parameter;

    public Rule(Column column, Order order, String parameter) {
        this.column = column;
        this.order = order;
        this.parameter = parameter;
    }

    public Rule(Column column, Order order) {
        this.column = column;
        this.order = order;
    }

    public enum Column {
        ID("ID"),
        NAME("Name"),
        X_COORDINATE("X"),
        Y_COORDINATE("Y"),
        CREATION_DATE("CreationDate"),
        AREA("Area"),
        POPULATION("Population"),
        METERS("MetersAboveSeaLevel"),
        CLIMATE("Climate"),
        GOVERNMENT("Government"),
        STANDARD_OF_LIVING("StandardOfLiving"),
        GOVERNOR("Governor");

        private final String name;

        Column(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum Order {
        SORT_INCREASE("SORT_INCREASE"),
        SORT_DECREASE("SORT_DECREASE"),
        FILTER("FILTER");

        private final String name;

        Order(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
