package com.project.sj.service;

import com.project.sj.domain.User;
import com.project.sj.repository.UserRepository;

public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@Override
	public void join(User user) {
		userRepository.insert(user);
	}
}
