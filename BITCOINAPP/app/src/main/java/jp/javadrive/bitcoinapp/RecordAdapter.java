package jp.javadrive.bitcoinapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecordAdapter extends ArrayAdapter {


    // 見易さのために定義。普段は直接 getView で指定する。
    private static final int resource = R.layout.record_listview;

    public RecordAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // super.getView() は 呼ばない(カスタムビューにしているため)
        View view;

        // テンプレート処理。
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(resource, parent, false);
        } else {
            view = convertView;
        }

        // データをgetItemで取る
        BitCoin bitCoin = (BitCoin) getItem(position);

        // カスタムビューの場合はViewが確実にあるtry-catch は不要ためか。
        TextView tv1 = (TextView) view.findViewById(R.id.JPYBTC);
        TextView tv2 = (TextView) view.findViewById(R.id.BTCSize);

        //List.xmlに表示する情報をセット
        tv1.setText("￥"+bitCoin.getBpi()+"/BTC");
        tv2.setText(bitCoin.getSize()+"BTC");


        return view;
    }

    // 設定されている CustomListItem の ArrayList を返す。
// 縦横切替などでデータを移行するために使う。
    public ArrayList<BitCoin> getItemList() {
        // 今回は Bundle#putParcelableArrayList() を使うことを想定する。
        // 必要に応じて Bundle#putSparseParcelableArray() を使ってもいい。

        int size = getCount();
        ArrayList<BitCoin> weatherdayList = new ArrayList<BitCoin>(size);
        for (int index = 0; index < size; index++) {
            weatherdayList.add((BitCoin) getItem(index));
        }
        return weatherdayList;
    }

    // Bundleから復元するときに必要になるはず。
    public void addAll(ArrayList<BitCoin> parcelableArrayList) {
// 強制でキャスト。落ちる場合は、設計か実装が間違っている。
        @SuppressWarnings("unchecked")
        ArrayList<BitCoin> bitCoinList = (ArrayList<BitCoin>) parcelableArrayList;
        super.addAll(bitCoinList);
    }

    public void add(float bpi,double size) {
        BitCoin bitCoin = new BitCoin();
        bitCoin.setBpi(bpi);
        bitCoin.setSize(size);
        super.add(bitCoin);
    }

    // 削除
    public void remove(int index) {
        if (index < 0 || index >= getCount()) {
            return;
        }
        remove(getItem(index));
    }
}

