package com.maiyatang.tokyo.community.controller;

import com.maiyatang.tokyo.community.dto.AccessTokenDTO;
import com.maiyatang.tokyo.community.dto.GithubUser;
import com.maiyatang.tokyo.community.mapper.UserMapper;
import com.maiyatang.tokyo.community.model.User;
import com.maiyatang.tokyo.community.provider.GithubProvider;
import com.maiyatang.tokyo.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String secret;
    @Value("${github.redirect.uri}")
    private String uri;

    /**
     * login ログイン
     * @param code
     * @param state
     * @param request
     * @param model
     * @return githubUser
     */
    @GetMapping("/callback")
    public String login(@RequestParam(value = "code", required = false) String code,
                        @RequestParam(value = "state", required = false) String state,
                        HttpServletRequest request, HttpServletResponse response,
                        Model model) {

        //AccessTokenDTOの値を設定する
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(uri);
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getGithubUser(accessToken);
        if (githubUser!=null && githubUser.getId()!=null){
            User user =new User();
            user.setName(githubUser.getLogin());
            user.setAccountId(githubUser.getId().toString());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            //生成36位的UUID，作为唯一标识的token
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            // userを新規まだは更新する
            userService.createOrUpdate(user);
            // add cookie in response
            response.addCookie(new Cookie("token",token));
            // cookie,sessionを作る
            request.getSession().setAttribute("user",user);
            return "redirect:/";
        }else {
            return "redirect:/";
        }

    }

    /**
     * logout
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        //attributeを削除
        request.getSession().removeAttribute("user");
        // delete token
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
