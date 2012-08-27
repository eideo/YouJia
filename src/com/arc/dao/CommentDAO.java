package com.arc.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.arc.entity.Comment;
import com.arc.entity.DataBean;
import com.arc.util.Pager;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

@Repository
public class CommentDAO {
	@Resource
	Dbo dbo;

	public void save(Comment comment) {
		String sql = "insert into comments(user_id,name,email,create_time,comment) values(?,?,?,now(),?)";
		dbo.update(sql, comment.getUserId(), comment.getName(),
				comment.getEmail(), comment.getComment());
	}

	public void update(Comment comment) {

	}

	public void delete(int id) {
		String sql = "delete from comments where id=?";
		dbo.update(sql, id);
	}

	public Comment find(int id) {
		String sql = "select * from comments where id=?";
		DataBean bean = this.dbo.queryForMapBean(sql, id);
		if (bean == null)
			return null;
		else
			return mapper(bean);
	}

	public List<Comment> findByPager(Pager pager, String name, String email) {
		String condition = "";
		if (!Strings.isNullOrEmpty(name)) {
			condition = " where name like '%" + name + "%'";
		}
		if (!Strings.isNullOrEmpty(email)) {
			if (Strings.isNullOrEmpty(condition)) {
				condition = " where email like '%" + email + "%'";
			} else {
				condition += " and email  like '%" + email + "%'";
			}
		}
		int totalSize = dbo.queryForInt("select count(*) from comments"
				+ condition);
		pager.setTotalSize(totalSize);

		List<DataBean> tmpList = dbo.queryForList("select * from comments "
				+ condition + " order by id desc limit ?,?",
				(pager.getCurrentPage() - 1) * pager.getPageSize(),
				pager.getPageSize());
		List<Comment> comments = Lists.newArrayList();

		if (tmpList == null)
			return comments;
		for (DataBean bean : tmpList) {
			comments.add(mapper(bean));
		}
		return comments;
	}

	public Comment mapper(DataBean bean) {
		Comment comment = new Comment();
		if (bean.containsKey("id"))
			comment.setId(bean.getInt("id"));
		if (bean.containsKey("user_id"))
			comment.setUserId(bean.getInt("user_id"));
		if (bean.containsKey("name"))
			comment.setName(bean.getString("name"));
		if (bean.containsKey("email"))
			comment.setEmail(bean.getString("email"));
		if (bean.containsKey("create_time"))
			comment.setCreateTime(bean.getDate("create_time"));
		if (bean.containsKey("comment"))
			comment.setComment(bean.getString("comment"));
		return comment;
	}

	public List<Comment> findByPager(Pager pager, String userId) {
		String condition = " where user_id=?";
		int totalSize = dbo.queryForInt("select count(*) from comments"
				+ condition, userId);
		pager.setTotalSize(totalSize);

		List<DataBean> tmpList = dbo.queryForList(
				"select name,comment from comments " + condition
						+ " order by id desc limit ?,?", userId,
				(pager.getCurrentPage() - 1) * pager.getPageSize(),
				pager.getPageSize());
		List<Comment> comments = Lists.newArrayList();

		if (tmpList == null)
			return comments;
		for (DataBean bean : tmpList) {
			comments.add(mapper(bean));
		}
		return comments;
	}

}
