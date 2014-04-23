package com.pcide;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class TestPrint {

	public static void main(String[] args) {
		try {
			// 由 IReport 做好报表格式后(.jrxml)，用该方法编译成 JasperReport 支持的 .jasper 格式：
			// 这里使用了子报表（subreport），子报表也需要被编译成 jasper 文件

			String jrxml = "e:/Mytest.jrxml";
			String jasper = jrxml.substring(0, jrxml.lastIndexOf(".jrxml"))	+ ".jasper";

			JasperCompileManager.compileReportToFile(jrxml, jasper);

			InputStream is = new FileInputStream(jasper);
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);

			// HashMap 里存放的 key ，对应着报表中的 $P{key} ：
			List<Detile> listDetiles = new ArrayList<Detile>();
			Detile d1 = new Detile(1,"女鞋10021款",new Pa("nemo"),1);
			Detile d2 = new Detile(2,"男鞋2355",new Pa("nemo1"),3);
			Detile d3 = new Detile(3,"包包",new Pa("nemo2"),1);
			listDetiles.add(d1);
			listDetiles.add(d2);
			listDetiles.add(d3);
			


			HashMap parameters = new HashMap();
			parameters.put("SHOP_TITLE", "报表测试结束");
			parameters.put("OP_DATE", new Date());

			JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(listDetiles));
			
			// 查看测试
			//JasperViewer.viewReport(print, false);

			// JRPdfExporter exporter = new JRPdfExporter();

			// 设置成打印横向
			// print.setOrientation(OrientationEnum.LANDSCAPE);
			
			

			String printerName = "default";

			// 获取打印服务
			PrintService printService = null;
			if (printerName.equals("default")) {
				printService = PrintServiceLookup.lookupDefaultPrintService();
			} else {
				PrintService[] printlist = PrintServiceLookup.lookupPrintServices(null, null);
				for (int i = 0; i < printlist.length; i++) {
					if (printerName.equals(printlist[i].getName())) {
						printService = printlist[i];
						break;
					}
				}
			}

			JRAbstractExporter export = new JRPrintServiceExporter();
			// 设置打印内容
			export.setParameter(JRExporterParameter.JASPER_PRINT, print);
			// 设置指定打印机
			export.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService);
			export.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
			export.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);

			export.exportReport();
			 
			System.out.println("done!");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
