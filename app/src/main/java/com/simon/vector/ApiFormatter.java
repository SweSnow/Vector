package com.simon.vector;

public class ApiFormatter {

    public final static String ACCESS_TOKEN = "access_token=4e5ecd9c1fda50d086f911abde31d445553e0d6bdda1909f1d102d9f39f3a1b3";
    public final static String Prefix = "https://api.dribbble.com/v1/";

    private StringBuilder stringBuilder = new StringBuilder();
    private int length = 0;

    public ApiFormatter() {
        stringBuilder.append(Prefix);
    }

    public ApiFormatter addQuery(String str) {
        stringBuilder.append(str);
        return this;
    }

    public ApiFormatter addOption(String str) {
        length++;

        if (length == 1) {
            stringBuilder.append("?");
        } else {
            stringBuilder.append("&");
        }

        stringBuilder.append(str);

        return this;
    }

    public String toString() {
        length = 0;

        return stringBuilder.toString();
    }

}
