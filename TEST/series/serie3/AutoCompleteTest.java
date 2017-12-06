package series.serie3;

import org.junit.jupiter.api.Test;

public class AutoCompleteTest {

    TNode root = new TNode();
    @Test
    public void loadWordsFromFileTest1(){
        AutoCompleteUtils.loadWordsFromFile(root,"s1.txt");
    }
}
