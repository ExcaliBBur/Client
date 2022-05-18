package Models;

import java.util.Comparator;
import java.util.stream.Stream;

public class Rule {
    private final Column column;
    private final Order order;
    private final String parameter;

    public Rule(Column column, Order order, String parameter) {
        this.column = column;
        this.order = order;
        this.parameter = parameter;
    }

    public Stream<City> transform(Stream<City> stream) {
        return order.doOption(column, parameter, stream);
    }

    public Column getColumn() {
        return column;
    }

    public Order getOrder() {
        return order;
    }

    public String getParameter() {
        return parameter;
    }

    public enum Column {
        ID("id") {
            @Override
            public Comparator<City> getComparator() {
                return Comparator.comparingInt(Collectables::getId);
            }

            @Override
            public String getString(City city) {
                return Integer.toString(city.getId());
            }
        },
        NAME("name") {
            @Override
            public Comparator<City> getComparator() {
                return Comparator.comparing(Collectables::getName);
            }

            @Override
            public String getString(City city) {
                return city.getName();
            }
        },
        X_COORDINATE("x") {
            @Override
            public Comparator<City> getComparator() {
                return Comparator.comparingDouble(o -> o.getCoordinates().getFirstCoordinates());
            }

            @Override
            public String getString(City city) {
                return Double.toString(city.getCoordinates().getFirstCoordinates());
            }
        },
        Y_COORDINATE("y") {
            @Override
            public Comparator<City> getComparator() {
                return Comparator.comparing(o -> o.getCoordinates().getSecondCoordinates());
            }

            @Override
            public String getString(City city) {
                return Double.toString(city.getCoordinates().getSecondCoordinates());
            }
        },
        CREATION_DATE("creationDate") {
            @Override
            public Comparator<City> getComparator() {
                return Comparator.comparing(o -> o.getCreationDate().toString());
            }

            @Override
            public String getString(City city) {
                return city.getCreationDate().toString().replace("T",
                        " ");
            }
        },
        AREA("area") {
            @Override
            public Comparator<City> getComparator() {
                return Comparator.comparingInt(City::getArea);
            }

            @Override
            public String getString(City city) {
                return Integer.toString(city.getArea());
            }
        },
        POPULATION("population") {
            @Override
            public Comparator<City> getComparator() {
                return Comparator.comparingInt(City::getPopulation);
            }

            @Override
            public String getString(City city) {
                return Integer.toString(city.getPopulation());
            }
        },
        METERS("meters") {
            @Override
            public Comparator<City> getComparator() {
                return Comparator.comparingInt(City::getMeters);
            }

            @Override
            public String getString(City city) {
                return Integer.toString(city.getMeters());
            }
        },
        CLIMATE("climate") {
            @Override
            public Comparator<City> getComparator() {
                return Comparator.comparing(o -> {
                    try {
                        return o.getClimate().getName();
                    } catch (NullPointerException e) {
                        return "";
                    }
                });
            }

            @Override
            public String getString(City city) {
                try {
                    return city.getClimate().getName();
                } catch (NullPointerException e) {
                    return "";
                }
            }
        },
        GOVERNMENT("government") {
            @Override
            public Comparator<City> getComparator() {
                return Comparator.comparing(o -> {
                    try {
                        return o.getGovernment().getName();
                    } catch (NullPointerException e) {
                        return "";
                    }
                });
            }

            @Override
            public String getString(City city) {
                try {
                    return city.getGovernment().getName();
                } catch (NullPointerException e) {
                    return "";
                }
            }
        },
        STANDARD_OF_LIVING("standardOfLiving") {
            @Override
            public Comparator<City> getComparator() {
                return Comparator.comparing(o -> {
                    try {
                        return o.getStandardOfLiving().getName();
                    } catch (NullPointerException e) {
                        return "";
                    }
                });
            }

            @Override
            public String getString(City city) {
                try {
                    return city.getStandardOfLiving().getName();
                } catch (NullPointerException e) {
                    return "";
                }
            }
        },
        HUMAN_NAME("humanName") {
            @Override
            public Comparator<City> getComparator() {
                return Comparator.comparing(o -> o.getGovernor().getHumanName());
            }

            @Override
            public String getString(City city) {
                return city.getGovernor().getHumanName();
            }
        };

        private final String name;

        Column(String name) {
            this.name = name;
        }

        public abstract Comparator<City> getComparator();

        public abstract String getString(City city);

        public String getName() {
            return name;
        }
    }

    public enum Order {
        SORT_INCREASE("SORT_INCREASE") {
            @Override
            public Stream<City> doOption(Column column, String parameter, Stream<City> stream) {
                return stream.filter(o -> column.getString(o).contains(parameter))
                        .sorted(column.getComparator());
            }
        },
        SORT_DECREASE("SORT_DECREASE") {
            @Override
            public Stream<City> doOption(Column column, String parameter, Stream<City> stream) {
                return stream.filter(o -> column.getString(o).contains(parameter)).sorted(column
                        .getComparator().reversed());
            }
        },
        NONE("NONE") {
            @Override
            public Stream<City> doOption(Column column, String parameter, Stream<City> stream) {
                return stream.filter(o -> column.getString(o).contains(parameter));
            }
        };

        private final String name;

        Order(String name) {
            this.name = name;
        }

        abstract Stream<City> doOption(Column column, String parameter, Stream<City> stream);

        public String getName() {
            return name;
        }
    }
}
