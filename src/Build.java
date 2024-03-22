public class Build {

    public static int[][] colorTable(int[][] rod) {
        int h = Main.getH();


        int[][] colorTableV = new int[Main.getH()][Main.getCols()];


        for (int i = 0; i < h; i++) {

            int j_cT = 0; // j w tablicy colorTable

            for (int j = 0; j < 3; j++) {

                if (rod[j][h - i - 1] != 0) {

                    for (int j1 = 0; j1 < h - rod[j][h - i - 1]; j1++) {
                        colorTableV[i][j_cT] = 0;
                        j_cT++;
                    }

                    for (int j1 = 0; j1 < 2 * rod[j][h - i - 1] - 1; j1++) {
                        colorTableV[i][j_cT] = rod[j][h - i - 1];
                        j_cT++;
                    }

                    for (int j1 = 0; j1 < h - rod[j][h - i - 1]; j1++) {
                        colorTableV[i][j_cT] = 0;
                        j_cT++;
                    }

                } else {
                    for (int j1 = 0; j1 < 2 * h - 1; j1++) {
                        colorTableV[i][j_cT] = 0;
                        j_cT++;
                    }
                }

                if (j != 2) {
                    colorTableV[i][j_cT] = 0;
                    j_cT++;
                    colorTableV[i][j_cT] = 0;
                    j_cT++;
                }


            }

        }
        return colorTableV;
    }
}
