package ru.job4j.ood.isp;

/* Нарушение принципа ISP в наличии методов, ненужных для реализации классов, имплементирующих этот интерфейс.
Например, в обычно резиновой лодке нет мотора, т.е. нет функции включить мотор.
На моторной (нерезиновой) лодке нет весет - не фнкции гребли.
*/
public interface Boat {

    void engineStart();

    void row();
}