package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {
	
	@Autowired 
	PostRepository postRepository;
	
	public void insertPost(PostVo vo) {	
		postRepository.insertPost(vo);				
	}

	public void joinPost() {
		postRepository.joinPost();
	}

	public void joinPost(CategoryVo voc) {
		PostVo vop = new PostVo();
		vop.setCategory_no(voc.getNo());
		vop.setTitle("비어있음");
		vop.setContents("비어있음");
		postRepository.joinPost(vop);
	}

	public List<PostVo> getPostAll(String id, Long categoryNo) {
		return postRepository.getPostAll(id, categoryNo);
	}

	public PostVo findByCategoryNo(Long categoryNo , Long postNo) {
		PostVo vop = new PostVo();
		vop.setCategory_no(categoryNo);
		vop.setNo(postNo);
		System.out.println(vop);
		return postRepository.findByCategoryNo(vop);
	}

	public void insertPost(Long categoryNo, String title, String contents) {
		PostVo vop = new PostVo();
		vop.setCategory_no(categoryNo);
		vop.setContents(contents);
		vop.setTitle(title);
		postRepository.insertPost(vop);
	}

	public PostVo findByPostNo(Long no) {
		return postRepository.findByPostNo(no);
	}

	public Long getNo(Long categoryNo) {
		return postRepository.getNo(categoryNo);
	}

	public void delete(Long no) {
		postRepository.delete(no);
	}

	

	

}
