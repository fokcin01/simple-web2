package org.example.secutiry;

public class SecurityUtil {
    private static int authUserId;

    public static void setAuthUserId(int authUserId) {
        SecurityUtil.authUserId = authUserId;
    }

    public static int getAuthUserId() {
        return authUserId;
    }
}
