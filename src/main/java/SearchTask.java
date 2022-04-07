import java.io.*;
import java.util.List;
import java.util.Scanner;

public class SearchTask implements Runnable {

    String fileName;
    String sourceFile;
    String targetDirectory;
    String charset;
    List<String> stringsToFind;

    @Override
    public void run() {
        try {
            PrintWriter outStream = new PrintWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(targetDirectory
                                    + fileName
                                    + "_resultData.txt"), charset));

            outStream.println("FILE_NAME = " + fileName + " in Progress..." + "SearchTask: " + this.toString());
            System.out.println("FILE_NAME = " + fileName + " in Progress..." + "SearchTask: " + this.toString());

            File fileSource = new File(sourceFile);
            Scanner inputStream = new Scanner(fileSource, charset);

            while (inputStream.hasNext()) {
                String lineData = inputStream.nextLine();

                for (String s : stringsToFind) {
                    if (lineData.contains(s)) {
                        outStream.println("FOUND = \"" + s + "\": " + lineData);
                        System.out.println("FILE = " + fileName + "; FOUND = \"" + s + "\": " + lineData);
                    }
                    outStream.flush();
                }
            }
            inputStream.close();
            outStream.close();

            outStream.println("SearchTask: " + this.toString() + " finished!");
            System.out.println("SearchTask: " + this.toString() + " finished!");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public SearchTask(String fileName, String sourceFile, String targetDirectory, String encoding, List<String> stringsToFind) {
        this.fileName = fileName;
        this.sourceFile = sourceFile + "\\" + fileName;
        this.targetDirectory = targetDirectory + "\\";
        this.charset = encoding;
        this.stringsToFind = stringsToFind;
    }
}
