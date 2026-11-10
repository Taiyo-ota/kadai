package com.example.calendar.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.calendar.entity.User;
import com.example.calendar.form.SignupForm;
import com.example.calendar.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	// ユーザー情報を操作するRepository
	private final UserRepository userRepository;

	// パスワードをハッシュ化・照合する
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	// メールアドレスからユーザーを検索する
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	// ユーザーを新規登録する
	public boolean register(SignupForm form) {

		// メールアドレスが登録済みか確認する
		if (userRepository.findByEmail(form.getEmail()).isPresent()) {
			return false;
		}

		// ユーザー情報を作成する
		User user = new User();
		user.setEmail(form.getEmail());
		user.setName(form.getName());

		// パスワードをハッシュ化する
		user.setPassword(passwordEncoder.encode(form.getPassword()));

		// データベースに登録する
		userRepository.save(user);

		return true;
	}

	// ログイン認証を行う
	public Optional<User> authenticate(String email, String password) {

		// メールアドレスからユーザーを検索する
		Optional<User> result = userRepository.findByEmail(email);

		// ユーザーが存在しない場合
		if (result.isEmpty()) {
			return Optional.empty();
		}

		User user = result.get();

		// パスワードを照合する
		if (!passwordEncoder.matches(password, user.getPassword())) {
			return Optional.empty();
		}

		return Optional.of(user);
	}
}