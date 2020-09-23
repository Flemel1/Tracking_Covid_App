package com.example.coronavirus;

import java.util.Comparator;

import Model.Attributes;
import Model.Corona;

public class CompareProvinsi implements Comparator<Attributes> {

    @Override
    public int compare(Attributes o1, Attributes o2) {
        if (o1.getAttributes().getKasus_Posi() == o2.getAttributes().getKasus_Posi())
            return 0;
        else if (o1.getAttributes().getKasus_Posi() < o2.getAttributes().getKasus_Posi())
            return 1;
        else
            return -1;
    }
}
