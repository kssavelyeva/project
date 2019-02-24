// Класс для генерации ИНН физического лица из 77 региона
public class InnGenerator {
    private long data;

    public InnGenerator() {
        int arr [] = new int [12];
        arr [0] = 7;
        arr [1] = 7;
        arr [2] = (int) (Math.random()*(5));
        for (int i=3; i<9; i++)
        {
            arr [i] = (int) (Math.random()*(9));

        }

        arr [10] = (((arr[0] * 7 + arr[1] * 2 + arr[2] * 4 + arr[3]*10 + arr[4] * 3 + arr[5]*5 + arr[6]*9 + arr[7]*4+
                arr[8] * 6 + arr[9]*8) % 11) % 10);
        arr [11] = ((arr[0] * 3 + arr[1] * 7 + arr[2] * 2 + arr[3]*4 + arr[4] * 10 + arr[5]*3 + arr[6]*5 + arr[7]*9+
                arr[8] * 4 + arr[9] * 6 + arr [10] * 8) % 11) % 10;
        this.join(arr);
    }

    public void join(int [] data) {
        long result = 0;
        for (int i = data.length-1, n = 0; i>=0; i--,n++) {
            result += data[n] * (long)Math.pow(10, i);
        }
        this.data = result;
    }

    public long getInn() {
        return this.data;
    }
}