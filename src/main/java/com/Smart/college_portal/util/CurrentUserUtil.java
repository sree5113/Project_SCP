// com.Smart.college_portal.util.CurrentUserUtil.java
package com.Smart.college_portal.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class CurrentUserUtil {
    public static String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // returns email (username)
    }
}
