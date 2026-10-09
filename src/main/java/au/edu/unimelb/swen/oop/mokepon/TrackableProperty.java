package au.edu.unimelb.swen.oop.mokepon;

// trackable object. Can be passed through to functions to limit their access to a larger object.
// id is unused in this version, but has been left in for future extension.
public class TrackableProperty<T> {
    private int id;
    private T value;
    private static int id_counter = 0;

    public TrackableProperty(T value) {
        this.id = id_counter++;
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int getId()
    {
        return id;
    }
}
