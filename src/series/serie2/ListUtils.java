package series.serie2;
import java.util.NoSuchElementException;
import java.util.Comparator;

public class ListUtils {

	public static <E> void internalReverse(Node<Node<E>> list){
		while (list!=null){

			if(list.value==null){
				list=list.next;
				continue;
			}

			while (true){
				Node<E> aux=list.value.next;
				list.value.next=list.value.previous;
				list.value.previous=aux;
				if(aux==null)break;
				list.value=aux;
			}


			list=list.next;
		}
	}


	public static <E> Node<E> 
	occurAtLeastKTimes(Node<E>[] lists, Comparator<E> cmp, int k)  {
		Node<E> r = new Node<>();
		r.next=r.previous=r;
		if(lists.length==0)return r;
		merge(lists,cmp);
		int count = 0 ;


		Node<E> aux= lists[0];
		if (aux==null)return r;
		E val = aux.value;
		while (aux!=null){

			if (cmp.compare(val,aux.value)==0){

				count++;
				if (count==k) {
					Node<E>aux2=aux.next;
					aux.next=r;
					aux.previous=r.previous;
					r.previous.next=aux;
					r.previous=aux;
					aux=aux2;
					count=0;
					continue;
				}
				aux=aux.next;


			}else {
				val=aux.value;
				//a0ux=aux.next;
				count=0;





		}}
		return r;

	}

	private static <E> void merge(Node<E>[] lists, Comparator<E> cmp) {
		int r = 0;
		for (int i = 1; i < lists.length; i++) {
			Node<E> aux = lists[i];
			if(aux==null||lists[r]==null)continue;
			if(cmp.compare(lists[r].value,aux.value)>=0){
				aux=lists[r];
				lists[r]=null;
				r=i;
			}
//			r = cmp.compare(r.value,aux.value)>=0?aux:r;
			Node<E> rAux = lists[r];

			while (rAux!=null&&aux!=null){

				if (cmp.compare(rAux.value, aux.value)>=0){

					Node<E> next = aux.next;
					aux.next=rAux;
					aux.previous=rAux.previous;
					if(rAux.previous!=null)
						rAux.previous.next= aux;
					rAux.previous= aux;
					aux=next;
					continue;

				}
				if (rAux.next == null) {
					break;
				}
				rAux=rAux.next;
			}
			if(aux!=null){
				aux.previous=rAux;
				rAux.next=aux;
			}
			if(r!=i)
				lists[i]=null;


		}
		lists[0]= lists[r];


	}


}
