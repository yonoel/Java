package com.study.chapter.Six_Context;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdDraw;

public class CollisionSystem {
    private MinPQ<Event> pq;
    private double t = .0;
    private Particle[] particles;

    public CollisionSystem(Particle[] particles) {
        this.particles = particles;
    }

    private void predictCollisions(Particle particle, double limit) {
        if (particle == null) return;
        for (int i = 0; i < particles.length; i++) {
            double dt = particle.timeToHit(particles[i]);
            if (t + dt <= limit) pq.insert(new Event(t + dt, particle, particles[i]));
        }
        double dtx = particle.timeTohHitVerticalWall();
        if (t + dtx <= limit) pq.insert(new Event(t + dtx, particle, null));
        double dty = particle.timeTohHitHorizontalWall();
        if (t + dty <= limit) pq.insert(new Event(t + dty, null, particle));
    }

    public void redraw(double limit, double Hz) {
        StdDraw.clear();
        for (int i = 0; i < particles.length; i++) {
            particles[i].draw();
        }
        StdDraw.pause(20);
        StdDraw.enableDoubleBuffering();
        if (t < limit) {
            pq.insert(new Event(t + 1.0 / Hz, null, null));
        }
    }

    public void simulate(double limit, double Hz) {
        pq = new MinPQ<>();
        for (int i = 0; i < particles.length; i++) {
            predictCollisions(particles[i], limit);
        }
        pq.insert(new Event(0, null, null));
        while (!pq.isEmpty()) {
            Event event = pq.delMin();
            if (!event.isValid()) continue;
            for (int i = 0; i < particles.length; i++) {
                particles[i].move(event.getTime() - t);
            }
            t = event.getTime();
            Particle a = event.getA(), b = event.getB();
            if (a != null && b != null) a.bounceOff(b);
            else if (a != null && b == null) a.bounceOffHorizontalWall();
            else if (a == null && b != null) b.bounceOffVerticalWall();
            else if (a == null && b == null) redraw(limit, Hz);
            predictCollisions(a, limit);
            predictCollisions(b, limit);
        }
    }

    public static void main(String[] args) {
        StdDraw.show();
        StdDraw.pause(0);
        int n = 20;
        Particle[] particles = new Particle[n];
        for (int i = 0; i < n; i++) {
            particles[i] = new Particle();
        }
        CollisionSystem system = new CollisionSystem(particles);
        system.simulate(10000, 0.5);
    }
}
