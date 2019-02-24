import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Класс для чтения файлов txt

public class ReadFiles {

    static final String dir = new File("").getAbsolutePath() + File.separator + "src"
                        + File.separator + "main" + File.separator + "resources";

    private Map<String, ArrayList<String>> dataList = new HashMap<String, ArrayList<String>>();//

    private ArrayList<String> maleName 	= new ArrayList<String>();
    private ArrayList<String> womanName = new ArrayList<String>();
    private ArrayList<String> maleFam 	= new ArrayList<String>();
    private ArrayList<String> womanFam 	= new ArrayList<String>();
    private ArrayList<String> maleOtc  	= new ArrayList<String>();
    private ArrayList<String> womanOtc 	= new ArrayList<String>();

    private ArrayList<String> city    = new ArrayList<String>();
    private ArrayList<String> country = new ArrayList<String>();
    private ArrayList<String> region  = new ArrayList<String>();
    private ArrayList<String> street  = new ArrayList<String>();

    public ReadFiles() {
        this.dataList.put("maleName",  this.setList("NameM.txt", this.maleName));
        this.dataList.put("womanName", this.setList("NameW.txt", this.womanName));
        this.dataList.put("maleFam",   this.setList("surnameM.txt", this.maleFam));
        this.dataList.put("womanFam",  this.setList("surnameW.txt", this.womanFam));
        this.dataList.put("maleOtc",   this.setList("patronymicM.txt", this.maleOtc));
        this.dataList.put("womanOtc",  this.setList("patronymicW.txt", this.womanOtc));
        this.dataList.put("city",      this.setList("city.txt", this.city));
        this.dataList.put("country",   this.setList("country.txt", this.country));
        this.dataList.put("region",    this.setList("region.txt", this.region));
        this.dataList.put("street",    this.setList("street.txt", this.street));
    }

    private ArrayList<String> setList (String fileName,  ArrayList<String> list) {

        try(BufferedReader br = new BufferedReader(new FileReader(ReadFiles.dir + File.separator + fileName)))
        {
            String line;
            while((line=br.readLine())!=null) {
                list.add(line);
            }
        }
        catch(IOException ex){
            System.out.println(fileName + " - ошибка чтения");

        }
        return list;
    }

    public String getListElm(String prop){
        return this.dataList.get(prop).get(UsersGenerator.generateIndex(this.dataList.get(prop).size()));
    }
}
