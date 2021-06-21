package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;
	
	String namespace = "category.";
	

	public void insertCategory(CategoryVo vo) {	
		sqlSession.insert(namespace + "insert" , vo);					
	}


	public CategoryVo insert(CategoryVo voc) {
		sqlSession.insert(namespace+"insert", voc);
		return sqlSession.selectOne(namespace+"selectNo", voc);
	}


	public List<CategoryVo> findByCategory(String id) {
		return sqlSession.selectList(namespace+"findByCategory", id);
	}


	public void addCategory(CategoryVo voc) {
		sqlSession.insert(namespace+"addCategory" , voc);
	}


	public int findByNo(String id) {
		return sqlSession.selectOne(namespace+"findByNo", id);
	}


	public Long getNo(String id) {
		return sqlSession.selectOne(namespace+"getNo",id);
	}


	public void delete(CategoryVo vo) {
		sqlSession.delete(namespace+"delete" , vo);
	}




	
}
