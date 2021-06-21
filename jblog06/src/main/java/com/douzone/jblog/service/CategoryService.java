package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void insertCategory(CategoryVo vo) {	
		categoryRepository.insertCategory(vo);			
	}

	public CategoryVo joinCategory(UserVo vo) {
		CategoryVo voc = new CategoryVo();
		voc.setBlog_id(vo.getId());
		voc.setName(vo.getName()+"님의 카테고리 >_<");
		voc.setDesc("비어있음");
		System.out.println(voc + "voc");
		return categoryRepository.insert(voc);
	}

	public List<CategoryVo> findByCategory(String id) {
		return categoryRepository.findByCategory(id);
		
	}

	public void addCategory(CategoryVo voc) {
		categoryRepository.addCategory(voc);
	}

	public int findByNo(String id) {
		return categoryRepository.findByNo(id);
	}

	public Long getNo(String id) {
		return categoryRepository.getNo(id);
	}

	public void delete(Long no, String id) {
		CategoryVo vo = new CategoryVo();
		vo.setNo(no);
		vo.setBlog_id(id);
		categoryRepository.delete(vo);
	}




}
