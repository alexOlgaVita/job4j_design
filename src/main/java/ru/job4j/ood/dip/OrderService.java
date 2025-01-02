package ru.job4j.ood.dip;

public class OrderService {
    /* 1. Нарушение принципа DIP: есть зависимость от конкретной реализации хранилища */
    PostgresOrderRepository repository = new PostgresOrderRepository();
    /* 2. Нарушение принципа DIP: есть зависимость от конкретной реализации отправки уведомлений */
    ConfirmationEmailSender mailSender = new ConfirmationEmailSender();

    public void process(Order order) {
        if (order.isValid() && repository.save(order)) {
            mailSender.sendConfirmationEmail(order);
        }
        /* 3. Нарушение принципа DIP: логгирование следует делать независымым, используя логгер, который можно
        гибко настроить должным образом */
        System.out.println("Обработан заказ " + order);
    }
}