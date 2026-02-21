package com.Elite.Erum_Makeover.Exeptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String msg){ super(msg); }
}