package views;

import logic.Results;
import logic.Typing;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class TypingPanel extends JPanel implements ActionListener, KeyListener {

    private JButton returnButton;
    private JLabel testInfoLabel;
    private String testInfo = "Pisanie";

    private JPanel panel;
    private JPanel upPanel;
    private Typing typing;
    private JLabel text;
    private JPanel textPanel;

    private JButton startButton;
    private int currentFocus = 0;
    private int textSize;
    private JLabel title;


    private Font font = new Font("Monospaced", Font.BOLD, (int) (20));
    private Font font2 = new Font("Sans Serif", Font.BOLD, (int) (40));

    public TypingPanel(Results results) {

        this.typing = new Typing();

        this.setLayout(new MigLayout("fill"));
        this.setBackground(new Color(66, 63, 63));

        this.upPanel = new JPanel();
        this.upPanel.setLayout(new MigLayout("", // Layout Constraints
                "[]170[]", // Column constraints
                "[][]")); // Row constraints));));
        this.upPanel.setBackground(new Color(66, 63, 63));
        this.add(upPanel, "height 25%,wrap");

        this.panel = new JPanel();
        this.panel.setLayout(new MigLayout());
        this.panel.setBackground(new Color(82, 143, 205));
        this.add(panel, "height 75%, grow");

        this.returnButton = new JButton("Powrót");
        this.returnButton.setFont(font2);
        this.returnButton.setForeground(Color.white);
        this.returnButton.setBackground(new Color(111, 111, 114));
        this.returnButton.addActionListener(this);
        this.testInfoLabel = new JLabel(testInfo);

        String resultText = this.makeText();



        this.upPanel.add(returnButton,"push, top, left");

        this.title = new JLabel("Prędkość pisania");
        this.title.setFont(font2);
        this.title.setForeground(Color.white);
        this.upPanel.add(this.title, "skip 3");
//        this.panel.add(testInfoLabel, "push, align center, wrap");

        this.text = new JLabel(resultText);

        this.textPanel = new JPanel();
        this.textPanel.setLayout(new MigLayout("gap 0!"));
        this.textPanel.setBackground(new Color(82, 143, 205));

        this.panel.add(textPanel,"push, align center, wrap");

        this.addText();

        this.startButton = new JButton("Start");
        this.startButton.setFont(font2);
        this.startButton.setForeground(Color.white);
        this.startButton.setBackground(new Color(111, 111, 114));

        this.panel.add(startButton, "push, align center");
        this.startButton.addActionListener(this);

        textSize = this.typing.getText().size();


    }

    public void addText() {
        for (String line: this.typing.getText()
             ) {
            for (int i = 0; i < line.length(); i++) {
                JTextField character = new JTextField();
                character.setText(String.valueOf(line.charAt(i)));
                character.setFont(font);
                character.setBackground(new Color(82, 143, 205));
                character.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                character.addKeyListener(this);
                if (i == line.length()-1) {
                    this.textPanel.add(character, "grow, wrap");
                } else {
                    this.textPanel.add(character, "grow, gapx 0");
                }
            }
        }
    }

    public String makeText() {
//        "<html>" + "  Dodaj" + "<br>" + "kategorię" + "</html>"
        String result = "<html>";
        for (String line: this.typing.getText()
             ) {
            result = result.concat(line + "<br>");
        }
        result = result.concat("</html>");
        return result;
    }

    public void setFocusForComponent(){
        Component[] components = this.panel.getComponents();
        components[0].requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.returnButton) {
            MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
            mf.returnFromTypingPanel();
        }
        if (e.getSource() == this.startButton) {
            Component[] components = this.textPanel.getComponents();
            components[0].requestFocusInWindow();
            this.startButton.setVisible(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.KEY_TYPED) {
            if (this.currentFocus<this.textSize) {
                currentFocus++;
                Component[] components = this.textPanel.getComponents();
                components[currentFocus].requestFocusInWindow();
            }

//            this.startButton.setVisible(false);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
