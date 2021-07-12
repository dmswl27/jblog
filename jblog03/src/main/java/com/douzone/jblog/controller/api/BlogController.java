package com.douzone.jblog.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@RestController("jblogControllerApi")
@RequestMapping("/{id:(?!assets).*}/api")
public class BlogController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@Auth
	@RequestMapping(value="/admin/category", method=RequestMethod.GET)
	public String adminCategory(@AuthUser UserVo authUser, Model model) {
		List<CategoryVo> list = categoryService.findByCategory(authUser.getId());
		model.addAttribute("list",list);
		return "blog/admin/category2";
		
	}
	@Auth
	@RequestMapping(value="/admin/category", method=RequestMethod.POST)
	public String adminCategory(
			@PathVariable("id") String id,
			@RequestParam(value = "name", required=true, defaultValue="") String name,
			@RequestParam(value = "desc", required=true, defaultValue="") String desc ) {
		CategoryVo voc = new CategoryVo();
		voc.setName(name);
		voc.setDesc(desc);
		voc.setBlog_id(id);
		categoryService.addCategory(voc);
		
		return "redirect:/" +id+ "/admin/category";
	}
	@Auth
	@RequestMapping(value = "/admin/delete/{no}")
	public String delete(@PathVariable("no") Long no,
			@PathVariable("id") String id) {
		postService.delete(no);
		categoryService.delete(no,id );
		return "redirect:/" +id+ "/admin/category";
		
	}
	
	
}
