package ch.ffhs.jsf.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import ch.ffhs.jpa.domain.Role;
import ch.ffhs.jpa.service.intf.RoleService;

@ManagedBean
@RequestScoped
public class RoleController implements Serializable{
	private static final long serialVersionUID = 7530910279475628466L;
	private Role role = new Role();
	private List<Role> roles;
	private String searchFilter = "";
	
	public void setSearchFilter(String searchFilter) {
		this.searchFilter = searchFilter;
	}

	public List<Role> getRoles() {
		if(roles == null){
			roles = roleService.getAll();
		}
		return  roleService.getAll();
	}

	@EJB
	private RoleService roleService;
	private List<Role> filteredRoles;
	
	
	public void setFilteredRoles(List<Role> filteredRoles) {
		this.filteredRoles = filteredRoles;
	}

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
	
	public void updateRole(ActionEvent event){
		int roleId =  (int) event.getComponent().getAttributes().get("roleId");
		String name =  (String) event.getComponent().getAttributes().get("roleName");
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erfolgreich gespeichert", "asd"));
	}
	
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public List<Role> getFilteredRoles() {
		return filteredRoles;
	}

	public String getSearchFilter() {
		return searchFilter;
	}

}
