package com.jit.mayy.mytopnews.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jit.mayy.mytopnews.db.UserDatabaseHelper;
import com.jit.mayy.mytopnews.domain.User;

public class UserService {
	private UserDatabaseHelper dbHelper;

	public UserService(Context context){
		dbHelper=new UserDatabaseHelper(context);
	}
	
	//登陆用
	public boolean login(String username,String password){
		SQLiteDatabase sdb=dbHelper.getReadableDatabase();
		String sql="select * from user where username=? and password=?";
		Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});		
		if(cursor.moveToFirst()==true){
			cursor.close();
			return true;
		}
		return false;
	}

	//注册用
	public boolean register(User user){
		SQLiteDatabase sdb=dbHelper.getReadableDatabase();
		String sql="insert into user(username,password,age,sex) values(?,?,?,?)";
		Object obj[]={user.getUsername(),user.getPassword(),user.getAge(),user.getSex()};
		sdb.execSQL(sql, obj);	
		return true;
	}
}
