public class HighInd {
    public static int ind(int[] rod) {
        if (rod[0] != 0) {
            for (int i = 1; i < rod.length; i++) {
                if (rod[i] == 0 && rod[i - 1] != 0) {
                    return i - 1;
                }
                if (rod[i] != 0 && i == rod.length - 1) {
                    return i;
                }


            }
        }
        return 0;
    }
}
