package org.stroganov.aspects;

public class CurrentPrincipal {
    private static String principalName;

    private CurrentPrincipal() {
    }

    public static String getPrincipalName() {
        return principalName;
    }

    public static void setPrincipalName(String principalName) {
        CurrentPrincipal.principalName = principalName;
    }
}
