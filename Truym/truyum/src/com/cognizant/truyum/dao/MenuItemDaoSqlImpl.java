package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoSqlImpl implements MenuItemDao {
	public static final String MENU_ITEM_DETAILS = "select * from menu_item";
	public static final String UPDATE_MENU_ITEM = "update menu_item set me_name=?,me_price=?,me_active=?,me_date_of_launch=?,me_category=?,me_free_delivery=? where me_id=?";
	public static final String GET_ALL_MENUITEM_CUSTOMER="select * from menu_item  where me_active='1' and me_date_of_launch < (select CURDATE())"  ;
	public static final String GET_MENUITEM="select * from menu_item where me_id=?";
	public ArrayList<MenuItem> getMenuItemListAdmin() {
		ArrayList<MenuItem> menuItemList = new ArrayList<>();
		Connection connection = ConnectionHandler.getConnection();
		// A object can then be used to efficiently execute this statement multiple
		// times
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(MENU_ITEM_DETAILS);
			// A ResultSet object maintains a cursor pointing to its current row of data
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				MenuItem me = new MenuItem();
				me.setId(resultSet.getLong("me_id"));
				me.setName(resultSet.getString("me_name"));
				me.setPrice(resultSet.getFloat("me_price"));
				me.setActive(resultSet.getString("me_active").equals("1"));
				me.setDateOfLaunch(resultSet.getDate("me_date_of_launch"));
				me.setCategory(resultSet.getString("me_category"));
				me.setFreeDelivery(resultSet.getString("me_free_delivery").equals("1"));
				menuItemList.add(me);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {

			}
		}
		return menuItemList;
	}


	@Override
    public ArrayList<MenuItem> getMenuItemListCustomer() {
		ArrayList<MenuItem> menuItemList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
		Connection connection = ConnectionHandler.getConnection();
        ResultSet resultSet = null;
       try { 
        	preparedStatement = connection.prepareStatement(GET_ALL_MENUITEM_CUSTOMER);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	MenuItem me = new MenuItem();
				me.setId(resultSet.getLong("me_id"));
				me.setName(resultSet.getString("me_name"));
				me.setPrice(resultSet.getFloat("me_price"));
				me.setActive(resultSet.getString("me_active").equals("1"));
				me.setDateOfLaunch(resultSet.getDate("me_date_of_launch"));
				me.setCategory(resultSet.getString("me_category"));
				me.setFreeDelivery(resultSet.getString("me_free_delivery").equals("1"));
                menuItemList.add(me);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                preparedStatement.close();
                if(connection!=null)
                connection.close();
            } catch (SQLException e) {
            }
        }
        return menuItemList;
	}

    @Override
    public void modifyMenuItem(MenuItem menuItem) {
        PreparedStatement preparedStatement = null;
		Connection connection = ConnectionHandler.getConnection();

        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_MENU_ITEM);
            preparedStatement.setString(1, menuItem.getName());
            preparedStatement.setFloat(2, menuItem.getPrice());
            preparedStatement.setBoolean(3, menuItem.getActive());
            preparedStatement.setDate(4, DateUtil.convertToSqlDate(menuItem.getDateOfLaunch())); 
            preparedStatement.setString(5, menuItem.getCategory());
            preparedStatement.setBoolean(6, menuItem.getFreeDelivery());
            preparedStatement.setLong(7, menuItem.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet!=null)
                    resultSet.close();
                if(preparedStatement!=null)
                preparedStatement.close();
                if(connection!=null)
                connection.close();
            } catch (SQLException e) {
            }
        }
    }



	@Override
	public MenuItem getMenuItem(long menuItemId) {
        Connection connection = ConnectionHandler.getConnection();
        ArrayList<MenuItem> menuItemList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        MenuItem menuItem = null;
        try {
            preparedStatement = connection.prepareStatement(GET_MENUITEM);
            preparedStatement.setLong(1, menuItemId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 menuItem = new MenuItem();
                 menuItem.setId(resultSet.getLong("me_id"));
                 menuItem.setName(resultSet.getString("me_name"));
                 menuItem.setPrice(resultSet.getFloat("me_price"));
                menuItem.setActive(resultSet.getString("me_active").equals("1"));
                menuItem.setDateOfLaunch(resultSet.getDate("me_date_of_launch"));
                 menuItem.setCategory(resultSet.getString("me_category"));
            menuItem.setFreeDelivery(resultSet.getString("me_free_delivery").equals("1"));
                 menuItemList.add(menuItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                 if (resultSet != null)
                      resultSet.close();
                 if (preparedStatement != null)
                      preparedStatement.close();
                 if (connection != null)
                      connection.close();
            } catch (SQLException e) {
            }
        }
        System.out.println(menuItem);
        return menuItem;
   }


	


}

