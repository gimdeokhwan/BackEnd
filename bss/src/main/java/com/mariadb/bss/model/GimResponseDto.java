package com.mariadb.bss.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GimResponseDto {
    private int resultCode;
    private String message;
}
