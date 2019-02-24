import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

// ����� ��� ���������� ����� � PDF
public class SaveAsPDF {
    //�������� ������ ���������
    private Document document = new Document(PageSize.A4, 50, 50, 50, 50);
    private PdfPTable table = new  PdfPTable(14);
    private BaseFont font;

    private void createHeader() {
        this.table.addCell(this.getPhrase("���"));
        this.table.addCell(this.getPhrase("�������"));
        this.table.addCell(this.getPhrase("��������"));
        this.table.addCell(this.getPhrase("������"));
        this.table.addCell(this.getPhrase("���"));
        this.table.addCell(this.getPhrase("���� ��������"));
        this.table.addCell(this.getPhrase("���"));
        this.table.addCell(this.getPhrase("�������� ������"));
        this.table.addCell(this.getPhrase("������"));
        this.table.addCell(this.getPhrase("�������"));
        this.table.addCell(this.getPhrase("�����"));
        this.table.addCell(this.getPhrase("�����"));
        this.table.addCell(this.getPhrase("���"));
        this.table.addCell(this.getPhrase("��������"));
    }

    private void createBody(ArrayList<User> list) {

        for(int index = 0; index < list.size(); index++) {
            this.table.addCell(this.getPhrase(list.get(index).getName()));
            this.table.addCell(this.getPhrase(list.get(index).getFam()));
            this.table.addCell(this.getPhrase(list.get(index).getOtch()));
            this.table.addCell(this.getPhrase(String.valueOf(list.get(index).getAge())));
            this.table.addCell(this.getPhrase(list.get(index).getSex()));
            this.table.addCell(this.getPhrase(list.get(index).getDate()));
            this.table.addCell(this.getPhrase(String.valueOf(list.get(index).getInn())));
            this.table.addCell(this.getPhrase(String.valueOf(list.get(index).getPostCode())));
            this.table.addCell(this.getPhrase(list.get(index).getCountry()));
            this.table.addCell(this.getPhrase(list.get(index).getRegion()));
            this.table.addCell(this.getPhrase(list.get(index).getCity()));
            this.table.addCell(this.getPhrase(list.get(index).getStreet()));
            this.table.addCell(this.getPhrase(String.valueOf(list.get(index).getHomeNumber())));
            this.table.addCell(this.getPhrase(String.valueOf(list.get(index).getRoomNumber())));
        }
    }

    private String save(){
        String msg = "";
        try (FileOutputStream out = new FileOutputStream(new File(
                ReadFiles.dir + File.separator + "File.pdf"))) {
            PdfWriter.getInstance(this.document, out);
            this.document.open();
            this.document.add(this.table);
            this.document.close();
            msg = "���� ������. ����:" +
                    ReadFiles.dir + File.separator + "File.pdf";
        } catch (DocumentException e) {
            msg = "������ �������� pdf �����!";
            System.out.print(e);
        } catch(IOException e) {
            msg = "������ �������� pdf �����!";
            System.out.print(e);
        }
        return msg;
    }

    private void setFont() {
        try {
            this.font = BaseFont.createFont(
                    ReadFiles.dir + File.separator + "TIMES.TTF","cp1251",BaseFont.EMBEDDED);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile(ArrayList<User> list){
        this.setFont();
        this.createHeader();
        this.createBody(list);
        System.out.println(this.save());
    }

    private Phrase getPhrase(String text) {
        return new Phrase(text, new Font(this.font, 11));
    }
}