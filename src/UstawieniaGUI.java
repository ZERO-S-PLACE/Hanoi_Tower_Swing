import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UstawieniaGUI {


    JButton applyButton;
    JTextField wysokoscText;
    JFrame ustFrame;
    JTextField czasText;
    JTextField czasAnimacjiText;


    public void actionPerformed() {


        Main.disposeFrame();
        ustFrame = new JFrame("USTAWIENIA");
        ustFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout(4, 2);//stworzenie układu
        grid.setVgap(3);
        grid.setHgap(3);
        JPanel back = new JPanel(grid);
        back.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel wysokoscLabel = new JLabel("Ilość pięter : ");
        JLabel czasLabel = new JLabel("Czas ruchu - do obliczeń [ms]:");
        JLabel czasAnimacjiLabel = new JLabel("Czas ruchu- animacja [ms]:");
        JLabel emptyLabel = new JLabel("");

        wysokoscText = new JTextField(4);
        czasText = new JTextField(4);
        czasAnimacjiText = new JTextField(4);

        applyButton = new JButton("ZASTOSUJ");
        applyButton.addActionListener(new ApplyButtonListener());


        back.add(wysokoscLabel);
        back.add(wysokoscText);
        back.add(czasLabel);
        back.add(czasText);
        back.add(czasAnimacjiLabel);
        back.add(czasAnimacjiText);
        back.add(emptyLabel);
        back.add(applyButton);


        ustFrame.setContentPane(back);

        ustFrame.setResizable(false);

        ustFrame.setPreferredSize(new Dimension(400, 150));
        ustFrame.setBounds(50, 50, 400, 150);

        ustFrame.pack();
        ustFrame.setVisible(true);


    }


    public class ApplyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            Main main = new Main();


            if (!wysokoscText.getText().isEmpty() && !czasText.getText().isEmpty() && !czasAnimacjiText.getText().isEmpty()) {


                int newH = Integer.parseInt(wysokoscText.getText());
                double newT = (double) (Integer.parseInt(czasText.getText())) / 1000;
                double newTAnim = (double) (Integer.parseInt(czasAnimacjiText.getText())) / 1000;

                if (newH < 500) {
                    Main.setH(newH);
                    Main.setT(newT);
                    Main.settAnim(newTAnim);


                    ustFrame.dispose();


                    main.buildMainGUI();
                }
            } else {
                ustFrame.dispose();
                Main.clickUstawienia();
                Main.disposeFrame();
            }
        }
    }
}

