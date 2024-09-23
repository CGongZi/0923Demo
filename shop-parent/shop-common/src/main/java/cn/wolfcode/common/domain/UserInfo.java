package cn.wolfcode.common.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by lanxw
 */
@Setter@Getter
public class UserInfo implements Serializable {
    private String  phone;
    private String nickName;
    private String head;
    private String birthDay;
    private String info;
}
