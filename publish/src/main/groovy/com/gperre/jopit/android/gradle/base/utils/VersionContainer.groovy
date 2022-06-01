package com.gperre.jopit.android.gradle.base.utils

final class VersionContainer {

    private static Map<String, String> map

    static void init() {
        map = new HashMap<>()
    }

    private static String key(String projectName, String publicationName) {
        return "${projectName}:${publicationName}"
    }

    static void put(String projectName, String publicationName, String version) {
        if (!map.get(key(projectName, publicationName))) {
            map.put(key(projectName, publicationName), version)
        }
    }

    static String get(String projectName, String publicationName, String defaultValue = '') {
        return map.get(key(projectName, publicationName), defaultValue)
    }

    static void logVersion(String version) {
        println ("${(27 as Character)}[32mPublishing version: ${version} ${(27 as Character)}[0m")
    }
}
