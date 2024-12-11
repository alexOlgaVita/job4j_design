package ru.job4j.ood.spr;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/* нарушение принципа SPR в наличии 2 целей вместо 1: приведение к строго заданному формату и собствнно печать.
Форматирование должно задаваться извне. */

public class DataPrintFormatyyyyMMdd implements DataPrint {
    @Override
    public void print() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }
}