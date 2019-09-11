package com.store.o2o.service;

import com.store.o2o.dto.LocalAuthExecution;
import com.store.o2o.entity.LocalAuth;
import com.store.o2o.exceptions.LocalAuthOperationException;

public interface LocalAuthService {
    LocalAuth getLocalAuthByUsernameAndPwd(String userName, String password);

    LocalAuth getLocalAuthByUserId(long userId);

    LocalAuthExecution createLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

    LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword)
            throws LocalAuthOperationException;
}
