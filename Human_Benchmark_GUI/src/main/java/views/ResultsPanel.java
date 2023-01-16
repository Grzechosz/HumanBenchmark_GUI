package views;

import logic.ReactionTime;
import logic.Results;
import net.miginfocom.swing.MigLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultsPanel extends JPanel implements ActionListener {

    private JButton returnButton;
    private JLabel testInfoLabel;
    private String testInfo = "Gdy kolor zmieni się z czerwonego na zielony, kliknij tak szybko jak potrafisz.\nKliknij gdziekolwiek aby rozpocząć";

    private JPanel panel;
    private JPanel upPanel;
    private Results results;
    private Font font = new Font("Sans Serif", Font.BOLD, (int) (40));

    public ResultsPanel(Results results) {

        this.results = results;

        this.setLayout(new MigLayout("fill"));
        this.setBackground(new Color(66, 63, 63));

        this.upPanel = new JPanel();
        this.upPanel.setBackground(new Color(66, 63, 63));
        this.upPanel.setLayout(new MigLayout());
        this.add(upPanel, "wrap");

        this.panel = new JPanel();
        this.panel.setLayout(new MigLayout());
        this.panel.setBackground(new Color(82, 143, 205));
        this.add(panel, "height 190%, grow");

        this.returnButton = new JButton("Powrót");
        this.returnButton.setFont(font);
        this.returnButton.setForeground(Color.white);
        this.returnButton.setBackground(new Color(111, 111, 114));
        this.returnButton.addActionListener(this);
        this.upPanel.add(this.returnButton, "push, top, left");


        CategoryDataset reactionDataset = createReactionDataset(this.results.getReactionTimeResults());
        JFreeChart reactionChart = createReactionChart(reactionDataset);
        ChartPanel reactionChartPanel = new ChartPanel(reactionChart);
        reactionChartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        reactionChartPanel.setBackground(Color.white);
        this.panel.add(reactionChartPanel);

        CategoryDataset verbalDataset = createVerbalDataset(this.results.getVerbalMemoryResults());
        JFreeChart verbalChart = createVerbalChart(verbalDataset);
        ChartPanel verbalChartPanel = new ChartPanel(verbalChart);
        verbalChartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        verbalChartPanel.setBackground(Color.white);
        this.panel.add(verbalChartPanel, "wrap");

        CategoryDataset typingDataset = createTypingDataset(this.results.getTypingResults());
        JFreeChart typingChart = createTypingChart(typingDataset);
        ChartPanel typingChartPanel = new ChartPanel(typingChart);
        typingChartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        typingChartPanel.setBackground(Color.white);
        this.panel.add(typingChartPanel);

        CategoryDataset numberDataset = createNumberDataset(this.results.getNumberMemoryResults());
        JFreeChart numberChart = createNumberChart(numberDataset);
        ChartPanel numberChartPanel = new ChartPanel(numberChart);
        numberChartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        numberChartPanel.setBackground(Color.white);
        this.panel.add(numberChartPanel);


        printReactionResults();
        printVerbalMemoryResults();
    }

    private JFreeChart createNumberChart(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "Wyniki pomiaru pamięci numerów",
                "",
                "Liczba zapamiętanych cyfr",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }

    private CategoryDataset createNumberDataset(ArrayList<Integer> resultsList) {

        var dataset = new DefaultCategoryDataset();

        for (int result: resultsList
        ) {
            dataset.setValue(result,"wynik", "Proba " + resultsList.indexOf(result) + ".");
        }
        return dataset;
    }

    private JFreeChart createTypingChart(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "Wyniki pomiaru prędkości pisania",
                "",
                "Ilość słów na minutę",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }

    private CategoryDataset createTypingDataset(ArrayList<Integer> resultsList) {

        var dataset = new DefaultCategoryDataset();

        for (int result: resultsList
        ) {
            dataset.setValue(result,"wynik", "Proba " + resultsList.indexOf(result) + ".");
        }
        return dataset;
    }

    private JFreeChart createVerbalChart(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "Wyniki pomiaru pamięci słów",
                "",
                "Ilość zapamiętanych słów",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }

    private CategoryDataset createVerbalDataset(ArrayList<Integer> resultsList) {

        var dataset = new DefaultCategoryDataset();

        for (int result: resultsList
        ) {
            dataset.setValue(result,"wynik", "Proba " + resultsList.indexOf(result) + ".");
        }
        return dataset;
    }

    private JFreeChart createReactionChart(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "Wyniki pomiaru refleksu",
                "",
                "Czas reakcji [ms]",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }

    private CategoryDataset createReactionDataset(ArrayList<Integer> resultsList) {

        var dataset = new DefaultCategoryDataset();

        for (int result: resultsList
             ) {
            dataset.setValue(result,"wynik", "Proba " + resultsList.indexOf(result) + ".");
        }
        return dataset;
    }

    public void printReactionResults() {
        for (int timeResult: this.results.getReactionTimeResults()
             ) {
            System.out.println(timeResult);
        }
    }

    public void printVerbalMemoryResults() {
        for (int pointsResult: this.results.getVerbalMemoryResults()
        ) {
            System.out.println(pointsResult);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.returnButton) {
            MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
            mf.returnFromResultsPanel();
        }
    }
}
