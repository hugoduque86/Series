package series.serie3;

import java.util.Comparator;

public class TreeUtils {




    public static <E> boolean contains(Node<E> root, E min, E max, Comparator<E> cmp){
        if (root==null ) return false;
        if(cmp.compare(root.v,min)> 0 && cmp.compare(root.v,max)<0)return true;
        if(cmp.compare(root.v,min)<0) return contains(root.l,min,max,cmp);
        return contains(root.r,min,max,cmp);
    }

    public static <E extends Comparable<E>> Node<E> lowestCommonAncestor(Node<E> root, E n1, E n2 ){
        if(root==null) return  null;
        if((root.v.compareTo(n1)<=0 && root.v.compareTo(n2)>=0)||(root.v.compareTo(n2)<=0 && root.v.compareTo(n1)>=0)) return root;
        if(root.v.compareTo(n1)<0 && root.v.compareTo(n2)<0) return lowestCommonAncestor(root.l,n1,n2);
        return  lowestCommonAncestor(root.r,n1,n2);
    }

    public static <E> boolean isBalanced(Node<E> root){
        return isBalancedAux(root)!=-1;
    }

    private static <E> int isBalancedAux(Node<E> root) {
        if (root==null)return 0;
        int l = isBalancedAux(root.l);
        int r = isBalancedAux(root.r);
        if(l==-1||r==-1)return -1;
        if(r-l==0 || r-l == 1) return r+1;
        if(l-r==1 ) return  l+1;
        return -1;
    }
}
