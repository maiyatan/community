package com.maiyatang.tokyo.community.dto;

import com.maiyatang.tokyo.community.model.TucaoText;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    //当前页码
    private Integer page;
    //页码列表
    private List<Integer> pages = new ArrayList<>();
    //总页数
    private Integer totalPage;
    //吐槽内容
    private List<TucaoTextDTO> tucaoInfo;
    //显示上一页
    private boolean showPrevious;
    //显示下一页
    private boolean showNext;
    //显示第一页
    private boolean showFirstPage;
    //显示最后一页
    private boolean showLastPage;
    // 显示条数
    private int size;

    /**
     * 分页设置
     *
     * @param page
     * @param size
     * @param totalCount
     */
    public void setPagination(Integer page, Integer size, Integer totalCount) {
        //总页数
        this.totalPage = totalPage;
        // 设置总页数
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        //不正值处理
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        //页码列表设置
        this.page = page;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }
        //设置上一页
        showPrevious = page == 1 ? false : true;
        //设置下一页
        showNext = page == totalPage ? false : true;
        //设置第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //设置最后一页
        if (pages.contains(totalPage)) {
            showLastPage = false;
        } else {
            showLastPage = true;
        }

    }
}
