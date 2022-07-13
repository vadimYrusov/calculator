import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NotCorrectException {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String answer = scanner.nextLine();

            System.out.println(Main.calc(answer));
        }

    }

    public static String calc(String str) throws NotCorrectException {
        String[] arr = str.split(" ");
        String ans = "";

        if (arr.length > 3) {
            throw new NotCorrectException("Можно вычислять только два числа");
        } else if (arr.length < 3) {
            throw new NotCorrectException("Может быть только два операнда и один оператор");
        }

        if (Main.isRomanNumber(arr[0]) && Main.isRomanNumber(arr[2])) {
            ans = Main.romanCalculations(arr);
        } else if (!Main.isRomanNumber(arr[0]) && !Main.isRomanNumber(arr[2])) {
            ans = String.valueOf(Main.arabicCalculations(arr));
        } else {
            throw new NotCorrectException("используются одновременно разные системы счисления");
        }

        return ans;
    }

    public static boolean isRomanNumber(String num) {
        String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        for (String s : roman) {
            if (num.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static String romanCalculations(String[] arr) throws NotCorrectException {
        int num1 = Main.romanToArabic(arr[0]);
        int num2 = Main.romanToArabic(arr[2]);
        int ans = 0;

        if (num1 > 10 || num2 > 10 || num1 < 1 || num2 < 1) {
            throw new NotCorrectException("Число не может быть больше десяти и меньше одного");
        }

        if (arr[1].equals("+")) {
            ans = num1 + num2;
        } else if (arr[1].equals("-")) {
            ans = num1 - num2;
        } else if (arr[1].equals("*")) {
            ans = num1 * num2;
        } else if (arr[1].equals("/")) {
            ans = num1 / num2;
        } else {
            throw new NotCorrectException("неверный знак выражения");
        }

        if (ans < 1) {
            throw new NotCorrectException("Римские числа не имеют отрицательных значений и нуля");
        }

        return Main.arabicToRoman(ans);
    }

    public static int arabicCalculations(String[] arr) throws NotCorrectException {

        if (Integer.parseInt(arr[0]) > 10 || Integer.parseInt(arr[2]) > 10 ||
                Integer.parseInt(arr[0]) < 1 || Integer.parseInt(arr[2]) < 1
        ) {
            throw new NotCorrectException("Число не может быть больше десяти и меньше одного");
        }

        if (arr[1].equals("+")) {
            return Integer.parseInt(arr[0]) + Integer.parseInt(arr[2]);
        } else if (arr[1].equals("-")) {
            return Integer.parseInt(arr[0]) - Integer.parseInt(arr[2]);
        } else if (arr[1].equals("*")) {
            return Integer.parseInt(arr[0]) * Integer.parseInt(arr[2]);
        } else if (arr[1].equals("/")) {
            return Integer.parseInt(arr[0]) / Integer.parseInt(arr[2]);
        } else {
            throw new NotCorrectException("еверный знак выражения");
        }
    }

    public static int romanToArabic(String s) {
        String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        for (int i = 0; i < roman.length; i++) {
            if (s.equals(roman[i])) {
                return i + 1;
            }
        }
        return 11;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

}
