package ru.job4j.ood.spr.formatter;

import jakarta.xml.bind.JAXBException;

import java.util.List;

public interface DataConvert<T> {

    String convert(List<T> data) throws JAXBException;
}
