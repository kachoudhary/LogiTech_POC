package com.topcoder.autoinsurance.domain.repository.callback;


import com.topcoder.autoinsurance.domain.model.Policy;

public interface GetPolicyCallback extends Callback {
    void onPolicy(Policy policy);
}
