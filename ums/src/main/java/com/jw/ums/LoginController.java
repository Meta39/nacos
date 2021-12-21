package com.jw.ums;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jw.common.entity.ums.LoginInfo;
import com.jw.common.entity.ums.Node;
import com.jw.common.entity.ums.Role;
import com.jw.common.result.Code;
import com.jw.common.result.Err;
import com.jw.common.result.Res;
import com.jw.ums.aop.IgnoreResAnnotate;
import com.jw.ums.entity.*;
import com.jw.ums.mapper.*;
import com.jw.ums.redis.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
public class LoginController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleNodeMapper roleNodeMapper;
    @Resource
    private NodeMapper nodeMapper;
    @Resource
    private RedisUtils redisUtils;

//    @IgnoreResAnnotate//不返回Res，直接返回接口类型
    @GetMapping("test")
    public String test(){
        return "test";
    }

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("login")
    public Res login(@RequestParam String username, @RequestParam String password) throws JsonProcessingException {
        boolean root = false;//超级管理员
        List<Role> roles = new ArrayList<>();//角色数组
        List<String> nodes = new ArrayList<>();//权限数组（无去重）
        String token = UUID.randomUUID().toString();//token
        //校验
        User user = userMapper.selectByUsername(username);
        if (user == null) throw new Err("用户名不存在");
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex((username+password).getBytes()))) throw new Err("密码错误");
        //角色
        List<UserRole> userRoles = userRoleMapper.selectByUserId(user.getId());
        for (UserRole userRole:userRoles){
            Role role = roleMapper.select(userRole.getRoleId());
            roles.add(role);//把该用户所拥有的角色添加到角色集合
            root = role.getId() == 1;//判断是否超级管理员
        }
        if (root){//超级管理员直接拿node表所有数据
            List<Node> nodeList = nodeMapper.selectAll();
            for (Node node:nodeList){
                String nodeValue = node.getNodeValue();
                if (StringUtils.isNotBlank(nodeValue))nodes.add(nodeValue);//不是空的才放进来
            }
        }else {
            //权限（需要去重）
            if (userRoles.size()>0){
                for (UserRole userRole:userRoles){
                    for (RoleNode roleNode:roleNodeMapper.selectByRoleId(userRole.getRoleId())){
                        Node node = nodeMapper.select(roleNode.getNodeId());
                        String nodeValue = node.getNodeValue();
                        if (StringUtils.isNotBlank(nodeValue))nodes.add(nodeValue);//不是空的才放进来
                    }
                }
            }
        }
        //权限nodes去重
        HashSet h = new HashSet(nodes);
        nodes.clear();
        nodes.addAll(h);
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setToken(token);
        loginInfo.setUserId(user.getId());
        loginInfo.setUsername(user.getName());
        loginInfo.setRoles(roles);
        loginInfo.setNodes(nodes);
        redisUtils.set(token,new ObjectMapper().writeValueAsString(loginInfo));
        return new Res(loginInfo);
    }

    /**
     * 登出
     * @param token
     * @return
     */
    @PostMapping("logout")
    public Res logout(@RequestParam String token){
        if (StringUtils.isBlank(token)) return Res.err("token不能为空");
        if (!redisUtils.hasKey(token)) throw new Err(Code.NOT_LOGIN.getNum(), "token不存在，请重新登录。");
        redisUtils.del(token);//删除token即可
        return new Res();
    }

    /**
     * 修改密码前校验用户名和密码
     * @param name 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("checkUserNameAndPassword")
    public Res checkUserNameAndPassword(@RequestParam String name,@RequestParam String password){
        if (StringUtils.isBlank(name)) throw new Err("用户名不能为空");
        if (StringUtils.isBlank(password)) throw new Err("密码不能为空");
        User user = userMapper.selectByUsername(name);
        if (user == null)throw new Err("用户不存在");
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex((name+password).getBytes()))) throw new Err("密码错误");
        return new Res();
    }

    /**
     * 修改密码
     * @param userId 用户ID
     * @param name 用户名
     * @param password 无加密的新密码
     * @return
     */
    @PostMapping("updatePassword")
    public Res updatePassword(@RequestParam Integer userId,@RequestParam String name,@RequestParam String password){
        if (userId == null) throw new Err("用户ID不能为空");
        if (StringUtils.isBlank(name)) throw new Err("用户名不能为空");
        if (StringUtils.isBlank(password)) throw new Err("密码不能为空");
        String newPassword = DigestUtils.md5DigestAsHex((name+password).getBytes());//新密码加密
        return new Res(userMapper.updatePassword(userId,name,newPassword));
    }


    /**
     * 超管强制修改密码
     * @param userId 用户ID
     * @param name 用户名
     * @param password 无加密密码
     * @return
     */
    @PostMapping("forceUpdatePassword")
    public Res forceUpdatePassword(@RequestParam Integer userId,@RequestParam String name,@RequestParam(required = false) String password){
        if (userId == null) throw new Err("用户ID不能为空");
        if (StringUtils.isBlank(name)) throw new Err("用户名不能为空");
        if (StringUtils.isBlank(password)) password = "a";//不传密码，密码默认为a
        String newPassword = DigestUtils.md5DigestAsHex((name+password).getBytes());//新密码加密
        return new Res(userMapper.updatePassword(userId,name,newPassword));
    }

}
