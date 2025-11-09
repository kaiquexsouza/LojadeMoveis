package util;

public class DataUtil {

    public static int parseInt(String valor){
        try{
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
