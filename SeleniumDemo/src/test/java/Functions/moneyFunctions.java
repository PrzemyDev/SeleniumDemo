package Functions;

public class moneyFunctions {
    public String MoneyConverter(String input3, String accountsCurrency){
        String formattedInput = input3.substring(0, input3.length() - 3).replaceAll("(?!^)(?=(?:\\d{3})+(?:\\.|$))", " ");
        String fractionalCurr = input3.substring(input3.length()-3,input3.length()); // from, to
        fractionalCurr = fractionalCurr.replace(".",",");
        String result = formattedInput+fractionalCurr +" "+accountsCurrency;
        return result;
    }
}
