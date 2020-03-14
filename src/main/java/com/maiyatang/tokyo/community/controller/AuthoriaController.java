package com.maiyatang.tokyo.community.controller;

import com.maiyatang.tokyo.community.dto.AccessTokenDTO;
import com.maiyatang.tokyo.community.dto.GithubUser;
import com.maiyatang.tokyo.community.mapper.UserMapper;
import com.maiyatang.tokyo.community.model.User;
import com.maiyatang.tokyo.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.util.UUID;

@Controller
public class AuthoriaController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired(required=false)
    private UserMapper uerMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String secret;
    @Value("${github.redirect.uri}")
    private String uri;

    /**
     * login
     * @param code
     * @param state
     * @param request
     * @param model
     * @return githubUser
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(value = "code", required = false) String code,
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
        if (githubUser!=null){
            User user = new User();
            user.setName(githubUser.getLogin());
            user.setAccountId(githubUser.getId().toString());
            //生成36位的UUID，作为唯一标识的token
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setCreateTime(System.currentTimeMillis());
            user.setModifiedTime(user.getCreateTime());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            // ユーザー個人情報をテーブルuserにセット
            uerMapper.insertGithubUser(user);
            // add cookie in response
            response.addCookie(new Cookie("token",token));
//            // cookie,sessionを作る
//            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else {
            return "redirect:/";
        }

    }


}
