package Resource;

import java.util.ListResourceBundle;

public class ResourceSpanish extends ListResourceBundle {
    public static final Object[][] contents = {
            {"loginField", "Inicio de sesión"},
            {"passwordField", "Contraseña"},
            {"loginButton", "Entrada"},
            {"registerButton", "Registro"},
            {"username", "Nombre de usuario"},
            {"execute_script", "Ejecutar script"},
            {"clear", "Claro"},
            {"remove", "Quitar"},
            {"logout", "Cerrar sesión"},
            {"name", "Nombre"},
            {"coordinate_x", "Coordenada X"},
            {"coordinate_y", "Coordenada Y"},
            {"area", "Zona"},
            {"population", "Población"},
            {"meters", "Metros sobre el nivel del mar"},
            {"climate", "Clima"},
            {"government", "Gobierno"},
            {"standard", "Nivel de vida"},
            {"human", "Nombre humano"},
            {"ok", "OK"},
            {"add", "Añadir"},
            {"edit", "Editar"},
            {"add_if_min", "Añadir si es mío"},
            {"remove_greater", "Quitar mayor"},
            {"remove_lower", "Quitar inferior"},
            {"id", "ID"},
            {"coordinates", "Nombre"},
            {"x", "X"},
            {"y", "Y"},
            {"creation_date", "Fecha de Creación"},
            {"governor", "Gobernador"},
            {"sorting_and_filtration", "Clasificación y Filtración"},
            {"column", "Columna"},
            {"order", "Orden"},
            {"parameter", "Parámetro"},
            {"remove_rule", "Eliminar regla"},
            {"sort_text", "Ordenar por"},
            {"cancel", "Cancelación"},
            {"none", "Ninguno"},
            {"sort_increase", "Aumento de clasificación"},
            {"sort_decrease", "Ordenar disminución"}
    };

    public Object[][] getContents() {
        return contents;
    }
}
