package com.arc.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arc.dao.VoterDAO;
import com.arc.entity.Voter;

@Service
public class VoterService {
	@Resource
	VoterDAO voterDAO;

	public int save(Voter voter) {
		return voterDAO.save(voter);
	}

	public boolean exists(String phone) {
		return voterDAO.checkPhone(phone);
	}

	public Voter getVoter(String email, String password) {
		return voterDAO.getVoter(email, password);
	}

	public Voter getVoter(String phone) {
		return voterDAO.getVoter(phone);
	}
}
