package com.study.chapter.Sec_Sort.Fourth_PriorityQueue;

import java.util.Comparator;
import java.util.Date;

public class Transaction {
    // .....
    private final String who;
    private final Date when;
    private final double amount;

    public Transaction(String who, Date when, double amount) {
        this.who = who;
        this.when = when;
        this.amount = amount;
    }
    // ......

    public static class WhoOrder implements Comparator<Transaction> {
        @Override
        public int compare(Transaction o1, Transaction o2) {
            return o1.who.compareTo(o2.who);
        }
    }

    public static class WhenOrder implements Comparator<Transaction> {
        @Override
        public int compare(Transaction o1, Transaction o2) {
            return o1.when.compareTo(o2.when);
        }
    }

    public static class HowMuchOrder implements Comparator<Transaction> {
        @Override
        public int compare(Transaction o1, Transaction o2) {
            if (o1.amount < o2.amount) return -1;
            if (o2.amount > o1.amount) return 1;
            return 0;
        }
    }
}
