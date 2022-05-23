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
            {"files_path", "Caminho"},
            {"empty_line", "Uma string vazia foi inserida."},
            {"false_requirements", "A string inserida não atende aos requisitos."},
            {"not_an_option", "Um tipo inexistente foi introduzido."},
            {"false_arguments", "Algo está errado com os argumentos inseridos."},
            {"not_a_command", "O comando digitado não existe."},
            {"server_unavailable", "O servidor não está disponível."},
            {"empty_account", "Você não pode enviar comandos, faça login ou registre-se."},
            {"error", "Erro"}
    };

    public Object[][] getContents() {
        return contents;
    }
}
