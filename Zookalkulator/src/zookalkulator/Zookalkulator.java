import java.util.Scanner;

public class Zookalkulator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- Preisdefinitionen ---
        final double PREIS_ERWACHSEN = 24.50;
        final double PREIS_ERMAESSIGT = 14.90;
        final double PARKGEBUEHR = 6.00;

        // Zusatzangebote
        final double ZIEGENFUETTERUNG = 4.00;
        final double ORCA_SHOW = 12.00;

        System.out.println("=== Willkommen im Zoo-Kostenkalkulator ===\n");

        // --- Personendaten ---
        System.out.print("Anzahl Erwachsene: ");
        int anzahlErwachsene = scanner.nextInt();

        System.out.print("Anzahl Ermäßigte (Kinder/Schüler/Senioren): ");
        int anzahlErmaessigt = scanner.nextInt();

        // --- Parkgebühr ---
        System.out.print("Parkplatz benötigt? (j/n): ");
        boolean parkplatz = scanner.next().equalsIgnoreCase("j");

        // --- Zusatzangebote ---
        System.out.print("Ziegenfütterung hinzufügen? (+4,00 € pro Person) (j/n): ");
        boolean ziegen = scanner.next().equalsIgnoreCase("j");

        System.out.print("Orca-Show besuchen? (+12,00 € pro Person) (j/n): ");
        boolean orca = scanner.next().equalsIgnoreCase("j");

        // --- Berechnung ---
        double kostenEintritt = anzahlErwachsene * PREIS_ERWACHSEN +
                                anzahlErmaessigt * PREIS_ERMAESSIGT;

        double kostenParken = parkplatz ? PARKGEBUEHR : 0;

        double kostenExtras = 0;
        int gesamtPersonen = anzahlErwachsene + anzahlErmaessigt;

        if (ziegen) kostenExtras += gesamtPersonen * ZIEGENFUETTERUNG;
        if (orca) kostenExtras += gesamtPersonen * ORCA_SHOW;

        double gesamt = kostenEintritt + kostenParken + kostenExtras;

        // --- Auftragsnummer generieren ---
        String auftragsNummer = generateOrderNumber();

        // --- Ausgabe ---
        System.out.println("\n===== Kostenübersicht =====");
        System.out.printf("Eintritt Erwachsene: %d × %.2f € = %.2f €\n",
                anzahlErwachsene, PREIS_ERWACHSEN, anzahlErwachsene * PREIS_ERWACHSEN);

        System.out.printf("Eintritt Ermäßigt: %d × %.2f € = %.2f €\n",
                anzahlErmaessigt, PREIS_ERMAESSIGT, anzahlErmaessigt * PREIS_ERMAESSIGT);

        if (parkplatz)
            System.out.printf("Parkplatz: %.2f €\n", kostenParken);

        if (ziegen)
            System.out.printf("Ziegenfütterung: %.2f €\n", gesamtPersonen * ZIEGENFUETTERUNG);

        if (orca)
            System.out.printf("Orca-Show: %.2f €\n", gesamtPersonen * ORCA_SHOW);

        System.out.println("---------------------------");
        System.out.printf("GESAMT: %.2f €\n", gesamt);

        System.out.println("\nIhre Auftragsnummer: " + auftragsNummer);
        System.out.println("Barcode:");
        printBarcode(auftragsNummer);

        System.out.println("\nVielen Dank für Ihre Buchung!");
    }

    public static String generateOrderNumber() {
        return "ZOO-" + (int)(Math.random() * 900000 + 100000);
    }

    public static void printBarcode(String code) {
        for (char c : code.toCharArray()) {
            int value = (int) c;

            for (int i = 0; i < value % 10 + 3; i++) {
                System.out.print("|");
            }
            System.out.println();
        }
    }
}
