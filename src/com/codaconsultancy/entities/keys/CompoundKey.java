package com.codaconsultancy.entities.keys;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CompoundKey implements Serializable {

    private Long userId;
    private Long bankId;

    public CompoundKey() {
    }

    public CompoundKey(Long userId, Long bankId) {
        this.userId = userId;
        this.bankId = bankId;

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    @Override
    public boolean equals(Object key) {
        if (!(key instanceof CompoundKey)) {
            return false;
        }
        Long thatUserId = ((CompoundKey) key).getUserId();
        Long thatBankId = ((CompoundKey) key).getBankId();
        return !(thatUserId == null || thatBankId == null) && userId.equals(thatUserId) && bankId.equals(thatBankId);
    }

    @Override
    public int hashCode() {
        int code = 0;
        if (this.userId != null) {
            code += userId;
        }
        if (this.bankId != null) {
            code += bankId;
        }
        return code;
    }

}
