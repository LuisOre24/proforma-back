package com.soldev.service.impl;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import com.soldev.repo.IProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.soldev.entity.Proforma;
import com.soldev.repo.IProformaRepo;
import com.soldev.service.IProformaService;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class ProformaServiceImpl implements IProformaService {

	@Autowired
	private IProformaRepo repo;

	@Autowired
	private IProductoRepo prod;

	@Override
	public Proforma registrar(Proforma entity) {
		  
		entity.getDetalleProforma().forEach(det -> {
			det.setProforma(entity);
			//calcular el total en detalle y almacenarlo en la tabla detalle-proforma
			Double total = 0.00;
			total = det.getCantidad() * det.getPrecioVenta();
			det.setTotal(total);
			int cant = det.getProducto().getStock();
			prod.getById(det.getProducto().getIdProducto()).setStock(cant - det.getCantidad());

		});
		return repo.save(entity);
	}

	@Override
	public Proforma actualizar(Proforma entity) {
		return repo.save(entity);
	}

	@Override
	public List<Proforma> obtenerTodo() {
		return repo.findAll();
	}

	@Override
	public Proforma obtenerUno(Integer id) {
		Optional<Proforma> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Proforma();
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);

	}

	@Override
	public Page<Proforma> listPageable(Pageable pageable) {
		return repo.findAllByOrderByIdProformaDesc(pageable);
	}

	@Override
	public byte[] generarProforma(Proforma proforma) {
		byte[] data = null;
		try {
			
			  final InputStream image = new ClassPathResource("/reports/logo.jpeg").getInputStream();
			  //final File imageFooter = new ClassPathResource("/reports/logoFooter.jpeg").getFile();
			  //final File image = ResourceUtils.getFile("classpath:reports/logo.jpeg").;

			  //final File imageFooter = ResourceUtils.getFile("classpath:reports/logoFooter.jpeg");
			  final InputStream imageFooter = new ClassPathResource("/reports/logoFooter.jpeg").getInputStream();
			 
			
			Map<String, Object> params = new HashMap<>();
			params.put("cliente", proforma.getCliente());
			params.put("documento", proforma.getDocumento());
			params.put("logoEmpresa", image);
			params.put("logoFooter", imageFooter);
			params.put("total", proforma.getTotal());
			params.put("nroProforma", proforma.getIdProforma());
			params.put("ds", new JRBeanCollectionDataSource((Collection<?>) proforma.getDetalleProforma()));
			
			/* File file = new ClassPathResource("/reports/proforma.jasper").getFile(); */
			//File file = ResourceUtils.getFile("classpath:reports/proforma.jasper");
			InputStream jasper = getClass().getResourceAsStream("/reports/proforma.jasper");
			final JasperReport report = (JasperReport) JRLoader.loadObject(jasper);
			
			
//			JasperPrint jp = JasperFillManager.fillReport(file.getPath(), params, new JREmptyDataSource());
			JasperPrint jp = JasperFillManager.fillReport(report, params, new JREmptyDataSource());
			data = JasperExportManager.exportReportToPdf(jp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	
}
