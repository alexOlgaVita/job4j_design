package ru.job4j.ood.spr;

/* нарушение принципа SPR в том, что в этой абстрации есть 2 цели вместо 1: оплатить заказ и проверить заказ*/
public abstract class Order<T> {
    abstract T pay(T order);

    abstract boolean check();
}
