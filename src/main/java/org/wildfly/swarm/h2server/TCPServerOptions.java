package org.wildfly.swarm.h2server;

import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TCPServerOptions implements Function<H2ServerFraction, Stream<String>> {
    BASE_DIR {
        @Override
        public Stream<String> apply(H2ServerFraction fraction) {
            return Stream.of(
                    "-baseDir",
                    fraction.getBaseDirectory().get()
            );
        }
    },
    TCP_PORT {
        @Override
        public Stream<String> apply(H2ServerFraction fraction) {
            return Stream.of(
                    "-tcpPort",
                    String.valueOf(fraction.getTcpPort().get()));
        }
    },
    ALLOW_OTHERS {
        @Override
        public Stream<String> apply(H2ServerFraction fraction) {
            return Stream.of(
                    "-tcpAllowOthers",
                    fraction.getAllowOthers().toString()
            );
        }
    };

    private static final Logger LOGGER = Logger.getLogger(TCPServerOptions.class.getName());

    public static H2ServerFraction init(H2ServerFraction fraction) {
        List<String> options = Stream.of(values())
                .flatMap(tcpServerOptions -> tcpServerOptions.apply(fraction))
                .collect(Collectors.toList());
        LOGGER.info("Creating H2ServerFraction with options: "
            + String.join(" ", options));
        try {
            fraction.getTcpServer().getService()
                .init(options.toArray(new String[] {}));
        } catch (Exception e) {
            throw new H2ServerFractionException(e);
        }
        return fraction;
    }
}
