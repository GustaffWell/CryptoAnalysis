package classes;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class StatisticalAnalysis {

    static char[] alphabetArray = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":;-!? ".toCharArray();

    static HashMap<Double, Character> sampleMap = new HashMap<>();
    static HashMap<Double, Character> encryptedMap = new HashMap<>();

    static List<Character> textSample = new ArrayList<>();
    static List<Character> textEncrypted = new ArrayList<>();

    public static void decryptStat(Path encryptedFile, Path sampleFile) {
        readFile(sampleFile, textSample);
        calculateStatistics(textSample, sampleMap);

        readFile(encryptedFile, textEncrypted);
        calculateStatistics(textEncrypted, encryptedMap);

        Set<Double> sampleKeys = sampleMap.keySet();
        Set<Double> encryptedKeys = encryptedMap.keySet();

        ArrayList<Double> sampleKeysSort = new ArrayList<>(sampleKeys);
        ArrayList<Double> encryptKeysSort = new ArrayList<>(encryptedKeys);
        Collections.sort(sampleKeysSort);
        Collections.reverse(sampleKeysSort);
        Collections.sort(encryptKeysSort);
        Collections.reverse(encryptKeysSort);

        for (int i = 0; i < textEncrypted.size(); i++) {
            for (int j = 0; j < encryptKeysSort.size(); j++) {
                if (textEncrypted.get(i).equals(encryptedMap.get(encryptKeysSort.get(j)))) {
                    textEncrypted.set(i, sampleMap.get(sampleKeysSort.get(j)));
                    break;
                }
            }
        }
        writeFile(encryptedFile);
    }

    public static void calculateStatistics (List<Character> list, HashMap<Double, Character> map) {
        double percent;
        for (int i = 0; i < alphabetArray.length; i++) {
            double count = 0;
            for (int j = 0; j < list.size(); j++) {
                if (alphabetArray[i] == list.get(j)) {
                    count ++;
                }
            }
            percent = (count / list.size()) * 100;
            map.put(percent, alphabetArray[i]);
        }
    }

    private static void readFile(Path path, List<Character> text) {
        try (InputStreamReader inputStreamReader = new InputStreamReader(Files.newInputStream(path), "UTF-8")) {
            while (inputStreamReader.ready()) {
                text.add((char) inputStreamReader.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(Path path) {
        String str = path.getParent().toString();
        String[] name = path.getFileName().toString().split("\\.");
        String newName = name[0] + "NewSA." + name[1];
        Path newPath = Path.of(str, newName);

        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(newPath), "UTF-8")) {
            for (Character character : textEncrypted) {
                outputStreamWriter.write(character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
