import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;


public class DBIO {
    private final String getUserSql = "Select * from persons ps join address ads on ps.address_id = ads.id limit ?, 30";
    private final String setAddresSql = "INSERT INTO address (postcode, country, region, city, street, house, flat)"
            + "Values (?, ?, ?, ?, ?, ?, ?)";
    private final String getPersonSql = "INSERT INTO persons (surname, name, middlename, birthday, gender, inn, address_id)"
            + "Values (?, ?, ?, ?, ?, ?, ?)";
    private Connection connection;

    private ArrayList<User> usersList = new ArrayList<User>();

    public DBIO() {
        try {
            this.connection = this.getConnection(ReadFiles.getProperties());
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
            System.out.println("Ошибка работы с базой данных");
        }
    }

    private Connection getConnection(Properties props) throws SQLException {
        return DriverManager.getConnection(
                props.getProperty("url"),
                props.getProperty("username"),
                props.getProperty("password")
        );
    }

    public ArrayList<User> getUserData(int start) throws SQLException {
        this.parseSqlData(this.getSqlData(start));
        return this.usersList;
    }

    public int getTableRows() throws SQLException {
        int rows = 0;
        ResultSet res = this.connection.createStatement().executeQuery("select count(*) from persons");
        while (res.next()) {
            rows = res.getInt(1);
        }
        return rows;
    }

    private ResultSet getSqlData(int start) throws SQLException {
        PreparedStatement preparedStatemen =  this.connection.prepareStatement(this.getUserSql);
        preparedStatemen.setInt(1, start > this.getTableRows() - 30 ? start - (start - (this.getTableRows() - 30)) : start);
        return preparedStatemen.executeQuery();
    }

    private void parseSqlData(ResultSet resultSet)  throws SQLException{
        while(resultSet.next()){
            this.usersList.add(
                    (User) new UserFromApi()
                            .setName(resultSet.getString("name"))
                            .setFam(resultSet.getString("middlename"))
                            .setOtch(resultSet.getString("surname"))
                            .setCountry(resultSet.getString("country"))
                            .setRegion(resultSet.getString("region"))
                            .setCity(resultSet.getString("city"))
                            .setStreet(resultSet.getString("street"))
                            .setPostCode(Integer.parseInt(resultSet.getString("postcode")))
                            .setDate(resultSet.getDate("birthday"))
                            .setSex(resultSet.getString("gender"))
                            .setInnCode(Long.parseLong(resultSet.getString("inn")))
                            .setHomeNumber(resultSet.getInt("house"))
            );
        }
        this.connection.close();
    }

    public void saveUserToDb(ArrayList<User> users) {
        try {
            for(User user : users) {
                this.addPerson(user);
            }
            this.connection.close();
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
            System.out.println("Ошибка создания данных !");
        }

    }

    private int addAddress(User user) throws SQLException {
        int id = 0;
        String generatedKeys[]= {"id"};
        PreparedStatement preparedStatement = this.connection.prepareStatement(this.setAddresSql, generatedKeys);
        preparedStatement.setString(1, String.valueOf(user.getPostCode()));
        preparedStatement.setString(2, user.getCountry());
        preparedStatement.setString(3, user.getRegion());
        preparedStatement.setString(4, user.getCity());
        preparedStatement.setString(5, user.getStreet());
        preparedStatement.setString(6, String.valueOf(user.getHomeNumber()));
        preparedStatement.setString(7, String.valueOf(user.getRoomNumber()));
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        while(rs.next()){
            id = rs.getInt(1);
        }
        return id;
    }

    private void addPerson(User user) throws SQLException {

        ResultSet result = this.connection.createStatement().executeQuery(
                "Select * from persons where name =\"" + user.getName() + "\""
                        + " AND surname = \"" + user.getOtch() + "\""
                        +" AND middlename = \"" + user.getFam() + "\""
        );

        int id = result.next() ? result.getInt("id") : 0;

        if (id != 0) {
            this.updDate(user, result);
        } else {
            int addresId = this.addAddress(user);
            PreparedStatement preparedStatement = this.connection.prepareStatement(this.getPersonSql);
            preparedStatement.setString(1, user.getOtch());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getFam());
            preparedStatement.setDate(4, user.getDateToDb());
            preparedStatement.setString(5, user.getSex());
            preparedStatement.setString(6, String.valueOf(user.getInn()));
            preparedStatement.setString(7, String.valueOf(addresId));
            preparedStatement.executeUpdate();
        }
    }

    private void updDate(User user, ResultSet dateFromDb) throws SQLException{
        PreparedStatement address = this.connection.prepareStatement("UPDATE address set postcode = '?',"
                + " country = '?', region = '?', city = '?', street = '?', house = '?', flat = '?' where id = ?");
        address.setString(1, String.valueOf(user.getPostCode()));
        address.setString(2, user.getCountry());
        address.setString(3, user.getRegion());
        address.setString(4, user.getCity());
        address.setString(5, user.getStreet());
        address.setString(6, String.valueOf(user.getHomeNumber()));
        address.setString(7, String.valueOf(user.getRoomNumber()));
        address.setString(8, dateFromDb.getString("address_id"));
        address.executeUpdate();

        PreparedStatement person = this.connection.prepareStatement("UPDATE persons set surname = '?',"
                + " name = '?', middlename = '?', birthday = '?', gender = '?', inn = '?' where id = ?");
        person.setString(1, user.getOtch());
        person.setString(2, user.getName());
        person.setString(3, user.getFam());
        person.setDate(4, user.getDateToDb());
        person.setString(5, user.getSex());
        person.setString(6, String.valueOf(user.getInn()));
        person.setString(7, dateFromDb.getString("id"));
        person.executeUpdate();

    }
}
