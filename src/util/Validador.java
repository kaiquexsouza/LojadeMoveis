package util;

public class Validador {

    public static boolean validarCNPJ(String cnpj) {
        String num = cnpj.replaceAll("\\D", "");
        return num.length() == 14;
    }

    public static boolean validarTelefone(String tel) {
        String num = tel.replaceAll("\\D", "");
        return num.length() >= 10 && num.length() <= 11;
    }

    public static boolean validarEmail(String email) {
        if (email == null || email.isBlank()) return false;
        return email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$");
    }
}