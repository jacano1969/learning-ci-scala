package learningci.chapter02;

public class Tuple<L, R> {

    public L left;
    public R right;

    public Tuple() {
    }

    public Tuple(L left, R right) {
        this.left = left;
        this.right = right;
    }

}
