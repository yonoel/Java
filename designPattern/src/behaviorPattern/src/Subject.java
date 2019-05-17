package behaviorPattern.src;

import java.util.List;
import java.util.Observable;

public abstract class Subject extends Observable {
    void attach(Observer observer) {
        observers.add(observer);
    }

    void detach(Observer observer) {
        observers.remove(observer);
    }

    void Notify() {
        observers.forEach(observer -> observer.update(this));
    }

    private List<Observer> observers;
}
