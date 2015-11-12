package com.example.renancardoso.invasion;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by renan on 10/11/15.
 */
public class CalculateInvasion {
    private int mM;
    private int mN;
    private String[] mMs;
    private String[] mNs;
    private CharSequence mDate;
    private String method;
    final private String[] DAYS = {"31",  "1",  "2",  "3",  "4",  "5",  "6",  "7",  "8",  "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
    final private String[] MONTHS = {"dezembro", "janeiro", "fevereiro", "março", "abril", "maio",
            "junho", "julho", "agosto", "setembro", "outubro", "novembro"};

    public CalculateInvasion(int M,int N,String[] Ms, String[] Ns) {
        mM = M;
        mN = N;
        mMs = Ms;
        mNs = Ns;

        if (defineMethod().equals("A")) mDate = methodA();
        else                            mDate = methodB();

    }

    public CharSequence getDate() {
        return mDate;
    }

    public String getMethod() {
        return method;
    }

    private String defineMethod() {
        int sum = 0;
        for (String s : mMs) {
            sum += Integer.parseInt(s);
        }

        if ((sumDigits(sum) % 2) == 0) method = "A";
        else                           method = "B";

        return method;
    }

    private int sumDigits(int number) {
        int n = number;
        int sum = 0;
        while (n > 0) {
            int lastDigit = n % 10;
            n = n / 10;
            int penultDigit = n % 10;
            sum += lastDigit + penultDigit;
            n = n / 10;
        }
        return sum;
    }

    private CharSequence methodA() {
        int S = 0;
        ArrayList<Integer> ns = new ArrayList<>();

        for (String s: mNs) {
            ns.add(Integer.parseInt(s));
        }

        Collections.sort(ns);

        for (int i = 0; i < mN; i++) {
            int n = ns.get(i);
            int produto = n * (i + 1);
            S += produto;
        }
        return formatDate(S);
    }

    private CharSequence formatDate(int S) {
        int day   = S % 31;
        int month = S % 12;
        if ((day == 0) && ((month == 2) || (month == 4) ||
                           (month == 6) || (month == 9) || (month == 11))) {
            day++;
            month++;
        }

        if (day == 30 && month == 2) {
            day = 1;
            month++;
        }
        return DAYS[day] + " de " + MONTHS[month];
    }

    private CharSequence methodB() {
        //
        int count = 1;
        int[] partial = new int[mN];

        for (int i = 0; i < mNs.length; i++) {
            count += i;
            partial[i] = Integer.parseInt(mNs[i]) + count;
        }

        int S = sumLastTwoDigits(partial);
        return formatDate(S);
    }

    private int sumLastTwoDigits(int[] numbers) {
        int sum = 0;
        for (int number: numbers) {
            int lastDigit = number % 10;
            int penultDigit = (number / 10) % 10;
            sum += lastDigit + penultDigit;
        }
        return sum;
    }
}

