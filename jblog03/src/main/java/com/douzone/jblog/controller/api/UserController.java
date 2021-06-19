package com.douzone.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@RestController("userControllerApi")
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/checkid")
	public JsonResult checkid(@RequestParam(value = "id", required = true ,defaultValue = "") String id) {
		UserVo vo = userService.getUser(id);
//		Boolean date = vo !=null;
//		JsonResult result = new JsonResult();
//		JsonResult result = JsonResult.success(vo !=null);
//		JsonResult result = JsonResult.fail("...");
//		result.setResult("ok");
//		result.setDate(date);
		
		return JsonResult.success(vo != null);
	}
}
