package ch.ffhs.jsf.validators;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import ch.ffhs.jpa.service.intf.RoleService;
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
	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		final String bla = "asdasd";
		System.out.println(bla);

	}

}
