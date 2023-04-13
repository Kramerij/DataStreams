import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileChooser {
    JFileChooser chooser = new JFileChooser();
    FilterBy filter = new FilterBy();
    Lines lined  = new Lines();
    String filterPhrase;
    Set<String> textAreaLines = new HashSet<String>();
    Set<String> filterLines = new HashSet<String>();
    File selectedFile;
    String rec = "";
    List<String> lines = new ArrayList<>();



    String[] phrases;

    public String setFilter(String filterPhrase){
        this.filterPhrase = filterPhrase;
        return filterPhrase;
    }
    JTextArea ogFile ;
    public void FileChooser() {

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                int line = 0;
                while (reader.ready()) {
                    rec = reader.readLine();
                    phrases = rec.toLowerCase().split(".");
                    lines.add(rec);
                    ogFile.append(rec +"\n");

                    line++;



                }
                reader.close();
                //file has been read

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    JTextArea filteredFile;

    public void filterFile(){
        lines.stream()
                .filter(str -> str.toLowerCase().contains(filterPhrase))
                .forEach(s -> filteredFile.append(s+ "\n"));

       /* for (String l: lines) {
            if (l.contains(filterPhrase) == true) {
                filterLines.add(l);
            }
        }
        System.out.println(filterLines);*/


    }

    public void textAreaOutput(){
        filteredFile.setText("");
    }

}
