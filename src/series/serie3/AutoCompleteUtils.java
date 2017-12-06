package series.serie3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AutoCompleteUtils {

    public static TNode loadWordsFromFile(TNode root, String fileName){
        Scanner in;
        try {
            in = new Scanner(new File(fileName));

        while (in.hasNext()){
            loadWord(root,in.next());
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return  root;
    }

    private static void loadWord(TNode root, String next) {
        for (int i = 0; i < next.length(); i++) {
            char c = next.charAt(i);
            if(c<'a' || c>'z') return;

            if (root.children[c-'a'] == null) {
                root.children[c-'a']=new TNode();
            }
            root=root.children[c-'a'];
        }
        root.isWord=true;
    }

    public static TNode longestWithPrefix(TNode root, String prefix){
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(c<'a' || c>'z') return null;
            root=root.children[c-'a'];
            if (root == null) return null;
        }
        return root ;
    }

    public static int countPossibleWords(TNode root, String prefix){
        root = longestWithPrefix(root,prefix);
        return count(root);

    }

    private static int count(TNode root) {
        int c = 0;
        if(root==null) return 0;
        if(root.isWord)c++;
        for (TNode ch:
             root.children) {
            c+=count(ch);
        }
        return c    ;
    }


}
