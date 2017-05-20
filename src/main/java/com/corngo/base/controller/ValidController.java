package com.corngo.base.controller;


import com.corngo.base.support.captcha.CaptchaUtils;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 验证控制器 ---- 手机校验/邮箱校验/图像验证码/用户名校验/手机号校验
 * Created by kfc on 2016/7/31.
 */
@Controller
@RequestMapping("valid")
public class ValidController {

	private static Logger log = LoggerFactory.getLogger(ValidController.class);

	@Resource(name = "graphCache")
	private Cache graphCache;

	/**
	 * 生成验证码图片
	 *
	 * @param session
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("captchaImage")
	public void captcha(HttpSession session, HttpServletResponse response) throws Exception {
		// 禁止缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "No-cache");
		response.setHeader("Expires", "0");
		// 指定图片Mime类型
		response.setContentType("image/jpeg");
		BufferedImage image = new BufferedImage(CaptchaUtils.width, CaptchaUtils.height, BufferedImage.TYPE_INT_BGR);
		Graphics2D g = image.createGraphics();
		// 绘制背景
		// g.setColor(CaptchaUtils.getRandomColor(200, 210));
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, CaptchaUtils.width, CaptchaUtils.height);
		// 绘制边框
		// CaptchaUtils.drawBorder(g);
		// 绘制干扰线
		CaptchaUtils.drawRandomLine(g, 200);
		// 绘制字符
		String checkCode = CaptchaUtils.drawString(2, g);
		// 保存验证码缓存
		graphCache.put(new Element(session.getId(), checkCode));
		g.dispose(); // 释放资源
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}


}
