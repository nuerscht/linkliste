package ch.ffhs.jsf.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@ViewScoped
public class AuthenticationController implements Serializable {
	private static final long serialVersionUID = 5608008257590934546L;

	private HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	
	public String logout() {
		getSession().invalidate();
		return "/pages/web/index?faces-redirect=true";
	}
}