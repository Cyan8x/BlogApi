package com.mitocode.blog.blogapi.service.impl;

import java.util.List;

import com.mitocode.blog.blogapi.service.GenericCrudService;

public class GenericCrudImpl<T, ID, J> implements GenericCrudService<T, ID> {

//	GenericRepository<T, ID> genericRepository;
//
//	public GenericCrudImpl(GenericRepository<T, ID> genericRepository) {
//		super();
//		this.genericRepository = genericRepository;
//	}

	@Override
	public T create(T t) {
//		J j = mapToEntity(t);
//		J jnew = t.save(j);
//		return mapToDto(jnew);
		return null;
	}

	@Override
	public List<T> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T readById(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T update(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(ID id) {
		// TODO Auto-generated method stub
	}
}
