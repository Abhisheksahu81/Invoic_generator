package com.abhi.itexttest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.Table;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    int p1q = 0;
    int p2q = 0;
    int p3q = 0;
    int p4q = 0;

    int p1p = 100;
    int p2p = 200;
    int p3p = 300;
    int p4p = 400;

    String customername = "";

    SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat datetimeformat = new SimpleDateFormat("dd-MM-yyyy-h:mm a");



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]
                        {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);

        try {
            createPDF();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createPDF() throws IOException {

        String date = dateformat.format(new Date());
        String dateandtime  = datetimeformat.format(new Date());



        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();

        String pdftime = dateandtime;

        File file = new File(pdfPath+"/ShankarTraders","Bill at "+pdftime+".pdf");
        if (!file.exists())
            Files.createFile(file.toPath());

        OutputStream outputStream = new FileOutputStream(file);

        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        document.setMargins(0, 0, 0, 0);



        Drawable d2 = getDrawable(R.drawable.logo);
        Bitmap bitmap2 = ((BitmapDrawable) d2).getBitmap();
        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, stream2);
        byte[] bitmapData2 = stream2.toByteArray();


        ImageData imageData2 = ImageDataFactory.create(bitmapData2);
        Image image2 = new Image(imageData2);

        image2.scaleAbsolute(540f, 200f);
        document.add(image2);


        float columnwidth1[] = {120, 220, 120, 100};
        Table table1 = new Table(columnwidth1);
        table1.setMargin(20);

        getquantity();
        getrate();



        table1.addCell(new Cell().add(new Paragraph("Customer Name").setFontSize(14).setFontColor(ColorConstants.BLACK)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph(customername).setFontSize(14).setFontColor(ColorConstants.BLACK)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Invoice No.").setFontSize(14).setFontColor(ColorConstants.BLACK)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("#1212").setFontSize(14).setFontColor(ColorConstants.BLACK)).setBorder(Border.NO_BORDER));


        table1.addCell(new Cell().add(new Paragraph("Mo. no. ").setFontSize(14).setFontColor(ColorConstants.BLACK)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("10203040506").setFontSize(14).setFontColor(ColorConstants.BLACK)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph("Date").setFontSize(14).setFontColor(ColorConstants.BLACK)).setBorder(Border.NO_BORDER));
        table1.addCell(new Cell().add(new Paragraph(date).setFontSize(14).setFontColor(ColorConstants.BLACK)).setBorder(Border.NO_BORDER));

        DeviceRgb secColor = new DeviceRgb(8, 106, 119);

        float columnwidth2[] = {220, 120, 100, 120};
        Table table2 = new Table(columnwidth2);
        table2.setMargin(20);

        table2.addCell(new Cell().add(new Paragraph("Item Description")).setBorder(new SolidBorder(secColor, 2)));
        table2.addCell(new Cell().add(new Paragraph("Price")).setBorder(new SolidBorder(secColor, 2)));
        table2.addCell(new Cell().add(new Paragraph("Qty")).setBorder(new SolidBorder(secColor, 2)));
        table2.addCell(new Cell().add(new Paragraph("Total")).setBorder(new SolidBorder(secColor, 2)));

        table2.addCell(new Cell().add(new Paragraph("SSP")).setBorder(new SolidBorder(secColor, 2)));
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(p1p))).setBorder(new SolidBorder(secColor, 2)));
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(p1q))).setBorder(new SolidBorder(secColor, 2)));
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(p1q * p1p))).setBorder(new SolidBorder(secColor, 2)));

        table2.addCell(new Cell().add(new Paragraph("MOP")).setBorder(new SolidBorder(secColor, 2)));
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(p2p))).setBorder(new SolidBorder(secColor, 2)));
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(p2q))).setBorder(new SolidBorder(secColor, 2)));
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(p2q * p2p))).setBorder(new SolidBorder(secColor, 2)));

        table2.addCell(new Cell().add(new Paragraph("DAP")).setBorder(new SolidBorder(secColor, 2)));
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(p3p))).setBorder(new SolidBorder(secColor, 2)));
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(p3q))).setBorder(new SolidBorder(secColor, 2)));
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(p3q * p3p))).setBorder(new SolidBorder(secColor, 2)));

        table2.addCell(new Cell().add(new Paragraph("Urea")).setBorder(new SolidBorder(secColor, 2)));
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(p4p))).setBorder(new SolidBorder(secColor, 2)));
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(p4q))).setBorder(new SolidBorder(secColor, 2)));
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(p4q * p4p))).setBorder(new SolidBorder(secColor, 2)));


        table2.addCell(new Cell(1, 2).add(new Paragraph("----Thanks!!! Visit Again----")).setBorder(new SolidBorder(secColor, 2)));
        //table2.addCell(new Cell().add(new Paragraph("")));
        table2.addCell(new Cell().add(new Paragraph("Total")).setBorder(new SolidBorder(secColor, 2)));
        table2.addCell(new Cell().add(new Paragraph("Rs. " + totalcalc())).setBorder(new SolidBorder(secColor, 2)));


        document.add(table1);
        document.add(table2);
        document.close();
        Toast.makeText(getApplicationContext(), "Pdf created successfully", Toast.LENGTH_SHORT).show();
        //finish();
        openFile(file);

    }

    private void getrate() {
        @SuppressLint("WrongConstant")
        SharedPreferences sh = getSharedPreferences("RateList", MODE_APPEND);


        p1p = sh.getInt("ratep1", 0);
        p2p = sh.getInt("ratep2", 0);
        p3p = sh.getInt("ratep3", 0);
        p4p = sh.getInt("ratep4", 0);

    }


    private void getquantity() {
        Intent intent = getIntent();
        p1q = intent.getIntExtra("p1q", 0);
        p2q = intent.getIntExtra("p2q", 0);
        p3q = intent.getIntExtra("p3q", 0);
        p4q = intent.getIntExtra("p4q", 0);

        customername = intent.getStringExtra("c_name");
    }

    private String totalcalc() {
        int t = (p1p * p1q) + (p2q * p2p) + (p3q * p3p) + (p4q * p4p);
        return String.valueOf(t);
    }

    private void openFile(File file) {
        if (file.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(MainActivity.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }

    }
}