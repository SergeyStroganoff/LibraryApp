package org.stroganov.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static void setCookie(HttpServletResponse resp, String login) {
        Cookie cookie = new Cookie("Login", login);
        cookie.setMaxAge(60 * 60); // время жизни файла cookie
        resp.addCookie(cookie);
    }

    public static String getUserFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        return getValueCookie(cookies, "Login");
    }

    public static String getValueCookie(Cookie[] cookies, String cookieName) {
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())) {
                    return c.getValue();
                }
            }
        }
        return null;
    }

}
