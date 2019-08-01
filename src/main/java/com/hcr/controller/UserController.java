package com.hcr.controller;

import com.hcr.config.annotation.Decrypt;
import com.hcr.config.annotation.Encrypt;
import com.hcr.model.UserDto;
import com.hcr.utils.AesEncryptUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Encrypt加密
 * @Decrypt解密
 */
@RestController
public class UserController {
    private static final String KEY = "abcdef0123456780";

    /**
     * get请求->一个或多个参数使用工具类解密
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @Encrypt
    @GetMapping("/getStrData")
    public String getStrData(String username, String password) throws Exception {
        username = AesEncryptUtils.aesDecrypt(username, KEY);
        password = AesEncryptUtils.aesDecrypt(password, KEY);
        System.out.println(username + "---" + password);

        return "加密字符串";
    }

    /**
     * post请求->一个或多个参数使用工具类解密
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @Encrypt
    @PostMapping("/postStrData")
    public String postStrData(String username, String password) throws Exception {
        username = AesEncryptUtils.aesDecrypt(username, KEY);
        password = AesEncryptUtils.aesDecrypt(password, KEY);
        System.out.println(username + "---" + password);

        return "加密字符串";
    }

    /**
     * post请求->一个参数使用@Decrypt注解解密
     *
     * @param username
     * @return
     * @throws Exception
     */
    @Encrypt
    @Decrypt
    @PostMapping("/postStrData2")
    public String postStrData2(@RequestBody String username) throws Exception {
        System.out.println("username: " + username);

        return "加密字符串";
    }

    /**
     * post请求->多个参数使用@Decrypt注解解密
     *
     * @param paramsMap
     * @return
     * @throws Exception
     */
    @Encrypt
    @Decrypt
    @PostMapping("/postStrData3")
    public String postStrData4(@RequestBody Map<String, Object> paramsMap) throws Exception {
        System.out.println("username: " + paramsMap);
        System.out.println(paramsMap.get("username") + "---" + paramsMap.get("password"));

        return "加密字符串";
    }

    /**
     * post请求->发送并获取实体类数据,使用@Decrypt注解解密
     */
    @Encrypt
    @Decrypt
    @PostMapping("/encryptDto")
    public UserDto encryptDto(@RequestBody UserDto dto) {
        System.err.println(dto.getId() + "\t" + dto.getName());
        return dto;
    }

    /**
     * post请求->发送并获取map数据,使用@Decrypt注解解密
     *
     * @return
     */
    @Encrypt
    @Decrypt
    @PostMapping("/encryptMap")
    public Map<String, Object> encryptMap(@RequestBody Map<String, Object> map) {
        String username = (String) map.get("username");
        int password = (int) map.get("password");
        System.out.println(username + "---" + password);

        Map<String, Object> resultMap = new HashMap<>();

        UserDto dto = new UserDto();
        dto.setId(1);
        dto.setName("加密实体对象");

        UserDto dto2 = new UserDto();
        dto2.setId(2);
        dto2.setName("加密实体对象2");

        List<UserDto> list = new ArrayList<>();
        list.add(dto);
        list.add(dto2);

        resultMap.put("int", 666);
        resultMap.put("String", "wanghonghui");
        resultMap.put("dto", dto);
        resultMap.put("list", list);

        return resultMap;
    }


}
