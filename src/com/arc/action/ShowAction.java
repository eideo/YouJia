package com.arc.action;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.arc.entity.Region;
import com.arc.entity.User;
import com.arc.service.RegionService;
import com.arc.service.UserService;
import com.arc.service.VoterHistoryService;
import com.arc.service.VoterService;
import com.arc.util.ImageTool;
import com.arc.util.Pager;
import com.google.common.base.Strings;
import com.google.common.io.Files;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ShowAction {
	@Resource
	UserService userService;
	@Resource
	VoterHistoryService voterHistoryService;
	@Resource
	RegionService regionService;
	@Resource
	VoterService voterService;
	private File qqfile;
	private String upfileResult;
	@Resource
	Pager userPager;
	private int regionId;
	private List<User> entities;

	public String show() {
		return "show";
	}

	public String index() {
		return "index";
	}

	public String entities() {
		String pageNo = ServletActionContext.getRequest().getParameter("pn");

		userPager.setPageSize("12");
		if (Strings.isNullOrEmpty(pageNo)) {
			userPager.setCurrentPage("1");
		} else {
			userPager.setCurrentPage(pageNo);
		}
		entities = userService.findByPager(userPager, regionId);
		return "entities";
	}

	public void userPicture() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = null;
		String userId = ServletActionContext.getRequest().getParameter("u");
		String regionId = ServletActionContext.getRequest().getParameter("r");
		JSONObject result = new JSONObject();
		try {
			out = response.getWriter();
			User previousUser = userService.getPreviousUser(
					Integer.parseInt(userId), Integer.parseInt(regionId));
			User nextUser = userService.getNextUserId(Integer.parseInt(userId),
					Integer.parseInt(regionId));
			JSONObject previous = new JSONObject();
			if (previousUser != null) {
				previous.put("id", previousUser.getId());
				previous.put("pic", previousUser.getPic1());
				result.put("pre", previous);
			}
			JSONObject next = new JSONObject();
			if (nextUser != null) {
				next.put("id", nextUser.getId());
				next.put("pic", nextUser.getPic1());
				result.put("next", next);
			}
			out.write(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

	}

	public String upload() {
		JSONObject result = new JSONObject();
		try {
			String realPath = ServletActionContext.getServletContext()
					.getRealPath("/");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String datetime = sdf.format(new Date());
			realPath = realPath + "/users/" + datetime.substring(0, 8) + "/";
			File dir = new File(realPath);
			String ext = "jpg";
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String fileName = realPath + datetime.substring(8) + "_tmpbig."
					+ ext;
			File tmpImage = new File(fileName);
			Files.copy(qqfile, tmpImage);
			cropBigPic(fileName);
			cropMediumPic(fileName);
			cropSmallPic(fileName);
			tmpImage.delete();
			result.put("state", 1);
			result.put("pic", "users/" + datetime.substring(0, 8) + "/"
					+ datetime.substring(8) + "_big." + ext);
			upfileResult = result.toString();
		} catch (Exception e) {
			result.put("state", 0);
			upfileResult = result.toString();
		}
		return "upload";
	}

	public void register() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = null;
		String email = ServletActionContext.getRequest().getParameter("e");
		String password = ServletActionContext.getRequest().getParameter("p");
		String nickname = ServletActionContext.getRequest().getParameter("nn");
		String regionId = ServletActionContext.getRequest().getParameter("r");
		String name = ServletActionContext.getRequest().getParameter("n");
		String age = ServletActionContext.getRequest().getParameter("a");
		String telephone = ServletActionContext.getRequest().getParameter("te");
		String mobile = ServletActionContext.getRequest().getParameter("m");
		String bio = ServletActionContext.getRequest().getParameter("b");
		String pic = ServletActionContext.getRequest().getParameter("pic");
		String checkcode = ServletActionContext.getRequest().getParameter("c");
		JSONObject result = new JSONObject();
		try {
			out = response.getWriter();
			if (!checkcode.equals(ActionContext.getContext().getSession()
					.get("randomCode"))) {
				result.put("state", 3);
				result.put("message", "验证码不正确");
			} else {
				boolean state = userService.exists(email);
				boolean voterState = voterService.exists(email);
				if (state || voterState) {
					result.put("state", 1);
					result.put("message", "电子邮箱已经存在!");
				} else {
					User s = new User();
					s.setAge(Integer.parseInt(age));
					s.setEmail(email);
					s.setMobile(mobile);
					s.setPassword(password);
					s.setPic1("/" + pic);
					s.setRegionId(Integer.parseInt(regionId));
					s.setTelephone(telephone);
					s.setUsername(name);
					s.setNickname(nickname);
					s.setBio1(bio);
					int id = userService.save(s);
					result.put("state", 0);
					result.put("message", "注册成功");
				}
			}
			out.write(result.toString());
		} catch (Exception e) {
			result.put("state", 1);
			result.put("message", "注册失败");
			out.write(result.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

	}

	public void getRegions() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = null;
		JSONArray result = new JSONArray();
		try {
			out = response.getWriter();
			List<Region> regions = regionService.getRegions(1);
			JSONObject r = null;
			for (Region region : regions) {
				r = new JSONObject();
				r.put("id", region.getId());
				r.put("name", region.getName());
				result.add(r);
			}
			out.write(result.toString());
		} catch (Exception e) {
			out.write(result.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

	}

	public List<User> getEntities() {
		return entities;
	}

	public Pager getUserPager() {
		return userPager;
	}

	public void setUserPager(Pager pager) {
		this.userPager = pager;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public String getBasePath() {
		return ServletActionContext.getRequest().getContextPath();
	}

	public File getQqfile() {
		return qqfile;
	}

	public void setQqfile(File qqfile) {
		this.qqfile = qqfile;
	}

	public String getUpfileResult() {
		return upfileResult;
	}

	public void setUpfileResult(String upfileResult) {
		this.upfileResult = upfileResult;
	}

	private void cropBigPic(String fileName) {
		ImageTool loadIcon = new ImageTool();
		ImageIcon icon = loadIcon.loadImg(fileName);
		int height = icon.getIconHeight();
		ImageIcon resizedIcon = icon;
		if (height > 552) {
			resizedIcon = loadIcon.resizeImg_scale(icon, (double) 552 / height,
					(double) 552 / height);
		}
		int width = resizedIcon.getIconWidth();
		if (width > 446) {
			resizedIcon = loadIcon.resizeImg_scale(resizedIcon, (double) 446
					/ width, (double) 446 / width);
		}
		loadIcon.writeImgIcon(resizedIcon, fileName.replaceAll("tmpbig", "big"));
	}

	private void cropSmallPic(String fileName) {
		ImageTool loadIcon = new ImageTool();
		ImageIcon icon = loadIcon.loadImg(fileName);
		int width = icon.getIconWidth();
		ImageIcon resizeIcon = icon;
		if (width > 166) {
			resizeIcon = loadIcon.resizeImg_scale(icon, (double) 166 / width,
					(double) 166 / width);
		}
		if (resizeIcon.getIconHeight() > 141) {
			resizeIcon = loadIcon.getPieceImg_we(resizeIcon, 0, 0,
					resizeIcon.getIconWidth(), 141);
		} else {
			resizeIcon = loadIcon.getPieceImg_we(resizeIcon, 0, 0,
					resizeIcon.getIconWidth(), resizeIcon.getIconHeight());
		}
		loadIcon.writeImgIcon(resizeIcon,
				fileName.replaceAll("tmpbig", "small"));

	}

	private void cropMediumPic(String fileName) {
		ImageTool loadIcon = new ImageTool();
		ImageIcon icon = loadIcon.loadImg(fileName);
		int height = icon.getIconHeight();
		ImageIcon resizeIcon = icon;
		if (height > 269) {
			resizeIcon = loadIcon.resizeImg_scale(icon, (double) 269 / height,
					(double) 269 / height);

		}
		if (resizeIcon.getIconWidth() > 165) {
			int x = (resizeIcon.getIconWidth() - 165) / 2;
			resizeIcon = loadIcon.getPieceImg_we(resizeIcon, x, 0, 165,
					resizeIcon.getIconHeight());
		} else {
			resizeIcon = loadIcon.getPieceImg_we(resizeIcon, 0, 0,
					resizeIcon.getIconWidth(), resizeIcon.getIconHeight());
		}
		loadIcon.writeImgIcon(resizeIcon,
				fileName.replaceAll("tmpbig", "medium"));
	}
}
