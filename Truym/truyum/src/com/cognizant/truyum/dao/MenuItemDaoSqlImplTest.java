package com.cognizant.truyum.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoSqlImplTest {

	public static void testGetMenuItemListAdmin() {

//		Connection connection = ConnectionHandler.getConnection();
//		System.out.println("Connection->" + connection);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat df = new DecimalFormat("#.00");

		ArrayList<MenuItem> menuItemList = new MenuItemDaoSqlImpl().getMenuItemListAdmin();
		String active, freedelivery;

		System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "Id", "Name", "Price", "Active", "DateOFLaunch",
				"Category", "FreeDelivery");

		for (MenuItem menuItem : menuItemList) {
			if (menuItem.getActive() == true) {
				active = "yes";
			} else {
				active = "no";
			}
			if (menuItem.getFreeDelivery() == true) {
				freedelivery = "yes";
			} else {
				freedelivery = "no";
			}
			System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", menuItem.getId(), menuItem.getName(),
					df.format(menuItem.getPrice()), active, sdf.format(menuItem.getDateOfLaunch()),
					menuItem.getCategory(), freedelivery);
		}

	}

	public static void testGetMenuItemListCustomer() throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat df = new DecimalFormat("#.00");
		ArrayList<MenuItem> menuItemList = new MenuItemDaoSqlImpl().getMenuItemListCustomer();
		System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "ID", "NAME", "PRICE","ACTIVE","DATE OF LAUNCH", "CATEGORY", "FREE_DELIVERY");
		System.out.println();
		String freedelivery;
		String active;
		for (MenuItem menuItem : menuItemList) {
			
			if (menuItem.getActive()) {
				active = "yes";
			} else {
				active = "no";
			}
			if (menuItem.getFreeDelivery()) {
				freedelivery = "yes";
			} else {
				freedelivery = "no";
			}
			System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", menuItem.getId(), menuItem.getName(),
					df.format(menuItem.getPrice()),active, sdf.format(menuItem.getDateOfLaunch()),menuItem.getCategory(), freedelivery);
			System.out.println();
		}
	}

	public static void testModifyMenuItem() throws NumberFormatException, IOException {
		
		MenuItemDaoSqlImpl menuItemDaoSqlImpl = new MenuItemDaoSqlImpl();
		System.out.println("Enter the menu item id to be modified");
		MenuItem menuItem = new MenuItem(1l,"Briyani",150.00f,true,new DateUtil().convertToDate("12/12/2019"),"Spicy",true);
		menuItemDaoSqlImpl.modifyMenuItem(menuItem);
		

	}

	public static void main(String[] args) throws SQLException, NumberFormatException, IOException {
		System.out.println("Item List for Admin");
		testGetMenuItemListAdmin();
		System.out.println("Item List for Customer");
		testGetMenuItemListCustomer();
		testModifyMenuItem();
		System.out.println("Item List for Admin After Modification ");
		testGetMenuItemListAdmin();

	}

}