package structuralPattern.src;

import java.util.Iterator;
import java.util.LinkedList;

public abstract class CompositeEquipment extends Equipment {
    LinkedList<Equipment> equipments;

    public CompositeEquipment() {
        this.equipments = new LinkedList<>();
    }

    @Override
    void add(Equipment equipment) {
        this.equipments.add(equipment);
    }

    @Override
    void Remove(Equipment equipment) {
        this.equipments.remove(equipment);
    }

    @Override
    int power() {
        Iterator<Equipment> equipmentIterator = this.iterator();
        if (equipmentIterator == null) return 0;
        int total = 0;
        while (equipmentIterator.hasNext()) {
            Equipment equipment = equipmentIterator.next();
            total += equipment.power();
        }
        return total;
    }

    @Override
    double netPrice() {
        Iterator<Equipment> equipmentIterator = this.iterator();
        if (equipmentIterator == null) return 0;
        int total = 0;
        while (equipmentIterator.hasNext()) {
            Equipment equipment = equipmentIterator.next();
            total += equipment.netPrice();
        }
        return total;
    }

    @Override
    double discountPrice() {
        Iterator<Equipment> equipmentIterator = this.iterator();
        if (equipmentIterator == null) return 0;
        int total = 0;
        while (equipmentIterator.hasNext()) {
            Equipment equipment = equipmentIterator.next();
            total += equipment.discountPrice();
        }
        return total;
    }

    @Override
    public Iterator iterator() {
        return this.equipments.iterator();
    }
}
