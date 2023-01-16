package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logic.NumberMemory;
import logic.Results;
import net.miginfocom.swing.MigLayout;

public class NumberMemoryPanel extends JPanel implements ActionListener {

    private final JPanel upPanel;
    private final JPanel downPanel;
    private JButton returnButton;
    private JLabel testInfoLabel;
    private String testInfo = "Pamięć Numerów";

    private  JButton submit;
    private JLabel number;
    private JTextField textInput;
    private NumberMemory numberMemory;
    private  JProgressBar timeBar;
    private Results results;
    private Font font = new Font("Sans Serif", Font.BOLD, (int) (40));
//    private  Task task;

    private boolean isTestStarted;

    public NumberMemoryPanel(Results results) {

        this.results = results;

        this.isTestStarted = false;
//        this.setBackground(new Color(155, 34, 45));

        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(82, 143, 205));
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
        this.upPanel.setBackground(new Color(66, 63, 63));
        this.upPanel.setLayout(new MigLayout("", // Layout Constraints
                "[]300[]", // Column constraints
                "[][]")); // Row constraints));

        this.add(upPanel,c);
        this.testInfoLabel = new JLabel(testInfo);
        this.testInfoLabel.setFont(font);
        this.testInfoLabel.setForeground(Color.white);
//        this.testInfoLabel.setAlignmentX(CENTER_ALIGNMENT);

        this.returnButton = new JButton("Powrót");
        this.returnButton.setFont(font);
        this.returnButton.setForeground(Color.white);
        this.returnButton.setBackground(new Color(111, 111, 114));
        this.returnButton.addActionListener(this);
//        this.returnButton.setAlignmentX(LEFT_ALIGNMENT);



        this.upPanel.add(returnButton);
        this.upPanel.add(testInfoLabel,"span, center");


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
        this.number = new JLabel("Jak długi numer jesteś w stanie zapamiętać?");
        this.number.setFont(font);
        this.number.setForeground(Color.white);
        this.number.setBackground(new Color(111, 111, 114));
        this.number.setVerticalAlignment((int) CENTER_ALIGNMENT);

        this.timeBar = new JProgressBar(JProgressBar.HORIZONTAL, 0 , 100);
        this.timeBar.setValue(0);
//        this.timeBar.setStringPainted(true);
        this.timeBar.setVisible(false);

        this.textInput = new JTextField();
        this.textInput.setVisible(false);

        this.submit = new JButton("Start");
        this.submit.setFont(font);
        this.submit.setForeground(Color.white);
        this.submit.setBackground(new Color(111, 111, 114));
        this.submit.setVerticalAlignment((int) CENTER_ALIGNMENT);
        this.submit.addActionListener(this);

        this.downPanel.add(number, "center , wrap 15");
        this.downPanel.add(timeBar,"growx, wrap 15");
        this.downPanel.add(textInput, "growx, wrap 15");
        this.downPanel.add(submit, "center");

        this.numberMemory = new NumberMemory();

    }

    private class Task extends Thread {

        @Override
        public void run() {
            for (int i = 0; i <=100; i++) {
                timeBar.setValue(i);
                try {
                    Thread.sleep(10* numberMemory.getLevel());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number.setVisible(false);
            timeBar.setVisible(false);
            submit.setText("Zatwierdź");
            submit.setVisible(true);
            textInput.setVisible(true);

        }
    }

    public void makeTest() {
        String currentNumber = "";
        if (!isTestStarted) {
            this.isTestStarted = !isTestStarted;
            currentNumber = String.valueOf(numberMemory.drawNewNumber());
            this.number.setText(currentNumber);
            this.number.setVisible(true);
            this.submit.setVisible(false);
            this.timeBar.setVisible(true);
            this.validate();

            Task task = new Task();
            task.start();

        } else {
//            String correctNumber = this.textInput.getText();
            this.isTestStarted = !isTestStarted;
            this.submit.setText("Start");
            this.textInput.setVisible(false);
            number.setVisible(true);
            boolean result = this.numberMemory.compareInput(this.textInput.getText());
            if (result) {
                this.numberMemory.nextLevel();
                this.number.setText("Ukończyłeś poziom " + String.valueOf(this.numberMemory.getLevel()-1) + ". Kliknij Start aby kontynuować");
                this.textInput.setText("");
            }
            else {
                this.submit.setVisible(false);
                this.textInput.setVisible(false);

                this.number.setText("Ukończyłeś poziom " + String.valueOf(this.numberMemory.getLevel()-1) + ". Podano zły numer");  //   + ".\n Numer wpisany:" + this.textInput.getText() + "\nNumer poprawny: " + currentNumber
                this.results.addNumberMemoryResult(this.numberMemory.getLevel()-1);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.returnButton) {
            MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
            mf.returnFromNumberMemoryPanel();
        }
        if (e.getSource() == this.submit) {
            this.makeTest();
        }
    }
}
