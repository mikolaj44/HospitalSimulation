package Person;

public class LifeStats<T> {

    private T physical;
    private T internal;
    private T infection;

    public LifeStats(T physical, T internal, T infection) {
        this.physical = physical;
        this.internal = internal;
        this.infection = infection;
    }

    public int getStatAmount() {
        return getClass().getDeclaredFields().length;
    }

    public T getStatByIndex(int index) {

        return switch (index) {
            case 0 -> physical;
            case 1 -> internal;
            case 2 -> infection;
            default -> throw new IndexOutOfBoundsException();
        };
    }

    public void setStatByIndex(int index, T value) {

        switch (index) {
            case 0 -> physical = value;
            case 1 -> internal = value;
            case 2 -> infection = value;
            default -> throw new IndexOutOfBoundsException();
        };
    }

    public String toString() {
        return "Obrażenia fizyczne: " + physical + "\n" + "Obrażenia wewnętrzne: " + internal  + "\n" + "Obrażenia od infekcji: " + infection;
    }

    public T getPhysical() {
        return physical;
    }

    public void setPhysical(T physical) {
        this.physical = physical;
    }

    public T getInternal() {
        return internal;
    }

    public void setInternal(T internal) {
        this.internal = internal;
    }

    public T getInfection() {
        return infection;
    }

    public void setInfection(T infection) {
        this.infection = infection;
    }
}