package com.example.orderplacer.Utils;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.orderplacer.model.OrderDetails;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    public static final String ORDER_DATABASE = "od";
    private final String ORDER_MANAGEMENT_TABLE = "ORDER_MANAGEMENT_TABLE";

    private final String id = "id";
    private final String customer_Name = "customer_Name";
    private final String order_Details = "order_Details";

    private final SQLiteDatabase mLiteDatabase;

    public OrderManager(SQLiteDatabase liteDatabase) {
        mLiteDatabase = liteDatabase;
    }

    private void createOrderRecordDataBase(){
        String query = "CREATE TABLE IF NOT EXISTS " + ORDER_MANAGEMENT_TABLE + "(" +
                id + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                customer_Name + " VARCHAR(100) NOT NULL," +
                order_Details + " VARCHAR(5) NOT NULL" + ")";
        mLiteDatabase.execSQL(query);
    }
    public void saveOrdersData(OrderDetails orderDetails){
        String query = "INSERT INTO " + ORDER_MANAGEMENT_TABLE + "(" +
                customer_Name + "," + order_Details + ")" +
                "VALUES ('" + orderDetails.customerName+"' , '" +
                orderDetails.orderDetails + "')";
        mLiteDatabase.execSQL(query);
    }

//    public OrderDetails getOrderDetail(String id) {
//        Cursor cursor = mLiteDatabase.rawQuery("SELECT * FROM " + ORDER_MANAGEMENT_TABLE + " WHERE id='"+id+"'", null);
//        cursor.moveToFirst();
//        OrderDetails orderDetails = new OrderDetails(cursor.getString(1), cursor.getString(2), cursor.getString(3));
//        orderDetails.setId(cursor.getString(0));
//        return orderDetails;
//    }
    public List<OrderDetails> getOrderHistoryData(){
        Cursor cursor = mLiteDatabase.rawQuery("SELECT * FROM " + ORDER_MANAGEMENT_TABLE, null);
        List<OrderDetails> orderDetails = new ArrayList<>();
        while (cursor.moveToNext()) {
            orderDetails.add(new OrderDetails(cursor.getString(1), cursor.getString(2)));
        }
        return orderDetails;
    }

    public void dltData(OrderDetails details){
        String query = "DELETE FROM " + ORDER_MANAGEMENT_TABLE + " WHERE id= '"+ details.getId()+"'";
        mLiteDatabase.execSQL(query);
    }
    public void updateData(OrderDetails details){
        String query = "UPDATE " + ORDER_MANAGEMENT_TABLE + "SET" +
                customer_Name + "='" + details.customerName  + "', " +
                order_Details + "='" + details.orderDetails  + "', " +
                "WHERE " + id + "='"+details.getId()+"'";
        mLiteDatabase.execSQL(query);
    }
    public static OrderManager buildWith(SQLiteDatabase sqLiteDatabase) {
        OrderManager data = new OrderManager(sqLiteDatabase);
        data.createOrderRecordDataBase();
        return data;
    }
}
