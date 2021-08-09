package com.amit.springsercurity.service;

import com.amit.springsercurity.database.entity.User;
import com.amit.springsercurity.domain.UserDomain;
import com.amit.springsercurity.model.ApiException;
import com.amit.springsercurity.model.ERROR;
import com.amit.springsercurity.model.MainResponse;
import com.amit.springsercurity.model.request.AddUserRequest;
import com.amit.springsercurity.util.ICheckBCryptPasswordEncoder;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static Logger LOGGER = LogManager.getLogger(UserService.class);

    @Autowired
    private UserDomain userDomain;

    @Autowired
    private ICheckBCryptPasswordEncoder passwordEncoder;

    public MainResponse<String> createdUser(AddUserRequest request) throws ApiException{

        this.validateRequestCreatedUser(request);

        User user = new User(request);
        String passwordEncode = passwordEncoder.encode(request.getPassword());
        user.setPassword(passwordEncode);

        userDomain.saveUser(user);
        return new MainResponse<>();
    }

    protected void validateRequestCreatedUser(AddUserRequest request) throws ApiException{
        if (StringUtils.isBlank(request.getUserName())){
            throw new ApiException(ERROR.INVALID_PARAM , "username không được để trống");
        }

        if (StringUtils.isBlank(request.getPassword())){
            throw new ApiException(ERROR.INVALID_PARAM , "mật khẩu không được để trống");
        }

        if (StringUtils.isBlank(request.getName())){
            throw new ApiException(ERROR.INVALID_PARAM , "Họ tên không được để trống");
        }

        if (request.getPassword().length() < 6){
            throw new ApiException(ERROR.INVALID_PARAM , "mật khẩu không được dưới 6 kí tự");
        }

        User userByUserName = userDomain.getUserByUserName(request.getUserName());

        if (userByUserName != null){
            throw new ApiException(ERROR.INVALID_REQUEST , "Tài khoản này đã có người sử dụng, xin vui lòng sử dụng tên đăng nhập khác !");
        }
    }
}
