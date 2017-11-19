package series.serie2;

public class Utils {

	static Node<String> Name = new Node<>();
	public static boolean verifyXML(String str){
		if(str.length()==0)return true;
		int aux = verifyIfBeginElement(str,0);

		if(aux==-1)return false;
		aux=verifyMiddleElement(str,aux);
		if(aux==-1)return false;
		aux=verifyIfEndElement(str,aux);
		return aux==str.length();
	}

	private static int verifyMiddleElement(String str, int i) {
		int aux=advanceWhiteChars(str,i);
		aux=IsDescription(str,aux);
		if(aux!=-1)return aux;
		aux=advanceWhiteChars(str,i);
		while (true){
			aux=verifyIfBeginElement(str,aux);
			if(aux==-1)return aux;
			aux=verifyMiddleElement(str,aux);
			if(aux==-1)return aux;
			aux=advanceWhiteChars(str,aux);
			aux= verifyIfEndElement(str,aux);
			if(aux==-1) return aux;
			aux=advanceWhiteChars(str,aux);
			if((aux<str.length()&&str.charAt(aux)=='<')&&(aux+1<str.length()&&str.charAt(aux+1)=='/'))return aux;



		}
		//return aux=advanceWhiteChars(str, i);
	}

	private static int IsDescription(String str, int i) {
		if(str.length()==i||str.charAt(i++)=='<')
			if(str.length()==i||str.charAt(i)!='/') {
				return -1;
			}else if(str.length()>i&&str.charAt(i)=='/')
				return i-1;

		for (;i<str.length();i++){
			if(str.charAt(i)=='<')
				if(i+1<str.length()&&str.charAt(i+1)=='/') {
					return i;
				}else break;
//			break;
		}
		return -1;
	}

	/**
	 * Verify if it was element if not return -1
	 * @param str string to verify
	 * @param i index where to start verify
	 * @return
	 */
	private static int verifyIfBeginElement(String str, int i) {
		if(str.length()<=i||str.charAt(i++)!='<')return -1;
		int aux = verifyIfName(str,i);
		return aux;
//		while (checkIfEndElement(str,aux)==0
//				){
//			aux=verifyIfElementOrDescription(str,aux);
//			if(aux==-1)return -1;
//		}
//		aux=verifyIfEndElement(str,aux);
//		return aux;
	}



	private static int advanceWhiteChars(String str, int i) {
		for (;i<str.length();i++){
			if(str.charAt(i)!=' ')
				break;
		}
		return i;
	}

	private static int verifyIfEndElement(String str, int i) {
		int aux=-1;
		if(str.length()==i||str.charAt(i++)!='<'||str.charAt(i++)!='/')return aux;
		aux=verifyIfEndName(str,i);
		return aux;
	}

	private static int verifyIfEndName(String str, int i) {
		String s ="";
		for (int j = i; j < str.length(); j++) {
			char a= str.charAt(j);
			if((a>='a'&&a<='z')||(a>='A'&&a<='Z')||(a>='0'&&a<='9')){
				s= s +a;
				continue;
			}
			if(a=='>'&&s.length()>=1&&Name.value.equals(s)){
				Name=Name.previous;
				return ++j;
			}
			break;
		}
		return -1;

	}

/*	private static int verifyIfElementOrDescription(String str, int i) {
		//	int aux= i;

		verifyIfBeginElement(str,i);
		for (int j = i; j < str.length(); j++) {
			if(str.charAt(j)=='<')
				return j;

		}


		return -1;
	}*/

	private static int verifyIfName(String str, int i) {
		String s="";
		for (int j = i; j < str.length(); j++) {
			char a= str.charAt(j);
			if((a>='a'&&a<='z')||(a>='A'&&a<='Z')||(a>='0'&&a<='9')){
				s= s +a;
				continue;
			}
			if(a=='>'&&s.length()>=1){
				Name.next=new Node<>(s);
				Name.next.previous=Name;
				Name=Name.next;
				return ++j;
			}
			break;
		}
		return -1;
	}


}


