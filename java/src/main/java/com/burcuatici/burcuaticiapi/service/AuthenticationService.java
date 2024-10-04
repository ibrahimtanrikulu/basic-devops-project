package com.burcuatici.burcuaticiapi.service;

import com.burcuatici.burcuaticiapi.model.entity.request.SignUpRequest;
import com.burcuatici.burcuaticiapi.model.entity.request.SigninRequest;
import com.burcuatici.burcuaticiapi.model.entity.response.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
