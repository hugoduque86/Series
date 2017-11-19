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
                        query.put(word,query.get(word)+1);
                        continue;
                    }
                    query.put(word,1);

                }
                aux=in.readLine();

            }
            in.close();
            countQuery(query.values().iterator());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void countQuery(Iterator<Integer> entries) {
        while (entries.hasNext()){
            int aux= entries.next();
            queryCount+=aux*aux;
        }
    }


    private static void ranking(String arg) {
        Iterator<String> it =query.keySet().iterator();
        int c1 = 0 ;
        while (it.hasNext()){
            String aux = it.next();
            if(words.containsKey(arg+aux)){
                c1+=words.get(arg+aux);
//                c2+=query.get(aux);
            }
        }
        System.out.println(arg+" = "+(c1/(Math.sqrt(queryCount)*Math.sqrt(words.get(arg)))));



    }

    private static void count(HashMap<String, Integer> args, String file) {

        int count = 0;
        Iterator<Integer> it = args.values().iterator();
        while (it.hasNext()) {
            int aux = it.next();

            count+=aux*aux;
        }
        words.put(file,count);
    }

    private static void addWords(String[] args) {
        for (int i = 1; i<args.length;i++) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(args[i]));
                String aux = in.readLine();
                HashMap<String,Integer>  aux1 = new HashMap<String, Integer>();

                while (aux!=null){
                    String[] a1 = aux.split(" ");
                    for (String word:
                            a1) {
                        if(stopWords.contains(word))
                            continue;
                        if (aux1.containsKey(args[i]+word)){
                            aux1.put(args[i]+word,aux1.get(args[i]+word)+1);
                            continue;
                        }
                        aux1.put(args[i]+word,1);
                    }
                    aux=in.readLine();
                }
                count(aux1,args[i]);
                words.putAll(aux1);
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
