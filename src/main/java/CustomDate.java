import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Класс для работы с датами, установления возраста

public class CustomDate {
    LocalDate date;

    public CustomDate() {
        this.createDate();
    }

    public void createDate(){
        int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2001, 1, 1).toEpochDay();
        long randomDay = minDay + UsersGenerator.generateIndex(maxDay - minDay);
        this.date = LocalDate.ofEpochDay(randomDay);
    }

    public String getFormatDate() {
        return  this.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public  int getAge() {
        return (int) Math.abs(this.date.compareTo(LocalDate.now()));
    }
}