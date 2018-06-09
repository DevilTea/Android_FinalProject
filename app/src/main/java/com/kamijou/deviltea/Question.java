package com.kamijou.deviltea;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Question implements Serializable {

    private int kanaType;
    private KanaData answer;
    private ArrayList<KanaData> selections;
    private int incorrectCount = 0;

    public Question(int kanaType, ArrayList<KanaData> allKanaDatas, KanaData answer) {
        this.kanaType = kanaType;
        this.answer = answer;
        this.selections = new ArrayList<>();
        selections.add(answer);
        ArrayList<KanaData> tempList = new ArrayList<>(allKanaDatas);
        if(tempList.size() >= 4) {
            tempList.remove(answer);
        }
        for(int i = 0; i < 3; i++) {
            Collections.shuffle(tempList);
            int random = (int) Math.floor(Math.random() * tempList.size());
            selections.add(tempList.get(random));
            if(3 - i < tempList.size()) {
                tempList.remove(tempList.get(random));
            }
        }
        Collections.shuffle(selections);
    }

    public KanaData getAnswer() {
        return answer;
    }

    public ArrayList<KanaData> getSelections() {
        return selections;
    }

    public int getKanaType() {
        return kanaType;
    }

    public int getIncorrectCount() {
        return incorrectCount;
    }

    public void increaseIncorrectCount() {
        incorrectCount++;
    }
}
