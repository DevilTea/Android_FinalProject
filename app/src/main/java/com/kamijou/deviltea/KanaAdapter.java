package com.kamijou.deviltea;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;

public class KanaAdapter extends RecyclerView.Adapter {

    private ArrayList<KanaData> kanaDataArrayList1;
    private ArrayList<KanaData> kanaDataArrayList2;
    private ArrayList<Integer> examPositions = new ArrayList<>();
    private int type;
    private Context context;
    private boolean isExam;

    public static class ViewHolderRowType1 extends RecyclerView.ViewHolder {
        public ArrayList<Button> kanaButtons = new ArrayList<Button>();
        public CheckBox checkBox;
        public ViewHolderRowType1(View itemView) {
            super(itemView);
            kanaButtons.add((Button) itemView.findViewById(R.id.bt_row_1_kana_1));
            kanaButtons.add((Button) itemView.findViewById(R.id.bt_row_1_kana_2));
            kanaButtons.add((Button) itemView.findViewById(R.id.bt_row_1_kana_3));
            kanaButtons.add((Button) itemView.findViewById(R.id.bt_row_1_kana_4));
            kanaButtons.add((Button) itemView.findViewById(R.id.bt_row_1_kana_5));
            checkBox = (CheckBox) itemView.findViewById(R.id.cb_kana_row_1);
        }
    }

    public static class ViewHolderRowType2 extends RecyclerView.ViewHolder {
        public ArrayList<Button> kanaButtons = new ArrayList<Button>();
        public CheckBox checkBox;
        public ViewHolderRowType2(View itemView) {
            super(itemView);
            kanaButtons.add((Button) itemView.findViewById(R.id.bt_row_2_kana_1));
            kanaButtons.add((Button) itemView.findViewById(R.id.bt_row_2_kana_2));
            kanaButtons.add((Button) itemView.findViewById(R.id.bt_row_2_kana_3));
            checkBox = (CheckBox) itemView.findViewById(R.id.cb_kana_row_2);
        }
    }

    public KanaAdapter(Context context, int type, ArrayList<KanaData> kanaDataArrayList1, ArrayList<KanaData> kanaDataArrayList2, boolean isExam) {
        this.context = context;
        this.type = type;
        this.kanaDataArrayList1 = kanaDataArrayList1;
        this.kanaDataArrayList2 = kanaDataArrayList2;
        this.isExam = isExam;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kana_row_1, parent, false);
            ViewHolderRowType1 vh = new ViewHolderRowType1(view);
            return vh;
        } else if(viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kana_row_2, parent, false);
            ViewHolderRowType2 vh = new ViewHolderRowType2(view);
            return vh;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == 0) {
            for(int i = 0; i < 5; i++) {
                Button kanaButton = ((ViewHolderRowType1) holder).kanaButtons.get(i);
                KanaData kanaData = kanaDataArrayList1.get(position * 5 + i);
                if(type == Constant.TYPE_KATAKANA) {
                    kanaButton.getBackground().setTint(context.getColor(R.color.colorKatakanaButtonBackground));
                } else if(type == Constant.TYPE_ALLKANA) {
                    kanaButton.getBackground().setTint(context.getColor(R.color.colorAllkanaButtonBackground));
                }
                kanaButton.setText(kanaData.getKana(type));
                kanaButton.setOnClickListener(getKanaButtonOnClickListener(kanaData));
                CheckBox checkBox = ((ViewHolderRowType1) holder).checkBox;
                if(isExam) {
                    checkBox.setVisibility(View.VISIBLE);
                    checkBox.setOnCheckedChangeListener(getCheckBoxOnCheckedChangeListener(position));
                    checkBox.setChecked(examPositions.contains(position));
                } else {
                    checkBox.setVisibility(View.GONE);
                }
            }
        } else if(getItemViewType(position) == 1) {
            for(int i = 0; i < 3; i++) {
                Button kanaButton = ((ViewHolderRowType2) holder).kanaButtons.get(i);
                KanaData kanaData = kanaDataArrayList2.get((position - (kanaDataArrayList1.size() / 5)) * 3 + i);
                if(type == Constant.TYPE_KATAKANA) {
                    kanaButton.getBackground().setTint(context.getColor(R.color.colorKatakanaButtonBackground));
                } else if(type == Constant.TYPE_ALLKANA) {
                    kanaButton.getBackground().setTint(context.getColor(R.color.colorAllkanaButtonBackground));
                }
                kanaButton.setText(kanaData.getKana(type));
                kanaButton.setOnClickListener(getKanaButtonOnClickListener(kanaData));
                CheckBox checkBox = ((ViewHolderRowType2) holder).checkBox;
                if(isExam) {
                    checkBox.setVisibility(View.VISIBLE);
                    checkBox.setOnCheckedChangeListener(getCheckBoxOnCheckedChangeListener(position));
                    checkBox.setChecked(examPositions.contains(position));
                } else {
                    checkBox.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return (kanaDataArrayList1.size() / 5) + (kanaDataArrayList2.size() / 3);
    }

    @Override
    public int getItemViewType(int position) {
        int val = kanaDataArrayList1.size() / 5;
        if(position < val) {
            return 0;
        } else if(position >= val) {
            return 1;
        }
        return -1;
    }

    public View.OnClickListener getKanaButtonOnClickListener(final KanaData kanaData) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kanaData.getPinyin().isEmpty()) {
                    return;
                }
                showKanaInfoDialog(kanaData);
            }
        };
        return  onClickListener;
    }

    public void showKanaInfoDialog(KanaData kanaData) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_kana_info, null);
        ((TextView) view.findViewById(R.id.tv_kana)).setText(kanaData.getKana(type));
        ((TextView) view.findViewById(R.id.tv_hiragana)).setText(kanaData.getHiragana());
        ((TextView) view.findViewById(R.id.tv_katakana)).setText(kanaData.getKatakana());
        ((TextView) view.findViewById(R.id.tv_pinyin)).setText(kanaData.getPinyin());
        AlertDialog dialog = new AlertDialog.Builder(context).setView(view).create();
        dialog.show();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        dialog.getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = (int) (displayMetrics.widthPixels * 0.6f);
        dialog.getWindow().setAttributes(layoutParams);
    }

    public CompoundButton.OnCheckedChangeListener getCheckBoxOnCheckedChangeListener(final int position) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked && !examPositions.contains(position)) {
                examPositions.add(position);
            } else if(!isChecked && examPositions.contains(position)) {
                examPositions.remove((Object) position);
            }
            examPositions.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
            }
        };
        return onCheckedChangeListener;
    }

    public ArrayList<Integer> getExamPositions() {
        return examPositions;
    }

    public void setAllCheckboxes(final boolean checked) {
        if(checked) {
            examPositions.clear();
            for(int i = 0; i < getItemCount(); i++) {
                examPositions.add(i);
            }
        } else {
            examPositions.clear();
        }
        notifyDataSetChanged();
    }
}
