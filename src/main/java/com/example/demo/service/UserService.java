
package com.example.demo.service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.AuthRequest;

public interface UserService {

    String register(RegisterRequest req);

    String login(AuthRequest req);
}
