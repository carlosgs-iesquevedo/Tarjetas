package es.carlosgs.tarjetas.exceptions;


public abstract class TarjetaException extends RuntimeException {
    public TarjetaException(String message) {
        super(message);
    }
}
