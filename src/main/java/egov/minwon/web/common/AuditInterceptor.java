package egov.minwon.web.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuditInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AuditInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession(false);
        Object loginUser = (session != null) ? session.getAttribute("LOGIN_USER") : "ANONYMOUS";

        log.info("[AUDIT][REQUEST] user={}, method={}, uri={}, ip={}",
                loginUser,
                request.getMethod(),
                request.getRequestURI(),
                request.getRemoteAddr());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex == null) {
            log.info("[AUDIT][RESPONSE] status={}, uri={}", response.getStatus(), request.getRequestURI());
        } else {
            log.error("[AUDIT][ERROR] uri={}, message={}", request.getRequestURI(), ex.getMessage(), ex);
        }
    }
}
