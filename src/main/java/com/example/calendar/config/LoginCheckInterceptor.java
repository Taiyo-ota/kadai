package com.example.calendar.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

	// ログイン状態を確認する
	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler) throws Exception {

		// 現在のセッションを取得する
		HttpSession session = request.getSession(false);

		// ログインしていない場合
		if (session == null || session.getAttribute("userId") == null) {

			// ログイン画面へ移動する
			response.sendRedirect("/login");
			return false;
		}

		// ログイン済みの場合はアクセスを許可する
		return true;
	}
}