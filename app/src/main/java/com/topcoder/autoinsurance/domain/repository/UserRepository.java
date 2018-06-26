package com.topcoder.autoinsurance.domain.repository;

import com.topcoder.autoinsurance.domain.repository.callback.GetPolicyCallback;

public interface UserRepository {

    void getPolicy(GetPolicyCallback callback);

}
