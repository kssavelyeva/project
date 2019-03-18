public class User {
    protected String 		name;
    protected String 		fam;
    protected String 		otch;
    protected String 		city;
    protected String 		country;
    protected String 		region;
    protected String 		street;
    protected boolean 	sex;
    protected CustomDate 	date;
    protected long		inn;
    protected int 		postcode;
    protected int 		homeNumber;
    protected int 		roomNumber;

    User setName(boolean sex, ReadFiles data) {
        this.name = sex
                ? data.getListElm("maleName")
                : data.getListElm("womanName");
        return this;
    }
    User setFam(boolean sex, ReadFiles data) {
        this.fam = sex
                ? data.getListElm("maleFam")
                : data.getListElm("womanFam");
        return this;
    }
    User setOtch(boolean sex, ReadFiles data) {
        this.otch = sex
                ? data.getListElm("maleOtc")
                : data.getListElm("womanOtc");
        return this;
    }
    User setCity(ReadFiles data) {
        this.city = data.getListElm("city");
        return this;
    }
    User setCountry(ReadFiles data) {
        this.country = data.getListElm("country");
        return this;
    }
    User setRegion(ReadFiles data) {
        this.region = data.getListElm("region")	;
        return this;
    }
    User setStreet(ReadFiles data) {
        this.street = data.getListElm("street");
        return this;
    }
    User setRoomNumber() {
        this.roomNumber =  UsersGenerator.generateIndex(100);
        return this;
    }
    User setHomeNumber() {
        this.homeNumber = UsersGenerator.generateIndex(100);
        return this;
    }
    User setPostCode() {
        this.postcode = (int)(Math.random() * 100001) + 100000;
        return this;
    }
    User setInnCode() {
        this.inn = new InnGenerator().getInn();
        return this;
    }
    User setDate() {
        this.date = new CustomDate();
        return this;
    }

    User setSex(boolean sex) {
        this.sex = sex;
        return this;
    }

    public String toString(){
        return
                this.fam + ' ' +
                        this.name + ' ' +
                        this.otch + ' ' +
                        this.country + ' ' +
                        this.region + ' ' +
                        this.street + ' ' +
                        this.homeNumber + ' ' +
                        this.roomNumber + ' ' +
                        this.postcode + ' ' +
                        this.inn  + ' ' +
                        this.date.getFormatDate()  + ' ' +
                        this.date.getAge();
    }

    String getName() {
        return this.name;
    }
    String getFam() {
        return this.fam;
    }
    String getOtch() {
        return this.otch;
    }
    String getCity() {
        return this.city;
    }
    String getCountry() {
        return this.country;
    }
    String getRegion() {
        return this.region;
    }
    String getStreet() {
        return this.street;
    }
    String getSex() {
        return this.sex ? "М" : "Ж";
    }
    int getAge() {
        return this.date.getAge();
    }
    String getDate() {
        return this.date.getFormatDate();
    }
    long getInn() {
        return this.inn;
    }
    int getPostCode() {
        return this.postcode;
    }
    int getHomeNumber() {
        return this.homeNumber;
    }
    int getRoomNumber() {
        return this.roomNumber;
    }
    java.sql.Date getDateToDb() {
        return this.date.toDb();
    }
}
