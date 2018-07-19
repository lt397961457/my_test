package com.yly.testboot.controller;

import com.google.gson.Gson;
import com.yly.testboot.dao.mongo.User4MongoRepository;
import com.yly.testboot.dao.mysql.UserRepository;
import com.yly.testboot.entity.UserEntity;
import com.yly.testboot.util.propertiesutil.MyProperties;
import com.yly.testboot.util.propertiesutil.MyPropertise2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/first")
public class FisrtController {
    private  final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    HttpServletRequest request;
    @Autowired
    FilterRegistrationBean myFilterRegistrationBean;
    @Autowired
    private MyProperties myProperties;
    @Autowired
    private MyPropertise2 myPropertise2;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private User4MongoRepository user4MongoRepository;

    @GetMapping("/json")
    public UserEntity test1(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("zhangsan");
        userEntity.setAge(18);
        userEntity.setAddr("sc");
        return userEntity;

    }
    @GetMapping("/filter")
    public String filter(HttpServletRequest request){
        String paramaa = (String)myFilterRegistrationBean.getInitParameters().get("aaa");
        String parambb =  (String)myFilterRegistrationBean.getInitParameters().get("bbb");
        System.out.println(paramaa+parambb);
        log.info(paramaa+parambb);
        return paramaa+parambb;
    }
    @GetMapping("/properties")
    public MyProperties properties(){

//        return myProperties.getMyname() +" " +myProperties.getMyage() + " " +myProperties.getMylocation();
        return myProperties;
    }

    @GetMapping("/properties2")
    public MyPropertise2 properties2(){
        log.info(myPropertise2.toString());
        return myPropertise2;
    }

    /**
     * 如果后台禁止了跨域请求，如果前台使用angular 里面jsonp请求，就需要这样：
     *
         var myUrl ="http://localhost:8080/first/properties3";
         $http.jsonp(myUrl).success(
         function(data){
         console.log(data)
         $scope.name=data.myname;
         $scope.age=data.myage;
         $scope.addr=data.mylocation;
         }
         ).error(function(err){
         //错误代码
         });
     * @return
     */
    @GetMapping("/properties3")
    public String properties3(){
        String callback = request.getParameter("callback");
        log.info(callback);
        log.info(myPropertise2.toString());
        Gson gson = new Gson();
        String str = gson.toJson(myPropertise2);
        log.info(str);
        return "angular.callbacks._0("+str+");";
    }
    @GetMapping("/properties4")
    public String properties4(){

        return "JSON_CALLBACK({\"result\":\"E_FAIL\",\"desc\":\"\\u60a8\\u8fd8\\u6ca1\\u6709\\u767b\\u5f55\",\"data\":[]});";
    }

    @GetMapping("/getUserByname")
    public UserEntity getUserByUserName(@RequestParam(value = "name") String userName){
        return  userRepository.findByUserName(userName);
    }
    @GetMapping("/getUserByPhone")
    public UserEntity getUserByPhone(@RequestParam(value = "phone") String phoneNum){
        return  userRepository.findByPhoneNum(phoneNum);
    }
    @GetMapping("/mongo/getUserByname")
    public UserEntity getUse4MongorByUserName(@RequestParam(value = "name") String userName){
        return  user4MongoRepository.findByUserName(userName);
    }
    @GetMapping("/mongo/getUserByPhone")
    public UserEntity getUser4MongoByPhone(@RequestParam(value = "phone") String phoneNum){
        return  user4MongoRepository.findByPhoneNum(phoneNum);
    }
    @PostMapping("/mongo/save")
    public Boolean saveUserTomongo(@RequestBody UserEntity user){
        try {
            user4MongoRepository.save(user);
            return true;
        }catch (Exception e){
            log.info("保存User信息到MongoDB异常",e);
            return false;
        }
    }

}
