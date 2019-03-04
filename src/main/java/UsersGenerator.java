import java.util.ArrayList;
import java.util.Random;

public class UsersGenerator {
    private SaveAsExel exel;
    private SaveAsPDF pdf;
    private InnGenerator inn;
    private ReadFiles data;

    private ArrayList<User> users = new ArrayList<User>();

    public UsersGenerator(SaveAsExel exel, InnGenerator inn, ReadFiles data, SaveAsPDF pdf) {
        this.exel 	= exel;
        this.inn 	= inn;
        this.data 	= data;
        this.pdf 	= pdf;
    }

    public void run() {
        this.setUsers(31 + (int) Math.random()*100);
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
            for(int i = 0; i < UsersGenerator.generateIndex(index)+30;  i++) {
            this.users.add(this.getUser());
        }
    }

    static int generateIndex(int count) {
        return new Random().nextInt(count);
}
}
