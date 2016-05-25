package com.users.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.model.Imagine;
import com.users.repository.ImagineRepository;
import com.users.service.ImagineService;

@Service
public class ImagineServiceImpl implements ImagineService{
	@Autowired
	private ImagineRepository imagineRepository;
	@Override
	public Imagine save(Imagine r) {
		return imagineRepository.save(r);
	}

}
