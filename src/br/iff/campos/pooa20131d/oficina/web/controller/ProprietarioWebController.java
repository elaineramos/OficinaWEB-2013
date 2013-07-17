package br.iff.campos.pooa20131d.oficina.web.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.iff.campos.pooa20131d.oficina.model.controller.ProprietarioPersistence;
import br.iff.campos.pooa20131d.oficina.model.entity.Proprietario;

@ManagedBean(name = "proprietarioweb")
@RequestScoped
public class ProprietarioWebController {

	@EJB(lookup = "java:app/oficinaEJB/ProprietarioPersistence")
	private ProprietarioPersistence jproprietario;

	private Proprietario proprietario;

	public Proprietario getProprietario() {
		if (proprietario == null) {
			proprietario = new Proprietario();
		}
		return proprietario;
	}

	public List<Proprietario> getAllProprietario() {
		return jproprietario.findAll();
	}

	public String salva() {

		if (jproprietario.find(proprietario.getUid()) != null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Proprietario Ja Cadastrado"));

		} else {
			jproprietario.insert(proprietario);

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Cadastrado com sucesso!"));
		}
		return "ok";
	}
	
	public String delete() {

		if (jproprietario.find(proprietario.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Proprietario não Existe"));

		} else {
			jproprietario.delete(proprietario.getUid());

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Proprietario Excluido!"));
		}
		return "ok";
	}
	
	public String update() {

		if (jproprietario.find(proprietario.getUid()) == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Proprietario não Existe"));

		} else {
			jproprietario.update(proprietario);

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Alterado com sucesso!"));
		}
		return "ok";
	}

	public String busca() {

		proprietario = jproprietario.find(proprietario.getUid());
		if (proprietario == null) {
			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage("Proprietario não Existe"));

		} else {
			jproprietario.update(proprietario);

			FacesContext.getCurrentInstance().addMessage("frmTeste:msgOK",
					new FacesMessage(""));
		}
		return "ok";
	}

	public ProprietarioWebController() {

	}

}
