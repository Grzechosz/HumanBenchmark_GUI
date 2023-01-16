package views;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel implements ActionListener {

    private JButton returnButton;
    private JLabel testInfoLabel;
    private String testInfo = "Ustawienia";

    private JPanel panel;
    private JPanel upPanel;
    private Font font = new Font("Sans Serif", Font.BOLD, (int) (40));

    private JButton resetButton;
    private JLabel resetLabel;
    private JLabel title;

    public SettingsPanel() {

        this.setLayout(new MigLayout("fill"));
        this.setBackground(new Color(66, 63, 63));

        this.upPanel = new JPanel();
        this.upPanel.setBackground(new Color(66, 63, 63));
        this.upPanel.setLayout(new MigLayout("", // Layout Constraints
                "[]180[]", // Column constraints
                "[][]")); // Row constraints));
        this.add(upPanel, "wrap");

        this.panel = new JPanel();
        this.panel.setBackground(new Color(82, 143, 205));
        this.panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.add(panel, "height 190%, grow");

        this.returnButton = new JButton("Powrót");
        this.returnButton.addActionListener(this);
        this.returnButton.setFont(font);
        this.returnButton.setForeground(Color.white);
        this.returnButton.setBackground(new Color(111, 111, 114));
        this.upPanel.add(this.returnButton, "push, top, left");

        this.title = new JLabel("Ustawienia");
        this.title.setFont(font);
        this.title.setForeground(Color.white);
        this.upPanel.add(this.title, "skip 3");

        this.resetButton = new JButton("Reset");
        this.resetButton.setFont(font);
        this.resetButton.setAlignmentX(CENTER_ALIGNMENT);
        this.resetButton.setForeground(Color.white);
        this.resetButton.setBackground(new Color(111, 111, 114));
        this.resetButton.addActionListener(this);

        this.resetLabel = new JLabel("Usuń wyniki pomiarów");
        this.resetLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.resetLabel.setFont(font);
        this.resetLabel.setForeground(Color.white);
        this.resetLabel.setBackground(new Color(111, 111, 114));

        this.panel.add(resetLabel);
        this.panel.add(resetButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.returnButton) {
            MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
            mf.returnFromSettingsPanel();
        }
        if (e.getSource() == this.resetButton) {
            Object[] options = {"Tak, usuń wyniki",
                    "Nie"};
            int n = JOptionPane.showOptionDialog(null,
                    "Czy na pewno chcesz usunąć wyniki?",
                    "",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);

            if (n == 0) {
                MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
                mf.resetResults();
            }
            if (n == 1) {
            }
        }
    }
}
