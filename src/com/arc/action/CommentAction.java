package com.arc.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.arc.entity.Comment;
import com.arc.service.CommentService;

@Controller
@Scope("prototype")
public class CommentAction extends BaseAction {

	private Comment comment;
	private String name;
	private String email;
	@Resource
	CommentService commentService;

	public CommentAction() {
		this.comment = new Comment();
	}

	public String list() {
		list = commentService.findByPager(pager, name, email);
		if (list.isEmpty())
			result = Result.FAIL;
		return LIST;
	}

	public String edit() {
		comment = commentService.find(comment.getId());
		return EDIT;
	}

	public String add() {
		return ADD;
	}

	public String update() {
		commentService.update(comment);
		return list();
	}

	public String delete() {
		commentService.delete(comment.getId());
		return list();
	}

	public String save() {
		commentService.save(comment);
		return list();
	}

	public String query() {
		pager.setCurrentPage("1");
		return list();
	}

	public String view() {
		comment = commentService.find(comment.getId());
		return "view";
	}

	public Comment getComment() {
		return comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
