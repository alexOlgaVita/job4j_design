package ru.job4j.ood.isp;

/* Нарушение принципа ISP в наличии методов, ненужных для реализации классов, имплементирующих этот интерфейс.
Например, газовой плите нет функции включить/выключить электронфорку, а в электроплите нет функции
подать/остановить подачу газа.
*/
public interface KitchenStove {

    void gasTurnOn();

    void gasTurnOff();

    void electricHotplateTurnOn();

    void electricHotplateTurnOff();
}
