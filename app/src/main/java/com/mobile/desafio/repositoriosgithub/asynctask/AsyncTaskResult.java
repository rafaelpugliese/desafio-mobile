package com.mobile.desafio.repositoriosgithub.asynctask;

public class AsyncTaskResult<T> {

    private Exception exception;
    private T result;

    public void setResult(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public boolean hasException() {
        return exception != null;
    }

    public Exception getException() {
        return exception;
    }

    public boolean existeMensagem() {
        return getException() != null && getException().getMessage() != null;
    }

    public String getMensagem() {
        return getException().getMessage();
    }

}