package com.zhurong.platform.core.lantek.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
*  VO
*/
@Data
public class DisNestNest00000300VO implements Serializable {

        private String NstRef;
        private String ShtRef;
        private Integer RIndex;
        private Integer Quantity;
        private String Var1;
        private String Var2;
        private String Var3;
        private String Var4;
        private String Var5;
        private Integer RecState;
        private LocalDateTime CrtDate;
        private LocalDateTime LastDate;
        private String CrtUser;
        private String LastUser;
        private String Owner;
        private String RecEnt;
        private String RecOU;
        private Integer RecSec;
        private Integer CntID;
        private Integer RecID;
}
