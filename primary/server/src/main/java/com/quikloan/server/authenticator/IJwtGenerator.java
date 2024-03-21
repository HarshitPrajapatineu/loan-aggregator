package com.quikloan.server.authenticator;

import java.util.Map;

import com.quikloan.server.dto.UserSession;

public interface IJwtGenerator {

	String generateToken(UserSession user);
}
