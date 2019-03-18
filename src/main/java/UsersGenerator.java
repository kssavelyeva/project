import java.util.ArrayList;
import java.util.*;


public class UsersGenerator {
    private SaveAsExel exel;
    private SaveAsPDF pdf;
    private ReadFiles data;
    private DBIO 	dbio;

    private ArrayList<User> users = new ArrayList<User>();

    public UsersGenerator(SaveAsExel exel, ReadFiles data, SaveAsPDF pdf, DBIO dbio) {
        this.exel 	= exel;
        this.data 	= data;
        this.pdf 	= pdf;
        this.dbio 	= dbio;
    }

    public void run() {
        this.setUsers(30);
        this.exel.createFile(this.users);
        this.pdf.createFile(this.users);
    }

    private User getUser() {
        final boolean sex = new Random().nextInt(101) <= 50;
        return new User().setName(sex, this.data).setFam(sex,  this.data).setOtch(sex, this.data)
                .setCity(this.data).setCountry(this.data).setRegion(this.data).setStreet(this.data).setHomeNumber().setRoomNumber()
                .setPostCode().setDate().setInnCode().setSex(sex);

    }

    private void setUsers(int index) {
        this.users = this.data.getUsersByHttp(index);
        if(this.users.size() != 0) {
            this.dbio.saveUserToDb(this.users);
        } else {
            try {
                this.users = this.dbio.getUserData(UsersGenerator.generateIndex(this.dbio.getTableRows()));
            } catch(Exception ex) {
                System.out.println("Ошибка получения данных их бд!");
            }

        }
        if(this.users.size() == 0) {
            System.out.println("Генерация из файлов");
            for(int i = 0; i < UsersGenerator.generateIndex(index); i++) {
                this.users.add(this.getUser());
            }
        }
    }

        static int generateIndex(int count) {
            return new Random().nextInt(count);
        }
    }
