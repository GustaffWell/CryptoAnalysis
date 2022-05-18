package classes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CeasarCipher {

    static char[] alphabetArray = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":;-!? ".toCharArray();
    static List<Character> text = new ArrayList<>();

    public static void encrypt(Path path, int shift) {
        readFile(path);
        for (int i = 0; i < text.size(); i++) {
            for (int p = 0; p < alphabetArray.length; p++) {
                if (text.get(i) == alphabetArray[p]) {
                    if ((p + shift) >= alphabetArray.length) {
                        text.set(i, alphabetArray[p + shift - alphabetArray.length]);
                    } else {
                        text.set(i, alphabetArray[p + shift]);
                    }
                    break;
                }
            }
        }
        writeEncryptedFile(path);
        text.clear();
    }

    public static void decrypt(Path path, int shift) {
        readFile(path);
        for (int i = 0; i < text.size(); i++) {
            for (int p = 0; p < alphabetArray.length; p++) {
                if (text.get(i) == alphabetArray[p]) {
                    if ((p - shift) < 0) {
                        text.set(i, alphabetArray[alphabetArray.length - (shift - p)]);
                    } else {
                        text.set(i, alphabetArray[p - shift]);
                    }
                    break;
                }
            }
        }
        writeDecryptedFile(path);
        text.clear();
    }

    public static void brutForce(Path path) {
        readFile(path);
        List<Character> copyText;
        for (int s = 0; s < alphabetArray.length; s++) {
            copyText = new ArrayList<>(text);
            for (int i = 0; i < copyText.size(); i++) {
                for (int p = 0; p < alphabetArray.length; p++) {
                    if (copyText.get(i) == alphabetArray[p]) {
                        if ((p - s) < 0) {
                            copyText.set(i, alphabetArray[alphabetArray.length - (s - p)]);
                        } else {
                            copyText.set(i, alphabetArray[p - s]);
                        }
                        break;
                    }
                }
            }
            char[] result = new char[copyText.size()];
            int i = 0;
            for (Character character : copyText) {
                result[i] = character;
                i++;
            }
            String str = new String(result);
            if (str.contains(". ") && str.contains(", ") && !str.contains("  ")) {
                text = copyText;
                break;
            }
            copyText.clear();
        }
        writeDecryptedFile(path);
        text.clear();
    }

    private static void readFile(Path path) {
        try (InputStreamReader inputStreamReader = new InputStreamReader(Files.newInputStream(path), "UTF-8")) {
            while (inputStreamReader.ready()) {
                text.add((char) inputStreamReader.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeEncryptedFile(Path path) {
        String str = path.getParent().toString();
        String[] name = path.getFileName().toString().split("\\.");
        String newName = name[0] + "Encrypted." + name[1];
        Path newPath = Path.of(str, newName);

        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(newPath), "UTF-8")) {
            for (Character character : text) {
                outputStreamWriter.write(character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeDecryptedFile(Path path) {
        String str = path.getParent().toString();
        String[] name = path.getFileName().toString().split("\\.");
        String newName = name[0] + "Decrypted." + name[1];
        Path newPath = Path.of(str, newName);

        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(newPath), "UTF-8")) {
            for (Character character : text) {
                outputStreamWriter.write(character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
