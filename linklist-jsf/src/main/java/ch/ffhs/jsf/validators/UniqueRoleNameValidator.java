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

import org.jboss.logging.Logger;

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
	private Logger logger = Logger.getLogger(UniqueRoleNameValidator.class);
	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

			try {
				String name = value.toString();
				int id = Integer.parseInt((String) component.getAttributes().get("validatorRoleId"));
				Role role = roleService.findByName(name);
				if( role != null && role.getId() != id ){
					FacesMessage msg = new FacesMessage(MessageFormat.format(, arguments));
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

}
