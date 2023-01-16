package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import logic.ReactionTime;
import logic.Results;
import net.miginfocom.swing.MigLayout;

public class ReactionPanel extends javax.swing.JPanel implements ActionListener, MouseListener {

    private JButton returnButton;
    private JLabel testInfoLabel;
    private String testInfo = "Gdy kolor zmieni się z czerwonego na zielony, kliknij tak szybko jak potrafisz. Kliknij gdziekolwiek aby rozpocząć";
    private ReactionTime reactionTime;
    private boolean isTestStarted;
    private boolean isTimerStopped;
    private JPanel panel;
    private JPanel upPanel;
    private Results results;
    private Font font = new Font("Sans Serif", Font.BOLD, (int) (40));
    private Font font2 = new Font("Sans Serif", Font.BOLD, (int) (20));
    private JLabel title;

    public ReactionPanel(Results results) {

        this.results = results;

        this.setLayout(new MigLayout("fill"));
        this.setBackground(new Color(66, 63, 63));

        this.upPanel = new JPanel();
        this.upPanel.setBackground(new Color(66, 63, 63));
        this.upPanel.setLayout(new MigLayout("", // Layout Constraints
                "[]180[]", // Column constraints
                "[][]")); // Row constraints));
        this.add(upPanel, "height 25%,wrap");

        this.panel = new JPanel();
        this.panel.setLayout(new MigLayout());
        this.add(panel, "height 75%, grow");

        this.returnButton = new JButton("Powrót");
        this.returnButton.setFont(font);
        this.returnButton.setForeground(Color.white);
        this.returnButton.setBackground(new Color(111, 111, 114));
        this.returnButton.addActionListener(this);
        this.upPanel.add(this.returnButton, "push, top, left");

        this.title = new JLabel("Czas reakcji");
        this.title.setFont(font);
        this.title.setForeground(Color.white);
        this.upPanel.add(this.title, "skip 3");

        this.testInfoLabel = new JLabel(testInfo);
        this.testInfoLabel.setFont(font2);
        this.testInfoLabel.setForeground(Color.white);
        this.panel.add(this.testInfoLabel, "push, align center");
        this.panel.addMouseListener(this);
        this.panel.setBackground(new Color(82, 143, 205));
        this.isTestStarted = false;
        this.isTimerStopped = true;

        this.reactionTime = new ReactionTime();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.returnButton) {
            MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
            mf.returnFromReactionPanel();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!isTestStarted && isTimerStopped) {
            this.panel.setEnabled(false);
            this.panel.setBackground(new Color(155, 34, 45));
            this.testInfoLabel.setText("Czekaj");
            this.isTestStarted = true;
            this.isTimerStopped = false;
            this.validate();
            Task task = new Task();
            task.start();
//            this.reactionTime.setStart_time(System.nanoTime());
        }
        else if (isTimerStopped) {
            this.reactionTime.setEnd_time(System.nanoTime());

            this.isTestStarted = false;
            this.testInfoLabel.setText(String.valueOf(this.reactionTime.getReactionTime()));
            this.results.addReactionTimeResult(this.reactionTime.getReactionTime());
        }
//        else {
//            this.panel.setBackground(new Color(82, 143, 205));
//            this.testInfoLabel.setText("zbyt szybko");
//            this.isTestStarted = false;
//            this.isTimerStopped = true;
//            this.validate();
//            task.
//        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private class Task extends Thread {
        @Override
        public void run() {
            reactionTime.measureReactionTime();
            panel.setBackground(new Color(46, 155, 34));
            panel.setEnabled(true);
            reactionTime.setStart_time(System.nanoTime());
            isTimerStopped = true;
            validate();
            System.out.println("Skonczone");
            testInfoLabel.setText("Kliknij");
        }

    }
}
