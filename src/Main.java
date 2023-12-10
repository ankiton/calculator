import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int ADDITION = 0;
    private static final int SUBTRACTION = 1;
    private static final int MULTIPLIED = 2;
    private static final int DIVISION = 3;

    public static void main(String[] args) {
        System.out.println("Введите выражение:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        try {
            input = reader.readLine();
            calc(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void calc(String input) {

        String math = input.replace(" ", "");
        if (math.contains("+")) {
            String[] numbers = math.split("\\+");
            if (numbers.length != 2) throw new ArithmeticException();
            try {
                resultArabic(numbers, ADDITION);
            } catch (NumberFormatException nfe) {
                try {
                    resultRome(numbers, ADDITION);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (math.contains("-")) {
            String[] numbers = math.split("-");
            if (numbers.length != 2) throw new ArithmeticException();
            try {
                resultArabic(numbers, SUBTRACTION);
            } catch (NumberFormatException nfe) {
                try {
                    resultRome(numbers, SUBTRACTION);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (math.contains("/")) {
            String[] numbers = math.split("/");
            if (numbers.length != 2) throw new ArithmeticException();
            try {
                resultArabic(numbers, DIVISION);
            } catch (NumberFormatException nfe) {
                try {
                    resultRome(numbers, DIVISION);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (math.contains("*")) {
            String[] numbers = math.split("\\*");
            if (numbers.length != 2) throw new ArithmeticException();
            try {
                resultArabic(numbers, MULTIPLIED);
            } catch (NumberFormatException nfe) {
                try {
                    resultRome(numbers, MULTIPLIED);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else throw new ArithmeticException();
    }

    private static void resultRome(String[] numbers, int type) throws Exception {
        int value1 = checkRome(numbers[0]);
        int value2 = checkRome(numbers[1]);

        Integer result = switch (type) {
            case (ADDITION) -> value1 + value2;
            case (SUBTRACTION) -> value1 - value2;
            case (MULTIPLIED) -> value1 * value2;
            case (DIVISION) -> value1 / value2;
            default -> null;
        };

        if (result != null) {
            if (result <= 0) throw new Exception();
            int units = result % 10;
            int tens = (result % 100) / 10;
            int hundreds = (result % 1000) / 100;
            System.out.println(hundredsRome(hundreds) + tensRome(tens) + unitsRome(units));
        }
    }

    private static void resultArabic(String[] numbers, int type) throws Exception {
        int value1 = Integer.parseInt(numbers[0]);
        int value2 = Integer.parseInt(numbers[1]);
        if (value1 > 10 || value2 > 10) throw new Exception();
        switch (type) {
            case (ADDITION):
                System.out.println(value1 + value2);
                break;
            case (SUBTRACTION):
                System.out.println(value1 - value2);
                break;
            case (MULTIPLIED):
                System.out.println(value1 * value2);
                break;
            case (DIVISION):
                System.out.println(value1 / value2);
                break;
        }
    }

    private static int checkRome(String rome) {
        return switch (rome.toUpperCase()) {
            case ("I") -> 1;
            case ("II") -> 2;
            case ("III") -> 3;
            case ("IV") -> 4;
            case ("V") -> 5;
            case ("VI") -> 6;
            case ("VII") -> 7;
            case ("VIII") -> 8;
            case ("IX") -> 9;
            case ("X") -> 10;
            default -> throw new ArithmeticException();
        };
    }

    public static String unitsRome(int units) {
        return switch (units) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            default -> "";
        };
    }

    public static String tensRome(int tens) {
        return switch (tens) {
            case 1 -> "X";
            case 2 -> "XX";
            case 3 -> "XXX";
            case 4 -> "XL";
            case 5 -> "L";
            case 6 -> "LX";
            case 7 -> "LXX";
            case 8 -> "LXXX";
            case 9 -> "XC";
            default -> "";
        };
    }

    public static String hundredsRome(int hundreds) {
        String s_hundreds = "";
        if (hundreds == 1) s_hundreds = "C";
        return s_hundreds;
    }
}
