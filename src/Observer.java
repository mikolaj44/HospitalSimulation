public interface Observer {

    void update(Subject s);

    void registerWith(Subject s);

    void unRegisterWith(Subject s);
}
