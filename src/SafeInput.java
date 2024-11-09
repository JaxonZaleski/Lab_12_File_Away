import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SafeInput {
    public static void prettyHeader(String msg) {

        //************************************************************
        //***               Message Centered Here                  ***
        //************************************************************
        for (int i=1; i<=3; i++) {
            for (int row = 1; row <= 60; row++) {
                if (i==1) {
                    System.out.print("*");
                } else if (i==2) {
                    if (row<=3) {
                        System.out.print("*");
                    } else if (row<58) {
                        if (row==60%msg.length()) {
                            System.out.print(msg);
                            row+=msg.length();
                        }
                        System.out.print(" ");
                    } else {
                        System.out.print("*");
                    }
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
    public static String getNonZeroLenString(Scanner pipe, String prompt) {

        String retString = "";

        do {
            System.out.println("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);
        return retString;
    }
    public static int getInt(Scanner pipe, String prompt) {

        int enterInt = 0;
        boolean done = false;

        do {
            System.out.println("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                enterInt = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                System.out.println("You must enter a number.");
                pipe.nextLine();
            }
        } while (!done);
        System.out.println("You entered: " + enterInt);
        return enterInt;
    }
    public static double getDouble(Scanner pipe, String prompt) {

        double enterDouble = 0.0;
        boolean done = false;

        do {
            System.out.println("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                enterDouble = pipe.nextDouble();
                done = true;
            } else {
                System.out.println("You must enter a number.");
                pipe.nextLine();
            }
        } while(!done);
        System.out.println("You entered: " + enterDouble);
        return enterDouble;
    }
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {

        int enterRangedInt = 0;
        boolean done = false;

        if (prompt.equalsIgnoreCase("Enter your favorite integer from 1-100") || prompt.equalsIgnoreCase("Enter a number from 1-100")) {
            do {
                System.out.println("\n" + prompt + ": ");
                if (pipe.hasNextInt()) {
                    enterRangedInt = pipe.nextInt();
                    if (enterRangedInt<high && enterRangedInt>low) {
                        done = true;
                        System.out.println();
                        System.out.println("Low = " + low + " | high = " + high);
                    }
                } else {
                    System.out.println("You must enter a number.");
                    pipe.nextLine();
                }
            } while (!done);
        } else if (prompt.equalsIgnoreCase("Enter your birthday")) {
            int year = 0;
            int month = 0;
            int day = 0;
            int hour = 0;
            int minutes = 0;
            int [] birth = new int[5];
            System.out.println("\n" + prompt + ": ");
            do {
                System.out.println("Enter your birth year from 1950-2015");
                if (pipe.hasNextInt()) {
                    year = pipe.nextInt();
                    if (year >= 1950 && year <= 2015) {
                        done = true;
                    } else {
                        System.out.println("It must be 1950-2015");
                        pipe.nextLine();
                    }
                } else {
                    System.out.println("You must enter a number");
                    pipe.nextLine();
                }
            } while(!done);
            done = false;
            do {
                System.out.println("Enter your birth month 1-12");
                if (pipe.hasNextInt()) {
                    month = pipe.nextInt();
                    if (month >=1 && month <= 12) {
                        done = true;
                    } else {
                        System.out.println("It must be 1-12");
                        pipe.nextLine();
                    }
                } else {
                    System.out.println("You must enter a number");
                    pipe.nextLine();
                }
            } while (!done);
            done = false;
            do {
                do {
                    System.out.println("Enter your birth day");
                    if (pipe.hasNextInt()) {
                        day = pipe.nextInt();
                        done = true;
                    } else {
                        System.out.println("You must enter a number");
                        pipe.nextLine();
                        pipe.nextLine();
                    }
                } while(!done);
                done = false;
                if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    if (day <= 31 && day >= 1) {
                        done = true;
                    } else {
                        System.out.println("You entered the wrong day for that month");
                        pipe.nextLine();
                    }
                } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                    if (day <= 30 && day >= 1) {
                        done = true;
                    } else {
                        System.out.println("You entered the wrong day for that month");
                        pipe.nextLine();
                    }
                } else if (day <= 29 && day >= 1) {
                    done = true;
                } else {
                    System.out.println("You entered the wrong day for that month");
                    pipe.nextLine();
                }
            } while(!done);
            done = false;

            do {
                System.out.println("Enter the hour of your birth");
                if (pipe.hasNextInt()) {
                    hour = pipe.nextInt();
                    done = true;
                } else {
                    System.out.println("You must enter a number");
                    pipe.nextLine();
                }
            } while (!done);
            done = false;
            do {
                if (hour >= 1 && hour <= 24) {
                    done = true;
                } else {
                    System.out.println("It must be 1-24");
                    pipe.nextLine();
                }
            } while (!done);
            done = false;
            do {
                System.out.println("Enter the minute you were born [0-59]");
                if (pipe.hasNextInt()) {
                    minutes = pipe.nextInt();
                    done = true;
                } else {
                    System.out.println("You must enter a number");
                    pipe.nextLine();
                }
            } while (!done);
            done = false;
            do {
                if (minutes >= 0 && minutes <= 59) {
                    done = true;
                } else {
                    System.out.println("It must be 0-59");
                }
            } while (!done);
            System.out.println("Year: " + year);
            System.out.println("Month: " + month);
            System.out.println("Day: " + day);
            System.out.println("Hour: " + hour);
            System.out.println("Minutes: " + minutes);
            enterRangedInt = year;
        }
        return enterRangedInt;
    }
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {

        double enterRangedDouble = 0;
        boolean done = false;
        boolean yesNo = true;

        if (prompt.equalsIgnoreCase("Enter your favorite double from 1-10 [ex: 1.53]")) {
            do {
                System.out.println("\n" + prompt + ": ");
                if (pipe.hasNextDouble()) {
                    enterRangedDouble = pipe.nextInt();
                    done = true;
                } else {
                    System.out.println("You must enter a number.");
                    pipe.nextLine();
                    pipe.nextLine();
                }
            } while (!done);
            low = low + enterRangedDouble - enterRangedDouble + 1;
            high = high + enterRangedDouble;
            System.out.println();
            System.out.println("Low = " + low + " | high = " + high);
            pipe.nextLine();
        } else if (prompt.equalsIgnoreCase("Welcome to the $10 store")) {
            boolean yNConfirm = false;
            double enterPrice = 0;

            System.out.println("\n" + prompt + ": ");
            do {
                System.out.println("Enter the item price [$0.50-$10.00]");
                do {
                    done = false;
                    if (pipe.hasNextDouble()) {
                        enterPrice = pipe.nextDouble();
                        if (enterPrice >= 0.50 && enterPrice <= 10.00) {
                            done = true;
                            //total cost
                            enterRangedDouble = enterRangedDouble + enterPrice;
                            pipe.nextLine();
                        } else {
                            System.out.println("It must be $0.50-$10");
                            pipe.nextLine();
                        }
                    } else {
                        System.out.println("You must enter a number");
                        pipe.nextLine();
                    }
                } while (!done);
                System.out.printf("The total price: " + enterRangedDouble);
                System.out.println();
                yesNo = getYNConfirm(pipe, "Do you have more items, type 'Y' or 'N'");
            } while (!yesNo);
        }
        return enterRangedDouble;
    }
    public static boolean getYNConfirm(Scanner pipe, String prompt) {

        boolean finished = false;
        boolean yesNo = false;
        String yOrN = "";

        do {
            System.out.println("\n" + prompt + ": ");
            System.out.println("Enter Y or N");
            yOrN = pipe.nextLine();
            if (yOrN.equalsIgnoreCase("Y")) {
                yesNo = true;
                finished = true;
            } else if (yOrN.equalsIgnoreCase("N")) {
                finished = true;
            } else {
                System.out.println("You must enter Y or N");
            }
        } while (!finished);
        return yesNo;
    }
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {

        boolean matches = false;
        String pattern = "";

        do {
            System.out.println("\n" + prompt + ": ");
            pattern = pipe.nextLine();
            if (pattern.matches(regEx)) {
                matches = true;
            } else {
                System.out.println("Invalid input: " + pattern);
            }
        } while (!matches);
        return pattern;
    }
}
