/**
 * 
 */
package com.great.cms.db.dao;

import com.great.cms.db.entity.User;

/**
 * @author ziniapc
 *
 */
public interface UserDao extends GenericDao<User, Integer> {


	
	
	
	public User findUserByName(String Name);
	
	public User findByEmail(String Email);
	
	
	
	
	

	
}
