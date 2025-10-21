package com.miniorm.exceptions;
public class MiniOrmException extends RuntimeException {
    public MiniOrmException(String msg) { super(msg); }
    public MiniOrmException(String msg, Throwable cause) { super(msg, cause); }
}