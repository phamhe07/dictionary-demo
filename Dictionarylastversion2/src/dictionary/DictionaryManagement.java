package dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;


public class DictionaryManagement extends Dictionary {

    /**
     * read n word into arraylist;
     */

    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so luong tu vung:");
        n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String word_target = sc.nextLine();
            String word_explain = sc.nextLine();
            Word word = new Word(word_target, word_explain);
            Dictionary.add(word);

        }

    }

    /**
     * read list from file.word target and word_explain separate by tab.
     *
     * @return Dictionary
     */
    public List<Word> insertFromFile() {

        try {
            BufferedReader bufferedReader
                    = new BufferedReader(
                    new FileReader("D:\\java.java\\Dictionarylastversion2\\src\\dictionary\\dictionaries.txt"));
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                String[] text = line.split("\\t");

                Word word = new Word(text[1], text[2]);
                Dictionary.add(word);
            }
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Dictionary;
    }


    public String dictionaryLookup(String word, List<Word> Dictionary) {
        String s = "";
        for (int i = 0; i < Dictionary.size(); i++)
            if (Dictionary.get(i).getWord_target().equals(word)) {
                s = Dictionary.get(i).getinfor();
            }
        return s;
    }

    public List<Word> currentDictionary(String word, List<Word> dictionary) {
        List<Word> current = new ArrayList<>();
        for (int i = 0; i < Dictionary.size(); i++) {
            if (Dictionary.get(i).getWord_target().startsWith(word)) {
                current.add(Dictionary.get(i));
            }
        }
        return current;
    }
    public void delete(String Word_target, List<Word> dictionary) {
        for (int i = 0; i < dictionary.size(); i++)
            if (dictionary.get(i).getWord_target() == Word_target) {
                String Word_explain = dictionary.get(i).getWord_explain();
                Word word = new Word(Word_target, Word_explain);
                dictionary.remove(word);
            }
    }
}