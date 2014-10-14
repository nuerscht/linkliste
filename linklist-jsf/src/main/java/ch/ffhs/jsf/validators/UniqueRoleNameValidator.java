package ch.ffhs.jsf.validators;

import java.text.MessageFormat;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ffhs.jpa.domain.Role;
import ch.ffhs.jpa.service.intf.RoleService;
import ch.ffhs.jsf.configuration.DefaultResourceManager;
import ch.ffhs.jsf.configuration.ResourceManager;
/**
 * Simple unique role validator.
 * @author Sandro Dallo
 *
 */
@ManagedBean
@RequestScoped
public class UniqueRoleNameValidator implements Validator {

	@Inject
	private RoleService roleService;
	@Inject
	private @DefaultResourceManager ResourceManager resourceManager;
	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

			
				String name = value.toString();
				int id = (int) component.getAttributes().get("validatorRoleId");
				Role role = roleService.findByName(name);
				if( role != null && role.getId() != id ){					
					FacesMessage msg = new FacesMessage(MessageFormat.format(resourceManager.getValue(ResourceManager.MESSAGES, 
							"ch.ffhs.jsf.validators.UniqueRoleNameValidator.NOTUNIQUE"), role.getName()));
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(msg);
				}	
			
	}

}
