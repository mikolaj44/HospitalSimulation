public class LifeStats <T>{

    private T physical;
    private T internal;
    private T infection;

    public LifeStats(T physical, T internal, T infection) {
        this.physical = physical;
        this.internal = internal;
        this.infection = infection;
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
