package com.web.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("admin")
public class AdminController {

    /**
     * 限制权限为auth才能访问
     */
    @PreAuthorize("hasAuthority('auth')")
    @GetMapping("go1")
    public String getAdmin1() {
        return "777";
    }

    /**
     * 限制只能查询Id=1的用户
     */
    @PreAuthorize("#id==1")
    @GetMapping("go")
    public String getAdmin(Integer id) {
        System.out.println(id);
        return "666";
    }

    /**
     * 限制只能查询自己的信息
     */
    @PreAuthorize("principal.username.equals(#username)")
    @GetMapping("go2")
    public String getAdmin2(String username) {
        return username;
    }

    /**
     * 限制只能新增用户名称为abc的用户
     */
    @PreAuthorize("'abc'.equals(#name)")
    @GetMapping("add")
    public String add(String name) {
        return name;
    }

    /**
     * 返回值必须为1
     */
    @PostAuthorize("returnObject=='1'")
    @GetMapping("go3")
    public String getAdmin3() {
        return "1";
    }

    /**
     * 过滤移除
     */
    @PreFilter(filterTarget = "ids", value = "filterObject%2==0")
    @GetMapping("go4")
    public String getAdmin4(List<Integer> ids, List<String> usernames) {
        return "1";
    }

}
