package com.example.orderplacer.Utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.orderplacer.model.Order;
import com.example.orderplacer.model.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    public static final String ORDER_DATABASE = "od";
    private final String ORDER_MANAGEMENT_TABLE = "order_management_table";

    private final String id = "id";
    private final String customer_Name = "customer_name";
    private final String order_Details = "order_details";
    private final String order_completed = "order_completed";

    private final SQLiteDatabase mLiteDatabase;

    public OrderDao(SQLiteDatabase liteDatabase) {
        mLiteDatabase = liteDatabase;
    }

    private void createOrderRecordDataBase() {
        String query = "CREATE TABLE IF NOT EXISTS " + ORDER_MANAGEMENT_TABLE + "(" +
                id + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                customer_Name + " VARCHAR(100) NOT NULL," +
                order_Details + " VARCHAR(5) NOT NULL," +
                order_completed + " VARCHAR(5) NOT NULL" + ")";
        mLiteDatabase.execSQL(query);
    }

    // Insert - C
    public void saveOrdersData(Order order){
        String query = "INSERT INTO "+ORDER_MANAGEMENT_TABLE+" ("+customer_Name+", "+order_Details+", "+order_completed+") VALUES ('"+order.customerName+"', '"+order.orderDetails+"', '0')";
        mLiteDatabase.execSQL(query);
    }

    // READ - R
    public List<OrderDetail> getAllOrders(String filter) {
        String query = "SELECT * FROM " + ORDER_MANAGEMENT_TABLE + " WHERE " + customer_Name + " LIKE '%" + filter + "%' OR " + order_Details + " LIKE '%" + filter + "%'";
        Cursor cursor = mLiteDatabase.rawQuery(query, null);
        List<OrderDetail> orderDetails = new ArrayList<>();
        while (cursor.moveToNext()) {
            String orderId = cursor.getString(0);
            String customerName = cursor.getString(1);
            String orderDetail = cursor.getString(2);
            boolean isCompleted = cursor.getString(3).equals("1");
            orderDetails.add(new OrderDetail(orderId, customerName, orderDetail, isCompleted));
        }
        return orderDetails;
    }

    // READ
    public OrderDetail getOrderDetail(String id) {
        Cursor cursor = mLiteDatabase.rawQuery("SELECT * FROM " + ORDER_MANAGEMENT_TABLE + " WHERE id='"+id+"'", null);
        cursor.moveToFirst();
        return new OrderDetail(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3).equals("1")
        );
    }

    public void deleteOrder(OrderDetail details){
        String query = "DELETE FROM " + ORDER_MANAGEMENT_TABLE + " WHERE id= '"+ details.getId()+"'";
        mLiteDatabase.execSQL(query);
    }

    public void updateOrderCompletion(String id, boolean isCompleted) {
        String query = "UPDATE " + ORDER_MANAGEMENT_TABLE + " SET " +
                order_completed + "='" + (isCompleted ? '1' : '0') + "' " +
                "WHERE " + this.id + "='" + id + "'";
        Log.e("UpdateQuery", "updateOrderCompletion: "+ query);
        mLiteDatabase.execSQL(query);
    }

    public void updateOrder(OrderDetail detail){
        String query = "UPDATE " + ORDER_MANAGEMENT_TABLE + " SET " +
                customer_Name + "='" + detail.customerName  + "', " +
                order_Details + "='" + detail.orderDetails  + "' " +
                "WHERE " + id + "='"+ detail.getId() +"'";
        mLiteDatabase.execSQL(query);
    }

    public static OrderDao buildWith(SQLiteDatabase sqLiteDatabase) {
        OrderDao data = new OrderDao(sqLiteDatabase);
        data.createOrderRecordDataBase();
        return data;
    }
}
