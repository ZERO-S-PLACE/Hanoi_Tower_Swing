import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.BoxLayout.X_AXIS;
import static javax.swing.BoxLayout.Y_AXIS;

public class Main {
    static JFrame frame;
    static JLayeredPane displayPanel;
    static Box background;
    static Box upperPanel;
    static Box lowerPanel;
    static Box endPanel;
    static JButton ustawienia;
    static JButton pauza;
    static JLabel krok;
    static JLabel koniecOpis;
    private static int h;
    private static double t;
    private static double tAnim;
    private static int kroki;
    private static int cols;
    private static int wField;
    private static JPanel animation;
    private static ArrayList<Color> ringColor;
    private static JLabel[][] animationLabel;
    JButton start;

    public static void main(String[] args) {

        Main main = new Main();
        main.buildMainGUI();
        h = 7;
        t = 1;
        tAnim = 0.1;
        cols = 3 * (2 * h - 1) + 4;
        wField = 700 / cols;


    }

    public static void buildAnimationPanel() {
        AnimationColors aColors = new AnimationColors();
        aColors.createColors(h);
        ringColor = aColors.getRingColor();
        cols = 3 * (2 * h - 1) + 4;
        animationLabel = new JLabel[h][cols];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < cols; j++) {
                animationLabel[i][j] = new JLabel("");
                animationLabel[i][j].setPreferredSize(new Dimension(wField, wField));
                animationLabel[i][j].setBackground(AnimationColors.backgroundColor);
                animationLabel[i][j].setOpaque(true);
                animation.add(animationLabel[i][j]);
            }

        }
    }

    public static void refreshAnimationPanel(int[][] rod) {
        int[][] colorTable = Build.colorTable(rod);
        cols = 3 * (2 * h - 1) + 4;




        animation.setBounds(0, 0, frame.getWidth(), frame.getHeight() - 150);


        wField = animation.getWidth() / cols;

        if (wField == 0) {
            wField = 1;
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < cols; j++) {
                if (colorTable[i][j] == 0) {
                    animationLabel[i][j].setBackground(AnimationColors.backgroundColor);
                } else {
                    animationLabel[i][j].setBackground(ringColor.get(colorTable[i][j] - 1));
                }
                animationLabel[i][j].setPreferredSize(new Dimension(wField, wField));

            }

        }
        animation.revalidate();
        animation.repaint();

        try {
            Thread.sleep((long) (tAnim * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static int[][] generateStartRods() {
        int[][] rod = new int[3][h];
        // wygenerowanie tablicy z krążkami(numery) na pierwszym słupku(kolumnie)
        for (int i = 0; i < h; i++) {
            rod[0][i] = h - i;
            rod[1][i] = 0;
            rod[2][i] = 0;
        }
        return rod;
    }

    public static void updateJLabelKrok(int kroki) {
        krok.setText("KROK : " + kroki);
        krok.repaint();
    }

    public static void updateJLabelKoniecOpis() {

        koniecOpis.setText(CalculateTime.calculate(kroki, t));
        koniecOpis.repaint();
        endPanel.setVisible(true);

    }

    public static int getH() {
        return h;
    }

    public static void setH(int h) {
        Main.h = h;
    }

    public static void setT(double t) {
        Main.t = t;
    }

    public static void settAnim(double tAnim) {
        Main.tAnim = tAnim;
    }

    public static int getKroki() {
        return kroki;
    }

    public static void setKroki(int kroki) {
        updateJLabelKrok(kroki);
        Main.kroki = kroki;
    }

    public static int getCols() {
        return cols;
    }

    public static void disposeFrame() {
        frame.dispose();
    }

    public static void clickUstawienia() {
        ustawienia.doClick();
    }

    public void buildMainGUI() {
        kroki = 0;
        cols = 3 * (2 * h - 1) + 4;

        wField = 700 / cols;
        if (wField == 0) {
            wField = 1;
        }


        ustawienia = new JButton("Ustawienia");//tworzymy po kolei przyciski , i action listenery jak je naciśniemy
        ustawienia.addActionListener(new UstawieniaListener());
        ustawienia.setFont(new Font("Arial", Font.BOLD, 10));
        ustawienia.setBackground(Color.DARK_GRAY);
        ustawienia.setForeground(Color.WHITE);


        start = new JButton("START");
        start.addActionListener(new startListener());
        start.setFont(new Font("Arial", Font.BOLD, 10));
        start.setBackground(Color.DARK_GRAY);
        start.setForeground(Color.WHITE);

        pauza = new JButton("PAUZA");
        pauza.addActionListener(new pauzaListener());
        pauza.setFont(new Font("Arial", Font.BOLD, 10));
        pauza.setBackground(Color.DARK_GRAY);
        pauza.setForeground(Color.WHITE);

        krok = new JLabel("KROK: 0");
        krok.setFont(new Font("Arial", Font.BOLD, 20));


        krok.setForeground(Color.WHITE);


        upperPanel = new Box(X_AXIS);// tworzymy panele na przyciski i dodajemy je do nich


        lowerPanel = new Box(X_AXIS);


        upperPanel.add(start);
        upperPanel.add(pauza);
        upperPanel.add(ustawienia);

        upperPanel.setBorder(BorderFactory.createEmptyBorder(5, 70, 5, 70));


        lowerPanel.add(krok);
        lowerPanel.setBackground(Color.DARK_GRAY);
        lowerPanel.setOpaque(true);
        lowerPanel.setBorder(BorderFactory.createEmptyBorder(5, 70, 5, 70));


        GridLayout grid = new GridLayout(h, cols);// tworzymy panele pod główne pole
        grid.setVgap(0);
        grid.setHgap(0);
        animation = new JPanel(grid);
        buildAnimationPanel();


        JLabel koniec = new JLabel("KONIEC");
        koniec.setFont(new Font("Arial", Font.BOLD, 40));
        koniec.setForeground(Color.DARK_GRAY);
        koniec.setOpaque(false);
        koniecOpis = new JLabel(" ");
        koniecOpis.setFont(new Font("Arial", Font.BOLD, 10));
        koniecOpis.setForeground(Color.DARK_GRAY);
        koniecOpis.setOpaque(false);


        endPanel = new Box(BoxLayout.Y_AXIS);
        endPanel.setOpaque(false);
        endPanel.add(koniec);
        endPanel.add(koniecOpis);
        endPanel.setVisible(false);


        displayPanel = new JLayeredPane(); // na głównym panelu pod spodem są cyfry, na wierzchu przyciski, w razie przegranej komunikat
        displayPanel.setLayout(null);
        displayPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        displayPanel.add(animation, Integer.valueOf(1));
        displayPanel.add(endPanel, Integer.valueOf(2));


        background = new Box(Y_AXIS);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        background.add(upperPanel);// dodajemy wszystko do tła, a tło do ramki
        background.add(displayPanel);
        background.add(lowerPanel);


        frame = new JFrame("H_A_N_O_I___J_A_V_A");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(background);


        frame.setBounds(50, 50, 700, 400);
        displayPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight() - 150);
        animation.setBounds(0, 0, displayPanel.getWidth(), displayPanel.getHeight());
        endPanel.setBounds(50, 50, displayPanel.getWidth(), displayPanel.getHeight());

        background.setPreferredSize(new Dimension(frame.getWidth(), (frame.getHeight())));
        upperPanel.setPreferredSize(new Dimension(frame.getWidth(), 30));
        lowerPanel.setPreferredSize(new Dimension(frame.getWidth(), 50));

        animation.setPreferredSize(new Dimension(frame.getWidth(), (frame.getHeight()) - 150));

        frame.pack();
        frame.setVisible(true);

    }

    public static class UstawieniaListener implements ActionListener {

        public void actionPerformed(ActionEvent a) {
            UstawieniaGUI that = new UstawieniaGUI();
            that.actionPerformed();
        }
    }

    public static class startListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            frame.dispose();

            new Thread(() -> {

                Main main = new Main();
                main.buildMainGUI();


                Solve.towersolve(generateStartRods());
            }).start();

        }
    }

    public static class pauzaListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            if (pauza.getText().equals("PAUZA")) {
                pauza.setText("RESUME");
                Solve.pauza = true;
            } else {
                pauza.setText("PAUZA");
                Solve.pauza = false;
                Solve.resumeThis();
            }

        }
    }

}