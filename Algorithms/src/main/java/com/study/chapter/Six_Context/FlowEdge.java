package com.study.chapter.Six_Context;

public class FlowEdge {
    private final int from;
    private final int to;
    private final double cap;
    private double flow;

    public FlowEdge(int from, int to, double cap) {
        this.from = from;
        this.to = to;
        this.cap = cap;
        this.flow = .0;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public int other(int v) {
        if (v == from) return to;
        else if (v == to) return from;
        else return -1;
    }

    public double capacity() {
        return cap;
    }

    public double flow() {
        return flow;
    }

    public double residualCapacityTo(int v) {
        if (v == from) return flow;
        if (v == to) return cap - flow;
        else throw new RuntimeException("no such edge");
    }

    public void addResidualFlowTo(int v, double delta) {
        if (v == from) flow -= delta;
        else if (v == to) flow += delta;
        else throw new RuntimeException("no such edge");
    }

    @Override
    public String toString() {
        return String.format("%d -> %d %.2f %.2f", from, to, cap, flow);
    }

    public static void main(String[] args) {
        FlowEdge edge = new FlowEdge(12,23,4.56);
        System.out.println(edge);
    }
}
