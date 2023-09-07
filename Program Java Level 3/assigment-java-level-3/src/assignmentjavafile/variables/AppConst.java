package assignmentjavafile.variables;

public class AppConst {
    /* Files */
    public static String fileLocation = "src/assignmentjavafile/files/";
    public static String csvNameFile = "data_sekolah.csv";
    public static String modusNameFile = "data_sekolah_modus.txt";
    public static String meanMedianNameFile = "data_sekolah_mean_median.txt";

    /* Menu Page */
    public static String headerApp = """
            ---------------------------------------------------------
            Aplikasi Pengolah Nilai Siswa
            ---------------------------------------------------------
            """;
    public static String giveSourceFileMessage = "Letakkan file csv dengan nama file dengan nama file data_sekolah di bawah package/folder " +
            "assignmentjavafile.files";
    public static String mainMenuMessage = """
            
            Pilih Menu :
            1. Buat txt file untuk menampilkan modus
            2. Buat txt file untuk menampilkan nilai rata-rata, median
            3. Buat kedua file
            0. Exit
            """;
    public static String generatedFileMessage = """
            File telah dibuat pada [direktori-file]
            Silakan dicek.
            """;
    public static String generatedFileMenuMessage = """
            
            0. Exit
            1. Kembali ke menu utama
            """;
    public static String selectMenu = "\nPilih Menu : ";
    public static String mainPageMessage = headerApp.concat(giveSourceFileMessage).concat(mainMenuMessage).concat(selectMenu);
    public static String generatedPageMessage = headerApp.concat(generatedFileMessage).concat(generatedFileMenuMessage).concat(selectMenu);
}
