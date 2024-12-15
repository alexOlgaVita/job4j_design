package ru.job4j.ood.lsp;


/* Нарушение принципа LSP в том, что наследник по сути не реализует метод полета - можно сказать, нарушается правило
 предусловия */
public class Penguin extends Bird {

    public void pass() {
        System.out.println("Иду пешком со скоростью среднестатистического пингвина.");
    }

    @Override
    public void fly() {
        System.out.println("Извините, не умею летать");
    }
}
