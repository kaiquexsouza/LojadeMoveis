package util;

import javax.swing.text.JTextComponent;

public class FormUtil {

    public static void cleanJTexts(JTextComponent[] campos){
        for (JTextComponent campo : campos) {
            if (campo != null) {
                campo.setText("");
            }
        }
    }

    public static boolean hasEmpty(JTextComponent[] campos){
        for (JTextComponent campo : campos) {
            if (campo != null && campo.getText().trim().isEmpty()) {
                campo.requestFocus();
                return true;
            }
        }
        return false;
    }

    public static String limparMascara(String s) {
        return s.replaceAll("\\D", "");
    }
}
