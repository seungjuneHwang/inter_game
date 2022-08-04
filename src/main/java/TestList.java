import java.util.ArrayList;
import java.util.List;

public class TestList {
    public static void main(String[] args) {
        ArrayList<String> sList = new ArrayList<>();
        sList.add("문자열1");
        sList.add("문자열2");
        sList.add("문자열3");
        sList.add("문자열4");
        System.out.println(sList.get(0));
        System.out.println(sList.get(1));
        System.out.println(sList.get(2));
        System.out.println(sList.get(3));
        System.out.println("리스트 갯수: " + sList.size());
        for (int i = 0; i < sList.size(); i++) {
            System.out.println(sList.get(i));
        }
        // for each
        for (String str : sList) {
            System.out.println(str);
        }

        List<Integer> iList = new ArrayList<>();
        iList.add(1);
        iList.add(2);
        iList.add(3);

        for (int n : iList) {
            System.out.println(n);
        }
    }
}
