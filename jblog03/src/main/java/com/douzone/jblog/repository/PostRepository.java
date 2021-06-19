package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.vo.PostVo;

@Service
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;
	
	String namespace = "post.";
	

	public void insertPost(PostVo vo) {	
		sqlSession.insert(namespace + "insert" , vo);					
	}


	public void joinPost() {
		sqlSession.insert(namespace+"joinPost");
	}


	public void joinPost(PostVo vop) {
		sqlSession.insert(namespace+"insertPost" ,vop);
	}


	public List<PostVo> getPostAll(String id, Long categoryNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id",id);
		map.put("categoryNo", categoryNo);
		return sqlSession.selectList(namespace+"getPostAll", map);
	}


	public PostVo findByCategoryNo(Long categoryNo) {
		return sqlSession.selectOne(namespace+"findByCategoryNo" , categoryNo);
	}


}
