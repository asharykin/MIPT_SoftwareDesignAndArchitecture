package ru.mipt.zoo.model.thing;

public class Table extends Thing {

    public Table(int number) {
        super(number);
    }

    @Override
    public String toString() {
        return "Table{" +
                "number=" + number +
                '}';
    }
}
