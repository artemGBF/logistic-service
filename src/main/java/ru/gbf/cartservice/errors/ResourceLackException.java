package ru.gbf.cartservice.errors;

public class ResourceLackException extends RuntimeException{

    public ResourceLackException() {
        super("Данный товар закончился на складе :(");
    }

    public ResourceLackException(int a) {
        super("Данного товара в количестве "+a+"шт. нет на складе :(");
    }
}
