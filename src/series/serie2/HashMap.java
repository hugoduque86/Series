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
    /*
    * Por no next para simplificar o remove
    *
    *
    *
    *
    *
    * */
    /**
     *
     *
     * @param initialCapacity
     * @param lf
     */
    //<< Construtores >>
    public HashMap( int initialCapacity, float lf ) {
        super();
        array=new Node[initialCapacity];
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
        //    private int sizeSet=size;

            @Override
            public Iterator<Entry<K, V>> iterator() {

                return new Iterator<Entry<K, V>>() {

                    private Node<K,V> c =null;
                    private int ix = 0;
                    @Override
                    public boolean hasNext() {

                        if(c!=null)return true;
                        while (ix<array.length&&array[ix]==null)
                            ix++;
                        if(ix==array.length)return false;
                        c=array[ix++];

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
                Node<K,V> aux =(Node<K, V>) o;
                int i = ((aux.getKey().hashCode()%currentMaxCapacity)+currentMaxCapacity)%currentMaxCapacity ;// O(n)
                c = array[i];
                while (c!=null&&!c.equals(o))c=c.next;
                return c!=null;

            }
            @Override
            public void clear() { // O(m)
                for (int i = 0; i < array.length; i++) {
                    array[i]=null;
                }
                size=0;
            }
            @Override
            public boolean remove(Object o) {
                Node<K,V> aux =(Node<K, V>) o ;
                Node<K,V> a=array[(aux.getValue().hashCode()%7+currentMaxCapacity)%currentMaxCapacity];
                if(!containsKey(aux.getKey()))return false;
                while (a.getKey().hashCode()!=aux.getKey().hashCode())a=a.next;
                a=a.next;
                size--;
                return true;
            }

    };

    @Override
    public V put( K k, V v  ) { // Implementar O(1)
        if ( k == null )
            throw new IllegalArgumentException("not support null keys");
        int i = ((k.hashCode()%currentMaxCapacity)+currentMaxCapacity)%currentMaxCapacity;
        if(containsKey(k)){

            V prev = c.getValue();
            c.setValue(v);
            return prev;

        }
        size++;
        array[i]=new Node<K,V>(k,v , k.hashCode(),array[i]);
        return null;


    }

    /* M�todos que devem ser redefinidos OBRIGAT�RIAMENTE no SET
         * embora tenham herdado uma implementa��o de AbstractCollection
     */

    @Override
    public V get( Object k ) {
        if(!containsKey(k))return null;
        return c.getValue();
    }

    @Override
    public boolean containsKey(Object key) {
        int i = ((key.hashCode()%currentMaxCapacity)+currentMaxCapacity)%currentMaxCapacity;
        c=array[i];
        while (c !=null&&c.getKey().hashCode()!=key.hashCode())c=c.next;
        return c !=null;
    }

    public V remove( Object k ) {
        if(!containsKey(k))return null;
        int i = ((k.hashCode()%currentMaxCapacity)+currentMaxCapacity)%currentMaxCapacity;
        Node<K,V> aux = array[i];
        if(array[i].getKey().hashCode()==k.hashCode()) {
            array[i] = array[i].next;
            return aux.getValue();
        }
        while (array[i].next.getKey().hashCode()!=k.hashCode())array[i]=array[i].next;
        array[i].next=array[i].next.next;
        array[i]=aux;
        size--;
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
//            private Iterator<Entry<K,V>>it = entrySet.iterator();
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
