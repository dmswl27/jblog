package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;
import com.douzone.jblog.service.FileUploadService;


@Controller
@RequestMapping("/{id:(?!assets).*}") // assets 제외한 문자는 받아라 : ? 있어도 되고 없어도 되고 , ! 제외 
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	
	@Autowired
	private ServletContext application;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping({ "", "/{pathNo1}", "{pathNo1}/{pathNo2}" })
	public String index(@PathVariable("id") String id, 
			@PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2,
			@AuthUser UserVo authUser,
			Model model) {
		Long categoryNo = 0L;
		Long postNo = 0L;

		if (pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		} else {
            categoryNo = categoryService.getNo(id);
            postNo = postService.getNo(categoryNo);
		}

		System.out.println("id:" + id);
		System.out.println("category:" + categoryNo);
		System.out.println("post:" + postNo);
		

		BlogVo vob = blogService.findById(id);
		model.addAttribute("vob",vob);
		model.addAttribute(authUser);
		

		List<CategoryVo> listc = categoryService.findByCategory(id);
		model.addAttribute("listc",listc);
		
		
		List<PostVo> listp = postService.getPostAll(id, categoryNo);
		model.addAttribute("listp",listp);
		
		PostVo vop = postService.findByCategoryNo(categoryNo, postNo);
		model.addAttribute("vop",vop);
		
		application.setAttribute("title", vob.getTitle());
		application.setAttribute("id", id);
		
		return "blog/main";
	}

	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.GET)
	public String adminWrite(
			@PathVariable("id") String id,
			Model model) {
		List<CategoryVo> list = categoryService.findByCategory(id);
		model.addAttribute("list", list);
		return "blog/admin/write";
	}
	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String adminWrite(@PathVariable("id") String id,
			@RequestParam(value = "title", required=true, defaultValue="") String title,
			@RequestParam(value = "contents", required=true, defaultValue="") String contents,
			@RequestParam(value="category", required=true, defaultValue="") Long categoryNo) {
		
		postService.insertPost(categoryNo, title, contents);
		
		
		return "redirect:/" +id + "/" +categoryNo ;
	}
	@Auth
	@RequestMapping(value="/admin/category", method=RequestMethod.GET)
	public String adminCategory(@AuthUser UserVo authUser, Model model) {
		List<CategoryVo> list = categoryService.findByCategory(authUser.getId());
		model.addAttribute("list",list);
		return "blog/admin/category";
		
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
	@RequestMapping(value = "admin/delete/{no}")
	public String delete(@PathVariable("no") Long no,
			@PathVariable("id") String id) {
		postService.delete(no);
		categoryService.delete(no,id );
		return "redirect:/" +id+ "/admin/category";
		
	}
	@Auth
	@RequestMapping(value="/admin/basic", method=RequestMethod.GET)
	public String adminBasic(
			@PathVariable("id") String id,
			Model model) {
		BlogVo vob = blogService.findById(id);
		model.addAttribute("vob",vob);
		return "blog/admin/basic";
	}
	@Auth
	@RequestMapping(value="/admin/basic", method=RequestMethod.POST)
	public String adminBasic(@RequestParam("file1") MultipartFile file1,
			@RequestParam(value = "title", required=true, defaultValue="" )String title, 
			@PathVariable("id") String id) {
		String url = fileUploadService.restore(file1);  
		System.out.println(url);
		blogService.update(url, title, id);
		return "redirect:/" +id;
	}

}
