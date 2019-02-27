package com.study.chapter.Six_Context;

public class Event implements Comparable<Event> {
    private final double time;
    private final Particle a, b;
    private final int countA, countB;

    public Particle getA() {
        return a;
    }

    public Particle getB() {
        return b;
    }

    public Event(double time, Particle a, Particle b) {
        {
            this.time = time;
            this.a = a;
            this.b = b;
            if (a != null) {
                countA = a.count();
            } else countA = -1;
            if (b != null) {
                countB = b.count();
            } else countB = -1;
        }
    }

    public double getTime() {
        return time;
    }

    public int compareTo(Event that) {
        if (this.time < that.time) return -1;
        else if (this.time > that.time) return +1;
        else return 0;
    }

    public boolean isValid() {
        if (a != null && a.count() != countA) return false;
        if (b != null && b.count() != countB) return false;
        return true;
    }
}
