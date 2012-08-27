package com.arc.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.arc.entity.DataBean;
import com.arc.entity.User;
import com.arc.util.Pager;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

@Repository
public class UserDAO {
	@Resource
	private Dbo dbo;

	public int save(User user) {
		String sql = "insert into users(nickname,username,password,email,region_id,age,telephone,mobile,is_valid,pic1,bio1) values(?,?,?,?,?,?,?,?,0,?,?)";
		return dbo.insert(sql, user.getNickname(), user.getUsername(),
				user.getPassword(), user.getEmail(), user.getRegionId(),
				user.getAge(), user.getTelephone(), user.getMobile(),
				user.getPic1(), user.getBio1());
	}

	public void update(User user) {
		String sql = "update users set nickname=?,username=?,password=?,email=?,region_id=?,age=?,telephone=?,mobile=?,is_valid=?,pic1=?,pic2=?,bio1=?,bio2=?,rank1=?,rank2=?,remark=? where id=?";
		dbo.update(sql, user.getNickname(), user.getUsername(),
				user.getPassword(), user.getEmail(), user.getRegionId(),
				user.getAge(), user.getTelephone(), user.getMobile(),
				user.isValid(), user.getPic1(), user.getPic2(), user.getBio1(),
				user.getBio2(), user.getRank1(), user.getRank2(),
				user.getRemark(), user.getId());
	}

	public void updateRank1(int userId) {
		String sql = "update users set rank1=rank1+1 where id=?";
		dbo.update(sql, userId);
	}

	public void updateRank2(int userId) {
		String sql = "update users set rank2=rank2+1 where id=?";
		dbo.update(sql, userId);
	}

	public void delete(int id) {
		String sql = "delete from users where id=?";
		dbo.update(sql, id);
	}

	public List<User> findByPager(Pager pager, String regionId,
			String nickname, String valid) {
		String condition = "";
		if (!Strings.isNullOrEmpty(regionId)) {
			condition = " where region_id=" + regionId;
		}
		if (!Strings.isNullOrEmpty(valid)) {
			if (Strings.isNullOrEmpty(condition)) {
				condition = " where is_valid=" + valid;
			} else {
				condition += " and is_valid=" + valid;
			}
		}
		if (!Strings.isNullOrEmpty(nickname)) {
			if (Strings.isNullOrEmpty(condition)) {
				condition = " where nickname like '%" + nickname + "%'";
			} else {
				condition = " and like '%" + nickname + "%'";
			}
		}
		int totalSize = dbo.queryForInt("select count(*) from users"
				+ condition);
		pager.setTotalSize(totalSize);

		List<DataBean> tmpList = dbo
				.queryForList(
						"select u.id,nickname,email,region_id,r.name as region_name,age,telephone,mobile,is_valid,rank1,rank2 from users u left join regions r on r.id=u.region_id "
								+ condition
								+ " order by region_id asc ,rank1 desc ,rank2 desc ,u.id desc limit ?,?",
						(pager.getCurrentPage() - 1) * pager.getPageSize(),
						pager.getPageSize());
		List<User> users = Lists.newArrayList();

		if (tmpList == null)
			return users;
		for (DataBean bean : tmpList) {
			users.add(mapper(bean));
		}
		return users;
	}

