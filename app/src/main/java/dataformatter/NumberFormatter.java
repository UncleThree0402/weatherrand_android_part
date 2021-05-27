package dataformatter;

public class NumberFormatter {

    public static String roundNumber(String number){
        return Integer.toString((int) Math.round(Double.parseDouble(number)));
    }

    public static String percentageFormat(String number){
        return Integer.toString((int) Math.round(Double.parseDouble(number) * 100)) + "%";
    }
}
