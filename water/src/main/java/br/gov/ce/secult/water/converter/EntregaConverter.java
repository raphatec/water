package br.gov.ce.secult.water.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.gov.ce.secult.water.entity.Entrega;
import br.gov.ce.secult.water.repository.EntregaDAO;
import br.gov.ce.secult.water.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Entrega.class)
public class EntregaConverter implements Converter {
	private EntregaDAO dao;

	public EntregaConverter() {
		dao = CDIServiceLocator.getBean(EntregaDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Entrega retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = dao.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Entrega entrega = (Entrega) value;
			return entrega.getId() == null ? null : entrega.getId().toString();
		}

		return "";
	}
}
