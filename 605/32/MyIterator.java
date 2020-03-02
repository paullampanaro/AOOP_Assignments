import java.util.Iterator;

public class MyIterator implements Iterator<Integer> {

    private int position;
    private MyCollectionFrameWork myCollection;

    MyIterator(MyCollectionFrameWork myCollection) {
        this.myCollection = myCollection;
        position = 0;
    }
    public boolean hasNext() {
        return position < myCollection.count.length;
    }

    public Integer next() {
        return myCollection.count[position++];
    }
}
