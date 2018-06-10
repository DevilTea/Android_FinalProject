package com.kamijou.deviltea;

import android.media.MediaPlayer;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.Consumer;

public class KanaData implements Serializable {

    private static ArrayList<KanaData> allKanaList1;
    private static ArrayList<KanaData> allKanaList2;

    private String pinyin;
    private String hiragana;
    private String katakana;
    private int soundResId;

    public KanaData(String pinyin, String hiragana, String katakana, int resId) {
        this.pinyin = pinyin;
        this.hiragana = hiragana;
        this.katakana = katakana;
        this.soundResId = resId;
    }

    public String getKana(int type) {
        if(type == Constant.TYPE_HIRAGANA) {
            return hiragana;
        } else if(type == Constant.TYPE_KATAKANA) {
            return katakana;
        } else if(type == Constant.TYPE_ALLKANA) {
            return hiragana + "\n" + katakana;
        }
        return "???";
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getHiragana() {
        return hiragana;
    }

    public String getKatakana() {
        return katakana;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    public void setKatakana(String katakana) {
        this.katakana = katakana;
    }

    public int getResId() {
        return soundResId;
    }

    public static final ArrayList<KanaData> getAllKanaList1() {
        if(allKanaList1 == null) {
            allKanaList1 = new ArrayList<>();
            final String[] pinyin_1 = {
                "a", "i", "u", "e", "o",
                "ka", "ki", "ku", "ke", "ko",
                "sa", "shi", "su", "se", "so",
                "ta", "chi", "tsu", "te", "to",
                "na", "ni", "nu", "ne", "no",
                "ha", "hi", "fu", "he", "ho",
                "ma", "mi", "mu", "me", "mo",
                "ya", "", "yu", "", "yo",
                "ra", "ri", "ru", "re", "ro",
                "wa", "", "", "", "o(wo)",
                "n", "", "", "", "",
                "ga", "gi", "gu", "ge", "go",
                "za", "ji", "zu", "ze", "zo",
                "da", "ji(di)", "du", "de", "do",
                "ba", "bi", "bu", "be", "bo",
                "pa", "pi", "pu", "pe", "po"
            };
            final String[] hiragana_1 = {
                    "あ", "い", "う", "え", "お",
                    "か", "き", "く", "け", "こ",
                    "さ", "し", "す", "せ", "そ",
                    "た", "ち", "つ", "て", "と",
                    "な", "に", "ぬ", "ね", "の",
                    "は", "ひ", "ふ", "へ", "ほ",
                    "ま", "み", "む", "め", "も",
                    "や", "", "ゆ", "", "よ",
                    "ら", "り", "る", "れ", "ろ",
                    "わ", "", "", "", "を",
                    "ん", "", "", "", "",
                    "が", "ぎ", "ぐ", "げ", "ご",
                    "ざ", "じ", "ず", "ぜ", "ぞ",
                    "だ", "ぢ", "づ", "で", "ど",
                    "ば", "び", "ぶ", "べ", "ぼ",
                    "ぱ", "ぴ", "ぷ", "ぺ", "ぽ"
            };
            final String[] katakana_1 = {
                "ア", "ィ", "ウ", "エ", "オ",
                "カ", "キ", "ク", "ケ", "コ",
                "サ", "ㇱ", "ス", "セ", "ソ",
                "タ", "チ", "ッ", "テ", "ㇳ",
                "ナ", "ニ", "ヌ", "ネ", "ノ",
                "ハ", "ヒ", "フ", "ヘ", "ホ",
                "マ", "ミ", "ム", "メ", "モ",
                "ヤ", "", "ユ", "", "ヨ",
                "ラ", "リ", "ル", "レ", "ロ",
                "ワ", "", "", "", "ヲ",
                "ン", "", "", "", "",
                "ガ", "ギ", "グ", "ゲ", "ゴ",
                "ザ", "ジ", "ズ", "ゼ", "ゾ",
                "ダ", "ヂ", "ヅ", "デ", "ド",
                "バ", "ビ", "ブ", "ベ", "ボ",
                "パ", "ピ", "プ", "ペ", "ポ",
            };
            final int[] resId_1 = {
                R.raw.kana_a, R.raw.kana_i, R.raw.kana_u, R.raw.kana_e, R.raw.kana_o,
                R.raw.kana_ka, R.raw.kana_ki, R.raw.kana_ku, R.raw.kana_ke, R.raw.kana_ko,
                R.raw.kana_sa, R.raw.kana_shi, R.raw.kana_su, R.raw.kana_se, R.raw.kana_so,
                R.raw.kana_ta, R.raw.kana_chi, R.raw.kana_tsu, R.raw.kana_te, R.raw.kana_to,
                R.raw.kana_na, R.raw.kana_ni, R.raw.kana_nu, R.raw.kana_ne, R.raw.kana_no,
                R.raw.kana_ha, R.raw.kana_hi, R.raw.kana_fu, R.raw.kana_he, R.raw.kana_ho,
                R.raw.kana_ma, R.raw.kana_mi, R.raw.kana_mu, R.raw.kana_me, R.raw.kana_mo,
                R.raw.kana_ya, 0, R.raw.kana_yu, 0, R.raw.kana_yo,
                R.raw.kana_ra, R.raw.kana_ri, R.raw.kana_ru, R.raw.kana_re, R.raw.kana_ro,
                R.raw.kana_wa, 0, 0, 0, R.raw.kana_wo,
                R.raw.kana_n, 0, 0, 0, 0,
                R.raw.kana_ga, R.raw.kana_gi, R.raw.kana_gu, R.raw.kana_ge, R.raw.kana_go,
                R.raw.kana_za, R.raw.kana_ji, R.raw.kana_zu, R.raw.kana_ze, R.raw.kana_zo,
                R.raw.kana_da, R.raw.kana_ji, R.raw.kana_zu, R.raw.kana_de, R.raw.kana_do,
                R.raw.kana_ba, R.raw.kana_bi, R.raw.kana_bu, R.raw.kana_be, R.raw.kana_bo,
                R.raw.kana_pa, R.raw.kana_pi, R.raw.kana_pu, R.raw.kana_pe, R.raw.kana_po
            };
            for(int i = 0; i < pinyin_1.length; i++) {
                allKanaList1.add(new KanaData(pinyin_1[i], hiragana_1[i], katakana_1[i], resId_1[i]));
            }
        }
        return allKanaList1;
    }

    public static final ArrayList<KanaData> getAllKanaList2() {
        if(allKanaList2 == null) {
            allKanaList2 = new ArrayList<>();
            final String[] pinyin_2 = {
                "kya", "kyu", "kyo",
                "sha", "shu", "sho",
                "cha", "chu", "cho",
                "nya", "nyu", "nyo",
                "hya", "hyu", "hyo",
                "mya", "myu", "myo",
                "rya", "ryu", "ryo",
                "gya", "gyu", "gyo",
                "ja", "ju", "jo",
                "bya", "byu", "byo",
                "pya", "pyu", "pyo"
            };
            final String[] hiragana_2 = {
                "きゃ", "きゅ", "きょ",
                "しゃ", "しゅ", "しょ",
                "ちゃ", "ちゅ", "ちょ",
                "にゃ", "にゅ", "にょ",
                "ひゃ", "ひゅ", "ひょ",
                "みゃ", "みゅ", "みょ",
                "りゃ", "りゅ", "りょ",
                "ぎゃ", "ぎゅ", "ぎょ",
                "じゃ", "じゅ", "じょ",
                "びゃ", "びゅ", "びょ",
                "ぴゃ", "ぴゅ", "ぴょ"
            };
            final String[] katakana_2 = {
                "キャ", "ジュ", "キョ",
                "シャ", "シュ", "ショ",
                "チャ", "チュ", "チョ",
                "ニャ", "ニュ", "ニョ",
                "ヒャ", "ヒュ", "ヒョ",
                "ミャ", "ミュ", "ミョ",
                "リャ", "リュ", "リョ",
                "ギャ", "ギュ", "ギョ",
                "ジャ", "ジュ", "ジョ",
                "ビャ", "ビュ", "ビョ",
                "ピャ", "ピュ", "ピョ"
            };
            final int[] resId_2 = {
                R.raw.kana_kya, R.raw.kana_kyu, R.raw.kana_kyo,
                R.raw.kana_sha, R.raw.kana_shu, R.raw.kana_sho,
                R.raw.kana_cha, R.raw.kana_chu, R.raw.kana_cho,
                R.raw.kana_nya, R.raw.kana_nyu, R.raw.kana_nyo,
                R.raw.kana_hya, R.raw.kana_hyu, R.raw.kana_hyo,
                R.raw.kana_mya, R.raw.kana_myu, R.raw.kana_myo,
                R.raw.kana_rya, R.raw.kana_ryu, R.raw.kana_ryo,
                R.raw.kana_gya, R.raw.kana_gyu, R.raw.kana_gyo,
                R.raw.kana_ja, R.raw.kana_ju, R.raw.kana_jo,
                R.raw.kana_bya, R.raw.kana_byu, R.raw.kana_byo,
                R.raw.kana_pya, R.raw.kana_pyu, R.raw.kana_pyo
            };
            for(int i = 0; i < pinyin_2.length; i++) {
                allKanaList2.add(new KanaData(pinyin_2[i], hiragana_2[i], katakana_2[i], resId_2[i]));
            }
        }
        return allKanaList2;
    }

    public static final ArrayList<KanaData> getKanaDatasByPosition(int position) {
        int dividePosition = getAllKanaList1().size() / 5;
        ArrayList<KanaData> kanaDatas;
        if(position < dividePosition) {
            kanaDatas = new ArrayList<>(getAllKanaList1().subList(position * 5, position * 5 + 5));
        } else {
            position -= dividePosition;
            kanaDatas = new ArrayList<>(getAllKanaList2().subList(position * 3, position * 3 + 3));
        }
        return kanaDatas;
    }

    public static final ArrayList<KanaData> getKanaDatasByPositions(ArrayList<Integer> positions) {
        final ArrayList<KanaData> kanaDatas = new ArrayList<>();
        positions.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer position) {
                kanaDatas.addAll(getKanaDatasByPosition(position));
            }
        });
        return kanaDatas;
    }
}
