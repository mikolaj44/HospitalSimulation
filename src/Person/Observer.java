package Person;

public interface Observer {

    void onUpdate(Subject s);

    void registerWith(Subject s);

    void unRegisterWith(Subject s);
}
