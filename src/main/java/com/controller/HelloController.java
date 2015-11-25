package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class HelloController {

	@RequestMapping(value = "/hello1", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";

	}
	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("msg", name);

		return model;

	}
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    @ResponseBody
    public String hello() {
          return "这是";

    }
    @RequestMapping(value = "/hello/upload", method = RequestMethod.POST)
    @ResponseBody
    public void hello( @RequestParam MultipartFile fileData,HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = fileData.getOriginalFilename();
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            fileData.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}