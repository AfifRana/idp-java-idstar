package assignmentjavafile;

import assignmentjavafile.utility.CSVReader;
import assignmentjavafile.utility.DataUtility;
import assignmentjavafile.utility.TextFileWriter;
import assignmentjavafile.variables.AppConst;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        boolean interacting = true;
        Scanner scanner = new Scanner(System.in);
        int mainMenu = 0;
        int childMenu = 0;
        List<List<String>> dataFromCSV = new ArrayList<>();

        do {
            try {
                printText(AppConst.mainPageMessage);
                mainMenu = scanner.nextInt();

                switch (mainMenu) {
                    case 1 :
                        if (dataFromCSV.isEmpty()) {
                            dataFromCSV = CSVReader.getDataFromCSV(AppConst.fileLocation.concat(AppConst.csvNameFile));
                        }
                        modusUsecase(dataFromCSV, AppConst.fileLocation.concat(AppConst.modusNameFile));
                        printText(AppConst.generatedPageMessage);
                        childMenu = scanner.nextInt();
                        break;
                    case 2 :
                        if (dataFromCSV.isEmpty()) {
                            dataFromCSV = CSVReader.getDataFromCSV(AppConst.fileLocation.concat(AppConst.csvNameFile));
                        }
                        meanMedianUsecase(dataFromCSV, AppConst.fileLocation.concat(AppConst.meanMedianNameFile));
                        printText(AppConst.generatedPageMessage);
                        childMenu = scanner.nextInt();
                        break;
                    case 3 :
                        if (dataFromCSV.isEmpty()) {
                            dataFromCSV = CSVReader.getDataFromCSV(AppConst.fileLocation.concat(AppConst.csvNameFile));
                        }
                        modusUsecase(dataFromCSV, AppConst.fileLocation.concat(AppConst.modusNameFile));
                        meanMedianUsecase(dataFromCSV, AppConst.fileLocation.concat(AppConst.meanMedianNameFile));
                        printText(AppConst.generatedPageMessage);
                        childMenu = scanner.nextInt();
                        break;
                    case 0 :
                        interacting = false;
                        childMenu = 0;
                        break;
                }

                if (childMenu == 0) interacting = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (interacting);
    }

    static void printText(String text) {
        System.out.println(text);
    }

    static void modusUsecase(List<List<String>> dataFromCSV, String writeFileLocationData) {
        if (dataFromCSV.isEmpty()) throw new IllegalArgumentException("List dataFromCSV is empty!");

        List<String> listDataString = DataUtility.list2DTo1D(dataFromCSV);
        List<Integer> listDataInteger = DataUtility.listStringToInteger(listDataString);
        Map<Integer, Integer> groupDataMap = DataUtility.groupingDataFromList(listDataInteger);

        Integer modus = DataUtility.findModusFromMap(groupDataMap);
        List<String> linesOfData = new ArrayList<>(List.of("Modus : ".concat(String.valueOf(modus)),
                "Count : ".concat(String.valueOf(listDataInteger.size())), "Value | Frequency"));
        linesOfData.addAll(DataUtility.formatLineFromMap(groupDataMap));

        TextFileWriter.writeMultipleLines(linesOfData, writeFileLocationData);
    }

    static void meanMedianUsecase(List<List<String>> dataFromCSV, String writeFileLocationData) {
        if (dataFromCSV.isEmpty()) throw new IllegalArgumentException("List dataFromCSV is empty!");

        List<String> listDataString = DataUtility.list2DTo1D(dataFromCSV);
        List<Integer> listDataInteger = DataUtility.listStringToInteger(listDataString);
        Map<Integer, Integer> groupDataMap = DataUtility.groupingDataFromList(listDataInteger);

        Integer mean = DataUtility.findMeanFromList(groupDataMap);
        Integer median = DataUtility.findMedianFromList(listDataInteger);
        List<String> linesOfData = new ArrayList<>(List.of("Mean : ".concat(String.valueOf(mean)), "Median : ".concat(String.valueOf(median)),
                "Count : ".concat(String.valueOf(listDataInteger.size())), "Value | Frequency"));
        linesOfData.addAll(DataUtility.formatLineFromMap(groupDataMap));
        linesOfData.add("List Data Integer");
        Collections.sort(listDataInteger);
        linesOfData.add(String.join(", ", DataUtility.listIntegerToString(listDataInteger)));

        TextFileWriter.writeMultipleLines(linesOfData, writeFileLocationData);
    }
}