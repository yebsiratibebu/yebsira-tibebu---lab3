import java.io.IOException;
public class EmptyFileException extends IOException {
    public String path;
    public EmptyFileException (String path) {
       this.path = path;
    }
    public String toString() {
        return "EmptyFileException: " + path + " was empty";
    }
}
