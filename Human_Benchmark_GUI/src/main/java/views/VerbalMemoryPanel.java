package views;

import logic.Results;
import logic.VerbalMemory;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerbalMemoryPanel extends JPanel implements ActionListener {

    private JPanel upPanel;
    private JPanel downPanel;

    private JButton returnButton;
    private JLabel testInfoLabel;
    private String testInfo = "Pamięć słów";

    private JLabel infoLabel;
    private JButton startButton;

    private JLabel scoreLabel;
    private JLabel livesLabel;
    private JLabel wordLabel;

    private  JButton seenButton;
    private JButton newButton;

    private VerbalMemory verbalMemory;
    private Results results;

    private Font font = new Font("Sans Serif", Font.BOLD, (int) (40));
    private Font font1 = new Font("Sans Serif", Font.BOLD, (int) (20));

    public VerbalMemoryPanel(Results results) {

        this.results = results;

        verbalMemory = new VerbalMemory();

        this.setBackground(new Color(82, 143, 205));

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0,0,0,0);

        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.2;
        c.weightx = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;


        this.upPanel = new JPanel();
        this.upPanel.setLayout(new MigLayout("", // Layout Constraints
                "[]290[]", // Column constraints
                "[][]")); // Row constraints));
        this.upPanel.setBackground(new Color(66, 63, 63));

        this.add(upPanel,c);

        this.returnButton = new JButton("Powrót");
        this.returnButton.setFont(font);
        this.returnButton.setForeground(Color.white);
        this.returnButton.setBackground(new Color(111, 111, 114));
        this.returnButton.addActionListener(this);
        this.testInfoLabel = new JLabel(testInfo);

        this.testInfoLabel = new JLabel(testInfo);
        this.testInfoLabel.setFont(font);
        this.testInfoLabel.setForeground(Color.white);

//        this.returnButton = new JButton("Powrót");
//        this.returnButton.addActionListener(this);

        this.upPanel.add(returnButton," top, left");
        this.upPanel.add(testInfoLabel," center");

        c.fill = GridBagConstraints.VERTICAL;
        c.weighty = 0.8;
        c.weightx = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;

        this.downPanel = new JPanel();
        this.downPanel.setBackground(new Color(82, 143, 205));
        this.downPanel.setLayout(new MigLayout());
        this.add(downPanel,c);

        this.infoLabel = new JLabel("<html>" + "Na ekranie wyświetlane będą słowa, po jednym na raz. Jeżeli widziałeś słowo w trakcie testu kilknij BYŁO." +  "<br>" + "         Jeżeli to nowe słowo to kliknij NOWE" + "<html>" );
        this.infoLabel.setFont(font1);
        this.infoLabel.setForeground(Color.white);
        this.startButton = new JButton("Start");
        this.startButton.setFont(font);
        this.startButton.setForeground(Color.white);
        this.startButton.setBackground(new Color(111, 111, 114));
        this.startButton.addActionListener(this);

        this.downPanel.add(infoLabel, "wrap");
        this.downPanel.add(startButton, "align center");
    }

    public void changeDownPanel() {
        this.downPanel.remove(infoLabel);
        this.downPanel.remove(startButton);

        this.livesLabel = new JLabel("lives");
        this.livesLabel.setFont(font);
        this.livesLabel.setForeground(Color.white);
        this.scoreLabel = new JLabel("score");
        this.scoreLabel.setFont(font);
        this.scoreLabel.setForeground(Color.white);
        this.wordLabel = new JLabel("word");
        this.wordLabel.setFont(font);
        this.wordLabel.setForeground(Color.white);

        this.newButton = new JButton("NOWE");
        this.newButton.setFont(font);
        this.newButton.setForeground(Color.white);
        this.newButton.setBackground(new Color(111, 111, 114));
        this.newButton.addActionListener(this);
        this.seenButton = new JButton("BYŁO");
        this.seenButton.setFont(font);
        this.seenButton.setForeground(Color.white);
        this.seenButton.setBackground(new Color(111, 111, 114));
        this.seenButton.addActionListener(this);

        this.downPanel.add(livesLabel, "align center, cell 0 0");
        this.downPanel.add(scoreLabel, "align center, cell 1 0, wrap");
        this.downPanel.add(wordLabel, "spanx,align center");
        this.downPanel.add(newButton, "cell 0 2, growx");
        this.downPanel.add(seenButton, "cell 1 2, growx");

        this.validate();

    }

    public void makeTest() {
        this.changeDownPanel();
        this.livesLabel.setText("Szanse:" + verbalMemory.getLives());
        this.scoreLabel.setText("Punkty: " + verbalMemory.getPoints());
        verbalMemory.setWord(verbalMemory.getNewWord());
        this.wordLabel.setText(verbalMemory.getWord());
    }

    public void repaintLabels(VerbalMemory verbalMemory) {
        verbalMemory.addSeen(verbalMemory.getWord());
        this.livesLabel.setText("Szanse:" + verbalMemory.getLives());
        this.scoreLabel.setText("Punkty: " + verbalMemory.getPoints());
        if (verbalMemory.getNewOrSeen() == 0) {
            verbalMemory.setWord(verbalMemory.getSeenWord());
            this.wordLabel.setText(verbalMemory.getWord());
        } else {
            verbalMemory.setWord(verbalMemory.getNewWord());
            this.wordLabel.setText(verbalMemory.getWord());
        }
    }

    public void endGame() {
        this.downPanel.remove(livesLabel);
        this.downPanel.remove(scoreLabel);
        this.downPanel.remove(wordLabel);
        this.downPanel.remove(newButton);
        this.downPanel.remove(seenButton);

        this.infoLabel = new JLabel("Gra zakończona zdobyto " + this.verbalMemory.getPoints() + "pkt.");
        this.downPanel.add(infoLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.returnButton) {
            MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
            mf.returnFromVerbalMemoryPanel();
        }
        if (e.getSource() == this.startButton) {
            makeTest();
        }
        if (e.getSource() == this.seenButton) {
            if (this.verbalMemory.isWordInSeen(verbalMemory.getWord())) {
                this.verbalMemory.addPoints();
            } else {
                this.verbalMemory.loseLive();
                if (this.verbalMemory.getLives() == -1) {
                    this.endGame();
                    this.results.addVerbalMemoryResult(this.verbalMemory.getPoints());
                }
            }
            repaintLabels(this.verbalMemory);
        }
        if (e.getSource() == this.newButton) {
            if (this.verbalMemory.isWordInSeen(verbalMemory.getWord())) {
                this.verbalMemory.loseLive();
                if (this.verbalMemory.getLives() == -1) {
                    this.endGame();
                    this.results.addVerbalMemoryResult(this.verbalMemory.getPoints());
                }
            } else {
                this.verbalMemory.addPoints();
            }
            repaintLabels(this.verbalMemory);
        }
        validate();
    }
}
