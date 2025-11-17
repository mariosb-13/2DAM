import com.db4o.Db4o;
import com.db4o.ObjectContainer;

public class Main {
    public static void main(String[] args) {
        ObjectContainer db = Db4o.openFile("test.db4o");
        System.out.println("DB4O abierto correctamente: " + db);
        db.close();
    }
}
