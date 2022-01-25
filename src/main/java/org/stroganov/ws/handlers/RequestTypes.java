package org.stroganov.ws.handlers;

public enum RequestTypes {

    DELETE_USER("ns2:deleteUser"), BLOCK_USER("ns2:blockUser"),
    UNBLOCK_USER("ns2:unblockUser"), GET_ALL_HISTORY("ns2:getAllHistory"),
    ADD_USER("ns2:addUser"), FIND_USER("ns2:findUser");

    private final String string;

    RequestTypes(String string) {
        this.string = string;
    }

    public static boolean contains(String string) {
        for (RequestTypes c : RequestTypes.values()) {
            if (c.string.equals(string)) {
                return true;
            }
        }
        return false;
    }

    public String getString() {
        return string;
    }
}
