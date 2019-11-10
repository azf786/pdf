package com.jcg.examples;
/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * This code sample was written by Bruno Lowagie in answer to this question:
 * http://stackoverflow.com/questions/27241731/change-background-image-in-itext-to-watermark-or-alter-opacity-c-sharp-asp-net
 */

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.text.Chunk;

import java.io.File;

public class BackgroundTransparent {
    public static final String DEST = "C:\\CarsatTestPdf\\pdf\\src\\main\\resources\\background.pdf";
    public static final String IMAGE = "C:\\CarsatTestPdf\\pdf\\src\\main\\resources\\OdreDeMission.jpg";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new BackgroundTransparent().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));

        // Note that it is not necessary to create new PageSize object,
        // but for testing reasons (connected to parallelization) we call constructor here
        PageSize pageSize = new PageSize(PageSize.A4);
        Document doc = new Document(pdfDoc, pageSize);

        PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());
        ImageData image = ImageDataFactory.create(IMAGE);
        canvas.saveState();
        PdfExtGState state = new PdfExtGState();
        //state.setFillOpacity(0.6f);
        canvas.setExtGState(state);
        canvas.addImage(image, 0,0, pageSize.getWidth(), false);
        canvas.restoreState();

        doc.add(new Paragraph("\n\n\n\n"));
        Paragraph p1 = new Paragraph();
        p1.setPaddingLeft(100);
        p1.add("Berlin                                                                              ");
        p1.add("121315");
        p1.add("\n");
        p1.add("Berlin");
        p1.setBold();
        p1.setFontSize(25);
        doc.add(p1);

        doc.close();
    }
}