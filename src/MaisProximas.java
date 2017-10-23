import series.serie1.Arrays;

import java.io.*;
import java.util.Scanner;

public class MaisProximas {

    private static String closest [] ;
    private static int closestNletters[];
    private static  String word;
    private static String outputFile ;
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        closest=new String[k];
        closestNletters=new int[k];
        word=args[1];
        outputFile=args[2];
        for (int i = 3 ; i<args.length;++i){
            getClosest(args[i]);
        }
        printClosest();

    }

    private static void printClosest() {
        try {
            PrintWriter in = new PrintWriter(outputFile);
            for (String out:
                 closest) {
                in.println(out);
            }
            in.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void getClosest(String inputFile) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            while (true){
                String aux = in.readLine();
                if (aux==null)break;
                greaterPrefix(aux);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void greaterPrefix(String next) {
        int aux = countCommonLetters(next,word);
        for (int i = 0; i < closest.length; i++) {
            if(closest[i]==null){
                closest[i]=next;
                closestNletters[i]=aux;
                break;
            }
            if(isEqual(closest[i],next)){
                break;
            }
            if( aux> closestNletters[i]||(aux==closestNletters[i] && next.length()>closest[i].length())){
                closest[i]=next;
                closestNletters[i]=aux;
                break;
            }
        }

    }

    private static boolean isEqual(String s, String next) {
        for (int i = 0; i < s.length()&& i <next.length(); i++) {
            if(s.charAt(i)!=next.charAt(i))
                return false;
        }
        return s.length()==  next.length()? true : false ;
    }

    private static int countCommonLetters(String next, String word) {
        int i = 0;
        for (; ((i < word.length()) && (i< next.length())) && (next.charAt(i)==word.charAt(i)); i++) {

        }
        return i;
    }
}
