package gestaorh;

import java.util.Scanner;

/**
 *
 * @author creep
 */
public class UserInput {

    Scanner scan;

    /**
     *
     */
    public UserInput() {

        scan = new Scanner(System.in);

    }

    /**
     *
     * @param frase
     * @return
     */
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

    /**
     *
     * @param frase
     * @return
     */
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

    /**
     *
     * @param frase
     * @return
     */
    public String lerFrase(String frase) {
        System.out.print(frase + " > ");
        return scan.next();
    }

    /**
     *
     * @param frase
     * @return
     */
    public char lerChar(String frase) {
        System.out.print(frase + " > ");
        return scan.next().charAt(0);
    }
    
    /**
     *
     */
    public void closeScanner() {
    	scan.close();
    }

}