	private User mapper(DataBean bean) {
		User user = new User();
		if (bean.containsKey("id"))
			user.setId(bean.getInt("id"));
		if (bean.containsKey("nickname"))
			user.setNickname(bean.getString("nickname"));
		if (bean.containsKey("username"))
			user.setUsername(bean.getString("username"));
		if (bean.containsKey("password"))
			user.setPassword(bean.getString("password"));
		if (bean.containsKey("email"))
			user.setEmail(bean.getString("email"));
		if (bean.containsKey("region_id"))
			user.setRegionId(bean.getInt("region_id"));
		if (bean.containsKey("region_name"))
			user.setRegionName(bean.getString("region_name"));
		if (bean.containsKey("age"))
			user.setAge(bean.getInt("age"));
		if (bean.containsKey("telephone"))
			user.setTelephone(bean.getString("telephone"));
		if (bean.containsKey("mobile"))
			user.setMobile(bean.getString("mobile"));
		if (bean.containsKey("is_valid"))
			user.setValid(bean.getBoolean("is_valid"));
		if (bean.containsKey("pic1"))
			user.setPic1(bean.getString("pic1"));
		if (bean.containsKey("pic2"))
			user.setPic2(bean.getString("pic2"));
		if (bean.containsKey("bio1"))
			user.setBio1(bean.getString("bio1"));
		if (bean.containsKey("bio2"))
			user.setBio2(bean.getString("bio2"));
		if (bean.containsKey("rank1"))
			user.setRank1(bean.getInt("rank1"));
		if (bean.containsKey("rank2"))
			user.setRank2(bean.getInt("rank2"));
		if (bean.containsKey("remark"))
			user.setRemark(bean.getString("remark"));
		return user;
	}

	public User findById(int id) {
		String sql = "select u.*,r.name as region_name from users u left join regions r on r.id=u.region_id where u.id=?";
		DataBean bean = this.dbo.queryForMapBean(sql, id);
		if (bean == null)
			return null;
		else
			return mapper(bean);
	}

	public List<User> findByPager(Pager pager, int regionId) {
		int totalSize = dbo.queryForInt(
				"select count(*) from users where is_valid=1 and region_id=?",
				regionId);
		pager.setTotalSize(totalSize);
		List<DataBean> tmpList = dbo
				.queryForList(
						"select id,nickname,region_id,rank1,rank2,age,pic1,pic2,bio1,bio2 from users"
								+ " where is_valid=1 and region_id=? order by rank1 desc,rank2 desc,id asc limit ?,?",
						regionId,
						(pager.getCurrentPage() - 1) * pager.getPageSize(),
						pager.getPageSize());
		List<User> users = Lists.newArrayList();

		if (tmpList == null)
			return users;
		for (DataBean bean : tmpList) {
			users.add(mapper(bean));
		}
		return users;
	}

	public User getPreviousUser(int currentUserId, int regionId) {
		String sql = "select id,pic1 from users where is_valid=1 and rank1>= (select rank1 from users where id=?) and region_id=? order by rank1 desc,rank2 desc,id asc";
		List<DataBean> list = dbo.queryForList(sql, currentUserId, regionId);
		if (list == null || list.size() == 1) {
			return null;
		} else {
			User user = null;
			for (DataBean bean : list) {
				if (bean.getInt("id") == currentUserId) {
					return user;
				} else {
					user = mapper(bean);
				}
			}
		}
		return null;
	}

	public User findNextUser(int currentUserId, int regionId) {
		String sql = "select id,pic1 from users where is_valid=1 and rank1 <= (select rank1 from users where id=?) and region_id=? order by rank1 desc,rank2 desc,id asc";
		List<DataBean> list = dbo.queryForList(sql, currentUserId, regionId);
		if (list == null || list.size() == 1) {
			return null;
		} else {
			boolean isViewed = false;
			for (DataBean bean : list) {
				if (isViewed) {
					return mapper(bean);
				} else {
					if (bean.getInt("id") == currentUserId) {
						isViewed = true;
					}
				}
			}
		}
		return null;
	}

	public List<User> findLastestUsers(String regionId) {
		String sql = "select pic1,region_id,id from users where is_valid=1 and region_id=? order by rank1 desc,rank2 desc,id asc limit 0,6";
		List<DataBean> tmpList = dbo.queryForList(sql, regionId);
		List<User> users = Lists.newArrayList();
		if (tmpList == null)
			return users;
		for (DataBean bean : tmpList) {
			users.add(mapper(bean));
		}
		return users;
	}

	public boolean exists(String email) {
		String sql = "select count(*) from users where email=?";
		return dbo.queryForInt(sql, email) > 0;
	}

	public User getUser(String email, String password) {
		String sql = "select id,email,nickname from users where email=? and password=?";
		DataBean bean = this.dbo.queryForMapBean(sql, email, password);
		if (bean == null)
			return null;
		else
			return mapper(bean);
	}
}
