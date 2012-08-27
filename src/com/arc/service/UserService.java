package com.arc.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arc.dao.UserDAO;
import com.arc.entity.User;
import com.arc.util.Pager;

@Service
public class UserService {
	@Resource
	UserDAO userDAO;

	public int save(User user) {
		return userDAO.save(user);
	}

	public void update(User user) {
		userDAO.update(user);
	}

	public void delete(int id) {
		userDAO.delete(id);
	}

	public User find(int id) {
		return userDAO.findById(id);
	}

	public List<User> findByPager(Pager pager, String regionId,
			String username, String valid) {
		return userDAO.findByPager(pager, regionId, username, valid);
	}

	public List<User> findByPager(Pager pager, int regionId) {
		return userDAO.findByPager(pager, regionId);
	}

	public User getPreviousUser(int currentUserId, int regionId) {
		return userDAO.getPreviousUser(currentUserId, regionId);
	}

	public User getNextUserId(int currentUserId, int regionId) {
		return userDAO.findNextUser(currentUserId, regionId);
	}

	public List<User> findLastestUsers(String regionId) {
		return userDAO.findLastestUsers(regionId);
	}

	public boolean exists(String email) {
		return userDAO.exists(email);
	}

	public User getUser(String email, String password) {
		return userDAO.getUser(email, password);
	}
}
