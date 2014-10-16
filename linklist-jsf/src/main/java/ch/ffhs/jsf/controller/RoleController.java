package ch.ffhs.jsf.controller;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import ch.ffhs.jpa.domain.Role;
import ch.ffhs.jpa.service.intf.RoleService;
import ch.ffhs.jsf.configuration.DefaultResourceManager;
import ch.ffhs.jsf.configuration.ResourceManager;

@ManagedBean
@RequestScoped
public class RoleController implements Serializable{
	private static final long serialVersionUID = 7530910279475628466L;
	private Role role = new Role();
	private List<Role> roles;
	private String searchFilter = "";
	
	@Inject @DefaultResourceManager
	private ResourceManager resourceManager;
	
	public void setSearchFilter(String searchFilter) {
		this.searchFilter = searchFilter;
	}

	public List<Role> getRoles() {
		if(roles == null){
			roles = roleService.getAll(); 
		}
		return roles;
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
	
	public void clearRoles() {
		roles = null;
	}
	
	public void updateRole(ActionEvent event){
		int roleId =  (int) event.getComponent().getAttributes().get("roleId");		
		String compId = (String) event.getComponent().getAttributes().get("componentId");
		
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			
		try{
			String name = context.getRequestParameterMap().get(compId);
			Role role = roleService.find(roleId);
			if(role != null){
				role.setName(name);
				roleService.save(role);
				addMessages(FacesMessage.SEVERITY_INFO, 
						MessageFormat.format(resourceManager.getValue(ResourceManager.MESSAGES, "ch.ffhs.jsf.controller.RoleController.UPDATEOK"), role.getName()),
						"Juhu");
			} else {
				addMessages(FacesMessage.SEVERITY_WARN, 
						MessageFormat.format(resourceManager.getValue(ResourceManager.MESSAGES, "ch.ffhs.jsf.controller.RoleController.ROLENOTFOUND"), name),
						"Juhu");
			}
			
		} catch (Exception ex){
			addMessages(FacesMessage.SEVERITY_ERROR, 
					MessageFormat.format(resourceManager.getValue(ResourceManager.MESSAGES, "ch.ffhs.jsf.controller.RoleController.SAVEERROR"), ""),
					"Juhu");
		}		
	}
	
	public void deleteRole(ActionEvent event){
		int roleId =  (int) event.getComponent().getAttributes().get("roleId");
		String name =  (String) event.getComponent().getAttributes().get("roleName");
			
		try{
		
			roleService.delete(roleId);
			addMessages(FacesMessage.SEVERITY_INFO, 
					MessageFormat.format(resourceManager.getValue(ResourceManager.MESSAGES, "ch.ffhs.jsf.controller.RoleController.ROLEDELETED"), name),
					"Juhu");
			
			
		} catch (Exception ex){
			addMessages(FacesMessage.SEVERITY_ERROR, 
					MessageFormat.format(resourceManager.getValue(ResourceManager.MESSAGES, "ch.ffhs.jsf.controller.RoleController.SAVEERROR"), ""),
					"Juhu");
		}		
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
	
	private void addMessages(Severity severity, String summary, String details){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("growl", new FacesMessage(severity, summary, details));
	}

}
