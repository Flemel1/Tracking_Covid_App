package com.example.coronavirus;

import java.util.Comparator;

import Model.Country;

public class CompareNegara implements Comparator<Country> {
    @Override
    public int compare(Country o1, Country o2) {
        if (o1.getAttributes().getConfirmed() == o2.getAttributes().getConfirmed())
            return 0;
        else if(o1.getAttributes().getConfirmed() < o2.getAttributes().getConfirmed())
            return 1;
        else {
            return -1;
        }
    }
}
