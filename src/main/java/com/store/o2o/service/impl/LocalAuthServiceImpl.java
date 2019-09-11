package com.store.o2o.service.impl;

import com.store.o2o.dao.LocalAuthDao;
import com.store.o2o.dao.PersonInfoDao;
import com.store.o2o.dto.LocalAuthExecution;
import com.store.o2o.entity.LocalAuth;
import com.store.o2o.entity.PersonInfo;
import com.store.o2o.enums.LocalAuthStateEnum;
import com.store.o2o.exceptions.LocalAuthOperationException;
import com.store.o2o.service.LocalAuthService;
import com.store.o2o.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LocalAuthServiceImpl implements LocalAuthService {
    private static Logger log = LoggerFactory.getLogger(LocalAuthServiceImpl.class);

    @Autowired
    private LocalAuthDao localAuthDao;
    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public LocalAuth getLocalAuthByUsernameAndPwd(String username, String password) {
        return localAuthDao.queryLocalByUserNameAndPwd(username, MD5.getMd5(password));
    }

    @Override
    public LocalAuth getLocalAuthByUserId(long userId) {
        return localAuthDao.queryLocalByUserId(userId);
    }

    @Override
    @Transactional
    public LocalAuthExecution createLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
        if (localAuth == null || localAuth.getPassword() == null || localAuth.getUsername() == null) {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
        }
        if(localAuth.getPersonInfo() != null && localAuth.getPersonInfo().getUserId() == null){
            try {
                localAuth.getPersonInfo().setCreateTime(new Date());
                localAuth.getPersonInfo().setEnableStatus(1);
                PersonInfo personInfo = localAuth.getPersonInfo();
                int effectedNum = personInfoDao.insertPersonInfo(personInfo);
                localAuth.setPersonInfo(personInfo);
                if (effectedNum <= 0) {
                    throw new LocalAuthOperationException("Personal info add failed");
                }
            }catch (Exception e) {
                    log.error("insertPersonInfo error:" + e.toString());
                    throw new LocalAuthOperationException("insertPersonInfo error: " + e.getMessage());
            }
        }
        try {
            // 如果之前没有绑定过平台帐号，则创建一个平台帐号与该用户绑定
            localAuth.setCreateTime(new Date());
            localAuth.setLastEditTime(new Date());
            // 对密码进行MD5加密
            localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
            int effectedNum = localAuthDao.insertLocalAuth(localAuth);
            // 判断创建是否成功
            if (effectedNum <= 0) {
                throw new LocalAuthOperationException("Failed to create account");
            } else {
                return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS, localAuth);
            }
        } catch (Exception e) {
            throw new LocalAuthOperationException("insertLocalAuth error: " + e.getMessage());
        }

    }

    @Override
    public LocalAuthExecution modifyLocalAuth(Long userId, String userName, String password, String newPassword) throws LocalAuthOperationException {
        // 非空判断，判断传入的用户Id,帐号,新旧密码是否为空，新旧密码是否相同，若不满足条件则返回错误信息
        if (userId != null && userName != null && password != null && newPassword != null
                && !password.equals(newPassword)) {
            try {
                // 更新密码，并对新密码进行MD5加密
                int effectedNum = localAuthDao.updateLocalAuth(userId, userName, MD5.getMd5(password),
                        MD5.getMd5(newPassword), new Date());
                // 判断更新是否成功
                if (effectedNum <= 0) {
                    throw new LocalAuthOperationException("updated failed");
                }
                return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
            } catch (Exception e) {
                throw new LocalAuthOperationException("updated failed:" + e.toString());
            }
        } else {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
        }
    }
}
