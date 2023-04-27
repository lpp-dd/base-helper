package org.example.base.helper.redis.lock;

import lombok.Getter;

/**
 * @author panfudong
 * @description
 */
@Getter
public enum RejectPolicyEnum {

    THROW_EXCEPTION(() -> {
        throw new UnsupportedOperationException("redis failed");
    });

    private final RejectPolicy rejectPolicy;

    RejectPolicyEnum(RejectPolicy rejectPolicy) {
        this.rejectPolicy = rejectPolicy;
    }
}
