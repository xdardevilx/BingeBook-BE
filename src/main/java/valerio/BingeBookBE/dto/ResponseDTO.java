package valerio.BingeBookBE.dto;

public record ResponseDTO<T>(
         String message,
         T data
) {}