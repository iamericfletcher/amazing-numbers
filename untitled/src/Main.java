import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static String initialInput;
    public static String secondNaturalNumberCheck;
    public static long naturalNumberInput;
    public static int zeroCounter = 0;
    public static boolean firstNaturalNumberNoMatch;
    public static boolean secondNaturalNumberNoMatch;
    public static boolean isEven;
    public static boolean isOdd;
    public static boolean isBuzz;
    public static boolean isDuck;
    public static boolean isPalindromic;
    public static boolean isGapful;
    public static boolean isSpy;
    public static boolean isSquare;
    public static boolean isSunny;
    public static boolean isJumping;
    public static boolean isHappy;
    public static boolean isSad;
    public static boolean doubleMatches;
    public static boolean noMutualExclusiveProperties;
    public static boolean noMutualExclusiveMatch;
    public static boolean noInvalidProperties;

    public static String naturalNumberInputToString;
    public static char[] naturalNumberInputArray;
    public static String[] initialInputArray;
    public static String[] naturalNumberPropertyArray = {"EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY", "JUMPING", "HAPPY", "SAD"};

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        System.out.println("""
                Welcome to Amazing Numbers!

                Supported requests:
                - enter a natural number to know its properties;\s
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and two properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.
                """);

        boolean stop = false;
        while (!stop) {
            System.out.println("Enter a request:");
            initialInput = scanner.nextLine();

            // Test for empty request
            if (initialInput.equals("")) {
                menu();
            }

            initialInputArray = initialInput.split(" ");

            // regular expression for an integer number
            String regex = "^\\d*$";
            // compiling regex
            Pattern p = Pattern.compile(regex);

            String firstNaturalNumberCheck = initialInputArray[0];

            // create a matcher that will match input against regex
            Matcher m = p.matcher(firstNaturalNumberCheck);

            firstNaturalNumberNoMatch = false;
            secondNaturalNumberNoMatch = false;

            if (!m.find()) {
                firstNaturalNumberNoMatch = true;
                System.out.println("\nThe first parameter should be a natural number or zero.\n");
            }

            if (initialInputArray.length == 2 && !firstNaturalNumberNoMatch) {
                secondNaturalNumberCheck = initialInputArray[1];

                // create a matcher that will match input against regex
                Matcher t = p.matcher(secondNaturalNumberCheck);

                if (!t.find()) {
                    secondNaturalNumberNoMatch = true;
                    System.out.println("\nThe second parameter should be a natural number or zero.\n");
                }

            }

            if (initialInputArray.length == 1 && !firstNaturalNumberNoMatch && Long.parseLong(initialInputArray[0]) >= 1) {

                naturalNumberInput = Long.parseLong(initialInputArray[0]);

                determineHappySad();

                propertyMethods();

                System.out.printf("""

                                Properties of %d
                                        even: %s
                                        odd: %s
                                        buzz: %s
                                        duck: %s
                                 palindromic: %s
                                      gapful: %s
                                         spy: %s
                                      square: %s
                                       sunny: %s
                                     jumping: %s
                                       happy: %s
                                         sad: %s

                                """,
                        naturalNumberInput,
                        isEven,
                        isOdd,
                        isBuzz,
                        isDuck,
                        isPalindromic,
                        isGapful,
                        isSpy,
                        isSquare,
                        isSunny,
                        isJumping,
                        isHappy,
                        isSad);
            } else if (initialInputArray.length == 2 && !firstNaturalNumberNoMatch && !secondNaturalNumberNoMatch) {

                System.out.println();

                rangeDeterminations();

            } else if (initialInputArray.length >= 3 && initialInputArray.length <= 14) {

                int propertyCount1 = 0;
                int propertyCount2 = 0;
                int propertyCount = 0;

                if (initialInputArray.length == 3) {
                    // Determine if 1st property is valid
                    for (String property :
                            naturalNumberPropertyArray) {
                        if (property.equalsIgnoreCase(initialInputArray[2].replace("-", ""))) {
                            propertyCount++;
                            break;
                        }
                    }

                    if (propertyCount == 0) {
                        System.out.printf("""

                                        The property [%s] is wrong.
                                        Available properties: %s

                                        """,
                                initialInputArray[2].toUpperCase(),
                                Arrays.toString(naturalNumberPropertyArray));
                    }

                    if (propertyCount == 1) {
                        rangeFilteredCode();
                    }
                } else {
                    // Check for mutually exclusive properties
                    if (initialInputArray[2].equalsIgnoreCase("even") && initialInputArray[3].equalsIgnoreCase("odd") ||
                            initialInputArray[2].equalsIgnoreCase("-even") && initialInputArray[3].equalsIgnoreCase("-odd") ||
                            initialInputArray[2].equalsIgnoreCase("odd") && initialInputArray[3].equalsIgnoreCase("even") ||
                            initialInputArray[2].equalsIgnoreCase("-odd") && initialInputArray[3].equalsIgnoreCase("-even") ||
                            initialInputArray[2].equalsIgnoreCase("duck") && initialInputArray[3].equalsIgnoreCase("spy") ||
                            initialInputArray[2].equalsIgnoreCase("spy") && initialInputArray[3].equalsIgnoreCase("duck") ||
                            initialInputArray[2].equalsIgnoreCase("sunny") && initialInputArray[3].equalsIgnoreCase("square") ||
                            initialInputArray[2].equalsIgnoreCase("square") && initialInputArray[3].equalsIgnoreCase("sunny")) {
                        System.out.printf("""

                                        The request contains mutually exclusive properties [%s, %s]
                                        There are no numbers with these properties.

                                        """,
                                initialInputArray[3].toUpperCase(),
                                initialInputArray[2].toUpperCase());
                    } else {
                        // Determine if the properties provided are valid

                        if (initialInputArray.length == 4) {

                            for (String property :
                                    naturalNumberPropertyArray) {

                                if (property.equalsIgnoreCase((initialInputArray[2].replace("-", "")))) {
                                    propertyCount1++;
                                    propertyCount++;
                                }

                                if (property.equalsIgnoreCase(initialInputArray[3].replace("-", ""))) {
                                    propertyCount2++;
                                    propertyCount++;
                                }
                            }

                            // If none of the properties are valid
                            if (propertyCount == 0) {
                                System.out.printf("""

                                                The properties [%s, %s] are wrong.
                                                Available properties: %s

                                                """,
                                        initialInputArray[2].toUpperCase(),
                                        initialInputArray[3].toUpperCase(),
                                        Arrays.toString(naturalNumberPropertyArray));
                                // If the 1st property is valid and 2nd isn't
                            } else if (propertyCount1 == 1 && propertyCount2 == 0) {
                                System.out.printf("""

                                                The property [%s] is wrong.
                                                Available properties: %s

                                                """,
                                        initialInputArray[3].toUpperCase(),
                                        Arrays.toString(naturalNumberPropertyArray));
                                // If the 2nd property is valid and the 1st isn't
                            } else if (propertyCount1 == 0 && propertyCount2 == 1) {
                                System.out.printf("""

                                                The property [%s] is wrong.
                                                Available properties: %s

                                                """,
                                        initialInputArray[2].toUpperCase(),
                                        Arrays.toString(naturalNumberPropertyArray));
                            }

                            noMutualExclusiveMatch = true;


                            if (initialInputArray[2].equals(initialInputArray[3].replace("-", ""))) {
                                System.out.printf("""

                                                The request contains mutually exclusive properties [%s, %s]
                                                There are no numbers with these properties.

                                                """,
                                        initialInputArray[3].toUpperCase(),
                                        initialInputArray[2].toUpperCase());
                                noMutualExclusiveMatch = false;
                            } else if (initialInputArray[3].equals(initialInputArray[2].replace("-", ""))) {
                                System.out.printf("""

                                                The request contains mutually exclusive properties [%s, %s]
                                                There are no numbers with these properties.

                                                """,
                                        initialInputArray[3].toUpperCase(),
                                        initialInputArray[2].toUpperCase());
                                noMutualExclusiveMatch = false;
                            }
                        }

                        if (initialInputArray.length > 4) {

                            String[] initialInputArrayPropertiesLong = Arrays.copyOfRange(initialInputArray, 2, initialInputArray.length);

                            noMutualExclusiveProperties = true;

                            // Check for mutually exclusive properties
                            for (String value : initialInputArrayPropertiesLong) {
                                for (String s : initialInputArrayPropertiesLong) {
                                    if (value.equalsIgnoreCase("even") && s.equalsIgnoreCase("odd") ||
                                            value.equalsIgnoreCase("odd") && s.equalsIgnoreCase("even") ||
                                            value.equalsIgnoreCase("duck") && s.equalsIgnoreCase("spy") ||
                                            value.equalsIgnoreCase("spy") && s.equalsIgnoreCase("duck") ||
                                            value.equalsIgnoreCase("sunny") && s.equalsIgnoreCase("square") ||
                                            value.equalsIgnoreCase("square") && s.equalsIgnoreCase("sunny")) {
                                        System.out.printf("""

                                                        The request contains mutually exclusive properties [%s, %s]
                                                        There are no numbers with these properties.

                                                        """,
                                                value.toUpperCase(),
                                                s.toUpperCase());
                                        noMutualExclusiveProperties = false;
                                        break;
                                    }
                                }
                                if (!noMutualExclusiveProperties) {
                                    break;
                                }
                            }

                            int validPropertyCounter = 0;

                            for (String s :
                                    initialInputArrayPropertiesLong) {
                                for (String t :
                                        naturalNumberPropertyArray) {
                                    if (s.replace("-", "").equalsIgnoreCase(t)) {
                                        propertyCount++;
                                    }

                                    validPropertyCounter++;

                                    if (propertyCount == 0 && validPropertyCounter == naturalNumberPropertyArray.length) {
                                        System.out.printf("""

                                                        The property [%s] is wrong.
                                                        Available properties: %s

                                                        """,
                                                s.toUpperCase(),
                                                Arrays.toString(naturalNumberPropertyArray));
                                        noInvalidProperties = false;
                                        break;
                                    }
                                }
                                if (propertyCount != 0 && validPropertyCounter == naturalNumberPropertyArray.length) {
                                    noInvalidProperties = true;
                                    propertyCount = 0;
                                    validPropertyCounter = 0;
                                    propertyCount1++;
                                } else if (noInvalidProperties = false) {
                                    break;
                                }
                            } if (noInvalidProperties = false) {
                                break;
                            } else {
                                noInvalidProperties = true;
                            }

                            StringBuilder removeProperties = new StringBuilder();

                            for (String s :
                                    initialInputArrayPropertiesLong) {
                                if (s.startsWith("-")) {
                                    removeProperties.append(s).append(" ");
                                }
                            }

                            noMutualExclusiveMatch = true;

                            if (removeProperties.length() > 0) {

                                String[] removePropertiesArray = removeProperties.toString().split(" ");

                                for (String s :
                                        removePropertiesArray) {
                                    for (String t :
                                            initialInputArrayPropertiesLong) {
                                        if (s.replace("-", "").equalsIgnoreCase(t)) {
                                            System.out.printf("""

                                                            The request contains mutually exclusive properties [%s, %s]
                                                            There are no numbers with these properties.

                                                            """,
                                                    s.toUpperCase(),
                                                    t.toUpperCase());
                                            noMutualExclusiveMatch = false;
                                        }
                                    }
                                }
                            }
                        }

                        if (initialInputArray.length == 4 && !doubleMatches && propertyCount1 == 1 && propertyCount2 == 1 && noMutualExclusiveMatch) {
                            rangeFilteredCode();
                        }

                        if (initialInputArray.length > 4 && !doubleMatches && noMutualExclusiveProperties && propertyCount1 == initialInputArray.length - 2 && noMutualExclusiveMatch) {
                            rangeFilteredCode();
                        }
                    }
                }
            } else if (initialInputArray.length == 1 && initialInputArray[0].equals("0")) {
                System.out.println("\nGoodbye!");
                stop = true;
            }
        }
    }

    public static void initializeArray() {

        for (int i = 0; i < naturalNumberInputToString.length(); i++) {
            naturalNumberInputArray[i] = naturalNumberInputToString.charAt(i);
        }

        for (char num :
                Main.naturalNumberInputArray) {
            if (num == '0') {
                zeroCounter++;
            }
        }
    }

    public static void determineParity() {

        if (naturalNumberInput % 2 == 0) {
            isEven = true;
            isOdd = false;
        } else {
            isEven = false;
            isOdd = true;
        }

    }

    public static void determineBuzz() {

        if (naturalNumberInput % 7 == 0 && naturalNumberInputArray[naturalNumberInputArray.length - 1] == '7') {
            isBuzz = true;
        } else if (naturalNumberInput % 7 == 0) {
            isBuzz = true;
        } else isBuzz = naturalNumberInputArray[naturalNumberInputArray.length - 1] == '7';

    }

    public static void determineDuck() {

        if (zeroCounter == 1 && naturalNumberInputArray[0] == '0') {
            isDuck = false;
        } else isDuck = zeroCounter > 0;

        zeroCounter = 0;

    }

    public static void determinePalindromic() {

        boolean[] naturalNumberInputMatchReverse = new boolean[naturalNumberInputArray.length];

        for (int i = 0; i < naturalNumberInputArray.length; i++) {
            naturalNumberInputMatchReverse[i] = naturalNumberInputArray[i] == naturalNumberInputArray[naturalNumberInputArray.length - 1 - i];
        }

        for (boolean bool :
                naturalNumberInputMatchReverse) {
            if (!bool) {
                isPalindromic = false;
                break;
            } else {
                isPalindromic = true;
            }
        }

    }

    public static void determineGapful() {

        StringBuilder divisorSB = new StringBuilder(naturalNumberInputArray.length);

        for (int i = 0; i < naturalNumberInputArray.length; i++) {
            if (i == 0) {
                divisorSB.append(naturalNumberInputArray[i]);
            } else if (i == naturalNumberInputArray.length - 1) {
                divisorSB.append(naturalNumberInputArray[i]);
            }
        }

        long divisorInt = Long.parseLong(divisorSB.toString());


        if (naturalNumberInputArray.length < 3) {
            isGapful = false;
        } else {
            isGapful = naturalNumberInput % divisorInt == 0;
        }
    }

    public static void determineSpy() {

        long sumTotal = 0;
        long productTotal = 1;

        for (char c : naturalNumberInputArray) {
            sumTotal += Long.parseLong(String.valueOf(c));
        }

        for (char c : naturalNumberInputArray) {
            productTotal = productTotal * Long.parseLong(String.valueOf(c));
        }


        isSpy = sumTotal == productTotal;

    }

    public static void determineSquare() {
        double sqrRoot = Math.sqrt(naturalNumberInput);

        isSquare = sqrRoot % 1 == 0;

    }

    public static void determineSunny() {

        double plusOne = naturalNumberInput + 1.0;
        double sqrRoot = Math.sqrt(plusOne);
        // Test if there is a remainder after dividing by 1.
        // If so, then plusOne is not a perfect square number.
        double perfectNumberTest = sqrRoot % 1;


        isSunny = perfectNumberTest == 0.0;

    }

    public static void determineJumping() {

        int jumpingCount = naturalNumberInputArray.length - 1;

        for (int i = 0; i < naturalNumberInputArray.length - 1; i++) {
            if(naturalNumberInputArray[i + 1] - naturalNumberInputArray[i] == 1) {
                jumpingCount--;
            } else if (naturalNumberInputArray[i] - naturalNumberInputArray[i + 1] == 1) {
                jumpingCount--;
            }
        }
        isJumping = jumpingCount == 0;
    }

    public static void determineHappySad() {

        String[] initialArray = Long.toString(naturalNumberInput).split("");

        long sumOfDigits = 0;

        for (String s:
                initialArray) {
            sumOfDigits += Math.pow(Long.parseLong(s), 2);
        }

        HashSet<Long> st = new HashSet<>();



        while (true) {
            String foo = Long.toString(sumOfDigits);
            String[] bar = foo.split("");
            sumOfDigits = 0;

            for (String t :
                    bar) {
                sumOfDigits += Math.pow(Long.parseLong(t), 2);
            }


            if (sumOfDigits == 1) {
                isHappy = true;
                isSad = false;
                break;
            }

            if (st.contains(sumOfDigits)) {
                isHappy = false;
                isSad = true;
                break;
            }

            st.add(sumOfDigits);
        }
    }

    public static void propertyMethods() {
        naturalNumberInputToString = Long.toString(naturalNumberInput);
        naturalNumberInputArray = new char[naturalNumberInputToString.length()];

        initializeArray();
        determineParity();
        determineBuzz();
        determineDuck();
        determinePalindromic();
        determineGapful();
        determineSpy();
        determineSunny();
        determineSquare();
        determineJumping();
        determineHappySad();
    }

    public static void rangeFilteredCode() {
        StringBuilder rangeDeterminationsFilteredString = new StringBuilder();
        int matchCount = 0;
        long count = 0;

        System.out.println();

        while (matchCount < Long.parseLong(String.valueOf(initialInputArray[1]))) {

            naturalNumberInput = Long.parseLong(initialInputArray[0]) + count;

            propertyMethods();

            rangeDeterminationsFilteredString.append(naturalNumberInput).append(" is ");

            if (isEven) {
                rangeDeterminationsFilteredString.append("even, ");
            }
            if (isOdd) {
                rangeDeterminationsFilteredString.append("odd, ");
            }
            if (isBuzz) {
                rangeDeterminationsFilteredString.append("buzz, ");
            }
            if (isDuck) {
                rangeDeterminationsFilteredString.append("duck, ");
            }
            if (isPalindromic) {
                rangeDeterminationsFilteredString.append("palindromic, ");
            }
            if (isGapful) {
                rangeDeterminationsFilteredString.append("gapful, ");
            }
            if (isSpy) {
                rangeDeterminationsFilteredString.append("spy, ");
            }
            if (isSquare) {
                rangeDeterminationsFilteredString.append("square, ");
            }
            if (isSunny) {
                rangeDeterminationsFilteredString.append("sunny, ");
            }
            if (isJumping) {
                rangeDeterminationsFilteredString.append("jumping, ");
            }
            if (isHappy) {
                rangeDeterminationsFilteredString.append("happy");
            }
            if (isSad) {
                rangeDeterminationsFilteredString.append("sad");
            }

            String outputString = rangeDeterminationsFilteredString.toString();
            String outputStringNoComma = outputString.replaceAll(",", "");
            String[] noCommaArray = outputStringNoComma.split(" ");
            StringBuilder removeProperties = new StringBuilder();
            String removePropertiesString;
            String[] removePropertiesArray;
            StringBuilder keepProperties = new StringBuilder();
            String keepPropertiesSting;
            String[] keepPropertiesArray;

            for (String s :
                    initialInputArray) {
                if (s.startsWith("-")) {
                    removeProperties.append(s.replace("-", " "));
                }
            }

            removePropertiesString = removeProperties.toString();

            if (removePropertiesString.length() > 1) {
                removePropertiesString = removePropertiesString.replaceFirst(" ", "");
            }

            if (removePropertiesString.length() == 0) {
                removePropertiesArray = new String[0];
            } else {
                removePropertiesArray = removePropertiesString.split(" ");
            }

            for (String s :
                    initialInputArray) {
                if (Character.isAlphabetic(s.charAt(0))) {
                    keepProperties.append(s).append(" ");
                }
            }

            keepPropertiesSting = keepProperties.toString();
            keepPropertiesArray = keepPropertiesSting.split(" ");

            int keepMatch = 0;
            int removeMatch = 0;

            if (initialInputArray.length == 3) {
                for (String s :
                        noCommaArray) {
                    for (String s2 :
                            keepPropertiesArray) {
                        if (s.equalsIgnoreCase(s2)) {
                            keepMatch++;
                        }
                    }
                }

                for (String s :
                        noCommaArray) {
                    for (String s2 :
                            removePropertiesArray) {
                        if (s.equalsIgnoreCase(s2)) {
                            removeMatch++;
                        }
                    }
                }

                if (removePropertiesArray.length == 0 && keepMatch > 0) {
                    System.out.println(outputString);
                    matchCount++;
                } else if (removePropertiesArray.length >= 1 && removeMatch == 0) {
                    System.out.println(outputString);
                    matchCount++;
                }
            } else if (initialInputArray.length == 4) {

                for (String s :
                        noCommaArray) {
                    for (String s2 :
                            keepPropertiesArray) {
                        if (s.equalsIgnoreCase(s2)) {
                            keepMatch++;
                        }
                    }
                }

                for (String s :
                        noCommaArray) {
                    for (String s2 :
                            removePropertiesArray) {
                        if (s.equalsIgnoreCase(s2)) {
                            removeMatch++;
                        }
                    }
                }

                if (removePropertiesArray.length == 0 && keepMatch == keepPropertiesArray.length) {
                    System.out.println(outputString);
                    matchCount++;
                } else if (removePropertiesArray.length == 1 && keepMatch == 1 && removeMatch == 0) {
                    System.out.println(outputString);
                    matchCount++;
                } else if (removePropertiesArray.length == 2 && removeMatch == 0) {
                    System.out.println(outputString);
                    matchCount++;
                }
            } else if (initialInputArray.length > 4) {

                for (String s :
                        noCommaArray) {
                    for (String s2 :
                            keepPropertiesArray) {
                        if (s.equalsIgnoreCase(s2)) {
                            keepMatch++;
                        }
                    }
                }

                for (String s :
                        noCommaArray) {
                    for (String s2 :
                            removePropertiesArray) {
                        if (s.equalsIgnoreCase(s2)) {
                            removeMatch++;
                        }
                    }
                }

                if (removePropertiesArray.length == 0 && keepMatch == keepPropertiesArray.length) {
                    System.out.println(outputString);
                    matchCount++;
                } else if (removePropertiesArray.length >= 1 && keepMatch == keepPropertiesArray.length && removeMatch == 0) {
                    System.out.println(outputString);
                    matchCount++;
                }
            }
            rangeDeterminationsFilteredString.setLength(0);
            count++;
        }
        System.out.println();
    }

    public static void rangeDeterminations() {

        for (long i = Long.parseLong(initialInputArray[0]); i < Long.parseLong(initialInputArray[0]) + Long.parseLong(initialInputArray[1]); i++) {
            naturalNumberInput = i;

            propertyMethods();

            System.out.printf("%d is ", naturalNumberInput);
            if (isEven) {
                System.out.print("even, ");
            }
            if (isOdd) {
                System.out.print("odd, ");
            }
            if (isBuzz) {
                System.out.print("buzz, ");
            }
            if (isDuck) {
                System.out.print("duck, ");
            }
            if (isPalindromic) {
                System.out.print("palindromic, ");
            }
            if (isGapful) {
                System.out.print("gapful, ");
            }
            if (isSpy) {
                System.out.print("spy, ");
            }
            if (isSquare) {
                System.out.print("square, ");
            }
            if (isSunny) {
                System.out.print("sunny, ");
            }
            if (isJumping) {
                System.out.print("jumping, ");
            }
            if (isHappy) {
                System.out.print("happy");
            }
            if (isSad) {
                System.out.print("sad");
            }

            System.out.println();
        }
        System.out.println();
    }
}
