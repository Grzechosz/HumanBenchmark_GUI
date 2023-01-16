package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends javax.swing.JPanel implements ActionListener {
    private JButton reactionButton;
    private JButton numberMemoryButton;
    private JButton wordsMemoryButton;
    private JButton typingButton;
    private JButton resultButton;
    private JButton settingsButton;
    private JLabel title;
    private JPanel titlePanel;
    private JPanel buttonsPanel;

    private Font font = new Font("Sans Serif", Font.BOLD, (int) (40));

    public MenuPanel() {
        this.setBackground(new Color(66, 63, 63));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);

        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.1;
        c.weightx = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;


        this.titlePanel = new JPanel();
        this.titlePanel.setBackground(new Color(66, 63, 63));
        this.add(titlePanel,c);
        this.title = new JLabel("HumanBenchmark");
        this.title.setFont(font);
        this.title.setForeground(Color.white);
        this.title.setAlignmentX(CENTER_ALIGNMENT);
//        this.title.setAlignmentY(CENTER_ALIGNMENT);


        this.titlePanel.add(title);

        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.9;
        c.weightx = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;


        this.buttonsPanel = new JPanel();
        this.buttonsPanel.setBackground(new Color(82, 143, 205));
        this.buttonsPanel.setLayout(new GridLayout(2,3));
        this.add(buttonsPanel,c);

        this.reactionButton = new JButton("Refleks");
        this.reactionButton.setFont(font);
        this.reactionButton.setForeground(Color.white);
        this.reactionButton.setBackground(new Color(111, 111, 114));
        this.reactionButton.addActionListener(this);
        this.reactionButton.setAlignmentX(CENTER_ALIGNMENT);

        this.numberMemoryButton = new JButton("Numery");
        this.numberMemoryButton.setFont(font);
        this.numberMemoryButton.setForeground(Color.white);
        this.numberMemoryButton.setBackground(new Color(111, 111, 114));
        this.numberMemoryButton.setAlignmentX(CENTER_ALIGNMENT);
        this.numberMemoryButton.addActionListener(this);

        this.wordsMemoryButton = new JButton("Słowa");
        this.wordsMemoryButton.setFont(font);
        this.wordsMemoryButton.setForeground(Color.white);
        this.wordsMemoryButton.setBackground(new Color(111, 111, 114));
        this.wordsMemoryButton.setAlignmentX(CENTER_ALIGNMENT);
        this.wordsMemoryButton.addActionListener(this);

        this.typingButton = new JButton("Pisanie");
        this.typingButton.setFont(font);
        this.typingButton.setForeground(Color.white);
        this.typingButton.setBackground(new Color(111, 111, 114));
        this.typingButton.setAlignmentX(CENTER_ALIGNMENT);
        this.typingButton.addActionListener(this);

        this.resultButton = new JButton("Wyniki");
        this.resultButton.setFont(font);
        this.resultButton.setForeground(Color.white);
        this.resultButton.setBackground(new Color(111, 111, 114));
        this.resultButton.setAlignmentX(CENTER_ALIGNMENT);
        this.resultButton.addActionListener(this);

        this.settingsButton = new JButton("Ustawienia");
        this.settingsButton.setFont(font);
        this.settingsButton.setForeground(Color.white);
        this.settingsButton.setBackground(new Color(111, 111, 114));
        this.settingsButton.setAlignmentX(CENTER_ALIGNMENT);
        this.settingsButton.addActionListener(this);

        this.buttonsPanel.add(reactionButton);
        this.buttonsPanel.add(numberMemoryButton);
        this.buttonsPanel.add(wordsMemoryButton);
        this.buttonsPanel.add(typingButton);
        this.buttonsPanel.add(resultButton);
        this.buttonsPanel.add(settingsButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.reactionButton) {
            MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
            mf.showReactionPanel();
        }
        if (e.getSource() == this.numberMemoryButton) {
            MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
            mf.showNumberMemoryPanel();
        }
        if (e.getSource() == this.wordsMemoryButton) {
            MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
            mf.showVerbalMemoryPanel();
        }
        if (e.getSource() == this.typingButton) {
            MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
            mf.showTypingPanel();
        }
        if (e.getSource() == this.resultButton) {
            MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
            mf.showResultsPanel();
        }
        if (e.getSource() == this.settingsButton) {
            MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
            mf.showSettingsPanel();
        }
    }

}
