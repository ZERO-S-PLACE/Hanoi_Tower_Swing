import java.time.LocalDateTime;

public class CalculateTime {


    public static String calculate(int kroki, double t) {
        // przeliczenie czasu na lata

        long duration = (long) t * kroki;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.plusSeconds(duration);

        int lata = end.getYear() - now.getYear();
        int dni = end.getDayOfYear() - now.getDayOfYear();
        int godziny = end.getHour() - now.getHour();
        int minuty = end.getMinute() - now.getMinute();
        int sekundy = end.getSecond() - now.getSecond();

        if (sekundy < 0) {
            sekundy = sekundy + 60;
            minuty--;
        }
        if (minuty < 0) {
            minuty = minuty + 60;
            godziny--;
        }
        if (godziny < 0) {
            godziny = godziny + 24;
            dni--;
        }
        if (dni < 0) {
            dni = dni + 365;
            lata--;
        }

        System.out.println("Przeniesiono wieżę w " + kroki + " krokach , w czasie " + kroki * t);
        System.out.println("Czas układania przy tempie 1 przełożenia na  " + t + " sekund(y) wyniesie " + lata + " lat " + dni + " dni " + godziny + " h " + minuty + " min " + sekundy + " s.");

        return "<html>Przeniesiono wieżę w " + kroki + " krokach <br><br>" + "Czas układania przy tempie 1 przełożenia " + "na  " + t + " sekund(y) wyniesie: <br>" + lata + " lat <br>" + dni + " dni <br>" + godziny + " h <br>" + minuty + " min <br>" + sekundy + " s.</html>";


    }
}
