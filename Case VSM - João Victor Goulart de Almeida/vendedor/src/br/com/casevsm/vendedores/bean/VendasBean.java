package br.com.casevsm.vendedores.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.casevsm.vendedores.dao.VendedorDao;
import br.com.casevsm.vendedores.modelo.Venda;
import br.com.casevsm.vendedores.modelo.Vendedor;

@Named
@ViewScoped
public class VendasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	VendedorDao vendedorDao;

	public BarChartModel getVendasModel() {

		BarChartModel model = new BarChartModel();
		model.setAnimate(true);
		ChartSeries vendaSerie = new ChartSeries();
		vendaSerie.setLabel("Vendas 2016");

		List<Venda> vendas = getVendas(1234);

		for (Venda venda : vendas) {
			vendaSerie.set(venda.getVendas(), venda.getQuantidade());
		}

		model.addSeries(vendaSerie);

		ChartSeries vendaSerie2015 = new ChartSeries();
		vendaSerie2015.setLabel("Vendas 2015");

		vendas = getVendas(4321);

		for (Venda venda : vendas) {
			vendaSerie2015.set(venda.getVendas(), venda.getQuantidade());
		}

		model.addSeries(vendaSerie2015);

		return model;
	}

	public List<Venda> getVendas(long seed) {

		List<Vendedor> vendededores = vendedorDao.listaTodos();
		List<Venda> vendas = new ArrayList<Venda>();

		Random random = new Random(seed);

		for (Vendedor vendedor : vendededores) {
			Integer quantidade = random.nextInt(500);
			vendas.add(new Venda(quantidade, vendedor));
		}

		return vendas;
	}
}
