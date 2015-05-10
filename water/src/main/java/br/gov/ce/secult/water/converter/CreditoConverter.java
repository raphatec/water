package br.gov.ce.secult.water.converter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.gov.ce.secult.water.entity.Credito;
import br.gov.ce.secult.water.repository.CreditoDAO;
import br.gov.ce.secult.water.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Credito.class)
public class CreditoConverter implements Converter {

	private CreditoDAO dao;

	public CreditoConverter() {
		dao = CDIServiceLocator.getBean(CreditoDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Credito retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = dao.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Credito credito = (Credito) value;
			return credito.getId() == null ? null : credito.getId().toString();
		}

		return "";
	}
}
