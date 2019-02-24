import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class SaveAsExel {
    HSSFWorkbook book 	= new HSSFWorkbook();
    HSSFSheet sheet		= book.createSheet("����");

    void createHeader(){
        Row row = this.createRow(0);
        row.createCell(0).setCellValue("���");
        row.createCell(1).setCellValue("�������");
        row.createCell(2).setCellValue("��������");
        row.createCell(3).setCellValue("������");
        row.createCell(4).setCellValue("���");
        row.createCell(5).setCellValue("���� ��������");
        row.createCell(6).setCellValue("���");
        row.createCell(7).setCellValue("�������� ������");
        row.createCell(8).setCellValue("������");
        row.createCell(9).setCellValue("�������");
        row.createCell(10).setCellValue("�����");
        row.createCell(11).setCellValue("�����");
        row.createCell(12).setCellValue("���");
        row.createCell(13).setCellValue("��������");
    }

    private Row createRow(int index){
        return this.sheet.createRow(index);
    }

    private void createBody(ArrayList<User> list) {
        for(int index = 0; index < list.size(); index++) {
            Row row = this.createRow(index + 1);
            row.createCell(0).setCellValue(list.get(index).getName());
            row.createCell(1).setCellValue(list.get(index).getFam());
            row.createCell(2).setCellValue(list.get(index).getOtch());
            row.createCell(3).setCellValue(list.get(index).getAge());
            row.createCell(4).setCellValue(list.get(index).getSex());
            row.createCell(5).setCellValue(list.get(index).getDate());
            row.createCell(6).setCellValue(list.get(index).getInn());
            row.createCell(7).setCellValue(list.get(index).getPostCode());
            row.createCell(8).setCellValue(list.get(index).getCountry());
            row.createCell(9).setCellValue(list.get(index).getRegion());
            row.createCell(10).setCellValue(list.get(index).getCity());
            row.createCell(11).setCellValue(list.get(index).getStreet());
            row.createCell(12).setCellValue(list.get(index).getHomeNumber());
            row.createCell(13).setCellValue(list.get(index).getRoomNumber());
        }
    }

    public String save(){
        String msg = "";
        try (FileOutputStream out = new FileOutputStream(new File(ReadFiles.dir + File.separator + "File.xls"))) {
            this.book.write(out);
            msg = "���� ������. ����: " + ReadFiles.dir + File.separator + "File.xls";
        } catch (IOException e) {
            msg = "������ �������� exel �����!";
        }
        return msg;
    }

    void createFile(ArrayList<User> list){
        this.createHeader();
        this.createBody(list);
        System.out.println(this.save());
    }
}