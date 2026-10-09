package au.edu.unimelb.swen.oop.mokepon;

// trackable object. Can be passed through to functions to limit their access to a larger object.
// id is unused in this version, but has been left in for future extension.
public class TrackableDuplet<T,X>{

    private T value1;
    private X value2;
    private int id;
    private static int id_counter = 0;

    public TrackableDuplet(T value1, X value2) {
        this.id = id_counter++;
        this.value1 = value1;
        this.value2 = value2;
    }

    public T getValue1() {
        return value1;
    }

    public void setValue1(T value1) {
        this.value1 = value1;
    }

    public X getValue2() {
        return value2;
    }

    public void setValue2(X value2) {
        this.value2 = value2;
    }

    public int getId()
    {
        return id;
    }


}
