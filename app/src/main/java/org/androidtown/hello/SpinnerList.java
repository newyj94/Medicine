package org.androidtown.hello;
import android.content.Context;
import java.util.ArrayList;

public class SpinnerList {

    private Context context;
    private String[] items1 = {"정", "제", "류"};
    private String[] items2 = {"색", "빨", "파"};
    private String[] items3 = {"모양", "??", "??"};
    private String[] items4 = {"기호", "기", "호"};

    private ArrayList arrayList = new ArrayList();


    public ArrayList<String> getArrayList() {

        for (int i = 0; i < getSize(items1); i++) {
            arrayList.add(getitems(i));
        }
        return arrayList;

    }
    public String getitems(int indx) {

        return items1[indx];

    }

    public int getSize(String[] items) {

        return items.length;
    }
}

