package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class DictionaryManagement {

    public void insertFromText(Dictionary List, String word,String type,String mean)   {
        Word newword = new Word(word, type, mean);
        List.words.add(newword);
    }

    public void insertFromFile(Dictionary List) throws FileNotFoundException {
        File filename = new File(
                "C:\\Users\\Administrator\\Desktop\\HK I-2021\\LTHDT\\Test\\src\\sample\\dictionaries.txt");
        Scanner sc = new Scanner(filename);
        while(sc.hasNextLine()){
            String currentLine = sc.nextLine();
            int vt1 = currentLine.indexOf("(");
            int vt2 = currentLine.indexOf(")");
            String word = currentLine.substring(0, vt1-1);
            String type = currentLine.substring(vt1,vt2+1);
            String meanings = currentLine.substring(vt2+2, currentLine.length());
            //System.out.println(word +"."+ type +"."+ meanings);
            Word newword = new Word(word, type, meanings);
            List.words.add(newword);
        }
        sc.close();
    }

    public Word get_word(Dictionary List, String a) {
        Word exception = new Word();
        exception.setWord_target("ERROR!!");
        exception.setWord_type("");
        exception.setWord_mean("");
        for(int i=0; i<List.words.size();i++){
            if(a.equalsIgnoreCase(List.words.get(i).getWord_target())){
                return List.words.get(i);
            }
        }
        return exception;
    }

    public int dictionaryRemove(Dictionary List, String remove_word) {

        for(int i=0;i<List.words.size();i++){
            if(remove_word.equalsIgnoreCase(List.words.get(i).getWord_target())){
                List.words.remove(i);
                return 1;
            }
        }
        return 0;
    }

    public void dictionaryFix(Word word_fix, String target, String type, String mean) {
        word_fix.setWord_target(target);
        word_fix.setWord_type(type);
        word_fix.setWord_mean(mean);
    }

    public void save_file(Dictionary List) throws FileNotFoundException, UnsupportedEncodingException {
        try{
           FileWriter fw = new FileWriter(
                "C:\\Users\\Administrator\\Desktop\\HK I-2021\\LTHDT\\Test\\src\\sample\\dictionaries.txt");
        //writer.print("");
           for(int i=0; i<List.words.size(); i++){
               fw.write(List.words.get(i).getWord_target() + " (" + List.words.get(i).getWord_means() + ") "
                    + List.words.get(i).getWord_means() + "\n");
           }
            fw.close();
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
