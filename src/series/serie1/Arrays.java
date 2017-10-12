package series.serie1;
public class Arrays {

/*    public static int printEachThreeElementsThatSumTo(int[] v, int l, int r, int s){
        int count =0 ;
        int [] auxArray = new int[r-l+1];
        for (int i = 0; i <auxArray.length ; i++) {
            auxArray[i]=v[i+l];
        }
        mergeSort(auxArray,0,auxArray.length-1);
        for (int i = 0; i <=auxArray.length; i++) {
            for (int j = i+1; j <=auxArray.length ; j++) {
                int aux = j+1;
                int auxR = auxArray.length-1 ;
                while (aux<=auxR){
                    int m = aux +(auxR-aux)/2;
                    if(s==auxArray[i]+auxArray[j]+auxArray[m]){
                        count++;
                        break;
                    }
                    if(s>auxArray[i]+auxArray[j]+auxArray[m]){
                        aux=m+1;
                        continue;
                    }
                    auxR=m-1;
                }

            }
        }
        return count;
    }*/

    public static int printEachThreeElementsThatSumTo(int[] v, int l, int r, int s){
        int count =0 ;
        int [] auxArray = new int[r-l+1];
        for (int i = 0; i <auxArray.length ; i++) {
            auxArray[i]=v[i+l];
        }
        mergeSort(auxArray,0,auxArray.length-1);
        for (int i = 0; i <=auxArray.length; i++) {
            int auxL= i+1, auxR = auxArray.length-1;
            while (auxL<auxR){
                if(s==auxArray[i]+auxArray[auxL]+auxArray[auxR]){
                    count++;
                    auxL++;
                    continue;
                }
                if(s>auxArray[i]+auxArray[auxL]+auxArray[auxR]){
                    ++auxL;
                    continue;
                }
                --auxR;
            }
        }
        return count;
    }

/*
     public static int printEachThreeElementsThatSumTo(int[] v, int l, int r, int s){

         int count = 0;
         mergeSort(v,l,r);
         for (int i = l; i <= r; i++) {
             for (int j = i+1; j <= r; j++) {

                 for (int k = j+1; k <= r ; k++) {
                     if(s == v[i]+v[j]+v[k])
                         count++;
                 }
             }

         }
         return count;
     }*/

     public static void merge (int[] a , int l , int r, int m){
         int [] left = new int[m-l+1], rigth = new int[r-m];
         int iL = 0 , iR = 0, iA = l;
         for (int i = 0; i < left.length; i++) {
             left[i]=a[i+l];
         }
         for (int i = 0; i < rigth.length; i++) {
             rigth[i]=a[i+m+1];
         }
         while (iL<left.length && iR<rigth.length){
             if (left[iL]<=rigth[iR]){
                 a[iA++]=left[iL++];
                 continue;
             }
             a[iA++]=rigth[iR++];
         }
         while (iL<left.length)a[iA++]=left[iL++];
         while (iR<rigth.length)a[iA++]=rigth[iR++];



     }
     public static void mergeSort (int[] a, int l , int r){
         if(l>=r)return;
         int m = l + (r-l)/2;
         mergeSort(a,l,m);
         mergeSort(a,m+1,r);
         merge(a,l,r,m);
     }


    public static int removeIndexes(int v[], int l, int r, int[] vi, int li, int ri) {
        int offset = 0;

        for (int i = l; i <=r; i++) {
          //
            if(li<=ri&& i+offset<=r && i+offset==vi[li]){
                offset++;
                --i;
                ++li;
                continue;
            }
            if(offset+i>r)continue;


            v[i]=v[i+offset];
            if(li<=ri&& i>v[li]) {
                ++li;
                --i;
            }}
        return (r-l)-offset+1;
    }


    public static String greaterCommonPrefix(String[] v, int l, int r, String word) {
         int iR = -1, max = 0;
         if (v.length==0)return null;
        for (int i = l; i <= r ; i++) {
            int aux = countCommomLetters(v[i],word);
            if(aux > max) {
                max=aux;
                iR=i;
                continue;
            }
            if(iR!=-1 && aux==max && v[i].length()>v[iR].length())iR=i;
        }
         return iR==-1 ? v [v.length-1]: v[iR];

    }

    private static int countCommomLetters(String s, String word) {
        int i = 0;
        for (; ((i < word.length()) && (i< s.length())) && (s.charAt(i)==word.charAt(i)); i++) {

        }
         return i;
    }


    public static int sumGivenN(int n){

         int l = 0 , count =0, times=1 ;
        for (int r = 1; r < n; ) {
            if(count==n){
                times++;
                l=r;
                count=0;
            }
            if(count<n){
                count+=r++;
            }
            if (count>n){
                count-=l;
                l++;
            }


        }

         return times;
         }

    public static int deleteMin(int[] maxHeap, int sizeHeap) {
       throw new UnsupportedOperationException();
    }

}
