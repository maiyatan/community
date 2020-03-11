package com.maiyatang.tokyo.community.controller;

import com.maiyatang.tokyo.community.dto.AccessTokenDTO;
import com.maiyatang.tokyo.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthoriaController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "state", required = false) String state) {

        //AccessTokenDTOの値をセットする
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("Iv1.3e4bf8c3d2021cde");
        accessTokenDTO.setClient_secret("72f9232d5c19bb4925b8d4f5b52f8ab56078ae72");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        String loginName = githubProvider.getGithubUser(accessToken).getLogin();
        System.out.println(loginName);
        return "index";
    }


}
