package Resource;

import java.util.ListResourceBundle;

public class ResourceHungarian extends ListResourceBundle {
    public static final Object[][] contents = {
            {"loginField", "Bejelentkezés"},
            {"passwordField", "Jelszó"},
            {"loginButton", "Bejárat"},
            {"registerButton", "Regisztráció"},
            {"username", "Felhasználónév"},
            {"execute_script", "Parancsfájl végrehajtása"},
            {"clear", "Tiszta"},
            {"remove", "Eltávolítás"},
            {"logout", "Kijelentkezés"},
            {"name", "Név"},
            {"coordinate_x", "Koordináta X"},
            {"coordinate_y", "Koordináta Y"},
            {"area", "Terület"},
            {"population", "Népesség"},
            {"meters", "Méter tengerszint feletti magasságban"},
            {"climate", "Éghajlat"},
            {"government", "Kormány"},
            {"standard", "Életszínvonal"},
            {"human", "Emberi név"},
            {"ok", "OK"},
            {"add", "Hozzáadás"},
            {"edit", "Edit"},
            {"add_if_min", "Add hozzá, ha az enyém"},
            {"remove_greater", "Távolítsa el a nagyobbat"},
            {"remove_lower", "Távolítsa el az alsó részt"},
            {"id", "ID"},
            {"coordinates", "Koordináták"},
            {"x", "X"},
            {"y", "Y"},
            {"creation_date", "Létrehozás Dátuma"},
            {"governor", "Kormányzó"},
            {"sorting_and_filtration", "Válogatás és szűrés"},
            {"column", "Oszlop"},
            {"order", "Rendelés"},
            {"parameter", "Paraméter"},
            {"remove_rule", "Szabály eltávolítása"},
            {"sort_text", "Rendezés"},
            {"cancel", "Mégse"},
            {"none", "Nincs"},
            {"sort_increase", "Rendezés növekedése"},
            {"sort_decrease", "Rendezés csökkentése"},
            {"files_path", "Fájlok elérési útja"},
            {"empty_line", "Üres karakterlánc lett beírva."},
            {"false_requirements", "A megadott karakterlánc nem felel meg a követelményeknek."},
            {"not_an_option", "Egy nem létező típus került bevezetésre."},
            {"false_arguments", "Valami nincs rendben a beírt érvekkel."},
            {"not_a_command", "A megadott parancs nem létezik."},
            {"server_unavailable", "A szerver nem érhető el."},
            {"empty_account", "Nem küldhet parancsokat, jelentkezzen be vagy regisztráljon."},
            {"error", "Hiba"}
    };

    public Object[][] getContents() {
        return contents;
    }
}
