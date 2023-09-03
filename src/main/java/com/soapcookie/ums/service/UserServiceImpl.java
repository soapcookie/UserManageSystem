package com.soapcookie.ums.service;
import com.soapcookie.ums.dto.RequestDto;
import com.soapcookie.ums.dto.ResponseDto;
import com.soapcookie.ums.entity.User;
import com.soapcookie.ums.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(RequestDto requestDto) {
        User newUser = requestDto.toEntity();
        userRepository.save(newUser);
    }

    @Override
    public ResponseDto readUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // 사용자가 존재하지 않는 경우 예외를 던집니다.
            throw new RuntimeException("사용자가 존재하지 않습니다.");
        }
        return ResponseDto.fromEntity(user);
    }


    @Override
    public RequestDto updateUser(Long userId, RequestDto requestDto, Long loggedInUserId) {
        User userToUpdate = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

        // 로그인한 사용자와 수정 대상 사용자가 일치하는지 확인
        if (!userToUpdate.getId().equals(loggedInUserId)) {
            throw new RuntimeException("수정 권한이 없습니다.");
        }


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String enteredPassword = requestDto.getPassword();
        if (!passwordEncoder.matches(enteredPassword, userToUpdate.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }


        userToUpdate.setName(requestDto.getName());
        userToUpdate.setEmail(requestDto.getEmail());

        userRepository.save(userToUpdate);


        return requestDto;
}

    @Override
    public void deleteUser(Long userId, String password) {
        User userToDelete = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

        // 비밀번호 검증
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(password, userToDelete.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        userRepository.delete(userToDelete);
    }
}
