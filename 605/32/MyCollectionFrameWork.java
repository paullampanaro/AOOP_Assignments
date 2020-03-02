class MyCollectionFrameWork implements Iterable<Integer> {

    int[] count;

    MyCollectionFrameWork(int size) {
        count = new int[size];
    }

    public int get(int index) {
        return count[index];
    }

    public void set(int index, int value) {
        count[index] = value;
    }

    public MyIterator iterator(){
        return new MyIterator(this);
    }
}
