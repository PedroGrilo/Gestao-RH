package gestaorh;

import java.util.Scanner;

public class UserInput {

    Scanner scan;

    public UserInput() {

        scan = new Scanner(System.in);

    }

    public int lerInteiro(String frase) {
        int inteiro;

        System.out.print(frase + " > ");
        while (!scan.hasNextInt()) {
            System.out.print(frase + " > ");
            scan.next();
        }
        inteiro = scan.nextInt();
        scan.nextLine();
        return inteiro;
    }

    public double lerDouble(String frase) {
        double real;

        System.out.print(frase + " > ");
        while (!scan.hasNextDouble()) {
            System.out.print(frase + " > ");
            scan.nextLine();
        }
        real = scan.nextDouble();
        scan.nextLine();

        return real;
    }

    public String lerFrase(String frase) {
        System.out.print(frase + " > ");
        return scan.next();
    }

    public char lerChar(String frase) {
        System.out.print(frase + " > ");
        return scan.next().charAt(0);
    }

}
