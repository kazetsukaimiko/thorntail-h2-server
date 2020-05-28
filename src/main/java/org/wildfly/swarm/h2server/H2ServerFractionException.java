package org.wildfly.swarm.h2server;

public class H2ServerFractionException extends RuntimeException {
    public H2ServerFractionException() {
    }

    public H2ServerFractionException(String message) {
        super(message);
    }

    public H2ServerFractionException(String message, Throwable cause) {
        super(message, cause);
    }

    public H2ServerFractionException(Throwable cause) {
        super(cause);
    }

    public H2ServerFractionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
