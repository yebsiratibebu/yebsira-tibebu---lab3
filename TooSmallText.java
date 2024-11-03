public class TooSmallText extends Exception {
    public int count;
    public TooSmallText (int count) {
        this.count = count;
    }
    public String toString() {
        return "TooSmallText: Only found " + count + " words.";
    }
}
