public class InvalidStopwordException extends Exception{
    public String stopword;
    public InvalidStopwordException (String stopword) {
       this.stopword = stopword;
    }
    public String toString() {
        return "InvalidStopwordException: Couldn't find stopword: " + stopword;
    }
}
