package series.serie2;

import java.util.Comparator;

public class ListUtils {

    public static class Node<E> {
        public E item;
        public Node<E> next, previous;

        public Node(E item) {
            this.item = item;
            next=null;
            previous=null;
        }
    }

    /**
     *
     * @param lists
     * @param cmp
     * @param k
     * @param <E>
     * @return
     */
    public static <E> Node<E> OccurAtLeastTimes(Node<E> lists[] , Comparator<E> cmp , int k){
        // TODO: 25/10/2017
        Node<E> r = new Node<E>(null);
        r.previous=r;
        r.next=r;
        int count = 1 ;
        if(lists.length==0) return r;
        aggregateListInSingleList(lists,cmp);
        Node<E> aux = lists[0];
        while (aux.next!=null ){
                if(cmp.compare(aux.item,aux.next.item)==0)
                    count++;
                else if (count>= k ){
                    aux=aux.next;
                    aux.previous.next=r;
                    aux.previous.previous=r.previous;
                    r.previous.next = aux.previous;
                    r.previous=aux.previous;
                    continue;
                }
                aux=aux.next;
        }
        return r;

    }

    private static <E> void aggregateListInSingleList(Node<E>[] lists, Comparator<E> cmp) {
        Node<E> newList = lists[0];
        for (int i = 1; i < lists.length; i++) {
            Node<E> current = lists[i];
            while (newList!=null&& current.next!=null) {
                if (cmp.compare(current.item, newList.item) <= 0) {
                    current = current.next;
                    current.previous.next = newList;
                    if (newList.previous != null) {
                        current.previous.previous = newList.previous;
                        newList.previous.next = current.previous;
                    } else {
                        lists[0] = current.previous;
                        current.previous.previous = null;
                    }
                    newList.previous = current.previous;

                    continue;

                }
                while (newList.next != null && cmp.compare(newList.item, current.item) <= 0)
                    newList = newList.next;
                current = current.next;
                if (newList.next != null) {
                    current.previous.next = newList.next;
                    newList.next.previous = current.previous;
                } else
                    current.previous.next = null;
                current.previous.previous = newList;
                newList.next = current.previous;
            }
            if (cmp.compare(current.item, newList.item) > 0){
                while (current.next!=null){
                    current = current.next;
                    current.previous.next=null;
                    current.previous.previous=newList;
                    newList.next = current.previous;
                    newList=newList.next;
                }
                current.next=null;
                current.previous=newList;
                newList.next=current;
            }
            else {
                current.next=newList;
                current.previous=newList.previous;
                newList.previous.next=current;
                newList.previous=current;

            }


                newList=lists[0];

        }


    }
}
