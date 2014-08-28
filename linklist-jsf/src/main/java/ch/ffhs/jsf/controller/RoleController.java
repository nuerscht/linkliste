package ch.ffhs.jsf.controller;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import ch.ffhs.jpa.domain.Role;
import ch.ffhs.jpa.service.intf.RoleService;

@ManagedBean
public class RoleController {
	private Role role = new Role();

	@EJB
	private RoleService roleService;
	
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public void save() {
		roleService.save(this.role);
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolgreich gespeichert", "asd"));
	}
	
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
}
