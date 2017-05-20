package com.corngo.wechat.bean.button;

import java.util.List;

/**
 * 一级菜单
 * Created by kfc on 2017/1/6.
 */
public class Button extends BaseBean{
    private List<SubButton> sub_button;

    public List<SubButton> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<SubButton> sub_button) {
        this.sub_button = sub_button;
    }
}
