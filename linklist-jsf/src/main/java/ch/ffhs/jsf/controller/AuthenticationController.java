package ch.ffhs.jsf.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ch.ffhs.jpa.domain.User;
import ch.ffhs.jpa.service.intf.UserService;

@ManagedBean
@SessionScoped
public class AuthenticationController implements Serializable {
	private static final long serialVersionUID = 5608008257590934546L;
	
	public static final String cUSERNAME = "username";
	
	@EJB
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	private FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}

	private HttpSession getSession() {
		return (HttpSession) getContext().getExternalContext().getSession(false);
	}

	public String login() {
		User user = userService.findByUsernameAndPassword(this.username, this.password);
		String redirect = null;
		
		if (user != null) {
			//Login korrekt
			getSession().setAttribute(cUSERNAME, this.username);
			redirect = "/pages/web/index?faces-redirect=true";
		} else {
			getContext().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Die angegebenen Benutzerdaten sind fehlerhaft.", "Bitte versuchen Sie es erneut."));
		}
		
		return redirect;		
	}
	
	public String logout() {
		getSession().setAttribute(cUSERNAME, null);
		return "/pages/web/login/login?faces-redirect=true";
	}
}
