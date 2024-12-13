package ru.job4j.ood.spr.ocp;

public class Repository {
    /* нарушение принципа ОСР в том, что приходится менять исходны код - код зависит от реализации абстрации,
      а не от самой абстракции */
    public void save(AbstractEntity entity) {
        if (entity instanceof AccountEntity) {
            /* логика работы */
            return;
        } else if (entity instanceof CustomerEntity) {
            /* логика работы */
            return;
        }
    }
}
