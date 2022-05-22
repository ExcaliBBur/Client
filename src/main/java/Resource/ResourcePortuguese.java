package Resource;

import java.util.ListResourceBundle;

public class ResourcePortuguese extends ListResourceBundle {
    public static final Object[][] contents = {
            {"loginField", "Login"},
            {"passwordField", "Senha"},
            {"loginButton", "Entrada"},
            {"registerButton", "Registo"},
            {"username", "Usuario"},
            {"execute_script", "Executar script"},
            {"clear", "Desmarcar"},
            {"remove", "Remover"},
            {"logout", "Sessao"},
            {"name", "Nome"},
            {"coordinate_x", "Coordenadas X"},
            {"coordinate_y", "Coordenadas Y"},
            {"area", "Area"},
            {"population", "Populacao"},
            {"meters", "Metros acima do nível do mar"},
            {"climate", "Clima"},
            {"government", "Governo"},
            {"standard", "Padrão de vida"},
            {"human", "Nome humano"},
            {"ok", "BEM"},
            {"add", "Adicionar"},
            {"edit", "Editar"},
            {"add_if_min", "Adicionar se o meu"},
            {"remove_greater", "Remover maior"},
            {"remove_lower", "Remover inferior"},
            {"id", "ID"},
            {"coordinates", "Coordenada"},
            {"x", "X"},
            {"y", "Y"},
            {"creation_date", "Data De Criação"},
            {"governor", "Governo"},
            {"sorting_and_filtration", "Classificação e filtração"},
            {"column", "Coluna"},
            {"order", "Ordem"},
            {"parameter", "Parametro"},
            {"remove_rule", "Remover regra"},
            {"sort_text", "Ordenar por"},
            {"cancel", "Cancelamento"},
            {"none", "Nenhum"},
            {"sort_increase", "Ordenar aumento"},
            {"sort_decrease", "Ordenar diminuição"},
            {"files_path", "Caminho"}
    };

    public Object[][] getContents() {
        return contents;
    }
}
