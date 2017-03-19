package cn.nlr.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.nlr.model.User;
import cn.nlr.service.UserService;

@Controller
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    

	@RequestMapping("/regiester")
	public String regiester(Map<String, Object> model){
		userService.saveUser(null);
		model.put("msg", "张三丰");		
		return "regiester";
	}
	@RequestMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo(@RequestParam(value="name", required=false, defaultValue="5566") String name, Model model) {
        return new User();
    }
}
