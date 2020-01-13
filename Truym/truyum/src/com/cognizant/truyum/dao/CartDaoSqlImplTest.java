package com.cognizant.truyum.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import com.cognizant.truyum.model.Cart;

public class CartDaoSqlImplTest {
	
	public static void testAddCartItem() throws CartEmptyException, NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter user id");
		long userId = Long.parseLong(bf.readLine());
		System.out.println("Enter user menu item id");
		long menuItemId = Long.parseLong(bf.readLine());
		new CartDaoSqlImpl().addCartItem(userId, menuItemId);
	}
	
	public static void testGetAllCartItems() throws CartEmptyException, SQLException {
		CartDaoSqlImpl cartDaoSqlImpl = new CartDaoSqlImpl();
        Cart cart =  cartDaoSqlImpl.getAllCartItems(1L);
        System.out.println(cart.getMenuItemList());
        System.out.println("Total price= "+cart.getTotal());
	}
	
	public static void testRemoveCartItem() throws CartEmptyException, NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter user id");
		long userId = Long.parseLong(bf.readLine());
		System.out.println("Enter user menu item id");
		long menuItemId = Long.parseLong(bf.readLine());
		new CartDaoSqlImpl().removeCartItem(userId, menuItemId);
	}

	public static void main(String[] args) throws CartEmptyException, NumberFormatException, IOException, SQLException {
		System.out.println("User Added Cart List for CheckOut");
		testAddCartItem();
		System.out.println("Cart Items");
		testGetAllCartItems();
		System.out.println("Action Status");
		testRemoveCartItem();
		@SuppressWarnings("unused")
		Connection conection = ConnectionHandler.getConnection();
	}

}
