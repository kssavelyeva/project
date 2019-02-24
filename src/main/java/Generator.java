public class Generator {
    public static void main(String[] args) {
        new UsersGenerator(new SaveAsExel(), new InnGenerator(), new ReadFiles(), new SaveAsPDF()).run();
    }
}
