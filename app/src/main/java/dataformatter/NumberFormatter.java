package dataformatter;

public class NumberFormatter {

    public static String roundNumber(String number){
        return Integer.toString((int) Math.round(Double.parseDouble(number)));
    }

    public static String percentageFormat(String number){
        return Integer.toString((int) Math.round(Double.parseDouble(number) * 100)) + "%";
    }

    public static int stringToNumber(String number){
        return Integer.parseInt(number);
    }

    public static String correctToSig(String number){
        return Double.toString(Math.round(Double.parseDouble(number) * 100d) /100d);
    }
}
