package ch.ffhs.jsf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.ffhs.jsf.controller.AuthenticationController;

@WebFilter(urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession ses = req.getSession(false);
			String reqURI = req.getRequestURI();
			if (reqURI.indexOf("/login.faces") >= 0
					|| (ses != null && ses.getAttribute(AuthenticationController.cUSERNAME) != null)
					|| reqURI.contains("javax.faces.resource")) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + "/pages/web/login/login.faces");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
