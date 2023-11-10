package com.seminfo.api.mapper;

import com.seminfo.api.dto.LoginInputDTO;
import com.seminfo.api.dto.LoginInputGoogleDTO;
import com.seminfo.domain.model.User;
import org.modelmapper.ModelMapper;

public class LoginMapper {

    public static User mapperLoginInputDTOToUser(LoginInputDTO loginInputDTO){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(loginInputDTO, User.class);
    }

    public static User mapperLoginInputGoogleDTOToUser(LoginInputGoogleDTO loginInputGoogleDTO){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(loginInputGoogleDTO, User.class);
    }
}
