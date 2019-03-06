import com.fasterxml.jackson.databind.JsonNode;

public class UserFromApi extends User{
    UserFromApi setName(String name) {
        this.name = name;
        return this;
    }
    UserFromApi setFam(String fam) {
        this.fam = fam;
        return this;

    }
    UserFromApi setOtch(String otch) {
        this.otch = otch;
        return this;
    }
    UserFromApi setCity(String city) {
        this.city = city;
        return this;
    }
    UserFromApi setCountry(String country) {
        this.country = country;
        return this;
    }
    UserFromApi setRegion(String region) {
        this.region = region;
        return this;
    }
    UserFromApi setStreet(String street) {
        this.street = street;
        return this;
    }
    UserFromApi setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }
    UserFromApi setHomeNumber(int homeNumber) {
        this.homeNumber = homeNumber;
        return this;
    }
    UserFromApi setPostCode(int postcode) {
        this.postcode = postcode;
        return this;
    }
    UserFromApi setInnCode() {
        this.inn = new InnGenerator().getInn();
        return this;
    }
    UserFromApi setDate(String data) {
        this.date = new CustomDate(data);
        return this;
    }

    UserFromApi setSex(String sex) {
        this.sex = sex.equals("male") ? true : false;
        return this;
    }

    static User getUser(JsonNode data) {
        return (User) new UserFromApi()
                .setName(data.get("name").get("first").asText())
                .setFam(data.get("name").get("last").asText())
                .setCountry(data.get("location").get("state").asText())
                .setOtch("-")
                .setCity(data.get("location").get("city").asText())
                .setStreet(data.get("location").get("street").asText())
                .setPostCode(data.get("location").get("postcode").asInt())
                .setDate(data.get("dob").get("date").asText())
                .setSex(data.get("gender").asText())
                .setInnCode();
    }
}