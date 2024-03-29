package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class BlogService {
	
	
	@Autowired
	private BlogRepository blogRepository;
	

	public void insertBlog(BlogVo vo) {	
	   blogRepository.insertBlog(vo);	
	}


	public void joinBlog(UserVo vo)
	{
		BlogVo vob = new BlogVo();
		vob.setId(vo.getId());
		vob.setTitle(vo.getId()+"'s blog");
		vob.setLogo("/assets/image/default_image.jpg");
		blogRepository.insertBlog(vob);
		
	}


	public BlogVo findById(String id) {
		return blogRepository.findById(id);
	}


	public void update(String url, String title, String id) {
		BlogVo vob = new BlogVo();
		vob.setLogo(url);
		vob.setTitle(title);
		vob.setId(id);
		blogRepository.update(vob);
	}


}
