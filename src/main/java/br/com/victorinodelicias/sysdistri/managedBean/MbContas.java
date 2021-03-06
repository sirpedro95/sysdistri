package br.com.victorinodelicias.sysdistri.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import br.com.victorinodelicias.dto.DtoVendedor;
import br.com.victorinodelicias.sysdistri.bussiness.BoContasPR;
import br.com.victorinodelicias.sysdistri.bussiness.BoVendedor;
import br.com.victorinodelicias.sysdistri.entity.EnContasPr;
import br.com.victorinodelicias.sysdistri.enums.EnumPessoa;
import br.com.victorinodelicias.sysdistri.enums.EnumTipoConta;
import br.com.victorinodelicias.sysdistri.enums.EnumTipoDocumento;

@Named
@ViewAccessScoped
public class MbContas implements Serializable {

	private static final long serialVersionUID = 1L;

	private EnContasPr conta;
	private List<EnContasPr> listaContas;
	private List<EnumTipoConta> listaTipoConta;
	private List<EnumTipoDocumento> listaTipoDocumento;
	private List<EnumPessoa> listaPessoas;
	private EnumPessoa tipoPessoa;
	private Integer idVendedorSelecionado;

	private List<DtoVendedor> listaDtoVendedores;

	@Inject
	private BoContasPR boContasPr;

	@Inject
	private BoVendedor boVendedor;

	@PostConstruct
	public void init() {
		listaTipoConta = EnumTipoConta.getValues();
		listaPessoas = EnumPessoa.getValues();
		listaTipoDocumento = EnumTipoDocumento.getValues();
		listaContas = boContasPr.listarTodos();
		conta = new EnContasPr();
	}

	public String novo() {
		conta = new EnContasPr();
		return "form.xhtml?faces-redirect=true";
	}

	public void carregarPessoas() {

		if (tipoPessoa.equals(EnumPessoa.VENDEDOR))
			listaDtoVendedores = boVendedor.buscarTodosPorDto();
		// else if (tipoPessoa.equals(EnumPessoa.CLIENTE.getCodigo()))
		// listaDtoPessoas = new ArrayList<DtoPessoa>();
		// else if (tipoPessoa.equals(EnumPessoa.VENDEDOR.getCodigo()))
		// listaDtoPessoas = new ArrayList<DtoPessoa>();

	}

	public List<DtoVendedor> completeVendedor(String query) {
		List<DtoVendedor> results = new ArrayList<DtoVendedor>();
		for (DtoVendedor p : listaDtoVendedores) {
			if (p.getNome().toLowerCase().startsWith(query.toLowerCase()))
				results.add(p);
		}
		return results;
	}

	public void pessoaConta() {
		switch (tipoPessoa) {
		case VENDEDOR:
			conta.setCodVendedor(idVendedorSelecionado);
		default:
			break;
		}
	}

	public void salvar() {
		conta.setCodVendedor(idVendedorSelecionado);
	}

	public EnContasPr getConta() {
		return conta;
	}

	public void setConta(EnContasPr conta) {
		this.conta = conta;
	}

	public List<EnumTipoConta> getListaTipoConta() {
		return listaTipoConta;
	}

	public void setListaTipoConta(List<EnumTipoConta> listaTipoConta) {
		this.listaTipoConta = listaTipoConta;
	}

	public List<EnumTipoDocumento> getListaTipoDocumento() {
		return listaTipoDocumento;
	}

	public void setListaTipoDocumento(List<EnumTipoDocumento> listaTipoDocumento) {
		this.listaTipoDocumento = listaTipoDocumento;
	}

	public List<EnContasPr> getListaContas() {
		return listaContas;
	}

	public void setListaContas(List<EnContasPr> listaContas) {
		this.listaContas = listaContas;
	}

	public List<EnumPessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<EnumPessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}

	public List<DtoVendedor> getListaDtoVendedores() {
		return listaDtoVendedores;
	}

	public void setListaDtoVendedores(List<DtoVendedor> listaDtoVendedores) {
		this.listaDtoVendedores = listaDtoVendedores;
	}

	public Integer getIdVendedorSelecionado() {
		return idVendedorSelecionado;
	}

	public void setIdVendedorSelecionado(Integer idVendedorSelecionado) {
		this.idVendedorSelecionado = idVendedorSelecionado;
	}

	public EnumPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(EnumPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

}
