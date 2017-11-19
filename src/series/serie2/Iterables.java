package series.serie2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Iterables {

    public static Iterable<Pair<String, Integer>> groupingEquals(Iterable<String> words)  {
       return new Iterable<Pair<String, Integer>>() {
           @Override
           public Iterator<Pair<String, Integer>> iterator() {
               return new Iterator<Pair<String, Integer>>() {

                   Pair<String,Integer>c = null;
                   Iterator<String>it = words.iterator();
                   String n = null;
                   @Override
                   public boolean hasNext() {

                       if(c!=null)return true;

                       if(!it.hasNext())
                       {
                           if(n !=null){
                            c=new Pair<>(n,1);
                            n=null;
                           return true;
                       }
                       return false;
                       }
                       if(n ==null) n =it.next();
                       String word = n;
                       Integer count = 1;
                       while (it.hasNext()) {
                           n =it.next();

                           if(!word.equals(n))
                               break;
                           count++;

                       }
                       if(word.equals(n))n=null;
                       c=new Pair<>(word,count);


//                       Pair<String,Integer> aux = new Pair<>(it.n(),0);

                       return true;
                   }

                   @Override
                   public Pair<String, Integer> next() {
                       if(!hasNext()) throw new NoSuchElementException();
                        Pair<String,Integer>aux = c;
                        c=null;
                        return aux;
                   }

               };
           }
       };
    }


    public static Iterable<Integer> getSortedSubsequence(Iterable<Integer> src, int k)  {
        return new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {

                    Integer c = null;
                    Integer val = k;
                    Iterator<Integer> it = src.iterator();
                    @Override
                    public boolean hasNext() {
                        if(c!=null)return true;
                        while (it.hasNext()) {
                            Integer aux = it.next();
                            if (aux >=val){
                                c=aux;
                                val=c;
                                return true;
                            }
                        }
                        return false;
                    }

                    @Override
                    public Integer next() {
                        if(!hasNext()) throw new NoSuchElementException();
                        Integer aux = c;
                        c=null;
                        return aux;
                    }
                };
            }
        };
   }  
}



