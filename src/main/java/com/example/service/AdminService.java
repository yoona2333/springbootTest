package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Admin;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 *
 * @author Administrator
 * @since 2026-11-07 下午 12:49
 */
@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;


    public void add(Admin admin) {
        Admin dbadmin = adminMapper.selectByUsername(admin.getUsername());
        if (ObjectUtil.isNotNull(dbadmin)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        if(ObjectUtil.isEmpty(admin.getPassword())){
            admin.setPassword(Constants.DEFAULT_PASSWORD);
        }
        admin.setRole(RoleEnum.ADMIN.name());

        adminMapper.insert(admin);
    }
}
