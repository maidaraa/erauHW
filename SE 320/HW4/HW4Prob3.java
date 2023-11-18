import java.util.*;
import java.text.*;

public class HW4Prob3 {

    public static void main(String[] args) {

        double number = 12345.678;
        String stringNumber = "12,345.678";

        NumberFormat UKNumFormat = NumberFormat.getInstance(Locale.UK);
        UKNumFormat.setMaximumFractionDigits(2);
        System.out.println(UKNumFormat.format(number));

        NumberFormat USCurrencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println(USCurrencyFormat.format(number));

        try {
            Number nf = UKNumFormat.parse(stringNumber);
            System.out.println(nf);
        } catch (ParseException e) {
            System.out.println(e);
        }
    }

}