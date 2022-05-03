package com.mariadb.bss.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GimReqDto {
    private String Param;
    private String Condition;
}
