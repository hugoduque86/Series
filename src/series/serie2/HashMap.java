package series.serie2;

import java.util.*;

/**
 * Created by msousa on 11/13/2017.
 */
public class HashMap<K,V> extends AbstractMap<K, V> {
    private static final int DEFAULT_CAPACITY = 11;
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;

    private static class Node<K,V> extends SimpleEntry<K,V> {
        private final int hc;
        private Node<K,V> next;

        private Node(K key, V value, int hc, Node<K,V> next) {
            super( key, value );
            this.hc = hc;
            this.next = next;
        }
    }

    // << Variaveis de instancia >>

    private Node<K,V>[] array;
    private int currentMaxCapacity ;
    private Node<K,V>c = null;
    private float lf ;
    private int size = 0;


    //<< Construtores >>
    public HashMap ( int initialCapacity, float lf ) {
        super();
        array=new Node[initialCapacity];
        for (int i = 0; i < array.length; i++) {
            array[i]=new Node<K,V>(null,null,-1,null);
        }
        currentMaxCapacity=initialCapacity;
        this.lf=lf;

    }
    public HashMap(  ) {
        this( DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR );
    }
    public HashMap(Map<? extends K, ? extends V> m) {
        this(m.size(), DEFAULT_LOAD_FACTOR);
        putAll(m);
    }


    @Override
    public Set<Entry<K,V>> entrySet() { return entrySet; }
    private Set<Entry<K,V>> entrySet = new AbstractSet<Entry<K, V>>() {
            @Override
            public Iterator<Entry<K, V>> iterator() {

                return new Iterator<Entry<K, V>>() {

                    private Node<K,V> c =null;
                    private int ix = 0;
                    @Override
                    public boolean hasNext() {

                        if(c!=null)return true;
                        while (ix<array.length&&array[ix].next==null)
                            ix++;
                        if(ix==array.length)return false;
                        c=array[ix++].next;

                        return true;
                    }

                    @Override
                    public Entry<K, V> next() {
                        if(!hasNext()) throw new NoSuchElementException();
                        Entry<K,V> aux= c;
                        c=c.next;
                        return aux;
                    }
                };
            }
            @Override
            public int size() {

                return size;
            }
            /* M�todos que t�m que ser redefinidos OBRIGAT�RIAMENTE no SET
             * embora tenham herdado uma implementa��o de AbstractSet
             */
            @Override
            public boolean contains(Object o) {

                int i = ((o.hashCode()%currentMaxCapacity)+currentMaxCapacity)%currentMaxCapacity ;
                c = array[i];
                while (c.next!=null&&c.next.getKey().hashCode()!=o.hashCode())c=c.next;
                return c!=null;

            }
            @Override
            public void clear() { // O(m)
                for (int i = 0; i < array.length; i++) {
                    array[i].next=null;
                }
                size=0;
            }
            @Override
            public boolean remove(Object o) {
                if(!contains(o))return false;
                c.next=c.next.next;
                size--;
                return true;
            }

    };

    @Override
    public V put( K k, V v  ) { // Implementar O(1)
        if ( k == null )
            throw new IllegalArgumentException("not support null keys");

        if(containsKey(k)){

            V prev = c.next.getValue();
            c.next.setValue(v);
            return prev;

        }
        size++;
        int i = ((k.hashCode()%currentMaxCapacity)+currentMaxCapacity)%currentMaxCapacity;
        array[i].next=new Node<K,V>(k,v , k.hashCode(),array[i].next);
        return null;


    }

    /* M�todos que devem ser redefinidos OBRIGAT�RIAMENTE no SET
         * embora tenham herdado uma implementa��o de AbstractCollection
     */

    @Override
    public V get( Object k ) {
        if(!containsKey(k))return null;
        return c.next.getValue();
    }

    @Override
    public boolean containsKey(Object key) {
        int i = ((key.hashCode()%currentMaxCapacity)+currentMaxCapacity)%currentMaxCapacity;
        c=array[i];
        while (c.next !=null&&c.next.getKey().hashCode()!=key.hashCode())c=c.next;
        return c.next !=null;
    }

    public V remove( Object k ) {
        if(!containsKey(k))return null;
        Node<K,V> aux = c.next;
        size--;
        c.next=c.next.next;
        return aux.getValue();
    }

    @Override
    public void clear() {
        entrySet.clear();
    }

    //Analisar os m�todos
    @Override
    public Set<K> keySet() {
        // S� implementar se conseguirem diminuir a complexidade dos m�todos
        // caso contr�rio, remover este Override
        return new AbstractSet<K>() {
            @Override
            public Iterator<K> iterator() {
                return new Iterator<K>() {
                    private Iterator<Entry<K,V>> it= entrySet.iterator();
                    @Override
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    @Override
                    public K next() {
                        return it.next().getKey();
                    }
                };
            }

            @Override
            public int size() {
                return size;
            }
        };
    }
    @Override
    public Collection<V> values() {
        // S� implementar se conseguirem diminuir a complexidade dos m�todos
        // caso contr�rio, remover este Override
        return new AbstractCollection<V>() {
//            private Iterator<Entry<K,v>>it = entrySet.iterator();
            @Override
            public Iterator<V> iterator() {

                return new Iterator<V>() {
                    private Iterator<Entry<K,V>>it = entrySet.iterator();
                    @Override
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    @Override
                    public V next() {
                        return it.next().getValue();
                    }
                };
            }

            @Override
            public int size() {
                return size;
            }
        };
    }

}
