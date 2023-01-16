package views;

import logic.NumberMemory;
import logic.Results;
import logic.VerbalMemory;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

import static java.lang.System.exit;

public class MainFrame extends javax.swing.JFrame {

    private JPanel[] panels;
    private Results results;

    public MainFrame() {

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
                                    public void windowClosing(WindowEvent e) {
                                    onExit();
                                    }
                                });

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("objects.bin"))) {
            results = (Results) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            results = new Results();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
            results = new Results();
        }

        this.setTitle("Human Benchmark");
        this.panels = new JPanel[7];
    }

//    private InputStream getFileFromResourceAsStream(String fileName) {
//
//        // The class loader that loaded the class
//        ClassLoader classLoader = getClass().getClassLoader();
//        InputStream inputStream = classLoader.getResourceAsStream(fileName);
//
//        // the stream holding the file content
//        if (inputStream == null) {
//            throw new IllegalArgumentException("file not found! " + fileName);
//        } else {
//            return inputStream;
//        }
//
//    }


    public void showReactionPanel() {
        this.panels[1] = new ReactionPanel(results);
        this.add(this.panels[1]);
        this.panels[0].setVisible(false);
        this.panels[1].setVisible(true);
    }

    public void returnFromReactionPanel() {
        this.remove(this.panels[1]);
        this.panels[0].setVisible(true);
    }

    public void showNumberMemoryPanel() {
        this.panels[2] = new NumberMemoryPanel(results);
        this.add(this.panels[2]);
        this.panels[0].setVisible(false);
        this.panels[2].setVisible(true);
    }

    public void returnFromNumberMemoryPanel() {
        this.remove(this.panels[2]);
        this.panels[0].setVisible(true);
    }

    public void showVerbalMemoryPanel() {
        this.panels[3] = new VerbalMemoryPanel(results);
        this.add(this.panels[3]);
        this.panels[0].setVisible(false);
        this.panels[3].setVisible(true);
    }

    public void returnFromVerbalMemoryPanel() {
        this.remove(this.panels[3]);
        this.panels[0].setVisible(true);
    }

    public void showTypingPanel() {
        this.panels[4] = new TypingPanel(results);
//        this.panels[4].setF
        this.add(this.panels[4]);
        this.panels[0].setVisible(false);
        this.panels[4].setVisible(true);
    }

    public void returnFromTypingPanel() {
        this.remove(this.panels[4]);
        this.panels[0].setVisible(true);
    }

    public void showResultsPanel() {
        this.panels[5] = new ResultsPanel(results);
        this.add(this.panels[5]);
        this.panels[0].setVisible(false);
        this.panels[5].setVisible(true);
    }

    public void returnFromResultsPanel() {
        this.remove(this.panels[5]);
        this.panels[0].setVisible(true);
    }

    public void showSettingsPanel() {
        this.panels[6] = new SettingsPanel();
        this.add(this.panels[6]);
        this.panels[0].setVisible(false);
        this.panels[6].setVisible(true);
    }

    public void returnFromSettingsPanel() {
        this.remove(this.panels[6]);
        this.panels[0].setVisible(true);
    }

    public void onExit() {
        serializeResults();
        System.err.println("Exit");
        System.exit(0);
    }

    public  void serializeResults() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("objects.bin"))) {
            outputStream.writeObject(results);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetResults() {
        this.results = new Results();
    }

//    public void loadPanels() {
//        this.panels[0] = new MenuPanel();
//        this.panels[1] = new ReactionPanel();
//
//        this.add(this.panels[1]);
//        this.add(this.panels[0]);
//
//
//        this.panels[1].setVisible(false);
//        this.panels[0].setVisible(true);
//    }

    public static void main(String args[]) {
        try {

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainFrame mainframe = new MainFrame();
                mainframe.setVisible(true);

                mainframe.panels[0] = new MenuPanel();
                mainframe.add(mainframe.panels[0]);
                mainframe.panels[0].setVisible(true);

                mainframe.setExtendedState(mainframe.getExtendedState() | JFrame.MAXIMIZED_BOTH);   // Fullscreen
            }
        });
    }
}
