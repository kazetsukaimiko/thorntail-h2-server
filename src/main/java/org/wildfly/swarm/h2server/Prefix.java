package org.wildfly.swarm.h2server;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Prefix {
    public static final String PREFIX = "thorntail.h2server";
    public static final String DELIMITER = ".";
    private Prefix() {

    }

    public static String pieces(String... items) {
        return Stream.concat(Stream.of(PREFIX), Stream.of(items)).collect(Collectors.joining(DELIMITER));
    }
}
