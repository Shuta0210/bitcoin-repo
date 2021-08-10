package jp.javadrive.bitcoinapp;

import java.util.ArrayList;

public class Response {

    private String message;
    private ArrayList<BitCoin> list;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public ArrayList<BitCoin> getList() {
        return list;
    }

    public void setList(ArrayList<BitCoin> list) {
        this.list = list;
    }

}
