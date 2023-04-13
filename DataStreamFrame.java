import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class DataStreamFrame extends JFrame {
    FileChooser fileChooser = new FileChooser();
    FilterBy filter = new FilterBy();
    String filterString;
    JPanel mainPnl, buttonPnl, textPnl;
    JButton pickFileButton, searchButton, quitButton;

    public DataStreamFrame(){
        mainPnl = new JPanel(new BorderLayout());
        createButtonPnl();
        createTextPnl();
        add(mainPnl);
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        mainPnl.add(buttonPnl, BorderLayout.NORTH); mainPnl.add(textPnl, BorderLayout.CENTER);
    }
    JTextField searchPhrase;

    public void createButtonPnl() {
        buttonPnl = new JPanel(new GridLayout(2,3));
        pickFileButton = new JButton("pick file");
        quitButton = new JButton("quit");
        searchButton = new JButton("search for phrase");
        searchPhrase = new JTextField();
        searchPhrase.setEditable(true);
        pickFileButton.addActionListener((ActionEvent ae) -> { //pick a file
            fileChooser.FileChooser();
            //filteredFile.append(String.valueOf(fileChooser.filterLines));
        });
        searchButton.addActionListener((ActionEvent ae) -> { //search file
            fileChooser.textAreaOutput();
            filterString = searchPhrase.getText();
            fileChooser.setFilter(filterString);
            fileChooser.textAreaOutput();
            fileChooser.filterFile();
        });
        buttonPnl.add(pickFileButton); buttonPnl.add(quitButton); buttonPnl.add(searchButton);
        buttonPnl.add(searchPhrase);
        quitButton.addActionListener((ActionEvent ae) -> { //quit
            System.exit(0);
        });
    }

    public void createTextPnl() {
        textPnl = new JPanel(new GridLayout(1, 2));
        fileChooser.ogFile = new JTextArea(10, 20);
        fileChooser.filteredFile = new JTextArea(10, 20);
        fileChooser.ogFile.setBorder(new LineBorder(Color.BLACK, 4, false));
        fileChooser.filteredFile.setBorder(new LineBorder(Color.BLACK, 4, false));
        textPnl.add(fileChooser.filteredFile); textPnl.add(fileChooser.ogFile);
    }

}
