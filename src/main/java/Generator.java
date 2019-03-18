public class Generator {
    public static void main(String[] args) {
        new UsersGenerator(new SaveAsExel(), new ReadFiles(), new SaveAsPDF(), new DBIO()).run();
    }
}
