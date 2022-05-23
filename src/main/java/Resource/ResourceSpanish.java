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
            {"sort_decrease", "Ordenar disminución"},
            {"files_path", "Ruta de archivos"},
            {"empty_line", "Se ha introducido una línea vacía."},
            {"false_requirements", "La cadena introducida no cumple con los requisitos."},
            {"not_an_option", "Introdujo un tipo."},
            {"false_arguments", "Algo está mal con los argumentos introducidos."},
            {"not_a_command", "El comando introducido no existe."},
            {"server_unavailable", "El servidor no está disponible."},
            {"empty_account", "No puede enviar comandos, iniciar sesión o registrarse."},
            {"error", "Error"}
    };

    public Object[][] getContents() {
        return contents;
    }
}
