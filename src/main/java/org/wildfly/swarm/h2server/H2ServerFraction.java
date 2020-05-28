package org.wildfly.swarm.h2server;

import org.h2.tools.Server;
import org.wildfly.swarm.config.runtime.AttributeDocumentation;
import org.wildfly.swarm.spi.api.Defaultable;
import org.wildfly.swarm.spi.api.Fraction;
import org.wildfly.swarm.spi.api.annotations.Configurable;

import java.sql.SQLException;
import java.util.logging.Logger;

public class H2ServerFraction implements Fraction<H2ServerFraction> {
    private static final Logger LOGGER = Logger.getLogger(H2ServerFraction.class.getName());

    private final Server tcpServer;

    public H2ServerFraction() {
        try {
            this.tcpServer = Server
                    .createTcpServer();
        } catch (SQLException throwables) {
            throw new H2ServerFractionException(throwables);
        }
    }

    @Configurable("thorntail.h2-server.base-directory")
    @AttributeDocumentation("Base directory for ")
    private Defaultable<String> baseDirectory = Defaultable.string("~/.config/h2");

    @Configurable("thorntail.h2-server.tcp-port")
    @AttributeDocumentation("TCP Port to listen on")
    private Defaultable<Integer> tcpPort = Defaultable.integer(9092);

    @Configurable("thorntail.h2-server.allow-others")
    @AttributeDocumentation("Allow other hosts to connect")
    private Defaultable<Boolean> allowOthers = Defaultable.bool(true); // TODO False


    public Server getTcpServer() {
        return tcpServer;
    }

    public Defaultable<String> getBaseDirectory() {
        return baseDirectory;
    }

    public Defaultable<Integer> getTcpPort() {
        return tcpPort;
    }

    public Defaultable<Boolean> getAllowOthers() {
        return allowOthers;
    }

    @Override
    public H2ServerFraction applyDefaults() {
        return applyDefaults(false);
    }

    @Override
    public H2ServerFraction applyDefaults(boolean hasConfiguration) {
        TCPServerOptions.init(this);
        return this;
    }

    public static H2ServerFraction createDefaultFraction() {
        return (new H2ServerFraction()).applyDefaults();
    }

}
