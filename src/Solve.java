import java.util.concurrent.CountDownLatch;

public class Solve {
    static int d;//kierunek przesuwania krążka
    static boolean pauza = false;

    static CountDownLatch latch = new CountDownLatch(1);


    synchronized public static void towersolve(int[][] rod) {
        int h = Main.getH();





        int next = 1;
        int current = 0;
        int other = 2;
        if (h % 2 == 1) {//kierunek przesuwania krążka - jeśli nieparzyste to w prawo, jeśli parzyste lewo
            d = -1;
            next = 2;
            other = 1;
        } else {
            d = 1;
        }

        while (rod[2][h - 1] != 1) {

            //Przesunięcie małego krążka o 1

            if (rod[next][HighInd.ind(rod[(next)])] != 0) {
                rod[next][HighInd.ind(rod[(next)]) + 1] = rod[current][HighInd.ind(rod[current])];
            } else {
                rod[next][0] = rod[current][HighInd.ind(rod[current])];
            }

            rod[current][HighInd.ind(rod[current])] = 0;

            if (!Main.frame.isVisible()) {
                return;
            }
            Main.setKroki(Main.getKroki() + 1);
            Main.refreshAnimationPanel(rod);
            if (pauza) {

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }


            //Wykonanie jedynego możliwego ruchu

            if (rod[current][HighInd.ind(rod[current])] > rod[other][HighInd.ind(rod[other])]) {

                if (rod[other][HighInd.ind(rod[(other)])] != 0) {
                    rod[current][HighInd.ind(rod[current]) + 1] = rod[other][HighInd.ind(rod[other])];
                    rod[other][HighInd.ind(rod[other])] = 0;
                } else {
                    rod[other][0] = rod[current][HighInd.ind(rod[current])];
                    rod[current][HighInd.ind(rod[current])] = 0;
                }

                Main.setKroki(Main.getKroki() + 1);


            } else if (rod[current][HighInd.ind(rod[current])] < rod[other][HighInd.ind(rod[other])]) {

                if (rod[current][HighInd.ind(rod[(current)])] != 0) {
                    rod[other][HighInd.ind(rod[other]) + 1] = rod[current][HighInd.ind(rod[current])];
                    rod[current][HighInd.ind(rod[current])] = 0;
                } else {
                    rod[current][0] = rod[other][HighInd.ind(rod[other])];
                    rod[other][HighInd.ind(rod[other])] = 0;
                }

                Main.setKroki(Main.getKroki() + 1);


            }
            if (!Main.frame.isVisible()) {
                return;
            }

            Main.refreshAnimationPanel(rod);
            if (pauza) {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }


            other = (other + d) % 3;
            current = (current + d) % 3;
            next = (next + d) % 3;
            if (next == -1) {
                next = 2;
            }
            if (current == -1) {
                current = 2;
            }
            if (other == -1) {
                other = 2;
            }
        }
        Main.updateJLabelKoniecOpis();


    }

    public static void resumeThis() {
        latch.countDown();
        latch = new CountDownLatch(1);

    }

}

