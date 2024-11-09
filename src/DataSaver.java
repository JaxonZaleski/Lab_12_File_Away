import java.sql.Array;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import static java.nio.file.StandardOpenOption.CREATE;

public class DataSaver {
    private static String[] csvLine = new String[100];
    private static int count = 0;
    public static void main (String[] args) {

        for (int i=0; i<csvLine.length; i++) {
            csvLine[i] = "| ";
        }
        Scanner in = new Scanner(System.in);
        boolean done = false;
        boolean finished = false;

        do {
            String firstName = "";
            String lastName = "";
            String email = "";
            int id = 0;
            int birthYear = 0;
            firstName = SafeInput.getNonZeroLenString(in, "Enter your First name");
            lastName = SafeInput.getNonZeroLenString(in, "Enter your Last name");
            do {
                System.out.println();
                System.out.println("Enter your 6 digit id number: ");
                if (in.hasNextInt()) {
                    id = in.nextInt();
                    String length = String.valueOf(id);
                    if (length.length() == 6) {
                        done = true;
                    } else {
                        in.nextLine();
                        System.out.println("It must be 6 digits");
                    }
                } else {
                    in.nextLine();
                }
            } while (!done);
            done = false;
            in.nextLine();
            System.out.println();
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
            do {
                System.out.println("Enter your email: ");
                email = in.nextLine();
                if (pattern.matcher(email).find()) {
                    done = true;
                } else {
                    System.out.println("Invalid input: " + email);
                }
            } while (!done);
            done = false;
            System.out.println();
            do {
                System.out.println("Enter your birth year: ");
                if (in.hasNextInt()) {
                    birthYear = in.nextInt();
                    String length = String.valueOf(birthYear);
                    if (length.length() == 4) {
                        done = true;
                    } else {
                        in.nextLine();
                        System.out.println("It must be 4 digits");
                    }
                } else {
                    in.nextLine();
                }
            } while (!done);
            in.nextLine();
            System.out.println();
            String[] csvInput = new String[5];
            csvInput[0] = firstName + ", ";
            csvInput[1] = lastName + ", ";
            csvInput[2] = id + ", ";
            csvInput[3] = email + ", ";
            csvInput[4] = birthYear + "";
            for (int i=0; i<csvInput.length; i++) {
                csvLine[count] = csvLine[count] + csvInput[i];
            }
            count++;
            finished = SafeInput.getYNConfirm(in, "Do you have more records to input?");
        } while (finished != false);
        writer(csvLine);
        read();
    }

    public static void writer (String[] line) {

        Path writeFile = new File(System.getProperty("user.dir")).toPath();
        writeFile = writeFile.resolve("src\\data.txt");

        try {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(writeFile, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for (int i=0; i<count; i++) {
                writer.write(line[i]);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public static void read () {

        Path readFile = new File(System.getProperty("user.dir")).toPath();
        readFile = readFile.resolve("src\\data.txt");
        System.out.println("Path is " + readFile);

        try {
            InputStream in =
                    new BufferedInputStream(Files.newInputStream(readFile, CREATE));
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(in));
            while (reader.ready()) {
                System.out.println(reader.readLine());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
