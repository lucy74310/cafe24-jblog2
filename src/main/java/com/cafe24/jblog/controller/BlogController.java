package com.cafe24.jblog.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.cafe24.jblog.dto.JSONResult;
import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	
	@RequestMapping({"","/"})
	public String blog
	(	@PathVariable("id") String blogId,
		Model model
	) 
	{
		
		BlogVo blogVo = blogService.get(blogId);
		if(blogVo == null ) {
			return "redirect:/main";
		}
		List<CategoryVo> cateList = blogService.getCategoryList(blogId);
		
		model.addAttribute("blog", blogVo);
		model.addAttribute("category", cateList);
		
		return "blog/blog-main";
	}
	
	@RequestMapping(value="/admin/{menu}", method=RequestMethod.GET)
	public String admin
	(
		@PathVariable("id") String blogId,
		@PathVariable("menu") String menu,
		Model model	
	) {
		BlogVo blogVo = blogService.get(blogId);
		
		if(blogVo == null ) {
			return "redirect:/main";
		}
		
		List<CategoryVo> cateList = blogService.getCategoryList(blogId);
		
		
		model.addAttribute("category", cateList);
		model.addAttribute("blog", blogVo);
		
		
		if("basic".equals(menu) || "category".equals(menu) || "write".equals(menu)) {
			
			
			return "blog/blog-admin-"+menu;
		}
		
		
		
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/admin/basic", method=RequestMethod.POST)
	public String adminBasic
	(
			@PathVariable("id") String blogId,
			@RequestParam("title") String blogTitle,
			@RequestParam("logo-file") MultipartFile multipartFile,
			
			Model model
	) {
		blogService.restoreBlogInfo(multipartFile, blogId, blogTitle);
		model.addAttribute("blog", blogService.get(blogId));
		
		
		return "blog/blog-admin-basic";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/admin/category", method=RequestMethod.POST)
	public JSONResult adminAddCategory
	(	
		@PathVariable("id") String blogId,
		@ModelAttribute CategoryVo categoryVo
	) {
		
		System.out.println(categoryVo);
		blogService.addCategory(categoryVo);
		
		List<CategoryVo> cateList = blogService.getCategoryList(blogId);
		
		return JSONResult.success(cateList);
	}
	
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String adminWrite
	(
			
	) {
		return "blog/blog-admin-write";
	}
	
	
	/*
	 * @RequestMapping(value="/admin/basic", method=RequestMethod.GET) public String
	 * adminBasic (
	 * 
	 * @PathVariable("id") String blogId, Model model ) { BlogVo blogVo =
	 * blogService.get(blogId); if(blogVo == null ) { return "redirect:/main"; }
	 * 
	 * model.addAttribute("blog", blogVo); return "blog/blog-admin-basic"; }
	 * 
	 * 
	 * @RequestMapping(value="/admin/category", method=RequestMethod.GET) public
	 * String adminCategory (
	 * 
	 * @PathVariable("id") String blogId, Model model ) { List<CategoryVo> cateList
	 * = blogService.getCategoryList(blogId); model.addAttribute("category",
	 * cateList); System.out.println("admin/category"); return
	 * "blog/blog-admin-category"; }
	 * 
	 * 
	 * @RequestMapping(value="/admin/write", method=RequestMethod.GET) public String
	 * adminWrite (
	 * 
	 * @PathVariable("id") String blogId, Model model ) { List<CategoryVo> cateList
	 * = blogService.getCategoryList(blogId); model.addAttribute("category",
	 * cateList); return "blog/blog-admin-write"; }
	 */
	
	

}
