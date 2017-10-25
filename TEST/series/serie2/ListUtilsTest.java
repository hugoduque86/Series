package series.serie2;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class ListUtilsTest {
    @Test
    public void a (){
        ListUtils.Node<Integer> a= new ListUtils.Node<Integer>(5);
        ListUtils.Node<Integer> b= new ListUtils.Node<Integer>(5);
        ListUtils.Node<Integer> c= new ListUtils.Node<Integer>(5);
        a.previous=new ListUtils.Node<>(3);
        a.previous.next=a;
        a=a.previous;
        ListUtils.Node<Integer> ar[]= new ListUtils.Node[3];
        ar[0]=a;
        ar[1]=b;
        ar[2]=c;
        ListUtils.OccurAtLeastTimes(ar, Comparator.comparingInt(o -> o),1);

    }
}
