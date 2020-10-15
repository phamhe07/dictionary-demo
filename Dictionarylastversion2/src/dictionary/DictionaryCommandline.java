package dictionary;

import java.util.Scanner;

public class DictionaryCommandline extends Dictionary {
    public void showAllWord() {
        DictionaryManagement DictionaryManagement = new DictionaryManagement();
        Dictionary = DictionaryManagement.insertFromFile();
        for (int i = 0; i < Dictionary.size(); i++) {
            System.out.println("No " + i + "\t" + Dictionary.get(i).getinfor());
        }
    }

    public void dictionaryBasic() {
        DictionaryManagement DictionManagement = new DictionaryManagement();
        DictionManagement.insertFromCommandline();
        showAllWord();
    }

    public void dictionaryAdvanced() {
        DictionaryManagement DictionManagement = new DictionaryManagement();
        DictionManagement.insertFromFile();
        showAllWord();
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        DictionManagement.dictionaryLookup(s,Dictionary);
    }
}
