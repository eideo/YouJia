package com.arc.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arc.dao.CommentDAO;
import com.arc.entity.Comment;
import com.arc.util.Pager;

@Service
public class CommentService {
	@Resource
	CommentDAO commentDAO;

	public void save(Comment comment) {
		commentDAO.save(comment);
	}

	public void update(Comment comment) {
		commentDAO.update(comment);
	}

	public void delete(int id) {
		commentDAO.delete(id);
	}

	public Comment find(int id) {
		return commentDAO.find(id);
	}

	public List<Comment> findByPager(Pager pager, String name, String email) {
		return commentDAO.findByPager(pager, name, email);
	}

	public List<Comment> findByPager(Pager pager, String userId) {
		return commentDAO.findByPager(pager, userId);
	}
}
