package com.arc.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.arc.entity.Comment;
import com.arc.entity.Region;
import com.arc.entity.User;
import com.arc.entity.Voter;
import com.arc.service.CommentService;
import com.arc.service.RegionService;
import com.arc.service.UserService;
import com.arc.service.VoterHistoryService;
import com.arc.service.VoterService;
import com.arc.util.Pager;
import com.google.common.base.Strings;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class VoterAction {
	@Resource
	RegionService regionService;
	@Resource
	VoterHistoryService voterHistoryService;
	@Resource
	VoterService voterService;
	@Resource
	CommentService commentService;
	@Resource
	UserService userService;
	@Resource
	Pager commentPager;
	private List<Comment> comments;
	private String userId;

	public void register() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = null;
		String name = ServletActionContext.getRequest().getParameter("n");
		String phone = ServletActionContext.getRequest().getParameter("p");
		String rid = ServletActionContext.getRequest().getParameter("rid");
		String uid = ServletActionContext.getRequest().getParameter("uid");
		JSONObject result = new JSONObject();
		try {
			out = response.getWriter();
			Object sessionObj = ActionContext.getContext().getSession()
					.get("voter");
			int regionId = Integer.parseInt(rid);
			int userId = Integer.parseInt(uid);
			Voter voter = null;
			if (sessionObj != null) {
				voter = (Voter) sessionObj;
			} else {
				voter = new Voter();
				voter.setName(name);
				voter.setPhone(phone);
				int vid = voterService.save(voter);
				voter.setId(vid);
				result.put("state", 9);
				result.put("message", "投票成功");
				result.put("nuid", true);
				ActionContext.getContext().getSession().put("voter", voter);
			}
			Region region = regionService.find(regionId);
			if (region.getState() != 1 && region.getState() != 2) {
				result.put("state", 1); // 结束
				result.put("message", "投票已经结束");
			} else {
				switch (region.getState()) {
				case 1: {
					int count = voterHistoryService.getVoterHistoryCount(
							regionId, voter.getId());
					if (count > 0) {
						result.put("state", 3); // 已经投过票
						result.put("message", "每个选区每天只能投一次票");
					} else {
						voterHistoryService.vote(regionId, voter.getId(),
								userId, 1);
						result.put("state", 0);
						result.put("message", "投票成功");
					}
				}
					break;
				case 2: {
					int count = voterHistoryService.getVoterHistoryCount(voter
							.getId());
					if (count > 0) {
						result.put("state", 3);// 已经投过票
						result.put("message", "每天只能投一次票");
					} else {
						voterHistoryService.vote(regionId, voter.getId(),
								userId, 2);
						result.put("state", 0);
						result.put("message", "投票成功");
					}
				}
					break;
				default:
					result.put("state", 1); // 结束
					result.put("message", "投票已经结束");
					break;
				}
			}
			out.write(result.toString());
		} catch (Exception e) {
			result.put("state", 2);
			result.put("message", "注册失败");
			out.write(result.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	public String comments() {
		commentPager.setPageSize("4");
		userId = ServletActionContext.getRequest().getParameter("u");
		String pageNo = ServletActionContext.getRequest().getParameter("pn");
		if (Strings.isNullOrEmpty(pageNo)) {
			commentPager.setCurrentPage("1");
		} else {
			commentPager.setCurrentPage(pageNo);
		}
		comments = commentService.findByPager(commentPager, userId);
		return "comments";
	}

	public void login() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = null;
		String email = ServletActionContext.getRequest().getParameter("e");
		String password = ServletActionContext.getRequest().getParameter("p");
		String checkCode = ServletActionContext.getRequest().getParameter("c");
		JSONObject result = new JSONObject();
		try {
			out = response.getWriter();
			if (!checkCode.equals(ActionContext.getContext().getSession()
					.get("randomCode"))) {
				result.put("state", 3);
				result.put("message", "验证码不正确");
			} else {
				Voter voter = voterService.getVoter(email, password);
				User user = userService.getUser(email, password);
				if (voter == null && user == null) {
					result.put("state", 1);
					result.put("message", "用户名或密码不正确，请重新输入！");
				} else {
					if (voter != null) {
						// voter.setType(0);
					} else {
						voter = new Voter();
						// voter.setEmail(user.getEmail());
						voter.setName(user.getNickname());
						voter.setId(user.getId());
						// voter.setType(1);
					}
					ActionContext.getContext().getSession().put("voter", voter);
					result.put("state", 0);
					result.put("message", "登录成功");
				}
			}
			out.write(result.toString());
		} catch (Exception e) {
			result.put("state", 2);
			result.put("message", "登录失败");
			out.write(result.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	public void checkState() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = null;
		JSONObject result = new JSONObject();
		try {
			out = response.getWriter();
			ActionContext request = ActionContext.getContext();
			Map session = request.getSession();
			Voter voter = (Voter) session.get("voter");
			if (voter == null) {
				result.put("state", 0);// 未登录
				result.put("name", "");
				result.put("message", "请先登录!");
			} else {
				result.put("name", voter.getName());
				result.put("state", 1);
			}
			out.write(result.toString());
		} catch (Exception e) {
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	public void comment() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = null;
		String content = ServletActionContext.getRequest().getParameter("c");
		String name = ServletActionContext.getRequest().getParameter("n");
		String uid = ServletActionContext.getRequest().getParameter("uid");
		JSONObject result = new JSONObject();
		try {
			out = response.getWriter();
			int userId = Integer.parseInt(uid);
			ActionContext request = ActionContext.getContext();
			Map session = request.getSession();
			Voter voter = (Voter) session.get("voter");
			if (voter == null) {
				voter = new Voter();
				voter.setName(name);
				voter.setPhone("123456");
				int id = voterService.save(voter);
				voter.setId(id);
			}
			session.put("voter", voter);
			if (content.length() > 40) {
				result.put("state", 3);
				result.put("message", "字数不能超过40个字符");
			} else {
				Comment comment = new Comment();
				comment.setComment(content);
				comment.setEmail(voter.getPhone());
				comment.setName(voter.getName());
				comment.setUserId(userId);
				commentService.save(comment);
				result.put("state", 0);
				result.put("message", "评论成功");
			}
			out.write(result.toString());
		} catch (Exception e) {
			result.put("state", 1); // 结束
			result.put("message", "投票失败");
			out.write(result.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	public void vote() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = null;
		String rid = ServletActionContext.getRequest().getParameter("rid");
		String uid = ServletActionContext.getRequest().getParameter("uid");
		JSONObject result = new JSONObject();
		try {
			out = response.getWriter();
			int regionId = Integer.parseInt(rid);
			int userId = Integer.parseInt(uid);
			ActionContext request = ActionContext.getContext();
			Map session = request.getSession();
			Voter voter = (Voter) session.get("voter");
			if (voter == null) {
				result.put("state", 2);// 未登录
				result.put("message", "请先登录!");
			} else {
				Region region = regionService.find(regionId);
				if (region.getState() != 1 && region.getState() != 2) {
					result.put("state", 1); // 结束
					result.put("message", "投票已经结束");
				} else {
					switch (region.getState()) {
					case 1: {
						int count = voterHistoryService.getVoterHistoryCount(
								regionId, voter.getId());
						if (count > 0) {
							result.put("state", 3); // 已经投过票
							result.put("message", "每个选区每天只能投一次票");
						} else {
							voterHistoryService.vote(regionId, voter.getId(),
									userId, 1);
							result.put("state", 0);
							result.put("message", "投票成功");
						}
					}
						break;
					case 2: {
						int count = voterHistoryService
								.getVoterHistoryCount(voter.getId());
						if (count > 0) {
							result.put("state", 3);// 已经投过票
							result.put("message", "每天只能投一次票");
						} else {
							voterHistoryService.vote(regionId, voter.getId(),
									userId, 2);
							result.put("state", 0);
							result.put("message", "投票成功");
						}
					}
						break;
					default:
						result.put("state", 1); // 结束
						result.put("message", "投票已经结束");
						break;
					}
				}
			}
			out.write(result.toString());
		} catch (Exception e) {
			result.put("state", 1); // 结束
			result.put("message", "投票失败");
			out.write(result.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Pager getCommentPager() {
		return commentPager;
	}

	public void setCommentPager(Pager pager) {
		this.commentPager = pager;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
