import java.util.ArrayList;
import java.util.List;

public class Ex68Main {
    public static void main(String[] args) {
        String[] test = {
                "What", "must", "be", "acknowledgment", "shall", "be"
        };
        int widthTest = 16;
        System.out.println(fullJustify(test, widthTest));


    }

    public static List<String> fullJustify(String[] test, int maxWidth) {

        List<String> finalList = new ArrayList<>();

        ArrayList<Integer> tempIndexList = lineCounter(test, maxWidth);
        ArrayList<Integer> nOfSpaces = new ArrayList<>();
        ArrayList<Integer> indexList = new ArrayList<>();
        int tempNuOfSpaces;
        int tempIndex = -1;
        for (int i = 0; i < tempIndexList.size(); i += 2) {
            Integer index = tempIndexList.get(i);
            tempNuOfSpaces = index - tempIndex - 1;
            tempIndex = index;
            nOfSpaces.add(tempNuOfSpaces);
            indexList.add(index);
        }


        ArrayList<Integer> nOfTotalChar = new ArrayList<>();

        for (int i = 1; i < tempIndexList.size(); i += 2) {
            Integer index = tempIndexList.get(i);
            nOfTotalChar.add(index);
        }
        int tempParcialSpaces;
        ArrayList<Integer> nOfParcialSpaces = new ArrayList<>();
        int currentWord = 0;


        for (int i = 0; i < indexList.size(); i++) {
            StringBuilder line = new StringBuilder();
            if (nOfSpaces.get(i) == 0) {
                line.append(test[currentWord]);

                int tempLastSpaces = maxWidth - line.length();
                line.append(" ".repeat(Math.max(0, tempLastSpaces)));
                finalList.add(line.toString());
                currentWord++;
                continue;
            }
            int spacesAdded = 0;
            int l = (maxWidth - nOfTotalChar.get(i)) % nOfSpaces.get(i);
            tempParcialSpaces = (maxWidth - nOfTotalChar.get(i)) / nOfSpaces.get(i);
            for (int j = 0; j <= nOfSpaces.get(i); j++) {

                nOfParcialSpaces.add(tempParcialSpaces);
                line.append(test[currentWord]);

                if (l > 0) {
                    line.append(" ");
                    l--;
                }
                for (int k = -1; k < tempParcialSpaces; k++) {

                    if (spacesAdded == nOfSpaces.get(i)) {
                        break;
                    }
                    line.append(" ");
                }
                spacesAdded++;
                currentWord++;
            }
            finalList.add(line.toString());
        }
        StringBuilder line = new StringBuilder();
        for (int i = currentWord; i < test.length; i++) {
            if (i == test.length - 1) {
                line.append(test[currentWord]);
                continue;
            }
            line.append(test[currentWord]);
            line.append(" ");

            currentWord++;
        }
        int tempLastSpaces = maxWidth - line.length();
        line.append(" ".repeat(Math.max(0, tempLastSpaces)));
        finalList.add(line.toString());

        return finalList;
    }

    public static ArrayList<Integer> lineCounter(String[] line, int maxWidth) {
        ArrayList<Integer> indexes = new ArrayList<>();
        int currentChar = 0;
        int index;
        for (int i = 0; i < line.length; i++) {
            currentChar += (line[i].length() + 1);
            if (currentChar > maxWidth + 1) {
                currentChar -= (line[i].length() + 2);
                index = i - 1;
                indexes.add(index);
                indexes.add(currentChar);
                currentChar = 0;
                i--;
            }

        }

        return indexes;
    }

}
