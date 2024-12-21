package ru.job4j.ood.isp;

/* Нарушение принципа ISP в наличии методов, ненужных для реализации для классов, имплементирующих этот интерфейс.
Например, птицы не плавают. Рыбы не ходят и не говорят. Парнокоптные не плавают и не летают.
*/
public interface Animal {

    void swim();

    void fly();

    void eat();

    void foot();

    void makeSound();
}
