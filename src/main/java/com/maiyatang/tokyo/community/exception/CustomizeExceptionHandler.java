package com.maiyatang.tokyo.community.exception;

import com.alibaba.fastjson.JSON;
import com.maiyatang.tokyo.community.dto.ResultDTO;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
//拦截全部的exception
    ModelAndView handleControllerException(HttpServletRequest request, HttpServletResponse response, Throwable ex, Model model) {
        String contentType = request.getContentType();
        if ("application/JSON".equals(contentType)) {
            ResultDTO resultDTO;
            // return JSON
            if (ex instanceof CustomizeException) {
                resultDTO = ResultDTO.errorOf(((CustomizeException) ex).code, ex.getMessage());
            } else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYSTEM_ERROR.getErrorCode(), CustomizeErrorCode.SYSTEM_ERROR.getMessage());
            }
            response.setContentType("application/JSON");
            response.setStatus(200);
            response.setCharacterEncoding("utf-8");
            try {
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        } else {
            // return error page
            // 需要人为处理的异常
            if (ex instanceof CustomizeException) {
                model.addAttribute("message", ex.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYSTEM_ERROR.getMessage());
            }
            return new ModelAndView("error");//返回error页面
        }
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
