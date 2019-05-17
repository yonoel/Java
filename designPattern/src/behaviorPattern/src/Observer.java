package behaviorPattern.src;

import java.util.Observable;

public abstract class Observer implements java.util.Observer {
    abstract void update(Subject changedSubject);
}
