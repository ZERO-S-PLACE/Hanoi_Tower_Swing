import java.awt.*;
import java.util.ArrayList;

public class AnimationColors {
    public static Color backgroundColor = new Color(126, 197, 184);
    private ArrayList<Color> ringColor = new ArrayList<>();

    public ArrayList<Color> getRingColor() {
        return ringColor;
    }

    public void createColors(int h) {
        int rEnd = 63;
        int gEnd = 162;
        int bEnd = 217;

        int rStart = 36;
        int gStart = 196;
        int bStart = 89;
        for (int i = 0; i < h; i++) {
            ringColor.add(new Color((rStart + i * (rEnd - rStart) / h), (gStart + i * (gEnd - gStart) / h), (bStart + i * (bEnd - bStart) / h)));
        }


    }

}
