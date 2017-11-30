package series;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import series.serie2.HashMap;
import java.util.*;

public class RankBySimikarities {

    private static HashSet<String> stopWords=new HashSet<>();
    private static HashMap<String,Integer>query = new HashMap<>();
    private static HashMap<String,Integer> words = new HashMap<>();
    private static int queryCount = 0;
    public static void main(String[] args) {
        addStopOrQueryWords(args[0],stopWords);
        addWords(args);
        Scanner in =new Scanner(System.in);
        String g;
        do {
            queryCount=0;
            query.clear();
            g = in.nextLine();
            if(g.startsWith("ranking")){
                addQuery(g.replace("ranking ",""));
                for (int i = 1; i <args.length ; i++) {
                    ranking(args[i]);
                }
            }
        }while (!g.equals("exit"));


    }

    private static void addQuery(String arg) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(arg));
            String aux = in.readLine();
            while (aux!=null){
                String[] a1 = aux.split(" ");
                for (String word:
                        a1) {
                    if(stopWords.contains(word))
                        continue;
                    if(query.containsKey(word)){
                        int c1 = query.get(word);
                        queryCount-=c1*c1++;
                        queryCount+=c1*c1;
                        query.put(word,c1);
                        continue;
                    }
                    queryCount++;
                    query.put(word,1);

                }
                aux=in.readLine();

            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private static void ranking(String arg) {
        Iterator<String> it =query.keySet().iterator();
        int c1 = 0 ;
        while (it.hasNext()){
            String aux = it.next();
            if(words.containsKey(arg+aux)){
                c1+=words.get(arg+aux);
            }
        }
        System.out.println(arg+" = "+(c1/(Math.sqrt(queryCount)*Math.sqrt(words.get(arg)))));



    }

    private static void addWords(String[] args) {
        for (int i = 1; i<args.length;i++) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(args[i]));
                String aux = in.readLine();
                int c = 0;
                while (aux!=null){
                    String[] a1 = aux.split(" ");
                    for (String word:
                            a1) {
                        if(stopWords.contains(word))
                            continue;
                        if (words.containsKey(args[i]+word)){
                            int c1 = words.get(args[i]+word);
                            c-=c1*c1++;
                            c+=c1*c1;

                            words.put(args[i]+word,c1);
                            continue;
                        }
                        c++;
                        words.put(args[i]+word,1);
                    }
                    aux=in.readLine();
                }
                words.put(args[i],c);
                in.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    private static void addStopOrQueryWords(String arg, HashSet<String> words) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(arg));
            String aux = in.readLine();
            while (aux!=null){
                String[] a1 = aux.split(" ");
                for (String word:
                        a1) {
                    words.add(word);
                }
                aux=in.readLine();
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
