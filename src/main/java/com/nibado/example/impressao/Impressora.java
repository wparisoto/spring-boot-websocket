package com.nibado.example.impressao;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;

public final class Impressora {
	// variavel estatica porque será utilizada por inumeras threads

	private static PrintService impressora;

	public Impressora() {
		detectaImpressoras();
	}
// O metodo verifica se existe impressora conectada e a
// define como padrao.

	public void detectaImpressoras() {
		try {
			DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
			PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);
			for (PrintService p : ps) {
				if (p.getName() != null && p.getName().contains("HP")) {
					System.out.println("Impressora Selecionada: " + p.getName());
					System.out.println("Impressora encontrada: " + p.getName());
					impressora = p;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized boolean imprime(String texto) {
		// se nao existir impressora, entao avisa usuario
		// senao imprime texto
		if (impressora == null) {
			String msg = "Nennhuma impressora foi encontrada. Instale uma impressora padrão \r\n(Generic Text Only) e reinicie o programa.";
			System.out.println(msg);
		} else {
			System.out.println(texto);
			try {
				DocPrintJob dpj = impressora.createPrintJob();
				InputStream stream = new ByteArrayInputStream(texto.getBytes());
				DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
				Doc doc = new SimpleDoc(stream, flavor, null);
				
				PrintRequestAttributeSet printerAttributes = new HashPrintRequestAttributeSet();
				printerAttributes.add(new JobName("Impressao", null));
				printerAttributes.add(OrientationRequested.PORTRAIT);
				printerAttributes.add(MediaSizeName.ISO_A4);

				dpj.print(doc, (PrintRequestAttributeSet) printerAttributes);
				return true;
			} catch (PrintException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public synchronized boolean imprime(byte[] bytes) {
		// se nao existir impressora, entao avisa usuario
		// senao imprime texto
		if (impressora == null) {
			String msg = "Nennhuma impressora foi encontrada. Instale uma impressora padrão \r\n(Generic Text Only) e reinicie o programa.";
			System.out.println(msg);
		} else {
			System.out.println(bytes);
			try {
				DocPrintJob dpj = impressora.createPrintJob();

				InputStream stream = new ByteArrayInputStream(bytes);

				DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

				Doc doc = new SimpleDoc(stream, flavor, null);

				PrintRequestAttributeSet printerAttributes = new HashPrintRequestAttributeSet();
				printerAttributes.add(new JobName("Impressao", null));
				printerAttributes.add(OrientationRequested.PORTRAIT);
				printerAttributes.add(MediaSizeName.ISO_A4);

				dpj.print(doc, (PrintRequestAttributeSet) printerAttributes);

				return true;
			} catch (PrintException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

//	public void imprimir(){
//		try{
//		InputStream prin = new ByteArrayInputStream(imp2.getBytes());
//		INPUT_STREAM docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
//		SimpleDoc documentoTexto = new SimpleDoc(prin, docFlavor, null);
//		PrintService impressora = PrintServiceLookup.lookupDefaultPrintService(); // pega a impressora padrao
//		PrintRequestAttributeSet printerAttributes = new HashPrintRequestAttributeSet();
//		printerAttributes.add(new JobName(“Impressao”, null));
//		printerAttributes.add(OrientationRequested.PORTRAIT);
//		printerAttributes.add(MediaSizeName.ISO_A4); // informa o tipo de folha
//		DocPrintJob printJob = impressora.createPrintJob();
//		try{
//		printJob.print(documentoTexto, (PrintRequestAttributeSet)printerAttributes); //tenta imprimir
//		}
//		catch(PrintException e){
//		JOptionPane.showMessageDialog(null, “Não foi possível realizar a impressão !!”,“Erro”,
//		JOptionPane.ERROR_MESSAGE); // mostra mensagem de erro
//		}
//		prin.close();
//		}
//		catch(Exception e){
//		}
//
//		}

//	Finalmente consegui
//	fazer o
//	que queria.
//	Caso alguém
//	um dia
//	precise pegar
//	o tamanho do
//	papel da
//	impressora padrão, abaixo
//	segue o código:
//
//	PrinterData data = Printer.getDefaultPrinterData();
//	Printer printer = new Printer(data);
//	Rectangle retangulo = printer.getBounds();
//	double valorLargura = retangulo.width / 2;
//	double valorAltura = retangulo.height / 2;valorLargura=((valorLargura/300)*25.4)+0.5D;valorAltura=((valorAltura/300)*25.4)+0.5D;
//	int altura = (int) valorAltura;
//	int largura = (int) valorLargura;System.out.println(largura+" "+altura);
}
