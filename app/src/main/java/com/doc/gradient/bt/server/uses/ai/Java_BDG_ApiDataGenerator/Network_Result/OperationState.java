package com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.Network_Result;

public class OperationState<T> {
    public final Status status;
    public final T data;
    public final String message;
    private OperationState(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> OperationState<T> loading() {
        return new OperationState<>(Status.RAHJOVO, null, null);
    }

    public static <T> OperationState<T> success(T data) {
        return new OperationState<>(Status.MALYU, data, null);
    }

    public static <T> OperationState<T> failure(String message) {
        return new OperationState<>(Status.NOMALYU, null, message);
    }

    public enum Status { RAHJOVO, MALYU, NOMALYU }
}