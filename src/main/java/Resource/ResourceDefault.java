package Resource;

import java.util.ListResourceBundle;

public class ResourceDefault extends ListResourceBundle {
    public static final Object[][] contents = {
            {"add", "add"},
            {"edit", "edit"},
            {"add_if_min", "add_if_min"},
            {"remove_greater", "remove_greater"},
            {"remove_lower", "remove_lower"},
            {"name", "name"},
            {"coordinate_x", "coordinate_x"},
            {"coordinate_y", "coordinate_y"},
            {"area", "area"},
            {"population", "population"},
            {"meters", "meters"},
            {"climate", "climate"},
            {"government", "government"},
            {"standard", "standard"},
            {"human", "human"},
            {"id", "id"},
            {"creation_date", "creation_date"},
            {"none", "none"},
            {"sort_increase", "sort_increase"},
            {"sort_decrease", "sort_decrease"},
            {"username", "username"}
    };

    public Object[][] getContents() {
        return contents;
    }
}
