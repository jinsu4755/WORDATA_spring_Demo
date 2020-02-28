package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.domain.User;
import com.example.security.repository.UserRepository;
import com.example.security.utils.EncryptionUtils;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public User login(String loginId, String password) {
		User user = userRepository.findOneByLoginId(loginId);
		if (user == null)
			return null;
		String pw = EncryptionUtils.encryptMD5(password); //현 DB가 MD5 알고리즘으로 암호화 되어있으므로 MD5 암호화 비교
		if (user.getPassword().equals(pw) == false)
			return null;
		return user;
	}
}